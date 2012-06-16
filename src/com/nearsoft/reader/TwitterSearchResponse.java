package com.nearsoft.reader;

import java.util.Collections;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TwitterSearchResponse {
	@SerializedName("results")
	private List<TwitterEntry> _tweets = Collections.emptyList();

	@SerializedName("refresh_url")
	private String _refreshUrl;

	public List<TwitterEntry> getTweets() {
		return _tweets;
	}

	public void setTweets(List<TwitterEntry> tweets) {
		_tweets = tweets;
	}

	public void setRefreshUrl(String refreshUrl) {
		_refreshUrl = refreshUrl;
	}

	public String getRefreshUrl() {
		return _refreshUrl;
	}
}