package ca.danedmunds.kakuro.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Palette {
	
	public static final int PALETTE_SQUARE_SIZE = 20;
	
	abstract class PaletteSquare {
		
		protected boolean isSelected;
		
		public void draw(Graphics pen, Point location) {
			if(isSelected()) {
				pen.setColor(Color.PINK);
				pen.drawRect(location.x - 1, location.y - 1, 
						location.x + PALETTE_SQUARE_SIZE + 1, location.y + PALETTE_SQUARE_SIZE + 1);
			}
			
			drawSquare(pen, location);
		}
		
		protected abstract void drawSquare(Graphics pen, Point location);
		
		public void setSelected(boolean selected) {
			isSelected = selected;
		}
		
		public boolean isSelected() {
			return isSelected;
		}
	}
	
	class BlockPaletteSquare extends PaletteSquare {

		@Override
		protected void drawSquare(Graphics pen, Point location) {
			pen.setColor(Color.BLACK);
			pen.fillRect(location.x, location.y, 
					location.x + PALETTE_SQUARE_SIZE, location.y + PALETTE_SQUARE_SIZE);
			
			pen.setColor(Color.WHITE);
			pen.drawLine(location.x, location.y, 
					location.x + PALETTE_SQUARE_SIZE, location.y + PALETTE_SQUARE_SIZE);
		}
	}
	
	class CellPaletteSquare extends PaletteSquare {

		@Override
		protected void drawSquare(Graphics pen, Point location) {
			pen.setColor(Color.WHITE);
			pen.drawRect(location.x, location.y, 
					location.x + PALETTE_SQUARE_SIZE, location.y + PALETTE_SQUARE_SIZE);
		}
	}
	
	public void draw(Graphics pen, Point location) {
		//TODO this is where you left off
//		pen.drawString("Palette", arg1, arg2);
	}
}
