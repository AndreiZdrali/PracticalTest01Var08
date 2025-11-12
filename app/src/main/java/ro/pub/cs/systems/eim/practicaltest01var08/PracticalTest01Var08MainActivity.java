package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {
    private Button playButton;
    private EditText riddleText;
    private EditText answerText;
    private EditText lastResultText;

    private PlayButtonClickListener playButtonClickListener = new PlayButtonClickListener();
    private class PlayButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            var riddle = riddleText.getText().toString();
            var answer = answerText.getText().toString();

            if (!riddle.isEmpty() && !answer.isEmpty()) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08PlayActivity.class);
                intent.putExtra("RIDDLE", riddle);
                intent.putExtra("ANSWER", answer);
                startActivityForResult(intent, 108);
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var08_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playButton = findViewById(R.id.play_button);
        riddleText = findViewById(R.id.riddle_text);
        answerText = findViewById(R.id.answer_text);
        lastResultText = findViewById(R.id.lastresult_text);

        playButton.setOnClickListener(playButtonClickListener);

        SharedPreferences prefs = getSharedPreferences("Share", Context.MODE_PRIVATE );
        var won = prefs.getInt("VICTORY", 0);
        if (won == 1) {
            Toast.makeText(this, "Victory", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Fail", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 108) {
            SharedPreferences prefs = getSharedPreferences("Share", Context.MODE_PRIVATE );
            SharedPreferences.Editor editor = prefs.edit();
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Victory", Toast.LENGTH_LONG).show();
                editor.putInt("VICTORY", 1);

            } else {
                Toast.makeText(this, "Fail", Toast.LENGTH_LONG).show();
                editor.putInt("VICTORY", 0);

            }
            editor.commit();
        }
    }
}