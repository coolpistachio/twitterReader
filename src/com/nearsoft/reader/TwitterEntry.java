package com.nearsoft.reader;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TwitterEntry implements Serializable {

	private String _title;
	private String _author;

	public TwitterEntry() {
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String _author) {
		this._author = _author;
	}
}
