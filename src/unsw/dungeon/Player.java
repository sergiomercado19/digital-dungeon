package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements MovableEntity {

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
        super(x, y, true);
        this.dungeon = dungeon;
        this.keyIDs = new ArrayList<>();
        this.swordHits = 0;
        this.invincibilityLeft = 0;
    }
    
    public void makeMove(Direction d) {
    	MoveContext move = null;
    	switch(d) {
    	case UP:
    		move = new MoveContext(new MoveUp());
			break;
    	case DOWN:
    		move = new MoveContext(new MoveDown());
    		break;
    	case LEFT:
    		move = new MoveContext(new MoveLeft());
    		break;
    	case RIGHT:
    		move = new MoveContext(new MoveRight());
    		break;
    	}
    	move.doMove(this);
    }

    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
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
	
	public Dungeon getDungeon() {
		return dungeon;
	}
}
