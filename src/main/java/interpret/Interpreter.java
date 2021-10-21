package interpret;

import model.Event;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Interpreter {

    private List<Event> eventList = new ArrayList<>();

    public Event interpreteEvent(String l){
        l = l.strip();
        int i = l.indexOf('(');
        int idEvent = Integer.parseInt(l.substring(1,i));
        Event e = new Event(idEvent);

        int indexEgal = l.indexOf('=',i);
        int indexComa = l.indexOf(',',indexEgal);

        while (indexEgal != -1 && indexComa != -1){ // si l'une des deux recherche foire on arrête

            String key = l.substring(i+1,indexEgal).strip(); // on part de l'ancien coma et on va jusqu'au egal
            String value = l.substring(indexEgal+1,indexComa).strip(); // on part du égal et on va jusqu'au nouveau coma
            if (!key.isEmpty())
                e.put(key,value);

            i = indexComa; // l'ancien coma
            char c; // = l.charAt(indexComa + 1);
            if (l.charAt(indexComa) == ')')
                break;
            for(int j = indexComa+1, arrayLength = l.toCharArray().length ; j < arrayLength; ++j){
                c = l.charAt(j);
                if (c == '='){
                    indexEgal = j;
                    indexComa = l.indexOf(',',indexEgal); // nouveau coma à partir du egal
                    if (indexComa == -1) // attention il se peut qu'il n'y ai pas d'autre paramètre
                        indexComa = l.indexOf(')',indexEgal); // chercher la parathèse à la place du coma
                    break;
                }
                if (c == ','){
                    indexEgal = j; // index de la virgule
                    indexComa = j+1; // permet d'avoir une value égale à string empty
                    break;
                }
            }
        }

        return e;
    }

    private int interpreteLine(String line){
        var x = line.split("\\);");
        for (String s : x) {
            Event e = interpreteEvent(s);
            if (e == null)
                return -1;
            else{
                eventList.add(e);
            }
        }
        // to remove
        eventList.forEach(System.out::println);
        return 0;
    }

    public int readFile(File f) throws IOException {
        if (!f.canRead() || !f.isFile()){
            throw new AccessDeniedException("Le fichier n'est peut-être pas lisible ou ce n'est pas un fichier");
        }
        AtomicInteger i = new AtomicInteger();
        Files.lines(f.toPath())
                .forEach(line -> {
                    if (interpreteLine(line) == -1){
                        i.set(1);
                    }
                });
        return i.get();
    }
}
