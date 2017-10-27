package cn.caozhongsheng.bird;
/**
 * 程序运行类
 * @author caozhongsheng.cn
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BirdGame extends JPanel {
	Bird bird;
	Column column1,column2;
	Ground ground;
	public static final int WIDTH =440;
	public static final int HEIGHT =670;
	public static BufferedImage background;
	public static BufferedImage gameOver;
	public static BufferedImage start;
	public static BufferedImage column;
	public static BufferedImage groundImage;
	
	/*静态变量birdi用于存储8个不同鸟的图片，实现鸟的动画效果*/
	public static BufferedImage bird0;
	public static BufferedImage bird1;
	public static BufferedImage bird2;
	public static BufferedImage bird3;
	public static BufferedImage bird4;
	public static BufferedImage bird5;
	public static BufferedImage bird6;
	public static BufferedImage bird7;
	public int score = 0;//分数
//	public boolean GameOver = false;//该标记用于检测游戏是否结束
	
	/*游戏状态参数*/
	int state;
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int GAME_OVER = 2;
	
	static{//静态块加载资源
		try{
		background = ImageIO.read(BirdGame.class.getResource("bg.png"));
		gameOver = ImageIO.read(BirdGame.class.getResource("gameover.png"));
		start = ImageIO.read(BirdGame.class.getResource("start.png"));
		column =ImageIO.read(BirdGame.class.getResource("column.png"));
		groundImage = ImageIO.read(BirdGame.class.getResource("ground.png"));
		bird0 = ImageIO.read(BirdGame.class.getResource("0.png"));
		bird1 = ImageIO.read(BirdGame.class.getResource("1.png"));
		bird2 = ImageIO.read(BirdGame.class.getResource("2.png"));
		bird3 = ImageIO.read(BirdGame.class.getResource("3.png"));
		bird4 = ImageIO.read(BirdGame.class.getResource("4.png"));
		bird5 = ImageIO.read(BirdGame.class.getResource("5.png"));
		bird6 = ImageIO.read(BirdGame.class.getResource("6.png"));
		bird7 = ImageIO.read(BirdGame.class.getResource("7.png"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*构造方法 初始化各个变量*/
	BirdGame(){
		bird = new Bird();
		column1 = new Column(1);
		column2 = new Column(2);
		ground = new Ground();
		state = START;
	}
	
	/*主函数*/
	public static void main(String[] args) throws IOException, InterruptedException{
		JFrame frame = new JFrame("FloopyBird");
		BirdGame game = new BirdGame(); // 面板对象
		frame.add(game); // 将面板添加到JFrame中
		frame.setSize(WIDTH, HEIGHT); // 设置大小
		frame.setAlwaysOnTop(true); // 设置其总在最上
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作
		frame.setLocationRelativeTo(null); // 设置窗体初始位置
		frame.setVisible(true); // 尽快调用paint方法
		game.action();	//调用动作方法
	}
	
	/*重写paint方法*/
	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		g.drawImage(column1.image,column1.x-column1.width/2,column1.y-column1.height/2,null);
		g.drawImage(column2.image,column2.x-column2.width/2,column2.y-column2.height/2,null);
		g.drawImage(ground.image,ground.x,ground.y,null);
		g.drawImage(bird.image,bird.x-bird.width/2,bird.y-bird.height/2,null);
		/*以下代码用于画鸟的倾斜（2D效果）*/
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		g.drawImage(bird.image,bird.x-bird.width/2,bird.y-bird.height/2,null);
		g2.rotate(bird.alpha,bird.x,bird.y);
		
		paintScore(g);//画分数
//		if(GameOver == true){//判断游戏是否结束
//			g.drawImage(gameOver,0,0,null);
//		}
		//控制游戏 开始与结束
		switch(state){
		case GAME_OVER:g.drawImage(gameOver,0,0,null);
			break;
		case START : g.drawImage(start,0,0,null);
			break;
		}
		
	}
	
	/*动作方法*/
	public void action() throws InterruptedException{
		MouseListener l=new MouseAdapter(){//鼠标按下
			public void mousePressed(MouseEvent e){
				//小鸟上飞
//				bird.flappy();
				try{
				switch(state){
				case GAME_OVER:
					bird = new Bird();
					column1 = new Column(1);
					column2 = new Column(2);
					state = START;
					score = 0;
					break;
				case START:
					state=RUNNING;
				case RUNNING:
					//小鸟上飞
					bird.flappy();
				}
			}catch(Exception ex){
				ex.printStackTrace();
				}
			}
		};
		addMouseListener(l);//将l挂在面板上
		while(true){
//			if(!GameOver){
//			ground.step();
//			column1.step();
//			column2.step();
//			bird.step();
//			bird.fly();
//			}
//			birdHit();//检测是否碰撞
//			Score();//计分方法
			switch(state){
			case START:
				bird.fly();
				ground.step();
				break;
			case RUNNING:
				column1.step();
				column2.step();
				bird.step();
				bird.fly();
				ground.step();
				Score();//计分方法
				birdHit();//检测是否碰撞
				break;
			}
			repaint();
			Thread.sleep(1000/30);//实现一秒30次 throws InterruptedException解决异常
		}
	}
	
	/*画分数*/
	public void paintScore(Graphics g){
		int x=40,y=40;
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 25);
		g.setColor(new Color(0xFFFFFF));
		g.setFont(f); // 设置字体
		g.drawString("" + score, x, y); // 画分数
	}
	
	/*计分方法*/
	public void Score(){
		if(bird.x==column1.x || bird.x==column2.x){
			score++;
		}
	}
	
	/*检测是否碰撞*/
	public void birdHit(){
		if(bird.hit(column1) || bird.hit(column2) || bird.hit(ground)){
			state = GAME_OVER;
		}
	}
}
