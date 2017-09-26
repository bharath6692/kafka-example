import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class Test {
	private static final String FILENAME = "/home/bharath/Documents/output1.txt";

	public static void main(String[] args) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = "This is the content to write into file\n";
			
			try {
			    Files.write(Paths.get(FILENAME), content.getBytes(), StandardOpenOption.APPEND);
			    Files.write(Paths.get(FILENAME), content.getBytes(), StandardOpenOption.APPEND);
			}catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
			
			/*fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.newLine();
			bw.write(content);
*/
			System.out.println("Done");

		} finally {

			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
}
