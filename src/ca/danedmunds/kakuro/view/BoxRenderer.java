package ca.danedmunds.kakuro.view;

import static ca.danedmunds.kakuro.view.ViewConstants.BOX_SIZE;

import java.awt.Color;
import java.awt.Graphics;

import ca.danedmunds.kakuro.model.BlockBox;
import ca.danedmunds.kakuro.model.BorderBox;
import ca.danedmunds.kakuro.model.Box;
import ca.danedmunds.kakuro.model.CellBox;

public class BoxRenderer {
	
	public static void render(Box box, Graphics pen, int xoffsetPx, int yoffsetPx, int xpos, int ypos) {
		Class<? extends Box> type = box.getType();
		
		if(type.equals(BlockBox.class)){
			render((BlockBox) box, pen, xoffsetPx, yoffsetPx, xpos, ypos);
		} else if (type.equals(BorderBox.class)) {
			render((BorderBox) box, pen, xoffsetPx, yoffsetPx, xpos, ypos);
		} else if (type.equals(CellBox.class)) {
			render((CellBox) box, pen, xoffsetPx, yoffsetPx, xpos, ypos);
		}
	}
	
	public static void render(CellBox cell, Graphics pen, int xoffsetPx, int yoffsetPx, int xpos, int ypos) {
		int x = xoffsetPx + xpos*BOX_SIZE;
		int y = yoffsetPx + ypos*BOX_SIZE;
		int width = BOX_SIZE;
		int height = BOX_SIZE;
		
		if(cell.isSelected()){
			pen.setColor(Color.PINK);
		} else {
			pen.setColor(Color.WHITE);
		}
		pen.fillRect(x, y, width, height);
		
		pen.setColor(Color.BLACK);
		pen.drawRect(x, y, width, height);
		
		if(cell.getValue() != 0){
			pen.setColor(Color.BLACK);
			pen.drawString(Integer.toString(cell.getValue()), x + width/2 - 3, y+height/2 + 3);
		}
	}
	
	public static void render(BorderBox border, Graphics pen, int xoffsetPx, int yoffsetPx, int xpos, int ypos) {
		int x = xoffsetPx + xpos*BOX_SIZE;
		int y = yoffsetPx + ypos*BOX_SIZE;
		int width = BOX_SIZE;
		int height = BOX_SIZE;
		int goalHorizontalSum = border.getGoalHorizontalSum();
		int goalVerticalSum = border.getGoalVerticalSum();
		
		if(border.isSelected()){
			pen.setColor(Color.PINK);
		} else {
			pen.setColor(Color.BLACK);
		}
		pen.fillRect(x, y, width, height);
		
		pen.setColor(Color.BLACK);
		pen.drawRect(x, y, width, height);
		
		pen.setColor(Color.WHITE);
		pen.drawLine(x, y, x+width, y+height);
		
		if(goalHorizontalSum != 0){
			pen.setColor(Color.WHITE);
			pen.drawString(Integer.toString(goalHorizontalSum), x + width/2 + 3, y+height/2);
		}
		
		if(goalVerticalSum != 0){
			pen.setColor(Color.WHITE);
			pen.drawString(Integer.toString(goalVerticalSum), x+5 , y+height/2 + 15);
		}
	}
	
	public static void render(BlockBox block, Graphics pen, int xoffsetPx, int yoffsetPx, int xpos, int ypos) {
		int x = xoffsetPx + xpos*BOX_SIZE;
		int y = yoffsetPx + ypos*BOX_SIZE;
		int width = BOX_SIZE;
		int height = BOX_SIZE;
		
		if(block.isSelected()){
			pen.setColor(Color.PINK);
		} else {
			pen.setColor(Color.BLACK);
		}
		pen.fillRect(x, y, width, height);
		
		pen.setColor(Color.BLACK);
		pen.drawRect(x, y, width, height);
	}
	
}
