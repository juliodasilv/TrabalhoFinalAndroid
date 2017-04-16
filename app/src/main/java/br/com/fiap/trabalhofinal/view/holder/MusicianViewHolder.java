package br.com.fiap.trabalhofinal.view.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.fiap.trabalhofinal.activity.MusicianActivity;
import br.com.fiap.trabalhofinal.R;
import br.com.fiap.trabalhofinal.dao.MusicianDAO;
import br.com.fiap.trabalhofinal.model.Musician;
import br.com.fiap.trabalhofinal.view.adapter.MusicianAdapter;

/**
 * Created by Julio on 15/04/2017.
 */
public class MusicianViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    public final TextView artisticName;
    public final TextView detail;
    private Long musicianId;
    public final MusicianAdapter adapter;


    public MusicianViewHolder(final View view, final MusicianAdapter adapter) {
        super(view);
        this.adapter = adapter;

        view.setOnLongClickListener(this);

        artisticName = (TextView) view.findViewById(R.id.tvArtisticName);
        detail = (TextView) view.findViewById(R.id.tvDetail);
    }

    public void fill(Musician musician) {
        musicianId = musician.getId();
        artisticName.setText(musician.getArtisticName());
        detail.setText(musician.getName() + " - " + musician.getInstrument());
    }

    @Override
    public boolean onLongClick(final View v) {
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.activity_action_drawer, popup.getMenu());

        final Activity context = (Activity) v.getContext();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menuEditar:

                        final Intent intent = new Intent(context, MusicianActivity.class);
                        intent.putExtra("musicianId", musicianId);
                        intent.putExtra("code", MusicianActivity.CODE_UPDATE);
                        context.startActivityForResult(intent, MusicianActivity.CODE_UPDATE);

                        break;

                    case R.id.menuDeletar:
                        MusicianDAO musicianDAO = new MusicianDAO(context);
                        musicianDAO.delete(musicianId);
                        adapter.remove(getAdapterPosition());
                        break;
                }

                return true;
            }
        });

        popup.show();
        return false;
    }
}
