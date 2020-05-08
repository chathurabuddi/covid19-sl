package lk.chathurabuddi.dashboard.corona;

import java.util.Locale;

public class AppUtil {
    public static String format(long number) {
        final long startValue = 1000000;
        if (number < 0 ? -startValue < number : number < startValue){
            return String.valueOf(number);
        }
        final String[] suffixes = { "m", "b", "t" };
        final int divider = 1000;
        double x = (double)number/startValue;
        for (String suffix : suffixes) {
            if (x < 0 ? -divider < x : x < divider) {
                String formattedNumber = String.format(Locale.getDefault(),"%.2f", x);
                formattedNumber = formattedNumber.endsWith(".00")?formattedNumber.split(".")[0]:formattedNumber;
                return  formattedNumber + suffix;
            }
            x = x / divider;
        }
        return "N/A";
    }
}
