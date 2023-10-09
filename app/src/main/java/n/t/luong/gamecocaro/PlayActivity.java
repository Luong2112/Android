package n.t.luong.gamecocaro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt1, txt2;
    Button[][] buttons = new Button[3][3];
    boolean player1Turn = true;
    int roundCount;
    int player1Point, player2Point;
    Button btnReset, btnExit;
    String txtOne, txtTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        txt1 = (TextView) findViewById(R.id.txtPlayer1);
        txt2 = (TextView) findViewById(R.id.txtPlayer2);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnExit = (Button) findViewById(R.id.btnExit);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String btnID = "btn" + i + j;
                int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                buttons[i][j] = (Button) findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        nhanDL();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.setText(txtOne + ": 0");
                txt2.setText(txtTwo + ": 0");
                resetBoard();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void nhanDL(){
        Intent intent = getIntent();
        txtOne = intent.getStringExtra("ten1");
        txtTwo = intent.getStringExtra("ten2");
        txt1.setText(txtOne + ": 0");
        txt2.setText(txtTwo + ": 0");
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }
        roundCount++;

        if(checkWin()){
            if(player1Turn){
                player1Win();
            }else{
                player2Win();
            }
        }else if(roundCount == 9){
            draw();
        }else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }
        return false;
    }

    private void player1Win(){
        player1Point++;
        Toast.makeText(PlayActivity.this, txtOne + " win!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void player2Win(){
        player2Point++;
        Toast.makeText(PlayActivity.this, txtTwo + " win!", Toast.LENGTH_LONG).show();
        updatePointText();
        resetBoard();
    }

    private void draw()
    {
        Toast.makeText(PlayActivity.this, "Draw!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void updatePointText(){
        nhanDL();
        txt1.setText(txtOne + ": " + player1Point);
        txt2.setText(txtTwo + ": " + player2Point);
    }

    private void resetBoard(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }
}