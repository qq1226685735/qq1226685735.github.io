/**
 *@author caozhongsheng.cn
 *������
 */
package cn.caozhongsheng.bird;

import java.awt.image.BufferedImage;

public class Ground {
	BufferedImage image;
	int width;	//ͼƬ����
	int height;	//�߶�
	int x;	//
	int y; //
	/*���췽��*/
	Ground(){
		image =BirdGame.groundImage;
		width=image.getWidth();
		height=image.getHeight();
		x=0;
		y=500;
	}
	/*�˶����� */
	public void step(){
		x--;
		if(x < -109){
			x = 0;
		}
	}
}
