import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerProfile implements FileSaving, FileReading{
	
	private int lifeTimeChars = 0;
	private int lifeTimeErrors = 0;
	private int lifeTimeCorrectAnswers = 0;
	private String fileName = "playerProfileFile.txt";
	
	public int getLifeTimeChars() {
		return lifeTimeChars;
	}

	public int getLifeTimeErrors() {
		return lifeTimeErrors;
	}

	public int getLifeTimeCorrectAnswers() {
		return lifeTimeCorrectAnswers;
	}

	public void addStats(int lifetimeChars, int lifeTimeErrors, int lifeTimeCorrectAnswers) {
		
		this.lifeTimeErrors += lifeTimeErrors;
		this.lifeTimeCorrectAnswers += lifeTimeCorrectAnswers;
		this.lifeTimeChars += lifetimeChars;
	}
	
	public void displayPlayerProfile() {
		
		System.out.println("\nWelcome To Your Profile");
		System.out.println("Your life time characters typed: " + this.lifeTimeChars);
		System.out.println("Your life time Errors: " + this.lifeTimeErrors);
		System.out.println("Your life time Correct Answers: " + this.lifeTimeCorrectAnswers);
		System.out.println("Your Accuracy is: " + calculateAccuracy());
	}
	
	public float calculateAccuracy() {
		
		float accuracy = (((float) this.lifeTimeCorrectAnswers - (float) this.lifeTimeChars)/((float) this.lifeTimeCorrectAnswers))*100;
		
		float finalAccuracy = 100 - Math.abs(accuracy);

		return finalAccuracy;
		
	}

	@Override
	public void readEntireFile() {
		
		this.lifeTimeChars = stringToNumber(this.readSpecificLine(1));
		this.lifeTimeErrors = stringToNumber(this.readSpecificLine(2));
		this.lifeTimeCorrectAnswers = stringToNumber(this.readSpecificLine(3));
		
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

	public int stringToNumber(String text) {
		
		Pattern numberRegex = Pattern.compile("\\d+");
		Matcher stringToSearch = numberRegex.matcher(text);
		
		int result = 0;
		
		if(stringToSearch.find()) {
			
			result = Integer.parseInt(stringToSearch.group(0)); 
		}
		
		else {
			
			System.out.println("No Number Found in String");
		}
		
		return result;
	}

	@Override
	public void saveToFile(int chars, int error, int correct) {
		
		FileWriter myWriter;
		
		try {
			
			myWriter = new FileWriter(this.fileName, false);
			myWriter.write("PLayer Profile\r\n");
			myWriter.write("Life Time Characters Typed: " + chars + "\r\n");
			myWriter.write("Life Time Errors Made: " + error + "\r\n");
			myWriter.write("Life Time Total Correct Answer: " + correct + "\r\n");
			myWriter.write("Your Accuracy is: " + this.calculateAccuracy());
			myWriter.close();
			
		}
		
		catch (IOException e) {
			
			System.out.println("An error occurred.");
            e.printStackTrace();
		}
	}

	@Override
	public void saveToFile(String level, int chars, int error, int correct, long time) {
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

	@Override
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
	

}
