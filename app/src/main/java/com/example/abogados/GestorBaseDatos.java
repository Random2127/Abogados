package com.example.abogados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GestorBaseDatos  extends SQLiteOpenHelper {


    public GestorBaseDatos(@Nullable Context context) {
        super(context, "Gritos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE prueba (id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, password TEXT, mail TEXT, role TEXT)");
        //'CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT, apellido1 TEXT, apellido2 TEXT, dni TEXT, telefono TEXT, email TEXT, passwd TEXT, role TEXT, empresaFK INTEGER)',
        //'CREATE TABLE IF NOT EXISTS servicio (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, titulo TEXT, dewcripcion TEXT, precio FLOAT, empresaFK NUM)',
        //'CREATE TABLE IF NOT EXISTS cita (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, titulo TEXT, userFK INTEGER, servicioFK INTEGER, fecha TIMESTAMP, estado TEXT, notas TEXT, empresaFK INTEGER)',
        //'CREATE TABLE IF NOT EXISTS empresa (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT, fecha_creacion TIMESTAMP)',
        //'CREATE TABLE IF NOT EXISTS mensaje (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT, desde_user INTEGER, hasta_user INTEGER, comentario TEXT, time TIMESTAMP, empresaFK INTEGER)',
        //'CREATE TABLE IF NOT EXISTS noticia (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, titulo, url, TEXT, descripcion TEXT, subido_por (userId) INTEGER , empresaFK INTEGER)',

        db.execSQL("INSERT INTO prueba VALUES(null, 'a','a','a@gmail.com','abo')");
        db.execSQL("INSERT INTO prueba VALUES(null, 'c','c','c@gmail.com','cli')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
