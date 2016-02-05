package rasterops;

/**
 * A generic function to compute linear interpolation between two instances of
 * ValueType
 *
 * @param <ValueType>
 *            the pixel value type parameter
 */
@FunctionalInterface
public interface Lerp<ValueType> {
	/**
	 * Computes linear interpolation (weighted average) between two values v1,
	 * v2 using given v2 weight t, in other words, it returns the equivalent of
	 * v1 * (1 - t) + v2 * t
	 * 
	 * @param v1
	 *            The first value, must not be null
	 * @param v2
	 *            The second value, must not be null
	 * @param t
	 *            The weight of the second value
	 * @return A new instance of ValueType holding the result of a computation
	 *         equivalent to v1 * (1 - t) + v2 * t, not null
	 */
	/* @NotNull */ValueType lerp(/* @NotNull */ValueType v1, /* @NotNull */
			ValueType v2, double t);
}
