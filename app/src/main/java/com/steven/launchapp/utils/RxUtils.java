package com.steven.launchapp.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class RxUtils {

	private static final String TAG = RxUtils.class.getSimpleName();

	private RxUtils() {
	}

	public static <T> ObservableTransformer<T, T> observableIOToMainThreadScheduler() {
		return observable -> observable.subscribeOn(Schedulers.io()).observeOn(
				AndroidSchedulers.mainThread());
	}

	public static <T> SingleTransformer<T, T> singleIOToMainThreadScheduler() {
		return observable -> observable.subscribeOn(Schedulers.io()).observeOn(
				AndroidSchedulers.mainThread());
	}
}
