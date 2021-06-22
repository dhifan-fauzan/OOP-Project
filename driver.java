import java.util.Scanner;

public class driver {
	
	public static void tutorial() {
		
		System.out.println("Finger Placement and Finger - Key Relation");
		System.out.println("Left Pinky used for 'a', 'q', 'z' and '1'");
		System.out.println("left Ring Finger used for 's', 'w', 'x' and '2'");
		System.out.println("Left Middle Finger used for 'e', 'd', 'c' and '3'");
		System.out.println("Left Index Finger used for 'f', 'r', 'v', 'g', 't', 'b', '4', '5'");
		System.out.println("Right Index Finger used for 'j', 'u', 'm', 'h', 'n', 'y', '7', '6'");
		System.out.println("Right Middle Finger used for 'k', 'i', ',', and '8'");
		System.out.println("Right Ring Finger used for 'l', 'o', '.', and '9'");
		System.out.println("Right Pinky used for ';', 'p', 'enter' and '0'");
		System.out.println("Both Thumbs used for 'spacebar'");
		
		
	}
	
	public static void main(String[] args) {
		
		char[] difficult2 = {'j', 'k', 'l', ';'};
		char[] difficult3 = {'a', 's', 'd', 'f', 'j', 'k', 'l'};
		char[] difficult4 = {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'q', 'w', 'e', 'r', 
							 't', 'y', 'u', 'i', 'o', 'p'};
		char[] difficult5 = {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'q', 'w', 'e', 'r', 
							 't', 'y', 'u', 'i', 'o', 'p', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
		
		PlayerProfile userProfile = new PlayerProfile();
		GameHistory userHistory = new GameHistory();
		
		userHistory.createFile();
		userProfile.createFile();
		
		userProfile.readEntireFile();
		
		Levels level1 = new Levels();
		Levels level2 = new Levels(difficult2);
		Levels level3 = new Levels(difficult3);
		Levels level4 = new Levels(difficult4);
		Levels level5 = new Levels(difficult5);
		
		Levels levelList[] = {level1, level2, level3, level4, level5};
		
		Scanner choice = new Scanner(System.in);
		int option = 0;
		
		while(option != 5) {
			
			System.out.println("Welcome to typing Simulator");
			System.out.println("1. Level Menu");
			System.out.println("2. Game Setting");
			System.out.println("3. Player Profile");
			System.out.println("4. Game History");
			System.out.println("5. Exit");
			
			System.out.print("Your Choice: ");
			option = choice.nextInt();
			System.out.println("");
			switch(option) {
			
			case 1:
				
				option = 0;
				
				while(option != 6) {
					
					System.out.println("Select A Level");
					System.out.println("1. Level 1");
					System.out.println("2. Level 2");
					System.out.println("3. Level 3");
					System.out.println("4. Level 4");
					System.out.println("5. Level 5");
					System.out.println("6. Exit");
					
					System.out.print("Your Choice: ");
					option = choice.nextInt();
					System.out.println("");
					int levelChoice = option - 1;
					
					if(option < 6) {
						levelList[levelChoice].playLevel(choice);
						userProfile.addStats(levelList[levelChoice].getCharsTyped(), levelList[levelChoice].getError(), levelList[levelChoice].getScore());
						userHistory.saveToFile("Level " + option, levelList[levelChoice].getCharsTyped(), levelList[levelChoice].getError(), levelList[levelChoice].getScore(), levelList[levelChoice].getTotalTime());
					}		
							
				}
				
				break;
			
			case 2:
				
				option = 0;
				
				while(option != 3) {
					
					System.out.println("Game Settings");
					System.out.println("Current Game Length: " + level1.getGameLength());
					System.out.println("1. Change Game Length");
					System.out.println("2. tutorial");
					System.out.println("3. Exit");
					
					System.out.print("Your Choice: ");
					option = choice.nextInt();
					System.out.println("");
					
					switch(option) {
					
						case 1:
							System.out.print("Enter Desired Game Length: ");
							int gameLength = choice.nextInt();
							
							for(int i = 0; i < levelList.length; i++) {
								levelList[i].setGameLength(gameLength);
							}
							
							System.out.print("Enter any letter to return: ");
							choice.next();
							System.out.println();
							break;
							
						case 2:
							tutorial();
							
							System.out.print("Enter any letter to return: ");
							choice.next();
							System.out.println();
							break;
					}
					
				}
				break;
				
			case 3:
				userProfile.displayPlayerProfile();
				
				System.out.print("Enter any letter to return: ");
				choice.next();
				System.out.println();
				break;
				
			case 4:
				
				option = 0;
				
				while(option != 3) {
					System.out.println("1. Read Entire Game History");
					System.out.println("2. Read Specific Game");
					System.out.println("3. Exit");
					
					System.out.print("Your Choice: ");
					option = choice.nextInt();
					System.out.println("");
					
					switch(option) {
					
						case 1:
							userHistory.readEntireFile();
							
							System.out.print("Enter any letter to return: ");
							choice.next();
							System.out.println();
							break;
							
						case 2:
							userHistory.readSpecificGame(choice);
							
							System.out.print("Enter any letter to return: ");
							choice.next();
							System.out.println();
							break;
					}
					
				}
				
				break;
				
			case 5:
				userProfile.saveToFile(userProfile.getLifeTimeChars(), userProfile.getLifeTimeErrors(), userProfile.getLifeTimeCorrectAnswers());
				System.out.println("Goodbye and thank you for playing");
				break;
				
			default:
				System.out.println("Please Enter a Valid Input");
				break;

			}
			
		}
		
		choice.close();

	}
}
