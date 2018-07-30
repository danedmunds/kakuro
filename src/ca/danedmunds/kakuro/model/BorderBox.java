package ca.danedmunds.kakuro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ca.danedmunds.kakuro.solvers.KakuroSums;
import ca.danedmunds.kakuro.view.BoxDescriber;


public class BorderBox extends Box {
	
	private int goalHorizontalSum;
//	private int[][] horizontalPossibilities;
	
	private int goalVerticalSum;
//	private int[][] verticalPossibilties;
	
	private ArrayList<CellBox> horizontalCells;
	private ArrayList<CellBox> verticalCells;
	
	public BorderBox(int horizontalSum, int verticalSum){
		this.goalHorizontalSum = horizontalSum;
		this.goalVerticalSum = verticalSum;
		
		horizontalCells = new ArrayList<CellBox>();
		verticalCells = new ArrayList<CellBox>();
	}
	
	public BorderBox(){
		this(0, 0);
	}
	
	@Override
	public Class<? extends Box> getType() {
		return BorderBox.class;
	}
	
	@Override
	public void setSelected(boolean selected, boolean isSubCall) {
		super.setSelected(selected, isSubCall);
		
		if(isSubCall){
			return;
		}
		
		for(CellBox cell : horizontalCells){
			cell.setSelected(selected, true);
		}
		
		for(CellBox cell : verticalCells){
			cell.setSelected(selected, true);
		}
	}
	
	public ArrayList<CellBox> getHorizontalCells(){
		return new ArrayList<CellBox>(horizontalCells);
	}
	
	public ArrayList<CellBox> getVerticalCells(){
		return new ArrayList<CellBox>(verticalCells);
	}
	
	public int getGoalHorizontalSum() {
		return goalHorizontalSum;
	}
	
	public void setGoalHorizontalSum(int horizontalSum) {
		this.goalHorizontalSum = horizontalSum;
	}
	
	public int getGoalVerticalSum() {
		return goalVerticalSum;
	}
	
	public void setGoalVerticalSum(int verticalSum) {
		this.goalVerticalSum = verticalSum;
	}
	
	public void addVerticalCell(CellBox cell){
		verticalCells.add(cell);
		cell.setVerticalBorder(this);
	}
	
	public void addHorizontalCell(CellBox cell){
		horizontalCells.add(cell);
		cell.setHorizontalBorder(this);
	}
	
	public void clearKnownCells(){
		verticalCells.clear();
		horizontalCells.clear();
	}

	@Override
	public Box copy() {
		return new BorderBox(goalHorizontalSum, goalVerticalSum);
	}

	public boolean isValidHorizontalGuess(int guess){
		//TODO this is just for now, we will start collapsing this list
		StringBuffer buffer = new StringBuffer();
		BoxDescriber.printSet(buffer,getAllHorizontalPossibilities());
		System.out.println("HORIZONTAL");
		System.out.println("guess: "+guess+"     "+buffer.toString());
		System.out.println("contained: "+(getAllHorizontalPossibilities().contains(new Integer(guess))));
		return getAllHorizontalPossibilities().contains(new Integer(guess));
	}
	
	public boolean isValidVerticalGuess(int guess){
		StringBuffer buffer = new StringBuffer();
		BoxDescriber.printSet(buffer,getAllVerticalPossbilities());
		System.out.println("VERTICAL");
		System.out.println("guess: "+guess+"     "+buffer.toString());
		System.out.println("contained: "+getAllVerticalPossbilities().contains(new Integer(guess)));
		
		//TODO ditto
		return getAllVerticalPossbilities().contains(new Integer(guess));
	}
	
	public List<Set<Integer>> getHorizontalPossibilities() {
		return KakuroSums.getSums(goalHorizontalSum, horizontalCells.size());
	}
	
	public Set<Integer> getAllHorizontalPossibilities() {
		return KakuroSums.getAllPossibilities(goalHorizontalSum, horizontalCells.size());
	}

	public List<Set<Integer>> getVerticalPossibilties() {
		return KakuroSums.getSums(goalVerticalSum, verticalCells.size());
	}
	
	public Set<Integer> getAllVerticalPossbilities() {
		return KakuroSums.getAllPossibilities(goalVerticalSum, verticalCells.size());
	}
}
