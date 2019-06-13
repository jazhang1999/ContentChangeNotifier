# ContentChangeNotifier Description
Idea: Check any webpage for when it is updated, and be able to pull information off said webpage, all through a terminal without having to search on the web to get to that webpage

# Program Organization
As previously mentioned, there entirety of phase one can be run from the select main programs:
* MainExecutor.java - contains the main method that makes everything work
* PageParser.java - takes a URL and find when it was last updated (limited - will only definitively for wikipedia articles)
* WriteRecord.java - saves the last updated date in a .json file, and determines whether an email needs to be sent (only when there is a difference between the saved message and the new message that both point to the same URL)
* EmailSender.java - sends the email to the user. Keep in mind that both the sender and the recipient must have their restrictions turned off on this account or else exceptions will be thrown

![](Design.jpg)

# Resources
* javax.mail.jar and activation.jar - resources needed to send the email 
* json-simple-1.1.1.jar - resource used to implement .json file reading and writing


# How to build and run
There are a couple things to remember when modifying or using this code:
* The main programs are MainExecutor.java, PageParser.java, EmailSender.java, and WriteRecord.java
* You will also need all the .jar files to run 
* There also needs to be an existing .json file named record.json (can be empty object [{}] or non-empty) - the WriteRecord.java static methods will use this file as a base to either modify or create new entries to be referenced in the future
* To compile the program (in linux): ```javac -cp \* MainExecutor.java PageParser.java EmailSender.java WriteRecord.java```
* To run the program (in linux): ```java -cp .:\* MainExecutor```
* For more information, please see https://stackoverflow.com/questions/9395207/how-to-include-jar-files-with-java-file-and-compile-in-command-prompt

