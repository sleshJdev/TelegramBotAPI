package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import kotlinx.serialization.*

@Serializer(KeyboardMarkup::class)
internal object KeyboardMarkupSerializer : KSerializer<KeyboardMarkup> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        KeyboardMarkup::class.toString(),
        PolymorphicKind.OPEN
    )
    override fun serialize(encoder: Encoder, value: KeyboardMarkup) {
        when(value) {
            is ForceReply -> ForceReply.serializer().serialize(encoder, value)
            is InlineKeyboardMarkup -> InlineKeyboardMarkup.serializer().serialize(encoder, value)
            is ReplyKeyboardMarkup -> ReplyKeyboardMarkup.serializer().serialize(encoder, value)
            is ReplyKeyboardRemove -> ReplyKeyboardRemove.serializer().serialize(encoder, value)
        }
    }

    override fun deserialize(decoder: Decoder): KeyboardMarkup {
        TODO("not implemented")
    }
}
