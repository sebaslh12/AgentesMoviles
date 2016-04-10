package Model.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebas on 10/04/2016.
 */
public class MessageDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "localmessages.db";
    private static final String TABLE_MESSAGES = "messages";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TO= "Mto";
    public static final String COLUMN_FROM= "Mfrom";
    public static final String COLUMN_TEXT= "Mtext";

    public MessageDB(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGES_TABLE = "CREATE TABLE " +
                TABLE_MESSAGES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement," + COLUMN_TO
                + " INTEGER," + COLUMN_FROM + " INTEGER,"+ COLUMN_TEXT + " TEXT" + ")";
        db.execSQL(CREATE_MESSAGES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        onCreate(db);
    }

    public void addMessage(ResultMessages Message){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TO, Message.getTo());
        values.put(COLUMN_FROM, Message.getFrom());
        values.put(COLUMN_TEXT, Message.getText());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_MESSAGES, null, values);
        db.close();
    }

    public ArrayList<String> findMessages(String from, String to) {
        String query = "Select * FROM " + TABLE_MESSAGES + " WHERE " + COLUMN_FROM + " = ? and " + COLUMN_TO + "= ?";
        SQLiteDatabase db = this.getWritableDatabase();
        //Binding
        Cursor cursor = db.rawQuery(query, new String[]{from, to});
        ArrayList<String> queryResult= new ArrayList<>();
        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            queryResult.add(cursor.getString(3));

            while (cursor.moveToNext()) {
                cursor.moveToNext();
                queryResult.add(cursor.getString(3));
            }
            cursor.close();
        }
        db.close();
        return queryResult;
    }
}
