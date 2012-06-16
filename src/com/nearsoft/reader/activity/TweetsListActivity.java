package com.nearsoft.reader.activity;

import static com.nearsoft.reader.activity.TwitterReaderActivity.searchParamsKey;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;

import com.nearsoft.reader.TwitterEntry;
import com.nearsoft.reader.TwitterReader;
import com.nearsoft.reader.adapter.TwitterArrayAdapter;

public class TweetsListActivity extends ListActivity {
	ProgressDialog _progressDialog;
	TwitterReader _twitterReader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prepareTweets();
	}

	private void prepareTweets() {
		_progressDialog = ProgressDialog.show(this, "",
				"Loading tweets, please wait...", false);

		Bundle bundle = this.getIntent().getExtras();
		String params = bundle.getString(searchParamsKey);

		_twitterReader = new TwitterReader(params, handler);
		Thread thread = new Thread(_twitterReader);
		thread.start();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			_progressDialog.dismiss();
			ArrayAdapter<TwitterEntry> adapter = 
					new TwitterArrayAdapter(TweetsListActivity.this, _twitterReader.getTweets());
			setListAdapter(adapter);
		}
	};
}
