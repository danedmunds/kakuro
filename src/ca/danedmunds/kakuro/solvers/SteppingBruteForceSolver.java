package ca.danedmunds.kakuro.solvers;

import java.awt.Point;

import javax.swing.SwingUtilities;

import ca.danedmunds.kakuro.model.CellBox;
import ca.danedmunds.kakuro.model.KakuroBoard;
import ca.danedmunds.kakuro.model.iterators.BoxTypeIterator;
import ca.danedmunds.kakuro.view.KakuroPanel;

public class SteppingBruteForceSolver extends AbstractKakuroSolver {

	private KakuroBoard board;
	private BoxTypeIterator<CellBox> iterator;
	private CellBox current;
	private Point position;
	private int currentGuess;
	
	private boolean isSolved;
	
	public SteppingBruteForceSolver(KakuroPanel panel) {
		super(panel);
		
		board = panel.getBoard();
		iterator = new BoxTypeIterator<CellBox>(board, CellBox.class);
	}
	
	public boolean isSolved(){
		return isSolved;
	}

	@Override
	public void solve() {
		current = iterator.next();
		position = iterator.getPosition();
		currentGuess = 1;
	}
	
	private void advance(){
		//TODO maybe this won't work at end
		current = iterator.next();
		position = iterator.getPosition();
	}
	
	private void retreat(){
		//TODO maybe won't work at end
//		System.out.println("retreating from: "+position);
		
		current.setValue(0);
		
		current = iterator.previous();
		position = iterator.getPosition();
//		System.out.println("new position: "+position);
//		System.out.println("new box value: "+current.getValue());
//		System.out.flush();
	}

	public void step(){
		logicForStep();
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				getPanel().repaint();
			}
		});
	}
	
	private void logicForStep(){
		if(!iterator.hasNext() && board.validateRowAndColumn(position.x, position.y, false)){
			System.out.println("solved");
			isSolved = true;
			return;
		}
		
		if(currentGuess > 9){
			retreat();
			currentGuess = current.getValue() + 1;
			return;
		}
		
		current.setValue(currentGuess);
		
		if(board.validateRowAndColumn(position.x, position.y, true)){
			//so far so good
			advance();
			currentGuess = 1;
			return;
		}
		
		//try the next value
		++currentGuess;
	}
}
