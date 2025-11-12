package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
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

public class PracticalTest01Var08PlayActivity extends AppCompatActivity {
    private TextView riddleText;
    private EditText answerText;
    private Button checkButton;
    private Button backButton;
    private String correctAnswer;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.check_button) {
                var givenAnswer = answerText.getText().toString();
                if (givenAnswer.toLowerCase().strip() == correctAnswer) {
                    setResult(RESULT_OK, null);
                } else {
                    setResult(RESULT_CANCELED, null);
                }
            }
            if (view.getId() == R.id.back_button) {
                setResult(RESULT_CANCELED, null);
            }
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var08_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        riddleText = findViewById(R.id.riddle_text);
        answerText = findViewById(R.id.answer_text);
        checkButton = findViewById(R.id.check_button);
        backButton = findViewById(R.id.check_button);

        checkButton.setOnClickListener(buttonClickListener);
        backButton.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("RIDDLE") && intent.getExtras().containsKey("ANSWER")) {
            var riddle = intent.getStringExtra("RIDDLE");
            correctAnswer = intent.getStringExtra("ANSWER");

            riddleText.setText(riddle);
        } else {
            Toast.makeText(this, "invalid intent", Toast.LENGTH_LONG).show();
        }
    }
}