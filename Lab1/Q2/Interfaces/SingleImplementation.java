package lab1.Interfaces;

//Implementing an Interface
public class SingleImplementation implements Moviles{
	public static void main(String[] args) {
		Moviles instance = new SingleImplementation();
		instance.helloWorld();
	}
	//Method Implementation
	@Override
	public void helloWorld() {
		//Accesing to the variable from the interface
		System.out.println(Moviles.Hello + " This is Lab1");
		//Accesing to the variable from the implementation		
		System.out.println(SingleImplementation.Hello + " This is Lab1");
	}

}