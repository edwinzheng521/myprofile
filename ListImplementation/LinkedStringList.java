package cs1302.p2;

import cs1302.adt.Node;
import cs1302.adt.StringList;


/**
 * child class of {@link BaseStringList}.
 */
public class LinkedStringList extends BaseStringList {

    private Node head;

    /**
     *constructs a empty linkedlist that has its head points to null.
     */
    public LinkedStringList() {
        this.head = null;

    }

    /**
     * add method of a linkedlist.
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
        } else if (index == 0) {
            head = new Node(item,head); //if the index is 0, just set it to the head.next.
        } else {
            Node temp = head;
            while (--index > 0) {
                temp = temp.getNext();
            } //find the responding node.
            Node newNode = new Node(item);
            newNode.setNext(temp.getNext());
            temp.setNext(newNode); // set the the node to the newly added node.

        }
        size++;
        return true;
    }

    /**
     *size is zero and the head points to null.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.head = null;
        super.size = 0;
    }

    /**
     * get item from given index.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else {
            Node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            } //loop through the list and set the head.next to a node until reaches the end of loop
            return temp.getItem();
            //return the item at this node.
        }

    }

    /**
     * remove from given index.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Incorrect Index");
        } else if (get(index) == null) {
            throw new NullPointerException("the String is null");
        } else if (index == 0) {
            String s0 = head.getItem();
            size--;
            head = head.getNext();
            return s0; // get the string from the head.getNext because the index is 0.

        } else {
            Node temp = head;
            while (--index > 0) {
                temp = temp.getNext();
            } // find the node according to the index
            String s = temp.getNext().getItem();
            Node newNode = temp.getNext(); // set the pointer to the node next to the node that
            // we want to remove.
            temp.setNext(newNode.getNext());
            //set it to that node
            size--;
            return s;
        }

    }

    /**
     * use the {@link #append()} to slice the list.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public StringList slice(int start, int stop) {
        if (start < 0 || stop > size() || start > stop) {
            throw new IndexOutOfBoundsException ("Index out of bounds");
        } else if (stop < start) {
            throw new IndexOutOfBoundsException("start can't be larger than stop");
        } else {
            LinkedStringList lsl = new LinkedStringList();
            for (int i = start; i < stop; i++) {
                lsl.append(get(i));
            }
            return lsl;
        }
    }
}
