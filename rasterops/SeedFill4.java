package rasterops;

import java.util.function.Function;

import rasterdata.RasterImage;

public class SeedFill4<ValueType> implements SeedFill<ValueType> {
	private final/* @NotNull */RasterImage<ValueType> img;

	public SeedFill4(/* @NotNull */RasterImage<ValueType> img) {
		this.img = img;
	}

	@Override
	public void fill(final int c, final int r,
			final/* @NotNull */Function<ValueType, Boolean> isInArea,
			final/* @NotNull */ValueType newValue) throws Exception {
		new Object() {
			void fill(final int c, final int r) {
				img.getPixel(c, r).ifPresent((ValueType pixel) -> {
					if (isInArea.apply(pixel)) {
						img.setPixel(c, r, newValue);
						fill(c, r - 1);
						fill(c + 1, r);
						fill(c, r + 1);
						fill(c - 1, r);
					}
				});
			}
		}.fill(c, r);
	}

}
