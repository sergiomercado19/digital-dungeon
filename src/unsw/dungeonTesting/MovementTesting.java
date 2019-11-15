package unsw.dungeonTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

import unsw.dungeon.*;

/*
 * Dungeon outline:
 * x x x x x
 * x       x
 * x   P   x
 * x       x
 * x x x x x
 */

public class MovementTesting {

   Dungeon dungeon;
   Player player;

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

   // US1
   @Test
   void testMoveRight() {
      int preX = player.getX();
      int preY = player.getY();

      player.makeMove(Direction.RIGHT);

      int postX = player.getX();
      int postY = player.getY();

      assertEquals(preX + 1, postX, "Move to the adjacent right tile");
      assertEquals(preY, postY, "Move to the adjacent right tile");
   }

   // US1
   @Test
   void testMoveDown() {
      int preX = player.getX();
      int preY = player.getY();

      player.makeMove(Direction.DOWN);

      int postX = player.getX();
      int postY = player.getY();

      assertEquals(preX, postX, "Move to the adjacent bottom tile");
      assertEquals(preY + 1, postY, "Move to the adjacent bottom tile");
   }

   // US1
   @Test
   void testMoveLeft() {
      int preX = player.getX();
      int preY = player.getY();

      player.makeMove(Direction.LEFT);

      int postX = player.getX();
      int postY = player.getY();

      assertEquals(preX - 1, postX, "Move to the adjacent left tile");
      assertEquals(preY, postY, "Move to the adjacent left tile");
   }

   // US1
   @Test
   void testMoveUp() {
      int preX = player.getX();
      int preY = player.getY();

      player.makeMove(Direction.UP);

      int postX = player.getX();
      int postY = player.getY();

      assertEquals(preX, postX, "Move to the adjacent top tile");
      assertEquals(preY - 1, postY, "Move to the adjacent top tile");
   }

   // US2
   @Test
   void testWallTraversing() {
      int preX = this.player.getX();
      int preY = this.player.getY();

      this.player.makeMove(Direction.UP);
      this.player.makeMove(Direction.UP);

      int postX = this.player.getX();
      int postY = this.player.getY();

      assertEquals(preX, postX, "Attempting to traverse wall");
      assertEquals(preY - 1, postY, "Attempting to traverse wall");
   }

   // US2
   @Test
   void testSolidityOfEntities() {

      Player player = new Player(null, 0, 0);
      assertEquals(player.isSolid(), false, "Player is non-solid");

      Wall wall = new Wall(0, 0);
      assertEquals(wall.isSolid(), true, "Wall is solid");

      Exit exit = new Exit(0, 0);
      assertEquals(exit.isSolid(), false, "Exit is non-solid");
      
      Key key = new Key(0, 0, 0);
      assertEquals(key.isSolid(), false, "Key is non-solid");
      
      Invincibility potion = new Invincibility(0, 0);
      assertEquals(potion.isSolid(), false, "Invincibility is non-solid");
      
      Sword sword = new Sword(0, 0);
      assertEquals(sword.isSolid(), false, "Sword is non-solid");
      
      Treasure treasure = new Treasure(0, 0);
      assertEquals(treasure.isSolid(), false, "Treasure is non-solid");

      Door door = new Door(0, 0, 0);
      assertEquals(door.isSolid(), true, "Locked door is solid");
      door.unlock();
      assertEquals(door.isSolid(), false, "Unlocked door is non-solid");

      Boulder boulder = new Boulder(null, 0, 0);
      assertEquals(boulder.isSolid(), false, "Boulder is non-solid");

      FloorSwitch floorSwitch = new FloorSwitch(0, 0);
      assertEquals(floorSwitch.isSolid(), false, "FloorSwitch is non-solid");

      Portal portal = new Portal(0, 0, 0);
      assertEquals(portal.isSolid(), false, "Portal is non-solid");

      Enemy enemy = new Enemy(null, 0, 0, new IneptStrategy());
      assertEquals(enemy.isSolid(), false, "Enemy is non-solid");

   }
}
