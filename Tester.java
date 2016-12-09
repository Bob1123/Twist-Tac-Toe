import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tester extends JPanel {

	GameBoard playField = new GameBoard(100, 100, 500, 500);
	Button save = new Button(650, 100, 100, 50, "Save");
	Button undo = new Button(650, 150, 100, 50, "Undo");
	Button reset = new Button(750, 100, 100, 50, "New Game");
	Button load = new Button(750, 150, 100, 50, "Load");
	Player p1 = new Player(true, 0);
	Player p2 = new Player(false, 1);

	public Tester() {
		JFrame window = new JFrame("Twist-Tac-Toe");
		window.setBounds(0, 0, 1000, 700);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.add(this);
		window.setVisible(true);		

		// Mouse Clicked
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			// All the work is going to be done in this method here
			@Override
			public void mouseClicked(MouseEvent e) {
				play(getMousePosition());
			}
		});

		// Mouse Moved
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {}
			@Override
			public void mouseDragged(MouseEvent e) {}
		});

	}
	
	public void play(Point p) {
		if(playField.tryMove(p, whosPlaying().getPiece())) {
			p1.swapTurns();
			p2.swapTurns();
			repaint();
		}
		if(undo.contains(p)) {
			System.out.println("Undo");
			playField.undo();
			p1.swapTurns();
			p2.swapTurns();
			repaint();
		}
		if(save.contains(p)) {
			System.out.println("Save Game");
			try {
				RandomAccessFile raf = new RandomAccessFile("savegame.bin", "rw");
				raf.setLength(0);
				playField.save(raf);
				raf.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(reset.contains(p)) {
			playField = new GameBoard(playField.getX(), playField.getY(), playField.getWidth(), playField.getHeight());
			p1.setMyTurn(true);
			p2.setMyTurn(false);
			repaint();
		}
		if(load.contains(p)) {
			playField = new GameBoard(playField.getX(), playField.getY(), playField.getWidth(), playField.getHeight());
			try {
				playField.load(new RandomAccessFile("savegame.bin", "rw"));
				playField.run();
				System.out.println("LOADING GAME");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(playField.getMoves().size() % 2 == 0) {
				p1.setMyTurn(true);
				p2.setMyTurn(false);
			} else if(playField.getMoves().size() % 2 == 1) {
				p1.setMyTurn(false);
				p2.setMyTurn(true);
			}
			repaint();
		}
		if(playField.checkWin(PieceType.BLACK) && playField.checkWin(PieceType.YELLOW)) {
			JOptionPane.showMessageDialog(null, "BOTH COLORS WIN!");
		} else if(playField.checkWin(PieceType.BLACK)) {
			JOptionPane.showMessageDialog(null, "BLACK WINS!");
		} else if(playField.checkWin(PieceType.YELLOW)) {
			JOptionPane.showMessageDialog(null, "YELLOW WINS!");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		playField.draw(g);
		save.draw(g);
		undo.draw(g);
		reset.draw(g);
		load.draw(g);
	}
	
	public Player whosPlaying() {
		if(p1.isMyTurn()) return p1;
		if(p2.isMyTurn()) return p2;
		return p1;
	}

	public static void main(String[] args) {
		System.out.println("Time to test!");
		new Tester();
	}

}
