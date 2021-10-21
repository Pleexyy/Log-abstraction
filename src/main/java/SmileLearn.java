import org.apache.commons.csv.CSVFormat;
import smile.clustering.DBSCAN;
import smile.io.Read;
import smile.plot.swing.Canvas;
import smile.plot.swing.ScatterPlot;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

public class SmileLearn {
    public void realize(){
        try {
            var x = Read.csv("/home/IUT/gatheuws/ptut/src/main/resources/data/nile.csv", CSVFormat.DEFAULT.withDelimiter(',')).toArray();
            var clusters = DBSCAN.fit(x, 10, 2);
            var d = ScatterPlot.of(x,clusters.y,'.');
            Canvas c = d.canvas();
            c.window();
        } catch (IOException | URISyntaxException | InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // ScatterPlot.of(x, clusters.y, '.', Palette.COLORS).window();
    }
}
