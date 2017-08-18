package com.steven.launchapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import java.util.Locale;

public final class Utils {

	private static final String TAG = Utils.class.getSimpleName();

	public static final String LAUNCH_ID_KEY = "launch_id";
	public static final String LAUNCH_NAME_KEY = "launch_name";
	public static final String ROCKET_ID_KEY = "rocket_id";
	public static final String MISSION_ID_KEY = "mission_id";

	private Utils() {
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager =
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}

	public static boolean myEquals(Object a, Object b) {
		return (a == b) || (a != null && a.equals(b));
	}

	public static Locale getCurrentLocale(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return context.getResources().getConfiguration().getLocales().get(0);
		} else {
			//noinspection deprecation
			return context.getResources().getConfiguration().locale;
		}
	}
}
