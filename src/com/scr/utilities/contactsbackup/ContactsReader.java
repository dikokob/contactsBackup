/**
 * 
 */
package com.scr.utilities.contactsbackup;

import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.preference.DialogPreference;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.widget.ProgressBar;

import com.scr.mobile.phone.contacts.Contact;
import com.scr.mobile.phone.contacts.SIMContact;

/**
 * @author SQUADLAPBETA
 *
 */
public class ContactsReader implements Runnable
{
	public static enum Status { Reading, Writing, None };
	protected Status _currentStatus;
	protected ThreadedReaderParameters _readingParameters;
	protected int progress, goal;
	protected Handler _handler;
	protected UIProgressDialogUpdater _dialogUpdater;
	
	public ContactsReader( Object _readingOptions )
	{
		this._readingParameters = (ThreadedReaderParameters)_readingOptions;
		this._currentStatus = ContactsReader.Status.None;
		this._handler = new Handler();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		String contactName = null, contactNumber = null;
		Contact contactTmp = null;
		String [] projection = new String[]
				{
					Contacts.DISPLAY_NAME,
					ContactsContract.CommonDataKinds.Phone.NUMBER
				};
		Uri simUriQuery = Uri.parse("content://icc/adn");
		Cursor results;
		this._currentStatus = Status.Reading;
		results = this._readingParameters._activity.getContentResolver().query( simUriQuery, projection, null, null, null);
		this.goal = results.getCount();
		this.progress = 0;
		this._dialogUpdater = new UIProgressDialogUpdater(this._readingParameters._dialogReference, this.goal, 1, "Reading SIM contacts");
		while( results.moveToNext())
		{
			contactName = results.getString( results.getColumnIndex("name"));
			contactNumber = results.getString( results.getColumnIndex("number"));
			contactTmp = new SIMContact(contactName, contactNumber);
			this._readingParameters._bookReference._book.put( contactTmp._name, contactTmp);
			this.progress++;
			this._handler.post( this._dialogUpdater );
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		results.close();
	}
	
	protected void _updateTitle()
	{
		
	}

}
