import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HUD extends JPanel implements MouseListener, KeyListener {

    Data data;
    JButton restartButton, selectLevelButton;


    public void mouseClicked (MouseEvent e){}
    public void mousePressed (MouseEvent e){}
    public void mouseReleased (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseExited (MouseEvent e){}
    public void keyTyped (KeyEvent e){}
    public void keyPressed (KeyEvent e){}
    public void keyReleased (KeyEvent e){}

    public HUD (Data D){
        data = D;
        D.hud = this;
        data.hud.setBackground(Color.RED);
        addMouseListener(this);

    }


}
