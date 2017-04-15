package br.com.fiap.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
    private static final String COLUMN_ID = "ID_MUSICO";
    private static final String COLUMN_NAME = "NAME_MUSICIAN";
    private static final String COLUMN_ARTISTIC_NAME = "ARTISTICNM_MUSICIAN";
    private static final String COLUMN_CPF = "CPF_MUSICIAN";
    private static final String COLUMN_INSTRUMENT = "CPF_INSTRUMENT";
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

    public void delete(int id) {
        SQLiteDatabase db = database.getWritableDatabase();
        db.delete(TABLE_MUSICIAN, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
