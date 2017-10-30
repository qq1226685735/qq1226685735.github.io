/**
 * 鸟类
 * @author caozhongsheng.cn
 */
package cn.caozhongsheng.bird;

import java.awt.image.BufferedImage;

public class Bird {
	BufferedImage image;
	int width,height;
	int x,y;
	int size;//鸟的大小
	double g;//重力加速度
	double t;//时间间隔t
	double v0;//初始上抛速度
	double speed;//当前上抛速度
	double s;//时间t后的位移
	double alpha;//倾角
	BufferedImage[] images = new BufferedImage[]
	{BirdGame.bird0,BirdGame.bird1,BirdGame.bird2,BirdGame.bird3,
	BirdGame.bird4,BirdGame.bird5,BirdGame.bird6,BirdGame.bird7};//鸟的动画数组
	int index;//图片变换计数
	/*构造方法*/
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
		index=0;//初始化计数为0 
	}
	/*走步*/
	public void step(){
		double v0=speed;
		s=v0*t+g*t*t/2;//
		y=y-(int)s;//
		double v=v0-g*t;//
		speed=v;
		alpha=Math.atan(s/8);//计算倾角
		if(y < size){//如果超过上边界，让小鸟固定不出界
			y = size;
		}
	}
	/*飞行*/
	public void fly(){
		index++;
		image=images[(index/12)%8]; //实现图片切换
	}
	public void flappy(){
		speed = v0;//重置速度 重新向上飞
	}
	/*碰撞方法(小鸟撞地面)*/
	public boolean hit(Ground ground){
		boolean hit = y + size/2 > ground.y;
			if(hit){
				y = ground.y -size/2;//将小鸟落在地面上
				alpha = 3.141592653 /2;//实现小鸟撞地栽头效果（角度偏移）
			}
			return hit;
	}
	/*碰撞方法（小鸟撞柱子）*/
	public boolean hit(Column column){
		int x1 = column.x - column.width/2 - size/2;
		int x2 = column.x + column.width/2 + size/2;
		int y1 = column.y - column.gap/2 + size/2;
		int y2 = column.y + column.gap/2 - size/2;
		if(x > x1 && x < x2){//先检测是否在柱子范围内
			if(y > y1 && y < y2){//再检测是否在缝隙内
				return false;
			}
			return true;
		}
		return false;
	}
	
}
