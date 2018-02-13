import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class Main {
	

	public static void main(String[] args) {
		ImageIcon title;
		JFrame frame = new JFrame();
		JLabel label;
		GamePanel game = new GamePanel();

		
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.black);
	
		
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//inserts the label of the title into the frame
		title = new ImageIcon("Title.png");
		label = new JLabel(title);
		frame.getContentPane().add(label);
		label.setBounds(50, 10, 700, 70);
		
		frame.getContentPane().add(game);
		game.setLocation(50, 90);
		
		
		

		

	}

}
