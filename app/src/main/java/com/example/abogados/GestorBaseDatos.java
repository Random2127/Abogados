package com.example.abogados;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GestorBaseDatos extends SQLiteOpenHelper {


    public GestorBaseDatos(@Nullable Context context) {
        super(context, "Gritos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE prueba (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, password TEXT, email TEXT, role TEXT)");
        //'CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT, apellido1 TEXT, apellido2 TEXT, dni TEXT, telefono TEXT, email TEXT, passwd TEXT, role TEXT, empresaFK INTEGER)',

        db.execSQL("INSERT INTO prueba VALUES(null, 'a','a','a@test.com','admin')");
        db.execSQL("INSERT INTO prueba VALUES(null, 'w','w','w@test.com','worker')");
        db.execSQL("INSERT INTO prueba VALUES(null, 'c','c','c@test.com','cliente')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String comprobarCredenciales(String nombre, String password, Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, email, role FROM prueba WHERE nombre= ? AND password= ?", new String[]{nombre, password});

        if (cursor.moveToFirst()) {
            int id = cursor.getInt((cursor.getColumnIndexOrThrow("id")));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String role = cursor.getString(cursor.getColumnIndexOrThrow("role"));

            // Guardamo datos no sensibles en preferencias
            SharedPreferences preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("user_id", id);
            editor.putString("nombre", nombre);
            editor.putString("email", email);
            editor.putString("role", role);
            editor.apply();
            // devolvemos role para controlar la entrada del ususario
            return role;
        }
        // si no devuelve nada las credenciales no son validas
        return null;
    }

    public boolean existeUser(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM prueba WHERE nombre= ?", new String[]{nombre});
        boolean existe = false;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                existe = true;
            }
        }
        return existe;
    }
}
