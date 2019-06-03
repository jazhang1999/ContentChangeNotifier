import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;
import java.util.*;

public class ExamplePageParserExperiment 
{
        //Searches Wikipedia to see when it was last updated
      	public static void main(String[] args) throws IOException
        {
                // Asks for search topic, and plugs the result into Wikipeda. 
                String url = JOptionPane.showInputDialog("Search topic?");
                URL link = new URL("https://www.wikipedia.org/wiki/" + url);

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

                if (piece.equals(""))
		{
			System.out.println("Not found");
		}
		else
		{
			System.out.println(piece);			
		}
        }
}

