/**
 * 
 */
package com.scr.utilities.contactsbackup;

import android.app.ProgressDialog;

/**
 * @author SQUADLAPBETA
 *
 */
public class UIProgressDialogUpdater extends UIDialogUpdater
{

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		ProgressDialog _dialogRef = (ProgressDialog)this._dialogReference;
		_dialogRef.incrementProgressBy(1);
	}

}
