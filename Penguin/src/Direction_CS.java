
// coffee �� stone�� vertical move expression

public class Direction_CS extends PosImageIcon {

//	int moveX = 10;		// x�������� ����� ����
//	int moveY = 10;		// y�������� ����� ����
	private String name;

	public Direction_CS(String img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		name= img;
	}
	
//	public Direction_CS(String img, int x, int y, int width, int height, int step) {
//		super(img, x, y, width, height);  // step ����� ���ؼ� �߰��� ������ ����.
//		moveY=step; // �־��� ���ܿ� ���� move x,y�� ������ ��.
//		name = img;
//	}
	
//	public void move(int y) {
//		pY+=y;
//	}
	
	public String getName() {
		return name;
	}
	

}
