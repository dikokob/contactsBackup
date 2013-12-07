/**
 * 
 */
package com.scr.utilities.contactsbackup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Scr_Ra
 * @date December 6th, 2013
 *
 */
public class SendEmailFragment extends Fragment
{
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sendemail, container, false);
    }
}
