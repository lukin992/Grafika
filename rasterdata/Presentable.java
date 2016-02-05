/**
 * 
 */
package rasterdata;

/**
 * An abstraction of an object that can be presented using another object.
 *
 */
public interface Presentable<PresenterType> {
	/**
	 * Presents the contents of the object to the given presenter device
	 * 
	 * @param presenterDevice
	 *            Must not be null.
	 */
	void present(PresenterType /* @NotNull */presenterDevice);
}
