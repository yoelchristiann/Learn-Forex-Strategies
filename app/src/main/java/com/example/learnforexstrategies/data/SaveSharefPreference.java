package com.example.learnforexstrategies.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.learnforexstrategies.data.PreferencesUtility.EMAIL;
import static com.example.learnforexstrategies.data.PreferencesUtility.LOGGED_IN_PREF;
import static com.example.learnforexstrategies.data.PreferencesUtility.NAME;
import static com.example.learnforexstrategies.data.PreferencesUtility.TOKEN_USER;

public class SaveSharefPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    //save login session
    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static boolean reset(Context context) {
        return getPreferences(context).edit().clear().commit();
    }


    //save token user
    public static void setTokenIn(Context context, String tokenIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(TOKEN_USER, tokenIn);
        editor.apply();
    }

    public static String getTokenStatus(Context context) {
        return getPreferences(context).getString(TOKEN_USER, "");
    }

    //save first name user
    public static void setNameIn(Context context, String nameIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(NAME, nameIn);
        editor.apply();

    }

    public static String getNameStatus(Context context) {
        return getPreferences(context).getString(NAME, "");
    }

    //save last name user
    public static void setEmail(Context context, String lastNameIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(EMAIL, lastNameIn);
        editor.apply();
    }

    public static String getEmail(Context context) {
        return getPreferences(context).getString(EMAIL, "");
    }

}
