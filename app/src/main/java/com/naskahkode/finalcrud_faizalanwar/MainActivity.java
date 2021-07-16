package com.naskahkode.finalcrud_faizalanwar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private EditText nama,nrp;
    private Button btnUpload, btnSimpan, btnLihat;
    private ImageView foto;
    public static DBHelper sQliteHelper;
    final int REQUEST_CODE_GALLERY = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // object initialize
        nama = findViewById(R.id.nama);
        nrp = findViewById(R.id.nrp);
        btnUpload = findViewById(R.id.btnUpload);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnLihat = findViewById(R.id.btnLihat);
        foto = findViewById(R.id.foto);


        // SQlite database initialization & database & table create fuctions
        sQliteHelper = new DBHelper(this,"Akademik.sqlite",null,1);
        sQliteHelper.queryData("CREATE TABLE IF NOT EXISTS Mahasiswa (ID INTEGER PRIMARY KEY AUTOINCREMENT, nama VARCHAR, nrp VARCHAR, foto BLOG)");

        // Imagecoose button action
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Request Permission
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);

            }
        });

        // Add Food button Action
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    sQliteHelper.insertdata(

                            nama.getText().toString().trim(),
                            nrp.getText().toString().trim(),
                            imageViewToByte(foto)
                    );
                    // after execute before insert data method then show a messege to notify user & set all empty
                    Toast.makeText(MainActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                    nama.setText("");
                    nrp.setText("");
                    foto.setImageResource(R.drawable.placeholde);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        // Foodlist button Action to go food list activity
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Daftar.class));

            }
        });

    }

    // caller method in save food insert image
    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

    // on Request Permission overide method here for check storage permission added or not
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(MainActivity.this, "You dont have permission to access file storage", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // on Activity Result override method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try
            {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                // set our ui image view from storage choose image
                foto.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
