package ca.danedmunds.kakuro.view;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import ca.danedmunds.kakuro.io.KakuroFileLoader;
import ca.danedmunds.kakuro.io.KakuroWriter;
import ca.danedmunds.kakuro.io.KakuroFileLoader.KakuroFileLoaderListener;
import ca.danedmunds.kakuro.model.BorderBox;
import ca.danedmunds.kakuro.model.Box;
import ca.danedmunds.kakuro.model.CellBox;
import ca.danedmunds.kakuro.model.KakuroBoard;
import ca.danedmunds.kakuro.model.iterators.BoxTypeIterator;
import ca.danedmunds.kakuro.solvers.SmartSearchSolver;
import ca.danedmunds.kakuro.solvers.SmartSolver;
import ca.danedmunds.kakuro.solvers.SteppingBruteForceSolver;
import ca.danedmunds.kakuro.view.KakuroPanel.Mode;

public class KakuroFrame extends JFrame {
	
	private KakuroPanel panel;
	private KakuroFileLoader fileLoader;
	private JMenu recentFilesMenu;

	public KakuroFrame(){
		super("Kakuro");
		
		initializeContentPane();
		initializeMenues();
		initializeFileLoader();
		
		initializeFrame();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					fileLoader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void initializeFileLoader() {
		try {
			fileLoader = new KakuroFileLoader();
			fileLoader.initialize();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		fileLoader.addListener(new KakuroFileLoaderListener(){
			@Override
			public void fileListChanged(ArrayList<File> pastFiles) {
				recentFilesMenu.removeAll();
				buildRecentFilesMenu(pastFiles);
			}
		});
		
		buildRecentFilesMenu(fileLoader.getPastFiles());
	}
	
	private void buildRecentFilesMenu(ArrayList<File> pastFiles) {
		for(int i = 0; i < pastFiles.size() && i < 5; ++i){
			final File current = pastFiles.get(i);
			JMenuItem loadThisFile = new JMenuItem(new AbstractAction(current.getName()){
				@Override
				public void actionPerformed(ActionEvent e) {
					loadKakuroFile(current);
				}
			});
			recentFilesMenu.add(loadThisFile);
		}
	}
	
	private void initializeFrame() {
		setSize(900, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initializeContentPane(){
		panel = new KakuroPanel(12, 16);
		setContentPane(panel);
	}
	
	private void initializeMenues(){
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		createFileMenu(menuBar);
		createSolveMenu(menuBar);
		createDebugMenu(menuBar);
	}
	
	private void createDebugMenu(JMenuBar menuBar) {
		JMenu debugMenu = new JMenu("Debug");
		menuBar.add(debugMenu);
		
		JMenuItem switchToDebug = new JMenuItem(new AbstractAction("Switch to debug"){
			public void actionPerformed(ActionEvent e) {
				panel.setMode(Mode.Debug);
			}
		});
		
		debugMenu.add(switchToDebug);
		
		JMenuItem describe = new JMenuItem(new AbstractAction("Describe selected"){
			public void actionPerformed(ActionEvent e) {
				Box selected = panel.getSelectedBox();
				if(selected != null){
					JOptionPane.showMessageDialog(KakuroFrame.this, BoxDescriber.describe(selected));
//					System.out.println("=========");
//					System.out.println(BoxDescriber.describe(selected));
//					System.out.print("=========");
				}
			}
		});
		
		debugMenu.add(describe);
		
		JMenuItem enumerateCells = new JMenuItem(new AbstractAction("Enumerate Cells"){
			public void actionPerformed(ActionEvent e) {
				BoxTypeIterator<CellBox> iterator = new BoxTypeIterator<CellBox>(panel.getBoard(), CellBox.class);
				
				while(iterator.hasNext()){
					iterator.next();
					System.out.println(iterator.getPosition());
				}
			}
		});
		
		debugMenu.add(enumerateCells);
		
		JMenuItem enumerateBorder = new JMenuItem(new AbstractAction("Enumerate Border"){
			public void actionPerformed(ActionEvent e) {
				BoxTypeIterator<BorderBox> iterator = new BoxTypeIterator<BorderBox>(panel.getBoard(), BorderBox.class);
				
				while(iterator.hasNext()){
					iterator.next();
					System.out.println(iterator.getPosition());
				}
			}
		});
		
		debugMenu.add(enumerateBorder);
		
		JMenuItem parseBoard = new JMenuItem(new AbstractAction("Parse Board"){
			public void actionPerformed(ActionEvent e) {
				panel.getBoard().parse();
			}
		});
		
		debugMenu.add(parseBoard);
		
		JMenuItem validateBoard = new JMenuItem(new AbstractAction("Validate Board"){
			public void actionPerformed(ActionEvent e) {
				BoxTypeIterator<CellBox> iterator = new BoxTypeIterator<CellBox>(panel.getBoard(), CellBox.class);
				
				while(iterator.hasNext()){
					iterator.next();
					Point position = iterator.getPosition();
					boolean v = panel.getBoard().validateColumn(position.x, position.y, false);
					boolean h = panel.getBoard().validateRow(position.x, position.y, false);

					System.out.println("("+position.x+", "+position.y+") -> H:"+h+" V:"+v);
				}
			}
		});
		
		debugMenu.add(validateBoard);
		
		JMenuItem validateCell = new JMenuItem(new AbstractAction("Validate Selected Cell"){
			public void actionPerformed(ActionEvent e) {
				Point position = panel.getSelectedPoint();
				boolean v = panel.getBoard().validateColumn(position.x, position.y, false);
				boolean h = panel.getBoard().validateRow(position.x, position.y, false);

				System.out.println("("+position.x+", "+position.y+") -> H:"+h+" V:"+v);
			}
		});
		
		debugMenu.add(validateCell);
	}

	private void createSolveMenu(JMenuBar menuBar){
		JMenu solveMenu = new JMenu("Solve");
		menuBar.add(solveMenu);
		
		JMenuItem bruteForceStepSearch = new JMenuItem(new AbstractAction("Brute Force Search"){
			public void actionPerformed(ActionEvent e) {
				
				Thread thread = new Thread(new Runnable(){
					@Override
					public void run() {
						SteppingBruteForceSolver solver = new SteppingBruteForceSolver(panel);
						solver.solve();
						
						int step = 0;
						while(!solver.isSolved()){
							System.out.println("Step "+(++step));
							solver.step();
						}
					}
				});
				
				thread.start();
				
			}
		});
		
		solveMenu.add(bruteForceStepSearch);
		
		JMenuItem smartSolvedStepper = new JMenuItem(new AbstractAction("Smart Search"){
			public void actionPerformed(ActionEvent e) {
				
				Thread thread = new Thread(new Runnable(){
					@Override
					public void run() {
						SmartSearchSolver solver = new SmartSearchSolver(panel);
						solver.solve();
						
						int step = 0;
						while(!solver.isSolved()){
							System.out.println("Step "+(++step));
							solver.step();
						}
					}
				});
				
				thread.start();
				
			}
		});
		
		solveMenu.add(smartSolvedStepper);
		
		JMenuItem smartSolver = new JMenuItem(new AbstractAction("Smart Solve (no guess)"){
			public void actionPerformed(ActionEvent e) {
				
				Thread thread = new Thread(new Runnable(){
					@Override
					public void run() {
						SmartSolver solver = new SmartSolver(panel);
						solver.solve();
						
						panel.repaint();
						
//						int step = 0;
//						while(!solver.isSolved()){
//							System.out.println("Step "+(++step));
//							solver.step();
//						}
					}
				});
				
				thread.start();
				
			}
		});
		
		solveMenu.add(smartSolver);
	}

	private void createFileMenu(JMenuBar menuBar) {
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem saveItem = new JMenuItem(new AbstractAction("Save..."){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if(JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(KakuroFrame.this)){
					try{
						File file = chooser.getSelectedFile();
						KakuroWriter kWriter = new KakuroWriter(new FileWriter(file));
						kWriter.write(panel.getBoard());
						kWriter.close();
					} catch (IOException ioe){
						ioe.printStackTrace();
					}
				}
			}
		});
		
		fileMenu.add(saveItem);
		
		JMenuItem loadItem = new JMenuItem(new AbstractAction("Load..."){
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				if(JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(KakuroFrame.this)){
					File file = chooser.getSelectedFile();
					loadKakuroFile(file);
				}
			}
		});
		
		fileMenu.add(loadItem);
		
		fileMenu.add(new JSeparator());
		
		recentFilesMenu = new JMenu("Recent...");
		fileMenu.add(recentFilesMenu);
	}
	
	public void loadKakuroFile(File file) {
		try {
			KakuroBoard loaded = fileLoader.loadKakuro(file);
			panel.setBoard(loaded);
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		KakuroFrame frame = new KakuroFrame();
		frame.setVisible(true);
	}
}
