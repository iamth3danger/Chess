import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReactPanel extends JPanel implements MouseMotionListener {

    public ReactPanel(){
        setPreferredSize(new Dimension(450, 450));

        addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged (" + e.getX() + ',' + e.getY() + ')');
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse moved (" + e.getX() + ',' + e.getY() + ')');

    }

    public static void main(String[] args){
        //Create and set up the window.
        JFrame frame = new JFrame("MouseMotionEventDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ReactPanel();
        newContentPane.setOpaque(true); //content panes must be opaque
       
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}