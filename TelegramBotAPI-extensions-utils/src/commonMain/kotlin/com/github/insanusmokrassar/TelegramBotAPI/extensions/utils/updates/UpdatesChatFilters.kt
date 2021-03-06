package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.SentMediaGroupUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

/**
 * [Flow.filter] incoming [BaseMessageUpdate]s by their [ChatId]
 */
fun <T : BaseMessageUpdate> Flow<T>.filterBaseMessageUpdatesByChatId(chatId: ChatId): Flow<T> = filter { it.data.chat.id == chatId }
/**
 * [Flow.filter] incoming [BaseMessageUpdate]s by their [ChatId] using [Chat.id] of [chat]
 */
fun <T : BaseMessageUpdate> Flow<T>.filterBaseMessageUpdatesByChat(chat: Chat): Flow<T> = filterBaseMessageUpdatesByChatId(chat.id)
/**
 * [Flow.filter] incoming [BaseMessageUpdate]s by their [ChatId]
 */
@Deprecated("Renamed", ReplaceWith("filterBaseMessageUpdatesByChatId"))
fun <T : BaseMessageUpdate> Flow<T>.filterBaseMessageUpdates(chatId: ChatId): Flow<T> = filterBaseMessageUpdatesByChatId(chatId)
/**
 * [Flow.filter] incoming [BaseMessageUpdate]s by their [ChatId] using [Chat.id] of [chat]
 */
@Deprecated("Renamed", ReplaceWith("filterBaseMessageUpdatesByChat"))
fun <T : BaseMessageUpdate> Flow<T>.filterBaseMessageUpdates(chat: Chat): Flow<T> = filterBaseMessageUpdatesByChatId(chat.id)


/**
 * [Flow.filter] incoming [SentMediaGroupUpdate]s by their [ChatId]
 */
fun <T : SentMediaGroupUpdate> Flow<T>.filterSentMediaGroupUpdatesByChatId(chatId: ChatId): Flow<T> = filter { it.data.first().chat.id == chatId }
/**
 * [Flow.filter] incoming [SentMediaGroupUpdate]s by their [ChatId] using [Chat.id] of [chat]
 */
fun <T : SentMediaGroupUpdate> Flow<T>.filterSentMediaGroupUpdatesByChat(chat: Chat): Flow<T> = filterSentMediaGroupUpdatesByChatId(chat.id)
/**
 * [Flow.filter] incoming [SentMediaGroupUpdate]s by their [ChatId]
 */
@Deprecated("Renamed", ReplaceWith("filterSentMediaGroupUpdatesByChatId"))
fun <T : SentMediaGroupUpdate> Flow<T>.filterSentMediaGroupUpdates(chatId: ChatId): Flow<T> = filterSentMediaGroupUpdatesByChatId(chatId)
/**
 * [Flow.filter] incoming [SentMediaGroupUpdate]s by their [ChatId] using [Chat.id] of [chat]
 */
@Deprecated("Renamed", ReplaceWith("filterSentMediaGroupUpdatesByChat"))
fun <T : SentMediaGroupUpdate> Flow<T>.filterSentMediaGroupUpdates(chat: Chat): Flow<T> = filterSentMediaGroupUpdatesByChatId(chat.id)
