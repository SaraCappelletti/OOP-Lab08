package it.unibo.oop.lab.iogui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is a simple application that writes a random number on a file.
 * 
 * This application does not exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, not as a template for your
 * applications.
 */
public class BadIOGUI {

    private static final String TITLE = "A very simple GUI application";
    private static final String PATH = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + BadIOGUI.class.getSimpleName() + ".txt";
    private static final int PROPORTION = 5;
    private final Random rng = new Random();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * 
     */
    public BadIOGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JButton write = new JButton("Write on file");
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
         * 4.Add the "write" JButton to the new panel
         */
        p1.add(write);
        /* 5.Test your application: it should appear similar, but the button will get smaller
         * 6.In display(), use JFrame.pack() to resize the frame to the minimum size prior to displaying
        */
        /*
         * Ex 01.02
         * 1.Create a new "Read" button
         */
        final JButton b1 = new JButton("Read");
        /*
         * 2.Add it to the JPanel created in Ex 01.01
         */
        p1.add(b1);
        /*
         * 3.Test your application. Verify that you can see a new button, but it is useless
         * 4.Write a new ActionListener for the new button, in form of anonymous class,
         * that prints a string on terminal (use System.out) when the button is pressed.
         * 5.Test it
         */
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                System.out.println("Pressed Button");
            }
        });

        /*
         * Ex 01.03
         * 1.Modify the ActionListener in such a way that it reads the content of the same file
         * that is written when the button "write" is pressed, and prints its content
         * on standard output
         */
        /*
         * Handlers
         */
        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                /*
                 * This would be VERY BAD in a real application.
                 * 
                 * This makes the Event Dispatch Thread (EDT) work on an I/O
                 * operation. I/O operations may take a long time, during which
                 * your UI becomes completely unresponsive.
                 */
                try (PrintStream ps = new PrintStream(PATH)) {
                    ps.print(rng.nextInt());
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
                try {
                    final List<String> o = Files.readAllLines(Path.of(PATH));
                    System.out.println(o);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
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
       new BadIOGUI().display();
    }
}
