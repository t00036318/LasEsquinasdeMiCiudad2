package com.example.windows7.lasesquinasdemiciudad;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {

    private int aleatorio;
    private String foto;
    private static final int TAKE=0, PICK=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //aleatorio = new Double(Math.random() * 100).intValue();
        //foto = Environment.getExternalStorageDirectory() + "/imagen"+ aleatorio +".jpg";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action1) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri output = Uri.fromFile(new File(foto));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
            startActivityForResult(intent, TAKE);
        }

        if (id == R.id.action2) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PICK);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri;
        if(resultCode==RESULT_OK)
        switch (requestCode){
            case TAKE:
                break;
            case PICK:
                uri=data.getData();
                String[] str={MediaStore.Images.Media.DATA};
                Cursor cursor=getContentResolver().query(uri,str,null,null,null);
                cursor.moveToFirst();
                int index=cursor.getColumnIndex(str[0]);
                String path=cursor.getString(index);
                cursor.close();
                Log.v("Archivo: ",path);
                break;
        }
    }
}
