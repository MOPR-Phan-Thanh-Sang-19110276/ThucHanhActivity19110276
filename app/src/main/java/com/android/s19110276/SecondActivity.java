package com.android.s19110276;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView textView_tienLai;
    private TextView textView_tongTien;
    private Button btn_takePic;

    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;

    double soTienGui, laiSuatGui, kyHanGui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView_tienLai = (TextView) findViewById(R.id.textView_tienLai);
        textView_tongTien = (TextView) findViewById(R.id.textView_tongTien);
        btn_takePic = (Button) findViewById(R.id.btn_takePic);

        loadContent();

        btn_takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void loadContent() {
        Intent intent = this.getIntent();

        soTienGui = Double.parseDouble(intent.getStringExtra("soTienGui"));
        laiSuatGui = Double.parseDouble(intent.getStringExtra("laiSuatGui"));
        kyHanGui = Double.parseDouble(intent.getStringExtra("kyHanGui"));

        double soTienLai = soTienGui * (laiSuatGui / 100.0) * (kyHanGui / 12.0);
        double soTienTongNhan = soTienLai + soTienGui;

        textView_tienLai.setText(Double.toString(soTienLai) + " đ");
        textView_tongTien.setText(Double.toString(soTienTongNhan) + " đ");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        data.putExtra("soTienGui", Double.toString(soTienGui));
        data.putExtra("laiSuatGui", Double.toString(laiSuatGui));
        data.putExtra("kyHanGui", Double.toString(kyHanGui));

        setResult(Activity.RESULT_OK, data);
        finish();
    }
}