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

import lk.chathurabuddi.dashboard.corona.BuildConfig;

public class DeviceInformation {

    public static String extract() {
        StringBuilder sb = new StringBuilder();

        sb.append("App Version Name: ").append(BuildConfig.VERSION_NAME)
                .append(" | App Version Code: ").append(BuildConfig.VERSION_CODE);

        sb.append(" | OS Version: ").append(System.getProperty("os.version")).append(" (").append(android.os.Build.VERSION.INCREMENTAL).append(")")
                .append(" | OS API Level: ").append(android.os.Build.VERSION.SDK)
                .append(" | Device: ").append(android.os.Build.DEVICE)
                .append(" | Model (and Product): ").append(android.os.Build.MODEL).append(" (").append(android.os.Build.PRODUCT).append(")");

        sb.append(" | Manufacturer: ").append(android.os.Build.MANUFACTURER)
                .append(" | Other TAGS: ").append(android.os.Build.TAGS);

        return sb.toString();
    }
}