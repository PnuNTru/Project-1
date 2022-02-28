import java.util.Arrays;
public class ResizeableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    private boolean integrity = false;
    private static final int MAX_CAPACITY = 100;
    
    //default constructor
    public ResizeableArrayBag()
    {
        T[] newbag = (T[])new Object[DEFAULT_CAPACITY];
    }
    
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
    }
    
    
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
    }

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
        return counter;
    } 
    
    public boolean contains(T anEntry)
    {
        checkIntegrity();
        return getIndexOf(anEntry) > -1; // or >= 0
    }
    
    private void doubleCapacity()
    {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    } 
    
    private void checkIntegrity()
    {
     	if (!integrity)
        	throw new SecurityException("ResizableArrayBag is corrupt.");
    }
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
	
	
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   }  
   
}
