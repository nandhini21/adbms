package ca.concordia.test.adbms;

import ca.concordia.adbms.ExitException;
import ca.concordia.adbms.IndexManager;
import ca.concordia.adbms.MemoryManager;
import ca.concordia.adbms.SelectQuery;
import ca.concordia.adbms.SelectTask;
import ca.concordia.adbms.Task;
import ca.concordia.adbms.conf.Configuration;
import ca.concordia.adbms.util.Parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DataTronTest {
    
    IndexManager indexManager;
    MemoryManager memoryManager;

    @Before
    public void setUp() throws FileNotFoundException {
        memoryManager = new MemoryManager();
        indexManager = new IndexManager();
        indexManager.createIndex(new FileInputStream(new File(Configuration.PERSON_FILE)));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConfiguration() {
        assertEquals(Configuration.TUPLE_SIZE, 100);
        assertEquals(Configuration.BLOCK_SIZE, (4 * 1024));
        assertTrue(Configuration.MEMORY_SIZE == (5 * 1024 * 1024) || Configuration.MEMORY_SIZE == (2 * 1024 * 1024));
        assertTrue(Configuration.PERSON_TABLE == "person.txt");
        assertTrue(Configuration.PERSON_FILE == "data/db/person.txt");
    }

    @Test
    public void testCanInterpretInputCommand() {
        assertEquals(Parser.parseCommand("exit"), "exit");
        assertEquals(Parser.parseCommand("select"), "select");

        SelectQuery query = Parser.parseSelect("select -max 30 -min 20");
        assertEquals(query.getMax(), 30);//min
        assertEquals(query.getMin(), 20);//max
        assertEquals(query.getAge(), -1);//age

        query = Parser.parseSelect("select -age 30 -min 20 -max 40");
        assertEquals(query.getMax(), 40);//min
        assertEquals(query.getMin(), 20);//max
        assertEquals(query.getAge(), 30);//age

        query = Parser.parseSelect("select -age 12");
        assertEquals(query.getMax(), -1);//min
        assertEquals(query.getMin(), -1);//max
        assertEquals(query.getAge(), 12);//age

        query = Parser.parseSelect("select");
        assertEquals(query.getMax(), -1);//min
        assertEquals(query.getMin(), -1);//max
        assertEquals(query.getAge(), -1);//age

    }

    @Test
    public void testCanFindTeenagers() throws ExitException, IOException {

        Task task = new SelectTask("select -age 19");
        task.setMemoryManager(memoryManager);
        task.setIndexManager(indexManager);
        task.execute();
        assertEquals(indexManager.getIndexKeys(19).length, 119);
        assertEquals(task.getMemoryManager().getResultSize(), 119);

        task = new SelectTask("select -age 18");
        task.setMemoryManager(memoryManager);
        task.setIndexManager(indexManager);
        task.execute();
        assertEquals(indexManager.getIndexKeys(18).length, 128);
        assertEquals(task.getMemoryManager().getResultSize(), 128);
    }

    @Test
    public void testCanParseIndexFileLine() {
        String line = "30 36 231 240 311 403 760 822 845 866 961 1015 1062 1102 1116 1261 1304 1307 1366 1414 1433 1466 1603 1636 1872 1880 1962 2263 2317 2520 2577 2586 2721 2749 2916 2941 2973 3004 3073 3356 3388 3504 3711 3791 3931 4142 4175 4347 4383 4389 4391 4403 4438 4465 4486 4606 4786 4874 4893 4998 5034 5040 5124 5132 5151 5242 5546 5677 5684 5778 5857 5918 5945 6071 6127 6154 6168 6188 6195 6292 6300 6331 6401 6407 6434 6443 6648 6781 6800 6806 7012 7035 7381 7440 7487 7528 7867 7896 8045 8050 8062 8137 8339 8418 8508 8619 8757 8833 8848 9021 9037 9100 9119 9190 9252 9270 9398 9431 9492 9526 9598 9620 9663 9680 9686 9698 9724 9777 30 36 231 240 311 403 760 822 845 866 961 1015 1062 1102 1116 1261 1304 1307 1366 1414 1433 1466 1603 1636 1872 1880 1962 2263 2317 2520 2577 2586 2721 2749 2916 2941 2973 3004 3073 3356 3388 3504 3711 3791 3931 4142 4175 4347 4383 4389 4391 4403 4438 4465 4486 4606 4786 4874 4893 4998 5034 5040 5124 5132 5151 5242 5546 5677 5684 5778 5857 5918 5945 6071 6127 6154 6168 6188 6195 6292 6300 6331 6401 6407 6434 6443 6648 6781 6800 6806 7012 7035 7381 7440 7487 7528 7867 7896 8045 8050 8062 8137 8339 8418 8508 8619 8757 8833 8848 9021 9037 9100 9119 9190 9252 9270 9398 9431 9492 9526 9598 9620 9663 9680 9686 9698 9724 9777";
        Integer[] indexLines = Parser.parseIndexPointer(line);
        assertEquals(indexLines.length, 128);
    }

    @Test
    public void testCanFindSeniors() throws ExitException, IOException {

        Task task = new SelectTask("select -age 82");
        task.setMemoryManager(memoryManager);
        task.setIndexManager(indexManager);
        task.execute();
        assertEquals(indexManager.getIndexKeys(82).length, 95);
        assertEquals(task.getMemoryManager().getResultSize(), 95);

        task = new SelectTask("select -age 87");
        task.setMemoryManager(memoryManager);
        task.setIndexManager(indexManager);
        task.execute();
        assertEquals(indexManager.getIndexKeys(87).length, 115);
        assertEquals(task.getMemoryManager().getResultSize(), 115);
    }

    @Test
    public void testCanFindMiddleAged() throws ExitException, IOException {
        Task task = new SelectTask("select -age 66");
        task.setMemoryManager(memoryManager);
        task.setIndexManager(indexManager);
        task.execute();
        assertEquals(task.getMemoryManager().getResultSize(), 135);

        task = new SelectTask("select -age 53");
        task.setMemoryManager(memoryManager);
        task.setIndexManager(indexManager);
        task.execute();
        assertEquals(task.getMemoryManager().getResultSize(), 136);
    }

    @Test
    public void testAverageIncome() {
		//for all age groups
        //
    }

    @Test
    public void testCanCountPassesByMemoryConfiguration() throws IOException, ExitException {
        Task task = new SelectTask("select -age 53");
        task.setMemoryManager(memoryManager);
        task.setIndexManager(indexManager);
        task.execute();
        assertEquals(task.getMemoryManager().getResultSize(), 136);
        //for 4KB block(read once) count in following scenarios
        assertEquals(task.getMemoryManager().getTableFileSize(), 1000000);
        //Using 2MB for main memory
        if (Configuration.MEMORY_SIZE == 2097152) {
            assertEquals(task.getMemoryManager().getFileReadPasses(), 1953);
        } else {
            //Using 5MB for main memory 
            assertEquals(task.getMemoryManager().getFileReadPasses(), 781);
        }
    }

    @Test
    public void testCanNotExceedPasses() throws IOException, ExitException {
        Task task = new SelectTask("select -age 53");
        task.setMemoryManager(memoryManager);
        task.setIndexManager(indexManager);
        task.execute();
    }

}
