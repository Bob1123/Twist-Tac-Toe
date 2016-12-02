import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tester extends JPanel {
	
	GameBoard playField = new GameBoard(100, 100, 500, 500);
	
	public Tester() {
		JFrame window = new JFrame("Twist-Tac-Toe");
		window.setBounds(0, 0, 700, 700);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.add(this);
		window.setVisible(true);
		
		System.out.println(playField.getNE().getArrows()[0]);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        playField.draw(g);
    }
	
	public static void main(String[] args) {
		System.out.println("Time to test!");
		new Tester();
	}

}
