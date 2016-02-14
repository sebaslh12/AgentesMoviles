package lab1.Figures;

public class Rectangular extends GeometricFigure {
	private float height;
	private float width;

	public Rectangular(float height, float width) {
		super();
		this.name = "Rectangle";
		this.height = height;
		this.width = width;
		this.description = "4 sides, with width = " + Float.toString(this.width) + " and height = "
				+ Float.toString(this.height);
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public float calculatePerimeter() {
		return (2 * this.height) + (2 * this.width);
	}

	@Override
	public float calculateArea() {
		return this.height * this.width;
	}

}
