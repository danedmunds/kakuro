package ca.danedmunds.kakuro.io;

import static ca.danedmunds.kakuro.io.IOConstants.BLOCK;
import static ca.danedmunds.kakuro.io.IOConstants.PREFIX_HEIGHT;
import static ca.danedmunds.kakuro.io.IOConstants.PREFIX_WIDTH;

import java.io.IOException;
import java.io.Writer;

import ca.danedmunds.kakuro.model.BlockBox;
import ca.danedmunds.kakuro.model.BorderBox;
import ca.danedmunds.kakuro.model.Box;
import ca.danedmunds.kakuro.model.CellBox;
import ca.danedmunds.kakuro.model.KakuroBoard;

public class KakuroWriter {
	
	private Writer writer;
	
	public KakuroWriter(Writer writer){
		this.writer = writer;
	}
	
	public void close() throws IOException{
		if(writer != null){
			writer.close();
		}
	}

	public void write(KakuroBoard board) throws IOException{
		
		writer.write(PREFIX_WIDTH+board.getWidth()+"\n");
		writer.write(PREFIX_HEIGHT+board.getHeight()+"\n");
		
		for(int y=0; y<board.getHeight(); ++y){
			for(int x=0; x<board.getWidth(); ++x){
				Box current = board.getBoxAt(x, y);
				
				if (current.getType().equals(BorderBox.class)){
					writeBorder((BorderBox)current);
				} else if (current.getType().equals(CellBox.class)){
					writeCell((CellBox)current);
				} else if (current.getType().equals(BlockBox.class)) {
					writer.write(BLOCK);
			 	} else {
					throw(new RuntimeException("Unrecognized type: "+current.getType().toString()));
				}
				
				if(x < board.getWidth()-1){
					writer.write(',');
				}
			}
			
			writer.write('\n');
		}
	}
	
	public void writeBorder(BorderBox box) throws IOException {
		writer.write("("+box.getGoalVerticalSum()+"/"+box.getGoalHorizontalSum()+")");
	}
	
	public void writeCell(CellBox box) throws IOException {
		writer.write(String.valueOf(box.getValue()));
	}
}
