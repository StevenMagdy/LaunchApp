package com.steven.launchapp.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public final class RxUtils {

	private RxUtils() {
	}

	public static <T> ObservableTransformer<T, T> createIOToMainThreadScheduler() {
		return new ObservableTransformer<T, T>() {
			@Override
			public ObservableSource<T> apply(@NonNull Observable<T> observable) {
				return observable.subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread());
			}
		};
	}
}
