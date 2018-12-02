package com.soul.a94806.app13;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nameInsert = null;
    private EditText telephonyNo = null;
    private EditText emailAddr = null;
    private EditText nameQuery = null;
    private Button query = null;
    private Button insert = null;
    private ContentResolver resolver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameInsert = findViewById(R.id.name);
        telephonyNo = findViewById(R.id.telephone);
        emailAddr = findViewById(R.id.email);
        nameQuery = findViewById(R.id.nameQuery);
        insert = findViewById(R.id.insert);
        query = findViewById(R.id.query);
        resolver = this.getContentResolver();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                Uri rawContactUri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
                long rawContactId = ContentUris.parseId(rawContactUri);
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, nameInsert.getText().toString());
                resolver.insert(ContactsContract.Data.CONTENT_URI, values);
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, telephonyNo.getText().toString());
                values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                resolver.insert(ContactsContract.Data.CONTENT_URI, values);
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
                values.put(ContactsContract.CommonDataKinds.Email.DATA, emailAddr.getText().toString());
                values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
                resolver.insert(ContactsContract.Data.CONTENT_URI, values);
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.android.contacts/contacts");
                Cursor cursor = resolver.query(uri, null, null, null, null);
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    if (!name.equals(nameQuery.getText().toString())) {
                        continue;
                    }
                    StringBuilder sb = new StringBuilder();
                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    sb.append("contactId=").append(contactId).append(",name=").append(name);
                    Cursor phones = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
                    while (phones.moveToNext()) {
                        String phone = phones.getString(phones.getColumnIndex("data1"));
                        sb.append(",phone=").append(phone);
                    }
                    Cursor emails = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contactId, null, null);
                    while (emails.moveToNext()) {
                        String email = emails.getString(emails.getColumnIndex("data1"));
                        sb.append(",email=").append(email);
                    }
                    Log.i("contacts", sb.toString());
                }
            }
        });
    }
}
