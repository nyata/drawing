/* * * * * *
 * 2014/03/10
 * BufferedImage
 * 画像表示練習用
 * yata
 * * * * * */

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Drawing4 extends JFrame {
  public static void main(String[] args) {
    Drawing4 drawing = new Drawing4();

    drawing.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){System.exit(0);}
    });
    drawing.setBounds(0, 0, 200, 200);
    drawing.setVisible(true);
  }
  public void paint(Graphics g){
    Graphics2D g2 = (Graphics2D)g;
    BufferedImage readImage = null;

    try {
	readImage = ImageIO.read(new File("sand.jpg"));
    } catch (Exception e) {
    	e.printStackTrace();
	readImage = null;
    }

    if (readImage != null) {
	int w = readImage.getWidth();
	int h = readImage.getHeight();
	BufferedImage write = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	for(int y=0;y<h;y++) {
	  for(int x=0;x<w;x++) {
	    Color c = new Color(readImage.getRGB(x, y));
	    int r1 = c.getRed();
	    int g1 = c.getGreen();
	    int b1 = c.getBlue();
	    int r = 255-r1;
	    int gr = 255-g1;
	    int b = 255-b1;
	    Color rgb = new Color(r, gr, b);
	    write.setRGB(x, y, rgb);
	  }
	}
    	g2.drawImage(write, 0, 0, this);
    }
  }
}
