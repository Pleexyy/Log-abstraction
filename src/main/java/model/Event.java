package model;

import utils.PatternPreCompiled;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * @author Blot Elliott
 */

/*
 * Represent an event
 *
 * @param  ligne  all the event
 * @param  from  get from where the event came
 * @param  to  get where the event was going to
 * @param  session  get the parameters from the session
 * @param  label  get the label from the event
 * @param  separator  get the separator of the event
 * @param  params  represent all the parameters
 */

public class Event {
    public String ligne, label;
    private ArrayList<String> params;

    public Event(String line) {
        this.ligne = line;
        label = SeparationWhenNoRegex.nameOfAnEvent(line);
        params = SeparationWhenNoRegex.separationWithoutRegex(line);
    }

    public Event() {

    }

    /**
     * Return the label of the event.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Return all the parameters.
     */
    public ArrayList<String> getParams() {
        return params;
    }

    public String toString() {
        String res = label + "(";
        String separator = ",";
        for (String param : params) {
            res = res + param + separator;
        }
        res = res.substring(0, res.length() - separator.length());
        res = res + ")";
        return res;
    }

    /**
     * Return parameters for each parameter
     *
     * @param params
     * @return String
     */
    public String getParameters(String params) {
        // on divise notre chaine de caractères en tableau
        String[] parts = params.split("\\=");

        // on récupère notre premier tableau -> ce qui precede le "=" -> le paramètre
        String beforeEqual = parts[0];

        return beforeEqual;
    }

    /**
     * Return typed assignments for each parameter
     *
     * @param params
     * @return String
     */
    public String getTypedAssignments(String params) {
        // on récupère uniquement les assignments des paramètres
        String assignment = params.substring(params.lastIndexOf("=") + 1);

        // détermine le type de l'assignment

        // expression régulière pour un entier
        Matcher matcherInteger = PatternPreCompiled.patternInteger.matcher(assignment);

        // expression régulière pour un float
        Matcher matcherFloat = PatternPreCompiled.patternFloat.matcher(assignment);

        if (matcherInteger.matches())
            return "int";
        return matcherFloat.matches() ? "float" : "String";
    }

    /**
     * Return events with types.
     *
     * @return String
     */
    public String getEventWithTypes() {
        String stringedEvent = "";
        String parameter, assignment;
        // on parcours nos données
        for (int i = 0; i < this.getParams().size(); i++) {
            parameter = this.getParameters(this.getParams().get(i));
            assignment = this.getTypedAssignments(this.getParams().get(i));
            // concaténation de nos paramètres et de nos assignments
            stringedEvent += parameter.concat("=").concat(assignment).concat(",");
        }
        return stringedEvent.substring(0, stringedEvent.length() - 1);
    }

}