package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.UploadStickerFile
import com.github.insanusmokrassar.TelegramBotAPI.types.CommonUser
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId

suspend fun RequestsExecutor.uploadStickerFile(
    userId: UserId,
    sticker: MultipartFile
) = execute(
    UploadStickerFile(userId, sticker)
)

suspend fun RequestsExecutor.uploadStickerFile(
    user: CommonUser,
    sticker: MultipartFile
) = execute(
    UploadStickerFile(user.id, sticker)
)
