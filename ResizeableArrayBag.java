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
    public int getCurrentSize()
    {
        return numberOfEntries;
    } 
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }
    
    public boolean isFull()
    {
        return numberOfEntries == bag.length;
    }
    
    public T remove()
    {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
     }
     public boolean remove(T anEntry)
    {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index); 
        return anEntry.equals(result);
    }
    public void clear() 
    {
         while (!isEmpty())
         remove();
    }
    
    public int getFrequencyOf(T anEntry)
    {
        checkIntegrity();
         int counter = 0;
         for (int index = 0; index < numberOfEntries; index++)
         {
            if (anEntry.equals(bag[index]))
            {
                counter++;
            }
         }
    } 
    
    public boolean contains(T anEntry)
    {
        checkIntegrity();
        return getIndexOf(anEntry) > -1; // or >= 0
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
