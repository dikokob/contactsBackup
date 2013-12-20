/**
 * 
 */
package com.scr.utilities.contactsbackup;

import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;

import com.scr.utilities.IO.*;
import com.scr.utilities.contactsbackup.ThreadedReaderParameters.ReadingOptions;
import com.scr.mobile.phone.contacts.*;

/**
 * @author Scr_Ra
 *
 */
public class ReadContactsFrament extends Fragment implements OnClickListener
{
	private CheckBox _checkBoxSIMContacts, _checkBoxPhoneContacts;
	private String contactsFileName;
	private FileOutputStream _file;
	private AddressBook _contacts;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
	{
		Button _button;
		// Inflate the layout for this fragment
		View createdView = inflater.inflate(R.layout.fragment_readcontacts, container, false);
        _button = (Button)createdView.findViewById(R.id.buttonReadContacts);
		_button.setOnClickListener(this);
		this._checkBoxSIMContacts = (CheckBox)createdView.findViewById(R.id.checkBoxReadSIMContacts);
		this._checkBoxPhoneContacts = (CheckBox)createdView.findViewById(R.id.checkBoxReadPhoneContacts);
		
        return createdView;
    }
	
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this._contacts = new AddressBook();
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
		results = this.getActivity().getContentResolver().query( simUriQuery, projection, null, null, null);
		while( results.moveToNext())
		{
			contactName = results.getString( results.getColumnIndex("name"));
			contactNumber = results.getString( results.getColumnIndex("number"));
			contactTmp = new SIMContact(contactName, contactNumber);
			this._contacts._book.put( contactTmp._name, contactTmp);
		}
		results.close();
	}
	
	public void readContactsButtonPressed( View _view )
	{
		Thread _readerThread = null;
		ContactsReader _threadContactsReader = null;
		ThreadedReaderParameters _threadParameters = null;
		this._contacts._book.clear();
		_threadParameters = new ThreadedReaderParameters( this.getActivity(), _contacts, ReadingOptions.SIM);
		ProgressDialog readingDialog = new ProgressDialog( this.getActivity());
		readingDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		readingDialog.setIndeterminate(true);
		readingDialog.setProgress(0);
		readingDialog.setCancelable(false);
		readingDialog.setTitle("Fetching SIM contacts");
		_threadParameters._dialogReference = readingDialog;
		_threadContactsReader = new ContactsReader( _threadParameters);
		_readerThread = new Thread( _threadContactsReader );
		readingDialog.show();
		_readerThread.start();
		
		/*
		File folder = null;
		ObjectWrapper wrapper = new ObjectWrapper();
		Date dateNow = new Date();
		SimpleDateFormat dateFormatter =  new SimpleDateFormat("E_yyyyMMdd_HHmmss_SSSS_a");
		this._contacts._book.clear();
		this.contactsFileName = new String( "Contacts_" + dateFormatter.format(dateNow) + ".csv" );
		switch( Folder.MKDIR(MainActivity.ApplicationFolder, wrapper) )
		{
			case 0:
				folder = (File)wrapper.getReference();
				try
				{
					_file = new FileOutputStream(folder.getAbsolutePath() + "/" + this.contactsFileName);
					this._readSIMContacts(this._contacts);
					_file.write( this._contacts.toCSVFormat().getBytes());
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
							Toast.makeText(this.getActivity(), folder.getAbsolutePath() + "/" + this.contactsFileName + "created", Toast.LENGTH_SHORT).show();
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
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
		/*
				break;
			case -1:
				Toast.makeText( this.getActivity(), "Error creating the specified folder", Toast.LENGTH_SHORT).show();
				break;
			case -2:
				Toast.makeText( this.getActivity(), "Error writing into external storage, please check if the external storage is properly mounted", Toast.LENGTH_SHORT).show();
				Log.e("STORAGE_SERVICE", "Error writing into external storage, please check either if the external storage is properly mounted or if you have your manifest permissions correctly, try using <uses-permission android:name=\"android.permission.WRITE_EXTERNAL_STORAGE\" />");
				break;
		}
		if( this._checkBoxSIMContacts.isChecked() )
		{
			
		}
		*/
	}

	@Override
	public void onClick(View _v)
	{
		switch( _v.getId() )
		{
			case R.id.buttonReadContacts:
				this.readContactsButtonPressed(_v);
				break;
		}
	}
}
