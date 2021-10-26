package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * @param  paramsSess  represent all the parameters useful in order to use the event
 * @param  date  represent the timestamp of the event
 */

public class Event {
    public String ligne;

    private String label;
    private ArrayList<String> params;
    private ArrayList<String> paramsSess;

    public Event(String line) {
        this.ligne = line;
        label = SeparationWhenNoRegex.nameOfAnEvent(line);
        params = SeparationWhenNoRegex.separationWithoutRegex(line);
        paramsSess = new ArrayList<>();
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
        String regexInt = "[+-]?[0-9]+";
        Pattern patternInteger = Pattern.compile(regexInt);
        Matcher matcherInteger = patternInteger.matcher(assignment);

        // expression régulière pour un float
        String regexFloat = "^([+-]?\\d*\\.?\\d*)$";
        Pattern patternFloat = Pattern.compile(regexFloat);
        Matcher matcherFloat = patternFloat.matcher(assignment);

        if (matcherInteger.find() && matcherInteger.group().equals(assignment)) {
            return "int";
        }
        if (matcherFloat.find() && matcherFloat.group().equals(assignment)) {
            return "float";
        } else {
            return "String";
        }
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