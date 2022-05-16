import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece {
	private char symbol;
	private Point kords;
	private MouseInput.Turn color;
    public Piece(char symbol, int x, int y) {
    	this.kords=new Point(x,y);
    	this.symbol=symbol;
    	if(symbol>'Z')
    		setColor(MouseInput.Turn.Black);
    	else 
    		setColor(MouseInput.Turn.White);
    }
	public Piece(char symbol) {

		this.symbol=symbol;
		if(symbol>'Z')
			setColor(MouseInput.Turn.Black);
		else
			setColor(MouseInput.Turn.White);
	}
	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public Point getKords() {
		return kords;
	}

	public void setKords(Point kords) {
		this.kords = kords;
	} 
	public void setKords(int x,int y) {
		this.kords = new Point(x,y);
	} 
	public static void drawPieces(Piece p,Graphics2D g2d,int fildHight) {
		String path="src/main/resources/basic/"+p.getSymbol();
		if(p.getColor()==MouseInput.Turn.White)
			path=path+"W.png";
		else
			path=path+"B.png";	
		BufferedImage image=null;
		try {
			image=ImageIO.read(new File(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
		g2d.drawImage(image,p.getKords().x,p.getKords().y,fildHight-15,fildHight-15,null,null);
	}
	public static void drawPieces(Piece p,Graphics2D g2d,int fildHight,Point pos) {
		String path="src/main/resources/basic/"+p.getSymbol();
		if(p.getColor()==MouseInput.Turn.White)
			path=path+"W.png";
		else
			path=path+"B.png";
		BufferedImage image=null;
		try {
			image=ImageIO.read(new File(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
		g2d.drawImage(image,pos.x,pos.y,fildHight-15,fildHight-15, null,null);
	}
	public MouseInput.Turn getColor() {
		return color;
	}
	public void setColor(MouseInput.Turn color) {
		this.color = color;
	}
}
