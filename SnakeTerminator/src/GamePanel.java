import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	
	private int panelwidth = 700, panellength=460;
	private Timer timer;
	private int delay = 400;
	private int[] snakexlength = new int[700];
	private int[] snakeylength = new int[700];
	
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	private boolean gameover = false;
	
	private int lengthofsnake = 3;
	private int move = 0;
	
	private ImageIcon rightside, leftside,upside, downside, body, tail;
	
	GamePanel(){
		setSize(panelwidth,panellength);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
		rightside = new ImageIcon("rightside.png");
		leftside = new ImageIcon("leftside.png");
		upside = new ImageIcon("upside.png");
		downside = new ImageIcon("downside.png");
		tail = new ImageIcon("tail.png");
		body = new ImageIcon("body.png");
	}
	
	public void paint(Graphics g){
		
		g.setColor(Color.black);
		g.fillRect(0, 0, panelwidth, panellength);
		
		//Draw white boarder for the GamePanel
		g.setColor(Color.white);
		g.drawRect(0, 0, panelwidth, panellength);
		
		

		
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
			
				
		}
		//placement of the snake's tail
		if(up||down||right||left){
			tail.paintIcon(this, g, snakexlength[lengthofsnake-1], snakeylength[lengthofsnake-1]);
		}
		//placement of the snake's body(tail not included)
		for(int i=1; i<lengthofsnake-1; i++){
			if(up||down||right||left){
				body.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
		}

		//placement of the snake's head
		if(right){
			rightside.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		if(down){
			downside.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		if(up){
			upside.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		if(left){
			leftside.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		

		g.dispose();
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
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			//doesn't allow snake to move from left to right
			if(right){
				left = false;
			}
			else{
				left = true;
				right = false;
				up = false;
				down = false;
				move++;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			//doesn't allow snake to move from up to down
			if(down){
				up = false;
			}
			else{
				up = true;
				left = false;
				right = false;
				down = false;
				move++;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			//doesn't allow snake to move from down to up
			if(up){
				down = false;
			}
			else{
				down = true;
				left = false;
				right = false;
				up = false;
				move++;
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//movement of tail
		if((up||down||right||left)&&!gameover){
			snakexlength[lengthofsnake-1]=snakexlength[lengthofsnake-2];
			snakeylength[lengthofsnake-1]=snakeylength[lengthofsnake-2];
		}
		
		//movement of body 
		if((up||down||right||left)&&!gameover){
			for(int i = lengthofsnake-1; i>=1; i--){
				snakexlength[i]=snakexlength[i-1];
				snakeylength[i]=snakeylength[i-1];
			}
		}
		//movement of head when right key pressed
		if(right && !gameover ){
			snakexlength[0]+=20;
			if(snakexlength[0]>=panelwidth){
				snakexlength[0]=0;
			}
			
		}
		//movement of head when left key pressed
		if(left && !gameover){
			snakexlength[0]-=20;
			if(snakexlength[0]<0){
				snakexlength[0]=panelwidth-20;
			}
		}
		//movement of head when up key pressed
		if(up && !gameover ){
			snakeylength[0]-=20;
			
			if(snakeylength[0]<0){
				snakeylength[0]=panellength-20;
			}
		}
		
		//movement of head when down key pressed
		if(down && !gameover ){
			snakeylength[0]+=20;
			
			if(snakeylength[0]>=panellength){
				snakeylength[0]=0;
			}
		}
		

		System.out.println("head: "+snakexlength[0]+","+snakeylength[0]);
		System.out.println("body: "+snakexlength[1]+","+snakeylength[1]);
		System.out.println("tail: "+snakexlength[2]+","+snakeylength[2]);
		
		
		repaint();
		
		
	}

}
