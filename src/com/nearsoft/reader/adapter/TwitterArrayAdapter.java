package com.nearsoft.reader.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nearsoft.reader.R;
import com.nearsoft.reader.TwitterEntry;

public class TwitterArrayAdapter extends ArrayAdapter<TwitterEntry>{
	private final Activity _context;
	private final List<TwitterEntry> _tweets;
	
	public TwitterArrayAdapter(Activity context, List<TwitterEntry> tweets) {
		super(context, R.layout.tweet_item, tweets);
		_context = context;
		_tweets = tweets;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = _context.getLayoutInflater();
			view = inflator.inflate(R.layout.tweet_item, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.author = (TextView) view.findViewById(R.id.tweetAuthor);
			viewHolder.title = (TextView) view.findViewById(R.id.tweetTitle);
			
			view.setTag(viewHolder);
		} else {
			view = convertView;
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.author.setText(_tweets.get(position).getAuthor());
		holder.title.setText(_tweets.get(position).getTitle());
		return view;
	}
	
	static class ViewHolder {
		protected TextView author;
		protected TextView title;
	}
}
