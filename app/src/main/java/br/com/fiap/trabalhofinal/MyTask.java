package br.com.fiap.trabalhofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.fiap.trabalhofinal.dao.DBOpenHelper;

/**
 * Created by Julio on 14/04/2017.
 */
public class MyTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] params) {
        String url = "http://www.mocky.io/v2/58b9b1740f0000b614f09d2f";

        InputStream content = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            // executa a requisição GET
            HttpResponse response = httpclient.execute(get);
            // obtém o conteúdo retornado pela requisição GET
            content = response.getEntity().getContent();

            //content está declarado no slide anterior !
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            StringBuilder str = new StringBuilder();
            // lê linha a linha a partir do InputStream
            String line = null;
            while ((line = reader.readLine()) != null)
                str.append(line + "\n");
            // retorno contém todo o conteúdo lido


            JSONObject jsonObject = new JSONObject(str.toString());
            String user = (String) jsonObject.get("usuario");
            String password = (String) jsonObject.get("senha");

            ContentValues values = new ContentValues();
            values.put("LOGIN_USER", user);
            values.put("PASS_USER", password);

            DBOpenHelper database = new DBOpenHelper((Context) params[0]);
            SQLiteDatabase db = database.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from TB_USER", null);
            if (!cursor.moveToFirst() ) {
                db.insert("TB_USER", null, values);
            }else{
               Log.d("OUT", String.format("LOGIN: %s\nPASS: %s\n\n",cursor.getString(cursor.getColumnIndex("LOGIN_USER")),cursor.getString(cursor.getColumnIndex("PASS_USER"))));
            }

            db.close();

            return str.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
