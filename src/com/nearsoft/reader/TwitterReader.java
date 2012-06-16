package com.nearsoft.reader;

import java.util.List;

import android.os.Handler;

public class TwitterReader implements Runnable {

	private String _query;
	private Handler _handler;
	private TwitterSearchResponse _response;
	
	public TwitterReader(String query, Handler handler) {
		_query = query;
		_handler = handler;
	}

	private TwitterSearchResponse searchForTweets() {
		return new TwitterTransport().send(_query);
	}

	public List<TwitterEntry> getTweets() {
		return _response.getTweets();
	}
		
	@Override
	public void run() {
		_response = searchForTweets();
		_handler.sendEmptyMessage(0);
	}
}