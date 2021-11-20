/**
 * 
 */
package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class SimpleController implements Controller {

    private String nextString;
    private final List<String> stringHistory = new LinkedList<>();

    @Override
    public void setNextStringToPrint(final String s) {
        this.nextString = Objects.requireNonNull(s, "Null values are not acceptable");
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public List<String> getPrintedStrings() {
        return this.stringHistory;
    }

    /**
     *
     */
    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("There is no current String");
        }
        this.stringHistory.add(this.nextString);
        System.out.println(this.nextString);
    }

}
