package com.nearsoft.reader.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nearsoft.reader.R;
import com.nearsoft.reader.TwitterEntry;

public class TwitterArrayAdapter extends ArrayAdapter<TwitterEntry> {
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
		setHolderData(position, view);
		return view;
	}

	private void setHolderData(int position, View view) {
		ViewHolder holder = (ViewHolder) view.getTag();
		
		String fromUsername = _tweets.get(position).getFromUsername();
		if (fromUsername != null) {
			holder.author.setText(fromUsername);
		}

		Drawable img = _tweets.get(position).getImage();
		if(img != null){
			img.setBounds( 0, 0, 60, 60 );
			holder.author.setCompoundDrawables(img, null, null, null);
		}
		
		String text = _tweets.get(position).getText();
		if (text != null) {
			holder.title.setText(text);
		}		
	}

	static class ViewHolder {
		protected TextView author;
		protected TextView title;
	}
}
