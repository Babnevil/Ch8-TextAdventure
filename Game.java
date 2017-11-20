import java.util.Random;
/**
 *  This class is the main class of the game i have created. It is very simple, and has a few items to collect along the way
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Item currentRoomItem;
    private Room lastRoom;
        
    /**
     * Create the game and initialise its internal map and set the items.
     * 
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     * Then link an item to every room.
     */
    private void createRooms()
    {
        Room outside,spawn, theater, pub, lab,bar, office, bedroom, childBedroom, library, broomCloset, trapDoor;
        Room sittingRoom, diningRoom, kitchen, storeRoom, staffBunks, wineCellar, sittingRoomLeft;
      
        // create the rooms
        
        office = new Room("in an office furnished with a dark wooden desk, and a single chair");
        spawn = new Room("just inside the door of the house. There are 3 hallways stretching out \nfrom the entrance, all dimly lit by the pale light of the full moon");
        bar = new Room("in a small home bar, with broken glass littering the floor");
        bedroom = new Room("in a spacious bedroom. You cant help but notice rather large blood stains on the white sheets.");
        childBedroom = new Room("in a child's bedroom, a crib stands alone in the center of the room");
        library = new Room("in an enormous library with bookshelfs stretching to the ceeling");
        sittingRoom = new Room("in a cozy sitting room, a torn picture hangs above a stone fireplace");
        broomCloset = new Room("in a tiny broom closet\nwhy are you still in here, are you a broom?");
        sittingRoomLeft = new Room("in a rather large sitting room, with many large cushioned chairs");
        diningRoom = new Room("in a dining room with a table that looked able to seat 50");
        kitchen = new Room("in a kitchen, pots and pans hanging from the cabinets that lined the entire room");
        storeRoom = new Room("in a store room with shelves filled with rotten foods");
        wineCellar = new Room("in a wine cellar, with quite a few rare vintages");
        staffBunks = new Room("in a simple room with 4 beds and a single worn table in the center");
        trapDoor = new Room("in a dark stone pit, with no light and no way out.\nGame Over");
        
        //create the items
        Item goldSword, oldTome, emptyBottle, shreddedTrousers, goldCoin, humanSkull, axe;
        Item journal, torch, key, pocketWatch, broom;
        
        goldSword = new Item("Gold Sword", "An impratical golden sword, who would even use this?", 15);
        sittingRoom.setItem(sittingRoom, goldSword);
        
        oldTome = new Item("Old Tome", "A weathered old tome bound in a deep, red leather.\n" +
        "It is written in strange characters you have never seen before", 3);
        library.setItem(library, oldTome);
        
        emptyBottle = new Item("Bottle", "Just an empty wine bottle. Couldn't even be half empty?", 1);
        wineCellar.setItem(wineCellar, emptyBottle);
        
        shreddedTrousers = new Item("Pants", "A shredded pair of tan trousers lie on the floor", 3);
        bar.setItem(bar, shreddedTrousers);
        
        goldCoin = new Item("Coin", "A small golden coin", 0);
        sittingRoomLeft.setItem(sittingRoomLeft, goldCoin);
        
        humanSkull = new Item("Skull", "A intact human skull...comforting", 3);
        storeRoom.setItem(storeRoom, humanSkull);
        
        axe = new Item("Axe", "A simple wood cutting axe. Great for logs, or anything that needs chopping", 10);
        bedroom.setItem(bedroom, axe);
        
        journal = new Item("Journal", "Last Entry: Meal after meal, yet never full. Wine followed by mead,"+
        "yet never quenced. What will it take to sate my desires?", 2);
        childBedroom.setItem(childBedroom, journal);
        
        torch = new Item("Torch", "The dark passages won't give you the chills anymore", 2);
        kitchen.setItem(kitchen, torch);
        
        key = new Item("Key", "An ancient looking key. This might come in handy.", 1);
        diningRoom.setItem(diningRoom, key);
        
        pocketWatch = new Item("Watch", "A beautiful silver engraved pocket watch", 1);
        office.setItem(office, pocketWatch);
        // initialise room exits
        spawn.setExit("east", sittingRoom);
        spawn.setExit("north", office);
        spawn.setExit("west", sittingRoomLeft);
        
        office.setExit("south", spawn);
        office.setExit("west", diningRoom);
        office.setExit("north", bar);
        
        diningRoom.setExit("south", sittingRoomLeft);
        diningRoom.setExit("west", kitchen);
        
        kitchen.setExit("east",diningRoom);
        kitchen.setExit("north",storeRoom);
        
        storeRoom.setExit("east", wineCellar);
        storeRoom.setExit("north",staffBunks);
        
        wineCellar.setExit("west", storeRoom);
        
        staffBunks.setExit("south",storeRoom);
        
        bar.setExit("north", bedroom);
        bar.setExit("south", office);
        
        bedroom.setExit("south", bar);
        bedroom.setExit("east", childBedroom);
        
        childBedroom.setExit("west", bedroom);
        childBedroom.setExit("south",library);
        
        library.setExit("south",sittingRoom);
        library.setExit("north", childBedroom);
        
        sittingRoom.setExit("north", library);
        sittingRoom.setExit("west", spawn);
        sittingRoom.setExit("south", broomCloset);
        sittingRoom.setExit("east", trapDoor);

        currentRoom = spawn;  // start game at spawn
        currentRoomItem = currentRoom.getItem(currentRoom);
    }
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        int health = player.healthCheck();
        while ((! finished)&& (health>0)) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bloodied and limping, you spot a old house in the distance.");
        System.out.println("After great effort, you reach the door to the house and collapse against it");
        System.out.println("To your surprise the doors fall open under your weight, leaving you flat on your face.");
        System.out.println("No time to wonder why the door would be unlocked, the beast is too near!");
        System.out.println("You quickly stack whatever you can find against the door, but you know it will not hold");
        System.out.println("There must be another way out!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                System.out.println(currentRoom.getLongDescription());
                System.out.println(currentRoomItem.getDescription());
                break;
                
            case EAT:
                System.out.println("You rummage through your pack, and surprisingly"+
                "find a potato. Better than nothing I guess. \n Health "+player.healthCheck());
                player.increaseHealth(5);
                break;
                
            case TAKE:
                if((currentRoomItem.getWeight()+player.getCarryWeight())<= player.maxCarryWeight){
                player.addToInventory(currentRoomItem);
                System.out.println(currentRoomItem);
                currentRoom.removeItem(currentRoomItem);}
                else
                System.out.println("I can't carry any more!");
                break;
                
            case DROP:
                player.dropFromInventory(currentRoomItem);
                System.out.println("You dropped your " + currentRoomItem.getName());
                break;
                
            case INVENTORY:
                player.printInventory();
                break;
                
            case BACK:
                System.out.println(lastRoom.getLongDescription());
                currentRoom = lastRoom;
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are desperately trying to survive the night as you");
        System.out.println("explore an old manor. Perhaps there is somewhere to hide.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        Item nextItem = nextRoom.getItem(nextRoom);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        //update the fields that track which rooms the player is interacting with
        else {
            lastRoom = currentRoom;
            currentRoom = nextRoom;
            currentRoomItem = nextItem;
            System.out.println(currentRoomItem);
            player.decreaseHealth(8);
            System.out.println(currentRoom.getLongDescription());
            System.out.println(currentRoomItem.getName());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    Random rand = new Random(42);
}
