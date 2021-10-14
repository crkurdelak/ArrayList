import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A list of elements of type E.
 *
 * @param <E> the type of elements in this list
 *
 * @author ckurdelak20@georgefox.edu
 */
public class ArrayList<E> implements Iterable<E> {

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
     * If the initial capacity is 0, constructs the ArrayList with the default capacity of 10.
     *
     * @param initialCapacity the initial capacity of this ArrayList's backing array
     *
     * @throws IllegalArgumentException if the initial capacity is less than 0
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            if (initialCapacity == 0) {
                _values = new Object[DEFAULT_CAPACITY];
            }
            else {
                _values = new Object[initialCapacity];
                _index = 0;
            }
        }
        else {
            throw new IllegalArgumentException();
        }
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
        return _index == 0;
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
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }


    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently
     * at that position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index the index where the element will be inserted
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if the specified index is out of range
     */
    public void add(int index, E element) {
        if (_index >= _values.length) {
            this.growArray();
        }
        shiftElements(index, SHIFT_RIGHT);
        _values[index] = element;
        _index ++;
    }


    /**
     * Appends the specified element to the end of this list.
     *
     * @param element the element to be appended to this list
     * @return true if this collection has changed as a result of the call
     * @throws IndexOutOfBoundsException if the specified index is out of range
     */
    public boolean add(E element) {
        this.add(_index, element);
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
        E oldElement;
        if (!this.isEmpty()) {
            oldElement = this.set(index, null);
            this.shiftElements(index, SHIFT_LEFT);
            _index --;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
        return oldElement;
    }


    /**
     * Removes all the elements from this list. The list will be empty after this call returns.
     */
    public void clear() {
        // TODO find out what to do if list is already empty
        for (int i = 0; i < _index; i++) {
            _values[i] = null;
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
        E oldElement;
        if (this.isValidIndex(index)) {
            oldElement = this.get(index);
            _values[index] = element;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
        return oldElement;
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
        int index = NOT_FOUND;
        boolean isFound = false;
        int i = 0;
        while (!isFound && i < _index) {
            if (Objects.equals(element, this.get(i))) {
                index = i;
                isFound = true;
            }
            i++;
        }
        return index;
    }


    /**
     * TODO javadocs
     * @return
     */
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }


    /**
     * Returns true if specified int is a valid index in this list, and false if it is not.
     *
     * An index is valid if index > 0 and index < size()
     *
     * @param index the index to validate
     * @return true if the index is valid
     * false if the index is invalid
     */
    private boolean isValidIndex(int index) {
        boolean result = false;
        if (index == 0) {
            result = true;
        }
        else if (index > 0 && index < this.size()) {
            result = true;
        }
        return result;
    }


    /**
     * Grows the backing array by 10.
     * Creates a new backing array, and copies all elements of old backing array to new backing array.
     */
    private void growArray() {
        // TODO test this
        if (_index < Integer.MAX_VALUE) {
            Object[] newArray = new Object[_index + GROW_BY];
            for (int i = 0; i < _index; i++) {
                newArray[i] = this.get(i);
            }
            _values = newArray;
        }
        else {
            throw new OutOfMemoryError();
        }
    }


    /**
     * Shifts elements to the left or right starting at the specified starting index.
     * @param index the starting index
     * @param direction Either -1 for left or 1 for right
     * @throws IllegalArgumentException if passed a value other than -1 or 1 for direction
     */
    private void shiftElements(int index, int direction) {
        if (direction == SHIFT_RIGHT) {
            // starting at _index, shift each item to the right by 1 until you reach index
            for (int i = _index; i >= index; i--) {
                if (_values.length <= _index + 1) {
                    this.growArray();
                }
                _values[i + 1] = _values[i];
            }
        }
        else if (direction == SHIFT_LEFT) {
            // starting at index, shift each item to the left by 1 until you reach _index
            for (int i = index; i <= _index; i++) {
                _values[i] = _values[i + 1];
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Implements the Iterator<T> interface for the ArrayList class.
     */
    private class ArrayListIterator implements Iterator<E> {

        private int _currentIndex;

        /**
         * Constructs a new ArrayListIterator object.
         */
        public ArrayListIterator() {
            _currentIndex = 0;
        }


        /**
         * Returns true if the current index is less than the size of the ArrayList,
         * else returns false.
         *
         * @return true if the current index is less than the size of the ArrayList
         * else return false
         */
        public boolean hasNext() {
            return (_currentIndex < size());
        }


        /**
         * Returns the item at the current index.
         *
         * @return the item at the current index
         * @throws NoSuchElementException if the item at the current index is null
         */
        public E next() {
            E item = get(_currentIndex);
            if (item == null) {
                throw new NoSuchElementException();
            }
            _currentIndex ++;
            return item;
        }
    }
}
