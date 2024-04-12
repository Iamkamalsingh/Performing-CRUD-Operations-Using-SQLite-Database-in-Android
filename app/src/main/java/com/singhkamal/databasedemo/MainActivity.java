package com.singhkamal.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        myDB db=new myDB( this );
/*
        db.addContact( "Kamal","79757811147" );
        db.addContact( "Janvi","79757811147" );
        db.addContact( "Singh","79757811147" );
        db.addContact( "Jnandu","79757811147" );
        db.addContact( "Kamal","79757811147" );
        db.addContact( "Rahul","79757811147" );

*/

        ContactModel model=new ContactModel();
        model.id=1;
        model.name="kamal";
        model.phone_no="123456789";

        db.updateContact(model);


        db.DeleteContact( 2 );


        ArrayList<ContactModel>arrContacts=db.fetchContact();
        for (int i=0; i<arrContacts.size(); i++)
            Log.d("CONTACT_INFO", "Name:" +arrContacts.get( i ).name + ", Phone No:" + arrContacts.get( i ).phone_no );
    }
}