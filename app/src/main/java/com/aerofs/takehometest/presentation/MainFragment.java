package com.aerofs.takehometest.presentation;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aerofs.takehometest.services.GitHubClient;
import com.aerofs.takehometest.model.GithubRepo;
import com.aerofs.takehometest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nicholas Emerson
 */
public class MainFragment extends Fragment {

	private final Retrofit.Builder builder = new Builder()
			.baseUrl("https://api.github.com")
			.addConverterFactory(GsonConverterFactory.create());
	private final Retrofit retrofit = builder.build();
	private final GitHubClient gitHubClient = retrofit.create(GitHubClient.class);
	private EditText editText;
	private RecyclerView recyclerView;
	private ProgressBar progressBar;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		editText = view.findViewById(R.id.editText);
		progressBar = view.findViewById(R.id.progressBar);
		recyclerView = view.findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		return view;
	}

	@SuppressWarnings("unused")
	public void onFindReposClicked(View view) {
		String username = editText.getText().toString();
		if (username.trim().isEmpty()) {
			Toast.makeText(getActivity(), "The username cannot be empty.", Toast.LENGTH_LONG).show();
			return;
		}
		runRetrievalService(username);
		startProgressBar();
	}

	protected void startProgressBar() {
		progressBar.setVisibility(View.VISIBLE);
	}

	protected void runRetrievalService(String username) {
		Call<List<GithubRepo>> call = gitHubClient.reposForUser(username);
		call.enqueue(new Callback<List<GithubRepo>>() {
			@Override
			public void onResponse(@NonNull Call<List<GithubRepo>> call, @NonNull Response<List<GithubRepo>> response) {
				List<GithubRepo> repos = response.body();
				if(null == repos){
					showErrorMessage();
					cancelProgressBar();
					return;
				}
				recyclerView.setAdapter(new GithubRepoAdapter(repos));
				cancelProgressBar();
			}

			@Override
			public void onFailure(@NonNull Call<List<GithubRepo>> call, @NonNull Throwable throwable) {
				showErrorMessage();
				Log.e(this.getClass().getSimpleName(), throwable.getLocalizedMessage());
			}
		});
	}

	protected void showErrorMessage() {
		Toast.makeText(getActivity(), "Error retrieving repository information.", Toast.LENGTH_LONG).show();
	}

	protected void cancelProgressBar() {
		progressBar.setVisibility(View.GONE);
	}
}
