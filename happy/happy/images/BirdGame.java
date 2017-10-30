package cn.caozhongsheng.bird;
/**
 * ����������
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
	
	/*��̬����birdi���ڴ洢8����ͬ���ͼƬ��ʵ����Ķ���Ч��*/
	public static BufferedImage bird0;
	public static BufferedImage bird1;
	public static BufferedImage bird2;
	public static BufferedImage bird3;
	public static BufferedImage bird4;
	public static BufferedImage bird5;
	public static BufferedImage bird6;
	public static BufferedImage bird7;
	public int score = 0;//����
//	public boolean GameOver = false;//�ñ�����ڼ����Ϸ�Ƿ����
	
	/*��Ϸ״̬����*/
	int state;
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int GAME_OVER = 2;
	
	static{//��̬�������Դ
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
	
	/*���췽�� ��ʼ����������*/
	BirdGame(){
		bird = new Bird();
		column1 = new Column(1);
		column2 = new Column(2);
		ground = new Ground();
		state = START;
	}
	
	/*������*/
	public static void main(String[] args) throws IOException, InterruptedException{
		JFrame frame = new JFrame("FloopyBird");
		BirdGame game = new BirdGame(); // ������
		frame.add(game); // �������ӵ�JFrame��
		frame.setSize(WIDTH, HEIGHT); // ���ô�С
		frame.setAlwaysOnTop(true); // ��������������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ĭ�Ϲرղ���
		frame.setLocationRelativeTo(null); // ���ô����ʼλ��
		frame.setVisible(true); // �������paint����
		game.action();	//���ö�������
	}
	
	/*��дpaint����*/
	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		g.drawImage(column1.image,column1.x-column1.width/2,column1.y-column1.height/2,null);
		g.drawImage(column2.image,column2.x-column2.width/2,column2.y-column2.height/2,null);
		g.drawImage(ground.image,ground.x,ground.y,null);
		g.drawImage(bird.image,bird.x-bird.width/2,bird.y-bird.height/2,null);
		/*���´������ڻ������б��2DЧ����*/
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		g.drawImage(bird.image,bird.x-bird.width/2,bird.y-bird.height/2,null);
		g2.rotate(bird.alpha,bird.x,bird.y);
		
		paintScore(g);//������
//		if(GameOver == true){//�ж���Ϸ�Ƿ����
//			g.drawImage(gameOver,0,0,null);
//		}
		//������Ϸ ��ʼ�����
		switch(state){
		case GAME_OVER:g.drawImage(gameOver,0,0,null);
			break;
		case START : g.drawImage(start,0,0,null);
			break;
		}
		
	}
	
	/*��������*/
	public void action() throws InterruptedException{
		MouseListener l=new MouseAdapter(){//��갴��
			public void mousePressed(MouseEvent e){
				//С���Ϸ�
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
					//С���Ϸ�
					bird.flappy();
				}
			}catch(Exception ex){
				ex.printStackTrace();
				}
			}
		};
		addMouseListener(l);//��l���������
		while(true){
//			if(!GameOver){
//			ground.step();
//			column1.step();
//			column2.step();
//			bird.step();
//			bird.fly();
//			}
//			birdHit();//����Ƿ���ײ
//			Score();//�Ʒַ���
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
				Score();//�Ʒַ���
				birdHit();//����Ƿ���ײ
				break;
			}
			repaint();
			Thread.sleep(1000/30);//ʵ��һ��30�� throws InterruptedException����쳣
		}
	}
	
	/*������*/
	public void paintScore(Graphics g){
		int x=40,y=40;
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 25);
		g.setColor(new Color(0xFFFFFF));
		g.setFont(f); // ��������
		g.drawString("" + score, x, y); // ������
	}
	
	/*�Ʒַ���*/
	public void Score(){
		if(bird.x==column1.x || bird.x==column2.x){
			score++;
		}
	}
	
	/*����Ƿ���ײ*/
	public void birdHit(){
		if(bird.hit(column1) || bird.hit(column2) || bird.hit(ground)){
			state = GAME_OVER;
		}
	}
}
