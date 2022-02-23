import java.util.Arrays;
public class ArrayBagTest
{
    
    public static void main(String[] args)
    {
    BagInterface<String> bag1 = new ResizeableArrayBag<>();
    BagInterface<String> bag2 = new ResizeableArrayBag<>();

    bag1.add("A");
    bag1.add("B");
    bag1.add("C");
    bag1.add("D");
    bag1.add("E");
    bag2.add("A");
    bag2.add("B");
    bag2.add("C");
    bag2.add("D");
    bag2.add("E");
    System.out.println("The contents of bag1 is " + Arrays.toString(bag1.toArray()));

    System.out.println("The union of bag1 and bag2 is " + Arrays.toString(bag1.union(bag2).toArray()));

    System.out.println("The intersection of bag1 and bag2 is " + Arrays.toString(bag1.intersection(bag2).toArray()));

    System.out.println("The difference of bag1 and bag2 is " +Arrays.toString(bag1.difference(bag2).toArray()));}
}