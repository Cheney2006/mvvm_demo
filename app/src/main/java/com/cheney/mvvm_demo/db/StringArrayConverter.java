package com.cheney.mvvm_demo.db;

import androidx.room.TypeConverter;

public class StringArrayConverter {
    @TypeConverter
    public static String[] stringToStringArray(String s) {
        return s.isEmpty() ? null : s.split(";");
    }

    @TypeConverter
    public static String stringArrayToString(String[] array) {
        if (array == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s).append(";");
        }
        // delete sb last char.
        int length = sb.length();
        if (length > 0 && sb.charAt(length-1) == ';') {
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
}
