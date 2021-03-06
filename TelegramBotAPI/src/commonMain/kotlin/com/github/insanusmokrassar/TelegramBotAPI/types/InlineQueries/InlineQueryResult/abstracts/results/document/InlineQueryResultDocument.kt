package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.document

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.MimeTyped
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

interface InlineQueryResultDocument : InlineQueryResultDocumentCommon, UrlInlineQueryResult, ThumbedInlineQueryResult, ThumbSizedInlineQueryResult, MimeTyped