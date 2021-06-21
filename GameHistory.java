import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class GameHistory implements FileSaving, FileReading{
	
	private String fileName = "GameHistory.txt";
	
	public void createFile() {
		
		File myFile = new File(this.fileName);
		
		if(!myFile.exists()) {
			try {
				myFile.createNewFile(); 
				System.out.println("File created: " + myFile.getName());
					 
				} 
		
			catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		
		}
		
		else {
			System.out.println("File already exists.");
			}
		
	}
	
	@Override
	public void readEntireFile() {
		
		File myFile = new File(this.fileName);
		
		try {
			
			Scanner fileReader = new Scanner(myFile);
			
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				System.out.println(line);
			}
			
			fileReader.close();
		}
		
		catch (FileNotFoundException e){
			
			System.out.println("File Not Found");
			e.printStackTrace();
		}
	}

	@Override
	public String readSpecificLine(int n) {
		
		String line = "";
		
		try {
			line = Files.readAllLines(Paths.get(this.fileName)).get(n);
			
		}
		
		catch (IOException e ) {
			System.out.print(e);
		}
		
		return line;
	}
	
	@Override
	public long countLines() {
		
		Path myPath = Paths.get(this.fileName);
		
		long lines = 0;
		
		try {
			
			lines = Files.lines(myPath).count();
		}
		
		catch (IOException e ) {
			e.printStackTrace();
			
		}
		
		return lines;
	}
	
	public void readSpecificGame(Scanner userChoice) {
		
		int timesPlayed = (int)this.countLines()/6;
		System.out.println("Times Played: " + timesPlayed);

		int option = 0;

		System.out.print("Your Choice: ");
		option = userChoice.nextInt() - 1;
		
		int startingPoint = (option) + (4 * option );
		int endingPoint = startingPoint + 5;

		for( int i = startingPoint; i < endingPoint; i++) {
			System.out.println(this.readSpecificLine(i));
		}

		
	}

	@Override
	public void saveToFile(int chars, int error, int correct) {
	}

	@Override
	public void saveToFile(String level, int chars, int error, int correct, long time) {
		FileWriter myWriter;
		
		try {
			
			myWriter = new FileWriter(this.fileName, true);
			myWriter.write(level + "\r\n");
			myWriter.write("Chars Typed: " + chars + "\r\n");
			myWriter.write("Errors Made: " + error + "\r\n");
			myWriter.write("Total Correct Answer: " + correct + "\r\n");
			myWriter.write("Total Time Taken: " + (time/1000) + " Seconds\r\n");
			myWriter.close();
			
		}
		
		catch (IOException e) {
			
			System.out.println("An error occurred.");
            e.printStackTrace();
		}
		
	}
	
}
