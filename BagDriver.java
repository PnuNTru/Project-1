//CS2400
//Project 1
//February 28, 2022
//Phu Troung Sarah Camacho Jonathan Pena

import java.util.Arrays;

public class BagDriver
{
    public static void main(String [] args)
    {
        Baginterface<String> arrayBag1 = newResizeablearray<>();
            arrayBag1.add("A");
            arrayBag1.add("B");
            arrayBag1.add("C");
            arrayBag1.add("D");
            arrayBag1.add("E");
            arrayBag1.add("F");
        
        Baginterface<String> arrayBag2 = newResizeablearray<>();
        
            arrayBag2.add("A");
            arrayBag2.add("Z");
            arrayBag2.add("H");
            arrayBag2.add("B");
            arrayBag2.add("C");
            arrayBag2.add("E");
            arrayBag2.add("F");
        
        BagInterface<String> linkedBag1 = new LinkedBag<>();
        
            linkedBag1.add("A");
            linkedBag1.add("X");
            linkedBag1.add("Y");
            linkedBag1.add("Z");
            linkedBag1.add("B");
            linkedBag1.add("C");
        
        BagInterface<String> linkedBag2 = new LinkedBag<>();
            
            linkedBag2.add("B");
            linkedBag2.add("C");
            linkedBag2.add("H");
            linkedBag2.add("J:);
            linkedBag2.add("S");
            linkedBag2.add("Y");
        
        System.out.println("The Union between arrayBag1 & arrayBay2 is: " + Arrays.toString(arrayBag1.union(arrayBag2).toArray()));
        System.out.println("The Intersection between arrayBag1 & arrayBay2 is: " + Arrays.toString(arrayBag1.intersection(arrayBag2).toArray()));
        System.out.println("The Difference between arrayBag1 & arrayBay2 is: " + Arrays.toString(arrayBag1.difference(arrayBag2).toArray()));
        
        System.out.println("The Union between linkedBag1 & linkedBag2 is: " + Arrays.toString(linkedBag1.union(linkedBag2).toArray()));
        System.out.println("The Intersection between linkedBag1 & linkedBag2 is: " + Arrays.toString(linkedBag1.intersection(linkedBag2).toArray()));
        System.out.println("The Difference between linkedBag1 & linkedBag2 is: " + Arrays.toString(linkedBag1.difference(linkedBag2).toArray()));
    }
}
