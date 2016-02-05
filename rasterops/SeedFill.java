package rasterops;

import java.util.function.Function;

public interface SeedFill<ValueType> {
	void fill(final int c, final int r,
			final/* @NotNull */Function<ValueType, Boolean> isInArea,
			final/* @NotNull */ValueType newValue) throws Exception;
}
