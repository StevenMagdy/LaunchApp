package com.steven.launchapp.rocket;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.steven.launchapp.MyApp;
import com.steven.launchapp.R;
import com.steven.launchapp.base.BaseFragment;
import com.steven.launchapp.launch.LaunchActivity;
import com.steven.launchapp.network.LaunchLibraryAPI;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RocketFragment extends BaseFragment implements RocketContract.View {

	@BindView(R.id.tv_rocket_name) TextView rocketNameTextView;
	@BindView(R.id.tv_rocket_family) TextView rocketFamilyTextView;
	@BindView(R.id.progressBar) ProgressBar progressBar;
	@BindView(R.id.image_rocket) ImageView rocketImage;
	@BindView(R.id.button_site) Button siteButton;
	@BindView(R.id.button_wiki) Button wikiButton;

	@Inject LaunchLibraryAPI launchLibraryAPI;

	private RocketContract.Presenter presenter;
	private Unbinder unbinder;

	public static RocketFragment getInstance(Bundle bundle) {
		RocketFragment rocketFragment = new RocketFragment();
		rocketFragment.setArguments(bundle);
		return rocketFragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((MyApp) getActivity().getApplication()).getNetworkComponent().inject(this);
		presenter = new RocketPresenter(launchLibraryAPI);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_rocket, container, false);
		unbinder = ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (((LaunchActivity) getActivity()).getRocketID() == 0) return;
		presenter.attachView(this);
		presenter.loadDetails(false, ((LaunchActivity) getActivity()).getRocketID());
	}

	@Override
	public void onPause() {
		super.onPause();
		presenter.detachView();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && getView() != null) onResume();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	@Override
	public void setProgress(boolean inProgress) {
		progressBar.setVisibility(inProgress ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void showRocketName(String name) {
		rocketNameTextView.setText(name);
	}

	@Override
	public void showRocketFamily(String name) {
		rocketFamilyTextView.setText(name + " Family");
	}

	@Override
	public void showRocketSite(String url) {
		siteButton.setEnabled(true);
		siteButton.setOnClickListener(view -> {
			CustomTabsIntent customTabsIntent =
					new CustomTabsIntent.Builder().setToolbarColor(ContextCompat.getColor
							(getActivity(), R.color.colorPrimary))
							.build();
			customTabsIntent.launchUrl(getActivity(), Uri.parse(url));
		});
	}

	@Override
	public void showRocketWiki(String url) {
		wikiButton.setEnabled(true);
		wikiButton.setOnClickListener(view -> {
			CustomTabsIntent customTabsIntent =
					new CustomTabsIntent.Builder().setToolbarColor(ContextCompat.getColor
							(getActivity(), R.color.colorPrimary))
							.build();
			customTabsIntent.launchUrl(getActivity(), Uri.parse(url));
		});
	}

	@Override
	public void showRocketImage(String imageURL) {
		Glide.with(this)
				.load(imageURL)
				.apply(RequestOptions.centerCropTransform())
				.into(rocketImage);
	}

	@Override
	public void showConnectionError() {
		Toast.makeText(getContext(), R.string.msg_connection_error, Toast.LENGTH_SHORT).show();
	}
}
