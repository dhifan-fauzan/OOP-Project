
public interface FileSaving {

	public void createFile();
	public void saveToFile(int chars, int error, int correct);
	void saveToFile(String level, int chars, int error, int correct, long time);
	
}
