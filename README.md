### How to run this project 

	- The main program is Client.java src/[ca.concordia.adbms.Client.java]
	- Using Eclipse, right click on the file and choose "Run as" > "Java Application"
	- To select users ```sh select -age NUMBER or ( -max NUMBER -min NUMBER)```
	
### How to change project configuration 

	- Configuration file is Configuration.java src/[ca.concordia.adbms.config.Configuration.java]
	- MAIN_MEMORY can either be changed to 
		- 5MB (52428800 = 5 * 1024 * 1024) Bytes 
		- or 2MB (52428800 = 2 * 1024 * 1024) Bytes

### How to run test 

	- This project uses JUnit for Unit testing
	- Unit testing classes(DataTronTest and DataTronTestSuite) are located in test/[ca.concordia.test.adbms]
	- To run uni test, Select + Right Click > DataTronTestSuite.java[ca.concordia.test.adbms] > Run as > JUnit Test
	
### How to contribute to the project
	
	- Clone the project in a directory of your choice, using:
		- ```sh git clone https://github.com/nandhini21/adbms.git ```
	- Open project in Eclipse 
	- Run Maven to update project dependencies 
	- Update Unit testing as you add more code. 
		- ```sh git commit -am 'Message'```
	- Push code to master remote branch
		- ```sh git push origin master```

### Features
	
	- Parse Data, there is no delimiter on data 
	- Find a group of people using 
		- ```sh select -age NUMBER or (-max NUMBER -min NUMBER)```
	- Create index on database file
	- Use 5MB(and 2MB) memory to perform search operations  
	- Print _*average income for all age groups*_ 
	- Print _*memory usage*_
	- Print _*passes*_ required to read data

### File locations 
	
	- Directory /data has database data
	- For backup purposes, data/person is kept
	- Index files are stored at data/db/index
	- Database file are stored at data/db/person.txt
	
### How to read a massive file without exhausting the memory 
	
	- Streaming with Apache Commons IO 
		- Conservative memory wise
		- CONS: loads external dependency 

### References 
		
- [Java: Read large file lines](http://www.baeldung.com/java-read-lines-large-file)
- [Tips to read file quickly](http://nadeausoftware.com/articles/2008/02/java_tip_how_read_files_quickly)
- [Quora: Fastest way to read a file](https://www.quora.com/What-is-the-fastest-way-to-read-a-large-file-in-Java-3-4gb-line-by-line)
- [](https://dev.mysql.com/doc/refman/5.1/en/innodb-table-and-index.html)
- [How database indexing works](http://stackoverflow.com/questions/1108/how-does-database-indexing-work)
- [How to navigate InputStream - to search for data at a given point in a Stream](http://stackoverflow.com/a/15393924/132610)
- [What is the maximum size of the array in Java?](https://www.quora.com/What-is-the-maximum-size-of-the-array-in-Java)
- [Byte array that can read more than 1GB of address space](http://stackoverflow.com/a/5532716/132610)
