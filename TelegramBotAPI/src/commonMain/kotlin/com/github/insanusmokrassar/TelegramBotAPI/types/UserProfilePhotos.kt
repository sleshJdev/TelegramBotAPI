package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.types.files.Photo
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

@Serializable
data class UserProfilePhotos (
    @SerialName("total_count")
    val count: Int,
    @Serializable(UserProfilePhotosPhotosSerializer::class)
    val photos: List<Photo>
)

internal object UserProfilePhotosPhotosSerializer : KSerializer<List<Photo>> by ListSerializer(
    PhotoSerializer
)
