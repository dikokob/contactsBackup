package com.scr.utilities.contactsbackup;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.scr.utilities.IO.*;
import com.scr.mobile.phone.contacts.*;

public class MainActivity extends Activity
{
	private String contactsFileName;
	private TextView _statusTextView;
	private TextView _scanStatusTextView;
	private static String ApplicationFolder = "EZContactsBackup";
	private FileOutputStream _file;
	private AddressBook _contacts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this._contacts = new AddressBook();
		_statusTextView = ( TextView) findViewById(R.id.statusTextView);
		this._scanStatusTextView = ( TextView ) findViewById(R.id.scanTextView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void _readSIMContacts(AddressBook _book)
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
		results = this.getContentResolver().query( simUriQuery, projection, null, null, null);
		while( results.moveToNext())
		{
			contactName = results.getString( results.getColumnIndex("name"));
			contactNumber = results.getString( results.getColumnIndex("number"));
			contactTmp = new SIMContact(contactName, contactNumber);
			this._contacts._book.put( contactTmp._name, contactTmp);
		}
		results.close();
	}
	
	public void readContacts( View _view )
	{
		File folder = null;
		ObjectWrapper wrapper = new ObjectWrapper();
		Date dateNow = new Date();
		SimpleDateFormat dateFormatter =  new SimpleDateFormat("E_yyyyMMdd_HHmmss_SSSS_a");
		this._contacts._book.clear();
		this.contactsFileName = new String( "Contacts_" + dateFormatter.format(dateNow) + ".csv" );
		switch( Folder.MKDIR(MainActivity.ApplicationFolder, wrapper) )
		{
			case 0:
				
				this._statusTextView.setText( "Folder created: " + MainActivity.ApplicationFolder);
				folder = (File)wrapper.getReference();
				try
				{
					_file = new FileOutputStream(folder.getAbsolutePath() + "/" + this.contactsFileName);
					this._readSIMContacts(this._contacts);
					_file.write( this._contacts.toCSVFormat().getBytes());
					this._scanStatusTextView.setText(folder.getAbsolutePath() + "/" + this.contactsFileName);
				}
				catch(FileNotFoundException ex)
				{
					ex.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					if( _file != null)
					{
						try
						{
							_file.flush();
							_file.close();
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				/*Cursor _cursor = _contentResolver.query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
				while (_cursor.moveToNext())
				{
					String name =_cursor.getString(_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));    
					String phoneNumber = _cursor.getString(_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					System.out.println(name + "   " + phoneNumber);
		        }
				*/
				break;
			case -1:
				this._statusTextView.setText( "Error creating the specified folder");
				break;
			case -2:
				this._statusTextView.setText( "Error writing into external storage, please check if the external storage is properly mounted");
				Log.e(STORAGE_SERVICE, "Error writing into external storage, please check either if the external storage is properly mounted or if you have your manifest permissions correctly, try using <uses-permission android:name=\"android.permission.WRITE_EXTERNAL_STORAGE\" />");
				break;
		}
	}

}
