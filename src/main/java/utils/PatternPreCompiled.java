package utils;

import java.util.regex.Pattern;

public class PatternPreCompiled {
    public static final Pattern patternInteger = Pattern.compile("[+-]?[0-9]+");
    public static final Pattern patternFloat = Pattern.compile("^([+-]?\\d*\\.?\\d*)$");
}
