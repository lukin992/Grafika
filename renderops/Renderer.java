package renderops;

import java.util.List;
import java.util.function.Function;

import rasterops.LineRasterizerLerp;
import transforms.*;

public class Renderer<VertexType, PixelType> {
	private final int width, height;
	private final/* @NotNull */Function<VertexType, Point3D> getPoint3D;
	private final/* @NotNull */Function<VertexType, PixelType> getPixelType;
	private final/* @NotNull */LineRasterizerLerp<PixelType> liner;

	public Renderer(final int width, final int height,
			final/* @NotNull */Function<VertexType, Point3D> getPoint3D,
			final/* @NotNull */Function<VertexType, PixelType> getPixelType,
			final/* @NotNull */LineRasterizerLerp<PixelType> liner) {
		this.width = width;
		this.height = height;
		this.getPoint3D = getPoint3D;
		this.getPixelType = getPixelType;
		this.liner = liner;
	}

	public void render(/* @NotNull */final Solid<VertexType, Topology> solid,
			final/* @NotNull */Mat4 mat) {
		final/* @NotNull */List<VertexType> vertices = solid.getVertices();
		final/* @NotNull */List<Integer> indices = solid.getIndices();
		solid.getParts()
				.stream()
				.forEach(
						(final/* @NotNull */Solid.Part<Topology> part) -> {
							switch (part.getTopology()) {
							case LINE_LIST:
								renderLineList(vertices, indices,
										part.getStartIndex(),
										part.getPrimitiveCount(), mat);
								break;
							case TRIANGLE_LIST:
								renderTriangleList(vertices, indices,
										part.getStartIndex(),
										part.getPrimitiveCount(), mat);
								break;
							}
						});
	}

	public void renderLineList(final/* @NotNull */List<VertexType> vertices,
			final/* @NotNull */List<Integer> indices, final int startIndex,
			final int primitiveCount, final/* @NotNull */Mat4 mat) {
		if (indices.size() % 2 == 1) {
			System.err.println("Odd number of indices.");
			return;
		}
		for (int i = startIndex; i < startIndex + 2 * primitiveCount; i += 2) {
			int i1 = indices.get(i);
			int i2 = indices.get(i + 1);
			if (i1 < 0 || i1 >= vertices.size() || i2 < 0
					|| i2 >= vertices.size()) {
				System.err.println("Invalid index.");
				return;
			}
			VertexType v1 = vertices.get(i1);
			VertexType v2 = vertices.get(i2);
			if (v1 != null && v2 != null)
				transformLine(v1, v2, mat);
		}
	}

	private void transformLine(final/* @NotNull */VertexType v1,
			final/* @NotNull */VertexType v2, final/* @NotNull */Mat4 mat) {
		final Point3D p1 = getPoint3D.apply(v1).mul(mat);
		final Point3D p2 = getPoint3D.apply(v2).mul(mat);
		if (p1.w <= 0 || p2.w <= 0) {
			// zde interpolaci oriznout za pomoci Lerp
			return; // zatim misto interpolace, zbabele
		}
		final Vec3D vt1 = p1.dehomog();
		final Vec3D vt2 = p2.dehomog();

		int x1 = (int) ((vt1.x + 1) * 0.5 * (width - 1));
		int y1 = (int) ((-vt1.y + 1) * 0.5 * (height - 1));
		int x2 = (int) ((vt2.x + 1) * 0.5 * (width - 1));
		int y2 = (int) ((-vt2.y + 1) * 0.5 * (height - 1));

		liner.drawLine(x1, y1, x2, y2, getPixelType.apply(v1),
				getPixelType.apply(v2));
	}

	public void renderTriangleList(
			final/* @NotNull */List<VertexType> vertices,
			final/* @NotNull */List<Integer> indices, final int startIndex,
			final int primitiveCount, final/* @NotNull */Mat4 mat) {
		for (int i = startIndex; i < startIndex + 3 * primitiveCount; i += 3) {
			final VertexType v1 = vertices.get(indices.get(i));
			final VertexType v2 = vertices.get(indices.get(i + 1));
			final VertexType v3 = vertices.get(indices.get(i + 2));
		}
		System.err.println("Not implemented");
	}

}
