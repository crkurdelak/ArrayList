/**
 * // TODO write class description
 *
 * @param <E> the type of elements in this list
 *
 * @author ckurdelak20@georgefox.edu
 */
public class ArrayList<E> {
    // TODO implement class

    private Object[] _values;
    // a bookkeeping variable that keeps track of the highest filled index in the backing array
    private int _index;


    /**
     * Constructs a new ArrayList object with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of this ArrayList's backing array
     */
    public ArrayList(int initialCapacity) {
        _values = new Object[initialCapacity];
        _index = 0;
    }


    /**
     * Constructs a new ArrayList object with a default initial capacity of 10.
     */
    public ArrayList() {
        this(10);
    } // TODO make 10 a constant


    /**
     * Returns the number of elements in this ArrayList.
     *
     * @return the number of elements in this ArrayList
     */
    public int size() {
        return _index;
    }


    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     * false if this list contains elements
     */
    public boolean isEmpty() {
        // TODO implement isEmpty
        boolean result;
        if (_index == 0) {
            result = true;
        }
        else if (_index > 0) {
            result = false;
        }
        else {
            // TODO see if you need to throw an exception here
        }

        return result;
    }


    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index > 0 && index < _index) {
            return (E)_values[index];
            // ONLY cast right before return
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }


    /**
     * TODO write javadoc comments
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        // validate index
        // TODO shift values to right
        // _values[index] = value;
    }


    /**
     * TODO write javadoc
     * @param element
     * @return true if this collection has changed as a result of the call
     */
    public boolean add(E element) {
        add(_index, element);
        return true;
    }


    /**
     * Removes and returns the element from the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices)
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    public E remove(int index) {
        // TODO implement remove
        // validate index
        // should we use set method?
    }


    /**
     * Removes all the elements from this list. The list will be empty after this call returns.
     */
    public void clear() {
        // TODO implement clear
        // use remove method
    }


    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index the index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     */
    public E set(int index, E element) {
        // TODO implement set
        // validate index
    }


    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * More formally, returns the lowest index i such that
     * Objects.equals(o, get(i)), or -1 if there is no such index.
     *
     * @param element the element to search for
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     */
    public int indexOf(E element) {
        // TODO implement indexOf
        // linear search
    }


    // TODO write private helper method to grow array

    /**
     * Grows the array by TODO figure out growing strategy
     */
    private void growArray() {

    }

    // TODO write private helper method to shift elements
    /**
     * Shifts elements
     * @param index
     * @param direction
     */
    private void shiftElements(int index, int direction) {
        // if 1, then shift to right
        // for (int i = _index; i <= index; i--)
        // _values[i + 1] = values[i];

        // if -1, shift to left
        // for (int i = index; i <= _index; i++)
        // _values[i] = values[i + 1];
    }
}
