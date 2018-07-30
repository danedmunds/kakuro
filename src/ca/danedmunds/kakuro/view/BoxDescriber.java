package ca.danedmunds.kakuro.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ca.danedmunds.kakuro.model.BlockBox;
import ca.danedmunds.kakuro.model.BorderBox;
import ca.danedmunds.kakuro.model.Box;
import ca.danedmunds.kakuro.model.CellBox;
import ca.danedmunds.kakuro.solvers.KakuroSums;

public class BoxDescriber {
	
	public static String describe(Box box) {
		Class<? extends Box> type = box.getType();
		
		if(type.equals(BlockBox.class)){
			return describe((BlockBox) box);
		} else if (type.equals(BorderBox.class)) {
			return describe((BorderBox) box);
		} else if (type.equals(CellBox.class)) {
			return describe((CellBox) box);
		}
		
		throw(new RuntimeException("Unknown type: "+box.getType()));
	}
	
	public static String describe(CellBox cell) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Cell\n___________\n\n");
		
		BorderBox verticalBorder = cell.getVerticalBorder();
		
		Set<Integer> mergedVertical = null;
		if(verticalBorder !=  null){
			buffer.append("Vertical possibilities:\n");
//			List<Set<Integer>> vertPossibilities = verticalBorder.getVerticalPossibilties();
			List<Set<Integer>> vertPossibilities = cell.getVerticalPossibilities();
			printPossibilities(buffer, vertPossibilities);
			buffer.append("therefore possiblities:\n");
			mergedVertical = KakuroSums.multiUnion(vertPossibilities);
			printSet(buffer, mergedVertical);
		} else {
			buffer.append("No vertical border\n");
		}
		
		buffer.append("___________\n\n");
		
		BorderBox horizontalBorder = cell.getHorizontalBorder();
		
		Set<Integer> mergedHorizontal = null;
		if(horizontalBorder !=  null){
			buffer.append("Horizontal possibilities:\n");
//			List<Set<Integer>> horiPossibilities = horizontalBorder.getHorizontalPossibilities();
			List<Set<Integer>> horiPossibilities = cell.getHorizontalPossibilities();
			printPossibilities(buffer, horiPossibilities);
			buffer.append("therefore possiblities:\n");
			mergedHorizontal = KakuroSums.multiUnion(horiPossibilities);
			printSet(buffer, mergedHorizontal);
		} else {
			buffer.append("No horizontal border\n");
		}
		
		buffer.append("___________\n\n");
		
//		Set<Integer> possibilities = null;
//		if(mergedHorizontal == null){
//			possibilities = mergedVertical;
//		} else if (mergedVertical == null){
//			possibilities = mergedHorizontal;
//		} else {
//			possibilities = KakuroSums.intersection(mergedHorizontal, mergedVertical);
//		}
		
		Set<Integer> possibilities = cell.getAllPossibilities();
		buffer.append("therefore FOR THIS CELL possilbities:\n");
		printSet(buffer, possibilities);

		return buffer.toString();
	}
	
	public static String describe(BorderBox border) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("Border\n");
		
		buffer.append("== VERTICAL ==\n");
		ArrayList<CellBox> verticalCells = border.getVerticalCells();
		int goalVerticalSum = border.getGoalVerticalSum();
		List<Set<Integer>> verticalPossibilties = border.getVerticalPossibilties();
		describeData(buffer, verticalCells, goalVerticalSum, verticalPossibilties);
		
		buffer.append("== HORIZONTAL ==\n");
		ArrayList<CellBox> horizontalCells = border.getHorizontalCells();
		int goalHorizontalSum = border.getGoalHorizontalSum();
		List<Set<Integer>> horizontalPossibilities = border.getHorizontalPossibilities();
		describeData(buffer, horizontalCells, goalHorizontalSum, horizontalPossibilities);
		
		return buffer.toString();
	}

	private static void describeData(StringBuffer buffer, ArrayList<CellBox> cells, int goalSum, List<Set<Integer>> possibilities) {
		if(goalSum != 0 && !cells.isEmpty()){
			buffer.append(goalSum+"(sum) in "+cells.size()+"(cells)\n");
			buffer.append("Possibilities:\n");
			printPossibilities(buffer, possibilities);
		} else {
			buffer.append("No vertical sum\n");
		}
	}
	
	public static void printPossibilities(StringBuffer buffer, List<Set<Integer>> possiblities) {
		if(possiblities == null){
			buffer.append("POSSIBILITIES ARE NULL!!!!!!");
			return;
		}
		for(Set<Integer> possibility : possiblities){
			printSet(buffer, possibility);
		}
	}
	
	public static void printArray(StringBuffer buffer, int[] possibility) {
		buffer.append("[");
		for(int i=0; i<possibility.length - 1; ++i){
			buffer.append(possibility[i]).append(", ");
		}
		
		if(possibility.length > 0){
			buffer.append(possibility[possibility.length - 1]);
		}
		buffer.append("]\n");
	}

	
	public static String describe(BlockBox block) {
		return "Block\n";
	}

	public static void printSet(StringBuffer buffer, Set<Integer> set) {
		buffer.append("[");
		for(Integer value : set) 
			buffer.append(value).append(", ");
		
		if(set.size() > 0)
			buffer.delete(buffer.length() - 2, buffer.length());
		
		buffer.append("]\n");
	}

}
