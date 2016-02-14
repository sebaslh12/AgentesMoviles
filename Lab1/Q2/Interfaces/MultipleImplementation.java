package lab1.Interfaces;

public class MultipleImplementation implements Moviles, Moviles2 {
	public static void main(String[] args) {
		Moviles instance1 = new MultipleImplementation();
		instance1.helloWorld();
		Moviles2 instance2 = new MultipleImplementation();
		instance2.MultipleInterfaces("This is the second instance with the second type");

	}

	@Override
	public void MultipleInterfaces(String s) {
		System.out.println(s);
	}

	@Override
	public void helloWorld() {
		System.out.println("The type of the instance could be of any of the interfaces that it implements");
	}
}
