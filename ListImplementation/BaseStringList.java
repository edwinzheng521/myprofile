package cs1302.p2;

import cs1302.adt.StringList;

/**
 * An abstract StringList class that implements from StringList.
 */
public abstract class BaseStringList implements StringList {

    protected int size;

    /**
     * constructs a {@code BaseStringList} object.
     */
    public BaseStringList() {
        this.size = size();
    }

    /**
     * append method that use the {@link #add()} method.
     *
     * <p>
     * {@inheritDoc}
     */
    public boolean append(String item) {
        add(size(),item);
        return true;
    }

    /**
     * use the {@code size} to check if the list is empty or not.
     *
     * <p>
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }



    /**
     * makes the toString method looks nicer.
     *
     * <p>
     * {@inheritDoc}
     */
    public String makeString(String start, String sep, String end) {
        String temp = "";
        if (size() > 0) {
            for (int i = 0; i < size() - 1; i++) {
                temp += this.get(i) + sep;
            } // each passed temps are added with the sep to it.
            temp += get(size() - 1);
        }
        return start + temp + end; //return with the start and end.
    }


    /**
     * prepend method that use the {@link #add()} method.
     *
     * <p>
     * {@inheritDoc}
     */
    public boolean prepend(String item) {
        add(0,item);
        return true;
    }

    /**
     * return the size.
     *
     * <p>
     * {@inheritDoc}
     */
    public int size() {
        return size;
    }

    /**
     * toString method.
     *
     * <p>
     * {@inheritDoc}
     */
    public String toString() {
        return makeString ("[", ", ", "]");
    }

}
