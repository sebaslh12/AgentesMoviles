package lab1.StrategyPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortLastName implements IComparisonStrategy, Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		return (s1.getLastName().compareTo(s2.getLastName()));
	}

	@Override
	public void sort(ArrayList<Student> students) {
		Collections.sort(students, new SortLastName());
	}
	
	
}
