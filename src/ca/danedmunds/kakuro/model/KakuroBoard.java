package ca.danedmunds.kakuro.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ca.danedmunds.kakuro.model.iterators.BoxTypeIterator;
import ca.danedmunds.kakuro.model.iterators.KakuroIterator;
import ca.danedmunds.kakuro.model.iterators.KakuroIterator.Direction;
import ca.danedmunds.kakuro.solvers.KakuroSums;

public class KakuroBoard {
	
	private int width;
	private int height;
	private Box[][] grid;
	private boolean parsed;
	
	private ArrayList<BorderBox> borderBoxes;
	
	public KakuroBoard(int width, int height){
		setWidth(width);
		setHeight(height);
		
		initializeCleanBoard(width, height);
	}

	private void initializeCleanBoard(int width, int height) {
		grid = new Box[height][width];
		for(int y = 0; y < height; ++y){
			for(int x = 0; x < width; ++x){
				setBoxAt(x, y, new CellBox());
			}
		}
	}
	
	public KakuroBoard(KakuroBoard toCopy){
		setWidth(toCopy.getWidth());
		setHeight(toCopy.getHeight());
		
		copyBoard(toCopy);
	}

	private void copyBoard(KakuroBoard toCopy) {
		grid = new Box[height][width];
		for(int y = 0; y < height; ++y){
			for(int x = 0; x < width; ++x){
				setBoxAt(x, y, toCopy.getBoxAt(x, y).copy());
			}
		}
	}
	
	public boolean validate(boolean allowEmpty){
		//TODO implement
		return false;
	}
	
	public void parse() {
		if(parsed)
			return;
		
		borderBoxes = new ArrayList<BorderBox>();
		
		BoxTypeIterator<BorderBox> borderIterator = new BoxTypeIterator<BorderBox>(this, BorderBox.class);
		while(borderIterator.hasNext()){
			BorderBox border = borderIterator.next();
			borderBoxes.add(border);
			
			Point position = borderIterator.getPosition();
			
			KakuroIterator horizontalIterator = new KakuroIterator(this, position, Direction.HORIZONTAL);
			while(horizontalIterator.hasNext()){
				border.addHorizontalCell(horizontalIterator.next());
			}
			
			KakuroIterator verticalIterator = new KakuroIterator(this, position, Direction.VERTICAL);
			while(verticalIterator.hasNext()){
				border.addVerticalCell(verticalIterator.next());
			}
			
			int horizontalSum = border.getGoalHorizontalSum();
			if(horizontalSum != 0){
				ArrayList<CellBox> horizontalCells = border.getHorizontalCells();
				int horizontalCount = horizontalCells.size();
				
				List<Set<Integer>> hPossibilities = KakuroSums.getSums(horizontalSum, horizontalCount);
				if(hPossibilities == null){
					throw(new RuntimeException("Unable to find sums for "+horizontalSum+" in "+horizontalCount + " @ " + position));
				}
				
//				border.setHorizontalPossibilities(hPossibilities);
				for(CellBox cell : horizontalCells){
					cell.setHorizontalBorder(border);
					cell.setHorizontalPossibilities(hPossibilities);
				}
			}
			
			int verticalSum = border.getGoalVerticalSum();
			if(verticalSum != 0){
				ArrayList<CellBox> verticalCells = border.getVerticalCells();
				int verticalCount = verticalCells.size();
				
				List<Set<Integer>> vPossibilities = KakuroSums.getSums(verticalSum, verticalCount);
				if(vPossibilities == null){
					throw(new RuntimeException("Unable to find sums for "+verticalSum+" in "+verticalCount + " @ " + position));
				}
				
//				border.setVerticalPossibilties(vPossibilities);
				for(CellBox cell : verticalCells){
					cell.setVerticalBorder(border);
					cell.setVerticalPossibilities(vPossibilities);
				}
			}
		}
		
		parsed = true;
	}
	
