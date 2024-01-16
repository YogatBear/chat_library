package com.example.chat_library

import java.util.UUID
import java.time.Instant

class Message {
    val messageId: String
    val sender: String
    val timestamp: Long
    val type: String
    val content: String

    // Constructor for creating a new message
    constructor(sender: String, type: String, content: String) {
        this.messageId = UUID.randomUUID().toString()
        this.sender = sender
        this.timestamp = Instant.now().toEpochMilli()
        this.type = type
        this.content = content
    }

    // Constructor for received message
    constructor(messageId: String, sender: String, timestamp: Long, type: String, content: String) {
        this.messageId = messageId
        this.sender = sender
        this.timestamp = timestamp
        this.type = type
        this.content = content
    }

    // Additional functionalities and methods can be added here
}
