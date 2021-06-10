package ys.games.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityMultiplayer extends AppCompatActivity {
    String cp = "0";
    public static final String p1 = "p1";
    public static final String p2 = "p2";
    public static final String c_p = "cp";
//    public static final int  = ;

    public void firstmove(View view) {
        movesound.start();
        ImageView imgx = findViewById(R.id.x);
        imgx.setImageResource(0);
        ImageView imgo = findViewById(R.id.o);
        imgo.setImageResource(0);
        ImageView img = (ImageView) view;
        cp = img.getTag().toString();
        if(cp.equals("x")){
            imgx.setImageResource(R.drawable.ic_baseline_arrow_upward);
            cp = "0";
        } else {
            imgo.setImageResource(R.drawable.ic_baseline_arrow_upward);
            cp = "1";
        }

    }

    public void start(View view) {
        movesound.start();

        Intent intent = new Intent(this, Multiplayer.class);
        EditText text1 = findViewById(R.id.player1);
        EditText text2 = findViewById(R.id.player2);
        String player1 = text1.getText().toString();
        String player2 = text2.getText().toString();
        intent.putExtra(p1, player1);
        intent.putExtra(p2, player2);
        intent.putExtra(c_p, cp);
        startActivity(intent);
    }
    MediaPlayer movesound,winnersound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_multiplayer);
        movesound = MediaPlayer.create(this , R.raw.wieldingmusic);
        winnersound = MediaPlayer.create(this , R.raw.waterdropmusic);
        ImageView back = findViewById(R.id.imageButton);
        back.setOnClickListener(v -> {
            onBackPressed();
            movesound.start();
        });
    }
}