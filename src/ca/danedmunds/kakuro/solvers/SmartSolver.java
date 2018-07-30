package ca.danedmunds.kakuro.solvers;

import java.util.ArrayList;
import java.util.Set;

import ca.danedmunds.kakuro.model.BorderBox;
import ca.danedmunds.kakuro.model.CellBox;
import ca.danedmunds.kakuro.model.Constants;
import ca.danedmunds.kakuro.model.Constants.Direction;
import ca.danedmunds.kakuro.model.KakuroBoard;
import ca.danedmunds.kakuro.model.iterators.BoxTypeIterator;
import ca.danedmunds.kakuro.view.KakuroPanel;

public class SmartSolver extends AbstractKakuroSolver {

	private KakuroBoard board;
	private BoxTypeIterator<CellBox> iterator;

	public SmartSolver(KakuroPanel panel) {
		super(panel);
		
		board = panel.getBoard();
		board.parse();
		iterator = new BoxTypeIterator<CellBox>(board, CellBox.class);
	}

	@Override
	public void solve() {
		while(iterator.hasNext()) {
			CellBox cell = iterator.next();
			if(cell.getValue() != 0)
				continue;
			
			Set<Integer> possibilities = cell.getAllPossibilities();
			if(possibilities.size() == 1){
				//TODO ugly, don't see a nicer way
				setValueAndPropogate(cell, possibilities.iterator().next());
			}
		}

	}
	
	private void setValueAndPropogate(CellBox cell, int value) {
		cell.setValue(value);
		
		BorderBox horizontal = cell.getHorizontalBorder();
		if(horizontal != null) {
			ArrayList<CellBox> cells = horizontal.getHorizontalCells();
			propagate(cell, cells, Constants.Direction.Horizontal);
		}
		
		BorderBox vertical = cell.getVerticalBorder();
		if(vertical != null){
			ArrayList<CellBox> cells = vertical.getVerticalCells();
			propagate(cell, cells, Constants.Direction.Vertical);
		}
	}

	private void propagate(CellBox cell, ArrayList<CellBox> cells, Direction direction) {
		System.out.println("propagting...");
		for(CellBox otherCell : cells) {
			if(otherCell != cell && otherCell.getValue() == 0){
//				System.out.println("cell possibilities before...");
//				output(otherCell);
				
				otherCell.removePossibility(cell.getValue(), direction);
				Set<Integer> possibilities = otherCell.getAllPossibilities();
				if(possibilities.size() == 1 ) {
					setValueAndPropogate(otherCell, possibilities.iterator().next());
				}
				
//				System.out.println("cell posibilities after...");
//				output(otherCell);
			}
		}
	}
	
	private void output(Set<Integer> set) {
		for(Integer value : set){
			System.out.print(value);
		}
		System.out.println();
	}

}
