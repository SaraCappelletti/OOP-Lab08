package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     */
    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE = "output.txt";
    /*Setting default file*/
    private File f = new File(HOME + SEPARATOR + DEFAULT_FILE);
    /**
     * @param newf
     *              the new current file
     */
    public void setCurrentFile(final File newf) {
        f = newf;
    }
    /* 
     * 2) A method for getting the current File
     */ 
    /**
     * @return the current file
     */
    public File getCurrentFile() {
        return f;
    }
    /* 
     * 3) A method for getting the path (in form of String) of the current File
     */ 
    /**
     * @return the current file path
     */
    public String getCurrentFilePath() {
        return f.getPath();
    }
    /* 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     */
    /**
     * @param s
     *            the text to write
     * @throws IOException
     *             if the writing fails
     */
    public void write(final String s) throws IOException {
        try (PrintStream out = new PrintStream(f)) {
            out.println(s);
        } //catch (IOException e1) {
    }
    /* 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

    /** The solutions for point 1
    public void setDestination(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            dest = file;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }
    public void setDestination(final String file) {
        setDestination(new File(file));
    }
    */
}
