package ca.danedmunds.kakuro.model.iterators;

import java.awt.Point;
import java.util.Iterator;

import ca.danedmunds.kakuro.model.BorderBox;
import ca.danedmunds.kakuro.model.CellBox;
import ca.danedmunds.kakuro.model.KakuroBoard;

/**
 * Iterates over the {@link CellBox}s in specified direction, stops on hitting a non {@link CellBox}
 */
public class KakuroIterator implements Iterator<CellBox>, Iterable<CellBox> {

	public enum Direction {
		HORIZONTAL, VERTICAL
	}
	
	private KakuroBoard board;
	private Point position;
	private Direction direction;
	
	public KakuroIterator(KakuroBoard board, Point startPoint, Direction direction) {
		this.board = board;
		this.position = new Point(startPoint);
		this.direction = direction;
		
		if(!board.getBoxAt(startPoint.x, startPoint.y).getType().equals(BorderBox.class)){
			throw(new IllegalArgumentException("Starting point must be a border box"));
		}
		
		advancePosition();
		//TODO check that this makes sense, we need to be able to call next() the first time and not get the second cell
	}
	
	@Override
	public boolean hasNext() {
		if(board.isInBounds(position)){
			return board.getBoxAt(position).getType().equals(CellBox.class);
		}
		
		return false;
	}
	
	@Override
	public CellBox next() {
		CellBox result = null;
		if(hasNext()){
			result = (CellBox)board.getBoxAt(position);
			advancePosition();
		}
		
		return result;
	}

	private void advancePosition(){
		if(direction == Direction.HORIZONTAL){
			position.setLocation(position.x + 1, position.y);
		} else if (direction == Direction.VERTICAL){
			position.setLocation(position.x, position.y + 1);
		}
	}
	@Override
	public void remove() {
		throw(new UnsupportedOperationException("Unsupported: remove()"));
	}

	@Override
	public Iterator<CellBox> iterator() {
		return this;
	}
	
}
