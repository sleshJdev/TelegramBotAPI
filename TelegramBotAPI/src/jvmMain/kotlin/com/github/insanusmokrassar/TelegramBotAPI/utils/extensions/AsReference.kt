package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import java.lang.ref.WeakReference

fun <T> T.asReference() = WeakReference(this)