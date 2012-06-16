package com.nearsoft.reader;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

public class TwitterEntry {

    @SerializedName("profile_image_url")
    private String _profileImageUrl;
    
    @SerializedName("created_at")
    private String _createdAt;
    
    @SerializedName("from_user_name")
    private String _fromUsername;

    @SerializedName("text")
    private String _text;
    
    private Drawable _image;
    
    public TwitterEntry() {
	}

    public String getProfileImageUrl() {
		return _profileImageUrl;
	}

	public void setProfileImageUrl(String _profileImageUrl) {
		this._profileImageUrl = _profileImageUrl;
	}
	
	public String getCreatedAt() {
		return _createdAt;
	}

	public void setCreratedAt(String createdAt) {
		_createdAt = createdAt;
	}
	
	public void setFromUsername(String fromUsername){
		_fromUsername = fromUsername;
	}
	
	public String getFromUsername(){
		return _fromUsername;
	}

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}

	public Drawable getImage() {
		return _image;
	}

	public void setImage(Drawable image) {
		_image = image;
	}
}
