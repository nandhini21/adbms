	/**
	 * @todo --- add index in at this section.
	 * @todo --- only read sequentially if there is no index
	 * @todo --- build index on first read, or at initialization
	 * @todo SELECT from a line - find all 18 years old people - GOTO INDEX find
	 *       age 18 key ( INDEX[age[18]] ) - lines in index is now [l1,l3, l10]
	 *       - lines are sorted by line number - move sequentially in stream
	 *       using FileChannel.position( line# * TUPPLE_SIZE ) for(size in
	 *       [l1,l3, l10]) read from FileChannel.position( size * TUPPLE_SIZE )
	 * 
	 * @todo - how to work with BLOCK and Buffer/Tupple Moving back and forth in
	 *       a stream to read data <code>	
	 * 	FileInputStream fis = new FileInputStream("/etc/hosts");
	 * 	FileChannel     fc = fis.getChannel();
	 *  				fc.position(100);// set the file pointer to byte position 100;
	 * </code>
	 *       <code>
	 * 		public int read(byte[] b, int off, int len) throws IOException
	 * </code> buffer - the buffer into which the data is read. off - the start
	 *       offset in the destination array buffer len - the maximum number of
	 *       bytes read.
	public void execute() throws ExitException {
		Person person = null;
		int reads = 0;
		int irecords = 0;
		try {
			// @todo read and Establish the index - as the first search hits
			memoryManager.calculateFileReadPass(rstream);
			// Array Size should always be smaller than Integer.MAX_VALUE
			byte memory[] = new byte[Configuration.MEMORY_SIZE];//can be 5MB or 2MB
			byte block[] = new byte[Configuration.BLOCK_SIZE];//is 4096B
			byte buffer[] = new byte[Configuration.TUPLE_SIZE];//is 100KB
			irecords = 0; 
			//while ((reads = rstream.read(memory, 0, memory.length)) != -1) {
				//ByteArrayInputStream bais = new ByteArrayInputStream(memory);	
				//while((bais.read(buffer, 0, buffer.length)) != -1 ){
				while((rstream.read(buffer, 0, buffer.length)) != -1 ){
					System.out.println(String.format(" %s ", new String(buffer)));
					person = Parser.parse(buffer, 0);
					if (query.getAge() > -1 && person.getAge() == query.getAge()) {
						memoryManager.increment();
						//System.out.println(String.format(" %s ", person.toString()));
					} else if (query.getMax() > -1 && query.getMin() > -1) {
						if (person.getAge() <= query.getMax() && person.getAge() >= query.getMin()) {
							memoryManager.increment();
							//System.out.println(String.format(" %s ", person.toString()));
						}
					}
					//off = off + buffer.length;
					irecords = irecords + 1;
				}
				//System.out.println( String.format(" OFF %d BUFFER LEN : %d MEMORY %d RECORDS %d %s", off, buffer.length, memory.length, irecords, new String(buffer) ));
				System.out.println( String.format(" RECORDS %d ", irecords ));
			//}
			// buffer = null;
		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading file " + ioe);
		} finally {
			// close the streams using close method
			try {
				if (rstream != null) {
					rstream.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error while closing stream: " + ioe);
			}
		}
	}**/