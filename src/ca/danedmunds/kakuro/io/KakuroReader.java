package ca.danedmunds.kakuro.io;

import static ca.danedmunds.kakuro.io.IOConstants.BLOCK;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import ca.danedmunds.kakuro.model.BlockBox;
import ca.danedmunds.kakuro.model.BorderBox;
import ca.danedmunds.kakuro.model.CellBox;
import ca.danedmunds.kakuro.model.KakuroBoard;

public class KakuroReader {
	
	private static final String widthPrefix = "w=";
	private static final String heightPrefix = "h=";
	
	private BufferedReader reader;
	private int width = -1;
	private int height = -1;
	
	public KakuroReader(BufferedReader reader){
		this.reader = reader;
	}
	
	public KakuroBoard read() throws IOException {
		parseHeaderLine(reader.readLine());
		parseHeaderLine(reader.readLine());
		
		KakuroBoard board = new KakuroBoard(getWidth(), getHeight());
		
		String line;
		int y = 0;
		while((line = reader.readLine()) != null){
			StringTokenizer st = new StringTokenizer(line, "\n,");
			
			int x = 0;
			while(st.hasMoreTokens()){
				String token = st.nextToken();
				
				if (token.startsWith("(")){
					int barIndex = token.indexOf('/');
					int verticalSum = Integer.parseInt(token.substring(1, barIndex));
					int horizontalSum = Integer.parseInt(token.substring(barIndex+1, token.length()-1));
					
					board.setBoxAt(x, y, new BorderBox(horizontalSum, verticalSum));
				} else if (token.equals(BLOCK)){
					board.setBoxAt(x, y, BlockBox.BLOCK_BOX);
				} else if(token.length() > 0 && token.length() <= 2 && Character.isDigit(token.charAt(0))){
					int value = Integer.parseInt(token);
					board.setBoxAt(x, y, new CellBox(value));
				} else {
					throw(new RuntimeException("Unrecognized value: "+token));
				}
				
				++x;
			}
			
			++y;
		}
		
		return board;
	}
	
	public void parseHeaderLine(String line){
		if(line.startsWith(widthPrefix)){
			String widthString = line.substring(widthPrefix.length());
			setWidth(Integer.parseInt(widthString));
		} else if(line.startsWith(heightPrefix)){
			String heightString = line.substring(heightPrefix.length());
			setHeight(Integer.parseInt(heightString));
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void close() throws IOException {
		if(reader != null){
			reader.close();
		}
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
