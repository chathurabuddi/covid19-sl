package lk.chathurabuddi.dashboard.corona.util;

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