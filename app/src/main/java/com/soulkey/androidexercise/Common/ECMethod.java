package com.soulkey.androidexercise.Common;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
public class ECMethod {
    public static boolean checkNull(String str) {
        if(str == null || str.equals("null"))
            return true;

        return false;
    }
}
