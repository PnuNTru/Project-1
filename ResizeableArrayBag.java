import java.util.Arrays;
public class ResizeableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    
    private boolean integrity = false;
    private static final int MAX_CAPACITY = 100;
    public ResizableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }
    public ResizeableArrayBag(int capacity)
    {
        if (capacity <= MAX_CAPACITY)
        {
            @SupressWarnings("uncheckeed")
            T[] tempBag = (T[])new Object[capacity];
            bag = tempBag;
            numberOfEntries = 0;
            integrity = true;
        }
        else
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed " + "maximum of " + MAX_CAPACITY);
    }
    /** Adds a new entry to this bag.
        @param newEntry The object to be added as a new entry.
        @return True. */
    public boolean add(T newEntry)
    {
        boolean result = true;
        if (isFull())
        {
            result = false
        }
        else
        {
            bag[numberOfEntries = newEntry;
            numberOfEntries++;
        }

        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return result;
    } // end add

    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++)
        {
            result[index] = bag[index];
        }
        return result;
    }
    
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    } // end isEmpty
    
    public boolean isFull()
    {
        return numberOfEntries == bag.length;
    }
    // Doubles the size of the array bag.
    // Precondition: checkIntegrity has been called.
    private void doubleCapacity()
    {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    } // end doubleCapacity
    private void checkIntegrity()
    {
     if (!integrity)
        throw new SecurityException("ResizableArrayBag is corrupt.");
    }
}
