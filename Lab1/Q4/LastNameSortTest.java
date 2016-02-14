package lab1.StrategyPattern;

import org.junit.Test;

public class LastNameSortTest {

	@Test
	public void LastNametest() {
			Sorter test = new Sorter();
			test.sortingStudents(new SortLastName());
	}

}
