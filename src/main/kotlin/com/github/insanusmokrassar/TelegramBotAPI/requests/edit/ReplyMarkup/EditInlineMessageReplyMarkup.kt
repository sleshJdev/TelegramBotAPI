package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.ReplyMarkup

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.EditInlineMessage
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.EditReplyMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*

@Serializable
data class EditInlineMessageReplyMarkup(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditReplyMessage {

    override fun method(): String = editMessageReplyMarkupMethod
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}