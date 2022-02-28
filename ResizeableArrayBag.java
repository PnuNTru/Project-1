import java.util.Arrays;
public class ResizeableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    
    private boolean integrity = false;
    private static final int MAX_CAPACITY = 100;
    
    public ResizeableArrayBag()
    {
        T[] newbag = (T[])new Object[DEFAULT_CAPACITY];
    }
    public ResizeableArrayBag(int capacity)
    {
        if (capacity <= MAX_CAPACITY)
        {
             @SuppressWarnings("unchecked")
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
            result = false;
        }
        else
        {
            bag[numberOfEntries] = newEntry;
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
        @Override
   public BagInterface<T> union(BagInterface<T> other){

      checkIntegrity();
      ResizeableArrayBag<T> bagUnion = new ResizeableArrayBag<T>();
      T[] unionBag = other.toArray();

      for (int i = 0; i < this.numberOfEntries; i++)
     	{
         bagUnion.add(bag[i]);
     	}
      for (int ii = 0; ii < other.getCurrentSize(); ii++)
     	{
         bagUnion.add(unionBag[ii]);
     	}
     	 return bagUnion;
        }
       @Override
   public BagInterface<T> intersection(BagInterface<T> other)
   {  
      // Check the integrity
      checkIntegrity();
      // The RAB we will return
      ResizeableArrayBag<T> bagIntersection = new ResizeableArrayBag<T>();

      // Check if anything is empty
      if(isEmpty() || other == null || other.isEmpty()){
         return bagIntersection;
      }

      // The other bag's copy
      ResizeableArrayBag<T> otherCopy = new ResizeableArrayBag<T>(other.toArray());
      // Loop through and add our intersected items
      for(int i = 0; i < this.numberOfEntries; i++){
         if (otherCopy.remove(this.bag[i])){
            bagIntersection.add(this.bag[i]);
         }
      }

      return bagIntersection;
   }
   
    @Override
   public BagInterface<T> difference(BagInterface<T> other)
   {
      // Check the integrity
      checkIntegrity();
      // The array bag we will return
      ResizeableArrayBag<T> bagDifference = new ResizeableArrayBag<T>();

      // Check if anything is empty
      if(isEmpty() || other == null || other.isEmpty()){
         return bagDifference;
      }

      // The other bag's copy
      ResizeableArrayBag<T> otherCopy = new ResizeableArrayBag<T>(other.toArray());

      // Loop through and add our difference items
      for(int i = 0; i < this.numberOfEntries; i++){
         if (!otherCopy.remove(this.bag[i])){
            bagDifference.add(this.bag[i]);
         }
      }

      return bagDifference;
   }
   
   private int getIndexOf(T anEntry)
	{
		int where = -1;
		boolean found = false;
		int index = 0;
      
      while (!found && (index < numberOfEntries))
		{
			if (anEntry.equals(bag[index]))
			{
				found = true;
				where = index;
			} // end if
         index++;
		} // end while
      
      // Assertion: If where > -1, anEntry is in the array bag, and it
      // equals bag[where]; otherwise, anEntry is not in the array.
      
		return where;
	} // end getIndexOf
	private T removeEntry(int givenIndex)
	{
		T result = null;
		if (!isEmpty() && (givenIndex >= 0))
		{
			 result = bag[givenIndex];         
			 int lastIndex = numberOfEntries - 1;
			 bag[givenIndex] = bag[lastIndex];  // Replace entry to remove with last entry
			 bag[lastIndex] = null;             // Remove reference to last entry
			 numberOfEntries--;
		} // end if

      return result;
	} 
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   }
}
