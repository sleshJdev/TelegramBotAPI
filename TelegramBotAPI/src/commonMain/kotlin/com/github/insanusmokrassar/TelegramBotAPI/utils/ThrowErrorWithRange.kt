package com.github.insanusmokrassar.TelegramBotAPI.utils

internal fun throwRangeError(
    valueName: String,
    range: IntRange,
    actualValue: Int
): Nothing = error("$valueName must be in range $range, but was $actualValue")
