package ca.danedmunds.kakuro.view;

import java.io.IOException;

import javax.swing.JMenu;

import ca.danedmunds.kakuro.io.KakuroFileLoader;

public class RecentFilesMenu extends JMenu {
	
	private KakuroFileLoader loader;
	
	public RecentFilesMenu(String name) {
		super(name);
		
		loader = new KakuroFileLoader();
		try {
			loader.initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
