package lab1.Figures;

public class Circle extends GeometricFigure {
	private float ratio;
	private float pi;

	public Circle(float ratio, float pi) {
		super();
		this.name = "Circle";
		this.ratio = ratio;
		this.pi = pi;
		this.description = "4 sides, with Pi = " + Float.toString(this.pi) + " and Ratio = "
				+ Float.toString(this.ratio);
	}

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	public float getPi() {
		return pi;
	}

	public void setPi(float pi) {
		this.pi = pi;
	}

	@Override
	public float calculatePerimeter() {
		return 2*this.pi*this.ratio;
	}

	@Override
	public float calculateArea() {
		return (float) (this.pi*(Math.pow(this.ratio,2)));
	}
	
	public float calculateArea(float pi) {
		return (float) (pi*(Math.pow(this.ratio,2)));
	}

}
