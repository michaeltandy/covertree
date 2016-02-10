
package loehndorf;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class CoverTreeTest {
    

    public CoverTreeTest() {
    }

    @Test
    public void testBasicInsertAndGetNearest() {
        List<Entry> testData = makeTestData(1000);
        
        CoverTree<String> instance = new CoverTree();
        
        addAllConsecutively(instance, testData);
        assertEquals(1000, instance.size());
        
        testFindNearest(instance, testData, 3000);
    }
    
    @Ignore // This always fails - start with a failing test :)
    @Test
    public void testParallelInsert() throws InterruptedException {
        List<Entry> testData = makeTestData(1000);
        
        final CoverTree<String> instance = new CoverTree();
        
        ExecutorService ex = Executors.newFixedThreadPool(2);
        for (final Entry e : testData) {
            ex.execute(new Runnable() {
                @Override
                public void run() {
                    double[] point = {e.x, e.y};
                    instance.insert(e.getNote(), point);
                }
            });
        }
        ex.awaitTermination(10, TimeUnit.SECONDS);
        
        assertEquals(1000, instance.size());
        testFindNearest(instance, testData, 3000);
    }
    
    private List<Entry> makeTestData(int size) {
        Random r = new Random(1234);
        ArrayList<Entry> result = new ArrayList();
        for (int i=0 ; i<size ; i++) {
            Entry e = new Entry(10*r.nextDouble()-5, 10*r.nextDouble()-5);
            result.add(e);
        }
        return result;
    }
    
    private void addAllConsecutively(CoverTree<String> instance, List<Entry> testData) {
        for (Entry e : testData) {
            double[] point = {e.x, e.y};
            instance.insert(e.getNote(), point);
        }
    }
    
    private void testFindNearest(CoverTree<String> instance, List<Entry> testData, int iterations) {
        assertTrue(iterations>0);
        Random r = new Random(5678);
        
        for (int i=0 ; i<iterations ; i++) {
            double x = 10*r.nextDouble()-5;
            double y = 10*r.nextDouble()-5;
            
            Entry expected = simpleFindNearest(testData, x, y);
            
            double[] point = {x, y};
            String s = instance.getNearest(point);
            
            assertEquals(expected.getNote(), s);
        }
    }
    
    private Entry simpleFindNearest(List<Entry> entries, double x, double y) {
        Entry nearest = null;
        double nearestDist = Double.MAX_VALUE;
        
        for (Entry e : entries) {
            double thisDist = Math.sqrt(Math.pow(x-e.x, 2)+Math.pow(y-e.y, 2));
            if (nearest==null || thisDist < nearestDist) {
                nearest = e;
                nearestDist = thisDist;
            }
        }
        
        return nearest;
    }
    
    private class Entry {
        final double x,y;

        public Entry(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        public String getNote() {
            return String.format("%.6f,%.6f", x, y);
        }
    }
    
}