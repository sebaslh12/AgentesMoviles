package lab1.UnitTest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Maps {
	public HashMap<Integer,Integer> intHash;
	public LinkedHashMap<Integer,Integer> intLinked;
	
	public void testInsert(Map<Integer,Integer> t, int c) {
		if (t.size() >= 0) {
			for (int i = 0; i < c; i++) {
				t.put(i, i);
			}
		}
	}
	public void testRemove(Map<Integer,Integer> t, int c) {
		if (t.size() >= c) {
			for (int i = 0; i < c; i++) {
				t.remove(i);
			}
		}
	}
	public void testGet(Map<Integer,Integer> t, int c) {
		if (t.size() >= c) {
			for (int i = 0; i < c; i++) {
				t.get(i);
			}
		}
	}

}
