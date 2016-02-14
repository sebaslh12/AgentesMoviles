package lab1.StrategyPattern;

import org.junit.Test;

public class IdSorterTest {

	@Test
	public void Idtest() {
		Sorter test = new Sorter();
		test.sortingStudents(new SortId());
	}

}
