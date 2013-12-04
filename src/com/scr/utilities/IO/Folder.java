/**
 * 
 */
package com.scr.utilities.IO;

import java.io.File;

import android.os.Environment;
import android.util.Log;

/**
 * @author Scr_Ra
 * @date December 3rd, 2013
 *
 */
public class Folder
{
	/*
	 * This method would create a folder with the specified name on the external storage
	 * @return 0 -> No error, -1 -> Can not create the folder, mkdir failed, -2 -> An error on the external storage
	 */
	public static int MKDIR( String _folderName)
	{
		String _storageStatus;
		File _fileManager;
		int _result = -2;
		_storageStatus = Environment.getExternalStorageState();
		if( Environment.MEDIA_MOUNTED.equals(_storageStatus) || Environment.MEDIA_SHARED.equals(_storageStatus))
		{
			_fileManager = new File(Environment.getExternalStorageDirectory().toString() + "/" + _folderName );
			if( !_fileManager.exists() )
			{
				if( _fileManager.mkdir() )
				{
					_result = 0;
				}
				else
				{
					_result = -1;
				}
			}
			else
			{
				_result = 0;
			}
		}
		return _result;
	}
}
