package com.example.chat_library

import android.content.ContentValues
import android.content.Context

class Index(context: Context) {
    private val dbHelper: DatabaseHelper = DatabaseHelper(context.applicationContext)

    fun storeMessage(message: Message) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("messageId", message.messageId)
            put("sender", message.sender)
            put("timestamp", message.timestamp)
            put("type", message.type)
            put("content", message.content)
        }
        db.insert(DatabaseHelper.TABLE_MESSAGES, null, values)
        db.close()
    }


    fun getMessages(start: Int = 0, range: Int = 10): List<Message> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_MESSAGES,
            null,
            null,
            null,
            null,
            null,
            "timestamp DESC",
            "$start, $range"
        )

        val messages = mutableListOf<Message>()
        while (cursor.moveToNext()) {
            val messageId = cursor.getString(cursor.getColumnIndexOrThrow("messageId"))
            val sender = cursor.getString(cursor.getColumnIndexOrThrow("sender"))
            val timestamp = cursor.getLong(cursor.getColumnIndexOrThrow("timestamp"))
            val type = cursor.getString(cursor.getColumnIndexOrThrow("type"))
            val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
            messages.add(Message(messageId, sender, timestamp, type, content))
        }
        cursor.close()
        db.close()
        return messages
    }
}
