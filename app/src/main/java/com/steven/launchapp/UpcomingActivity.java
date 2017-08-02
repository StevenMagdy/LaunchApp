package com.steven.launchapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.steven.launchapp.models.Launch;
import com.steven.launchapp.utils.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;

public class UpcomingActivity extends AppCompatActivity {

	private static final String TAG = UpcomingActivity.class.getSimpleName();

	private RecyclerView recyclerView;
	private LaunchAdapter launchAdapter;
	private Consumer<List<Launch>> listConsumer;
	private CompositeDisposable compositeDisposable = new CompositeDisposable();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle("Upcoming Launches");
		}
		String launchNumber = PreferenceManager.getDefaultSharedPreferences(this)
				.getString(getString(R.string.pref_launchesNumber_key), getString(R.string
						.ten_numeric));
		recyclerView = (RecyclerView) findViewById(R.id.list_launches);

		launchAdapter = new LaunchAdapter(null, this);
		launchAdapter.setOnItemClickListener(new LaunchAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(int position) {

			}
		});
		recyclerView.setAdapter(launchAdapter);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(linearLayoutManager);

		// recyclerView.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager
		// 		.getOrientation()));

		listConsumer = new Consumer<List<Launch>>() {
			@Override
			public void accept(@NonNull List<Launch> launches) throws Exception {
				showLaunches(launches);
			}
		};
		Retrofit retrofit = Utils.provideRetrofit(Utils.provideOkHttpClient());
		final LaunchLibraryAPI launchLibraryAPI = Utils.provideInfinityAPI(retrofit);

		Observable<List<Launch>> launchesObservable = Utils.provideListObservable
				(launchLibraryAPI, Integer
				.parseInt(launchNumber));
		compositeDisposable.add(launchesObservable.subscribe(listConsumer));

		Log.d(TAG, launchNumber);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		compositeDisposable.dispose();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_settings:
				Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
				startActivity(startSettingsActivity);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void showLaunches(List<Launch> launches) {
		// mProgressBar.setVisibility(View.GONE);
		// mInfoTextView.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
		// swipeRefreshLayout.setRefreshing(false);
		launchAdapter.addLaunches(launches);
	}
}
