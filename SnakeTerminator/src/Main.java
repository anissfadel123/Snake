import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.FlowLayout;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GamePanel game = new GamePanel();
		
		frame.setTitle("Snakinator");
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(game);
		game.setBackground(new Color(145,0,0));
		
		
		//frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		

	}

}
