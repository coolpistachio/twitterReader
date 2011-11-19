package com.nearsoft.reader;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.Handler;

public class TwitterReader implements Runnable{
	private static String _endpointUrl = "http://search.twitter.com/search.atom?q=%s&rpp=25";	
	
	private String _searchUrl;
	private TwitterTransport _transport;
	private List<TwitterEntry> _tweets = new ArrayList<TwitterEntry>();
	private Handler _handler;
		
	public TwitterReader(String params, Handler handler) {
		_handler = handler;
		setSearchUrl(params);
	}

	private Document sendSearchQuery() {
		return getTransport().send();
	}
	
	private void sendAndReceiveTweets(){
		Document xmlResponse = sendSearchQuery();
		parseResponse(xmlResponse);
	}
	
	private void parseResponse(Document xmlResponse){
		NodeList nl = xmlResponse.getElementsByTagName("entry");
		
		int nlLenght = nl.getLength();
		
		for(int i = 0; i < nlLenght; i++) {
			
			TwitterEntry tweet = new TwitterEntry();
			Node entry = nl.item(i);
			NodeList props = entry.getChildNodes();			
			
			int propsLength = props.getLength();
			
			for(int j = 0; j < propsLength; j++){
				
				Node node = props.item(j);
				String nodeName = node.getNodeName();
				
				if("title".equalsIgnoreCase(nodeName)) {
					tweet.setTitle(node.getTextContent());
				} else if("author".equalsIgnoreCase(nodeName)) {
					tweet.setAuthor(node.getChildNodes().item(0).getTextContent());
				}
			}
			_tweets.add(tweet);
		}
	}
	
	private TwitterTransport getTransport() {
		if(_transport == null){
			_transport = new TwitterTransport(getSearchUrl());
		}
		return _transport;
	}
	
	public String getSearchUrl() {
		return _searchUrl;
	}

	void setSearchUrl(String params) {
		_searchUrl = String.format(_endpointUrl, params.replaceAll("[ ]", ""));
	}
	
	public List<TwitterEntry> getTweets(){
		return _tweets;
	}
	
	@Override
	public void run() {
		sendAndReceiveTweets();
		_handler.sendEmptyMessage(0);
	}
}