	public ArrayList<BorderBox> getBorder(){
		return new ArrayList<BorderBox>(borderBoxes);
	}
	
	public boolean validateRowAndColumn(int x, int y, boolean allowEmpty){
		return validateColumn(x, y, allowEmpty) && validateRow(x, y, allowEmpty);
	}
	
	public boolean validateRow(int x, int y, boolean allowEmpty){
		Point borderCoords = getGoverningHorizontalBorderCoords(x, y);
		return validateFrom(borderCoords, Direction.HORIZONTAL, allowEmpty);
	}
	
	public boolean validateColumn(int x, int y, boolean allowEmpty){
		Point borderCoords = getGoverningVerticalBorderCoords(x, y);
		return validateFrom(borderCoords, Direction.VERTICAL, allowEmpty);
	}
	
	private boolean validateFrom(Point startPosition, Direction direction, boolean allowEmpty) {
		Box box = getBoxAt(startPosition);
		//TODO need to make blocks be borders completely
		if(!box.getType().equals(BorderBox.class) && !box.getType().equals(BlockBox.class)){
			throw(new IllegalArgumentException("startPosition must be of type Border"));
		}
		
		BorderBox border = (BorderBox)box;
		int goalSum;
		
		if(direction == Direction.HORIZONTAL){
			goalSum = border.getGoalHorizontalSum();
		} else if (direction == Direction.VERTICAL){
			goalSum = border.getGoalVerticalSum();
		} else {
			throw(new IllegalArgumentException("Invalid direction: "+direction));
		}
		
		if(goalSum == 0){
			//no required sum
			return true;
		}
		
		OneToNineCounter oneToNine = new OneToNineCounter();
		int sum = 0;
		boolean thereIsAnEmptyBox = false;
		
		KakuroIterator iterator = new KakuroIterator(this, startPosition, direction);
		for(CellBox cell : iterator){
			int value = cell.getValue();
			
			if(cell.getValue() == 0 && !allowEmpty){
				return false;
			}
			
			if(value == 0){
				thereIsAnEmptyBox = true;
				continue;
			}
			
			if(oneToNine.take(value)){
				return false;
			}
			
			sum += value;
			if(sum > goalSum){
				return false;
			}
		}
		
		return thereIsAnEmptyBox || sum == goalSum;
	}
	
	private Point getGoverningVerticalBorderCoords(int x, int y){
		Box current = getBoxAt(x, y);
		if(current.getType().equals(BorderBox.class) || current.getType().equals(BlockBox.class)){
			return new Point(x, y);
		} 
		
		return getGoverningVerticalBorderCoords(x, y - 1);
	}
	
	private Point getGoverningHorizontalBorderCoords(int x, int y){
		Box current = getBoxAt(x, y);
		if(current.getType().equals(BorderBox.class) || current.getType().equals(BlockBox.class)){
			return new Point(x, y);
		} 
		
		return getGoverningHorizontalBorderCoords(x - 1, y);
	}
	
	public Box getBoxAt(int x, int y){
		validateCoordinates(x, y);
		return grid[y][x];
	}
	
	public Box getBoxAt(Point position){
		return getBoxAt(position.x, position.y);
	}
	
	public void setBoxAt(int x, int y, Box box){
		validateCoordinates(x, y);
		grid[y][x] = box;
	}
	
	private void validateCoordinates(int x, int y){
		if(x >= width || x < 0){
			throw(new IllegalArgumentException("Invalid width: "+x+", width must be 0 to "+(width - 1)));
		}
		
		if(y >= height || y < 0){
			throw(new IllegalArgumentException("Invalud height: "+y+", height must be 0 to "+(height - 1)));
		}
	}
	
	public boolean isInBounds(Point position){
		return position.x >= 0 && position.x < getWidth() &&
				position.y >= 0 && position.y < getHeight();
	}

	public int getWidth() {
		return width;
	}

	private void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		this.height = height;
	}
}
