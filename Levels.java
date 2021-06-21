import java.util.Random;
import java.util.Scanner;

public class Levels {

	private int gameLength = 10;
	private int score = 0;
	private int error = 0;
	private int charsTyped = 0;
	private long totalTime = 0;
	private char[] availableChars = {'a', 's', 'd', 'f'};
	private Random randomizer = new Random();
	
	public Levels() {
		
	}
	
	public Levels(char[] difficulty) {
		
		this.availableChars = difficulty;
	}
	
	public Levels(int gameLength) {
		
		this.gameLength = gameLength;
	}
	
	public Levels(int gameLength, char[] difficulty) {
		
		this.gameLength = gameLength;
		this.availableChars = difficulty;
	}
	
	public void playLevel(Scanner userAnswer) {
		
		this.score = 0;
		this.error = 0;
		char[] charsToPress = creatingCharsArray();
		
		System.out.print("Enter any letter to start: ");
		userAnswer.next().charAt(0);

		long startTime = System.currentTimeMillis();
		
		while(this.score < this.gameLength) {
			
			System.out.print("You have to enter " + charsToPress[score] + ": ");
			
			char userChar = userAnswer.next().charAt(0);
			
			if(userChar == charsToPress[score]) {
				this.score++;
			}
			
			else {
				this.error++;
			}
			
		}
		
		long endTime = System.currentTimeMillis();
		
		totalTime = endTime - startTime;
		charsTyped = this.score + this.error;
		
		System.out.println("\nSession Stats: ");
		System.out.println("You typed: " + charsTyped + " chars");
		System.out.println("Errors: " + this.error);
		System.out.println("That took " + (totalTime/1000) + " seconds");
		
		System.out.print("Enter any letter to Continue: ");
		userAnswer.next();
		System.out.println();
		
	}
	 
	private char[] creatingCharsArray() {
		
		char[] gameChar = new char[this.gameLength];
		
		for(int i = 0; i < this.gameLength; i++) {
			
			int randomNumber = this.randomizer.nextInt(this.availableChars.length);
			gameChar[i] = this.availableChars[randomNumber];
			
		}
		
		return gameChar;
		
	}

	public int getScore() {
		return score;
	}

	public int getError() {
		return error;
	}
	
	public int getCharsTyped() {
		return charsTyped;
	}
	
	public long getTotalTime() {
		return totalTime;
	}
	
	public void setGameLength(int gameLength) {
		
		this.gameLength = gameLength;
	}
	
	public int getGameLength() {
		
		return gameLength;
	}
}
