package ca.danedmunds.kakuro.solvers;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class KakuroSums {
	
	public static HashMap<Point, List<Set<Integer>>> sumLookupTable;
	public static HashMap<Point, Set<Integer>> mergedPossibilitiesTable;
	
	static {
		generateLookupTable();
	}
	
	public static List<Set<Integer>> getSums(int sum, int numberOfCells){
		return sumLookupTable.get(new Point(sum, numberOfCells));
	}
	
	public static Set<Integer> getAllPossibilities(int sum, int numberOfCells) {
		return mergedPossibilitiesTable.get(new Point(sum, numberOfCells));
	}
	
	public static Set<Integer> multiIntersection(List<Set<Integer>> sets) {
		if(sets.size() == 0)
			return new HashSet<Integer>();
		
		Set<Integer> result = new HashSet<Integer>(sets.get(0));
		for(int i = 1; i < sets.size(); ++i)
			result = intersection(result, sets.get(i));
			
		return result;
	}
	
	public static Set<Integer> intersection(Set<Integer> one, Set<Integer> two) {
		Set<Integer> result = new HashSet<Integer>();
		
		for(Integer value : one) {
			if(two.contains(value))
				result.add(value);
		}
		
		return result;
	}
	
	public static Set<Integer> multiUnion(List<Set<Integer>> sets) {
		Set<Integer> result = new HashSet<Integer>();
		for(Set<Integer> set : sets)
			result.addAll(set);
		
		return result;
	}
	
	public static Set<Integer> union(Set<Integer> one, Set<Integer> two) {
		Set<Integer> result = new HashSet<Integer>();
		result.addAll(one);
		result.addAll(two);
		
		return result;
	}
	
	private static void generateLookupTable() {
		sumLookupTable = new HashMap<Point, List<Set<Integer>>>();
		mergedPossibilitiesTable = new HashMap<Point, Set<Integer>>();
		
		sumsFor1();
		sumsFor2();
		sumsFor3();
		sumsFor4();
		sumsFor5();
		sumsFor6();
		sumsFor7();
		sumsFor8();
		sumsFor9();
		sumsFor10();
		sumsFor11();
		sumsFor12();
		sumsFor13();
		sumsFor14();
		sumsFor15();
		sumsFor16();
		sumsFor17();
		sumsFor18();
		sumsFor19();
		sumsFor20();
		sumsFor21();
		sumsFor22();
		sumsFor23();
		sumsFor24();
		sumsFor25();
		sumsFor26();
		sumsFor27();
		sumsFor28();
		sumsFor29();
		sumsFor30();
		sumsFor31();
		sumsFor32();
		sumsFor33();
		sumsFor34();
		sumsFor35();
		sumsFor36();
		sumsFor37();
		sumsFor38();
		sumsFor39();
		sumsFor40();
		sumsFor41();
		sumsFor42();
		sumsFor43();
		sumsFor44();
		sumsFor45();
	}		
	
	private static void storeSums(Point point, List<Set<Integer>> sums) {
		sumLookupTable.put(point, sums);
		mergedPossibilitiesTable.put(point, multiUnion(sums));
	}
	
	public static List<Set<Integer>> list(Set<Integer> ... sets) {
		return Collections.unmodifiableList(Arrays.asList(sets));
	}
	
	public static Set<Integer> set(int ... ints) {
		Set<Integer> result = new HashSet<Integer>();
		for(int a : ints) 
			result.add(a);
		
		return Collections.unmodifiableSet(result);
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor1() {
		storeSums(new Point(1, 1), list(	set(1)));
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor2() {
		storeSums(new Point(2, 1), list(	set(2)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor3() {
		storeSums(new Point(3, 1), list(	set(3)));
		storeSums(new Point(3, 2), list(	set(1, 2)));
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor4() {
		storeSums(new Point(4, 1), list(	set(4)));
		storeSums(new Point(4, 2), list(	set(1, 3)));
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor5() {
		storeSums(new Point(5, 1), list(	set(5)));
		storeSums(new Point(5, 2), list(	set(1, 4),
											set(2, 3)));
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor6() {
		storeSums(new Point(6, 1), list(	set(6)));
		storeSums(new Point(6, 2), list(	set(1, 5), 
											set(2, 4)));
		storeSums(new Point(6, 3), list(	set(1, 2, 3)));
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor7() {
		storeSums(new Point(7, 1), list(	set(7)));
		storeSums(new Point(7, 2), list(	set(1, 6), 
											set(2, 5), 
											set(3, 4)));
		storeSums(new Point(7, 3), list(	set(1, 2, 4)));
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor8() {
		storeSums(new Point(8, 1), list(	set(8)));
		storeSums(new Point(8, 2), list(	set(1, 7), 
											set(2, 6), 
											set(3, 5)));
		storeSums(new Point(8, 3), list(	set(1, 2, 5), 
											set(1, 3, 4)));
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor9() {
		storeSums(new Point(9, 1), list(	set(9)));
		storeSums(new Point(9, 2), list(	set(1, 8),
											set(2, 7), 
											set(3, 6), 
											set(4, 5)));
		storeSums(new Point(9, 3), list(	set(1, 2, 6), 
											set(1, 3, 5), 
											set(2, 3, 4)));
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor10() {
		storeSums(new Point(10, 2), list(	set(1, 9), 
											set(2, 8), 
											set(3, 7), 
											set(4, 6)));
		storeSums(new Point(10, 3), list(	set(1, 2, 7), 
											set(1, 3, 6), 
											set(1, 4, 5), 
											set(2, 3, 5)));
		storeSums(new Point(10, 4), list(	set(1, 2, 3, 4)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor11() {
		storeSums(new Point(11, 2), list(	set(2, 9), 
											set(3, 8), 
											set(4, 7), 
											set(5, 6)));
		storeSums(new Point(11, 3), list(	set(1, 2, 8),
											set(1, 3, 7),
											set(1, 4, 6), 
											set(2, 3, 6), 
											set(2, 4, 5)));
		storeSums(new Point(11, 4), list(	set(1, 2, 3, 5)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor12() {
		storeSums(new Point(12, 2), list(	set(3, 9), 
											set(4, 8), 
											set(5, 7)));
		storeSums(new Point(12, 3), list(	set(1, 2, 9),
											set(1, 3, 8),
											set(1, 4, 7), 
											set(1, 5, 6), 
											set(2, 3, 7),
											set(2, 4, 6),
											set(3, 4, 5)));
		storeSums(new Point(12, 4), list(	set(1, 2, 3, 6),
											set(1, 2, 4, 5)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor13() {
		storeSums(new Point(13, 2), list(	set(4, 9), 
											set(5, 8), 
											set(6, 7)));
		storeSums(new Point(13, 3), list(	set(1, 3, 9),
											set(1, 4, 8),
											set(1, 5, 7), 
											set(2, 3, 8),
											set(2, 4, 7),
											set(2, 5, 6),
											set(3, 4, 6)));
		storeSums(new Point(13, 4), list(	set(1, 2, 3, 7),
											set(1, 2, 4, 6),
											set(1, 3, 4, 5)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor14() {
		storeSums(new Point(14, 2), list(	set(5, 9), 
											set(6, 8)));
		storeSums(new Point(14, 3), list(	set(1, 4, 9),
											set(1, 5, 8),
											set(1, 6, 7), 
											set(2, 3, 9),
											set(2, 4, 8),
											set(2, 5, 7),
											set(3, 4, 7),
											set(3, 5, 6)));
		storeSums(new Point(14, 4), list(	set(1, 2, 3, 8),
											set(1, 2, 4, 7),
											set(1, 2, 5, 6),
											set(1, 3, 4, 6),
											set(2, 3, 4, 5)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor15() {
		storeSums(new Point(15, 2), list(	set(6, 9), 
											set(7, 8)));
		storeSums(new Point(15, 3), list(	set(1, 5, 9),
											set(1, 6, 8),
											set(2, 4, 9), 
											set(2, 5, 8),
											set(2, 6, 7),
											set(3, 4, 8),
											set(3, 5, 7),
											set(4, 5, 6)));
		storeSums(new Point(15, 4), list(	set(1, 2, 3, 9),
											set(1, 2, 4, 8),
											set(1, 2, 5, 7),
											set(1, 3, 4, 7),
											set(1, 3, 5, 6),
											set(2, 3, 4, 6)));
		storeSums(new Point(15, 5), list(	set(1, 2, 3, 4, 5)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor16() {
		storeSums(new Point(16, 2), list(	set(7, 9)));
		storeSums(new Point(16, 3), list(	set(1, 6, 9),
											set(1, 7, 8),
											set(2, 5, 9), 
											set(2, 6, 8),
											set(3, 4, 9),
											set(3, 5, 8),
											set(3, 6, 7),
											set(4, 5, 7)));
		storeSums(new Point(16, 4), list(	set(1, 2, 4, 9),
											set(1, 2, 5, 8),
											set(1, 2, 6, 7),
											set(1, 3, 4, 8), 
											set(1, 3, 5, 7), 
											set(1, 4, 5, 6),
											set(2, 3, 4, 7),
											set(2, 3, 5, 6)));
		storeSums(new Point(16, 5), list(	set(1, 2, 3, 4, 6)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor17() {
		storeSums(new Point(17, 2), list(	set(8, 9)));
		storeSums(new Point(17, 3), list(	set(1, 7, 9),
											set(2, 6, 9), 
											set(2, 7, 8),
											set(3, 5, 9),
											set(3, 6, 8),
											set(4, 5, 8),
											set(4, 6, 7)));
		storeSums(new Point(17, 4), list(	set(1, 2, 5, 9),
											set(1, 2, 6, 8),
											set(1, 3, 4, 9),
											set(1, 3, 5, 8),
											set(1, 3, 6, 7),
											set(1, 4, 5, 7),
											set(2, 3, 4, 8),
											set(2, 3, 5, 7),
											set(2, 4, 5, 6)));
		storeSums(new Point(17, 5), list(	set(1, 2, 3, 4, 7),
											set(1, 2, 3, 5, 6)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor18() {
		storeSums(new Point(18, 3), list(	set(1, 8, 9),
											set(2, 7, 9), 
											set(3, 6, 9),
											set(3, 7, 8),
											set(4, 5, 9),
											set(4, 6, 8),
											set(5, 6, 7)));
		storeSums(new Point(18, 4), list(	set(1, 2, 6, 9),
											set(1, 2, 7, 8),
											set(1, 3, 5, 9),
											set(1, 3, 6, 8),
											set(1, 4, 5, 8),
											set(1, 4, 6, 7),
											set(2, 3, 4, 9),
											set(2, 3, 5, 8),
											set(2, 3, 6, 7),
											set(2, 4, 5, 7),
											set(3, 4, 5, 6)));
		storeSums(new Point(18, 5), list(	set(1, 2, 3, 4, 8),
											set(1, 2, 3, 5, 7),
											set(1, 2, 4, 5, 6)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor19() {
		storeSums(new Point(19, 3), list(	set(2, 8, 9),
											set(3, 7, 9), 
											set(4, 6, 9),
											set(4, 7, 8),
											set(5, 6, 8)));
		storeSums(new Point(19, 4), list(	set(1, 2, 7, 9),
											set(1, 3, 6, 9),
											set(1, 3, 7, 8),
											set(1, 4, 5, 9),
											set(1, 4, 6, 8),
											set(1, 5, 6, 7),
											set(2, 3, 5, 9),
											set(2, 3, 6, 8),
											set(2, 4, 5, 8),
											set(2, 4, 6, 7),
											set(3, 4, 5, 7)));
		storeSums(new Point(19, 5), list(	set(1, 2, 3, 4, 9),
											set(1, 2, 3, 5, 8),
											set(1, 2, 3, 6, 7),
											set(1, 2, 4, 5, 7),
											set(1, 3, 4, 5, 6)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor20() {
		storeSums(new Point(20, 3), list(	set(3, 8, 9),
											set(4, 7, 9), 
											set(5, 6, 9),
											set(5, 7, 8)));
		storeSums(new Point(20, 4), list(	set(1, 2, 8, 9),
											set(1, 3, 7, 9),
											set(1, 4, 6, 9),
											set(1, 4, 7, 8),
											set(1, 5, 6, 8),
											set(2, 3, 6, 9),
											set(2, 3, 7, 8),
											set(2, 4, 5, 9),
											set(2, 4, 6, 8),
											set(2, 5, 6, 7),
											set(3, 4, 5, 8),
											set(3, 4, 6, 7)));
		storeSums(new Point(20, 5), list(	set(1, 2, 3, 5, 9),
											set(1, 2, 3, 6, 8),
											set(1, 2, 4, 5, 8),
											set(1, 2, 4, 6, 7),
											set(1, 3, 4, 5, 7),
											set(2, 3, 4, 5, 6)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor21() {
		storeSums(new Point(21, 3), list(	set(4, 8, 9),
											set(5, 7, 9), 
											set(6, 7, 8)));
		storeSums(new Point(21, 4), list(	set(1, 3, 8, 9),
											set(1, 4, 7, 9),
											set(1, 5, 6, 9),
											set(1, 5, 7, 8),
											set(2, 3, 7, 9),
											set(2, 4, 6, 9),
											set(2, 4, 7, 8),
											set(2, 5, 6, 8),
											set(3, 4, 5, 9),
											set(3, 4, 6, 8),
											set(3, 5, 6, 7)));
		storeSums(new Point(21, 5), list(	set(1, 2, 3, 6, 9),
											set(1, 2, 3, 7, 8),
											set(1, 2, 4, 5, 9),
											set(1, 2, 4, 6, 8),
											set(1, 2, 5, 6, 7),
											set(1, 3, 4, 5, 8),
											set(1, 3, 4, 6, 7),
											set(2, 3, 4, 5, 7)));
		storeSums(new Point(21, 6), list(	set(1, 2, 3, 4, 5, 6)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor22() {
		storeSums(new Point(22, 3), list(	set(5, 8, 9),
											set(6, 7, 9)));
		storeSums(new Point(22, 4), list(	set(1, 4, 8, 9),
											set(1, 5, 7, 9),
											set(1, 6, 7, 8),
											set(2, 3, 8, 9),
											set(2, 4, 7, 9),
											set(2, 5, 6, 9),
											set(2, 5, 7, 8),
											set(3, 4, 6, 9),
											set(3, 4, 7, 8),
											set(3, 5, 6, 8),
											set(4, 5, 6, 7)));
		storeSums(new Point(22, 5), list(	set(1, 2, 3, 7, 9),
											set(1, 2, 4, 6, 9),
											set(1, 2, 4, 7, 8),
											set(1, 2, 5, 6, 8),
											set(1, 3, 4, 5, 9),
											set(1, 3, 4, 6, 8),
											set(1, 3, 5, 6, 7),
											set(2, 3, 4, 5, 8),
											set(2, 3, 4, 6, 7)));
		storeSums(new Point(22, 6), list(	set(1, 2, 3, 4, 5, 7)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor23() {
		storeSums(new Point(23, 3), list(	set(6, 8, 9)));
		storeSums(new Point(23, 4), list(	set(1, 5, 8, 9),
											set(1, 6, 7, 9),
											set(2, 4, 8, 9),
											set(2, 5, 7, 9),
											set(2, 6, 7, 8),
											set(3, 4, 7, 9),
											set(3, 5, 6, 9),
											set(3, 5, 7, 8),
											set(4, 5, 6, 8)));
		storeSums(new Point(23, 5), list(	set(1, 2, 3, 8, 9),
											set(1, 2, 4, 7, 9),
											set(1, 2, 5, 6, 9),
											set(1, 2, 5, 7, 8),
											set(1, 3, 4, 6, 9),
											set(1, 3, 4, 7, 8),
											set(1, 3, 5, 6, 8),
											set(1, 4, 5, 6, 7),
											set(2, 3, 4, 5, 9),
											set(2, 3, 4, 6, 8),
											set(2, 3, 5, 6, 7)));
		storeSums(new Point(23, 6), list(	set(1, 2, 3, 4, 5, 8),
											set(1, 2, 3, 4, 6, 7)));
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor24() {
		storeSums(new Point(24, 3), list(	set(7, 8, 9)));
		storeSums(new Point(24, 4), list(	set(1, 6, 8, 9),
											set(2, 5, 8, 9),
											set(2, 6, 7, 9),
											set(3, 4, 8, 9),
											set(3, 5, 7, 9),
											set(3, 6, 7, 8),
											set(4, 5, 6, 9),
											set(4, 5, 7, 8)));
		storeSums(new Point(24, 5), list(	set(1, 2, 4, 8, 9),
											set(1, 2, 5, 7, 9),
											set(1, 2, 6, 7, 8),
											set(1, 3, 4, 7, 9),
											set(1, 3, 5, 6, 9),
											set(1, 3, 5, 7, 8),
											set(1, 4, 5, 6, 8),
											set(2, 3, 4, 6, 9),
											set(2, 3, 4, 7, 8),
											set(2, 3, 5, 6, 8),
											set(2, 4, 5, 6, 7)));
		storeSums(new Point(24, 6), list(	set(1, 2, 3, 4, 5, 9),
											set(1, 2, 3, 4, 6, 8),
											set(1, 2, 3, 5, 6, 7)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor25() {
		storeSums(new Point(25, 4), list(	set(1, 7, 8, 9),
											set(2, 6, 8, 9),
											set(3, 5, 8, 9),
											set(3, 6, 7, 9),
											set(4, 5, 7, 9),
											set(4, 6, 7, 8)));
		storeSums(new Point(25, 5), list(	set(1, 2, 5, 8, 9),
											set(1, 2, 6, 7, 9),
											set(1, 3, 4, 8, 9),
											set(1, 3, 5, 7, 9),
											set(1, 3, 6, 7, 8),
											set(1, 4, 5, 6, 9),
											set(1, 4, 5, 7, 8),
											set(2, 3, 4, 7, 9),
											set(2, 3, 5, 6, 9),
											set(2, 3, 5, 7, 8),
											set(2, 4, 5, 6, 8),
											set(3, 4, 5, 6, 7)));
		storeSums(new Point(25, 6), list(	set(1, 2, 3, 4, 6, 9),
											set(1, 2, 3, 4, 7, 8),
											set(1, 2, 3, 5, 6, 8),
											set(1, 2, 4, 5, 6, 7)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor26(){
		storeSums(new Point(26, 4), list(	set(2, 7, 8, 9),	
											set(3, 6, 8, 9),
											set(4, 5, 8, 9),
											set(4, 6, 7, 9),
											set(5, 6, 7, 8)));
		storeSums(new Point(26, 5), list(	set(1, 2, 6, 8, 9),	
											set(1, 3, 5, 8, 9),
											set(1, 3, 6, 7, 9),
											set(1, 4, 5, 7, 9),
											set(1, 4, 6, 7, 8),
											set(2, 3, 4, 8, 9),
											set(2, 3, 5, 7, 9),
											set(2, 3, 6, 7, 8),
											set(2, 4, 5, 6, 9),
											set(2, 4, 5, 7, 8),
											set(3, 4, 5, 6, 8)));
		storeSums(new Point(26, 6), list(	set(1, 2, 3, 4, 7, 9),	
											set(1, 2, 3, 5, 6, 9),	
											set(1, 2, 3, 5, 7, 8),	
											set(1, 2, 4, 5, 6, 8),	
											set(1, 3, 4, 5, 6, 7)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor27(){
		storeSums(new Point(27, 4), list(	set(3, 7, 8, 9),
											set(4, 6, 8, 9),
											set(5, 6, 7, 9)));
		storeSums(new Point(27, 5), list(	set(1, 2, 7, 8, 9),
											set(1, 3, 6, 8, 9),
											set(1, 4, 5, 8, 9),
											set(1, 4, 6, 7, 9),
											set(1, 5, 6, 7, 8),
											set(2, 3, 5, 8, 9),
											set(2, 3, 6, 7, 9),
											set(2, 4, 5, 7, 9),
											set(2, 4, 6, 7, 8),
											set(3, 4, 5, 6, 9),
											set(3, 4, 5, 7, 8)));
		storeSums(new Point(27, 6), list(	set(1, 2, 3, 4, 8, 9),
											set(1, 2, 3, 5, 7, 9),
											set(1, 2, 3, 6, 7, 8),
											set(1, 2, 4, 5, 6, 9),
											set(1, 2, 4, 5, 7, 8),
											set(1, 3, 4, 5, 6, 8),
											set(2, 3, 4, 5, 6, 7)));
	}

	@SuppressWarnings("unchecked")
	private static void sumsFor28(){
		storeSums(new Point(28, 4), list(	set(4, 7, 8, 9),
											set(5, 6, 8, 9)));
		storeSums(new Point(28, 5), list(	set(1, 3, 7, 8, 9),
											set(1, 4, 6, 8, 9),
											set(1, 5, 6, 7, 9),
											set(2, 3, 6, 8, 9),
											set(2, 4, 5, 8, 9),
											set(2, 4, 6, 7, 9),
											set(2, 5, 6, 7, 8),
											set(3, 4, 5, 7, 9),
											set(3, 4, 6, 7, 8)));
		storeSums(new Point(28, 6), list(	set(1, 2, 3, 5, 8, 9),
											set(1, 2, 3, 6, 7, 9),
											set(1, 2, 4, 5, 7, 9),
											set(1, 2, 4, 6, 7, 8),
											set(1, 3, 4, 5, 6, 9),
											set(1, 3, 4, 5, 7, 8),
											set(2, 3, 4, 5, 6, 8)));
		storeSums(new Point(28, 7), list(	set(1, 2, 3, 4, 5, 6, 7)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor29(){
		storeSums(new Point(29, 4), list(	set(5, 7, 8, 9)));
		storeSums(new Point(29, 5), list(	set(1, 4, 7, 8, 9),
											set(1, 5, 6, 8, 9),
											set(2, 3, 7, 8, 9),
											set(2, 4, 6, 8, 9),
											set(2, 5, 6, 7, 9),
											set(3, 4, 5, 8, 9),
											set(3, 4, 6, 7, 9),
											set(3, 5, 6, 7, 8)));
		storeSums(new Point(29, 6), list(	set(1, 2, 3, 6, 8, 9),
											set(1, 2, 4, 5, 8, 9),
											set(1, 2, 4, 6, 7, 9),
											set(1, 2, 5, 6, 7, 8),
											set(1, 3, 4, 5, 7, 9),
											set(1, 3, 4, 6, 7, 8),
											set(2, 3, 4, 5, 6, 9),
											set(2, 3, 4, 5, 7, 8)));
		storeSums(new Point(29, 7), list(	set(1, 2, 3, 4, 5, 6, 8)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor30(){
		storeSums(new Point(30, 4), list(	set(6, 7, 8, 9)));
		storeSums(new Point(30, 5), list(	set(1, 5, 7, 8, 9),
											set(2, 4, 7, 8, 9),
											set(2, 5, 6, 8, 9),
											set(3, 4, 6, 8, 9),
											set(3, 5, 6, 7, 9),
											set(4, 5, 6, 7, 8)));
		storeSums(new Point(30, 6), list(	set(1, 2, 3, 7, 8, 9),
											set(1, 2, 4, 6, 8, 9),
											set(1, 2, 5, 6, 7, 9),
											set(1, 3, 4, 5, 8, 9),
											set(1, 3, 4, 6, 7, 9),
											set(1, 3, 5, 6, 7, 8),
											set(2, 3, 4, 5, 7, 9),
											set(2, 3, 4, 6, 7, 8)));
		storeSums(new Point(30, 7), list(	set(1, 2, 3, 4, 5, 6, 9),
											set(1, 2, 3, 4, 5, 7, 8)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor31(){
		storeSums(new Point(31, 5), list(	set(1, 6, 7, 8, 9),
											set(2, 5, 7, 8, 9),
											set(3, 4, 7, 8, 9),
											set(3, 5, 6, 8, 9),
											set(4, 5, 6, 7, 9)));
		storeSums(new Point(31, 6), list(	set(1, 2, 4, 7, 8, 9),
											set(1, 2, 5, 6, 8, 9),
											set(1, 3, 4, 6, 8, 9),
											set(1, 3, 5, 6, 7, 9),
											set(1, 4, 5, 6, 7, 8),
											set(2, 3, 4, 5, 8, 9),
											set(2, 3, 4, 6, 7, 9),
											set(2, 3, 5, 6, 7, 8)));
		storeSums(new Point(31, 7), list(	set(1, 2, 3, 4, 5, 7, 9),
											set(1, 2, 3, 4, 6, 7, 8)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor32(){
		storeSums(new Point(32, 5), list(	set(2, 6, 7, 8, 9),
											set(3, 5, 7, 8, 9),
											set(4, 5, 6, 8, 9)));
		storeSums(new Point(32, 6), list(	set(1, 2, 5, 7, 8, 9),
											set(1, 3, 4, 7, 8, 9),
											set(1, 3, 5, 6, 8, 9),
											set(1, 4, 5, 6, 7, 9),
											set(2, 3, 4, 6, 8, 9),
											set(2, 3, 5, 6, 7, 9),
											set(2, 4, 5, 6, 7, 8)));
		storeSums(new Point(32, 7), list(	set(1, 2, 3, 4, 5, 8, 9),
											set(1, 2, 3, 4, 6, 7, 9),
											set(1, 2, 3, 5, 6, 7, 8)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor33() {
		storeSums(new Point(33, 5), list(	set(3, 6, 7, 8, 9),
											set(4, 5, 7, 8, 9)));
		storeSums(new Point(33, 6), list(	set(1, 2, 6, 7, 8, 9),
											set(1, 3, 5, 7, 8, 9),
											set(1, 4, 5, 6, 8, 9),
											set(2, 3, 4, 7, 8, 9),
											set(2, 3, 5, 6, 8, 9),
											set(2, 4, 5, 6, 7, 9),
											set(3, 4, 5, 6, 7, 8)));
		storeSums(new Point(33, 7), list(	set(1, 2, 3, 4, 6, 8, 9),
											set(1, 2, 3, 5, 6, 7, 9),
											set(1, 2, 4, 5, 6, 7, 8)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor34() {
		storeSums(new Point(34, 5), list(	set(4, 6, 7, 8, 9)));
		storeSums(new Point(34, 6), list(	set(1, 3, 6, 7, 8, 9),
											set(1, 4, 5, 7, 8, 9),
											set(2, 3, 5, 7, 8, 9),
											set(2, 4, 5, 6, 8, 9),
											set(3, 4, 5, 6, 7, 9)));
		storeSums(new Point(34, 7), list(	set(1, 2, 3, 4, 7, 8, 9),
											set(1, 2, 3, 5, 6, 8, 9),
											set(1, 2, 4, 5, 6, 7, 9),
											set(1, 3, 4, 5, 6, 7, 8)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor35() {
		storeSums(new Point(35, 5), list(	set(5, 6, 7, 8, 9)));
		storeSums(new Point(35, 6), list(	set(1, 4, 6, 7, 8, 9),
											set(2, 3, 6, 7, 8, 9),
											set(2, 4, 5, 7, 8, 9),
											set(3, 4, 5, 6, 8, 9)));
		storeSums(new Point(35, 7), list(	set(1, 2, 3, 5, 7, 8, 9),
											set(1, 2, 4, 5, 6, 8, 9),
											set(1, 3, 4, 5, 6, 7, 9),
											set(2, 3, 4, 5, 6, 7, 8)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor36() {
		storeSums(new Point(36, 6), list(	set(1, 5, 6, 7, 8, 9),
											set(2, 4, 6, 7, 8, 9),
											set(3, 4, 5, 7, 8, 9)));
		storeSums(new Point(36, 7), list(	set(1, 2, 3, 6, 7, 8, 9),
											set(1, 2, 4, 5, 7, 8, 9),
											set(1, 3, 4, 5, 6, 8, 9),
											set(2, 3, 4, 5, 6, 7, 9)));
		storeSums(new Point(36, 8), list(	set(1, 2, 3, 4, 5, 6, 7, 8)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor37() {
		storeSums(new Point(37, 6), list(	set(2, 5, 6, 7, 8, 9),
											set(3, 4, 6, 7, 8, 9)));
		storeSums(new Point(37, 7), list(	set(1, 2, 4, 6, 7, 8, 9),
											set(1, 3, 4, 5, 7, 8, 9),
											set(2, 3, 4, 5, 6, 8, 9)));
		storeSums(new Point(37, 8), list(	set(1, 2, 3, 4, 5, 6, 7, 9)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor38() {
		storeSums(new Point(38, 6), list(	set(3, 5, 6, 7, 8, 9)));
		storeSums(new Point(38, 7), list(	set(1, 2, 5, 6, 7, 8, 9),
											set(1, 3, 4, 6, 7, 8, 9),
											set(2, 3, 4, 5, 7, 8, 9)));
		storeSums(new Point(38, 8), list(	set(1, 2, 3, 4, 5, 6, 8, 9)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor39() {
		storeSums(new Point(39, 6), list(	set(4, 5, 6, 7, 8, 9)));
		storeSums(new Point(39, 7), list(	set(1, 3, 5, 6, 7, 8, 9),
											set(2, 3, 4, 6, 7, 8, 9)));
		storeSums(new Point(39, 8), list(	set(1, 2, 3, 4, 5, 7, 8, 9)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor40() {
		storeSums(new Point(40, 7), list(	set(1, 4, 5, 6, 7 ,8, 9),
											set(2, 3, 5, 6, 7, 8, 9)));
		storeSums(new Point(40, 8), list(	set(1, 2, 3, 4, 6, 7, 8 ,9)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor41() {
		storeSums(new Point(41, 7), list(	set(2, 4, 5, 6, 7, 8, 9)));
		storeSums(new Point(41, 8), list(	set(1, 2, 3, 5, 6, 7, 8, 9)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor42() {
		storeSums(new Point(42, 7), list(	set(3, 4, 5, 6, 7, 8, 9)));
		storeSums(new Point(42, 8), list(	set(1, 2, 4, 5, 6, 7, 8, 9)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor43() {
		storeSums(new Point(43, 8), list(	set(1, 3, 4, 5, 6, 7, 8, 9)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor44() {
		storeSums(new Point(44, 8), list(	set(2, 3, 4, 5, 6, 7, 8, 9)));
	}
	
	@SuppressWarnings("unchecked")
	private static void sumsFor45() {
		storeSums(new Point(45, 9), list(	set(1, 2, 3, 4, 5, 6, 7, 8, 9)));
	}
	
	
	
/*
3	12
4	13
5	14	23
6	15	24	123
7	16	25	34	124
8	17	26	35	134	125
9	18	27	36	45	234	135	126
10	19	28	37	46	235	145	136	127	1234
11	29	38	47	56	245	236	146	137	128	1235
12	39	48	57	345	246	237	156	147	138	129	1236	1245
13	49	58	67	346	256	247	238	157	148	139	1237	1246	1345
14	59	68	356	347	257	248	239	167	158	149	1238	1247	1256	1346	2345
15	69	78	456	357	348	267	258	249	168	159	1239	1248	1257	1347	1356	2346	12345
16	79	457	367	358	349	268	259	178	169	1249	1258	1267	1348	1357	1456	2347	2356	12346
17	89	467	458	368	359	278	269	179	1259	1268	1349	1358	1367	1457	2348	2357	2456	12347	12356
18	567	468	459	378	369	279	189	1269	1278	1359	1368	1458	1467	2349	2358	2367	2457	3456	12348	12357	12456
19	568	478	469	379	289	1279	1369	1378	1459	1468	1567	2359	2368	2458	2467	3457	12349	12358	12367	12457	13456
20	578	569	479	389	1289	1379	1469	1478	1568	2369	2378	2459	2468	2567	3458	3467	12359	12368	12458	12467	13457	23456
21	678	579	489	1389	1479	1569	1578	2379	2469	2478	2568	3459	3468	3567	12369	12378	12459	12468	12567	13458	13467	23457	123456
22	679	589	1489	1579	1678	2389	2479	2569	2578	3469	3478	3568	4567	12379	12469	12478	12568	13459	13468	13567	23458	23467	123457
23	689	1589	1679	2489	2579	2678	3479	3569	3578	4568	12389	12479	12569	12578	13469	13478	13568	14567	23459	23468	23567	123458	123467
24	789	1689	2589	2679	3489	3579	3678	4569	4578	12489	12579	12678	13479	13569	13578	14568	23469	23478	23568	24567	123459	123468	123567
25	1789	2689	3589	3679	4579	4678	12589	12679	13489	13579	13678	14569	14578	23479	23569	23578	24568	34567	123469	123478	123568	124567
26	2789	3689	4589	4679	5678	12689	13589	13679	14579	14678	23489	23579	23678	24569	24578	34568	123479	123569	123578	124568	134567
27	3789	4689	5679	12789	13689	14589	14679	15678	23589	23679	24579	24678	34569	34578	123489	123579	123678	124569	124578	134568	234567
28	4789	5689	13789	14689	15679	23689	24589	24679	25678	34579	34678	123589	123679	124579	124678	134569	134578	234568	1234567
29	5789	14789	15689	23789	24689	25679	34589	34679	35678	123689	124589	124679	125678	134579	134678	234569	234578	1234568
30	6789	15789	24789	25689	34689	35679	45678	123789	124689	125679	134589	134679	135678	234579	234678	1234569	1234578
31	16789	25789	34789	35689	45679	124789	125689	134689	135679	145678	234589	234679	235678	1234579	1234678
32	26789	35789	45689	125789	134789	135689	145679	234689	235679	245678	1234589	1234679	1235678
33	36789	45789	126789	135789	145689	234789	235689	245679	345678	1234689	1235679	1245678
34	46789	136789	145789	235789	245689	345679	1234789	1235689	1245679	1345678
35	56789	146789	236789	245789	345689	1235789	1245689	1345679	2345678
36	156789	246789	345789	1236789	1245789	1345689	2345679	12345678
37	256789	346789	1246789	1345789	2345689	12345679
38	356789	1256789	1346789	2345789	12345689
39	456789	1356789	2346789	12345789
40	1456789	2356789	12346789
41	2456789	12356789
42	3456789	12456789
43	13456789
44	23456789
45	123456789
*/
}
