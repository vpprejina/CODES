package com.felight.codeig;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Contact extends Activity {
    public static final int PICK_CONTACT    = 1;
    private Button btnContacts;
    private TextView txtContacts1;
    private TextView txtContacts2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        btnContacts = (Button) findViewById(R.id.btnContact);
        txtContacts1 = (TextView) findViewById(R.id.tvName);
        txtContacts2 = (TextView) findViewById(R.id.tvNumber);
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK, Contacts.People.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });
    }
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        //String link=
                        String name = c.getString(c.getColumnIndexOrThrow(Contacts.People.NAME));
                        String number=c.getString(c.getColumnIndexOrThrow(Contacts.People.NUMBER));
                        txtContacts1.setText(name);
                        txtContacts2.setText(number);
                    }
                }
                break;
        }
    }
}
