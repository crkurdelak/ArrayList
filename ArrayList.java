/**
 * // TODO write class description
 *
 * @param <E> the type of elements in this list
 *
 * @author ckurdelak20@georgefox.edu
 */
public class ArrayList<E> {
    // TODO implement class

    private E[] _backingArray;
    // a bookkeeping variable that keeps track of the highest filled index in the backing array
    private int _index;


    /**
     * Constructs a new ArrayList object with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of this ArrayList's backing array
     */
    public ArrayList(int initialCapacity) {
        _backingArray = new E[initialCapacity];
        _index = 0;
    }


    /**
     * Constructs a new ArrayList object with a default initial capacity of 10.
     */
    public ArrayList() {
        this(10);
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
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public E get(int index) {
        if (index > 0 && index < _index) {
            return _backingArray[index];
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

}
