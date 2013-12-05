/**
 * 
 */
package com.scr.mobile.phone.contacts;

import com.scr.utilities.IO.Formatters.ICSVFormatter;

import android.util.Log;

/**
 * @author Scr_Ra
 * @date December 5th, 2013
 *
 */
public abstract class Contact implements ICSVFormatter
{
	protected static int IDCOUNTER;
	protected int _id;
	public String _name;
	
	public Contact()
	{
		this._id = Contact.IDCOUNTER++;
		this._name = null;
	}
	
	public Contact( int _id )
	{
		if( Contact.IDCOUNTER < _id )
			Contact.IDCOUNTER = _id;
		else
			Log.w("Contact id", "The provided id is minor thant the reference counter, so assigning the Contact.IDCOUNTER to the ContactId");
		this._id = Contact.IDCOUNTER++;
		this._name = null;
	}
	
	public int Id()
	{
		return this._id;
	}
	
	@Override public boolean equals( Object _obj )
	{
		Contact _contact;
		if( _obj == this )
			return true;
		if( _obj == null || _obj.getClass() != this.getClass() )
			return false;
		_contact = (Contact)_obj;
		return _contact._id == this._id;
	}
	
	@Override public int hashCode()
	{
		return this._id;
	}
}
