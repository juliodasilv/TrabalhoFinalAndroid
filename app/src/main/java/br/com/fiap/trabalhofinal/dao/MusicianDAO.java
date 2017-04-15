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
    private static final String COLUMN_CPF = "CPF_MUSICIAN";
    private static final String COLUMN_INSTRUMENT = "INSTRUMENT_MUSICIAN";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION_MUSICIAN";

    public String add(Musician musician) {
        long result;
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, musician.getName());
        values.put(COLUMN_ARTISTIC_NAME, musician.getArtisticName());
        values.put(COLUMN_CPF, musician.getCpf());
        values.put(COLUMN_DESCRIPTION, musician.getDescription());
        values.put(COLUMN_INSTRUMENT, musician.getInstrument());
        result = db.insert(TABLE_MUSICIAN, null, values);
        db.close();

        if (result == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public void delete(long id) {
        SQLiteDatabase db = database.getWritableDatabase();
        db.delete(TABLE_MUSICIAN, "ID_MUSICIAN=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Musician> getAll() {
        List<Musician> list = new LinkedList<>();
        String rawQuery = "SELECT ID_MUSICIAN, NAME_MUSICIAN, CPF_MUSICIAN, ARTISTICNM_MUSICIAN, INSTRUMENT_MUSICIAN, DESCRIPTION_MUSICIAN FROM " + TABLE_MUSICIAN;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Musician musician = null;
        if (cursor.moveToFirst()) {
            do {
                musician = new Musician();
                musician.setId(cursor.getLong(0));
                musician.setName(cursor.getString(1));
                musician.setCpf(cursor.getString(2));
                musician.setArtisticName(cursor.getString(3));
                musician.setInstrument(cursor.getString(4));
                musician.setDescription(cursor.getString(5));
                list.add(musician);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
