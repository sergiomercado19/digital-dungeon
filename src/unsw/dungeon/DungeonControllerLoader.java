package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

	private ArrayList<ImageView> entities;
   private TreeItem<String> goal;

	//Images
	private Image playerImage;
	private Image playerSwordImage;
	private Image playerInvincibleImage;
	private Image wallImage;
	private Image boulderImage;
	private Image treasureImage;
	private Image keyImage;
	private Image doorImage;
	private Image doorOpenImage;
	private Image swordImage;
	private Image invincibilityImage;
	private Image portalImage;
	private Image elfImage;
	private Image houndImage;
	private Image gnomeImage;
	private Image floorSwitchImage;
	private Image exitImage;

	public DungeonControllerLoader(String filename)
			throws FileNotFoundException {
		super(filename);
		entities = new ArrayList<>();
		playerImage = new Image("/human_new.png");
		playerSwordImage = new Image("/human_sword.png");
		playerInvincibleImage = new Image("/human_new_invincible.png");
		wallImage = new Image("/brick_brown_0.png");
		boulderImage = new Image("/boulder.png");
		treasureImage = new Image("/gold_pile.png");
		keyImage = new Image("/key.png");
		doorImage = new Image("/closed_door.png");
		doorOpenImage = new Image("/open_door.png");
		swordImage = new Image("/greatsword_1_new.png");
		invincibilityImage = new Image("/brilliant_blue_new.png");
		portalImage = new Image("/portal.png");
		houndImage = new Image("/hound.png");
		elfImage = new Image("/deep_elf_master_archer.png");
		gnomeImage = new Image("/gnome.png");
		floorSwitchImage = new Image("/pressure_plate.png");
		exitImage = new Image("/exit.png");
	}

	@Override
	public void onLoad(Player player) {
		ImageView view = new ImageView(playerImage);
		addEntity(player, view);
	}

	@Override
	public void onLoad(Wall wall) {
		ImageView view = new ImageView(wallImage);
		addEntity(wall, view);
	}

	@Override
	public void onLoad(Enemy enemy) {
	   ImageView view = null;
	   switch (enemy.getEnemyType()) {
	   case ENEMY:	      
	      view = new ImageView(elfImage);
	      break;
	   case HOUND:
	      view = new ImageView(houndImage);
	      break;
	   case GUARD:
	      view = new ImageView(gnomeImage);
	      break;
	   }
		addEntity(enemy, view);
	}

	@Override
	public void onLoad(Portal portal) {
		ImageView view = new ImageView(portalImage);
		addEntity(portal, view);
	}

	@Override
	public void onLoad(FloorSwitch floorSwitch) {
		ImageView view = new ImageView(floorSwitchImage);
		addEntity(floorSwitch, view);
	}

	@Override
	public void onLoad(Boulder boulder) {
		ImageView view = new ImageView(boulderImage);
		addEntity(boulder, view);
	}

	@Override
	public void onLoad(Door door) {
		ImageView view = new ImageView(doorImage);
		addEntity(door, view);
	}

	@Override
	public void onLoad(Key key) {
		ImageView view = new ImageView(keyImage);
		addEntity(key, view);
	}

	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
		addEntity(treasure, view);
	}

	@Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
		addEntity(sword, view);
	}

	@Override
	public void onLoad(Invincibility invincibility) {
		ImageView view = new ImageView(invincibilityImage);
		addEntity(invincibility, view);
	}

	@Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
		addEntity(exit, view);
	}

	private void addEntity(Entity entity, ImageView view) {
	   trackEntity(entity, view);
		entities.add(view);
	}

	/**
	 * Set a node in a GridPane to have its position track the position of an
	 * entity in the dungeon.
	 *
	 * By connecting the model with the view in this way, the model requires no
	 * knowledge of the view and changes to the position of entities in the
	 * model will automatically be reflected in the view.
	 * @param entity
	 * @param node
	 */
	private void trackEntity(Entity entity, Node node) {
		GridPane.setColumnIndex(node, entity.getX());
		GridPane.setRowIndex(node, entity.getY());
		entity.x().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				GridPane.setColumnIndex(node, newValue.intValue());
			}
		});
		entity.y().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				GridPane.setRowIndex(node, newValue.intValue());
			}
		});
		entity.inDungeon().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (oldValue == true && newValue == false) {
					node.setVisible(false);
					node.managedProperty().bind(node.visibleProperty());
				}
			}
		});
		if (entity instanceof Player) {
			Player p = (Player) entity;
			p.hasSword().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable,
						Boolean oldValue, Boolean newValue) {
					if (oldValue == false && newValue == true) {
						if (!p.isInvincible().get()) {
							((ImageView) node).setImage(playerSwordImage);
						}
					} else if (oldValue == true && newValue == false) {
						if (p.isInvincible().get()) {
							((ImageView) node).setImage(playerInvincibleImage);
						} else {
							((ImageView) node).setImage(playerImage);						
						}
					}
				}
			});
			p.isInvincible().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable,
						Boolean oldValue, Boolean newValue) {
					if (oldValue == false && newValue == true) {
						((ImageView) node).setImage(playerInvincibleImage);
					} else if (oldValue == true && newValue == false) {
						if (p.hasSword().get()) {
							((ImageView) node).setImage(playerSwordImage);
						} else {
							((ImageView) node).setImage(playerImage);						
						}
					}
				}
			});
		}
		if (entity instanceof Door) {
			Door d = (Door) entity;
			d.isUnlocked().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable,
						Boolean oldValue, Boolean newValue) {
					if (oldValue == false && newValue == true) {
						((ImageView) node).setImage(doorOpenImage);
					}
				}
			});
		}

	}

	@Override
	public void addGoal(GoalComponent goals) {
	   if (goals == null) return;
	   
	   this.goal = trackGoal(goals);
	   goal.setExpanded(true);
	}
	
	public TreeItem<String> trackGoal(GoalComponent goal) {
	   TreeItem<String> node = new TreeItem<String>();
	   
	   if (goal.getSubgoals() == null) {
	      // Single goal
	      node.setValue(goal.goalProgress().get());
	      goal.goalProgress().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                  String oldValue, String newValue) {
               node.setValue(newValue);
            }
         });
	      
	   } else {
	      // Composite goal
	      node.setValue(goal.goalProgress().get());
	      goal.goalProgress().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                  String oldValue, String newValue) {
               node.setValue(newValue);
            }
         });
	      
	      for (GoalComponent gc : goal.getSubgoals()) {
	         node.getChildren().add(trackGoal(gc));
	      }
	   }
	   
	   return node;
	}
	
	/**
	 * Create a controller that can be attached to the DungeonView with all the
	 * loaded entities.
	 * @return
	 * @throws FileNotFoundException
	 */
	public DungeonController loadController() throws FileNotFoundException {
		return new DungeonController(load(), this.entities, this.goal);
	}
}
