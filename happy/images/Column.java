/**
 * ������
 * @author caozhongsheng.cn
 */
package cn.caozhongsheng.bird;

import java.awt.image.BufferedImage;
import java.util.Random;


public class Column {
	BufferedImage image;
	int width,height;
	int x,y;
	int gap;//�����м�ķ�϶
	int distance;//��������֮��ľ���

	Random rand=new Random();
	/*���췽��*/
	Column(int n){
		image = BirdGame.column;
		width=image.getWidth();
		height=image.getHeight();
		gap=144;
		distance=245;
		x=550 + (n-1)*distance;
		y=rand.nextInt(218)+312;
	}
	/*�߲�����*/
	public void step(){
		x--;
		if(x == -width/2){
			x=distance*2-width/2;
			y=rand.nextInt(218)+312;
		}
	}
}
