package com.example.recyclerview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class listeFelderAdapter extends RecyclerView.Adapter<listeFelderAdapter.listeViewHolder> {
    private listeFelder listeFelder;
    private OnFeldListener onFeldListener;

    public listeFelderAdapter(listeFelder listeFelder, OnFeldListener onFeldListener){
        this.listeFelder = listeFelder;
        this.onFeldListener = onFeldListener;
    }

    @NonNull
    @Override
    public listeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listeFelderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feld, parent, false);
        return new listeViewHolder(listeFelderView);
    }

    @Override
    public void onBindViewHolder(@NonNull listeViewHolder holder, int position) {
        holder.bind(listeFelder.getFelder().get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return this.listeFelder.getFelder().size();
    }
    public List<Feld> getListeFelder(){
        return this.listeFelder.getFelder();
    }
    public void setListeFelder(listeFelder listeFelder) {
        this.listeFelder = listeFelder;
        notifyDataSetChanged();
    }
    public listeFelder getFelder(){
        return this.listeFelder;
    }

    public class listeViewHolder extends RecyclerView.ViewHolder{
        TextView valueTextView;

        public listeViewHolder(@NonNull View itemView){
            super(itemView);
            valueTextView = itemView.findViewById(R.id.feld_value);
        }
        public void bind(final Feld feld){
            itemView.setBackgroundColor(Color.GRAY);

            itemView.setOnClickListener((view -> {
                onFeldListener.onFeldClick(feld);
            }));
            if(!feld.isOpened()&&feld.isFlagged()){
                valueTextView.setText(R.string.flag);
            }
            if(feld.isOpened()){
                if(feld.isBomb()){
                    itemView.setBackgroundColor(Color.WHITE);
                    valueTextView.setText(R.string.bomb);
                }
                else{
                switch(feld.getAnliegendeBomben()) {
                    case 1:
                        valueTextView.setText(R.string.one);
                        itemView.setBackgroundColor(Color.BLUE);
                        break;
                    case 2:
                        valueTextView.setText(R.string.two);
                        itemView.setBackgroundColor(Color.GREEN);
                        break;
                    case 3:
                        valueTextView.setText(R.string.three);
                        itemView.setBackgroundColor(Color.RED);
                        break;
                    default:
                        for(Feld temp : listeFelder.anliegendeFelder(feld.getX(), feld.getY()))
                            if(temp.getAnliegendeBomben()==0&&!temp.isBomb()){
                                temp.setOpened(true);
                            }
                        valueTextView.setText("");
                        itemView.setBackgroundColor(Color.LTGRAY);
                        break;
                    }
                }
            }
        }
    }
}
