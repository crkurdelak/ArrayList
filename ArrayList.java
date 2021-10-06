import java.util.Objects;

/**
 * // TODO write class description
 * A list of elements of type E.
 *
 * @param <E> the type of elements in this list
 *
 * @author ckurdelak20@georgefox.edu
 */
public class ArrayList<E> {
    // TODO implement class

    private static final int DEFAULT_CAPACITY = 10;

    // for use in indexOf method
    private static final int NOT_FOUND = -1;

    // for use in private helper method shiftElements:
    private static final int SHIFT_RIGHT = 1;
    private static final int SHIFT_LEFT = -1;

    // for use in private helper method growArray:
    private static final int GROW_BY = 10;

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
        this(DEFAULT_CAPACITY);
    }


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
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (this.isValidIndex(index)) {
            return (E)_values[index];
            // ONLY cast right before return
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }


    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently
     * at that position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException if the specified index is out of bounds
     */
    public void add(int index, E element) {
        // validate index, if invalid, throw IndexOutOfBoundsException
        if (this.isValidIndex(index)) {
            // TODO implement add()
            shiftElements(index, SHIFT_RIGHT);
            this.set(index, element);
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }


    /**
     * Appends the specified element to the end of this list.
     *
     * @param element the element to be appended to this list
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
     * @throws IndexOutOfBoundsException if the specified index is out of range
     */
    public E remove(int index) {
        // TODO implement remove
        E temp;
        if (this.isValidIndex(index)) {
            // use set method to set the element to null
            temp = this.set(index, null);
            // shift elements to left to fill in hole
            this.shiftElements(index, SHIFT_LEFT);
        }
        else {
            throw new IndexOutOfBoundsException();
        }
        return temp;
    }


    /**
     * Removes all the elements from this list. The list will be empty after this call returns.
     */
    public void clear() {
        // TODO find out if it's a waste of time to shift the elements every time bc of using remove method
        for (int i = 0; i < _index; i++) {
            this.remove(i);
        }
        _index = 0;
    }


    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index the index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the specified index is out of range
     */
    public E set(int index, E element) {
        // temporary variable to store old element
        E temp;
        if (this.isValidIndex(index)) {
            temp = this.get(index);
            _values[index] = element;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
        return temp;
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
        int index = NOT_FOUND;
        boolean isFound = false;
        int i = 0;
        // go through array (while loop)
        while (!isFound && i < _index) {
            // check if each element is the same as the target
            // if found, exit loop, result = that index
            if (Objects.equals(element, this.get(i))) {
                index = i;
                isFound = true;
            }
            i++;
        }
        return index;
    }


    /**
     * Returns true if specified int is a valid index in this list, and false if it is not.
     *
     * @param index the index to validate
     * @return true if the index is valid
     * false if the index is invalid
     */
    private boolean isValidIndex(int index) {
        boolean result = false;
        if (index > 0 && index < this.size()) {
            result = true;
        }
        return result;
    }


    /**
     * Grows the backing array by 10.
     * Creates a new backing array, and copies all elements of old backing array to new backing array.
     */
    private void growArray() {
        // make new array of size (_index + GROW_BY)
        Object[] newArray = new Object[_index + GROW_BY];
        // for each element of old array, new array[i] = old array[i]
        for (int i = 0; i < _index; i++) {
            newArray[i] = this.get(i);
        }
        _values = newArray;
    }

    // TODO write private helper method to shift elements
    /**
     * Shifts elements to the left or right starting at the specified starting index.
     * @param startingIndex the starting index
     * @param direction Either -1 for left or 1 for right
     * @throws IllegalArgumentException if passed a value other than -1 or 1 for direction
     */
    private void shiftElements(int startingIndex, int direction) {
        // if 1, then shift to right
        // for (int i = _index; i <= startingIndex; i--)
        // this.set(this.get(i + 1), this.get(i));

        // if -1, shift to left
        // for (int i = startingIndex; i <= _index; i++)
        // this.set(this.get(i), this.get(i + 1));

        // else throw IllegalArgumentException
    }
}
