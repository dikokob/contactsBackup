/**
 * 
 */
package com.scr.utilities.contactsbackup;

import com.scr.mobile.phone.contacts.AddressBook;

import android.app.Activity;
import android.view.View;

/**
 * @author ScrRa
 *
 */
public class ThreadedReaderParameters
{
	public static enum ReadingOptions { SIM, Phone, None };
	
	public ReadingOptions _readingOptions;
	public Activity _activity;
	public AddressBook _bookReference;
	public View _viewReference;
	
	public ThreadedReaderParameters( Activity _activityReference, AddressBook _book, View _view, ReadingOptions _options )
	{
		this._activity = _activityReference;
		this._readingOptions = _options;
		this._bookReference = _book;
		this._viewReference = _view;
	}
}
