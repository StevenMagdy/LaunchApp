package com.steven.launchapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.steven.launchapp.models.Launch;
import com.steven.launchapp.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LaunchAdapter extends RecyclerView.Adapter<LaunchAdapter.LaunchHolder> {

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

	LaunchAdapter(List<Launch> launches, Context context) {
		this.launches = launches;
		this.context = context;
	}

	@Override
	public LaunchHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		return new LaunchHolder(LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.item_launch, viewGroup, false));
	}

	@Override
	public void onBindViewHolder(LaunchHolder launchHolder, int i) {
		launchHolder.nameTextView.setText(launches.get(i).getName());
		try {
			Date date = launchHolder.dateParserFormat.parse(launches.get(i).getNet());
			String notConfirmed = " (Not Confirmed)";
			launchHolder.dateTextView.setText(launchHolder.dateFormat.format(date).trim());
			if (launches.get(i).getTbdDate() == 1) launchHolder.dateTextView.append(notConfirmed);
			launchHolder.timeTextView.setText(launchHolder.timeFormat.format(date).trim());
			if (launches.get(i).getTbdTime() == 1) launchHolder.timeTextView.append(notConfirmed);
		} catch (ParseException e) {
			e.printStackTrace();
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

	public Launch getLaunch(int position) {
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

		private LaunchHolder(View itemView) {
			super(itemView);
			nameTextView = (TextView) itemView.findViewById(R.id.textView_name);
			dateTextView = (TextView) itemView.findViewById(R.id.textView_date);
			timeTextView = (TextView) itemView.findViewById(R.id.textView_time);
			dateParserFormat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss Z", Utils
					.getCurrentLocale(context));
			// dateParserFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			dateFormat = new SimpleDateFormat("yyyy-MM-dd", Utils.getCurrentLocale(context));
			timeFormat = new SimpleDateFormat("HH:mm:ss", Utils.getCurrentLocale(context));
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
