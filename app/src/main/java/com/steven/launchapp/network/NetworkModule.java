package com.steven.launchapp.network;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

	private String mBaseUrl;

	public NetworkModule() {
		this.mBaseUrl = "https://launchlibrary.net/1.2/";
	}

	@Provides
	@Singleton
	SharedPreferences providesSharedPreferences(Application application) {
		return PreferenceManager.getDefaultSharedPreferences(application);
	}

	@Provides
	@Singleton
	Cache provideOkHttpCache(Application application) {
		int cacheSize = 10 * 1024 * 1024; // 10 MiB
		return new Cache(application.getCacheDir(), cacheSize);
	}

	// @Provides
	// @Singleton
	// Interceptor provideInterceptor(Application application) {
	// 	return chain -> {
	// 		Response originalResponse = chain.proceed(chain.request());
	// 		if (Utils.isNetworkAvailable(application)) {
	// 			int maxAge = 60; // 1 Minute
	// 			return originalResponse.newBuilder().header("Cache-Control",
	// 					"public, max-age=" + maxAge).build();
	// 		} else {
	// 			int maxStale = 60 * 60 * 24 * 2; // 2 Days
	// 			return originalResponse.newBuilder().header("Cache-Control",
	// 					"public, only-if-cached, max-stale=" + maxStale).build();
	// 		}
	// 	};
	// }

	@Provides
	@Singleton
	Gson provideGson() {
		return new Gson();
	}

	@Provides
	@Singleton
	OkHttpClient provideOkHttpClient(Cache cache) {
		return new OkHttpClient.Builder().cache(cache)
				//	.addNetworkInterceptor(interceptor)
				.build();
	}

	@Provides
	@Singleton
	Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
		return new Retrofit.Builder().baseUrl(mBaseUrl)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(okHttpClient)
				.build();
	}

	@Provides
	@Singleton
	LaunchLibraryAPI provideLaunchLibraryAPI(Retrofit retrofit) {
		return retrofit.create(LaunchLibraryAPI.class);
	}
}

