## How to run this project 


## How to contribute to the project

	- git clone https://github.com/nandhini21/adbms.git 
	- Open project in Eclipse 
	- Run Maven to load project dependencies 
	- Update testing files at test/ and add more tested code 
	- Commit to local repository ```git commit -am 'Message'```
	- Push code to master remote branch as ```git push origin master```

## Features implemented in this project 
	
	- Read command of type "SELECT * FROM PERSON WHERE AGE > 20" 
	- Create index on database 
	- Use less than 50MB memory 
	- Print response of _*average income for all age groups*_ in the PERSON table provided
	- Parse Data, there is no delimiter on data 
	
## How to read a massive file without exausting the memory 
	
	- Streaming with Apache Commons IO 
		- Conservative memory wise
		- CONS: loads external dependency 
		
		- To read a particular line :: needed for sparse index 
			```
				# http://stackoverflow.com/a/14218145/132610
				String line = FileUtils.readLines(theFile).get(lineNumber);
			```
		- 
		```
			#Streaming a file using Commons IO utility - http://www.baeldung.com/java-read-lines-large-file 
			LineIterator it = FileUtils.lineIterator(theFile, "UTF-8");
			try {
			    while (it.hasNext()) {
			        String line = it.nextLine();
			        // do something with line
			    }
			} finally {
			    LineIterator.closeQuietly(it);
			}
		```
		
		```
		#Using Scanner to parse a file - http://www.javapractices.com/topic/TopicAction.do?Id=42
		 protected void processLine(String aLine){
			    //use a second Scanner to parse the content of each line 
			    Scanner scanner = new Scanner(aLine);
			    scanner.useDelimiter("=");
			    if (scanner.hasNext()){
			      //assumes the line has a certain structure
			      String name = scanner.next();
			      String value = scanner.next();
			      log("Name is : " + quote(name.trim()) + ", and Value is : " + quote(value.trim()));
			    }
			    else {
			      log("Empty or invalid line. Unable to process.");
			    }
		  }
		```
	- The program uses one single string, in one line of type: 932512143mhSznrWOVy     gOPhwPenh      870000044954201,jXZNStreet,Montreal,QC,Canada
	- Two rows: 
	- http://www.baeldung.com/java-read-lines-large-file
	- http://nadeausoftware.com/articles/2008/02/java_tip_how_read_files_quickly
	- https://www.quora.com/What-is-the-fastest-way-to-read-a-large-file-in-Java-3-4gb-line-by-line
		
## Initialization commands [DEPRECATED]	

```sh
	git init
	git add README.md
	git commit -m "first commit"
	git remote add origin https://github.com/nandhini21/adbms.git
	git push -u origin master
	git remote add origin https://github.com/nandhini21/adbms.git
	git push -u origin master
```