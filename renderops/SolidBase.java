package renderops;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic implementation of the Solid interface
 *
 * @param <VertexType>
 *            A vertex type parameter
 * @param <TopologyType>
 *            A type parameter specifying possible topologies
 */
public abstract class SolidBase<VertexType, TopologyType> implements Solid<VertexType, TopologyType> {
	public static class PartImpl<TopologyType> implements Part<TopologyType> {
		private final int startIndex, primitiveCount;
		private final TopologyType topology;
		public PartImpl(int startIndex, int primitiveCount, TopologyType topology) {
			this.startIndex = startIndex;
			this.primitiveCount = primitiveCount;
			this.topology = topology;
		}
		@Override
		public int getStartIndex() {
			return startIndex;
		}
		@Override
		public int getPrimitiveCount() {
			return primitiveCount;
		}
		@Override
		public TopologyType getTopology() {
			return topology;
		}
	}
	
	protected final /*@NotNull*/ List<VertexType> vertices = new ArrayList<>();
	protected final /*@NotNull*/ List<Integer> indices = new ArrayList<>();
	protected final /*@NotNull*/ List<Part<TopologyType>> parts = new ArrayList<>();

	@Override
	public /*@NotNull*/ List<VertexType> getVertices() {
		return vertices;
	}

	@Override
	public /*@NotNull*/ List<Integer> getIndices() {
		return indices;
	}

	@Override
	public /*@NotNull*/ List<Part<TopologyType>> getParts() {
		return parts;
	}

}
