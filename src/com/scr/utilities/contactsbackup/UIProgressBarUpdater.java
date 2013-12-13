/**
 * 
 */
package com.scr.utilities.contactsbackup;

import android.widget.ProgressBar;

/**
 * @author ScrRa
 *
 */
public class UIProgressBarUpdater extends UIUpdater {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		ProgressBar _widgetRef = (ProgressBar)this._uiReference;
		_widgetRef.incrementProgressBy(1);
	}
}
