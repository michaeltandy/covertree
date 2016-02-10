package loehndorf;

import java.util.Random;

public class BasicBenchmark {

    public static void main(String[] args) {

        testInserts(10000);
        testInserts(1000000);

    }

    public static void testInserts(int insertCount) {
        CoverTree<String> ct = new CoverTree();
        Random r = new Random();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < insertCount; i++) {
            double lat = randBetween(r, 49.8, 58.7);
            double lon = randBetween(r, -7.58, 1.97);
            double[] point = {lat, lon};

            String note = String.format("%.6f,%.6f", lat, lon);
            ct.insert(note, point);
        }
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Inserted " + insertCount + " records in " + duration + "ms");

    }

    private static double randBetween(Random r, double a, double b) {
        return a + (b - a) * r.nextDouble();
    }
}
