package ca.danedmunds.kakuro.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.danedmunds.kakuro.model.Constants.Direction;
import ca.danedmunds.kakuro.solvers.KakuroSums;


public class CellBox extends Box {
	
	private BorderBox horizontalBorder;
	private BorderBox verticalBorder;
	
	private List<Set<Integer>> horizontalPossibilities;
	private List<Set<Integer>> verticalPossibilities;
	
	private Set<Integer> allPossibilities;
	
	private int value = 0;
	
	public CellBox(int value){
		setValue(value);
	}
	
	public CellBox(){
		this(0);
	}
	
	public Class<? extends Box> getType() {
		return CellBox.class;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public Set<Integer> getAllPossibilities() {
		//TODO clean this up, we don't eliminate as we go
		
		if(allPossibilities == null){
			if(verticalBorder != null && horizontalBorder != null){
				allPossibilities = new HashSet<Integer>(KakuroSums.intersection(verticalBorder.getAllVerticalPossbilities(), horizontalBorder.getAllHorizontalPossibilities()));
			} else if(verticalBorder != null){
				allPossibilities = new HashSet<Integer>(verticalBorder.getAllVerticalPossbilities());
			} else if(horizontalBorder != null){
				allPossibilities = new HashSet<Integer>(horizontalBorder.getAllHorizontalPossibilities());
			} else {
				throw(new RuntimeException("both horizontal and vertical borders are null"));
			}
			
		}
		
		return allPossibilities;
	}

	public boolean isValidGuess(int guess){
		//TODO this is being lazy.... have to fix this
		getAllPossibilities();
		
		return allPossibilities.contains(guess);
	}
	
	public BorderBox getHorizontalBorder() {
		return horizontalBorder;
	}

	public void setHorizontalBorder(BorderBox horizontalBorder) {
		this.horizontalBorder = horizontalBorder;
	}

	public BorderBox getVerticalBorder() {
		return verticalBorder;
	}

	public void setVerticalBorder(BorderBox verticalBorder) {
		this.verticalBorder = verticalBorder;
	}
	
	public List<Set<Integer>> getHorizontalPossibilities() {
		return horizontalPossibilities;
	}

	public void setHorizontalPossibilities(List<Set<Integer>> horizontalPossibilities) {
		this.horizontalPossibilities = copy(horizontalPossibilities);
	}

	public List<Set<Integer>> getVerticalPossibilities() {
		return verticalPossibilities;
	}

	public void setVerticalPossibilities(List<Set<Integer>> verticalPossibilities) {
		this.verticalPossibilities = copy(verticalPossibilities);
	}
	
	private List<Set<Integer>> copy(List<Set<Integer>> toCopy) {
		List<Set<Integer>> result = new ArrayList<Set<Integer>>();
		
		for(Set<Integer> set : toCopy)
			result.add(new HashSet<Integer>(set));
		
		return result;
	}
	
	@Override
	public void setSelected(boolean selected, boolean isSubCall) {
		super.setSelected(selected, isSubCall);
		
		if(isSubCall){
			return;
		}
		
		Box vertical = getVerticalBorder();
		if(vertical != null){
			vertical.setSelected(selected, true);
		}
		
		Box horizontal = getHorizontalBorder();
		if(horizontal != null){
			horizontal.setSelected(selected, true);
		}
	}
	
	@Override
	public Box copy() {
		return new CellBox(getValue());
	}

	public void removePossibility(int takeOut, Direction direction) {
		int h = 0;
		if(horizontalBorder != null)
			h = horizontalBorder.getGoalHorizontalSum();
		
		int v = 0;
		if(verticalBorder != null)
			v = verticalBorder.getGoalVerticalSum();
		
		if(h == 19 && v == 5) {
			int a = 3;
			++a;
		}
		
		if(direction.equals(Constants.Direction.Horizontal)) {
			List<Set<Integer>> setsToRemove = new ArrayList<Set<Integer>>();
			for(Set<Integer> horizontalPosibility : horizontalPossibilities) {
				if(!horizontalPosibility.contains(new Integer(takeOut))) {
					setsToRemove.add(horizontalPosibility);
					continue;
				}
				
				horizontalPosibility.remove(new Integer(takeOut));
			}
			
			for(Set<Integer> toRemove : setsToRemove)
				horizontalPossibilities.remove(toRemove);
		} else if (direction.equals(Constants.Direction.Vertical)) {
		
			List<Set<Integer>> setsToRemove = new ArrayList<Set<Integer>>();
			for(Set<Integer> verticalPosibility : verticalPossibilities) {
				if(!verticalPosibility.contains(new Integer(takeOut))) {
					setsToRemove.add(verticalPosibility);
					continue;
				}
				
				verticalPosibility.remove(new Integer(takeOut));
			}
			
			for(Set<Integer> toRemove : setsToRemove)
				verticalPossibilities.remove(toRemove);
		}
		
		//recalculate
		Set<Integer> allHorizontal = KakuroSums.multiUnion(horizontalPossibilities);
		Set<Integer> allVertical = KakuroSums.multiUnion(verticalPossibilities);
		allPossibilities = KakuroSums.intersection(allHorizontal, allVertical);
	}
}
