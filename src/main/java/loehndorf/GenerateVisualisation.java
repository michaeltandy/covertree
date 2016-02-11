package loehndorf;

import java.util.Random;

public class GenerateVisualisation {

    public static void main(String[] args) {
        visualise(10);
    }

    public static void visualise(int insertCount) {
        CoverTree<String> ct = new CoverTree();
        Random r = new Random(1234);
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for (int i=0 ; i<insertCount ; i++) {
            double lat = randBetween(r, 0.25,0.75);
            double lon = randBetween(r, 0.25,0.75);
            double[] point = {lat, lon};
            
            String note = String.format("%.3f,%.3f", lat,lon);
            ct.insert(note, point);
            
            System.out.println("\n\nmaxLevel:"+ct.maxLevel() + ", minLevel:" + ct.minLevel() + ", size:" + ct.size());
            sb.append(ct.rootNode.toJson());
            sb.append(",\n");
        }
        
        sb.delete(sb.length()-2, sb.length()-1);
        sb.append("]");
        System.out.println(sb.toString());
    }

    private static double randBetween(Random r, double a, double b) {
        return a + (b - a) * r.nextDouble();
    }
}
