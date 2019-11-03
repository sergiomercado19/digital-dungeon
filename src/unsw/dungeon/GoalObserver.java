package unsw.dungeon;

/**
 * interface for a goal observer
 * observes a subject, updating progress on a goal accordingly
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public interface GoalObserver {
   /**
    * increase the progress of the goal's completion by 1
    */
   public void increaseProgress();
   /**
    * decrease the progress of the goal's completion by 1
    */
   public void decreaseProgress();
}
