import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Drawing2 extends JPanel implements MouseMotionListener, 
                                                    ActionListener{
  volatile Point pnt = new Point(0,0);
  volatile private Boolean clear=true;
  public Drawing2(){
    addMouseMotionListener(this);
  }
  public void paintComponent(Graphics g){
    if(clear){
      g.clearRect(0,0,600,400);
      clear=false;
    }else{
      g.fillRect(pnt.x,pnt.y,2,2);
    }
  }
  public void mouseDragged(MouseEvent e){
    pnt = e.getPoint(); 
    repaint();
  }
  public void mouseMoved(MouseEvent e){
  }
  public void actionPerformed(ActionEvent e){
    clear = true;
    repaint();
  }
  public static void main(String[] args){
    final JFrame jframe = new JFrame("mouseEventTest");
    Drawing2 drawing = new Drawing2();
    drawing.setPreferredSize(new Dimension(600,400));
    JButton jbutton = new JButton("clear");
    jbutton.addActionListener(drawing);
    Container container = jframe.getContentPane();
    container.setLayout(new BorderLayout());
    jframe.add(drawing,BorderLayout.NORTH);
    jframe.add(jbutton,BorderLayout.SOUTH);
    jframe.pack();
    jframe.setResizable(false);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SwingUtilities.invokeLater(new Runnable(){
        public void run(){
          jframe.setVisible (true);
        }});
  }
}