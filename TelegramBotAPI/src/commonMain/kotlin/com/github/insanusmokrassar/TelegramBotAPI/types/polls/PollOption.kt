package com.github.insanusmokrassar.TelegramBotAPI.types.polls

import com.github.insanusmokrassar.TelegramBotAPI.types.textField
import com.github.insanusmokrassar.TelegramBotAPI.types.votesCountField
import kotlinx.serialization.*

@Serializable(PollOptionSerializer::class)
sealed class PollOption {
    abstract val text: String
    abstract val votes: Int
}

@Serializable
data class SimplePollOption (
    @SerialName(textField)
    override val text: String,
    @SerialName(votesCountField)
    override val votes: Int
) : PollOption()

internal object PollOptionSerializer : KSerializer<PollOption> {
    override val descriptor: SerialDescriptor = SimplePollOption.serializer().descriptor

    override fun deserialize(decoder: Decoder): PollOption = SimplePollOption.serializer().deserialize(
        decoder
    )

    override fun serialize(encoder: Encoder, value: PollOption) {
        when (value) {
            is SimplePollOption -> SimplePollOption.serializer().serialize(
                encoder,
                value
            )
        }
    }
}
