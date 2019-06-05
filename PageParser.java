import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;
import java.util.*;

public class PageParser 
{
        //Searches Wikipedia to see when it was last updated
      	static String getMessage(String urlToSearch) throws IOException
        {
                // Asks for search topic, and plugs the result into Wikipeda. 
                URL link = new URL(urlToSearch);

                // Establishes connection to the created URL
                URLConnection connection = link.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                // Reads through the source page of the Wikipedia article
                StringBuffer pageContent = new StringBuffer();
                String line;
		String piece = "";
                while((line = reader.readLine()) != null)
                {
                        if (line.contains("last edited"))
			{
				piece = line.substring(line.indexOf(">"));
				piece = piece.substring(0, piece.indexOf("<") + 1);
				piece = piece.substring(1, piece.length() - 1);
				break;
			}
                }
		System.out.println("Message found: " + piece);
		return piece;
        }
}

