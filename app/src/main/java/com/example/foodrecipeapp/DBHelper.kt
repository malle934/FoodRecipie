package com.example.foodrecipeapp


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(ctx:Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(ctx,DATABASE_NAME,factory, DATABASE_VERSION) {



    companion object{
        private val DATABASE_NAME = "CSE226"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "my_table1"
        val ID_COL = "id"
        val NAME_COL = "name"
        val DES_COL = "desc"
        val IMG_COL = "image"
        val INS_COL = "ins"
    }

    override fun onCreate(db: SQLiteDatabase?){
        val query = ("CREATE TABLE $TABLE_NAME ($ID_COL INTEGER PRIMARY KEY, $NAME_COL TEXT,$DES_COL TEXT, $IMG_COL BLOB,$INS_COL TEXT)")

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(name : String, desc : String, img:ByteArray, ins: String ){
        val values = ContentValues()
        values.put(NAME_COL, name)
        values.put(DES_COL,desc)
        values.put(INS_COL, ins)
        values.put(IMG_COL,img)
        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun alterTable(newCol: String){
        val db = this.writableDatabase
        db.execSQL("ALTER TABLE $TABLE_NAME ADD $newCol TEXT")
        Log.d("altered table", "success")
    }

    fun deleteUser(name: String)
    {
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"$NAME_COL=?", arrayOf("$name"))
        db.close()
    }

    fun getUser(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }


}

