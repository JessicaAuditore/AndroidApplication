package com.soul.a94806.app13;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            }
        });
    }
}
