package com.singhkamal.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class myDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="ContactsDB";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_CONTACT="contacts";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String  KEY_PHONE_NO="phone_no";

    public myDB(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the contacts table
        String createTable = "CREATE TABLE " + TABLE_CONTACT + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT," +
                KEY_PHONE_NO + " TEXT" +
                ")";
        db.execSQL(createTable);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( " DROP TABLE IF EXISTS " + TABLE_CONTACT );
        onCreate(db);
    }

    public void addContact(String name, String phone_no){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE_NO, phone_no);
        db.insert(TABLE_CONTACT, null, values);
    }
public ArrayList<ContactModel>fetchContact(){

        SQLiteDatabase db=this.getReadableDatabase();
       Cursor cursor= db.rawQuery( "SELECT * FROM " + TABLE_CONTACT, null );
       ArrayList<ContactModel> arrContacts=new ArrayList<>();


       while (cursor.moveToNext()){
           ContactModel model=new ContactModel();
           model.id=cursor.getInt( 0 );
           model.name=cursor.getString( 1 );
           model.phone_no=cursor.getString( 2 );
           arrContacts.add( model );

       }
       return arrContacts;
}

// Update the data:
    public void updateContact(ContactModel contactModel) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PHONE_NO, contactModel.phone_no);

        database.update(TABLE_CONTACT, contentValues, KEY_ID + "=?", new String[]{String.valueOf(contactModel.id)});

}

//Delete the Data


    public void DeleteContact (int id){
        SQLiteDatabase database =this.getWritableDatabase();
        database.delete( TABLE_CONTACT, KEY_ID + " =? ", new String[]{String.valueOf( id )} );

    }

}
