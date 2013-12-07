/**
 * 
 */
package com.scr.utilities.contactsbackup;

/**
 * @author Scr_Ra
 *
 */
public class UIPageHandler
{
	public static enum Pages { ReadContactsFragment, SendEmailFragment };
	public static java.lang.Class [] PagesClasses = { ReadContactsFrament.class,SendEmailFragment.class}; 
	public static String FragmentId = "FragmentId";
}
