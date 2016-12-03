import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tester extends JPanel {

	GameBoard playField = new GameBoard(100, 100, 500, 500);
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
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(getMousePosition());
				Point compCell = playField.getPosition(getMousePosition());
				System.out.println(compCell);
				if(compCell.x != -1 && compCell.y != -1) {
					Point pieceCell = playField.getBoard()[compCell.x][compCell.y].getPosition(getMousePosition());
					if(playField.getBoard()[compCell.x][compCell.y].getBoard()[pieceCell.x][pieceCell.y].getType() == PieceType.BLANK){
						playField.getBoard()[compCell.x][compCell.y].getBoard()[pieceCell.x][pieceCell.y].setType(whosPlaying().getPiece());
						p1.swapTurns();
						p2.swapTurns();
						repaint();
					}
				}
			}
		});

		// Mouse Moved
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		playField.draw(g);
	}
	
	public Player whosPlaying() {
		if(p1.myTurn) return p1;
		if(p2.myTurn) return p2;
		return p1;
	}

	public static void main(String[] args) {
		System.out.println("Time to test!");
		new Tester();
	}

}
