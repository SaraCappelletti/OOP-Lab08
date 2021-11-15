/**
 * 
 */
package it.unibo.oop.lab.simplegui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class is a simple application that writes a random number on a file.
 * 
 * This application does not exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, not as a template for your
 * applications.
 */
public class MiniGUI {

    private static final String TITLE = "A very simple GUI application";
    private static final int PROPORTION = 5;
    private final Random rng = new Random();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * 
     */
    public MiniGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JButton write = new JButton("Print a random number on standard output");
        canvas.add(write, BorderLayout.CENTER);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /*
     * Ex 01.01
     * 1.Create a new JPanel
     */
        final JPanel p1 = new JPanel();
    /*
     * 2.Use an horizontal BoxLayout as layout
     */ 
        p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
    /*
     * 3.Set the new JPanel as the only content of the center of the current BorderLayout
     */
        canvas.add(p1, BorderLayout.CENTER);
    /*
     * 4.Add the JButton to the new panel
     */
        p1.add(write);
    /*
     * 5.Test your application: it should appear similar, but the button will get smaller
     * 6.In display(), use JFrame.pack() to resize the frame to the minimum size prior to displaying
     */
    /*
     * Ex 01.02
     * 1.Create a new "Result" text field
     */
        final JTextField t1 = new JTextField("Result");
    /*
     * 2.Add it to the external JPanel in such a way that it gets on the top of the frame (Borderlayout.NORTH)
     * 3.Test your application. Verify that you can see a new Text field, but it is useless
     */
        canvas.add(t1, BorderLayout.NORTH);
    /*
     * Ex 01.03
     * 1.Modify the application in such a way that the text field displays the same number that
     * gets printed
     * 2.Test it
     */
        /*
         * Handlers
         */
        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final int num = rng.nextInt();
                System.out.println(num);
                t1.setText(Integer.toString(num));
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * 6.In display(), use JFrame.pack() to resize the frame to the minimum size prior to displaying
         */
        frame.pack();
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to pull the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * @param args ignored
     */
    public static void main(final String... args) {
       new MiniGUI().display();
    }

}
