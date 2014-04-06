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
    this.drawLineMethod();
  }

  public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand() == "clear" ||
       e.getActionCommand() == "new") {
      clear = true;
      repaint();
    }

    if(e.getActionCommand() == "open") {
      this.openImage();
    }

    if(e.getActionCommand() == "save") {
      this.saveImage();
    }
  }

  //線を引く
  public void drawLineMethod() {
    bufferGraphics.setColor(Color.black);
    bufferGraphics.drawLine(pnt.x, pnt.y, pnt2.x, pnt2.y);
    pnt = pnt2;
    repaint();
  }

  public void paintComponent(Graphics g) {
    if(clear) {
      this.createBuffer(this.getWidth(), this.getHeight());
    } else {
    }
    super.paintComponent(g);
    if(bufferImage != null) {
      g.drawImage(bufferImage, 0, 0, this);
    }
  }

  private void createBuffer(int width, int height) {
    bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
    bufferGraphics = bufferImage.createGraphics();
    bufferGraphics.setBackground(Color.white);
    bufferGraphics.clearRect(0, 0, width, height);
    clear = false;
  }

  private JMenu createMenu
  ( String[] args, String menuName, ActionListener listener) {
    JMenu menu = new JMenu(menuName);
    JMenuItem[] menuItem = new JMenuItem[args.length];
    for(int i = 0; i < args.length; i++) {
      menuItem[i] = new JMenuItem(args[i]);
      menu.add(menuItem[i]);
      menuItem[i].addActionListener(listener);
    }
    return menu;
  }

  public void openImage() {
    try {String fileName = this.chooseFile("open");
      if(fileName == "error") {
        this.alert("error");
      } else if(fileName == "cancel") {
        this.alert("canceled");
      } else {
        File file = new File(fileName);
        BufferedImage openImage = ImageIO.read(file);
        
        this.createBuffer(openImage.getWidth(), openImage.getHeight());
        bufferGraphics.drawImage(openImage, 0, 0, this);
        repaint();
        
        this.alert("success");
      }
    } catch(IOException e) {
      this.alert("error");
      return;
    }
  }

  public void saveImage() {
    try {
      String fileName = this.chooseFile("save");
      if(fileName == "error") {
        this.alert("error");
      } else if(fileName == "cancel") {
        this.alert("canceled");
      } else {
        File file = new File(fileName);
        String extension = this.getExtension(fileName);
        if(extension != null) {
          ImageIO.write(bufferImage, extension, file);
          this.alert("success");
        } else {
          this.alert("error");
        }
      }
    } catch(IOException e) {
      this.alert("error");
      return;
    }
  }
  
  private static String getExtension(String str) {
    String result;
    if(str == null) {
       result = null;
    } else {
      int point = str.lastIndexOf(".");
      if(point != -1) {
        result = str.substring(point+1);
      } else {
        result = null;
      }
    }
    return result;
  }

  private String chooseFile(String str) {
    JFileChooser filechooser = new JFileChooser();
    Integer selected = null;
    
    if(str == "open") {
      selected = filechooser.showOpenDialog(this);
    } else {
      selected = filechooser.showSaveDialog(this);
    }
    if(selected == JFileChooser.APPROVE_OPTION) {
      File file = filechooser.getSelectedFile();
      return file.getName();
    } else if(selected == JFileChooser.CANCEL_OPTION) {
      return "cancel";
    } else {
      return "error";
    }
  }

  public void alert(String str) {
    JLabel label = new JLabel(str);
    JOptionPane.showMessageDialog(this, label);
  }

  public static void main(String[] args) {
    DrawingYata2 drawing = new DrawingYata2();
    drawing.setPreferredSize(new Dimension(600, 400));
    JButton jbutton = new JButton("clear");
    JMenuBar menubar = new JMenuBar();
    JMenu menu;
    String[] menuItem = new String[3];
    menuItem[0] = "new";
    menuItem[1] = "open";
    menuItem[2] = "save";
    menu = drawing.createMenu(menuItem, "file", drawing);
    menubar.add(menu);
    menubar.add(Box.createRigidArea(new Dimension(5, 1)));
 
    jbutton.addActionListener(drawing);
    Container container = jframe.getContentPane();
    container.setLayout(new BorderLayout());
    jframe.add(menubar, BorderLayout.NORTH);
    jframe.add(drawing, BorderLayout.CENTER);
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
