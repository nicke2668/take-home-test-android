package com.aerofs.takehometest.presentation;

import java.util.List;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aerofs.takehometest.model.GithubRepo;
import com.aerofs.takehometest.R;

/**
 * Created by Nicholas Emerson
 */
public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoAdapter.ViewHolder> {

	private List<GithubRepo> repos;

	protected static class ViewHolder extends RecyclerView.ViewHolder {

		protected TextView repoName;

		protected ViewHolder(TextView repoName) {
			super(repoName);
			this.repoName = repoName;
		}
	}

	public GithubRepoAdapter(List<GithubRepo> repos) {
		this.repos = repos;
	}

	@NonNull
	@Override
	public GithubRepoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		TextView view = (TextView) LayoutInflater.from(parent.getContext())
				.inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.repoName.setText(repos.get(position).getName());
	}

	@Override
	public int getItemCount() {
		return repos.size();
	}
}
