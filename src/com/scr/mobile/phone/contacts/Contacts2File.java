/**
 * 
 */
package com.scr.mobile.phone.contacts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.scr.utilities.IO.*;

/**
 * @author SQUADLAPBETA
 *
 */
public class Contacts2File extends ContactsExporter
{

	/* (non-Javadoc)
	 * @see com.scr.mobile.phone.contacts.ContactsExporter#Export(com.scr.mobile.phone.contacts.AddressBook)
	 */
	@Override
	public boolean Export(AddressBook _bookSource)
	{
		File folder = null;
		String contactsFileName = null;
		ObjectWrapper wrapper = new ObjectWrapper();
		Date dateNow = new Date();
		SimpleDateFormat dateFormatter =  new SimpleDateFormat("E_yyyyMMdd_HHmmss_SSSS_a");
		contactsFileName = new String( "Contacts_" + dateFormatter.format(dateNow) + ".csv" );
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
		return false;
	}

}
