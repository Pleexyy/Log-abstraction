package parser;

import model.Conversation;
import model.ConversationSet;
import model.Event;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * LogParser
 * parse des fichiers de logs
 */
public class LogParser {

    /**
     * Parse une ligne de log formatée
     *
     * @param line ligne
     * @return La conversation que donne la ligne
     */
    private Conversation parseLine(String line) {
        Conversation conv = new Conversation();
        var split = line.split("\\);");
        for (String strEvent : split) {
            conv.nouvelEvent(new Event(strEvent.strip() + ')'));
        }
        return conv;
    }

    /**
     * Parse un fichier de log
     *
     * @param f le fichier de log
     * @return la conversationSet résultant du parsage du fichier
     * @throws IOException si le fichier n'est pas lisible ou n'est pas un fichier
     */
    public ConversationSet parseFile(File f) throws IOException {
        final ConversationSet convSet = new ConversationSet();
        if (!f.canRead() || !f.isFile()) {
            throw new IOException("Aucun fichier n'est \"" + f.getAbsolutePath() + "\", soit n'est pas lisible");
        }

        Files.lines(f.toPath())
                .forEach(line -> {
                    convSet.addConversation(parseLine(line));
                });
        return convSet;
    }
}
