package com.github.insanusmokrassar.TelegramBotAPI.requests.send.polls

import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.PollContent
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<PollContent>> = TelegramBotAPIMessageDeserializationStrategyClass()

private fun checkPollInfo(
    question: String,
    options: List<String>
) {
    if (question.length !in pollQuestionTextLength) {
        throw IllegalArgumentException("The length of questions for polls must be in $pollQuestionTextLength range, but was ${question.length}")
    }
    options.forEach {
        if (it.length !in pollOptionTextLength) {
            throw IllegalArgumentException("The length of question option text for polls must be in $pollOptionTextLength range, but was ${it.length}")
        }
    }
    if (options.size !in pollOptionsLimit) {
        throw IllegalArgumentException("The amount of question options for polls must be in $pollOptionsLimit range, but was ${options.size}")
    }
}

fun SendPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendRegularPoll(
    chatId,
    question,
    options,
    isAnonymous,
    isClosed,
    disableNotification = disableNotification,
    replyToMessageId = replyToMessageId,
    replyMarkup = replyMarkup
)

sealed class SendPoll : SendMessageRequest<ContentMessage<PollContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<PollContent>> {
    abstract val question: String
    abstract val options: List<String>
    abstract val isAnonymous: Boolean
    abstract val isClosed: Boolean
    abstract val type: String

    override fun method(): String = "sendPoll"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<PollContent>>
        get() = commonResultDeserializer
}

@Serializable
data class SendRegularPoll(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<String>,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = true,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(allowsMultipleAnswersField)
    val allowMultipleAnswers: Boolean = false,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendPoll() {
    override val type: String = regularPollType
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        checkPollInfo(question, options)
    }
}

@Serializable
data class SendQuizPoll(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<String>,
    @SerialName(correctOptionIdField)
    val correctOptionId: Int,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = true,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendPoll() {
    override val type: String = quizPollType
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        checkPollInfo(question, options)
        val correctOptionIdRange = 0 .. options.size
        if (correctOptionId !in correctOptionIdRange) {
            throw IllegalArgumentException("Correct option id must be in range of $correctOptionIdRange, but actual " +
                "value is $correctOptionId")
        }
    }
}