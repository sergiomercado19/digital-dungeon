package unsw.dungeonTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

import unsw.dungeon.*;

/*
 * Dungeon outline:
 * x x x x x x x
 * x           x
 * x   P   E   x
 * x           x
 * x x x x x x x
 */

public class CombatTesting {

   Dungeon dungeon;
   Player player;
   Enemy enemy;
   
   @BeforeEach
   public void setup() {
      dungeon = new Dungeon(7, 5);
      
      // Add walls
      for (int i = 0; i < 7; i++)
         dungeon.addEntity(new Wall(i, 0));
      for (int i = 0; i < 7; i++)
         dungeon.addEntity(new Wall(i, 4));
      dungeon.addEntity(new Wall(0, 1));
      dungeon.addEntity(new Wall(0, 2));
      dungeon.addEntity(new Wall(0, 3));
      dungeon.addEntity(new Wall(6, 1));
      dungeon.addEntity(new Wall(6, 2));
      dungeon.addEntity(new Wall(6, 3));
      
      // Add player
      player = new Player(dungeon, 2, 2);
      dungeon.setPlayer(player);
      dungeon.addEntity(player);
      
      // Add enemy
      enemy = new Enemy(dungeon, 4, 2);
      enemy.setPlayer(player);
      dungeon.addEntity(enemy);
   }
   
   // US12
   @Test
   void testEnemyChasingPlayer() {
      int preX = enemy.getX();
      int preY = enemy.getY();
      
      player.makeMove(Direction.LEFT);
      
      int post1X = enemy.getX();
      int post1Y = enemy.getY();

      assertEquals(preX - 1, post1X, "Chase player");
      assertEquals(preY, post1Y, "Chase player");
      
      player.makeMove(Direction.LEFT);
      
      int post2X = enemy.getX();
      int post2Y = enemy.getY();
      
      assertEquals(post1X - 1, post2X, "Chase player");
      assertEquals(post1Y, post2Y, "Chase player");
      
      player.makeMove(Direction.LEFT);
      
      int post3X = enemy.getX();
      int post3Y = enemy.getY();

      assertEquals(post2X - 1, post3X, "Kill player");
      assertEquals(post2Y, post3Y, "Kill player");

      assertEquals(DungeonState.LOST, dungeon.getState(), "Player lost");
   }
   
   // US13
   @Test
   void testPlayerChasingEnemy() {
      
      // Add sword
      Sword sword1 = new Sword();
      Collectable cSword1 = new Collectable(1, 2, sword1);
      dungeon.addEntity(cSword1);
      
      int preX = enemy.getX();
      int preY = enemy.getY();
      
      player.makeMove(Direction.LEFT);
      
      int post1X = enemy.getX();
      int post1Y = enemy.getY();

      assertEquals(preX - 1, post1X, "Chase player");
      assertEquals(preY, post1Y, "Chase player");
      assertEquals(true, player.hasSword(), "Player picks up sword");
      assertEquals(5, player.getSwordHits(), "Player has 5 sword hits left");
      
      player.makeMove(Direction.RIGHT);
      
      int post2X = enemy.getX();
      int post2Y = enemy.getY();

      assertEquals(post1X - 1, post2X, "Chase player");
      assertEquals(post1Y, post2Y, "Chase player");
      
      assertEquals(true, dungeon.getEnemies().isEmpty(), "Enemy killed");
      assertEquals(4, player.getSwordHits(), "Player has 4 sword hits left");
      
      // Add another sword
      Sword sword2 = new Sword();
      Collectable cSword2 = new Collectable(3, 2, sword2);
      dungeon.addEntity(cSword2);
      
      player.makeMove(Direction.RIGHT);
      assertEquals(4, player.getSwordHits(), "Player can't pick up sword because he already has one");
      
      
      // Add another enemy
      Enemy enemy2 = new Enemy(dungeon, 4, 2);
      enemy2.setPlayer(player);
      dungeon.addEntity(enemy2);
      
      player.makeMove(Direction.RIGHT);
      assertEquals(3, player.getSwordHits(), "Player kills enemy and has 3 sword hits left");
      
      
      // Add another enemy
      Enemy enemy3 = new Enemy(dungeon, 5, 2);
      enemy3.setPlayer(player);
      dungeon.addEntity(enemy3);
      
      player.makeMove(Direction.RIGHT);
      assertEquals(2, player.getSwordHits(), "Player kills enemy and has 2 sword hits left");
      
      
      // Add another enemy
      Enemy enemy4 = new Enemy(dungeon, 4, 2);
      enemy4.setPlayer(player);
      dungeon.addEntity(enemy4);
      
      player.makeMove(Direction.LEFT);
      assertEquals(1, player.getSwordHits(), "Player kills enemy and has 1 sword hits left");
      
      
      // Add another enemy
      Enemy enemy5 = new Enemy(dungeon, 3, 2);
      enemy5.setPlayer(player);
      dungeon.addEntity(enemy5);
      
      player.makeMove(Direction.LEFT);
      assertEquals(0, player.getSwordHits(), "Player kills enemy and has 0 sword hits left");
      
      // Sword breaks
      assertEquals(false, player.hasSword(), "Sword breaks after being used 5 times");
   }
   
   // US14
   @Test
   void testInvinciblePlayerChasingEnemy() {
      
      // Add invincibility
      Invincibility potion = new Invincibility();
      Collectable cPotion = new Collectable(1, 2, potion);
      dungeon.addEntity(cPotion);
      
      int preX = enemy.getX();
      int preY = enemy.getY();
      
      player.makeMove(Direction.LEFT);
      
      int post1X = enemy.getX();
      int post1Y = enemy.getY();

      assertEquals(preX + 1, post1X, "Flee from player");
      assertEquals(preY, post1Y, "Flee from player");
      assertEquals(true, player.isInvincible(), "Player picks up invincibility potion");
      assertEquals(15, player.getInvincibilityLeft(), "Invincibility lasts 15 moves");
      
      player.makeMove(Direction.RIGHT);
      
      int post2X = enemy.getX();
      int post2Y = enemy.getY();

      assertEquals(post1X, post2X, "Trying to flee from player but can't move");
      assertEquals(post1Y, post2Y, "Trying to flee from player but can't move");
      
      player.makeMove(Direction.RIGHT);
      player.makeMove(Direction.RIGHT);
      player.makeMove(Direction.RIGHT);

      assertEquals(true, dungeon.getEnemies().isEmpty(), "Enemy killed");
      assertEquals(11, player.getInvincibilityLeft(), "After 4 moves, invincibility is active for another 11 moves");
   }
}
