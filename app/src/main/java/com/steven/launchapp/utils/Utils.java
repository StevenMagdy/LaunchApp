package com.steven.launchapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.steven.launchapp.LaunchLibraryAPI;
import com.steven.launchapp.models.Launch;
import com.steven.launchapp.models.LaunchesResult;

import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Utils {

	private static final String TAG = Utils.class.getSimpleName();
	public static final String BASE_URL_LAUNCH_LIBRARY = "https://launchlibrary.net/1.2/";
	private static OkHttpClient okHttpClient;
	private static Retrofit retrofit;
	private static LaunchLibraryAPI launchLibraryAPI;
	private static Observable<List<Launch>> listObservable;

	private Utils() {
	}

	public static synchronized OkHttpClient provideOkHttpClient() {
		if (okHttpClient == null) {
			okHttpClient = new OkHttpClient.Builder().retryOnConnectionFailure(false).build();
		}
		return okHttpClient;
	}

	public static synchronized Retrofit provideRetrofit(OkHttpClient okHttpClient) {
		if (retrofit == null) {
			retrofit = new Retrofit.Builder().baseUrl(Utils.BASE_URL_LAUNCH_LIBRARY)
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.client(okHttpClient)
					.build();
		}
		return retrofit;
	}

	public static synchronized LaunchLibraryAPI provideInfinityAPI(Retrofit retrofit) {
		if (launchLibraryAPI == null) {
			launchLibraryAPI = retrofit.create(LaunchLibraryAPI.class);
		}
		return launchLibraryAPI;
	}

	public static synchronized Observable<List<Launch>> provideListObservable(
			LaunchLibraryAPI launchLibraryAPI,int launchesNumber) {
		if (listObservable == null) {
			listObservable = launchLibraryAPI.getUpcomingLaunches(launchesNumber)
					.map(new Function<LaunchesResult, List<Launch>>() {

						@Override
						public List<Launch> apply(
								@NonNull LaunchesResult launchesResult) throws Exception {
							return launchesResult.getLaunches();
						}
					})
					.compose(RxUtils.<List<Launch>>createIOToMainThreadScheduler())
					.cache();
		}
		return listObservable;
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService
				(Context.CONNECTIVITY_SERVICE);
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
			return context.getResources().getConfiguration().locale;
		}
	}
}
