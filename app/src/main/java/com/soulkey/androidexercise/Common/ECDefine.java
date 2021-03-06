package com.soulkey.androidexercise.Common;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
public class ECDefine {
    private static final String testUrl = "https://s3-ap-southeast-2.amazonaws.com/elasticbeanstalk-ap-southeast-2-510434927256/facts.json";
    private static final String realUrl = "https://dl.dropboxusercontent.com/u/746330/facts.json";

    private static final boolean isTest = false;

    public static String getUrl()
    {
        return isTest ? testUrl : realUrl;
    }

    public static final String DEFINE_TITLE = "title";
    public static final String DEFINE_ROWS = "rows";

    public static final String TOKEN_DESCRIPTION = "description";
    public static final String TOKEN_TITLE= "title";
    public static final String TOKEN_IMAGEHREF = "imageHref";

    public static final String MSG_RECEIVING_DATA = "Receiving Data...";
    public static final String MSG_CON_FAIL = "Connection Failed";
    public static final String MSG_TRY_AGAIN = "Please check your network connection and try again";
    public static final String MSG_OK = "OK";

    public static final String RESULT_FAILED = "FAILED";
    public static final String RESULT_SUCCESS = "SUCCESS";

    public static final int LENGTH_TIME_OUT = 5000;
    public static final int RETRY_COUNT = 0;

    public static final String TAG = "AndroidExcercise";

}
