package unsw.dungeon;

/**
 * interface for a goal subject
 * updates its observers based on the state of the subject in achieving progress towards some goal
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public interface GoalSubject {
   /**
    * add a new observer to monitor the progress of the subject
    * @param o
    */
   public void addObserver(GoalObserver o);
   /**
    * remmove an observer from the subject
    * @param o
    */
   public void removeObserver(GoalObserver o);
   /**
    * notify all observers that the progress of the goal's completion has increased by 1
    */
   public void notifyObserversOfIncrease();
   /**
    * notify all observers that the progress of the goal's completion has decreased by 1
    */
   public void notifyObserversOfDecrease();
}
