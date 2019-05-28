import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

/** This program pulls information from a selected site (timeanddate.com), allowing the user to 
 * pick a selected city and see the temperature of said city. The program does this by
 * utilizing input / output streams, and much of the code has been lifted from examples
 * viewed in 132 classes towards the end of the semester 
 *
 * @author Nicholas Zhang
 * @version 1.0
 * @since 2019-05-28
 */

public class WebPageParser {

	public static void main(String[] args) throws IOException 
	{
		URL link = new URL("https://www.timeanddate.com/weather/");
		URLConnection connection = link.openConnection();
		BufferedReader  reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuffer pageContent = new StringBuffer();
		
		while((line = reader.readLine()) != null) 
		{
			pageContent.append(line + "\n");
		}
		
		String city = JOptionPane.showInputDialog("City?");
		int cityLocation = pageContent.indexOf(city);
		if (cityLocation == -1) 
		{
			System.out.println("NOT FOUND");
		} 
		else 
		{
			int valueStartIndex = pageContent.indexOf("rbi>", cityLocation) + 4;
			int valueEndIndex = pageContent.indexOf("&", valueStartIndex);
			String temperature = pageContent.substring(valueStartIndex, valueEndIndex);
			System.out.println(temperature);
		}
	}
}
