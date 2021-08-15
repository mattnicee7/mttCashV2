package me.matt.mttcashv2.util;

import lombok.var;

public class Methods {

    public static boolean isNumeric(String number) {
        try {
            var n = Double.parseDouble(number);
            return !Double.isNaN(n) && !Double.isInfinite(n);
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
