package lab1.UnitTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class IndexOperators {
	public ArrayList<Integer> intArray;
	public LinkedList<Integer> intLinked;
	public Vector<Integer> intVector;

	public void testInsert(List<Integer> t, int c) {
		if (t.size() >= 0) {
			for (int i = 0; i < c; i++) {
				t.add(i);
			}
		}
	}

	
	public void testRemove(List<Integer> t, int c) {
		if (t.size() >= c) {
			for (int i = 0; i < c; i++) {
				t.remove(i);
			}
		}
	}

	public void testGet(List<Integer> t, int c) {
		if (t.size() >= c) {
			for (int i = 0; i < c; i++) {
				t.get(i);
			}
		}
	}

}
