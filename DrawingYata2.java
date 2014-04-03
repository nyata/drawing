/*
 * 2014/04/03
 * yatect java
 * oekaki program2
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
//import javaa.io.*;
//import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawingYata2 extends JPanel implements MouseListener,
                                                    MouseMotionListener,
                                                    ActionListener {
  volatile Point pnt = new Point(0, 0);
  volatile Point pnt2 = new Point(0, 0);
  volatile private Boolean clear = true;
  //BufferedImage bufferImage = null;
  //Graphics2D bufferGraphics = null;
  //int x1, x2, y1, y2;

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
    //e.consume();
    pnt = e.getPoint();
    System.out.println("hoge");
    //pnt2 = e.getPoint();
    //repaint();
  }

  public void mouseDragged(MouseEvent e) {
    //e.consume();
    pnt2 = e.getPoint();
    System.out.println("hoge2");
    repaint();
    //pnt = pnt2;
  }

  public void actionPerformed(ActionEvent e) {
    clear = true;
    repaint();
  }

  public void paintComponent(Graphics g) {
    if(clear) {
      //画面を消す処理
      g.clearRect(0, 0, 600, 400);
      clear = false;
    } else {
      //絵を書く処理
      g.drawLine(pnt.x, pnt.y, pnt2.x, pnt2.y);
      pnt = pnt2;
      //System.out.println("hoge");
      //g.fillRect(pnt2.x, pnt2.y, 2, 2);
    }
  }

  public static void main(String[] args) {
    final JFrame jframe = new JFrame("mouseEventTest");
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
