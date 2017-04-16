package br.com.fiap.trabalhofinal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Julio on 14/04/2017.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user.db";
    private static final int VERSAO_BANCO = 7;
    private Context ctx;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSAO_BANCO);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = "CREATE TABLE TB_USER (ID_USER INTEGER PRIMARY KEY AUTOINCREMENT, LOGIN_USER TEXT, PASS_USER TEXT)";
        db.execSQL(sql);
        final String sql2 = "CREATE TABLE TB_MUSICIAN (ID_MUSICIAN INTEGER PRIMARY KEY AUTOINCREMENT, NAME_MUSICIAN TEXT, ARTISTICNM_MUSICIAN TEXT, BORN_MUSICIAN TEXT, NACIONALITY_MUSICIAN TEXT, INSTRUMENT_MUSICIAN TEXT, DESCRIPTION_MUSICIAN TEXT)";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TB_USER");
        db.execSQL("DROP TABLE IF EXISTS TB_MUSICIAN");
        onCreate(db);
    }
}
