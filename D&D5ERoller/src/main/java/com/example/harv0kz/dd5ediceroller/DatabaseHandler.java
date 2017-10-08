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
    private static final String KEY_LEVEL = "level";
    private static final String KEY_STRENGTH = "strength";
    private static final String KEY_DEXTERITY = "dexterity";
    private static final String KEY_CONSTITUTION = "constitution";
    private static final String KEY_INTELLIGENCE = "intelligence";
    private static final String KEY_WISDOM = "wisdom";
    private static final String KEY_CHARISMA = "charisma";
    private static final String KEY_SKILLPROFS = "skillprofs";
    private static final String KEY_EXPERTSKILLS = "expertskills";
    private static final String KEY_TOOLPROFS = "toolprofs";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_CHARACTER_TABLE ="CREATE_TABLE "+TABLE_CHARACTERS+"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_LEVEL+" TEXT,"+KEY_STRENGTH+" INTEGER,"+KEY_DEXTERITY+" INTEGER,"+KEY_CONSTITUTION+" INTEGER,"
                +KEY_INTELLIGENCE+" INTEGER,"+KEY_WISDOM+" INTEGER,"+KEY_CHARISMA+" INTEGER"+KEY_SKILLPROFS+" TEXT,"+KEY_EXPERTSKILLS+" TEXT,"+KEY_TOOLPROFS+" TEXT"+")";
        db.execSQL(CREATE_CHARACTER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CHARACTERS);
        onCreate(db);
    }

    //CRUD ops

    public void addCharacter(Character character){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, character.get_name());
        values.put(KEY_LEVEL, character.get_level());
        values.put(KEY_STRENGTH, character.get_strength());
        values.put(KEY_DEXTERITY, character.get_dexterity());
        values.put(KEY_CONSTITUTION, character.get_constitution());
        values.put(KEY_INTELLIGENCE, character.get_intelligence());
        values.put(KEY_WISDOM, character.get_wisdom());
        values.put(KEY_CHARISMA, character.get_charisma());
        values.put(KEY_SKILLPROFS, character.get_skillProfs());
        values.put(KEY_EXPERTSKILLS, character.get_expertSkills());
        values.put(KEY_TOOLPROFS, character.get_toolProfs());

        db.insert(TABLE_CHARACTERS, null, values);
        db.close();
    }

    public Character getCharacter(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CHARACTERS, new String[] {KEY_ID,KEY_NAME,KEY_LEVEL,KEY_STRENGTH,KEY_DEXTERITY,KEY_CONSTITUTION,KEY_INTELLIGENCE,KEY_WISDOM,KEY_CHARISMA,KEY_SKILLPROFS,KEY_EXPERTSKILLS,KEY_TOOLPROFS}, KEY_ID + "=?"
        , new String[] {String.valueOf(id)}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Character character = new Character(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),
                Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)),Integer.parseInt(cursor.getString(8)),cursor.getString(9),cursor.getString(10),cursor.getString(11));

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
        values.put(KEY_LEVEL, character.get_level());
        values.put(KEY_STRENGTH, character.get_strength());
        values.put(KEY_DEXTERITY, character.get_dexterity());
        values.put(KEY_CONSTITUTION, character.get_constitution());
        values.put(KEY_INTELLIGENCE, character.get_intelligence());
        values.put(KEY_WISDOM, character.get_wisdom());
        values.put(KEY_CHARISMA, character.get_charisma());
        values.put(KEY_SKILLPROFS, character.get_skillProfs());
        values.put(KEY_EXPERTSKILLS, character.get_expertSkills());
        values.put(KEY_TOOLPROFS, character.get_toolProfs());

        return db.update(TABLE_CHARACTERS, values, KEY_ID + " = ?", new String[] { String.valueOf(character.get_id())});

    }

    public void deleteCharacter(Character character){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHARACTERS, KEY_ID + " = ?",
                new String[] { String.valueOf(character.get_id()) });
        db.close();
    }

}
