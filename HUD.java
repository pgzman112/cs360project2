import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HUD extends JPanel implements MouseListener, KeyListener {

    Data data;
    JButton restartButton;
    JLabel scoreLabel;
    JLabel title;

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

        title = new JLabel("Shape maker 3000");
        this.add(title);

        scoreLabel = new JLabel("Score: " + data.score );
        this.add(scoreLabel);

        addMouseListener(this);
        restartButton = new JButton("Restart Level");
        this.add(restartButton);

        restartButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //one of these conditions could be removed, but this will make certain no out of bounds issues happen
                for(int i = 0; i < data.startPoints.size() && i < data.blocks.size(); i++){
                    //move block and set back in play
                    data.blocks.get(i).moveTo(data.startPoints.get(i).x,data.startPoints.get(i).y);
                    data.blocks.get(i).onBoard = true;
                    data.blocks.get(i).placed  = false;

                    double offset = -1 * data.blocks.get(i).shape.getAngleR();
                    data.blocks.get(i).rotate(offset);

                    //reset holes to accept shapes again
                    data.holes.get(i).filledWith = null;
                    data.holes.get(i).filled     = false;
                    
                }
                data.blocksToPlace = 6 ;//monitors level completion
                data.score = data.scoreAtLevelStart;
                scoreLabel.setText("Score: " + data.score);
                data.game.repaint();
            }
        }

        );


    }










}
