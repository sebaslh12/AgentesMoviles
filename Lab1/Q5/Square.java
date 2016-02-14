package lab1.Figures;

public class Square extends GeometricFigure {
	private float edge;

	public Square(float edge) {
		super();
		this.edge = edge;
	}

	public float getEdge() {
		return edge;
	}

	public void setEdge(float edge) {
		this.edge = edge;
	}

	@Override
	public float calculatePerimeter() {
		return 4*this.edge;
	}

	@Override
	public float calculateArea() {
		return (float) Math.pow(this.edge, 2);
	}

}
