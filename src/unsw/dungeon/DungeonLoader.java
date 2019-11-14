package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 *
 * For milestone 3 we will change this to an abstract class, extended by 
 * an EntityLoader that will render the visual elements of the dungeon.
 */
public abstract class DungeonLoader {

	private JSONObject json;

	public DungeonLoader(String filename) throws FileNotFoundException {
		json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
	}

	/**
	 * Parses the JSON to create a dungeon.
	 * @return the newly created dungeon
	 */
	public Dungeon load() {
		int width = json.getInt("width");
		int height = json.getInt("height");

		// 1. Create dungeon
		Dungeon dungeon = new Dungeon(width, height);
		JSONArray jsonEntities = json.getJSONArray("entities");
		
		// 2. Sort entities by Z position
		
		// Convert JSONArray to ArrayList
		ArrayList<JSONObject> entitiesList = new ArrayList<JSONObject>();
		for (int i = 0; i < jsonEntities.length(); i++) {
		   entitiesList.add(jsonEntities.getJSONObject(i));
      }
		
		// Define comparator based on Z position
		Collections.sort(entitiesList, new Comparator<JSONObject>() {
         private static final String KEY_NAME = "type";
         @Override
         public int compare(JSONObject a, JSONObject b) {
            String aType = new String();
            String bType = new String();
            try {
               aType = (String)a.get(KEY_NAME);
               bType = (String)b.get(KEY_NAME);
            } catch(JSONException e) {
               e.printStackTrace();
            }
            
            int aZPos = getTypeZPosition(aType);
            int bZPos = getTypeZPosition(bType);
            return aZPos - bZPos;
         }
      });
		
		// 3. Load entities
		for (JSONObject entity : entitiesList) {
			loadEntity(dungeon, entity);
		}

		// 4. Initialize dungeon
		dungeon.initialize(loadGoal(dungeon, json.getJSONObject("goal-condition")));
		addGoal(dungeon.getGoal());

		// 5. Start dungeon
		dungeon.setState(DungeonState.INPROGRESS);

		return dungeon;
	}

	private int getTypeZPosition(String type) {
	   switch (type) {
	   case "wall":
	   case "door":
	   case "portal":
	   case "exit":
	   case "switch":
	      return 0;
	   case "sword":
	   case "invincibility":
	   case "treasure":
	   case "key":
	      return 1;
	   case "player":
      case "enemy":
         return 2;
      case "boulder":
         return 3;
      default:
         return 4;
      }
	}
	
	/**
	 * load a goal from json into a dungeon
	 * @param dungeon the dungeon to load the goal into
	 * @param json the supplied json
	 * @return the newly created goal component
	 */
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

			ArrayList<Entity> entities = new ArrayList<Entity>();

			switch (name) {
			case "exit":
				// Get goal relevant entity list
				entities.addAll(dungeon.getEntityArrayList("exit"));
				break;
			case "enemies":
				// Get goal relevant entity list
				entities.addAll(dungeon.getEntityArrayList("enemy"));
				break;
			case "boulders":
				// Get goal relevant entity list
				entities.addAll(dungeon.getEntityArrayList("floorswitch"));
				break;
			case "treasure":
				// Get goal relevant entity list
				entities.addAll(dungeon.getEntityArrayList("treasure"));
				break;
			}

			gc = new Goal(name, entities.size());
			// Pass this Goal (that implements GoalObserver) into all the relevant entities (that implement GoalSubject)
			for (Entity e : entities) {
				((GoalSubject) e).addObserver((GoalObserver) gc);
			}

		}

		return gc;
	}

	/**
	 * load an entity from json into a dungeon
	 * @param dungeon the dungeon to load the entity into
	 * @param json the supplied json
	 */
	private void loadEntity(Dungeon dungeon, JSONObject json) {
		String type = json.getString("type");
		int x = json.getInt("x");
		int y = json.getInt("y");

		Entity entity = null;
		switch (type) {
		case "player":
			Player player = new Player(dungeon, x, y);
			dungeon.setPlayer(player);
			onLoad(player);
			entity = player;
			break;
		case "wall":
			Wall wall = new Wall(x, y);
			onLoad(wall);
			entity = wall;
			break;
		case "exit":
			Exit exit = new Exit(x, y);
			onLoad(exit);
			entity = exit;
			break;
		case "treasure":
			Treasure treasure = new Treasure(x, y);
			onLoad(treasure);
			entity = treasure;
			break;
		case "door":
			int doorID = json.getInt("id");;
			Door door = new Door(x, y, doorID);
			onLoad(door);
			entity = door;
			break;
		case "key":
			int keyID = json.getInt("id");;
			Key key = new Key(x, y, keyID);
			onLoad(key);
			entity = key;
			break;
		case "boulder":
			Boulder boulder = new Boulder(dungeon, x, y);
			onLoad(boulder);
			entity = boulder;
			break;
		case "switch":
			FloorSwitch floorSwitch = new FloorSwitch(x, y);
			onLoad(floorSwitch);
			entity = floorSwitch;
			break;
		case "portal":
			int portalID = json.getInt("id");
			Portal portal = new Portal(x, y, portalID);
			onLoad(portal);
			entity = portal;
			break;
		case "enemy":
			Enemy enemy = new Enemy(dungeon, x, y);
			onLoad(enemy);
			entity = enemy;
			break;
		case "sword":
			Sword sword = new Sword(x, y);
			onLoad(sword);
			entity = sword;
			break;
		case "invincibility":
			Invincibility invincibility = new Invincibility(x, y);
			onLoad(invincibility);
			entity = invincibility;
			break;
		}
		dungeon.addEntity(entity);
	}

	public abstract void onLoad(Enemy enemy);
	public abstract void onLoad(Portal portal);
	public abstract void onLoad(FloorSwitch floorSwitch);
	public abstract void onLoad(Boulder boulder);
	public abstract void onLoad(Door door);
	public abstract void onLoad(Exit exit);
	public abstract void onLoad(Player player);
	public abstract void onLoad(Wall wall);
	public abstract void onLoad(Key key);
	public abstract void onLoad(Treasure treasure);
	public abstract void onLoad(Sword sword);
	public abstract void onLoad(Invincibility invincibility);

	public abstract void addGoal(GoalComponent goals);
	
}
