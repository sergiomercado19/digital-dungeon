package unsw.dungeon;

/**
 * a wall, which impedes the movement of entities
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Wall extends InertEntity {

   /**
    * create a new wall
    * @param x x position of the wall
    * @param y y position of the wall
    */
   public Wall(int x, int y) {
      super(x, y, true);
   }

}
