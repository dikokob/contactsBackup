/**
 * 
 */
package com.scr.mobile.phone.contacts;

import com.scr.utilities.IO.Formatters.*;

/**
 * @author SQUADLAPBETA
 *
 */
public class SIMContact extends Contact implements ICSVFormatter
{
	public String _phoneNumber;
	/**
	 * 
	 */
	public SIMContact( String _name, String _phoneNumber)
	{
		super();
		this._name = _name;
		this._phoneNumber = _phoneNumber;
	}

	/**
	 * @param _id
	 */
	public SIMContact(int _id, String _name, String _phoneNumber )
	{
		super(_id);
		this._name = _name;
		this._phoneNumber = _phoneNumber;
	}

	@Override
	public String toCSVFormat()
	{
		return new String( this._id + "," + this._name + "," + this._phoneNumber + ";\n");
	}
}
