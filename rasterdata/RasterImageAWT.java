package rasterdata;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.function.Function;

/**
 * RasterImage and Presentable implementation using BufferedImage and Graphics
 * in java.awt. The pixel value type is converted to and from integer using the
 * given functions.
 *
 * @param <ValueType>
 *            the pixel value type parameter
 */
public class RasterImageAWT<ValueType> implements RasterImage<ValueType>,
		Presentable<Graphics> {

	private final/* @NotNull */BufferedImage img;
	private final/* @NotNull */Function<ValueType, Integer> toInteger;
	private final/* @NotNull */Function<Integer, ValueType> fromInteger;

	/**
	 * Initializes all object attributes
	 * 
	 * @param img
	 *            A BufferedImage instance that will be used to store the pixels
	 *            in an int RGB(A) representation
	 * @param toInteger
	 *            A function that converts the pixel value type to an integer
	 * @param fromInteger
	 *            A function that converts an integer to the pixel value type
	 */
	public RasterImageAWT(final/* @NotNull */BufferedImage img,
			final/* @NotNull */Function<ValueType, Integer> toInteger,
			final/* @NotNull */Function<Integer, ValueType> fromInteger) {
		this.img = img;
		this.toInteger = toInteger;
		this.fromInteger = fromInteger;
	}

	@Override
	public void setPixel(final int column, final int row,
			final/* @NotNull */ValueType value) {
		if (column >= 0 && column < img.getWidth() && row >= 0
				&& row < img.getHeight())
			img.setRGB(column, row, toInteger.apply(value));
	}

	@Override
	public/* @NotNull */Optional<ValueType> getPixel(final int column,
			final int row) {
		if (column >= 0 && column < img.getWidth() && row >= 0
				&& row < img.getHeight())
			return Optional.of(fromInteger.apply(img.getRGB(column, row)));
		return Optional.empty();
	}

	@Override
	public void present(final Graphics /* @NotNull */presenterDevice) {
		presenterDevice.drawImage(img, 0, 0, null);
	}

}
