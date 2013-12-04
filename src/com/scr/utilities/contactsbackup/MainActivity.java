package com.scr.utilities.contactsbackup;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.scr.utilities.IO.*;

public class MainActivity extends Activity
{
	private String contactsFileName;
	private TextView _statusTextView;
	private static String ApplicationFolder = "EZContactsBackup";
	
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
	
	public void readContacts( View _view )
	{
		switch( Folder.MKDIR(MainActivity.ApplicationFolder) )
		{
			case 0:
				String ClsSimPhonename = null; String ClsSimphoneNo = null;
				this._statusTextView.setText( "Folder created: " + MainActivity.ApplicationFolder);
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
