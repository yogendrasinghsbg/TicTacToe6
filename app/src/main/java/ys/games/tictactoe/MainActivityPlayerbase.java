package ys.games.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static ys.games.tictactoe.Computer.findBestMove;


public class MainActivityPlayerbase extends AppCompatActivity {

    char currentPlayer='x';
    String winnername;
    Dialog myDialog;
    MediaPlayer movesound, winnersound;
    boolean active = true ,canTouch = true;
    int x = 0,o = 0;
    char[] board = {'_','_','_','_','_','_','_','_','_'};
    char[][] board2D = {{'_','_','_'},{'_','_','_'},{'_','_','_'}};

    int[][] positions = {{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
    int[][] winner = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDialog = new Dialog(this);
        setContentView(R.layout.activity_main_playerbase);
        movesound = MediaPlayer.create(this , R.raw.wieldingmusic);
        winnersound = MediaPlayer.create(this , R.raw.waterdropmusic);
        ImageView back = findViewById(R.id.imageButton);
        back.setOnClickListener(v -> {
            onBackPressed();
            movesound.start();
        });
        Intent intent = getIntent();
        String player1 = intent.getStringExtra(MainActivitySingleplayer.p1);
        String player2 = intent.getStringExtra(MainActivitySingleplayer.p2);

        TextView s1 = findViewById(R.id.textView4);
        TextView s2 = findViewById(R.id.textView5);


        s1.setText(player1);
        s2.setText(player2);
        currentPlayer = 'x';
        TextView turns = findViewById(R.id.status);
        String str = "X's Turn";
        turns.setText(str);
    }


    public void position(View view){
        if(canTouch) {
            canTouch = false;
            ImageView img = (ImageView) view;
            int pos = Integer.parseInt(img.getTag().toString());

            if (!active) {
                ShowPopup(winnername);
            } else if (board[pos] == '_') {
                String str;
                board[pos] = currentPlayer;
                int[] temparray = positions[pos];
                int tempvar1 = temparray[0], tempvar2 = temparray[1];
                board2D[tempvar1][tempvar2] = currentPlayer;
                img.setTranslationY(-1000f);
                if (currentPlayer == 'x') {
                    img.setImageResource(R.drawable.x);
                    movesound.start();
                    currentPlayer = 'o';
                    TextView turns = findViewById(R.id.status);
                    str = "X's Turn";
                    turns.setText(str);
                } else {
                    img.setImageResource(R.drawable.o);
                    movesound.start();
                    currentPlayer = 'x';
                    TextView turns = findViewById(R.id.status);
                    str = "AI's Turn";
                    turns.setText(str);
                }
                img.animate().translationYBy(1000f).setDuration(300);
                checkwinner();
                if (active && board[0] == '_' || board[1] == '_' ||
                        board[2] == '_' || board[3] == '_' ||
                        board[4] == '_' || board[5] == '_' ||
                        board[6] == '_' || board[7] == '_' ||
                        board[8] == '_') {
                    computermove();
                }
            }

            checkwinner();
        }
    }

    public void computermove(){

        if(active) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    movesound.start();
                    String str;
                    Computer.Move bestMove = findBestMove(board2D);

                    int[] temparr = {bestMove.row, bestMove.col};
                    ImageView img;
                    int i = 0;
                    for (int[] poss : positions) {
                        if (poss[0] == temparr[0] && poss[1] == temparr[1]) {
                            break;
                        }
                        i++;
                    }
                    board2D[temparr[0]][temparr[1]] = currentPlayer;
                    board[i] = currentPlayer;
                    if (currentPlayer == 'o') {
                        currentPlayer = 'x';
                        TextView turns = findViewById(R.id.status);
                        str = "X's Turn";
                        turns.setText(str);
                    } else {
                        currentPlayer = 'o';
                        TextView turns = findViewById(R.id.status);
                        str = "AI's Turn";
                        turns.setText(str);
                    }
                    switch (i) {
                        case 0:
                            img = findViewById(R.id.activity_view).findViewWithTag("0");
                            img.setImageResource(R.drawable.o);
                            break;
                        case 1:
                            img = findViewById(R.id.activity_view).findViewWithTag("1");
                            img.setImageResource(R.drawable.o);
                            break;
                        case 2:
                            img = findViewById(R.id.activity_view).findViewWithTag("2");
                            img.setImageResource(R.drawable.o);
                            break;
                        case 3:
                            img = findViewById(R.id.activity_view).findViewWithTag("3");
                            img.setImageResource(R.drawable.o);
                            break;
                        case 4:
                            img = findViewById(R.id.activity_view).findViewWithTag("4");
                            img.setImageResource(R.drawable.o);
                            break;
                        case 5:
                            img = findViewById(R.id.activity_view).findViewWithTag("5");
                            img.setImageResource(R.drawable.o);
                            break;
                        case 6:
                            img = findViewById(R.id.activity_view).findViewWithTag("6");
                            img.setImageResource(R.drawable.o);
                            break;
                        case 7:
                            img = findViewById(R.id.activity_view).findViewWithTag("7");
                            img.setImageResource(R.drawable.o);
                            break;
                        case 8:
                            img = findViewById(R.id.activity_view).findViewWithTag("8");
                            img.setImageResource(R.drawable.o);
                            break;
                    }

                    checkwinner();
                }
            }, 200);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                canTouch = true;
            }
        },300);
    }




    public void checkwinner(){
        TextView turns = findViewById(R.id.status);
        for(int[] win: winner){

            if(active && board[win[0]] == (board[win[1]]) &&
                    board[win[1]] == (board[win[2]]) &&
                    board[win[0]] != ('_')) {

                currentPlayer='x';
                active = false;
                String s;

                if (board[win[0]] == ('x')) {
                    winnername = "X";
                    winnersound.start();
                    x = x+1;
                    TextView winn = findViewById(R.id.statusx);
                    s = String.valueOf(x);
                    winn.setText(s);
                } else{
                    winnername = "O";
                    winnersound.start();
                    o = o+1;
                    TextView winn = findViewById(R.id.statuso);
                    s = String.valueOf(o);
                    winn.setText(s);
                }
                ShowPopup(winnername);
                break;

            }
        }
        if( active && board[0] != '_' && board[1] != '_' &&
                board[2] != '_' && board[3] != '_' &&
                board[4] != '_' && board[5] != '_' &&
                board[6] != '_' && board[7] != '_' &&
                board[8] != '_'){

            currentPlayer='x';
            winnername = "tie";
            active = false;
            ShowPopup(winnername);
            turns.setText(winnername);
            winnersound.start();
        }
    }

    @SuppressLint("SetTextI18n")
    public void ShowPopup(String s) {
        active = false;
        myDialog.setContentView(R.layout.popupstep);
        TextView txtclose = myDialog.findViewById(R.id.textclose);
        TextView txtreset = myDialog.findViewById(R.id.textreset);
        TextView txthome = myDialog.findViewById(R.id.texthome);
        TextView popresult = myDialog.findViewById(R.id.popupresult);
        ImageView img = myDialog.findViewById(R.id.winnerimg);
        switch (s) {
            case "X":
                img.setImageResource(R.drawable.x);
                break;
            case "O":
                img.setImageResource(R.drawable.o);
                break;
            case "tie":
                img.setImageResource(R.drawable.ic_emoticon);
                popresult.setText("MATCH IS TIE");
                break;
        }

        txtclose.setOnClickListener(v1 -> {
            reset(v1);
            movesound.start();
            myDialog.dismiss();
        });
        txtreset.setOnClickListener(v1 -> {
            reset(v1);
            movesound.start();
            myDialog.dismiss();
        });
        txthome.setOnClickListener(v1 -> {
            movesound.start();
            Intent intent = new Intent(this, MainActivityhome.class);
            startActivity(intent);
            myDialog.dismiss();
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void reset(View view) {
        for(int i=0 ; i<9 ; i++){
            board[i] = '_';
        }
        for(int i=0 ; i<3 ; i++){
            for(int j=0 ; j<3 ; j++){
                board2D[i][j]='_';
            }
        }
        currentPlayer = 'x';
        active = true;
        canTouch = true;
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView turns = findViewById(R.id.status);
        String str;
        str = "Player's Turn";
        turns.setText(str);

    }
}