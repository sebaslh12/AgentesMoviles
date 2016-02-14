package lab1.StrategyPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortName implements IComparisonStrategy, Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		return (s1.getName().compareTo(s2.getName()));
	}
	@Override
	public void sort(ArrayList<Student> students) {
		Collections.sort(students, new SortName());
	}

}
