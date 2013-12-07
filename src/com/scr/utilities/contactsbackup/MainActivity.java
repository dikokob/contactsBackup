package com.scr.utilities.contactsbackup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity
{
	public static String ApplicationFolder = "EZContactsBackup";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void readContacts( View _view )
	{
		Intent generalActivity = new Intent(this, GeneralActivity.class);
		generalActivity.putExtra(UIPageHandler.FragmentId, UIPageHandler.Pages.ReadContactsFragment.ordinal());
		startActivityForResult(generalActivity, 1);
	}
	
	public void sendEmailPressed( View _view )
	{
		Intent generalActivity = new Intent(this, GeneralActivity.class);
		generalActivity.putExtra(UIPageHandler.FragmentId, UIPageHandler.Pages.SendEmailFragment.ordinal());
		startActivityForResult(generalActivity, 1);
	}
}
