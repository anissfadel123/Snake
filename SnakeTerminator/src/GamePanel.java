import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	private int[] snakexlength = new int[700];
	private int[] snakeylength = new int[700];
	
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	private boolean gameover = false;
	
	private int lengthofsnake = 3;
	private int move = 0;
	
	private ImageIcon rightside, leftside,frontside, body, tail;
	
	GamePanel(){
		setSize(700,450);
		addKeyListener(this);
		
		rightside = new ImageIcon("rightside.png");
		leftside = new ImageIcon("leftside.png");
		frontside = new ImageIcon("frontside.png");
		tail = new ImageIcon("tail.png");
		body = new ImageIcon("body.png");
	}
	
	public void paint(Graphics g){
		
		//Draw white boarder for the GamePanel
		g.setColor(Color.white);
		g.drawRect(0, 0, 700, 450);
		
		

		
		//initial location and size of the snake
		if (move == 0 && lengthofsnake == 3){
			//snakes initial head location
			snakexlength[0]=60;
			snakeylength[0]=20;
			//snakes initial body location
			snakexlength[1]=40;
			snakeylength[1]=20;
			
			//snakes tail location
			snakexlength[2]=20;
			snakeylength[2]=20;
			
			rightside.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			body.paintIcon(this, g, snakexlength[1], snakeylength[1]);
			tail.paintIcon(this, g, snakexlength[2], snakeylength[2]);
			
			right = true;
			
				
		}
		
		
		for(int i = 0; i<lengthofsnake; i++){
			if(right){
				
			}
			
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			//doesn't allow snake to move from right to left
			if(left){
				right = false;
			}
			else{
				right = true;
				left = false;
				up = false;
				down = false;
				move++;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
