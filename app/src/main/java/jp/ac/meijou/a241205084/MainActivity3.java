package jp.ac.meijou.a241205084;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    private TextView textViewResult;
    private StringBuilder input = new StringBuilder();
    private double operand1 = 0;
    private String operator = "";
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewResult = findViewById(R.id.textViewResult);
        int[] numberIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        for (int i = 0; i < numberIds.length; i++) {
            Button btn = findViewById(numberIds[i]);
            int finalI = i;
            btn.setOnClickListener(v -> onNumberClick(finalI));
        }
        findViewById(R.id.buttonPlus).setOnClickListener(v -> onOperatorClick("+"));
        findViewById(R.id.buttonMinus).setOnClickListener(v -> onOperatorClick("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(v -> onOperatorClick("×"));
        findViewById(R.id.buttonDivide).setOnClickListener(v -> onOperatorClick("÷"));
        findViewById(R.id.buttonEqual).setOnClickListener(v -> onEqualClick());
        findViewById(R.id.buttonC).setOnClickListener(v -> onClearClick());
    }

    private void onNumberClick(int num) {
        if (isNewInput) {
            input.setLength(0);
            isNewInput = false;
        }
        input.append(num);
        textViewResult.setText(input.toString());
    }

    private void onOperatorClick(String op) {
        if (input.length() > 0) {
            operand1 = Double.parseDouble(input.toString());
            operator = op;
            isNewInput = true;
        }
    }

    private void onEqualClick() {
        if (operator.isEmpty() || input.length() == 0) return;
        double operand2 = Double.parseDouble(input.toString());
        double result = 0;
        switch (operator) {
            case "+": result = operand1 + operand2; break;
            case "-": result = operand1 - operand2; break;
            case "×": result = operand1 * operand2; break;
            case "÷": result = operand2 != 0 ? operand1 / operand2 : 0; break;
        }
        textViewResult.setText(String.valueOf(result));
        input = new StringBuilder(String.valueOf(result));
        operator = "";
        isNewInput = true;
    }

    private void onClearClick() {
        input.setLength(0);
        textViewResult.setText("0");
        operator = "";
        operand1 = 0;
        isNewInput = true;
    }
}