package br.com.fiap.trabalhofinal.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import br.com.fiap.trabalhofinal.R;
import br.com.fiap.trabalhofinal.dao.DBOpenHelper;

public class LoginActivity extends AppCompatActivity{

    private final String KEY_APP_PREFERENCES = "login";
    private final String KEY_LOGIN = "login";
    private TextInputLayout tilLogin;
    private TextInputLayout tilSenha;
    private CheckBox cbManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tilLogin = (TextInputLayout) findViewById(R.id.tilLogin);
        tilSenha = (TextInputLayout) findViewById(R.id.tilSenha);
        cbManterConectado = (CheckBox) findViewById(R.id.cbManterConectado);
        if (isConectado())
            iniciarApp();
    }

    //Método que será chamado no onclick do botao
    public void logar(View v) {
        if (isLoginValido()) {
            if (cbManterConectado.isChecked()) {
                manterConectado();
            }
            iniciarApp();
        }else{
            Toast.makeText(LoginActivity.this, R.string.incorrectLogin,  Toast.LENGTH_LONG).show();
        }
    }

    // Valida o login
    private boolean isLoginValido() {
        String login = tilLogin.getEditText().getText().toString();
        String senha = tilSenha.getEditText().getText().toString();

        DBOpenHelper database = new DBOpenHelper(this);
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from TB_USER", null);
        cursor.moveToFirst();

        if (login.equals(cursor.getString(cursor.getColumnIndex("LOGIN_USER")))
                && senha.equals(cursor.getString(cursor.getColumnIndex("PASS_USER")))) {
            return true;
        } else
            return false;
    }

    private void manterConectado() {
        String login = tilLogin.getEditText().getText().toString();
        SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_LOGIN, login);
        editor.apply();
    }

    private boolean isConectado() {
        SharedPreferences shared = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        String login = shared.getString(KEY_LOGIN, "");
        if (login.equals(""))
            return false;
        else
            return true;
    }

    private void iniciarApp() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}

