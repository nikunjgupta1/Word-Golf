import java.util.Scanner;

public class WordGolf {
	private final Hole hole1;
	private final Hole hole2;

	/**
	 * Constructs a new WordGolf game with two holes of specified distances.
	 *
	 * @param hole1Distance the distance of the first hole
	 * @param hole2Distance the distance of the second hole
	 */
	public WordGolf(int hole1Distance, int hole2Distance) {
		this.hole1 = new Hole(hole1Distance);
		this.hole2 = new Hole(hole2Distance);
	}

	// Starts the WordGolf game.
	public void play() {
		System.out.println("Welcome to WordGolf! Your course has 2 holes.");
		Scanner scanner = new Scanner(System.in);
		int totalStrokes = 0;

		for (Hole hole : new Hole[] { hole1, hole2 }) {
			int holeStrokes = 0;
			System.out.println("Playing hole " + hole.getTargetDistance() + " yards...");

			while (!hole.isCompleted()) {
				System.out.println("Current distance: " + hole.getDistance() + " yards.");

				// Prompt user for sentence input
				System.out.print("Enter your sentence: ");
				String sentence = scanner.nextLine().trim();

				// Check if sentence is valid
				boolean isValid = isValidSentence(sentence);
				if (!isValid) {
					System.out.println("Invalid sentence entered. You lose 1 stroke.");
					holeStrokes++;
					continue;
				}

				// Check if user wants to quit
				if (sentence.equalsIgnoreCase("quit")) {
					System.out.println("You have quit the game. You incur a 100 stroke penalty.");
					holeStrokes += 100;
					continue;
				}

				// Calculate sentence yardage and add it to the hole
				int sentenceYardage = calculateSentenceYardage(sentence);
				hole.addDistance(sentenceYardage);

				// Update stroke count and output results
				holeStrokes++;
				System.out.println("Stroke #" + holeStrokes + ": " + sentence);
				System.out.println("Yardage gained: " + sentenceYardage);
				System.out.println("Total yardage: " + hole.getDistance());
				System.out.println("Distance from hole: " + hole.getRemainingDistance() + " yards.\n");
			}

			totalStrokes += holeStrokes;
			System.out.println("Hole completed in " + holeStrokes + " strokes.\n");
		}

		System.out.println("Game over. Total strokes: " + totalStrokes);
	}

	/**
	 * Checks if the sentence is valid for WordGolf.
	 *
	 * @param sentence the sentence to check
	 * @return true if the sentence is valid, false otherwise
	 */
	private boolean isValidSentence(String sentence) {
		String[] words = sentence.split(" ");
		if (words.length < 1 || words.length > 4) {
			return false;
		}
		for (String word : words) {
			if (!word.matches("[a-zA-Z]+")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * A method to calculate the yardage of a given sentence.
	 * 
	 * @param sentence, a String for which the yardage will be calculated.
	 * @return an integer representing the yardage of the sentence.
	 */
	private int calculateSentenceYardage(String sentence) {
		// Split sentence into words
		String[] words = sentence.split(" ");
		// Initialize sentence yardage to 0
		int sentenceYardage = 0;
		// Loop through each word in the sentence and calculate its yardage
		for (String word : words) {
			// Initialize word yardage to 1 and vowel encountered flag to false
			int wordYardage = 1;
			boolean vowelEncountered = false;
			// Loop through each character in the word and calculate its yardage
			for (char c : word.toCharArray()) {
				// If character is a vowel, double the word yardage and set vowel encountered
				// flag to true
				if ("aeiouAEIOU".indexOf(c) >= 0) {
					wordYardage *= 2;
					vowelEncountered = true;
				}
				// If character is a letter (but not a vowel), increment the word yardage
				else if (Character.isLetter(c)) {
					wordYardage++;
				}
			}

			// If no vowel was encountered, set the word yardage to 0
			if (!vowelEncountered) {
				wordYardage = 0;
			}

			// Add the word yardage to the sentence yardage
			sentenceYardage += wordYardage;
		}
		// Return the final sentence yardage
		return sentenceYardage;
	}

	// The main method that creates a new WordGolf game and starts playing.
	public static void main(String[] args) {
		WordGolf game = new WordGolf(75, 125);
		game.play();
	}
}
