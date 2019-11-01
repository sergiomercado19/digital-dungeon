package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 *
 * For milestone 3 we will change this to an abstract class, extended by 
 * an EntityLoader that will render the visual elements of the dungeon.
 */
public class DungeonLoader {

   private JSONObject json;

   public DungeonLoader(String filename) throws FileNotFoundException {
      json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
   }

   /**
    * Parses the JSON to create a dungeon.
    * @return
    */
   public Dungeon load() {
      int width = json.getInt("width");
      int height = json.getInt("height");

      // 1. Create dungeon
      Dungeon dungeon = new Dungeon(width, height);

      JSONArray jsonEntities = json.getJSONArray("entities");

      // 2. Load entities
      for (int i = 0; i < jsonEntities.length(); i++) {
         loadEntity(dungeon, jsonEntities.getJSONObject(i));
      }
      
      // 3. Alert enemies of players
      dungeon.alertEnemies();
      
      // 4. Set dungeon goals
      dungeon.setGoals(loadGoal(dungeon, json.getJSONObject("goal-condition")));
      
      return dungeon;
   }

   private GoalComponent loadGoal(Dungeon dungeon, JSONObject json) {
      String name = json.getString("goal");
      GoalComponent gc;
      
      if (name.equals("AND") || name.equals("OR")) {
         
         ArrayList<GoalComponent> subgoals = new ArrayList<GoalComponent>();
         JSONArray jsonSubgoals = json.getJSONArray("subgoals");
         for (int i = 0; i < jsonSubgoals.length(); i++) {
            subgoals.add(loadGoal(dungeon, jsonSubgoals.getJSONObject(i)));
         }
         
         if (name.equals("AND")) gc = new GoalAnd(subgoals);
         else gc = new GoalOr(subgoals);
         
      } else {
         
         gc = new Goal(name, dungeon.getEntityQuantity(name));
         
      }
      
      return gc;
   }

   private void loadEntity(Dungeon dungeon, JSONObject json) {
      String type = json.getString("type");
      int x = json.getInt("x");
      int y = json.getInt("y");

      Entity entity = null;
      switch (type) {
         case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
//            onLoad(player);
            entity = player;
            break;
         case "wall":
            Wall wall = new Wall(x, y);
//            onLoad(wall);
            entity = wall;
            break;
         case "exit":
            
            break;
         case "treasure":
            Treasure treasure = new Treasure();
            Collectable cTreasure = new Collectable(x, y, treasure);
            entity = cTreasure;
            break;
         case "door":
            int doorID = dungeon.getEntityQuantity("door");
            Door door = new Door(x, y, doorID);
            entity = door;
            break;
         case "key":
            int keyID = dungeon.getEntityQuantity("key");
            Key key = new Key(keyID);
            Collectable cKey = new Collectable(x, y, key);
            entity = cKey;
            break;
         case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            entity = boulder;
            break;
         case "floorSwitch":
            
            break;
         case "portal":
            
            break;
         case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            entity = enemy;
            break;
         case "sword":
            
            break;
         case "invincibility":
            
            break;
      }
      dungeon.addEntity(entity);
   }

//   public abstract void onLoad(Entity player);
//
//   public abstract void onLoad(Wall wall);

   // TODO MILESTONE 3: Create additional abstract methods for the other entities

}
