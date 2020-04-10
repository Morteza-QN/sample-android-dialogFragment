package com.example.sampledialogfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MyDialog.MyDialogEventListener {

    TextView textView;
    Button   showDialogBtn;
    MyDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("DialogFragment");

        textView      = findViewById(R.id.tv_main);
        showDialogBtn = findViewById(R.id.btn_main_dialog);
        showDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = MyDialog.newInstance(textView.getText().toString());
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    public void onOkButtonClicked(String data) {
        textView.setText(data);
    }

    @Override
    public void onCancelButtonClicked() {
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
    }
}
