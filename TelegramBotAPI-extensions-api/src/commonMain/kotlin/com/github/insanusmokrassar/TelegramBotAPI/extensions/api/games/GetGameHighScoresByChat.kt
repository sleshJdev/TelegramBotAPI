package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.games

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.GetGameHighScoresByChat
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.GameContent

suspend fun RequestsExecutor.getGameScore(
    userId: UserId,
    chatId: ChatId,
    messageId: MessageIdentifier
) = execute(
    GetGameHighScoresByChat(userId, chatId, messageId)
)

suspend fun RequestsExecutor.getGameScore(
    user: CommonUser,
    chatId: ChatId,
    messageId: MessageIdentifier
) = getGameScore(
    user.id, chatId, messageId
)

suspend fun RequestsExecutor.getGameScore(
    userId: UserId,
    chat: Chat,
    messageId: MessageIdentifier
) = getGameScore(
    userId, chat.id, messageId
)

suspend fun RequestsExecutor.getGameScore(
    user: CommonUser,
    chat: Chat,
    messageId: MessageIdentifier
) = getGameScore(
    user.id, chat.id, messageId
)

suspend fun RequestsExecutor.getGameScore(
    userId: UserId,
    message: ContentMessage<GameContent>
) = getGameScore(
    userId, message.chat.id, message.messageId
)

suspend fun RequestsExecutor.getGameScore(
    user: CommonUser,
    message: ContentMessage<GameContent>
) = getGameScore(
    user.id, message.chat.id, message.messageId
)
