/* This code is designed to go into a designated website (timeanddate.com) and pull temperatures
 * of a given city off of the website, displaying them as output. This code is a modified version
 * of our CMSC132 lab, utilizing information from I/O Streams. It was later slightly modified and 
 * transported to be used as part of a linux terminal
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

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
