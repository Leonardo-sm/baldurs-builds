package com.baldursbuilds.utils;

public class StringUtils {
    public static boolean isNullOrEmpty(String value) {
      return value.isEmpty() || value.isBlank();
    }
}
