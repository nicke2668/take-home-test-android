package com.aerofs.takehometest.services;

import java.util.List;

import com.aerofs.takehometest.model.GithubRepo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Nicholas Emerson
 */
public interface GitHubClient {

	@GET("users/{user}/repos")
	Call<List<GithubRepo>> reposForUser(@Path("user") String user);
}
