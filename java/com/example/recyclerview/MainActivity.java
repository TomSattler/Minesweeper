package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.InputDevice;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnFeldListener{
    RecyclerView recyclerView;
    listeFelderAdapter listeFelderAdapter;
    Minesweeper minesweeper;
    TextView smiley, timer, flag;
    EditText size, bombs;
    boolean flagMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context =this;
        this.smiley = findViewById(R.id.start_button);
        this.timer = findViewById(R.id.Timer);
        this.bombs = findViewById(R.id.amount_bombs);
        this.size = findViewById(R.id.size_InputField);
        this.flag = findViewById(R.id.flag_Button);

        this.smiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Minesweeper game = new Minesweeper(getIntFromText(size,3,15), getIntFromText(bombs,1,getIntFromText(size,3,15)*getIntFromText(size,3,15)));
                    recyclerView.setLayoutManager(new GridLayoutManager(context, getIntFromText(size,3,15)));
                    listeFelderAdapter.setListeFelder(game.getListeFelder());//wait 0 ms before doing the action and do it evry 1000ms (1second)
                }
                catch(Exception e){

                }
            }
        });

        this.flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flagMode){
                    flag.setBackgroundColor(Color.GREEN);
                }
                if(flagMode){
                    flag.setBackgroundColor(Color.WHITE);
                }
                flagMode = !flagMode;
            }
        });

        this.minesweeper = new Minesweeper(10, 20);
        this.recyclerView = findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 10));
        this.listeFelderAdapter = new listeFelderAdapter(minesweeper.getListeFelder(), this);
        recyclerView.setAdapter(this.listeFelderAdapter);

    }

    @Override
    public void onFeldClick(Feld feld) {
        boolean spielGewonnen = true;
        if(!flagMode) {
            if (feld.isBomb()) {
                listeFelder temp = this.listeFelderAdapter.getFelder();
                for (Feld i : temp.getFelder()) {
                    i.setOpened(true);
                }
                Toast.makeText(this, "Spiel verloren", Toast.LENGTH_SHORT).show();
            } else {
                feld.setOpened(true);
                for(Feld temp:this.listeFelderAdapter.getFelder().getFelder()){
                    if(!temp.isBomb()&&!temp.isOpened()){
                        spielGewonnen = false;
                    }
                }
                if(spielGewonnen){
                    for(Feld temp:this.listeFelderAdapter.getFelder().getFelder()){
                        temp.setOpened(true);
                        Toast.makeText(this, "Spiel Gewonnen", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }else{
            feld.setFlagged(!feld.isFlagged());
        }
        this.listeFelderAdapter.notifyDataSetChanged();
    }
    public int getIntFromText(EditText eT, int min, int max) throws Exception{
        int x = 0;
        try{
            String value= eT.getText().toString();
            x=Integer.parseInt(value);
            if(x<min||x>max){
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Bitte geben sie eine gültige Zahl ein", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return x;
    }
}