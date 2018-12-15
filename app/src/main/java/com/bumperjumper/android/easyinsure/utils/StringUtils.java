package com.bumperjumper.android.easyinsure.utils;

public enum StringUtils {

    INSTANCE;

    public String getCleansedString(String string) {
        return string == null || string.isEmpty() || string.equalsIgnoreCase("NULL") ? "" : string;
    }
}
