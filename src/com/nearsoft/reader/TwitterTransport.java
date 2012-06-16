package com.nearsoft.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.gson.Gson;

public class TwitterTransport {
	private static String _endpointUrl = "https://search.twitter.com/search.json?q=%s&result_type=mixed&rpp=10";

	private String buildSearchUrl(final String query) {
		try {
			return String.format(_endpointUrl,
					URLEncoder.encode(query, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			Log.e(getClass().getSimpleName(),
					"Invalid encoding: " + e.getMessage());
		}
		return null;
	}

	public TwitterSearchResponse send(final String query) {
		InputStream inputStream = sendHttpRequest(buildSearchUrl(query));
		if (inputStream != null) {
			Reader reader = new InputStreamReader(inputStream);
			Gson gson = new Gson();
			TwitterSearchResponse response = gson.fromJson(reader,
					TwitterSearchResponse.class);
			downloadAndSetImages(response.getTweets());
			return response;
		}
		return null;
	}

	private void downloadAndSetImages(List<TwitterEntry> tweets) {
		for (TwitterEntry entry : tweets) {
			entry.setImage(createImage(entry.getProfileImageUrl()));
		}

	}

	private InputStream sendHttpRequest(final String url) {
		if (url == null) {
			return null;
		}

		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);

		try {
			Log.i(getClass().getSimpleName(), "Sending request: " + url);
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.e(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();

		} catch (IOException e) {
			getRequest.abort();
			Log.e(getClass().getSimpleName(), "Error for URL " + url, e);
		}
		return null;
	}

	private Drawable createImage(String url) {
		try {
			InputStream is = (InputStream) this.fetch(url);
			Drawable d = Drawable.createFromStream(is, "src");
			return d;
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public Object fetch(String address) throws MalformedURLException,
			IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}
}
