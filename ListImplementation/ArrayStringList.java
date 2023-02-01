package cs1302.p2;

import cs1302.adt.StringList;

/**
 *child class of {@link BaseStringList}.
 */
public class ArrayStringList extends BaseStringList {

    private String[] items;

/**
 *constructs a class that initialize the array to a size of 3.
 */
    public ArrayStringList() {
        this.items = new String[3];

    }

    /**
     * add method that mirror the add method of java Arraylist.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, String item) {
        if (item == null) {
            throw new NullPointerException("Please input valid String");
        } else if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Please input valid index");
        } else if (item.equals("")) {
            throw new IllegalArgumentException("Please input valid String");
        } else {
            String[] temp = new String[size() + 1];
            //create a temp array that always has more size than the index.
            int count = 0;
            for (int i = 0; i < index; i++) {
                temp[i] = items[i];
                count++;
            } // setting the temp equals to the items array.
            temp[count] = item;

            for (int i = index + 1; i < temp.length; i++) {
                temp[i] = items[i - 1];
            }

            items = temp;
        }

        size++;
        return true;

    }

    /**
     * set the size of the list to zero.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.items = new String[3];
        super.size = 0;
    }

    /**
     * get the item in the specific index.
     *
     * <p>
     * {@inheritDoc}
     */
    public String get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Incorrect Index");
        } else {
            return items[index];
        }

    }

    /**
     * removes the item at the given index.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Incorrect Index");
        }
        String removed = get(index);
        for (int i = index; i < items.length - 1; i++) {
            items[i] = items[i + 1];
        } // for loop that starts with the index and loop through and add the item to the end.
        // size-- will remove it.
        size--;
        return  removed;
    }


/**
 * uses the {@link #append()} method.
 *
 * <p>
 * {@inheritDoc}
 */
    @Override
    public StringList slice(int start, int stop) {
        if (start < 0 || stop > size() || start > stop) {
            throw new IndexOutOfBoundsException ("Index out of bounds");
        }
        StringList slice = new ArrayStringList();
        for (int i = start; i < stop; i++) {
            slice.append(items[i]);
        }
        return slice;
    }

}
