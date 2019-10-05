
// coffee 와 stone을 vertical move expression

public class Direction_CS extends PosImageIcon {

	int moveX = 10;		// x축으로의 방향과 보폭
	int moveY = 10;		// y축으로의 방향과 보폭

	public Direction_CS(String img, int x, int y, int width, int height) {
		super(img,x,y,width,height);
		
	}
	
	public Direction_CS(String img, int x, int y, int width, int height, int step) {
		super(img, x, y, width, height);  // step 만들기 위해서 추가로 생성자 만듦.
		moveY=step; // 주어진 스텝에 따라서 move x,y가 결정이 됨.
		
	}
	
	public void move(int y) {
		pY+=y;
		
	}
	

}
