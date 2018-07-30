package ca.danedmunds.kakuro.model;


public abstract class Box {
		
	protected boolean selected;
	
	public void setSelected(boolean selected, boolean isSubCall) {
		this.selected = selected;
	}
	
	public boolean isSelected(){
		return selected;
	}

	public abstract Class<? extends Box> getType();
	public abstract Box copy();

}
