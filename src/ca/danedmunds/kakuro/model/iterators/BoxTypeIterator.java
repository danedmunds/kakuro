package ca.danedmunds.kakuro.model.iterators;

import java.awt.Point;

import ca.danedmunds.kakuro.model.Box;
import ca.danedmunds.kakuro.model.KakuroBoard;

/**
 * Iterates over every single cell in the {@link KakuroBoard}
 *
 */
public class BoxTypeIterator<T extends Box> {
	
	private KakuroBoard board;
	private Class<? extends Box> type;
	
	private Point previousPosition;
	private Point position;
	private Point nextPosition;
	
	public BoxTypeIterator(KakuroBoard board, Class<? extends Box> type){
		this.board = board;
		this.type = type;
		
		nextPosition = new Point(0, 0);
		
		while(board.isInBounds(nextPosition) 
				&& board.getBoxAt(nextPosition).getType() != type) {
			
			blindMoveForward(nextPosition);
		}
		
		if(!board.isInBounds(nextPosition)){
			throw(new IllegalArgumentException("Couldn't find first cell box"));
		}
	}
	
	private void blindMoveForward(Point point) {
		point.setLocation(point.x + 1, point.y);
		if(!board.isInBounds(point)){
			point.setLocation(0, point.y + 1);
		}
	}
	
	private void blindMoveBackward(Point point){
		point.setLocation(point.x - 1, point.y);
		if(!board.isInBounds(point)){
			point.setLocation(board.getWidth() - 1, point.y - 1);
		}
	}
	
	public Point getPosition(){
		return new Point(position);
	}
	
	private void advancePosition(){
		previousPosition = (position == null ? null : new Point(position));
		position = (nextPosition == null ? null : new Point(nextPosition));
		
		do{
			blindMoveForward(nextPosition);
		} while (board.isInBounds(nextPosition) 
				&& board.getBoxAt(nextPosition).getType() != type);
		
		if(!board.isInBounds(nextPosition)){
			nextPosition = null;
		}
	}
	
	private void retreatPosition(){
		nextPosition = (position == null ? null : new Point(position));
		position = (previousPosition == null ? null : new Point(previousPosition));
		
		do {
			blindMoveBackward(previousPosition);
		} while(board.isInBounds(previousPosition) 
				&& board.getBoxAt(previousPosition).getType() != type);
		
		if(!board.isInBounds(previousPosition)){
			previousPosition = null;
		}
	}
	
	public boolean hasNext(){
		return nextPosition != null;
	}
	
	@SuppressWarnings("unchecked")
	public T next(){
		T result = null;
		
		if(hasNext()){
			result = (T)board.getBoxAt(nextPosition);
			advancePosition();
		}
		
		return result;
	}
	
	public boolean hasPrevious(){
		return previousPosition != null;
	}
	
	@SuppressWarnings("unchecked")
	public T previous(){
		T result = null;
		
		if(hasPrevious()){
			result = (T)board.getBoxAt(previousPosition);
			retreatPosition();
		}
		
		return result;
	}
}
