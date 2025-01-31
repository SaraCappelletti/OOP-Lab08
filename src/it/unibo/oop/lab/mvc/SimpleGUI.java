package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */

public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final Controller controller;

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) It has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     * @param controller
     *                  new Controller for my SimpleGUI
     */
    public SimpleGUI(final Controller controller) {
        this.controller = controller;
        /*Point 3*/
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        panel1.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        panel1.add(textArea, BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel1.add(panel2, BorderLayout.SOUTH);
        final JButton printButton = new JButton("Print");
        panel2.add(printButton, BorderLayout.WEST);
        final JButton showButton = new JButton("Show History");
        panel2.add(showButton, BorderLayout.EAST);
        frame.setContentPane(panel1);
        /*Point 4*/
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*Point 5*/
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                SimpleGUI.this.controller.setNextStringToPrint(textField.getText());
                SimpleGUI.this.controller.printCurrentString();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder text = new StringBuilder();
                final List<String> printedStrings = SimpleGUI.this.controller.getPrintedStrings();
                for (final String s: printedStrings) {
                    text.append(s);
                    text.append('\n');
                }
                /*Nelle soluzioni aggiunge questo
                if (!printedStrings.isEmpty()) {
                    text.deleteCharAt(text.length() - 1);
                }*/
                textArea.setText(text.toString());
            }
        });
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
    }

    public void display() {
        frame.setVisible(true);
    }
    /*Point 1*/
    public static void main(final String... agrs) {
        new SimpleGUI(new SimpleController()).display();
    }

}
