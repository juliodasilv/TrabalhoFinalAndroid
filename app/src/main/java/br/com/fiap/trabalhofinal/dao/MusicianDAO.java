package br.com.fiap.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import br.com.fiap.trabalhofinal.model.Musician;

/**
 * Created by julio on 13/04/2017.
 */
public class MusicianDAO {

    private SQLiteDatabase db;
    private DBOpenHelper database;

    public MusicianDAO(Context context) {
        database = new DBOpenHelper(context);
    }

    private static final String TABLE_MUSICIAN = "TB_MUSICIAN";
    private static final String COLUMN_ID = "ID_MUSICIAN";
    private static final String COLUMN_NAME = "NAME_MUSICIAN";
    private static final String COLUMN_ARTISTIC_NAME = "ARTISTICNM_MUSICIAN";
    private static final String COLUMN_BORN = "BORN_MUSICIAN";
    private static final String COLUMN_NACIONALITY = "NACIONALITY_MUSICIAN";
    private static final String COLUMN_INSTRUMENT = "INSTRUMENT_MUSICIAN";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION_MUSICIAN";

    public long add(Musician musician) {
        long result;
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, musician.getName());
        values.put(COLUMN_ARTISTIC_NAME, musician.getArtisticName());
        values.put(COLUMN_BORN, musician.getBorn());
        values.put(COLUMN_NACIONALITY, musician.getNacionality());
        values.put(COLUMN_DESCRIPTION, musician.getDescription());
        values.put(COLUMN_INSTRUMENT, musician.getInstrument());

        if(musician.getId() == 0L){
            result = db.insert(TABLE_MUSICIAN, null, values);
        }else{
            values.put(COLUMN_ID, musician.getId());
            result = db.update(TABLE_MUSICIAN, values, "ID_MUSICIAN=?", new String[]{String.valueOf(musician.getId())});
        }

        db.close();

        return result;
    }

    public void delete(long id) {
        SQLiteDatabase db = database.getWritableDatabase();
        db.delete(TABLE_MUSICIAN, "ID_MUSICIAN=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Musician> getAll() {
        List<Musician> list = new LinkedList<>();
        String rawQuery = "SELECT ID_MUSICIAN, NAME_MUSICIAN, BORN_MUSICIAN, NACIONALITY_MUSICIAN, ARTISTICNM_MUSICIAN, INSTRUMENT_MUSICIAN, DESCRIPTION_MUSICIAN FROM " + TABLE_MUSICIAN;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Musician musician = null;
        if (cursor.moveToFirst()) {
            do {
                musician = new Musician();
                musician.setId(cursor.getLong(0));
                musician.setName(cursor.getString(1));
                musician.setBorn(cursor.getString(2));
                musician.setNacionality(cursor.getString(3));
                musician.setArtisticName(cursor.getString(4));
                musician.setInstrument(cursor.getString(5));
                musician.setDescription(cursor.getString(6));
                list.add(musician);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public Musician findById(Long id) {
        String rawQuery = "SELECT ID_MUSICIAN, NAME_MUSICIAN, BORN_MUSICIAN, NACIONALITY_MUSICIAN, ARTISTICNM_MUSICIAN, INSTRUMENT_MUSICIAN, DESCRIPTION_MUSICIAN FROM " + TABLE_MUSICIAN + " where ID_MUSICIAN=" + id;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Musician musician = null;
        if (cursor.moveToFirst()) {
            do {
                musician = new Musician();
                musician.setId(cursor.getLong(0));
                musician.setName(cursor.getString(1));
                musician.setBorn(cursor.getString(2));
                musician.setNacionality(cursor.getString(3));
                musician.setArtisticName(cursor.getString(4));
                musician.setInstrument(cursor.getString(5));
                musician.setDescription(cursor.getString(6));
            } while (cursor.moveToNext());
        }
        return musician;
    }
}