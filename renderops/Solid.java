package renderops;

import java.util.List;

/**
 * An abstraction of a geometric mesh with multiple parts of varying geometry
 * primitive topologies (connectivities) stored within a single vertex and index
 * buffer
 *
 * @param <VertexType>
 *            A vertex type parameter
 * @param <TopologyType>
 *            A type parameter specifying possible topologies
 */
public interface Solid<VertexType, TopologyType> {
	/**
	 * An abstraction of a mesh part description
	 *
	 * @param <TopologyType>
	 *            A type parameter specifying possible topologies
	 */
	public static interface Part<TopologyType> {
		/**
		 * Returns the first index within the index buffer of the first
		 * primitive of this mesh part
		 * 
		 * @return Integer in [0; getIndices().size())
		 */
		int getStartIndex();

		/**
		 * Returns the number of primitives of this mesh part
		 * 
		 * @return Positive integer
		 */
		int getPrimitiveCount();

		/**
		 * Returns the primitive connectivity (topology) of this mesh part
		 * 
		 * @return Instance of the TopologyType, not null
		 */
		/* @NotNull */TopologyType getTopology();
	}

	/**
	 * Returns the vertex buffer with all mesh vertices
	 * 
	 * @return Instance of List, not null
	 */
	/* @NotNull */List<VertexType> getVertices();

	/**
	 * Returns the index buffer with indices of all mesh parts
	 * 
	 * @return Instance of List, not null
	 */
	/* @NotNull */List<Integer> getIndices();

	/**
	 * Returns a list of all the mesh parts
	 * 
	 * @return Instance of List, not null
	 */
	/* @NotNull */List<Part<TopologyType>> getParts();
}
