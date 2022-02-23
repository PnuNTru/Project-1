import java.util.Arrays;
public class ResizeableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    public ResizeableArrayBag(int capacity)
    {
        

    }
    
    public ResizeableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }

    /** Adds a new entry to this bag.
        @param newEntry The object to obe added as a new entry.
        @return True. */
    public boolean add(T newEntry)
    {
        checkIntegrity();
        boolean result = true;
        if (isArrayFull())
        {
            doubleCapacity();
        } // end if

        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;
    } // end add

    // Doubles the size of the array bag.
    // Precondition: checkIntegrity has been called.
    private void doubleCapacity()
    {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    } // end doubleCapacity

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose " + "capacity exceeds allowed " + "maximum of " + MAX_CAPACITY);
    } // end checkCapacity


}