package com.example.hiteshb.projectmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hitesh b on 4/8/2017.
 */

public class DBHelper {
    public static final String DATABASE_NAME="PROJECT_MANAGEMENT";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_STUDENT = "STUDENT";
    public static final String S_KEY_ID = "id";
    public static final String S_USER = "USER_ID";
    public static final String S_EMAIL = "EMAIL";
    public static final String S_PASSWORD = "PASSWORD";


    public static final String TABLE_FACULTY = "FACULTY";
    public static final String F_KEY_ID = "_id";
    public static final String F_USER = "USER_ID";
    public static final String F_EMAIL = "EMAIL";
    public static final String F_PASSWORD = "PASSWORD";

    public static final String TABLE_STUDENT_PREFERENCE = "S_PREFERENCE";
    public static final String P_USER = "USER_ID";
    public static final String PRE = "PREFERENCE";
    public static final String PRE1 = "PREFERENCE1";
    public static final String PRE2 = "PREFERENCE2";
    public static final String PRE3 = "PREFERENCE3";
    public static final String PRE4 = "PREFERENCE4";
    public static final String PRE5 = "PREFERENCE5";
    public static final String PRE6 = "PREFERENCE6";
    public static final String PRE7 = "PREFERENCE7";
    public static final String PRE8 = "PREFERENCE8";
    public static final String PRE9 = "PREFERENCE9";
    public static final String PRE10 = "PREFERENCE10";

    public static final String TABLE_FACULTY_PREFERENCE = "F_PREFERENCE";
    public static final String P_FUSER = "USER_ID";
    public static final String PREF1 = "PREFERENCE_F1";
    public static final String PREF2 = "PREFERENCE_F2";
    public static final String PREF3 = "PREFERENCE_F3";
    public static final String PREF4 = "PREFERENCE_F4";
    public static final String PREF5 = "PREFERENCE_F5";

    //public static final String LOGIN_TYPE = "TYPE";



    private static final String studentTable = "create table " + TABLE_STUDENT + " (" + S_KEY_ID + " integer primary key " +
            "autoincrement , " + S_USER + " text not null, " + S_EMAIL + " text not null, " + S_PASSWORD + " text not null);";

    private static final String facultyTable = "create table " + TABLE_FACULTY + " (" + F_KEY_ID + " integer primary key " +
            "autoincrement , " + F_USER + " text not null, " + F_EMAIL + " text not null, " + F_PASSWORD + " text not null);";

    private static final String studentPreferenceTable="create table "+TABLE_STUDENT_PREFERENCE+" ("
            +P_USER+" text primary key not null, "
            + PRE+" text not null, "
            +PRE1+" text not null, "
            +PRE2+" text not null, "
            +PRE3+" text not null, "
            +PRE4+" text not null, "
            +PRE5+" text not null, "
            +PRE6+" text not null, "
            +PRE7+" text not null, "
            +PRE8+" text not null, "
            +PRE9+" text not null, "
            +PRE10+" text not null);";

    private static final String facultyPreferenceTable="create table "+TABLE_FACULTY_PREFERENCE+" ("
            +P_FUSER+" text primary key not null, "
            + PREF1+" text not null, "
            +PREF2+" text not null, "
            +PREF3+" text not null, "
            +PREF4+" text not null, "+
            PREF5+" text not null);";

    private SQLiteDatabase database;
    private final Context context;
    private MyDBAdapter helper;

    public DBHelper(Context context)
    {
        this.context = context;
        helper = new MyDBAdapter(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper open()
    {
        database=helper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        database.close();
    }


    public long insertData(setGetValues data) {
        ContentValues contentValues = new ContentValues();

        Log.d("1:",data.getType().toString());

        if(data.getType().toString().equals("Student"))
        {
            Log.d("2:",data.getType().toString());
            contentValues.put(S_USER,data.getUserId());
            contentValues.put(S_EMAIL,data.getEmail());
            contentValues.put(S_PASSWORD,data.getPassword());
            return database.insert(TABLE_STUDENT, null, contentValues);
        }
        else
        {
            Log.d("3:",data.getType().toString());
            contentValues.put(F_USER,data.getUserId());
            contentValues.put(F_EMAIL,data.getEmail());
            contentValues.put(F_PASSWORD,data.getPassword());
            return database.insert(TABLE_FACULTY, null, contentValues);
        }
    }

    public long insertStudentPreferences(setGetValues data)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(P_USER,data.getUser());
        contentValues.put(PRE,data.getPre());
        contentValues.put(PRE1,data.getPre1());
        contentValues.put(PRE2,data.getPre2());
        contentValues.put(PRE3,data.getPre3());
        contentValues.put(PRE4,data.getPre4());
        contentValues.put(PRE5,data.getPre5());
        contentValues.put(PRE6,data.getPre6());
        contentValues.put(PRE7,data.getPre7());
        contentValues.put(PRE8,data.getPre8());
        contentValues.put(PRE9,data.getPre9());
        contentValues.put(PRE10,data.getPre10());
        return database.insert(TABLE_STUDENT_PREFERENCE, null, contentValues);
    }

    public long insertFacultyPreferences(setGetValues data)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(P_FUSER,data.getUserF());
        contentValues.put(PREF1,data.getPreF1());
        contentValues.put(PREF2,data.getPreF2());
        contentValues.put(PREF3,data.getPreF3());
        contentValues.put(PREF4,data.getPreF4());
        contentValues.put(PREF5,data.getPreF5());
        return database.insert(TABLE_FACULTY_PREFERENCE,null,contentValues);
    }

