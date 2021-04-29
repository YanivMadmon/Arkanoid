/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.util.List;


import java.util.ArrayList;

/**
 * .
 * <p>
 * A class of the Game Environment
 */
public class GameEnvironment {

    private List<Collidable> list;

    /**
     * .
     * Constructor
     */
    public GameEnvironment() {
           this.list = new ArrayList<Collidable>();
       }

    /**
     * .
     * Add the given collidable to the environment
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
       }

    /**
     * .
     * Remove the given collidable from the environment
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
           if (this.list.contains(c)) {
               this.list.remove(c);
           }
       }


    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the Line
     * @return CollisionInfo of the closet collision point
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
           if (this.list == null) {
               return null;
           }
           Point closePoint = null;
           int index = -1;
           for (int i = 0; i < this.list.size(); i++) {
               Point p = trajectory.closestIntersectionToStartOfLine(
                       list.get(i).getCollisionRectangle());
               if (p != null) {
                   if (closePoint == null) {
                       closePoint = p;
                       index = i;
                   } else {
                       if (p.distance(trajectory.start()) < closePoint.distance(trajectory.start())) {
                           closePoint = p;
                           index = i;
                       }

                   }
               }
           }
           if (index == -1) {
               return null;
           }
           CollisionInfo info = new CollisionInfo(closePoint, this.list.get(index));
           return info;
        }

    /**
     * .
     * Check if the balls accidently penetrated into a collidable,
     * and fix the problem without letting him panatrating
     *
     * @param ball - the Ball
     */
    public void penetration(Ball ball) {
           if (!(this.list.isEmpty())) {
           for (int i = 0; i < this.list.size(); i++) {

               int temp = this.list.get(i).getCollisionRectangle().hasPoint(ball.getCenter());
               if (temp != 0) {
                   double dx = ball.getVelocity().getDx();
                   double dy = ball.getVelocity().getDy();

                   if (temp == 1) {
                       dx = -Math.abs(dx);
                   }
                   if (temp == 2) {
                       dx = Math.abs(dx);
                   }
                   if (temp == 3) {
                       dy = Math.abs(dy);
                   }
                   if (temp == 4) {
                       dy = -Math.abs(dy);
                   }

                   Velocity v = new Velocity(dx, dy);
                   ball.setVelocity(v);
               }
           }
           }
       }
}