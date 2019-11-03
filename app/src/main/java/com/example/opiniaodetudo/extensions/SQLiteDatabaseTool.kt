package com.example.opiniaodetudo.extensions

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class SQLiteDatabaseTool {
    companion object {
        fun SQLiteDatabase.query(
            tableName: String,
            columns: Array<String>,
            selection: String? = null,
            selectionArgs: Array<String>? = null,
            groupBy: String? = null,
            having: String? = null,
            orderBy: String? = null
        ): Cursor {
            return this.query(
                tableName,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
            )
        }
    }


}