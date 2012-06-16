package com.nearsoft.reader.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.nearsoft.reader.R;
import com.nearsoft.reader.utils.StringUtils;

public class TwitterReaderActivity extends Activity implements
		View.OnClickListener {
	public static String searchParamsKey = "searchParams";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupListeners();
	}

	private void setupListeners() {
		View searchBtn = findViewById(R.id.btnSearchTweets);
		searchBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSearchTweets:
			String params = getQueryParams();
			if (!StringUtils.isEmpty(params)) {
				Intent intent = new Intent(this, TweetsListActivity.class);
				intent.putExtra(searchParamsKey, params);
				startActivity(intent);
			}
			break;
		}
	}

	private String getQueryParams() {
		EditText searchInput = (EditText) findViewById(R.id.txtSearchInput);
		return searchInput.getText().toString();
	}
}