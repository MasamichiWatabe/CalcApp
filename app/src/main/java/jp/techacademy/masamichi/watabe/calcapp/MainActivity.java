package jp.techacademy.masamichi.watabe.calcapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText mEditText1;
    EditText mEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mEditText1 = (EditText)findViewById(R.id.editText1);
        mEditText2 = (EditText)findViewById(R.id.editText2);

        if (mEditText1.length() == 0 || mEditText2.length() == 0) {
            showAlertDialog();
        } else {

            String str1 = mEditText1.getText().toString();
            String str2 = mEditText2.getText().toString();

            try {
                double num1 = Double.parseDouble(str1);
                double num2 = Double.parseDouble(str2);

                Intent intent = new Intent(this, SecondActivity.class);
                switch (v.getId()) {
                    case R.id.button1:
                        intent.putExtra("VALUE", num1 + num2);
                        break;
                    case R.id.button2:
                        intent.putExtra("VALUE", num1 - num2);
                        break;
                    case R.id.button3:
                        intent.putExtra("VALUE", num1 * num2);
                        break;
                    case R.id.button4:
                        if (num2 == 0) {
                            showAlertDialogOfZero();
                            return;
                        } else {
                            intent.putExtra("VALUE", num1 / num2);
                        }
                        break;
                }
                startActivity(intent);

            } catch (NumberFormatException e) {
                showAlertDialog();
            }

        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("入力エラー");
        alertDialogBuilder.setMessage("数値を入力してください。");

        alertDialogBuilder.setPositiveButton("了解",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("UI_PARTS", "肯定ボタン");
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void showAlertDialogOfZero() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("入力エラー");
        alertDialogBuilder.setMessage("0での割り算はできません。");

        alertDialogBuilder.setPositiveButton("了解",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("UI_PARTS", "肯定ボタン");
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
