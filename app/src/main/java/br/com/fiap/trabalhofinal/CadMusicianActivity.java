package br.com.fiap.trabalhofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.fiap.trabalhofinal.dao.MusicianDAO;
import br.com.fiap.trabalhofinal.model.Musician;

public class CadMusicianActivity extends AppCompatActivity {

    public static final int CODE_NEW = 1002;

    private TextInputLayout tilName;
    private TextInputLayout tilArtisticName;
    private TextInputLayout tilCpf;
    private TextInputLayout tilInstrument;
    private TextInputLayout tilDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_musician);

        tilName = (TextInputLayout) findViewById(R.id.tilName);
        tilArtisticName = (TextInputLayout) findViewById(R.id.tilArtisticName);
        tilCpf = (TextInputLayout) findViewById(R.id.tilCpf);
        tilInstrument = (TextInputLayout) findViewById(R.id.tilInstrument);
        tilDescription = (TextInputLayout) findViewById(R.id.tilDescription);
    }

    public void insert(View v) {
        MusicianDAO musicianDAO = new MusicianDAO(this);

        Musician musician = new Musician();
        musician.setName(tilName.getEditText().getText().toString());
        musician.setArtisticName(tilArtisticName.getEditText().getText().toString());
        musician.setCpf(tilCpf.getEditText().getText().toString());
        musician.setInstrument(tilInstrument.getEditText().getText().toString());
        musician.setDescription(tilDescription.getEditText().getText().toString());
        musicianDAO.add(musician);
        retornaParaTelaAnterior();
    }

    //retorna para tela de lista de torcedores
    public void retornaParaTelaAnterior() {
        Intent intentMessage=new Intent();
        setResult(CODE_NEW, intentMessage);
        finish();
    }
}
