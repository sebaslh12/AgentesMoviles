package lab1.StrategyPattern;

import org.junit.Test;

public class NameSortTest {

	@Test
	public void Nametest() {
		Sorter test = new Sorter();
		test.sortingStudents(new SortName());
	}

}
