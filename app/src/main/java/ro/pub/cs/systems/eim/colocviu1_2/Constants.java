package ro.pub.cs.systems.eim.colocviu1_2;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;

public interface Constants {

    final public static String TAG = "[Started Service]";
    final public static String NEXT = "n";
    final public static String ALL = "a";
    final public static String PREV = "p";
    final public static int SECONDARY_ACTIVITY_REQUEST_CODE = 99;
    final public static int MESSAGE_1 = 1;
    final public static int MESSAGE_2 = 2;
    final public static int MESSAGE_3 = 3;
    final public static String ACTION_1= "a1";
    final public static String DATA = "data";
    final public static String DATE_DATA = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Calendar.getInstance().getTime());
    final public static long SLEEP_TIME = 2000;
    final public static int LIMIT = 10;
    final public static String SERVICE_STATUS = "status";
    final public static int SERVICE_STOPPED = 0;
    final public static int SERVICE_STARTED = 1;

    // showHide
    final public static String SHOW_HIDE_LEFT = "shl";
    final public static String SHOW_HIDE_RIGHT = "shr";
}
