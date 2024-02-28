package droidrocks.com.namaztimeapp.Utils;

import org.jetbrains.annotations.NotNull;

public class Constance {

    public static final String DB_NAME = "HISEBI_LATEST_DB_V2";
    public static final String TAG = "HISHEBI_TAG";

    /**
     * @true if you want enable your ads
     */
    public static final boolean IS_ADS_ON = true;
    public static final String BANNER_AD_UNIT_ID_daily = "ca-app-pub-3753272458255718/6745270858";
    public static final String BANNER_AD_UNIT_ID_Monthly = "ca-app-pub-3753272458255718/3927535826";
    public static final String BANNER_AD_UNIT_ID_Yearly = "ca-app-pub-3753272458255718/9436110851";

    public static final String Interstitial_ADS_UNIT = "ca-app-pub-3753272458255718/9343860791";
    public static final String Rewarded_ADS_UNIT = "ca-app-pub-3753272458255718/6071597448";


    /**
     * set limit to show ads after how many clicks
     */
    public static final int SHOW_ADS_AFTER_CLICK = 10;
    /**
     * all SharedPreferences key below
     */
    @NotNull
    public static final String CLICK_COUNT_KEY = "click_count";

    /**
     * ads api url
     */
    private static final String BASE_URL = "https://boimela.premadesoft.com/api/";
    public static final String AFFILIATE_ADS_API_URL = BASE_URL + "affilieateAds.php";


}
