# ContentChangeNotifier
Idea: Check any webpage for when it is updated, and be able to pull information off said webpage, all through a terminal without having to search on the web to get to that webpage

## Phase 1: Check for updates
This should only notify the user when an update to the page has been made. Often, this is as simple as checking to see if “last updated” section has a new date, or by checking in the source code to see if changes have been made

## Phase 2: What changes
This should check that if there are changes, what has changed. The user will get information on what has changed (all of it). Depending on what kind of change we want to see, then we will find some applicable way to present all of it (solid dump)

## Phase 3: Limitations on information
Often, a use does not want to see every little change, so probably the hardest step would be to find out how to limit the amount of information being shown to be most relevant to said user. Of course, this will be tailored for each type of site, so we will most likely start specific and get general over time

## Additional Requests:
* This program runs daily, or will at least specify what it has found over the span of 24 hours
* An email will be sent to the proper address detailing the findings / results of said program (most likely will be sent at 11:59 PM, and reset at 12:00 AM)
* Either a mod page (video game) or a page with solid text (wiki or other text presentation)

## Design Ideas:
* Have to be able to pull things out of a webpage given a url and be able to store it into a file
* Open up that file that was stored, and compare it with the new contents pulled from the web
* Send findings through an email if there's a change of contents (newly pulled vs. stored)

# Updates (6/10/19)
Phase 1 is mostly completed, with some additional features:
* Will be able to pull 'last edited' dates from certain pages (official articles, etc.)
* Will only send an email if there has been an actual change

# Organization Notes (6/10/19)
There are a couple things to remember when modifying or using this code:
* The main programs are MainExecutor.java, PageParser.java, EmailSender.java, and WriteRecord.java
* You will also need all the .jar files to run and an empty .json file to work (at least in the beginning)
* To compile the program (in linux terminal): javac -cp \* __________ (Modified code)
* To run the program (in linux terminal): java -cp .:\* MainExecutor

# Program Organization
As previously mentioned, there entirety of phase one can be run from the select main programs:
* MainExecutor.java - contains the main method that makes everything work
* PageParser.java - takes a URL and find when it was last updated (limited - will only definitively for wikipedia articles)
* WriteREcord.java - saves the last updated date in a .json file, and determines whether an email needs to be sent (only when there is a difference between the saved message and the new message that both point to the same URL)
* EmailSender.java - sends the email to the user. Keep in mind that both the sender and the recipient must have their restrictions turned off on this account or else exceptions will be thrown

#Auxilluary files
* All the .jar files included in ContentChangeNotifyer must be included when going to compile, hence the complicated javac command
* There also needs to be an existing .json file named record.json (can be empty object [{}] or non-empty) - the WriteRecord.java static methods will use this file as a base to either modify or create new entries to be referenced in the future



