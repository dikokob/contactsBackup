/**
 * 
 */
package com.scr.utilities.IO;

/**
 * @author Scr_Ra
 *
 */
public class ObjectWrapper
{
	protected Object _ref;
	
	public ObjectWrapper()
	{
		this._ref = null;
	}
	
	public ObjectWrapper( Object _reference )
	{
		this._ref = _reference;
	}
	
	public void setReference( Object _reference )
	{
		this._ref = _reference;
	}
	
	public Object getReference()
	{
		return this._ref;
	}
}
