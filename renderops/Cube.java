package renderops;

import transforms.Point3D;

public class Cube extends SolidBase<Point3D, Topology> {
	public Cube() {
		vertices.add(new Point3D(0,0,0));
		vertices.add(new Point3D(1,0,0));
		vertices.add(new Point3D(1,1,0));
		vertices.add(new Point3D(0,1,0));
		vertices.add(new Point3D(0,0,1));
		vertices.add(new Point3D(1,0,1));
		vertices.add(new Point3D(1,1,1));
		vertices.add(new Point3D(0,1,1));
		
		for (int i = 0; i < 4; i++) {
			indices.add(i);
			indices.add((i + 1) % 4);
			indices.add(i);
			indices.add(i + 4);
			indices.add(i + 4);
			indices.add((i + 1) % 4 + 4);
		}
		
		parts.add(new PartImpl<>(0, 12, Topology.LINE_LIST));
	}

}
