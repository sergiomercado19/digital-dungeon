package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MovableEntity {

    private Dungeon dungeon;
    private List<Integer> keyIDs; 
    private int swordHits;
    private int invincibilityLeft;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, true);
        this.dungeon = dungeon;
        this.keyIDs = new ArrayList<>();
        this.swordHits = 0;
        this.invincibilityLeft = 0;
    }

	public void getSword() {
		// TODO Auto-generated method stub
		
	}

	public void becomeInvincible() {
		// TODO Auto-generated method stub
		
	}

	public void addKey(int ID) {
		keyIDs.add(ID);
	}
	
}