    public boolean studentUserSearch(String user)
    {
        open();
        String query="select USER_ID from S_PREFERENCE";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do{
                String student=cursor.getString(0);
                if(student.equals(user))
                {
                    return true;
                }
            }while (cursor.moveToNext());
        }
        close();
        return false;
    }

    public boolean facultyUserSearch(String user)
    {
        open();
        String query="select USER_ID from F_PREFERENCE";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do{
                String faculty=cursor.getString(0);
                if(faculty.equals(user))
                {
                    return true;
                }
            }while (cursor.moveToNext());
        }
        close();
        return false;
    }

    public String studentSearchPass(String suser){
        open();
        String query="select USER_ID,PASSWORD from STUDENT";

        Cursor cursor=database.rawQuery(query,null);
        String stu_user,stu_pass;
        stu_pass="not found";
        if(cursor.moveToFirst()){
            do{
                stu_user=cursor.getString(0);
                if(stu_user.equals(suser)){
                    stu_pass=cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }
        close();
        return stu_pass;
    }

    public String facultySearchpass(String fuser){
        open();
        String query1="select USER_ID,PASSWORD from FACULTY";
        Cursor cursor1=database.rawQuery(query1,null);
        String fac_user,fac_pass;
        fac_pass="not found";
        if(cursor1.moveToFirst()){
            do{
                fac_user=cursor1.getString(0);
                if(fac_user.equals(fuser)){
                    fac_pass=cursor1.getString(1);
                    break;
                }
            }while (cursor1.moveToNext());
        }
        close();
        return fac_pass;
    }

    public List<setGetValues> getAllEntries() {
        List<setGetValues> datalist = new ArrayList<setGetValues>();
        String query = "SELECT * from F_PREFERENCE";
        Cursor cursor = database.rawQuery(query,null);
        //Log.d("helper1:", "##");
        if (cursor.moveToFirst()) {
            do {
                setGetValues data = new setGetValues();
                //Log.d("helper2:", "**");
                //data.setUserF(cursor.getString(0));
                data.setPreF1(cursor.getString(1));
                data.setPreF2(cursor.getString(2));
                data.setPreF3(cursor.getString(3));
                data.setPreF4(cursor.getString(4));
                data.setPreF5(cursor.getString(5));
                datalist.add(data);
            } while (cursor.moveToNext());
        }
        //Log.d("helper3:", "@@");
        return datalist;
    }


    public String searchGuide(String user)
    {
        user = "h";
        Log.d("USERd:",user);
        open();
        String query1 = "SELECT * from S_PREFERENCE where USER_ID like '"+user+"';";
        Cursor cursor1 = database.rawQuery(query1,null);

        //Cursor cursor1 = database.query(TABLE_STUDENT_PREFERENCE, new String[]{P_USER, PRE, PRE1, PRE2, PRE3, PRE4, PRE5, PRE6, PRE7, PRE8, PRE9, PRE10}
          //      ,P_USER+"=?",new String[]{String.valueOf(user)},null,null,null);

       // String u = cursor1.getString(cursor1.getColumnIndex(user));
        String query2 = "SELECT * from F_PREFERENCE";
        Cursor cursor2 = database.rawQuery(query2, null);

        //Log.d("Student",cursor1.getString(0));
        if(cursor1!=null && cursor1.moveToFirst())
        {
            for(int i=1;i<=11;i++)
            {
                if(cursor2.moveToFirst())
                {
                    do {
                        for(int j=1;j<=5;i++)
                        {
                            if(cursor1.getString(i).equals(cursor2.getString(j)))
                            {
                                Log.d("preferenceS",cursor1.getString(i));
                                Log.d("preferenceF",cursor2.getString(j));
                                //allotProject project = new allotProject();
                                //project.passProject(cursor1.getString(i));
                                Log.d("faculty",cursor2.getString(0));
                                return cursor2.getString(0);
                            }
                        }
                    } while (cursor2.moveToNext());
                }
            }
        }
        close();
        return "Please give the Correct Entry";
    }


    private class MyDBAdapter extends SQLiteOpenHelper{
        public MyDBAdapter(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
            super(context,databaseName,factory,databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(studentTable);
            sqLiteDatabase.execSQL(facultyTable);
            sqLiteDatabase.execSQL(studentPreferenceTable);
            sqLiteDatabase.execSQL(facultyPreferenceTable);
            //database=sqLiteDatabase;
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            Log.v("Updating","DataBase is updating");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENT);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_FACULTY);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENT_PREFERENCE);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_FACULTY_PREFERENCE);
            onCreate(sqLiteDatabase);
        }

    }
}
