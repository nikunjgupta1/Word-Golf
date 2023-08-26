public class Hole {
	/**
	 * @ Nikunj Gupta The Hole class represents a single hole in the WordGolf game.
	 * It keeps track of the target distance, the current distance, and the
	 * remaining distance.
	 */

	private final int targetDistance;

	// The current distance from the hole.
	private int distance;

	// Creates a new Hole object with the specified target distance.
	// @param targetDistance the target distance for the hole
	public Hole(int targetDistance) {
		this.targetDistance = targetDistance;
		this.distance = 0;
	}

	// @return true if the current distance is equal to the target distance, false
	// otherwise
	public boolean isCompleted() {
		return distance == targetDistance;
	}

	// @return the target distance for the hole
	public int getTargetDistance() {
		return targetDistance;
	}

	// @return the current distance from the hole
	public int getDistance() {
		return distance;
	}

	/**
	 * Adds the specified number of yards to the current distance. If the new
	 * distance exceeds the target distance, the current distance is set to the
	 * target distance.
	 * 
	 * @param yards the number of yards to add to the current distance
	 */
	public void addDistance(int yards) {
		int newDistance = distance + yards;
		if (newDistance > targetDistance) {
			distance = targetDistance - (newDistance - targetDistance);
		} else {
			distance = newDistance;
		}
		if (distance < 0) {
			distance = -distance;
		}
	}

	// @return the remaining distance from the hole
	public int getRemainingDistance() {
		return targetDistance - distance;

	}
}
