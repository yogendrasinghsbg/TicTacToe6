package ys.games.tictactoe;

        import androidx.appcompat.app.AppCompatActivity;

        import android.annotation.SuppressLint;
        import android.app.Dialog;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

public class Multiplayer extends AppCompatActivity {

    String currentPlayer,resetcp;
    String winnername;
    Dialog myDialog;
    MediaPlayer movesound, winnersound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDialog = new Dialog(this);
        setContentView(R.layout.activity_multiplayer);
        movesound = MediaPlayer.create(this , R.raw.wieldingmusic);
        winnersound = MediaPlayer.create(this , R.raw.waterdropmusic);
        ImageView back = findViewById(R.id.imageButton);
        back.setOnClickListener(v -> {
            onBackPressed();
            movesound.start();
        });
        Intent intent = getIntent();
        String player1 = intent.getStringExtra(MainActivityMultiplayer.p1);
        String player2 = intent.getStringExtra(MainActivityMultiplayer.p2);
        String current = intent.getStringExtra(MainActivityMultiplayer.c_p);

        TextView s1 = findViewById(R.id.textView4);
        TextView s2 = findViewById(R.id.textView5);


        s1.setText(player1);
        s2.setText(player2);

        if (current.equals("0")){
            currentPlayer = "x";
            resetcp = "x";
            TextView turns = findViewById(R.id.status);
            String str = "X's Turn";
            turns.setText(str);
        }
        else if(current.equals("1")){
            currentPlayer = "o";
            resetcp = "o";
            TextView turns = findViewById(R.id.status);
            String str = "O's Turn";
            turns.setText(str);
        }
    }



    boolean active = true ;
    int x = 0,o = 0;
    String[] board = {"_", "_", "_", "_", "_", "_", "_", "_", "_"};
    int[][] winner = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};


    public void position(View view){
        movesound.start();
        ImageView img = (ImageView) view;
        int pos = Integer.parseInt(img.getTag().toString());

        if(!active){
            ShowPopup(winnername);
        }

        else if(board[pos].equals("_")) {
            String str;
            board[pos] = currentPlayer;
            img.setTranslationY(-1000f);
            if (currentPlayer.equals("x")) {
                img.setImageResource(R.drawable.x);
//                movesound.start();
                currentPlayer = "o";
                TextView turns = findViewById(R.id.status);
                str = "O's Turn";
                turns.setText(str);
            } else {
                img.setImageResource(R.drawable.o);
//                movesound.start();
                currentPlayer = "x";
                TextView turns = findViewById(R.id.status);
                str = "X's Turn";
                turns.setText(str);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        checkwinner();
    }


    public void checkwinner(){
        TextView turns = findViewById(R.id.status);
        for(int[] win: winner){

            if(active && board[win[0]].equals(board[win[1]]) &&
                    board[win[1]].equals(board[win[2]]) &&
                    !board[win[0]].equals("_")) {

                active = false;
                String s;

                if (board[win[0]].equals("x")) {
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
        if( active && !board[0].equals("_") && !board[1].equals("_") &&
                !board[2].equals("_") && !board[3].equals("_") &&
                !board[4].equals("_") && !board[5].equals("_") &&
                !board[6].equals("_") && !board[7].equals("_") &&
                !board[8].equals("_")){

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
            board[i] = "_";
        }
        active = true;
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
        if (currentPlayer.equals("x")){
            str = "X's Turn";
        }
        else {
            str = "O's Turn";
        }
        turns.setText(str);

    }
}
