/**
 * 柱子类
 * @author caozhongsheng.cn
 */
package cn.caozhongsheng.bird;

import java.awt.image.BufferedImage;
import java.util.Random;


public class Column {
	BufferedImage image;
	int width,height;
	int x,y;
	int gap;//柱子中间的缝隙
	int distance;//两根柱子之间的距离

	Random rand=new Random();
	/*构造方法*/
	Column(int n){
		image = BirdGame.column;
		width=image.getWidth();
		height=image.getHeight();
		gap=144;
		distance=245;
		x=550 + (n-1)*distance;
		y=rand.nextInt(218)+312;
	}
	/*走步方法*/
	public void step(){
		x--;
		if(x == -width/2){
			x=distance*2-width/2;
			y=rand.nextInt(218)+312;
		}
	}
}
