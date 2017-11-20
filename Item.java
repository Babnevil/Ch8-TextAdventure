import java.util.HashMap;
/**
 * Write a description of class Items here.
 *
 * @author Matthew Schilling
 * @version 11/16/17
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int weight;
    private String description;
    private String name;
    private static String printingRoomName;

    /**
     * Constructor for objects of class Items
     */
    public Item(String name, String description, int weight)
    {
        // initialise instance variables
        this.description=description;
        this.weight=weight;
        this.name=name;
        printingRoomName=name;
    }

    /**
     * returns the weight of the item, used to calculate if the player would be able to pick up
     * the item and not go over their carry limit
     */
    public int getWeight()
    {
        return weight;
    }
    /**
     * return a description of the item when the player calls the look command
     */
    public String getDescription(){
        return description;
    }
    /**
     * an attempt to track the item names, believe I need to remove
     */
    public static String trackName(){
        return printingRoomName;
    }
    /**
     * returns the name of the item, which is used to append to the room information
     * and to the list of items the player is carrying
     */
    public String getName(){
        return name;
    }
    
}
