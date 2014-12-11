package com.myideaway.easyapp.core.intent;

import android.content.Intent;
import roboguice.util.Ln;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: cdm
 * Date: 12-5-22
 * Time: PM10:27
 */
public class IntentObjectPool {
    private static HashMap<String, HashMap<String, Object>> pool = new HashMap<String, HashMap<String, Object>>();

    private IntentObjectPool() {

    }

    public static void remove(Intent intent) {
        String pooluid = intent.getStringExtra("pooluid");

        Ln.d("remove object intent " + pooluid);

        if (pool.containsKey(pooluid)) {
            pool.remove(pooluid);
        }
    }

    public static void putObjectExtra(Intent intent, String name, Object value) {

        String pooluid = intent.getStringExtra("pooluid");
        if (pooluid == null) {
            pooluid = UUID.randomUUID().toString();
            intent.putExtra("pooluid", pooluid);

            Ln.d("generate object intent " + pooluid);
        }

        HashMap<String, Object> item = null;
        if (pool.containsKey(pooluid)) {
            item = pool.get(pooluid);
        } else {
            item = new HashMap<String, Object>();
            pool.put(pooluid, item);
        }

        item.put(name, value);
    }

    public static Object getObjectExtra(Intent intent, String name, Object defaultValue) {
        String pooluid = intent.getStringExtra("pooluid");

        Ln.d("get object intent " + pooluid);

        HashMap<String, Object> item = null;
        if (pool.containsKey(pooluid)) {
            item = pool.get(pooluid);

            return item.get(name);
        } else {
            return defaultValue;
        }
    }

    public static void putBooleanExtra(Intent intent, String name, boolean value) {
        intent.putExtra(name, value);
    }

    public static boolean getBooleanExtra(Intent intent, String name, Boolean defaultValue) {
        return intent.getBooleanExtra(name, defaultValue);
    }

    public static void putByteExtra(Intent intent, String name, byte value) {
        intent.putExtra(name, value);
    }

    public static byte getByteExtra(Intent intent, String name, byte defaultValue) {
        return intent.getByteExtra(name, defaultValue);
    }

    public static void putCharExtra(Intent intent, String name, char value) {
        intent.putExtra(name, value);
    }

    public static char getCharExtra(Intent intent, String name, char defaultValue) {
        return intent.getCharExtra(name, defaultValue);
    }

    public static void putShortExtra(Intent intent, String name, short value) {
        intent.putExtra(name, value);
    }

    public static short getShortExtra(Intent intent, String name, short defaulValue) {
        return intent.getShortExtra(name, defaulValue);
    }

    public static void putIntExtra(Intent intent, String name, int value) {
        intent.putExtra(name, value);
    }

    public static int getIntExtra(Intent intent, String name, int defaulValue) {
        return intent.getIntExtra(name, defaulValue);
    }

    public static void putLongExtra(Intent intent, String name, long value) {
        intent.putExtra(name, value);
    }

    public static long getLongExtra(Intent intent, String name, long defaulValue) {
        return intent.getLongExtra(name, defaulValue);
    }


    public static void putFloatExtra(Intent intent, String name, float value) {
        intent.putExtra(name, value);
    }


    public static float getFloatExtra(Intent intent, String name, float defaulValue) {
        return intent.getFloatExtra(name, defaulValue);
    }

    public static void putDoubleExtra(Intent intent, String name, double value) {
        intent.putExtra(name, value);
    }

    public static double getDoubleExtra(Intent intent, String name, double defaulValue) {
        return intent.getDoubleExtra(name, defaulValue);
    }

    public static void putStringExtra(Intent intent, String name, String value) {
        intent.putExtra(name, value);
    }

    public static String getStringExtra(Intent intent, String name) {
        return intent.getStringExtra(name);
    }

    public static void putCharSequenceExtra(Intent intent, String name, CharSequence value) {
        intent.putExtra(name, value);
    }

    public static CharSequence getCharSequenceExtra(Intent intent, String name) {
        return intent.getCharSequenceExtra(name);
    }

}
