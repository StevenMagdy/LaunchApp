package com.steven.launchapp.upcoming;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.steven.launchapp.R;
import com.steven.launchapp.models.Launch;
import com.steven.launchapp.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.LaunchHolder> {

	private static final String TAG = UpcomingAdapter.class.getSimpleName();

	private List<Launch> launches;
	private Context context;
	private OnItemClickListener onItemClickListener;
	private OnItemLongClickListener onItemLongClickListener;

	interface OnItemClickListener {

		void onItemClick(int position);
	}

	interface OnItemLongClickListener {

		boolean onItemLongClick(int position);
	}

	UpcomingAdapter(Context context) {
		this.context = context;
	}

	@Override
	public LaunchHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		return new LaunchHolder(LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.item_launch, viewGroup, false));
	}

	@Override
	public void onBindViewHolder(LaunchHolder launchHolder, int i) {
		Launch currentLaunch = launches.get(i);
		launchHolder.nameTextView.setText(currentLaunch.getName());
		try {
			Date date = launchHolder.dateParserFormat.parse(currentLaunch.getNet());
			String dateString = launchHolder.dateFormat.format(date).trim();
			if (currentLaunch.getTbdDate() == 1) dateString += launchHolder.notConfirmed;
			launchHolder.dateTextView.setText(dateString);
			String timeString = launchHolder.timeFormat.format(date).trim();
			if (currentLaunch.getTbdTime() == 1) timeString += launchHolder.notConfirmed;
			launchHolder.timeTextView.setText(timeString);
		} catch (ParseException e) {
			Log.e(TAG, "Parsing Date Error", e);
		}
	}

	void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
		this.onItemLongClickListener = onItemLongClickListener;
	}

	@Override
	public int getItemCount() {
		return launches != null ? launches.size() : 0;
	}

	void swapLaunches(List<Launch> launches) {
		if (launches == null) return;
		this.launches = launches;
		notifyDataSetChanged();
	}

	void addLaunches(List<Launch> launches) {
		if (launches == null) return;
		if (this.launches != null) {
			this.launches.addAll(launches);
		} else {
			this.launches = launches;
		}
		notifyDataSetChanged();
	}

	Launch getLaunch(int position) {
		return launches.get(position);
	}

	class LaunchHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View
			.OnLongClickListener {

		private TextView nameTextView;
		private TextView dateTextView;
		private TextView timeTextView;
		private SimpleDateFormat dateParserFormat;
		private SimpleDateFormat dateFormat;
		private SimpleDateFormat timeFormat;
		private String notConfirmed;

		private LaunchHolder(View itemView) {
			super(itemView);
			nameTextView = itemView.findViewById(R.id.textView_name);
			dateTextView = itemView.findViewById(R.id.textView_date);
			timeTextView = itemView.findViewById(R.id.textView_time);
			dateParserFormat =
					new SimpleDateFormat("MMM d, yyyy HH:mm:ss Z", Utils.getCurrentLocale
							(context));
			dateFormat = new SimpleDateFormat("yyyy-MM-dd", Utils.getCurrentLocale(context));
			timeFormat = new SimpleDateFormat("HH:mm:ss", Utils.getCurrentLocale(context));
			notConfirmed = String.format(" (%s)", context.getString(R.string.not_confirmed));
			itemView.setOnClickListener(this);
			itemView.setOnLongClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (onItemClickListener == null) return;
			onItemClickListener.onItemClick(getAdapterPosition());
		}

		@Override
		public boolean onLongClick(View v) {
			if (onItemLongClickListener == null) return false;
			onItemLongClickListener.onItemLongClick(getAdapterPosition());
			return true;
		}
	}
}
