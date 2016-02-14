package lab1.AbstractClass;

public class AbstractInstance extends Moviles {
	public static void main(String[] args) {
		Moviles instance1 = new AbstractInstance();
		instance1.abstractMethod();
		instance1.nonAbstractMethod();		
	}
	@Override
	public void abstractMethod() {
		System.out.println("Implementation of the abstract method");
	}
}
