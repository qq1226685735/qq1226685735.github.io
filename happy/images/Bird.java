/**
 * ����
 * @author caozhongsheng.cn
 */
package cn.caozhongsheng.bird;

import java.awt.image.BufferedImage;

public class Bird {
	BufferedImage image;
	int width,height;
	int x,y;
	int size;//��Ĵ�С
	double g;//�������ٶ�
	double t;//ʱ����t
	double v0;//��ʼ�����ٶ�
	double speed;//��ǰ�����ٶ�
	double s;//ʱ��t���λ��
	double alpha;//���
	BufferedImage[] images = new BufferedImage[]
	{BirdGame.bird0,BirdGame.bird1,BirdGame.bird2,BirdGame.bird3,
	BirdGame.bird4,BirdGame.bird5,BirdGame.bird6,BirdGame.bird7};//��Ķ�������
	int index;//ͼƬ�任����
	/*���췽��*/
	Bird(){
		image = BirdGame.bird0;
		width=image.getWidth();
		height=image.getHeight();
		x=132;
		y=280;
		size=10;
		g=4;
		v0=20;
		t=0.25;
		speed=v0;
		s=0;alpha=0;
		index=0;//��ʼ������Ϊ0 
	}
	/*�߲�*/
	public void step(){
		double v0=speed;
		s=v0*t+g*t*t/2;//
		y=y-(int)s;//
		double v=v0-g*t;//
		speed=v;
		alpha=Math.atan(s/8);//�������
		if(y < size){//��������ϱ߽磬��С��̶�������
			y = size;
		}
	}
	/*����*/
	public void fly(){
		index++;
		image=images[(index/12)%8]; //ʵ��ͼƬ�л�
	}
	public void flappy(){
		speed = v0;//�����ٶ� �������Ϸ�
	}
	/*��ײ����(С��ײ����)*/
	public boolean hit(Ground ground){
		boolean hit = y + size/2 > ground.y;
			if(hit){
				y = ground.y -size/2;//��С�����ڵ�����
				alpha = 3.141592653 /2;//ʵ��С��ײ����ͷЧ�����Ƕ�ƫ�ƣ�
			}
			return hit;
	}
	/*��ײ������С��ײ���ӣ�*/
	public boolean hit(Column column){
		int x1 = column.x - column.width/2 - size/2;
		int x2 = column.x + column.width/2 + size/2;
		int y1 = column.y - column.gap/2 + size/2;
		int y2 = column.y + column.gap/2 - size/2;
		if(x > x1 && x < x2){//�ȼ���Ƿ������ӷ�Χ��
			if(y > y1 && y < y2){//�ټ���Ƿ��ڷ�϶��
				return false;
			}
			return true;
		}
		return false;
	}
	
}
