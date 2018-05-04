package bk.itc.html5.mylib.component.data;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import bk.itc.html5.mylib.component.Constant;

/**
 * Created by Hien on 5/1/2018.
 */

public class MyPreference {
    public static boolean isFirstOpen() {
        boolean isNotFirst = Prefs.getBoolean(Constant.PREF_KEY_FIRST_OPEN, false);
        if(!isNotFirst) {
            Prefs.putBoolean(Constant.PREF_KEY_FIRST_OPEN, true);
            return true;
        }
        return false;
    }

    public static int openTimesCount() {
        return Prefs.getInt(Constant.PREF_KEY_OPEN_COUNT, 0);
    }

    public static void updateOpenTimesCount() {
        Prefs.putInt(Constant.PREF_KEY_OPEN_COUNT, openTimesCount() + 1);
    }

    private static void saveToStringSet(String id, String key) {
        List<String> selectedIds = getStringSet(key);
        if(selectedIds == null) {
            selectedIds = new ArrayList<>();
        }

        selectedIds.add(id);
        Prefs.putOrderedStringSet(key, new HashSet<String>(selectedIds));
    }

    public static boolean checkAndSaveToStringSet(String id, String key) {
        if(!checkStringExistInSet(id, key)) {
            saveToStringSet(id, key);
            return true;
        }

        return false;
    }

    public static List<String> getStringSet(String key) {
        if(Prefs.getOrderedStringSet(key, null) == null)
            return new ArrayList<>();

        return new ArrayList<String>(Prefs.getOrderedStringSet(key, null));
    }

    public static boolean checkStringExistInSet(String id, String key) {
        List<String> selectedSet = getStringSet(key);
        if(selectedSet == null)
            return false;

        for (String s : selectedSet) {
            if(s.equals(id)) {
                return true;
            }
        }

        return false;
    }

    public static boolean removeStringInSet(String id, String key) {
        List<String> stringSet = getStringSet(key);
        if(stringSet == null)
            return false;

        for (Iterator<String> iterator = stringSet.iterator(); iterator.hasNext();) {
            String s =  iterator.next();
            if (s.equals(id)) {
                iterator.remove();
                Prefs.putOrderedStringSet(key, new HashSet<String>(stringSet));
                return true;
            }
        }

        return false;
    }
}
