package lk.chathurabuddi.dashboard.corona.util;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chathura Buddhika
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.Locale;

public class NumberUtil {
    public static String format(long number) {
        final long startValue = 10000;
        if (number < 0 ? -startValue < number : number < startValue){
            return String.valueOf(number);
        }
        final String[] suffixes = { "K", "M", "B", "T" };
        final int divider = 1000;
        double x = (double)number/startValue;
        for (String suffix : suffixes) {
            if (x < 0 ? -divider < x : x < divider) {
                String formattedNumber;
                if ("K".equals(suffix)) {
                    formattedNumber = String.valueOf((int)x);
                } else {
                    formattedNumber = String.format(Locale.getDefault(),"%.2f", x);
                }
                formattedNumber = formattedNumber.endsWith(".00")?formattedNumber.split(".")[0]:formattedNumber;
                return  formattedNumber + suffix;
            }
            x = x / divider;
        }
        return "N/A";
    }
}
