package lab1.StrategyPattern;

import java.util.ArrayList;

public class Sorter {
	private ArrayList<Student> students;

	public Sorter() {
		this.students = new ArrayList<Student>();
		this.students.add(new Student(31, "Fernanda", "Alvarez", 17, "career"));
		this.students.add(new Student(122, "Andres", "Salazar", 20, "career1"));
		this.students.add(new Student(43, "Felipe", "Torres", 30, "career"));
		this.students.add(new Student(413, "Julio", "Obando", 22, "career"));
		this.students.add(new Student(54, "Cesar", "Idrobo", 25, "career"));
		this.students.add(new Student(65, "Carlos", "Urrutia", 23, "career"));
		this.students.add(new Student(677, "Mauricio", "Vivas", 19, "career"));
		this.students.add(new Student(418, "Jhon", "Enriquez", 20, "career"));
		this.students.add(new Student(92, "Brian", "Rojas", 28, "career"));
		this.students.add(new Student(120, "Carlos", "Jimenez", 21, "career"));
		this.students.add(new Student(111, "Ana", "Lopez", 24, "career"));
		this.students.add(new Student(152, "Lucia", "Mejia", 21, "career"));
		this.students.add(new Student(173, "Sofia", "Benavides", 31, "career"));
		this.students.add(new Student(146, "Luis", "Carmona", 17, "career"));
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public void sortingStudents(IComparisonStrategy strategy) {
		strategy.sort(students);
	}
}
