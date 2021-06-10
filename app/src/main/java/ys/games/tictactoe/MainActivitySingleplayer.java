package ys.games.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivitySingleplayer extends AppCompatActivity {
    String cp = "0";
    public static final String p1 = "p1";
    public static final String p2 = "p2";
    public static final String c_p = "cp";
//    public static final int  = ;

    public void easyhard(View view) {
        movesound.start();
        ImageView imgx = findViewById(R.id.x);
        imgx.setImageResource(0);
        ImageView imgo = findViewById(R.id.o);
        imgo.setImageResource(0);
        TextView text = (TextView) view;
        cp = text.getTag().toString();
        if(cp.equals("0")){
            imgx.setImageResource(R.drawable.ic_baseline_arrow_upward);
        } else {
            imgo.setImageResource(R.drawable.ic_baseline_arrow_upward);
        }
    }

    public void start(View view) {

        movesound.start();
        Intent intent0 = new Intent(this, MainActivityAlbase.class);
        Intent intent1 = new Intent(this, MainActivityPlayerbase.class);
        EditText text1 = findViewById(R.id.player1);
        TextView text2 = findViewById(R.id.player2);
        String player1 = text1.getText().toString();
        String player2 = text2.getText().toString();
        if(cp.equals("0")){
            intent0.putExtra(p1, player1);
            intent0.putExtra(p2, player2);
            intent0.putExtra(c_p, cp);
            startActivity(intent0);
        } else {
            intent1.putExtra(p1, player1);
            intent1.putExtra(p2, player2);
            intent1.putExtra(c_p, cp);
            startActivity(intent1);
        }

    }

    MediaPlayer movesound,winnersound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_singleplayer);
        movesound = MediaPlayer.create(this , R.raw.wieldingmusic);
        winnersound = MediaPlayer.create(this , R.raw.waterdropmusic);
        ImageView back = findViewById(R.id.imageButton);
        back.setOnClickListener(v -> {
            onBackPressed();
            movesound.start();
        });
    }
}