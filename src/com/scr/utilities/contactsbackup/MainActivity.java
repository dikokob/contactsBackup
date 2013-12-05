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

import com.scr.utilities.IO.*;

public class MainActivity extends Activity
{
	private String contactsFileName;
	private TextView _statusTextView;
	private static String ApplicationFolder = "EZContactsBackup";
	private FileOutputStream _file;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_statusTextView = ( TextView) findViewById(R.id.statusTextView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void _readSIMContacts(OutputStream _stream)
	{
		String contactName = null, contactNumber = null;
		String [] projection = new String[]
				{
					Contacts.DISPLAY_NAME,
					ContactsContract.CommonDataKinds.Phone.NUMBER
				};
		String sortOrder       = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
		Uri simUriQuery = Uri.parse("content://icc/adn");
		Cursor results;
		FileOutputStream _writer = (FileOutputStream)_stream;
		results = this.getContentResolver().query( simUriQuery, projection, null, null, Contacts.DISPLAY_NAME + " ASC");
		while( results.moveToNext())
		{
			contactName = results.getString( results.getColumnIndex("name"));
			contactNumber = results.getString( results.getColumnIndex("number"));
			try
			{
				_writer.write( contactName.getBytes());
				_writer.write( contactNumber.getBytes());
				_writer.write('\r');
				_writer.write( '\n');
			}
			catch(IOException ex)
			{
				Log.e(STORAGE_SERVICE, ex.getMessage());
			}
		}
	}
	
	public void readContacts( View _view )
	{
		File folder = null;
		ObjectWrapper wrapper = new ObjectWrapper();
		switch( Folder.MKDIR(MainActivity.ApplicationFolder, wrapper) )
		{
			case 0:
				this._statusTextView.setText( "Folder created: " + MainActivity.ApplicationFolder);
				folder = (File)wrapper.getReference();
				try
				{
					_file = new FileOutputStream(folder.getAbsolutePath() + "/asd.txt");
					this._readSIMContacts(_file);
				}
				catch(FileNotFoundException ex)
				{
					
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
				/*
				String ClsSimPhonename = null; String ClsSimphoneNo = null;
				
				ContentResolver _contentResolver = this.getContentResolver();
				Uri simUri = Uri.parse("content://icc/adn"); 
			    Cursor cursorSim = this.getContentResolver().query(simUri,null,null,null,null);

			    while (cursorSim.moveToNext()) 
			    {      
			        ClsSimPhonename =cursorSim.getString(cursorSim.getColumnIndex("name"));
			        ClsSimphoneNo = cursorSim.getString(cursorSim.getColumnIndex("number"));
			        ClsSimphoneNo.replaceAll("\\D","");
			        ClsSimphoneNo.replaceAll("&", "");
			        ClsSimPhonename=ClsSimPhonename.replace("|","");
			            System.out.println("SimContacts"+ClsSimPhonename);
			            System.out.println("SimContactsNo"+ClsSimphoneNo);
			            //dts.createDatabse("MyCellFamily",getApplicationContext());

			    }
			    */
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
