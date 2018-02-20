import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy {
	private Random random;
	private int[] enemyxloc, enemyyloc;
	public int xloc, yloc;
	public ImageIcon enemy;
	Enemy(){
		//xloc = random.nextInt(700/20);
		//yloc = random.nextInt((460/20));
		enemyxloc = new int[700/20];
		enemyyloc = new int[460/20];
		enemy = new ImageIcon("enemy.png");
		for(int i=0; i<enemyxloc.length; i++){
			enemyxloc[i] = i*20;
		}
		for(int i=0; i<enemyyloc.length; i++){
			enemyyloc[i] = i*20;
		}
		
	}
	public int getxloc(){
		return enemyxloc[xloc];
	}
	public int getyloc(){
		return enemyyloc[yloc];
	}
	public void setxloc(int x){
		enemyxloc[xloc] = x;
	}
	public void setyloc(int y){
		enemyyloc[yloc] = y;
	}
	public void moveX(int i){
		enemyxloc[xloc] += i;
	}
	public void moveY(int i){
		enemyyloc[yloc] += i;
		
	}
	public void setLoc(int x, int y){
		xloc = x;
		yloc = y;
	}
}
