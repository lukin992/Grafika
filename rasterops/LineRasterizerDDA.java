package rasterops;

import rasterdata.RasterImage;
import static java.lang.Math.*;

public class LineRasterizerDDA<ValueType> implements LineRasterizer<ValueType> {
	private final/* @NotNull */RasterImage<ValueType> img;

	public LineRasterizerDDA(final/* @NotNull */RasterImage<ValueType> img) {
		this.img = img;
	}

	@Override
	public void drawLine(int c1, int r1, int c2, int r2, ValueType value) {
		final int dc = c2 - c1, dr = r2 - r1;
		final int absmax = max(abs(dc), abs(dr));
		final double ddc = (double) dc / absmax;
		final double ddr = (double) dr / absmax;
		final int count = absmax + 1;
		double c = c1 + 0.5;
		double r = r1 + 0.5;
		for (int i = 0; i < count; i++) {
			img.setPixel((int) c, (int) r, value);
			c += ddc;
			r += ddr;
		}
	}

}
