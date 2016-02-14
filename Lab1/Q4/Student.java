package lab1.StrategyPattern;

import java.util.Comparator;

public class Student implements Comparable<Student> {
	int id;
	String name;
	String lastName;
	int age;
	String career;

	public Student(int id, String name, String lastName, int age, String career) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.career = career;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getCareer() {
		return career;
	}

	public static Comparator<Student> ageComparator = new Comparator<Student>() {
		@Override
		public int compare(Student s1, Student s2) {
			return (s1.getAge() - s2.getAge());
		}
	};
	
	public static Comparator<Student> nameComparator = new Comparator<Student>() {
		@Override
		public int compare(Student s1, Student s2) {
			return (s1.getName().compareTo(s2.getName()));
		}
	};

	@Override
	public int compareTo(Student s1) {
		return (this.id - s1.id);
	}
}
