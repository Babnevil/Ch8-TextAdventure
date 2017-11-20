import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Arrays;
/**
 * A work in progress, this class is meant to represent the character the player is playing
 * there are fields that are currently unused, but could be expanded into a more robust game
 * which could include comabt
 *
 * @author Matthew Schilling
 * @version 11.20.2017
 */
public class Player
{
    // instance variables - replace the example below with your own
    private static ArrayList<Item> inventory;
    private String inventoryNames ="";
    private int nameTrack=0;
    private int timeTrack=32;
    private int currentHealth = 50;
    private int maxHealth =100;
    private int playerWeight =0;
    public static final int maxCarryWeight =23;
    private String characterName;
    private boolean alive;
    

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        inventory = new ArrayList();
        
    }

    /**
     * A simple way to check the players health
     */
    public int healthCheck()
    {
        return currentHealth;
    }
    /**
     * Add the selected item to the players inventory, add its weight to the players total carry weight, and append the name 
     * to a string that will return the names of all the items the player is carrying.
     * @param item The currentRoomItem that the player is trying to pick up
     */
    public void addToInventory(Item item){
        inventory.add(item);
        inventoryNames += item.getName()+(" ");
        playerWeight +=item.getWeight();
    }
    /**
     * A crude attempt to remove the item from the players inventory, I have been getting inconsistent results and
     * need to clean up the way the player selects the item to drop.
     * @param item The currentRoomItem the player is tying to drop.
     */
    public void dropFromInventory(Item item){
        inventory.remove(item);
    }
    /**
     * a simple method to reduce the health of the player
     * this is currently used on each room change to represent the player bleeding out as they move about
     * @parma damage The amount of health to be removed
     */
    public void decreaseHealth(int damage){
        currentHealth -=damage;
    }
    /**
     * a simple method to increase the health of the player
     * this is currently being used by the eat command, but can be used too many times per turn
     * @param heal The amount of health to increase by
     */
    public void increaseHealth(int heal){
        currentHealth +=heal;
    }
    /**
     * returns the players current carry weight
     */
    public int getCarryWeight(){
        return playerWeight;
    }
    /**
     * a cheeky way to work around my inventory returning non-sting results when I tried to print directly from it
     * this is currently updated each time a player adds an item.
     */
    public void printInventory(){
        System.out.println(inventoryNames);
    }
}
