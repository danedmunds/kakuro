package ca.danedmunds.kakuro.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import ca.danedmunds.kakuro.model.KakuroBoard;

public class KakuroFileLoader {
	
	public static final String PREFS_FILE = ".prefs";
	
	private File prefsFile;
	private ArrayList<File> pastFiles;
	private ArrayList<KakuroFileLoaderListener> listeners;
	
	public KakuroFileLoader() {
		prefsFile = new File(PREFS_FILE);
		pastFiles = new ArrayList<File>();
		listeners = new ArrayList<KakuroFileLoaderListener>();
	}
	
	public void initialize() throws IOException {
		if(prefsFile.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(prefsFile));
			String line;
			
			while((line = reader.readLine()) != null){
				pastFiles.add(new File(line));
			}
			
			reader.close();
		}
	}
	
	public void addListener(KakuroFileLoaderListener listener){
		listeners.add(listener);
	}
	
	private void fireListChanged(){
		for(KakuroFileLoaderListener listener : listeners){
			listener.fileListChanged(pastFiles);
		}
	}
	
	public KakuroBoard loadKakuro(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		KakuroReader kakuroReader = new KakuroReader(reader);
		KakuroBoard result = kakuroReader.read();
		
		if(!pastFiles.contains(file)){
			pastFiles.add(0, file);
		} else {
			//move to top of queue
			pastFiles.remove(file);
			pastFiles.add(0, file);
		}
		
		fireListChanged();
		
		return result;
	}
	
	public ArrayList<File> getPastFiles() {
		return pastFiles;
	}
	
	public void close() throws IOException {
		Writer prefWriter = new FileWriter(prefsFile);
		
		for(int i = 0; i < 5 && i < pastFiles.size(); ++i){
			File current = pastFiles.get(i);
			prefWriter.write(current.getAbsolutePath());
			prefWriter.write('\n');
		}
		
		prefWriter.close();
	}
	
	public static interface KakuroFileLoaderListener {
		public void fileListChanged(ArrayList<File> pastFiles);
	}

}
