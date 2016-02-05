package rasterops;

/**
 * An abstraction of a line rasterizing algorithm that performs linear
 * interpolation between two endpoint pixel values.
 *
 * @param <ValueType>
 *            the pixel value type parameter
 */
public interface LineRasterizerLerp<ValueType> {
	/**
	 * The rasterization algorithm
	 * 
	 * @param c1
	 *            The column address of the first endpoint
	 * @param r1
	 *            The row address of the first endpoint
	 * @param c2
	 *            The column address of the second endpoint
	 * @param r2
	 *            The row address of the second endpoint
	 * @param value1
	 *            The pixel value of the first endpoint, must not be null
	 * @param value2
	 *            The pixel value of the second endpoint, must not be null
	 */
	void drawLine(int c1, int r1, int c2, int r2,
	/* @NotNull */ValueType value1,
	/* @NotNull */ValueType value2);
}
