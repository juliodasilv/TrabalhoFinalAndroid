package br.com.fiap.trabalhofinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.fiap.trabalhofinal.R;
import br.com.fiap.trabalhofinal.dao.MusicianDAO;
import br.com.fiap.trabalhofinal.model.Musician;

public class MusicianActivity extends AppCompatActivity {

    public static final int CODE_NEW = 1002;
    public static final int CODE_UPDATE = 1003;

    private TextView id;
    private TextInputLayout tilName;
    private TextInputLayout tilArtisticName;
    private TextInputLayout tilBorn;
    private TextInputLayout tilNacionality;
    private TextInputLayout tilInstrument;
    private TextInputLayout tilDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician);

        tilName = (TextInputLayout) findViewById(R.id.tilName);
        tilArtisticName = (TextInputLayout) findViewById(R.id.tilArtisticName);
        tilBorn = (TextInputLayout) findViewById(R.id.tilBorn);
        tilNacionality = (TextInputLayout) findViewById(R.id.tilNacionality);
        tilInstrument = (TextInputLayout) findViewById(R.id.tilInstrument);
        tilDescription = (TextInputLayout) findViewById(R.id.tilDescription);

        if(getIntent().getIntExtra("code", CODE_NEW) == CODE_UPDATE){
            MusicianDAO musicianDAO = new MusicianDAO(this);
            Musician musician = musicianDAO.findById(getIntent().getLongExtra("musicianId", 0L));

            id = (TextView) findViewById(R.id.hidden_value);
            id.setText(String.valueOf(musician.getId()).toString());
            tilName.getEditText().setText(musician.getName());
            tilArtisticName.getEditText().setText(musician.getArtisticName());
            tilBorn.getEditText().setText(musician.getBorn());
            tilNacionality.getEditText().setText(musician.getNacionality());
            tilInstrument.getEditText().setText(musician.getInstrument());
            tilDescription.getEditText().setText(musician.getDescription());
        }

    }

    public void insert(View v) {
        MusicianDAO musicianDAO = new MusicianDAO(this);

        Musician musician = new Musician();
        musician.setName(tilName.getEditText().getText().toString());
        musician.setArtisticName(tilArtisticName.getEditText().getText().toString());
        musician.setBorn(tilBorn.getEditText().getText().toString());
        musician.setNacionality(tilNacionality.getEditText().getText().toString());
        musician.setInstrument(tilInstrument.getEditText().getText().toString());
        musician.setDescription(tilDescription.getEditText().getText().toString());

        if(getIntent().getIntExtra("code", CODE_NEW) == CODE_UPDATE) {
            musician.setId(Long.parseLong(id.getText().toString()));
        }

        if (musicianDAO.add(musician) == -1) {
            Toast.makeText(MusicianActivity.this, R.string.insert_error, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MusicianActivity.this, R.string.insert_success, Toast.LENGTH_LONG).show();
        }

        retornaParaTelaAnterior();
    }

    //retorna para tela de lista de torcedores
    public void retornaParaTelaAnterior() {
        Intent intentMessage=new Intent();
        setResult(CODE_NEW, intentMessage);
        finish();
    }
}
