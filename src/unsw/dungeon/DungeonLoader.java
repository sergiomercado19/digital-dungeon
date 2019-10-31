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

      Dungeon dungeon = new Dungeon(width, height);

      JSONArray jsonEntities = json.getJSONArray("entities");

      for (int i = 0; i < jsonEntities.length(); i++) {
         loadEntity(dungeon, jsonEntities.getJSONObject(i));
      }
      
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
            
            break;
         case "door":
            
            break;
         case "key":
            
            break;
         case "boulder":
            
            break;
         case "floorSwitch":
            
            break;
         case "portal":
            
            break;
         case "enemy":
            
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
