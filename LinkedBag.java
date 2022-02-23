//Project 1
//CS2400.03
//Febraury 28, 2022
//Phu Troung, Sarah Camacho, Jonathan Pena

public class LinkedBag<T> implements BagInterface<T>
{
 private Node firstNode;
 private int numberOfEntries;

    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    }
    //end default constructor 

 
    private class Node 
    {
        private T data;
        private Node next;
        
        private Node(T dataportion)
        {
         this(dataportion, null);
        }
        //end constructor

        private Node(T dataPortion, Node nextNode)
        {
         data = dataPortion;
         next = nextNode;
        }
        //end constructor
    }
    //end Node
}
//end LinkedBag

