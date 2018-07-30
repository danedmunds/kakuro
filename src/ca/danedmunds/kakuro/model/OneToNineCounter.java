package ca.danedmunds.kakuro.model;

public class OneToNineCounter {
	
	private boolean[] oneToNine = new boolean[9];
	
	public boolean isTaken(int value){
		return oneToNine[value - 1];
	}
	
	public boolean take(int value){
		boolean oldVal = oneToNine[value - 1];
		oneToNine[value - 1] = true;
		
		return oldVal;
	}
}
