package com.example.harv0kz.dd5ediceroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harv0kz on 8/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "databaseStorage";
    private static final String TABLE_CHARACTERS = "characters";

    //Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MAXPROFBONUS = "maxProfBonus";
    private static final String KEY_STATLINE = "statline";
    private static final String KEY_SKILLPROFS = "skillprofs";
    private static final String KEY_EXPERTSKILLS = "expertskills";
    private static final String KEY_TOOLPROFS = "toolprofs";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_CHARACTER_TABLE ="CREATE_TABLE "+TABLE_CHARACTERS+"("+KEY_ID+" INTEGER PRIMARY KEY,"
                +KEY_NAME+" TEXT,"
                +KEY_MAXPROFBONUS+" INTEGER,"
                +KEY_STATLINE+" TEXT,"
                +KEY_SKILLPROFS+" TEXT,"
                +KEY_EXPERTSKILLS+" TEXT,"
                +KEY_TOOLPROFS+" TEXT"+")";
        db.execSQL(CREATE_CHARACTER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CHARACTERS);
        onCreate(db);
    }

    public String intToCSV(int[] array){
        int lenarray = array.length;
        int count = 0;
        String csvForm = "";

        for(int value:array){
            csvForm = csvForm+Integer.toString(value);
            if(count==lenarray-1){
                break;
            }
            count++;
            csvForm = csvForm+",";
        }
        return csvForm;
    }

    public int[] csvToInt(String csv){
        String[] stringArray = csv.split(",");
        int[] array = new int[stringArray.length];

        for(int i = 0;i<stringArray.length;i++){
            array[i] = Integer.parseInt(stringArray[i]);
        }
        return array;
    }

    public String stringToCSV(String[] array){
        int lenarray = array.length;
        int count = 0;
        String csvForm = "";

        for(String word:array){
            csvForm = csvForm+word;
            if(count==lenarray-1){
                break;
            }
            count++;
            csvForm = csvForm+",";
        }
        return csvForm;
    }

    public String[] csvToString(String csv){
        String[] stringArray = csv.split(",");
        return stringArray;
    }

    //CRUD ops

    public void addCharacter(Character character){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, character.get_name());
        values.put(KEY_MAXPROFBONUS, character.get_maxProfBonus());
        values.put(KEY_STATLINE, intToCSV(character.get_statline()));
        values.put(KEY_SKILLPROFS, stringToCSV(character.get_skillProfs()));
        values.put(KEY_EXPERTSKILLS, stringToCSV(character.get_expertSkills()));
        values.put(KEY_TOOLPROFS, stringToCSV(character.get_toolProfs()));

        db.insert(TABLE_CHARACTERS, null, values);
        db.close();
    }

    public Character getCharacter(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CHARACTERS, new String[] {KEY_ID,KEY_NAME,KEY_MAXPROFBONUS,KEY_STATLINE,KEY_SKILLPROFS,KEY_EXPERTSKILLS,KEY_TOOLPROFS}, KEY_ID + "=?"
        , new String[] {String.valueOf(id)}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        //Int,String,Int,Int[],String[],String[],String[]
        Character character = new Character(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                csvToInt(cursor.getString(3)),
                csvToString(cursor.getString(4)),
                csvToString(cursor.getString(5)),
                csvToString(cursor.getString(6)));

        return character;
    }

    public List<Character> getAllCharacter(){
        List<Character> characterList = new ArrayList<Character>();

        return characterList;
    }

    public int updateCharacter(Character character){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, character.get_name());
        values.put(KEY_MAXPROFBONUS, character.get_maxProfBonus());
        values.put(KEY_STATLINE, intToCSV(character.get_statline()));
        values.put(KEY_SKILLPROFS, stringToCSV(character.get_skillProfs()));
        values.put(KEY_EXPERTSKILLS, stringToCSV(character.get_expertSkills()));
        values.put(KEY_TOOLPROFS, stringToCSV(character.get_toolProfs()));

        return db.update(TABLE_CHARACTERS, values, KEY_ID + " = ?", new String[] { String.valueOf(character.get_id())});

    }

    public void deleteCharacter(Character character){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHARACTERS, KEY_ID + " = ?",
                new String[] { String.valueOf(character.get_id()) });
        db.close();
    }

}
