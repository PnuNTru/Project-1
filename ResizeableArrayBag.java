//Project 1
//CS2400.03
//February 28, 2022
//Phu Truong, Jonathan Pena, Sarah Camacho

import java.util.Arrays;
public class ResizeableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    private boolean integrity = false;
    private static final int MAX_CAPACITY = 10000;
    
    //default constructor
    public ResizeableArrayBag()
    {
        T[] newbag = (T[])new Object[DEFAULT_CAPACITY];
    } // end default constructor
    
    // constructor with given bag size
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
    
    //copy constructor
    public ResizeableArrayBag(T[] newbag)
    {
        checkCapacity(newbag.length);
        bag = Arrays.copyOf(newbag, newbag.length);
        numberOfEntries = newbag.length;
        integrity = true;
    } // end copy constructor
    
    /**Adds a new entry to this bag.
	@param newEntry the object to be added as a new entry.
	@return True. */
    public boolean add(T newEntry)
    {
    	checkIntegrity();
        if (isArrayFull())
	{
            doubleCapacity();
        }
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    /** Retrieves all entries that are in this bag.
       @return  A newly allocated array of all the entries in this bag. */
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
    
    /** Gets the current number of entries in this bag.
	@return The integer number of entries currently in the bag. */ 
    public int getCurrentSize()
    {
        return numberOfEntries;
    } 
    
    /** Sees whether this bag is empty.
	@return True if the bag is empty, or false if not. */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }
    
     // Returns true if the array bag is full, or false if not.
    private boolean isArrayFull()
    {
	return numberOfEntries >= bag.length;
    } // end isArrayFull
  
    
    /** Removes one unspecified entry from this bag, if possible.
	@return Either the removed entry, if the removal was successful, or null. */
    public T remove()
    {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }
    
    /** Removes one occurrence of a given entry from this bag, if possible.
	@param anEntry The entry to be removed.
	@return True if the removal was successful, or false if not. */
    public boolean remove(T anEntry)
    {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index); 
        return anEntry.equals(result);
    }
    
    /** Removes all entries from this bag. */
    public void clear() 
    {
         while (!isEmpty())
         remove();
    }
    
    /** Counts the number of times a given entry appears in this bag.
	@param anEntry The entry to be counted.
	@return The number of times anEntry appears in the bag. */
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
        return counter;
    } 
    
    /** Tests whether this bag contains a given entry.
	@param anEntry The entry to find.
	@return True if the bag contains anEntry, or false if not. */
    public boolean contains(T anEntry)
    {
        checkIntegrity();
        return getIndexOf(anEntry) > -1; // or >= 0
    }
    
    /** doubles the size of the array bag*/
    private void doubleCapacity()
    {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    } 
    
    /** throws an exception if the object is not initialized*/
    private void checkIntegrity()
    {
     	if (!integrity)
        	throw new SecurityException("ResizableArrayBag is corrupt.");
    }
       
   /* locates given entry within the array bag
   	@return index of entry ot -1 if not located*/
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
		}
         	index++;
	} 
	return where;
   } 
   
   /* removes entry at a given index within the array bag
   	@param index of entry to be removed from bag
	@return entry if it exists, null if it does not*/
   private T removeEntry(int givenIndex)
   {
	T result = null;
	if (!isEmpty() && (givenIndex >= 0))
	{
		result = bag[givenIndex];         
		int lastIndex = numberOfEntries - 1;
		bag[givenIndex] = bag[lastIndex]; 
		bag[lastIndex] = null;   
		numberOfEntries--;
	} 
	return result;
   } 

   //Throws an exceptoin if the requested capacity is too large.
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   }  
    
    /** Returns a new bag that contains elements of both bags, including duplicates.
     *  Does not affect the contents of the bags used.
    	@param bag1 The bag you want to union with.
    	@return  The union of both bags as a new bag. Order does not matter and duplicates are allowed. */
    @Override
    public BagInterface<T> union(BagInterface<T> other)
	{
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
	  
   /** Returns a new bag that contains elements that only appear in both bags. Duplicate items are counted
      * if both bags contain that duplicate item.
      * Does not affect the contents of the bags used.
        @param bag1  The bag you want to intersect with
        @return  The intersection of both bags as a new bag. */
    @Override   
   public BagInterface<T> intersection(BagInterface<T> other)
   {  
	checkIntegrity();
	ResizeableArrayBag<T> bagIntersection = new ResizeableArrayBag<T>();
	if(isEmpty() || other == null || other.isEmpty())
	{
		return bagIntersection;
	}
	ResizeableArrayBag<T> otherCopy = new ResizeableArrayBag<T>(other.toArray());
	for(int i = 0; i < this.numberOfEntries; i++)
	{
		if (otherCopy.remove(this.bag[i]))
		{
			bagIntersection.add(this.bag[i]);
		}
	}
	return bagIntersection;
    }
   
   /** Returns a new bag that contains elements in one bag after removing the elements that are found
      * in another bag. Does not affect the contents of the bags used.
        @param bag1 The bag that elements you don't want in the first bag.
        @return  The difference of both bags as a new bag. */
    @Override
   public BagInterface<T> difference(BagInterface<T> other)
   {
      checkIntegrity();
      ResizeableArrayBag<T> bagDifference = new ResizeableArrayBag<T>();
      if(isEmpty() || other == null || other.isEmpty())
      {
         return bagDifference;
      }
      ResizeableArrayBag<T> otherCopy = new ResizeableArrayBag<T>(other.toArray());
      for(int i = 0; i < this.numberOfEntries; i++)
      {
		if (!otherCopy.remove(this.bag[i]))
		{
			bagDifference.add(this.bag[i]);
		}
      }
      return bagDifference;
   }

}
