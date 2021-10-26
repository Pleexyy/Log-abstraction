package distance;

import model.Event;
import smile.math.distance.Distance;

public class DistanceEvent implements Distance<Event> {

    @Override
    public double d(Event event, Event t1) {
        if (event.equals(t1))
            return 1;
        /*
        Pour chaque params, s'il à les mêmes alors il est un peu plus fidèle
        On divisise par le nombre de params après
         */
        double r = 0;
        for (String p : event.getParams()){
            if (t1.getParams().contains(p))
                r += 1;
        }
        return r/Math.max(t1.getParams().size(),event.getParams().size());
    }
}
