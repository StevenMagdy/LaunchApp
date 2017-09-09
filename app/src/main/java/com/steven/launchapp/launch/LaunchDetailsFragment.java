package com.steven.launchapp.launch;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.launchapp.MyApp;
import com.steven.launchapp.R;
import com.steven.launchapp.base.BaseFragment;
import com.steven.launchapp.network.LaunchLibraryAPI;
import com.steven.launchapp.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LaunchDetailsFragment extends BaseFragment implements LaunchContract.View {

	@BindView(R.id.progressBar) ProgressBar progressBar;
	@BindView(R.id.textView_status) TextView statusTextView;
	@BindView(R.id.textView_net) TextView netTextView;
	@BindView(R.id.textView_window_start) TextView windowStartTextView;
	@BindView(R.id.textView_window_end) TextView windowEndTextView;
	@BindView(R.id.button_video) Button videoButton;
	@Inject LaunchLibraryAPI launchLibraryAPI;

	private LaunchContract.Presenter presenter;
	private Unbinder unbinder;

	public static LaunchDetailsFragment getInstance(Bundle bundle) {
		LaunchDetailsFragment launchDetailsFragment = new LaunchDetailsFragment();
		launchDetailsFragment.setArguments(bundle);
		return launchDetailsFragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((MyApp) getActivity().getApplication()).getNetworkComponent().inject(this);
		presenter = new LaunchDetailsPresenter(launchLibraryAPI);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_launch_details, container, false);
		unbinder = ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.attachView(this);
		//if (Utils.isNetworkAvailable(getContext())) {
			presenter.loadDetails(false, ((LaunchActivity) getActivity()).getLaunchID());
		//} else {
		//	showConnectionError();
		//}
	}

	@Override
	public void onPause() {
		super.onPause();
		presenter.detachView();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	@Override
	public void showName(String name) {
	}

	@Override
	public void showNet(Date date) {
		SimpleDateFormat simpleDateFormat =
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Utils.getCurrentLocale(getContext()));
		netTextView.setText(simpleDateFormat.format(date));
	}

	@Override
	public void showWindowStart(Date date) {
		SimpleDateFormat simpleDateFormat =
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Utils.getCurrentLocale(getContext()));
		windowStartTextView.setText(simpleDateFormat.format(date));
	}

	@Override
	public void showWindowEnd(Date date) {
		SimpleDateFormat simpleDateFormat =
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Utils.getCurrentLocale(getContext()));
		windowEndTextView.setText(simpleDateFormat.format(date));
	}

	@Override
	public void showLiveUrl(String url) {
		videoButton.setOnClickListener(view -> Utils.launchURL(getActivity(), url));
	}

	@Override
	public void setRocketID(int id) {
		((LaunchActivity) getActivity()).setRocketID(id);
	}

	@Override
	public void setMissionID(int id) {
		((LaunchActivity) getActivity()).setMissionId(id);
	}

	@Override
	public void setProgress(boolean inProgress) {
		progressBar.setVisibility(inProgress ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void showConnectionError() {
		Toast.makeText(getContext(), R.string.msg_connection_error, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showStatus(int code) {
		switch (code) {
			case 1:
				statusTextView.setText(R.string.status_launch_go);
				statusTextView.setTextColor(Color.GREEN);
				break;
			case 2:
				statusTextView.setText(R.string.status_launch_no_go);
				statusTextView.setTextColor(Color.RED);
				break;
			case 3:
				statusTextView.setText(R.string.status_launch_success);
				statusTextView.setTextColor(Color.GREEN);
				break;
			case 4:
				statusTextView.setText(R.string.status_launch_failed);
				statusTextView.setTextColor(Color.RED);
				break;
			default:
				statusTextView.setText(R.string.status_launch_unknown);
				break;
		}
	}
}
