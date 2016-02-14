package lab1.UnitTest;

import org.junit.Test;

public class MapsTest {

	@Test
	public void testfor10() {
		Maps testing10 = new Maps();

		testing10.testInsert(testing10.intHash, 10);
		testing10.testInsert(testing10.intLinked, 10);

		testing10.testGet(testing10.intHash, 10);
		testing10.testGet(testing10.intLinked, 10);

		testing10.testRemove(testing10.intHash, 10);
		testing10.testRemove(testing10.intLinked, 10);

	}

	@Test
	public void testfor100() {
		Maps testing100 = new Maps();

		testing100.testInsert(testing100.intHash, 100);
		testing100.testInsert(testing100.intLinked, 100);

		testing100.testGet(testing100.intHash, 100);
		testing100.testGet(testing100.intLinked, 100);

		testing100.testRemove(testing100.intHash, 100);
		testing100.testRemove(testing100.intLinked, 100);

	}

	@Test
	public void testfor1000() {
		Maps testing1000 = new Maps();

		testing1000.testInsert(testing1000.intHash, 1000);
		testing1000.testInsert(testing1000.intLinked, 1000);

		testing1000.testGet(testing1000.intHash, 1000);
		testing1000.testGet(testing1000.intLinked, 1000);

		testing1000.testRemove(testing1000.intHash, 1000);
		testing1000.testRemove(testing1000.intLinked, 100);

	}

}
