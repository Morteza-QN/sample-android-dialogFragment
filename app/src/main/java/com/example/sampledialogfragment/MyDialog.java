package com.example.sampledialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment implements View.OnClickListener {

    private static final String                TAG            = "MyDialog";
    private static final String                EXTRA_KEY_DATA = "data";
    private              Button                okBtn;
    private              Button                cancelBtn;
    private              EditText              inputEt;
    private              MyDialogEventListener myDialogEventListener;

    static MyDialog newInstance(String data) {
        //set data as mainActivity to  MyDialog
        Bundle args = new Bundle();
        args.putString(EXTRA_KEY_DATA, data);
        MyDialog myDialog = new MyDialog();
        myDialog.setArguments(args);
        return myDialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myDialogEventListener = (MyDialogEventListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View                view    = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, null, false);
        builder.setView(view);

        okBtn     = view.findViewById(R.id.btn_dialog_ok);
        cancelBtn = view.findViewById(R.id.btn_dialog_cancel);
        inputEt   = view.findViewById(R.id.et_dialog_input);

        if (getArguments() != null) {
            //get data as mainActivity to MyDialog
            String data = getArguments().getString(EXTRA_KEY_DATA);
            inputEt.setText(data);
        }

        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_ok:
                Log.i(TAG, "onClick: okBtn");
                if (!inputEt.getText().toString().trim().isEmpty()) {
                    myDialogEventListener.onOkButtonClicked(inputEt.getText().toString().trim());
                    dismiss();
                }
                else {
                    inputEt.setError("empty");
                    inputEt.requestFocus();
                }
                break;
            case R.id.btn_dialog_cancel:
                myDialogEventListener.onCancelButtonClicked();
                dismiss();
                Log.i(TAG, "onClick: cancelBtn");
                break;
        }
    }

    public interface MyDialogEventListener {
        //transaction data as MyDialog to mainActivity
        void onOkButtonClicked(String data);

        void onCancelButtonClicked();
    }
}
