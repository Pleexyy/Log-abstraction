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
 * @param  label  get the label from the event
 * @param  separator  get the separator of the event
 * @param  params  represent all the parameters
 */

public class Event {
    public String label;
    private ArrayList<String> params;

    public Event(String line) {
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
        String res;
        String separator = ",";
        StringBuilder resBuilder = new StringBuilder(label + "(");
        for (String param : params) {
            resBuilder.append(param).append(separator);
        }
        res = resBuilder.toString();
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
        String[] parts = params.split("=");

        // on récupère notre premier tableau -> ce qui precede le "=" -> le paramètre

        return parts[0];
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
        StringBuilder stringedEvent = new StringBuilder();
        String parameter, assignment;
        // on parcours nos données
        for (int i = 0; i < this.getParams().size(); i++) {
            parameter = this.getParameters(this.getParams().get(i));
            assignment = this.getTypedAssignments(this.getParams().get(i));
            // concaténation de nos paramètres et de nos assignments
            stringedEvent.append(parameter.concat("=").concat(assignment).concat(","));
        }
        return stringedEvent.substring(0, stringedEvent.length() - 1);
    }

}