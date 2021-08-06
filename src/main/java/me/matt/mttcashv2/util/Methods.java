package me.matt.mttcashv2.util;

public class Methods {

    public static boolean isNumeric(String number) {
        if (number.equalsIgnoreCase("nan") || number.equalsIgnoreCase("infinity")) return false;
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
