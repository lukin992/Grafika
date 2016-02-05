package rasterops;

public interface LineRasterizer<ValueType> {
	void drawLine(int c1, int r1, int c2, int r2, /* @NotNull */ValueType value);
}
