package unsw.dungeonTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

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
	   Sword sword = new Sword(0, 0);
      // Give sword to player
      player.pickupSword(sword);

      // Add enemy
      Enemy enemy = new Enemy(dungeon, 3, 2, new IneptStrategy());
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
      Treasure treasure = new Treasure(3, 2);
      dungeon.addEntity(treasure);

      // Configure Treasure goal
      GoalComponent gc = new Goal("treasure", 1);
      ((GoalSubject) treasure).addObserver((GoalObserver) gc);
      dungeon.setGoals(gc);

      player.makeMove(Direction.RIGHT);

      assertEquals(DungeonState.WON, dungeon.getState(), "Player wins when goal is completed");
   }

   @Test
   void testBoulderGoal() {

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

      assertEquals(DungeonState.WON, dungeon.getState(), "Player wins when goal is completed");
   }

   @Test
   void testAndGoal() {
      player.makeMove(Direction.LEFT);
      
      // Add boulder
      Boulder boulder = new Boulder(dungeon, 2, 2);
      dungeon.addEntity(boulder);
      // Add FloorSwitch
      FloorSwitch floorSwitch = new FloorSwitch(3, 2);
      dungeon.addEntity(floorSwitch);
      // Configure Boulder goal
      GoalComponent gc1 = new Goal("boulder", 1);
      ((GoalSubject) floorSwitch).addObserver((GoalObserver) gc1);

      // Add exit
      Exit exit = new Exit(3, 3);
      dungeon.addEntity(exit);
      // Configure Exit goal
      GoalComponent gc2 = new Goal("exit", 1);
      ((GoalSubject) exit).addObserver((GoalObserver) gc2);
      
      ArrayList<GoalComponent> subgoals = new ArrayList<GoalComponent>();
      subgoals.add(gc1);
      subgoals.add(gc2);
      dungeon.setGoals(new GoalAnd(subgoals));
      
      player.makeMove(Direction.RIGHT);
      player.makeMove(Direction.DOWN);
      player.makeMove(Direction.RIGHT);

      assertEquals(DungeonState.WON, dungeon.getState(), "Player wins when goal is completed");
   }
   
   @Test
   void testOrGoal() {
      player.makeMove(Direction.LEFT);
      // Add boulder
      Boulder boulder = new Boulder(dungeon, 2, 2);
      dungeon.addEntity(boulder);
      // Add FloorSwitch
      FloorSwitch floorSwitch = new FloorSwitch(3, 2);
      dungeon.addEntity(floorSwitch);
      // Configure Boulder goal
      GoalComponent gc1 = new Goal("boulder", 1);
      ((GoalSubject) floorSwitch).addObserver((GoalObserver) gc1);

      // Add exit
      Exit exit = new Exit(3, 3);
      dungeon.addEntity(exit);
      // Configure Exit goal
      GoalComponent gc2 = new Goal("exit", 1);
      ((GoalSubject) exit).addObserver((GoalObserver) gc2);
      
      ArrayList<GoalComponent> subgoals = new ArrayList<GoalComponent>();
      subgoals.add(gc1);
      subgoals.add(gc2);
      dungeon.setGoals(new GoalOr(subgoals));
      
      // Only complete one of the goals
      player.makeMove(Direction.RIGHT);

      assertEquals(DungeonState.WON, dungeon.getState(), "Player wins when goal is completed");
   }
   
   @Test
   void testCompoundGoal() {
      player.makeMove(Direction.LEFT);
      
      // Add boulder
      Boulder boulder = new Boulder(dungeon, 2, 2);
      dungeon.addEntity(boulder);
      // Add FloorSwitch
      FloorSwitch floorSwitch = new FloorSwitch(3, 2);
      dungeon.addEntity(floorSwitch);
      // Configure Boulder goal
      GoalComponent gc1 = new Goal("boulder", 1);
      ((GoalSubject) floorSwitch).addObserver((GoalObserver) gc1);

      // Add exit
      Exit exit = new Exit(3, 3);
      dungeon.addEntity(exit);
      // Configure Exit goal
      GoalComponent gc2 = new Goal("exit", 1);
      ((GoalSubject) exit).addObserver((GoalObserver) gc2);

      // Add treasure
      Treasure treasure = new Treasure(3, 1);
      dungeon.addEntity(treasure);
      // Configure Treasure goal
      GoalComponent gc3 = new Goal("treasure", 1);
      ((GoalSubject) treasure).addObserver((GoalObserver) gc3);
      
      ArrayList<GoalComponent> subgoals1 = new ArrayList<GoalComponent>();
      subgoals1.add(gc2);
      subgoals1.add(gc3);
      
      ArrayList<GoalComponent> subgoals2 = new ArrayList<GoalComponent>();
      subgoals2.add(gc1);
      subgoals2.add(new GoalOr(subgoals1));
      dungeon.setGoals(new GoalOr(subgoals2));
      
      player.makeMove(Direction.RIGHT);
      player.makeMove(Direction.DOWN);
      player.makeMove(Direction.RIGHT);

      assertEquals(DungeonState.WON, dungeon.getState(), "Player wins when goal is completed");
   }
   
}
