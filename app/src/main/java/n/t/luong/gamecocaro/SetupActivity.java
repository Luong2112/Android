package n.t.luong.gamecocaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetupActivity extends AppCompatActivity {

    Button btnPlay;
    EditText edit1;
    EditText edit2;

    // không để tên của hai người chơi trùng nhau
    // đếm thời gian chơi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        edit1 = (EditText) findViewById(R.id.editPlayer1);
        edit2 = (EditText) findViewById(R.id.editPlayer2);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten1 = edit1.getText().toString();
                String ten2 = edit2.getText().toString();

                if(ten1.length()<=0 || ten2.length()<=0)
                {
                    Toast.makeText(SetupActivity.this, "Bạn chưa nhập đủ tên người chơi!", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(SetupActivity.this, PlayActivity.class);

                    intent.putExtra("ten1", ten1 + "");
                    intent.putExtra("ten2", ten2+ "");

                    startActivity(intent);
                }
            }
        });
    }
}