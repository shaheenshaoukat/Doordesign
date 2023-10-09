package com.example.door_design

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        const val DATABASE_NAME = "Student.db"
        const val TABLE_NAME = "student_table"
        const val COL_1 = "ID"
        const val COL_2 = "NAME"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, " +
                    "SURNAME TEXT, MARKS INTEGER, IMAGE INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert Data Method
    fun insertData(image: Int): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, image)

        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return success != -1L
    }

    // Fetch Data Method
    fun getAllData(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun updateData(id: String, image: Int): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, image)

        val rowsAffected = db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        db.close()
        return rowsAffected > 0
    }

    fun deleteData(id: String): Int {
        val db = this.writableDatabase
        val rowsDeleted = db.delete(TABLE_NAME, "ID = ?", arrayOf(id))
        db.close()
        return rowsDeleted
    }
}
