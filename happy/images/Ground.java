/**
 *@author caozhongsheng.cn
 *地面类
 */
package cn.caozhongsheng.bird;

import java.awt.image.BufferedImage;

public class Ground {
	BufferedImage image;
	int width;	//图片长短
	int height;	//高度
	int x;	//
	int y; //
	/*构造方法*/
	Ground(){
		image =BirdGame.groundImage;
		width=image.getWidth();
		height=image.getHeight();
		x=0;
		y=500;
	}
	/*运动方法 */
	public void step(){
		x--;
		if(x < -109){
			x = 0;
		}
	}
}
