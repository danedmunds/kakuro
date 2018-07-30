package ca.danedmunds.kakuro.solvers;

import java.awt.Point;

import ca.danedmunds.kakuro.model.CellBox;
import ca.danedmunds.kakuro.model.KakuroBoard;
import ca.danedmunds.kakuro.model.iterators.BoxTypeIterator;
import ca.danedmunds.kakuro.view.KakuroPanel;

public class BruteForceSearchSolver extends AbstractKakuroSolver {

	private KakuroBoard board;
	private BoxTypeIterator<CellBox> iterator;

	public BruteForceSearchSolver(KakuroPanel panel) {
		super(panel);
		
		board = new KakuroBoard(panel.getBoard());
		iterator = new BoxTypeIterator<CellBox>(board, CellBox.class);
	}

	@Override
	public void solve() {
		System.out.println(internalSolve());
		getPanel().setBoard(board);
		getPanel().repaint();
	}
	
	private boolean internalSolve(){
		if(!iterator.hasNext()){
			//done!
			return true;
		}
		
		CellBox current = iterator.next();
		Point position = iterator.getPosition();
		
		for(int i = 1; i < 10; ++i){
			current.setValue(i);
			
			if(board.validateColumn(position.x, position.y, true)
					&& board.validateRow(position.x, position.y, true)){
				
				if(internalSolve()){
					return true;
				}
			}
		}
		
		//undo the damage
		current.setValue(0);
		iterator.previous();
		
		return false;
	}
}
