/**
 * 
 */
package com.scr.utilities.contactsbackup;

import android.app.Dialog;
import android.app.ProgressDialog;

/**
 * @author SQUADLAPBETA
 *
 */
public class UIProgressDialogUpdater extends UIDialogUpdater
{
	protected int maxValue;
	protected int incrementStep;
	public UIProgressDialogUpdater( Dialog _ref, int _maxValue,int _increment )
	{
		this._dialogReference = _ref;
		ProgressDialog _dialogRef = (ProgressDialog)this._dialogReference;
		this.maxValue = _maxValue;
		this.incrementStep = _increment;
		_dialogRef.setMax( this.maxValue);
		_dialogRef.setProgress(0);
		_dialogRef.setIndeterminate(false);
	}
	
	public UIProgressDialogUpdater( Dialog _ref, int _maxValue,int _increment, String _newTitle )
	{
		this._dialogReference = _ref;
		ProgressDialog _dialogRef = (ProgressDialog)this._dialogReference;
		this.maxValue = _maxValue;
		this.incrementStep = _increment;
		_dialogRef.setTitle(_newTitle);
		_dialogRef.setMax( this.maxValue);
		_dialogRef.setProgress(0);
		_dialogRef.setIndeterminate(false);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		ProgressDialog _dialogRef = (ProgressDialog)this._dialogReference;
		_dialogRef.incrementProgressBy(this.incrementStep);
		if( _dialogRef.getProgress() >= this.maxValue )
			_dialogRef.dismiss();
	}
}
