package com.android.s19110276;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText_soTienGui;
    private EditText editText_laiSuatGui;
    private EditText editText_kyHanGui;
    private Button btn_xemKetQua;

    private static final int REQUEST_CODE_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editText_soTienGui = (EditText) findViewById(R.id.editText_soTienGui);
        this.editText_laiSuatGui = (EditText) findViewById(R.id.editText_laiSuatGui);
        this.editText_kyHanGui = (EditText) findViewById(R.id.editText_kyHanGui);
        this.btn_xemKetQua = (Button) findViewById(R.id.btn_xemKetQua);

        this.btn_xemKetQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xemKetQua();
            }
        });
    }

    private void xemKetQua() {
        String soTienGuiStr = this.editText_soTienGui.getText().toString();
        String laiSuatGuiStr = this.editText_laiSuatGui.getText().toString();
        String kyHanGuiStr = this.editText_kyHanGui.getText().toString();

        if (soTienGuiStr.trim().isEmpty() || laiSuatGuiStr.trim().isEmpty() || kyHanGuiStr.trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                double soTienGui = Double.parseDouble(soTienGuiStr);
                double laiSuatGui = Double.parseDouble(laiSuatGuiStr);
                double kyHanGui = Double.parseDouble(kyHanGuiStr);

                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("soTienGui", Double.toString(soTienGui));
                intent.putExtra("laiSuatGui", Double.toString(laiSuatGui));
                intent.putExtra("kyHanGui", Double.toString(kyHanGui));

                this.startActivityForResult(intent, REQUEST_CODE_RESULT);
            } catch (Exception e) {
                Toast.makeText(this, "Không đúng định dạng!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_RESULT) {
            if(resultCode == Activity.RESULT_OK) {
                editText_soTienGui.setText(data.getStringExtra("soTienGui"));
                editText_laiSuatGui.setText(data.getStringExtra("laiSuatGui"));
                editText_kyHanGui.setText(data.getStringExtra("kyHanGui"));
            } else {
            }
        }
    }
}