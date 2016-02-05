package rasterdata;

import java.util.Optional;

/**
 * An abstraction of a raster of pixels. Generic class: the data type of pixels
 * is not specified - it is a type parameter of the generic class
 *
 * @param <ValueType>
 *            the pixel value type parameter
 */
public interface RasterImage<ValueType> {
	/**
	 * Sets the pixel at the column and row address to the given value. Invalid
	 * addresses are ignored.
	 * 
	 * @param column
	 *            Column address of the pixel to be set (pixel address within
	 *            row)
	 * @param row
	 *            Row address of the pixel to be set
	 * @param value
	 *            Value of the pixel to be set, must not be null.
	 */
	void setPixel(int column, int row, /* @NotNull */ValueType value);

	/**
	 * Returns the value of the pixel at the column and row address
	 * 
	 * @param column
	 *            Column address of the pixel to be returned (pixel address
	 *            within row)
	 * @param row
	 *            Row address of the pixel to be returned
	 * @return Returns an empty optional if pixel address is invalid.
	 */
	/* @NotNull */Optional<ValueType> getPixel(int column, int row);
}
