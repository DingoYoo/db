import java.awt.Graphics;

import javax.swing.ImageIcon;

// ImageIcon�� ��ǥ�� ��ġ�� �ο��ϰ��� ImageIcon Ŭ������ �����
public class PosImageIcon extends ImageIcon {
	int pX;				// ImageIcon�� X��ǥ
	int pY;				// ImageIcon�� y��ǥ
	int width;			// ImageIcon�� ����
	int height;			// ImageIcon�� ����
//	String name;
	
	public PosImageIcon(String img, int x, int y, int width, int height) {
		super(img);
//		name = img;
		pX=x;
		pY=y;
		this.width = width;
		this.height = height;
	}
	
	public void setImageIcon(String img) {
		new ImageIcon(img);
	}
	
	
	public void draw(Graphics g) {
//		System.out.println("name of this picture is " + name);
		g.drawImage(this.getImage(), pX, pY, width, height, null);
	}
}
