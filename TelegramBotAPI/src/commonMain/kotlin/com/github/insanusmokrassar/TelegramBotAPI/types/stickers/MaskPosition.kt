package com.github.insanusmokrassar.TelegramBotAPI.types.stickers

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaskPosition(
    @SerialName(pointField)
    val point: String,
    @SerialName(xShiftField)
    val xShift: Float,
    @SerialName(yShiftField)
    val yShift: Float,
    @SerialName(scaleField)
    val scale: Float
)
