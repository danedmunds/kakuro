package ca.danedmunds.kakuro.solvers;

import static org.junit.Assert.*;

import static ca.danedmunds.kakuro.solvers.KakuroSums.*;

import java.awt.Point;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class KakuroSumsTest {
	
	@Test
	public void whatTheWhat(){
		assertNotNull(KakuroSums.getSums(19, 3));
	}
	
	@Test
	public void testUnion(){
		assertSetEquals(set(1, 2), KakuroSums.union(set(), set(1, 2)));
		assertSetEquals(set(1, 2), KakuroSums.union(set(1, 2), set()));
		assertSetEquals(set(1, 2), KakuroSums.union(set(1, 2), set(1, 2)));
		assertSetEquals(set(1, 2, 3), KakuroSums.union(set(1, 2), set(1, 2, 3)));
		assertSetEquals(set(1, 2, 3), KakuroSums.union(set(1, 2, 3), set(1, 2)));
		assertSetEquals(set(1, 2, 3, 4), KakuroSums.union(set(1, 2, 3), set(1, 2, 4)));
		assertSetEquals(set(1, 2, 3, 4), KakuroSums.union(set(1, 2), set(3, 4)));
	}
	
	private void assertSetEquals(Set<Integer> one, Set<Integer> two) {
		assertEquals(one.size(), two.size());
		for(Integer value : one)
			assertTrue(two.contains(value));
	}
	
	@Test
	public void testIntersection(){
		assertSetEquals(set(1), KakuroSums.intersection(set(1), set(1, 2)));
		assertSetEquals(set(1), KakuroSums.intersection(set(1), set(1)));
		assertSetEquals(set(), KakuroSums.intersection(set(3), set(1, 2)));
		assertSetEquals(set(3, 4), KakuroSums.intersection(set(1, 2, 3, 4), set(3, 4, 5, 6)));
	}
	
	@Test
	public void testSizesAsAdvertized(){
		for(int sum = 1; sum <= 45; ++sum){
			
			for(int cellCount = 1; cellCount <= 9; ++cellCount){
				List<Set<Integer>> cells = KakuroSums.getSums(sum, cellCount) ;
				if(cells == null) {
					continue;
				}
				
				for(Set<Integer> set : cells){
					//make sure the length matches
					assertEquals(cellCount, set.size());
					
					int total = 0;
					for(Integer val : set)
						total += val;
					
					//make sure it totals to what it's supposed to
					assertEquals(sum, total);
					System.out.println(toString(set)+" -> "+total);
				}
				
				System.out.println(toString(KakuroSums.mergedPossibilitiesTable.get(new Point(sum, cellCount))));
				System.out.println("-----------");
			}
		}
		
		System.out.flush();
	}
	
	private String toString(Set<Integer> set){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("[");
		for(Integer value : set) 
			buffer.append(value).append(", ");
		
		if(set.size() > 0)
			buffer.delete(buffer.length() - 2, buffer.length());
		
		buffer.append("]");
		
		return buffer.toString();
	}

}
