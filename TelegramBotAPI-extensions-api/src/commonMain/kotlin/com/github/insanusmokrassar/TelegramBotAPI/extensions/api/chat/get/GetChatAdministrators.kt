package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.get.GetChatAdministrators
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.getChatAdministrators(
    chatId: ChatIdentifier
) = execute(GetChatAdministrators(chatId))

suspend fun RequestsExecutor.getChatAdministrators(
    chat: PublicChat
) = getChatAdministrators(chat.id)
