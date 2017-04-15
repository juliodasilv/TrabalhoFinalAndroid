package br.com.fiap.trabalhofinal.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.fiap.trabalhofinal.R;
import br.com.fiap.trabalhofinal.model.Musician;
import br.com.fiap.trabalhofinal.view.holder.MusicianViewHolder;

/**
 * Created by Julio on 14/04/2017.
 */

public class MusicianAdapter extends RecyclerView.Adapter {
    private List<Musician> list;
    private Context context;

    public MusicianAdapter(List<Musician> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.musician_item_list, parent, false);
        MusicianViewHolder holder = new MusicianViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MusicianViewHolder viewHolder = (MusicianViewHolder) holder;
        Musician musician  = list.get(position);

        ((MusicianViewHolder) holder).fill(musician);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
