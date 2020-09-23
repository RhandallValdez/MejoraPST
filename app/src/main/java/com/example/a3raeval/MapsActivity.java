package com.example.a3raeval;

import androidx.fragment.app.FragmentActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor cur = bd.rawQuery(
                "select fecha,longitud,latitud from information", null);
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            String[] valores = {cur.getString(0),cur.getString(1),cur.getString(2)};
            LatLng ubicacion = new LatLng(Double.parseDouble(valores[2]),Double.parseDouble(valores[1]));
            mMap.addMarker(new MarkerOptions().position(ubicacion).title(valores[0]));
            cur.moveToNext();
        }
        bd.close();
    }
}