/*
 * 2014/04/03
 * yatect java
 * oekaki program2
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawingYata2 extends JPanel implements MouseListener,
                                                    MouseMotionListener,
                                                    ActionListener {
  volatile Point pnt = new Point(0, 0);
  volatile Point pnt2 = new Point(0, 0);
  volatile private Boolean clear = true;
  BufferedImage bufferImage = null;
  Graphics2D bufferGraphics = null;
  static JFrame jframe = new JFrame("mouseEventTest");

  public DrawingYata2() {
    addMouseMotionListener(this);
    addMouseListener(this);
  }

  public void mouseMoved(MouseEvent e) {
  }

  public void mouseClicked(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
    pnt = e.getPoint();
  }

  public void mouseDragged(MouseEvent e) {
    pnt2 = e.getPoint();
    //repaint();
    this.drawLineMethod();
  }

  public void actionPerformed(ActionEvent e) {
    clear = true;
    repaint();
  }

  //線を引く
  public void drawLineMethod() {
    bufferGraphics.drawLine(pnt.x, pnt.y, pnt2.x, pnt2.y);
    pnt = pnt2;
    //System.out.println("draw");
    repaint();
  }

  public void paintComponent(Graphics g) {
    //g.setColor(Color.black);
    if(clear) {
      this.createBuffer(this.getWidth(), this.getHeight());
    } else {
      //this.drawLineMethod();
    }
    super.paintComponent(g);
    if(bufferImage != null) {
      //System.out.println("bufferimage");
      g.drawImage(bufferImage, 0, 0, this);
    }
  }

  private void createBuffer(int width, int height) {
    //System.out.println("create");
    bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
    bufferGraphics = bufferImage.createGraphics();
    bufferGraphics.setBackground(Color.white);
    bufferGraphics.clearRect(0, 0, width, height);
    clear = false;
  }

  public static void main(String[] args) {
    DrawingYata2 drawing = new DrawingYata2();
    drawing.setPreferredSize(new Dimension(600, 400));
    JButton jbutton = new JButton("clear");
    jbutton.addActionListener(drawing);
    Container container = jframe.getContentPane();
    container.setLayout(new BorderLayout());
    jframe.add(drawing, BorderLayout.NORTH);
    jframe.add(jbutton, BorderLayout.SOUTH);
    jframe.pack();
    jframe.setResizable(false);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        jframe.setVisible(true);
      }
    });
  }
}
