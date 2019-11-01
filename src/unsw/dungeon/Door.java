package unsw.dungeon;

public class Door extends InertEntity implements KeyObserver {
	
	private boolean isOpen;
	// FIXME do we really need key IDs when the observers will be linked?
	private int keyID;

	public Door(int x, int y, int keyID) {
		super(x, y, true);
		isOpen = false;
		this.keyID = keyID;
	}

	@Override
	public void update() {
		isOpen = true;
		super.setSolid(false);
	}

}
