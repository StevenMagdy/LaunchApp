package com.steven.launchapp.mission;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.launchapp.MyApp;
import com.steven.launchapp.R;
import com.steven.launchapp.base.BaseFragment;
import com.steven.launchapp.launch.LaunchActivity;
import com.steven.launchapp.network.LaunchLibraryAPI;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MissionFragment extends BaseFragment implements MissionContract.View {

	@BindView(R.id.tv_mission_name) TextView nameTextView;
	@BindView(R.id.tv_mission_type) TextView typeTextView;
	@BindView(R.id.tv_mission_desc) TextView descriptionTextView;
	@BindView(R.id.progressBar) ProgressBar progressBar;
	@Inject LaunchLibraryAPI launchLibraryAPI;

	private MissionContract.Presenter presenter;
	private Unbinder unbinder;

	public static MissionFragment getInstance(Bundle bundle) {
		MissionFragment missionFragment = new MissionFragment();
		missionFragment.setArguments(bundle);
		return missionFragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((MyApp) getActivity().getApplication()).getNetworkComponent().inject(this);
		presenter = new MissionPresenter(launchLibraryAPI);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mission, container, false);
		unbinder = ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (((LaunchActivity) getActivity()).getMissionID() == 0) return;
		presenter.attachView(this);
		presenter.loadDetails(false, ((LaunchActivity) getActivity()).getMissionID());
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && getView() != null) onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		presenter.detachView();
	}

	@Override
	public void setProgress(boolean inProgress) {
		progressBar.setVisibility(inProgress ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void showMissionName(String name) {
		nameTextView.setText(name);
	}

	@Override
	public void showMissionType(String type) {
		typeTextView.setText(type);
	}

	@Override
	public void showMissionDescription(String description) {
		descriptionTextView.setText(description);
	}

	@Override
	public void showConnectionError() {
		Toast.makeText(getContext(), R.string.msg_connection_error, Toast.LENGTH_SHORT).show();
	}
}
