package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class SetChatDescription (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(descriptionField)
    val description: String
): ChatRequest, SimpleRequest<Boolean> {
    init {
        if (description.length !in chatDescriptionLength) {
            throw IllegalArgumentException("Chat description must be in $chatDescriptionLength range")
        }
    }

    override fun method(): String = "setChatDescription"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}