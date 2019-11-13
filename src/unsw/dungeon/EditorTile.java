package unsw.dungeon;

import org.json.JSONObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EditorTile {
	private int x, y;
	private int ID;
	private Image image;
	private String type;

	private Image playerImage;
	private Image wallImage;
	private Image boulderImage;
	private Image treasureImage;
	private Image keyImage;
	private Image doorImage;
	private Image swordImage;
	private Image invincibilityImage;
	private Image portalImage;
	private Image enemyImage;
	private Image floorSwitchImage;
	private Image exitImage;

	public EditorTile(int x, int y) {
		playerImage = new Image("/human_new.png");
		wallImage = new Image("/brick_brown_0.png");
		boulderImage = new Image("/boulder.png");
		treasureImage = new Image("/gold_pile.png");
		keyImage = new Image("/key.png");
		doorImage = new Image("/closed_door.png");
		swordImage = new Image("/greatsword_1_new.png");
		invincibilityImage = new Image("/brilliant_blue_new.png");
		portalImage = new Image("/portal.png");
		enemyImage = new Image("/deep_elf_master_archer.png");
		floorSwitchImage = new Image("/pressure_plate.png");
		exitImage = new Image("/exit.png");

		this.x = x;
		this.y = y;
		this.ID = -1;
		this.image = null;
		this.type = null;
		
	}

	public void setTile(String tileName, String ID) {
		switch(tileName) {
		case "Eraser":
			this.ID = -1;
			image = null;
			type = null;
			break;
		case "Player":
			this.ID = -1;
			image = playerImage;
			type = "player";
			break;
		case "Wall":
			this.ID = -1;
			image = wallImage;
			type = "wall";
			break;
		case "Boulder":
			this.ID = -1;
			image = boulderImage;
			type = "boulder";
			break;
		case "Treasure":
			this.ID = -1;
			image = treasureImage;
			type = "treasure";
			break;
		case "Key":
			this.ID = Integer.parseInt(ID);
			image = keyImage;
			type = "key";
			break;
		case "Door":
			this.ID = Integer.parseInt(ID);
			image = doorImage;
			type = "door";
			break;
		case "Sword":
			this.ID = -1;
			image = swordImage;
			type = "sword";
			break;
		case "Invincibility":
			this.ID = -1;
			image = invincibilityImage;
			type = "invincibility";
			break;
		case "Portal":
			this.ID = Integer.parseInt(ID);
			image = portalImage;
			type = "portal";
			break;
		case "Enemy":
			this.ID = -1;
			image = enemyImage;
			type = "enemy";
			break;
		case "Floor switch":
			this.ID = -1;
			image = floorSwitchImage;
			type = "switch";
			break;
		case "Exit":
			this.ID = -1;
			image = exitImage;
			type = "exit";
			break;
		}
	}

	public ImageView getTile() {
		return new ImageView(image);
	}
	
	public JSONObject toJSON() {
		JSONObject tileInfo = new JSONObject();
		if (type != null) {
			tileInfo.put("x", x);
			tileInfo.put("y", y);
			tileInfo.put("type", type);
			if (ID != -1) {
				tileInfo.put("id", ID);
			}
			return tileInfo;
		} else {
			return null;
		}
	}
}
