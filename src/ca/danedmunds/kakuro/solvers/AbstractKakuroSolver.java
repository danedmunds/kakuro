package ca.danedmunds.kakuro.solvers;

import ca.danedmunds.kakuro.view.KakuroPanel;

public abstract class AbstractKakuroSolver {

	private KakuroPanel panel;
	
	public AbstractKakuroSolver(KakuroPanel panel){
		setPanel(panel);
	}
	
	public abstract void solve();
	
	protected void stepGUI(){
		getPanel().repaint();
	}

	public KakuroPanel getPanel() {
		return panel;
	}

	protected void setPanel(KakuroPanel panel) {
		this.panel = panel;
	}

}
