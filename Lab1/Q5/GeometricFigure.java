package lab1.Figures;

public abstract class GeometricFigure {
	public String name;
	public String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public abstract float calculatePerimeter();
	public abstract float calculateArea();

}
