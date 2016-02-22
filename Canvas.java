import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import rasterdata.*;
import rasterops.*;
import renderops.*;
import transforms.*;

public class Canvas {

	private final/* @NotNull */JFrame frame;
	private final/* @NotNull */JPanel panel;
	private final/* @NotNull */BufferedImage img;

	private final/* @NotNull */RasterImage<Col> raster;
	private final/* @NotNull */Presentable<Graphics> presentable;
	private final/* @NotNull */LineRasterizerLerp<Col> liner;

	private int startC, startR;

	public Canvas(int width, int height) {
		frame = new JFrame();
		frame.setTitle("UHK FIM PGRF : Canvas");
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);


		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final/* @NotNull */RasterImageAWT<Col> rasterAwt = new RasterImageAWT<>(
				img, 
				(Col i) -> {
					return i.getRGB();
				}, 
				(Integer i) -> {
					return new Col(i);
				});
		raster = rasterAwt;
		presentable = rasterAwt;
		liner = new LineRasterizerLerpDDA<>(raster, 
				(Col v1, Col v2, double t) -> {
					return v1.mul(1-t).add(v2.mul(t));
				});
		
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent ev) {
				if (ev.getButton() == MouseEvent.BUTTON1) {
					startC = ev.getX();
					startR = ev.getY();
				} else {
				}

			}
		});
		panel.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent ev) {
				// clear(0x2f2f2f);
				liner.drawLine(startC, startR, ev.getX(), ev.getY(), new Col(0, 0, 255),
						new Col(255, 0, 0));
				present();
			}
		});
	}

	public void clear(int color) {
		Graphics gr = img.getGraphics();
		gr.setColor(new Color(color));
		gr.fillRect(0, 0, img.getWidth(), img.getHeight());
	}

	public void present() {
		if (panel.getGraphics() != null)
			presentable.present(panel.getGraphics());
	}

	public void draw() {
		clear(0x2f2f2f);
		raster.setPixel(10, 10, new Col(65535));
		new Renderer<Point3D, Col>(
				img.getWidth(), img.getHeight(),
				(Point3D p) -> { 
					return p; 
				},
				(Point3D p) -> { 
					return new Col(0xffff); 
				},
				liner).render(new Cube(), new Mat4Identity());;
	}

	public void start() {
		draw();
		present();
	}

	public static void main(String[] args) {
		Canvas canvas = new Canvas(800, 600);
		SwingUtilities.invokeLater(() -> {
			SwingUtilities.invokeLater(() -> {
				SwingUtilities.invokeLater(() -> {
					SwingUtilities.invokeLater(() -> {
						canvas.start();
					});
				});
			});
		});
	}

}