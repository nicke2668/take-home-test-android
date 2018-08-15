package com.aerofs.takehometest.presentation;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.aerofs.takehometest.R;

public class MainActivity extends Activity {

	private MainFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragment = findFragmentById(R.id.fragment);

	}

	@SuppressWarnings("unchecked")
	protected <T extends Fragment> T findFragmentById(int resId) {
		return (T) getFragmentManager().findFragmentById(resId);
	}

	public void onFindReposClicked(View view) {
		fragment.onFindReposClicked(view);
	}

}
