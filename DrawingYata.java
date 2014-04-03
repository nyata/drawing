/*--------------------------
 *
 * 2014/03/26
 * test oekaki program
 * yata
 *
 * ------------------------*/


import java.awt.*;// java gui
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;// java swing <- gui

public class DrawingYata extends JPanel implements MouseMotionListener,
       							ActionListener {
// JFlame > JPanel <- swing
// class クラス名 extends スーパークラス名 implements インターフェース名
  volatile Point pnt = new Point(0, 0);// volatile toha...
  volatile private Boolean clear = true;
  //public static JFrame jframe = new JFrame("hoge");
  BufferedImage bufferImage = null;
  Graphics2D bufferGraphics = null;

  // constructor
  public DrawingYata () {
    addMouseMotionListener(this);
  }

  // buffer syori
  public void createBuffer (int width, int height) {
    bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
    bufferGraphics = bufferImage.createGraphics();
    bufferGraphics.setBackground(Color.white);
    bufferGraphics.clearRect(0, 0, width, height);
  }

  public void drawLine (int x1, int y1, int x2, int y2) {
    if(bufferGraphics == null) {
      this.createBuffer(this.getWidth(), this.getHeight());
    }
    bufferGraphics.drawLine(x1, y1, x2, y2);
    repaint();
  }

  // 画面処理
  public void paintComponent (Graphics g) {
    /*if(clear) {
      g.clearRect(0, 0, 600, 400);// 背景を指定の色でクリア
      clear = false;
    } else {
      g.fillRect(pnt.x, pnt.y, 2, 2);// 2*2の四角形（塗りつぶしたやつ）
    }*/
    super.paintComponent(g);
    if(bufferImage != null) {
      g.drawImage(bufferImage, 0, 0, this);
    }
  }

  // マウスの位置取るやつ
  public void mouseDragged (MouseEvent e) {
    pnt = e.getPoint();
    repaint();// 再描画->画面処理で線かく
  }

  // nazo
  public void mouseMoved(MouseEvent e) {
  }

  // ボタン（clear)が押された時
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() == "clear" ||
        e.getActionCommand() == "new") {
      clear = true;
      repaint();// 再描画->画面処理でクリアする
    }
    if(e.getActionCommand() == "open") {
    }
    if(e.getActionCommand() == "save") {
      saveImage();
    }
  }

  // save image
  public static void saveImage() {
    /*try {
      ImageIO.write(drawing, "png", new File("out.png"));
    } catch(IOException e) {
    }*/
  }

  public static void init() {
    
  }

  //
  public static void main(String[] args) {
    final JFrame jframe = new JFrame("mouseEventTest");//jframeとタイトル
    
    JMenuBar menubar = new JMenuBar();
    JMenu menu1 = new JMenu("File");
    JMenuItem menuitem1 = new JMenuItem("new");
    JMenuItem menuitem2 = new JMenuItem("open");
    JMenuItem menuitem3 = new JMenuItem("save");
    
    DrawingYata drawing = new DrawingYata();// クラス呼び出し
    drawing.setPreferredSize(new Dimension(600, 400));//画面の高さと幅
    // Dimension(int width, int height)
    JButton jbutton = new JButton("clear");
    
    menu1.add(menuitem1);
    menu1.add(menuitem2);
    menu1.add(menuitem3);
    menuitem1.addActionListener(drawing);
    menuitem2.addActionListener(drawing);
    menuitem3.addActionListener(drawing);

    menubar.add(menu1);
    menubar.add(Box.createRigidArea(new Dimension(5, 1)));
    jbutton.addActionListener(drawing);

    Container container = jframe.getContentPane();//画面に表示する奴のまとめるやつ？
    container.setLayout(new BorderLayout());//画面を５つのブロックに分けて部品を配置
    
    jframe.add(drawing, BorderLayout.CENTER);//各場所を北の位置に
    jframe.add(jbutton, BorderLayout.SOUTH);//クリアボタンを南の位置に
    jframe.pack();//内側の領域からサイズを決める
    jframe.setResizable(false);//画面の大きさは変更できなくする
    jframe.setBackground(Color.white);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンはデフォルトのやつ
    jframe.add(menubar,BorderLayout.NORTH);

    SwingUtilities.invokeLater(new Runnable() {//時間のかかる処理を任せるらしい
      public void run() {//ThreadTestTreadクラスのrunメソッド
          jframe.setVisible(true);//フレームを表示
      }
    });
  }
}
