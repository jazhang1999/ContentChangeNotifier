import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WriteRecord
{
	@SuppressWarnings("unchecked")

	//Static method that writes the updated information to the script, if and only if there is 
	//a change in the updates of the passed in website. If not, return false and do not
	//overwrite (no changes made)
	static boolean record(String message, String website)
	{
		// JSON array that will hold all of the objects (both old and new)
		JSONArray updatedList = new JSONArray();

		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		//Opens up the previous json file (always named recordData), meant to be rewritten
		try (FileReader reader = new FileReader("recordData.json"))
		{
			//Read JSON file
			Object obj = jsonParser.parse(reader);

			//All of the old objects from the previous recordData.json
			JSONArray recordList = (JSONArray) obj;

			JSONObject updateDetails = new JSONObject((JSONObject) recordList.get(0));

			//Checks to see if the update dates (formatted the same) are not the same
			//-will only update if they are
			if (!message.equals(updateDetails.get(website)))
			{
				updateDetails.put(website, message);
				updatedList.add(updateDetails);
				//Write JSON file - overwrites it as well
				try (FileWriter file = new FileWriter("recordData.json"))
				{
					file.write(updatedList.toJSONString());
					file.flush();
					return true;
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
}

