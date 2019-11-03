package unsw.dungeonTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

import unsw.dungeon.*;

/*
 * Dungeon outline:
 * x x x x x
 * x       x
 * x   P E x
 * x       x
 * x x x x x
 */

public class InteractionTesting {

   Dungeon dungeon;
   Player player;
   Entity entity;
   
   @BeforeEach
   public void setup() {
      dungeon = new Dungeon(5, 5);
      
      // Add walls
      for (int i = 0; i < 5; i++)
         dungeon.addEntity(new Wall(i, 0));
      for (int i = 0; i < 5; i++)
         dungeon.addEntity(new Wall(i, 4));
      dungeon.addEntity(new Wall(0, 1));
      dungeon.addEntity(new Wall(0, 2));
      dungeon.addEntity(new Wall(0, 3));
      dungeon.addEntity(new Wall(4, 1));
      dungeon.addEntity(new Wall(4, 2));
      dungeon.addEntity(new Wall(4, 3));
      
      // Add player
      player = new Player(dungeon, 2, 2);
      dungeon.setPlayer(player);
      dungeon.addEntity(player);
   }
   
   @Test
   void testInteractionExit() {
      // Add exit
      Entity exit = new Exit(3, 2);
      dungeon.addEntity(exit);
      
      int preX = player.getX();
      int preY = player.getY();
      
      player.makeMove(Direction.RIGHT);
      
      int postX = player.getX();
      int postY = player.getY();

      assertEquals(preX + 1, postX, "Player is able to go through it");
      assertEquals(preY, postY, "Player is able to go through it");
   }
   
   @Test
   void testInteractionPortal() {
      
      // Add pair of portals
      Portal portal1 = new Portal(3, 2, 0);
      dungeon.addEntity(portal1);
      Portal portal2 = new Portal(1, 2, 0);
      dungeon.addEntity(portal2);
      dungeon.linkPortals();
      
      int preX = player.getX();
      int preY = player.getY();
      
      player.makeMove(Direction.RIGHT);
      
      int postX = player.getX();
      int postY = player.getY();

      assertEquals(1, postX, "Player teleports to the corresponding portal");
      assertEquals(preY, postY, "Player teleports to the corresponding portal");
   }
   
   @Test
   void testInteractionTreasure() {
	   // add some treasure
	   Treasure treasure1 = new Treasure();
       Collectable cTreasure1 = new Collectable(3, 2, treasure1);
       dungeon.addEntity(cTreasure1);
       Treasure treasure2 = new Treasure();
       Collectable cTreasure2 = new Collectable(1, 2, treasure2);
       dungeon.addEntity(cTreasure2);
       
       assertEquals(true, dungeon.checkTile(3, 2).contains(cTreasure1), "First treasure is in dungeon");
       player.makeMove(Direction.RIGHT);
       assertEquals(false, dungeon.checkTile(3, 2).contains(cTreasure1), "Player picks up first treasure");
       
       assertEquals(true, dungeon.checkTile(1, 2).contains(cTreasure2), "Second treasure is in dungeon");
       player.makeMove(Direction.LEFT);
       player.makeMove(Direction.LEFT);
       assertEquals(false, dungeon.checkTile(1, 2).contains(cTreasure2), "Player picks up second treasure");
   }
   
   @Test
   void testInteractionBoulder() {
	   // add a boulder
	   Boulder boulder = new Boulder(dungeon, 3, 2);
	   dungeon.addEntity(boulder);
	   
	   // add a floor switch
	   FloorSwitch floorSwitch = new FloorSwitch(4, 3);
	   dungeon.addEntity(floorSwitch);
	   
	   assertEquals(true, dungeon.checkTile(3, 2).contains(boulder), "Boulder is in dungeon");
	   player.makeMove(Direction.RIGHT);
//	   assertEquals(false, dungeon.checkTile(3, 2).contains(boulder), "Boulder is no longer at (3, 2)");
//	   assertEquals(true, dungeon.checkTile(4, 2).contains(boulder), "Boulder is now at (4, 2)");
   }
   
   @Test
   void testInteractionKey() {
	   // add some doors
	   Door door1 = new Door(3, 2, 0);
	   dungeon.addEntity(door1);
	   Door door2 = new Door(1, 2, 1);
	   dungeon.addEntity(door2);
	   
	   // add some keys
	   Key key1 = new Key(0);
	   Collectable cKey1 = new Collectable(2, 3, key1);
	   dungeon.addEntity(cKey1);
	   Key key2 = new Key(1);
	   Collectable cKey2 = new Collectable(2, 1, key2);
	   dungeon.addEntity(cKey2);
	   
	   dungeon.linkKeysToDoors();
       
       assertEquals(player.getX(), 2, "Player is at their starting x");
       assertEquals(player.getY(), 2, "Player is at their starting y");
       
       assertEquals(true, door1.isSolid(), "door1 locked");
       assertEquals(true, door2.isSolid(), "door2 locked");
       
       player.makeMove(Direction.RIGHT);
       assertEquals(player.getX(), 2, "Player is still at their starting x as door1 is locked");
       assertEquals(player.getY(), 2, "Player is still at their starting y as door1 is locked");
       
       player.makeMove(Direction.LEFT);
       assertEquals(player.getX(), 2, "Player is still at their starting x as door2 is locked");
       assertEquals(player.getY(), 2, "Player is still at their starting y as door2 is locked");
       
       assertEquals(true, dungeon.checkTile(2, 3).contains(cKey1), "key1 is in dungeon");
       player.makeMove(Direction.DOWN);
       assertEquals(false, dungeon.checkTile(2, 3).contains(cKey1), "key1 was picked up");
       assertEquals(false, door1.isSolid(), "door1 unlocked");
       assertEquals(true, door2.isSolid(), "door2 locked");
       player.makeMove(Direction.UP);
       player.makeMove(Direction.LEFT);
       assertEquals(player.getX(), 2, "Player is at x = 2 as door2 is still locked");
       assertEquals(player.getY(), 2, "Player is at y = 2 as door2 is still locked");
       
       player.makeMove(Direction.RIGHT);
       assertEquals(player.getX(), 3, "Player is at x = 3 as door1 is unlocked");
       assertEquals(player.getY(), 2, "Player is at y = 2 as door1 is unlocked");
       
       player.makeMove(Direction.LEFT);
       assertEquals(true, dungeon.checkTile(2, 1).contains(cKey2), "key2 is in dungeon");
       player.makeMove(Direction.UP);
       assertEquals(false, dungeon.checkTile(2, 1).contains(cKey2), "key2 was picked up");
       assertEquals(false, door2.isSolid(), "door2 unlocked");
       player.makeMove(Direction.DOWN);
       
       player.makeMove(Direction.LEFT);
       assertEquals(player.getX(), 1, "Player is at x = 1 as door2 is unlocked");
       assertEquals(player.getY(), 2, "Player is at y = 2 as door2 is unlocked");
   }
   
   
   
   
   
   
   
   
   
   
}
