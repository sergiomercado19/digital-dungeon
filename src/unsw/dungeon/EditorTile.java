package unsw.dungeon;

import org.json.JSONObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditorTile {
	private int x, y;
	private int ID;
	private StringProperty currType;

	public EditorTile(int x, int y) {
		this.x = x;
		this.y = y;
		this.ID = -1;
		this.currType = new SimpleStringProperty("empty");
	}
	
	public void setTile(String tile) {
		currType.set(tile);
	}
	
	public void setTile(String tile, int ID) {
		currType.set(tile);
		this.ID = ID;
	}
	
	public StringProperty currType() {
		return this.currType;
	}
	
	public JSONObject toJSON() {
		JSONObject tileInfo = new JSONObject();
		if (!currType.get().equals("empty")) {
			tileInfo.put("x", x);
			tileInfo.put("y", y);
			tileInfo.put("type", currType.get());
			if (ID != -1) {
				tileInfo.put("id", ID);
			}
			return tileInfo;
		} else {
			return null;
		}
	}
}
