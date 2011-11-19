package com.nearsoft.reader;

import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import android.util.Log;

public class TwitterTransport {
	String _searchUrl;
	
	public TwitterTransport(String searchUrl){
		_searchUrl = searchUrl;
	}
	
	public Document send(){
		Document doc= null;
		try {
			doc = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder()
					.parse(new URL(_searchUrl).openStream());
			return doc;
		} catch (Exception e) {
			Log.e("TwitterReader", e.getMessage());
		}
		return doc;
	}
}
