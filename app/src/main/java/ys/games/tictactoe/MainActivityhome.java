package ys.games.tictactoe;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityhome extends AppCompatActivity {


    Dialog myDialog,myExitDialog;
    MediaPlayer movesound,winnersound;

    public void start(View view) {
        movesound.start();
        Intent intent = new Intent(this, MainActivityMultiplayer.class);
        startActivity(intent);
    }
    public void startwithAi(View view) {
        movesound.start();
        Intent intent = new Intent(this, MainActivitySingleplayer.class);
        startActivity(intent);
    }

    public void showPopup(View v) {

        winnersound.start();

        myDialog.setContentView(R.layout.popup_comming_soon);
        TextView txtclose = myDialog.findViewById(R.id.textclose);

        txtclose.setOnClickListener(v1 -> {
            movesound.start();
            myDialog.dismiss();
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDialog = new Dialog(this);
        myExitDialog = new Dialog(this);
        setContentView(R.layout.activity_main_home);
        movesound = MediaPlayer.create(this , R.raw.wieldingmusic);
        winnersound = MediaPlayer.create(this , R.raw.waterdropmusic);
    }

    @Override
    public void onBackPressed() {
        myExitDialog.setContentView(R.layout.exit_dialog);
        ImageButton yesbtn = myExitDialog.findViewById(R.id.yesButton);
        ImageButton nobtn = myExitDialog.findViewById(R.id.noButton);

        yesbtn.setOnClickListener(v -> finishAffinity());
        nobtn.setOnClickListener(v -> myExitDialog.cancel());

        myExitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myExitDialog.show();
    }
}