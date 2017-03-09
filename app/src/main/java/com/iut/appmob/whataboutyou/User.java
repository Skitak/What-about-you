package com.iut.appmob.whataboutyou;

import com.facebook.AccessToken;

/**
 * Created by guydo on 09/03/2017.
 */

public class User {
    private static AccessToken atk;

    public static AccessToken getAccessToken(){
        return atk;
    }

    public static void setAccessToken(AccessToken at){
        atk = at;
    }
}
