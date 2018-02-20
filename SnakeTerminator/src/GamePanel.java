import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	
	public static final int WIDTH = 700, HEIGHT=460;
	private Timer timer;
	private int delay = 100;
	//max length of snake
	private int maxlength = (WIDTH/20)*(HEIGHT/20);
	private int[] snakexlength = new int[maxlength];
	private int[] snakeylength = new int[(maxlength)];
	
	
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	private boolean gameover = false;
	
	private int lengthofsnake = 3;
	private int move = 0, score = 0;
	private Random random;
	private ImageIcon background;
	
	private ImageIcon rightside, leftside,upside, downside, body, tail;
	private Enemy ladybug
			//,ladybug2
	;
	
	GamePanel(){
		setSize(WIDTH,HEIGHT);
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		
		random = new Random();
		rightside = new ImageIcon("rightside.png");
		leftside = new ImageIcon("leftside.png");
		upside = new ImageIcon("upside.png");
		downside = new ImageIcon("downside.png");
		tail = new ImageIcon("tail.png");
		body = new ImageIcon("body.png");
		//enemy = new ImageIcon("enemy.png");
		background = new ImageIcon("background.png");
		ladybug = new Enemy();
		//ladybug2 = new Enemy();
		
		
		timer.start();
	}
	
	public void paint(Graphics g){
		background.paintIcon(this, g, 0, 0);
		//g.setColor(Color.black);
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Draw white boarder for the GamePanel
		g.setColor(Color.white);
		g.drawRect(0, 0, WIDTH, HEIGHT);
		

	
		//location of enemy

		ladybug.enemy.paintIcon(this, g, ladybug.getxloc(), 
				                 ladybug.getyloc());
		/*
		ladybug2.enemy.paintIcon(this, g, ladybug2.getxloc(), 
                ladybug2.getyloc());
		*/
		
		//initial location and size of the snake and location of the enemy
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
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			move = 0;
			lengthofsnake = 3;
			gameover=false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//ends game if snake intercepts itself
		for(int i=4; i<lengthofsnake; i++){
			if((snakexlength[0]==snakexlength[i])&&(snakeylength[0]==snakeylength[i])){
				gameover = true;
			}
		}
		
		if(lengthofsnake == maxlength){
			gameover = true;
		}
		if(!gameover){
			//movement of tail
			if(up||down||right||left){
				snakexlength[lengthofsnake-1]=snakexlength[lengthofsnake-2];
				snakeylength[lengthofsnake-1]=snakeylength[lengthofsnake-2];
			}
			
			//movement of body 
			if(up||down||right||left){
				for(int i = lengthofsnake-2; i>=1; i--){
					snakexlength[i]=snakexlength[i-1];
					snakeylength[i]=snakeylength[i-1];
				}
			}
			//movement of head when right key pressed
			if(right){
				snakexlength[0]+=20;
				if(snakexlength[0]>=WIDTH){
					snakexlength[0]=0;
				}
				
			}
			//movement of head when left key pressed
			if(left){
				snakexlength[0]-=20;
				if(snakexlength[0]<0){
					snakexlength[0]=WIDTH-20;
				}
			}
			//movement of head when up key pressed
			if(up){
				snakeylength[0]-=20;
				
				if(snakeylength[0]<0){
					snakeylength[0]=HEIGHT-20;
				}
			}
			
			//movement of head when down key pressed
			if(down){
				snakeylength[0]+=20;
				
				if(snakeylength[0]>=HEIGHT){
					snakeylength[0]=0;
				}
			}
			
			ladybug.moveX(10);
			if (ladybug.getxloc()>=WIDTH)
				ladybug.setxloc(0);
			ladybug.moveY((random.nextInt(3)-1)*3);
			if (ladybug.getyloc()>=HEIGHT)
				ladybug.setyloc(0);
			if (ladybug.getyloc()<0)
				ladybug.setyloc(HEIGHT-20);
			//if snake's head intersect the enemy,
			//the enemy will be place in another location
			//the score will increase by 5 point
			//the snake will increase in length by 1
			if(
					(snakexlength[0]>=ladybug.getxloc() && snakexlength[0]<=(ladybug.getxloc()+20) ||
					(snakexlength[0]<=(ladybug.getxloc())&& snakexlength[0]>=(ladybug.getxloc())))&&(
					snakeylength[0]>=(ladybug.getyloc())&& snakeylength[0]<=(ladybug.getyloc()+20) ||
					(snakeylength[0]<=(ladybug.getyloc())&& snakeylength[0]>=(ladybug.getyloc()-20)))//check
					){
				ladybug.setLoc(random.nextInt(WIDTH/20),random.nextInt(HEIGHT/20));
				score++;
				lengthofsnake++;
				snakexlength[lengthofsnake-1]=snakexlength[lengthofsnake-2];
				snakeylength[lengthofsnake-1]=snakeylength[lengthofsnake-2];
			}

			/*
			ladybug2.moveX(10);
			if (ladybug2.getxloc()>=WIDTH)
				ladybug2.setxloc(0);
			ladybug2.moveY((random.nextInt(3)-1)*3);
			if (ladybug2.getyloc()>=HEIGHT)
				ladybug2.setyloc(0);
			if (ladybug2.getyloc()<0)
				ladybug2.setyloc(HEIGHT-20);
			//if snake's head intersect the enemy,
			//the enemy will be place in another location
			//the score will increase by 5 point
			//the snake will increase in length by 1
			if(
					(snakexlength[0]>=ladybug2.getxloc() && snakexlength[0]<=(ladybug2.getxloc()+20) ||
					(snakexlength[0]<=(ladybug2.getxloc())&& snakexlength[0]>=(ladybug2.getxloc())))&&(
					snakeylength[0]>=(ladybug2.getyloc())&& snakeylength[0]<=(ladybug2.getyloc()+20) ||
					(snakeylength[0]<=(ladybug2.getyloc())&& snakeylength[0]>=(ladybug2.getyloc()-20)))//check
					){
				ladybug2.setLoc(random.nextInt(WIDTH/20),random.nextInt(HEIGHT/20));
				score++;
				lengthofsnake++;
				snakexlength[lengthofsnake-1]=snakexlength[lengthofsnake-2];
				snakeylength[lengthofsnake-1]=snakeylength[lengthofsnake-2];
			}

			
			*/

			
			
			repaint();
		}
		//else{
		//	timer.stop();
		//}
		
	}

}
