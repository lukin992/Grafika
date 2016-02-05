package rasterops;

import rasterdata.RasterImage;
import static java.lang.Math.*;

/**
 * DDA implementation of a line rasterizer with linear interpolation using a
 * RasterImage to consume pixels and a Lerp function to perform the linear
 * interpolation
 *
 * @param <ValueType>
 *            the pixel value type parameter
 */
public class LineRasterizerLerpDDA<ValueType> implements
		LineRasterizerLerp<ValueType> {
	private final/* @NotNull */RasterImage<ValueType> img;
	private final/* @NotNull */Lerp<ValueType> lerp;

	/**
	 * Initializes all object attributes
	 * 
	 * @param img
	 *            RasterImage to consume pixels
	 * @param lerp
	 *            Lerp function to perform linear interpolation
	 */
	public LineRasterizerLerpDDA(final/* @NotNull */RasterImage<ValueType> img,
			final/* @NotNull */Lerp<ValueType> lerp) {
		this.img = img;
		this.lerp = lerp;
	}

	@Override
	public void drawLine(final int c1, final int r1, final int c2,
			final int r2, final/* @NotNull */ValueType value1,
			final/* @NotNull */ValueType value2) {
		final int dc = c2 - c1, dr = r2 - r1;
		final int absmax = max(abs(dc), abs(dr));
		final double ddc = (double) dc / absmax;
		final double ddr = (double) dr / absmax;
		final int count = absmax + 1;
		double c = c1 + 0.5;
		double r = r1 + 0.5;
		for (int i = 0; i < count; i++) {
			final double t = (double) i / (count - 1);
			final/* @NotNull */ValueType value = lerp.lerp(value1, value2, t);
			img.setPixel((int) c, (int) r, value);
			c += ddc;
			r += ddr;
		}
	}

}
