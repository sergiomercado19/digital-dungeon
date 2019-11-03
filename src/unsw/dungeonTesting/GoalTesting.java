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

public class GoalTesting {

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
   
   @Test
   void testExitGoal() {
      // Add exit
      Exit exit = new Exit(3, 2);
      dungeon.addEntity(exit);
      // Configure Exit goal
      GoalComponent gc = new Goal("exit", 1);
      ((GoalSubject) exit).addObserver((GoalObserver) gc);
      dungeon.setGoals(gc);
      
      player.makeMove(Direction.RIGHT);

      assertEquals(DungeonState.WON, dungeon.getState(), "Player wins when goal is completed");
   }
   
   @Test
   void testEnemyGoal() {
      // Give sword to player
      player.pickupSword();
      
      // Add enemy
      Enemy enemy = new Enemy(dungeon, 3, 2);
      enemy.setPlayer(player);
      dungeon.addEntity(enemy);
      
      // Configure Enemy goal
      GoalComponent gc = new Goal("enemy", 1);
      ((GoalSubject) enemy).addObserver((GoalObserver) gc);
      dungeon.setGoals(gc);
      
      player.makeMove(Direction.RIGHT);

      assertEquals(DungeonState.WON, dungeon.getState(), "Player wins when goal is completed");
   }
   
   @Test
   void testTreasureGoal() {
      // Add treasure
      Treasure treasure = new Treasure();
      Collectable cTreasure = new Collectable(3, 2, treasure);
      dungeon.addEntity(cTreasure);
      
      // Configure Treasure goal
      GoalComponent gc = new Goal("treasure", 1);
      ((GoalSubject) treasure).addObserver((GoalObserver) gc);
      dungeon.setGoals(gc);
      
      player.makeMove(Direction.RIGHT);

      assertEquals(DungeonState.WON, dungeon.getState(), "Player wins when goal is completed");
   }
   
   @Test
   void testBoulderGoal() {
      System.out.println("--------");
      // Add boulder
      Boulder boulder = new Boulder(dungeon, 3, 2);
      dungeon.addEntity(boulder);
      
      // Add FloorSwitch
      FloorSwitch floorSwitch = new FloorSwitch(3, 1);
      dungeon.addEntity(floorSwitch);
      
      // Configure Boulder goal
      GoalComponent gc = new Goal("boulder", 1);
      ((GoalSubject) floorSwitch).addObserver((GoalObserver) gc);
      dungeon.setGoals(gc);
      
      player.makeMove(Direction.DOWN);
      player.makeMove(Direction.RIGHT);
      player.makeMove(Direction.UP);

      System.out.println("fs: " + floorSwitch.getX() + " " + floorSwitch.getY());
      System.out.println("boulder: " + boulder.getX() + " " + boulder.getY());
      System.out.println("player: " + player.getX() + " " + player.getY());
      
      assertEquals(DungeonState.WON, dungeon.getState(), "Player wins when goal is completed");
   }
   
   
}
