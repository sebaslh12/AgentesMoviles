package lab1.UnitTest;

import org.junit.Test;

public class IndexOperatorsTest {

	@Test
	public void testfor10() {
		IndexOperators testing10 = new IndexOperators();
		testing10.testInsert(testing10.intArray, 10);
		testing10.testInsert(testing10.intLinked, 10);
		testing10.testInsert(testing10.intVector, 10);

		testing10.testGet(testing10.intArray, 10);
		testing10.testGet(testing10.intLinked, 10);
		testing10.testGet(testing10.intVector, 10);

		testing10.testRemove(testing10.intArray, 10);
		testing10.testRemove(testing10.intLinked, 10);
		testing10.testRemove(testing10.intVector, 10);

	}

	@Test
	public void testfor100() {
		IndexOperators testing100 = new IndexOperators();

		testing100.testInsert(testing100.intArray, 100);
		testing100.testInsert(testing100.intLinked, 100);
		testing100.testInsert(testing100.intVector, 100);

		testing100.testGet(testing100.intArray, 100);
		testing100.testGet(testing100.intLinked, 100);
		testing100.testGet(testing100.intVector, 100);

		testing100.testRemove(testing100.intArray, 100);
		testing100.testRemove(testing100.intLinked, 100);
		testing100.testRemove(testing100.intVector, 100);

	}

	@Test
	public void testfor1000() {
		IndexOperators testing1000 = new IndexOperators();

		testing1000.testInsert(testing1000.intVector, 1000);
		testing1000.testInsert(testing1000.intVector, 1000);
		testing1000.testInsert(testing1000.intVector, 1000);

		testing1000.testGet(testing1000.intArray, 1000);
		testing1000.testGet(testing1000.intLinked, 1000);
		testing1000.testGet(testing1000.intVector, 1000);

		testing1000.testRemove(testing1000.intArray, 1000);
		testing1000.testRemove(testing1000.intLinked, 100);
		testing1000.testRemove(testing1000.intVector, 1000);
	}

}
