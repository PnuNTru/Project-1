//CS2400
//Project 1
//February 28, 2022
//Phu Troung Sarah Camacho Jonathan Pena

import java.util.Arrays;

public class BagDriver
{
    public static void main(String [] args)
    {
        BagInterface<String> arrayBag1 = new ResizeableArrayBag<>();
        arrayBag1.add();
        arrayBag1.add();
        arrayBag1.add();
        arrayBag1.add();
        arrayBag1.add();
        arrayBag1.add();
        arrayBag1.add();
        
        BagInterface<String> arrayBag2 = new ResizeableArrayBag<>();
        arrayBag2.add();
        arrayBag2.add();
        arrayBag2.add();
        arrayBag2.add();
        arrayBag2.add();
        arrayBag2.add();
        arrayBag2.add();
        
        BagInterface<String> linkedBag1 = new LinkedBag<>();
        linkedBag1.add();
        linkedBag1.add();
        linkedBag1.add();
        linkedBag1.add();
        linkedBag1.add();
        linkedBag1.add();
        linkedBag1.add();
        
        BagInterface<String> linkedBag2 = new LinkedBag<>();
        linkedBag2.add();
        linkedBag2.add();
        linkedBag2.add();
        linkedBag2.add();
        linkedBag2.add();
        linkedBag2.add();
        linkedBag2.add();
        
        System.out.println("Union between two array bags: " + Arrays.toString(arrayBag1.union(arrayBag2).toArray()));
        System.out.println("Intersection between two array bags: " + Arrays.toString(arrayBag1.intersection(arrayBag2).toArray()));
        System.out.println("Difference between two array bags: " + Arrays.toString(arrayBag1.difference(arrayBag2).toArray()));
        
        System.out.println("Union between two linked bags: " + Arrays.toString(linkedBag1.union(linkedBag2).toArray()));
        System.out.println("Intersection between two linked bags: " + Arrays.toString(linkedBag1.intersection(linkedBag2).toArray()));
        System.out.println("Difference between two linked bags: " + Arrays.toString(linkedBag1.difference(linkedBag2).toArray()));
        
        
        
        
    
    
    
    
    
    // array bag test
    
    //union demo
    //intersection demo
    //difference demo
    
    
    //linked bag test
    
}
