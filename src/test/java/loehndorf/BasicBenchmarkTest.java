package loehndorf;

import org.junit.Test;
import static org.junit.Assert.*;

public class BasicBenchmarkTest {

    public BasicBenchmarkTest() {
    }

    @Test
    public void testInsertBenchmarkDoesntCrash() {
        System.out.println("testInserts");
        int insertCount = 10;
        BasicBenchmark.testInserts(insertCount);
        
        
    }

}