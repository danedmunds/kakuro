package ca.danedmunds.kakuro.view;

import static ca.danedmunds.kakuro.view.ViewConstants.BOX_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ca.danedmunds.kakuro.model.BlockBox;
import ca.danedmunds.kakuro.model.BorderBox;
import ca.danedmunds.kakuro.model.Box;
import ca.danedmunds.kakuro.model.CellBox;
import ca.danedmunds.kakuro.model.KakuroBoard;

public class KakuroPanel extends JPanel implements MouseListener {
	
	enum Mode { Create, Debug };
	
	private static final String[] BOXTYPES;
	private static final HashMap<String, Class<? extends Box>> NAME2CLASS;
	static {
		NAME2CLASS = new HashMap<String, Class<? extends Box>>();
		
		NAME2CLASS.put("Cell", CellBox.class);
		NAME2CLASS.put("Border", BorderBox.class);
		NAME2CLASS.put("Block", BlockBox.class);
		
		BOXTYPES = new String[NAME2CLASS.size()];
		NAME2CLASS.keySet().toArray(BOXTYPES);
	}
	
	private Mode mode = Mode.Create;
	//default 12*16
	private int xoffset = 10;
	private int yoffset = 10;
	private KakuroBoard board;
	
	private Point selectedPoint;

	private Box selectedBox;
	
	public KakuroPanel(int width, int height){
		board = new KakuroBoard(width, height);
		addMouseListener(this);
	}
	
	public int getKakuroHeight(){
		return board.getHeight();
	}
	
	public int getKakuroWidth(){
		return board.getWidth();
	}
	
	public KakuroBoard getBoard(){
		return board;
	}
	
	public void setBoard(KakuroBoard board){
		this.board = board;
		
		repaint();
	}
	
	public void setMode(Mode mode){
		this.mode = mode;
	}
	
	public void clear(Graphics pen){
		pen.setColor(Color.WHITE);
		pen.fillRect(0, 0, 900, 800);
	}
	
	public void paint(Graphics pen) {
		clear(pen);
		
		for(int x=0; x<board.getWidth(); ++x){
			for(int y=0; y<board.getHeight(); ++y){
				Box box = board.getBoxAt(x, y);
				BoxRenderer.render(box, pen, xoffset, yoffset, x, y);
			}
		}
	}

	public void mouseClicked(MouseEvent click) {
		Point result = getClickedCoordinates(click);
		if(result.x != -1 && result.y != -1){
			int x = result.x;
			int y = result.y;
			
			if(mode == Mode.Create){
				handleCreateClick(x, y);
			} else if (mode == Mode.Debug){
				if(selectedBox != null){
					selectedBox.setSelected(false, false);
				}
				selectedBox = board.getBoxAt(result);
				
				selectedBox.setSelected(true, false);
				selectedPoint = result;
				
				repaint();
			}
		}
	}

	private void handleCreateClick(int x, int y) {
		//valid click
		
		
		
		int optionSelection = JOptionPane.showOptionDialog(this, "Which type would you like to make this box?", 
				"Change Box Type...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, BOXTYPES, "Border");
		
		
		if(optionSelection == -1){
			//cancelled
			return;
		}
		
		Class<? extends Box> selectedType = NAME2CLASS.get(BOXTYPES[optionSelection]);
		if(selectedType == null){
			throw(new IllegalStateException("Unknown box type: "+BOXTYPES[optionSelection]+", unable to map"));
		}
		
		if(selectedType.equals(BorderBox.class)){
			String horizontal = JOptionPane.showInputDialog("Sum for horizontal:");
			String vertical = JOptionPane.showInputDialog("Sum for vertical:");
			try{
				int horizontalSum = horizontal.equals("") ? 0 : Integer.parseInt(horizontal);
				int verticalSum = vertical.equals("") ? 0 : Integer.parseInt(vertical);
				
				if(horizontalSum != 0 && (horizontalSum < 1 || horizontalSum > 45) 
						|| verticalSum != 0 && (verticalSum < 1 || verticalSum > 45)){
					//invalid value
					return;
				}
				board.setBoxAt(x, y, new BorderBox(horizontalSum, verticalSum));
			} catch (NumberFormatException e){
				//sink it and ignore the input
				return;
			}
		} else if(selectedType.equals(CellBox.class)){
			CellBox box = new CellBox();
			board.setBoxAt(x, y, box);
			String value = JOptionPane.showInputDialog("value:");
			try{
				int val = Integer.parseInt(value);
				box.setValue(val);
			} catch (NumberFormatException e){
				e.printStackTrace();
			}
		} else if (selectedType.equals(BlockBox.class)) {
			board.setBoxAt(x, y, new BlockBox());
		} else {
			throw(new RuntimeException("Unrecognized type: "+selectedType));
		}
		
		repaint();
	}

	private Point getClickedCoordinates(MouseEvent click) {
		int x = click.getPoint().x;
		int y = click.getPoint().y;
		
		int boardWidth = board.getWidth();
		int boardHeight = board.getHeight();
		
		//check for out of bounds
		if(x < xoffset || x > boardWidth*BOX_SIZE || y < yoffset || y > boardHeight*BOX_SIZE){
			return new Point(-1, -1);
		}
		
		int posX = (x - xoffset) / BOX_SIZE;
		int posY = (y - yoffset) / BOX_SIZE;
		
		Point result = new Point(posX, posY);
		
		return result;
	}
	

	public Point getSelectedPoint() {
		return selectedPoint;
	}

	public void setSelectedPoint(Point selectedPoint) {
		this.selectedPoint = selectedPoint;
	}

	public Box getSelectedBox() {
		return selectedBox;
	}

	public void setSelectedBox(Box selectedBox) {
		this.selectedBox = selectedBox;
	}

	public void mouseEntered(MouseEvent arg0) {
		//no-op
	}

	public void mouseExited(MouseEvent arg0) {
		//no-op
	}

	public void mousePressed(MouseEvent arg0) {
		//no-op
	}

	public void mouseReleased(MouseEvent arg0) {
		//no-op
	}

}
