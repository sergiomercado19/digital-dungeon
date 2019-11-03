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
	   FloorSwitch
   }
   
   
   
   
   
   
   
   
   
   
}
