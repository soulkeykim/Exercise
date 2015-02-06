package com.soulkey.androidexercise.Common;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
public class ECDefine {
    private final static String testUrl = "https://s3-ap-southeast-2.amazonaws.com/elasticbeanstalk-ap-southeast-2-510434927256/facts.json";
    private final static String realUrl = "https://dl.dropboxusercontent.com/u/746330/facts.json";

    private final static boolean isTest = false;

    public static String getUrl()
    {
        return isTest ? testUrl : realUrl;
    }

    public final static String DEFINE_TITLE = "title";
    public final static String DEFINE_ROWS = "rows";

    public final static String TOKEN_DESCRIPTION = "description";
    public final static String TOKEN_TITLE= "title";
    public final static String TOKEN_IMAGEHREF = "imageHref";


    public final static String MSG_RECEIVING_DATA = "Receiving Data...";

}
