package unsw.dungeon;

import org.json.JSONObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditorTile {
	private int x, y;
	private int ID;
	private StringProperty currType;

	/**
	 * create a new tile within the editor
	 * @param x the x position of the tile
	 * @param y the y position of the tile
	 */
	public EditorTile(int x, int y) {
		this.x = x;
		this.y = y;
		this.ID = -1;
		this.currType = new SimpleStringProperty("empty");
	}

	/**
	 * set the type of tile
	 * @param tile the type of tile
	 */
	public void setTile(String tile) {
		currType.set(tile);
	}

	/**
	 * set the type and ID of the tile
	 * @param tile the type of tile
	 * @param ID the ID of the tile
	 */
	public void setTile(String tile, int ID) {
		currType.set(tile);
		this.ID = ID;
	}

	/**
	 * check if the current tile contains no entity
	 * @return if the tile is empty
	 */
	public boolean isEmpty() {
		return currType.get().equals("empty");
	}

	/**
	 * the current entity of the tile
	 * @return
	 */
	public StringProperty currType() {
		return this.currType;
	}

	/**
	 * a JSON representation of the tile, for exporting
	 * @return
	 */
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
