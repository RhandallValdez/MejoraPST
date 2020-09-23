package com.example.a3raeval;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    private String longitud,latitud,fecha;
    EditText lat,longit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lat= findViewById(R.id.latitud);
        longit = findViewById(R.id.longitud);    }
    public void mostrar(View v){
        Intent i = new Intent(this,MapsActivity.class);
        startActivity(i);    }
    public void guardar(View v){
        Double la,lo;
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        fecha = df.format(c);
        latitud = lat.getText().toString();
        longitud = longit.getText().toString();
        if(latitud.isEmpty()||longitud.isEmpty()){
            Toast.makeText(this, "Ingrese informacion.", Toast.LENGTH_SHORT).show();
            return;        }
        try{
            la= Double.parseDouble(latitud);
            lo=Double.parseDouble(longitud);
        } catch (NumberFormatException e){
            Toast.makeText(this, "Ingrese coordenadas en formato correcto.", Toast.LENGTH_SHORT).show();
            return;        }
        Toast.makeText(this, fecha+longitud+latitud, Toast.LENGTH_SHORT).show();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        bd.execSQL("insert into information (id,fecha,longitud,latitud) values (NULL,'"+fecha+"','"+longitud+"','"+latitud+"')");
        bd.close();
    }
}
