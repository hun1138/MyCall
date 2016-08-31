package com.phonebook.hun.mycall;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.phonebook.hun.mycall.Database.model.PhoneNumberInfo;

import java.util.ArrayList;

/**
 * Created by hun on 16. 7. 25.
 */
public class PhoneBookHandler {

    private static Cursor getContactCursor(Context context) {
        String[] strContactProjection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };

        return context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                strContactProjection,
                null, null, null);
    }

    private static Cursor getPhoneNumberCursor(Context context, String id) {
        String[] strPhoneNumberProjection = new String[] {
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        return context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                strPhoneNumberProjection,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                null, null);
    }

    private static Cursor getEmailCursor(Context context, String id) {
        String[] strEmailProjection = new String[] {
                ContactsContract.CommonDataKinds.Email.DATA
        };

        return context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                strEmailProjection,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                null, null);
    }

    private static Cursor getAddressCursor(Context context, String id) {
        String[] strAddressParam = new String[] {
                id,
                ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE
        };

        return context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",
                strAddressParam,
                null
        );
    }

    private static Cursor getMemoCursor(Context context, String id) {
        String[] strMemoParam = new String[] {
                id,
                ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE
        };

        return context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",
                strMemoParam,
                null
        );
    }

    public static ArrayList<PhoneNumberInfo> getContactsData(Context context) {
        int i, j = 0; // remove
        ArrayList<PhoneNumberInfo> arrayListTemp = new ArrayList<PhoneNumberInfo>();

        Cursor contactCursor = getContactCursor(context);

        while(contactCursor.moveToNext()) {
            Log.d("Phone_Number", "j : " + (j++));
            PhoneNumberInfo pTemp = new PhoneNumberInfo();

            // set id, name
            pTemp.setID(contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts._ID)));
            pTemp.setName(contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));

            Log.d("Phone_Number", "id : " + pTemp.getID());
            Log.d("Phone_Number", "name : " + pTemp.getName());

            Cursor phoneNumberCursor = getPhoneNumberCursor(context, pTemp.getID());

            i = 0; // remove
            // set phone number
            while(phoneNumberCursor.moveToNext()) {
                pTemp.addPhoneNumber(phoneNumberCursor.getString(phoneNumberCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                Log.d("Phone_Number", "phoneNumber : " + pTemp.getPhoneNumberByIndex(i++));
            }
            phoneNumberCursor.close();

            Cursor emailCursor = getEmailCursor(context, pTemp.getID());

            i = 0; // remove
            // set email
            while(emailCursor.moveToNext()) {
                pTemp.addEmail(emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
                Log.d("Phone_Number", "email : " + pTemp.getEmailByIndex(i++));
            }
            emailCursor.close();

            Cursor addressCursor = getAddressCursor(context, pTemp.getID());

            i = 0; // remove
            // set address
            while(addressCursor.moveToNext()) {
                pTemp.addAddress(addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX)));
                pTemp.addAddress(addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET)));
                pTemp.addAddress(addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY)));
                pTemp.addAddress(addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION)));
                pTemp.addAddress(addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE)));
                pTemp.addAddress(addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY)));
                pTemp.addAddress(addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE)));
                Log.d("Phone_Number", "address pobox : " + pTemp.getAddressByIndex(i++));
                Log.d("Phone_Number", "address street : " + pTemp.getAddressByIndex(i++));
                Log.d("Phone_Number", "address city : " + pTemp.getAddressByIndex(i++));
                Log.d("Phone_Number", "address region : " + pTemp.getAddressByIndex(i++));
                Log.d("Phone_Number", "address postcode : " + pTemp.getAddressByIndex(i++));
                Log.d("Phone_Number", "address country : " + pTemp.getAddressByIndex(i++));
                Log.d("Phone_Number", "address type : " + pTemp.getAddressByIndex(i++));
            }
            addressCursor.close();

            Cursor memoCursor = getMemoCursor(context, pTemp.getID());

            i = 0; // remove
            // set memo
            while(memoCursor.moveToNext()) {
                pTemp.addMemo(memoCursor.getString(memoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE)));
                Log.d("Phone_Number", "Memo : " + pTemp.getMemoByIndex(i++));
            }
            memoCursor.close();

            arrayListTemp.add(pTemp);
        }
        contactCursor.close();

        return arrayListTemp;
    }
}
