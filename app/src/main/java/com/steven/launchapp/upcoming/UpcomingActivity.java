package com.steven.launchapp.upcoming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.steven.launchapp.MyApp;
import com.steven.launchapp.R;
import com.steven.launchapp.base.BaseActivity;
import com.steven.launchapp.launch.LaunchActivity;
import com.steven.launchapp.models.Launch;
import com.steven.launchapp.network.LaunchLibraryAPI;
import com.steven.launchapp.settings.SettingsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.steven.launchapp.utils.Utils.LAUNCH_ID_KEY;
import static com.steven.launchapp.utils.Utils.LAUNCH_NAME_KEY;

public class UpcomingActivity extends BaseActivity implements UpcomingContract.View {

	private UpcomingContract.Presenter presenter;
	@BindView(R.id.list_launches) RecyclerView recyclerView;
	@BindView(R.id.swipeRefresh_launches) SwipeRefreshLayout launchesSwipeRefresh;
	@Inject SharedPreferences sharedPreferences;
	@Inject LaunchLibraryAPI launchLibraryAPI;

	private UpcomingAdapter upcomingAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upcoming);
		ButterKnife.bind(this);
		((MyApp) getApplication()).getNetworkComponent().inject(this);
		setSupportActionBar(findViewById(R.id.toolbar));
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) setupActionBar(actionBar);
		presenter = new UpcomingPresenter(launchLibraryAPI);
		String launchesNumber =
				sharedPreferences.getString(getString(R.string.pref_launchesNumber_key), getString
						(R.string.ten_numeric));
		launchesSwipeRefresh.setOnRefreshListener(() -> presenter.loadLaunches(true, Integer
				.parseInt(launchesNumber)));
		upcomingAdapter = new UpcomingAdapter(this);
		upcomingAdapter.setOnItemClickListener(position -> {
			Intent intent = new Intent(UpcomingActivity.this, LaunchActivity.class);
			intent.putExtra(LAUNCH_ID_KEY, upcomingAdapter.getLaunch(position).getId());
			intent.putExtra(LAUNCH_NAME_KEY, upcomingAdapter.getLaunch(position).getName());
			startActivity(intent);
		});
		recyclerView.setAdapter(upcomingAdapter);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(linearLayoutManager);
	}

	@Override
	protected void onResume() {
		super.onResume();
		presenter.attachView(this);
		String launchesNumber =
				sharedPreferences.getString(getString(R.string.pref_launchesNumber_key), getString
						(R.string.ten_numeric));
		//if (isNetworkAvailable(this)) {
		presenter.loadLaunches(false, Integer.parseInt(launchesNumber));
		//} else {
		//	showConnectionError();
		//}
	}

	@Override
	protected void onPause() {
		super.onPause();
		presenter.detachView();
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

	@Override
	public void showLaunches(List<Launch> launches) {
		upcomingAdapter.swapLaunches(launches);
	}

	@Override
	public void setProgress(boolean inProgress) {
		launchesSwipeRefresh.setRefreshing(inProgress);
		recyclerView.setVisibility(inProgress ? View.INVISIBLE : View.VISIBLE);
	}

	@Override
	public void showConnectionError() {
		Toast.makeText(this, R.string.msg_connection_error, Toast.LENGTH_SHORT).show();
	}

	private void setupActionBar(ActionBar actionBar) {
		actionBar.setTitle(R.string.upcoming_title);
	}
}
