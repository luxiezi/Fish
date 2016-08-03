package com.fish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity{
	
	

	@Override
	protected void onCreate(@Nullable Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(getContentView());
		intiView();
		registerLister();
	}

	public abstract void registerLister();

	public abstract void intiView(); 

	public abstract int getContentView();
	
}
