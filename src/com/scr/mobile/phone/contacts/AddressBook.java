package com.scr.mobile.phone.contacts;

import java.util.Iterator;
import java.util.TreeMap;

import com.scr.utilities.IO.Formatters.ICSVFormatter;

public class AddressBook implements ICSVFormatter
{
	public TreeMap< String, Contact> _book;
	
	public AddressBook()
	{
		this._book = new TreeMap<String, Contact>();
	}
	
	public void DebugToConsole()
	{
		Contact _reference = null;
		for( Iterator<Contact> it = this._book.values().iterator(); it.hasNext();)
		{
			_reference = it.next();
			System.out.println(_reference.toCSVFormat());
		}
	}

	@Override
	public String toCSVFormat()
	{
		StringBuffer _buffer = new StringBuffer();
		Contact _reference = null;
		for( Iterator<Contact> it = this._book.values().iterator(); it.hasNext();)
		{
			_reference = it.next();
			_buffer.append(_reference.toCSVFormat());
		}
		return _buffer.toString();
	}
}
