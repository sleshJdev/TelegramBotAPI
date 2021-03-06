package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.fullEntitiesList

@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun createFormattedText(
    entities: FullTextSourcesList,
    partLength: Int = textLength.last,
    mode: ParseMode = MarkdownParseMode
): List<String> {
    val texts = mutableListOf<String>()
    val textBuilder = StringBuilder(partLength)
    for (entity in entities) {
        val string = when (mode) {
            is MarkdownParseMode -> entity.asMarkdownSource
            is MarkdownV2ParseMode -> entity.asMarkdownV2Source
            is HTMLParseMode -> entity.asHtmlSource
        }
        if (textBuilder.length + string.length > partLength) {
            if (textBuilder.isNotEmpty()) {
                texts.add(textBuilder.toString())
                textBuilder.clear()
            }
            val chunked = string.chunked(partLength)
            val last = chunked.last()
            textBuilder.append(last)
            val listToAdd = if (chunked.size > 1) {
                chunked.subList(0, chunked.size - 1)
            } else {
                emptyList()
            }
            listToAdd.forEach {
                texts.add(it)
            }
        } else {
            textBuilder.append(string)
        }
    }
    if (textBuilder.isNotEmpty()) {
        texts.add(textBuilder.toString())
        textBuilder.clear()
    }
    return texts
}


@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun createMarkdownText(
    entities: FullTextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, MarkdownParseMode)

@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun FullTextSourcesList.toMarkdownCaptions(): List<String> = createMarkdownText(
    this,
    captionLength.last
)
@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun CaptionedInput.toMarkdownCaptions(): List<String> = fullEntitiesList().toMarkdownCaptions()

@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun FullTextSourcesList.toMarkdownTexts(): List<String> = createMarkdownText(
    this,
    textLength.last
)
@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun TextContent.toMarkdownTexts(): List<String> = fullEntitiesList().toMarkdownTexts()

@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun FullTextSourcesList.toMarkdownExplanations(): List<String> = createMarkdownText(
    this,
    explanationLimit.last
)
@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun ExplainedInput.toMarkdownExplanations(): List<String> = fullEntitiesList().toMarkdownTexts()


@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun createMarkdownV2Text(
    entities: FullTextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, MarkdownV2ParseMode)

@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun FullTextSourcesList.toMarkdownV2Captions(): List<String> = createMarkdownV2Text(
    this,
    captionLength.last
)
@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun CaptionedInput.toMarkdownV2Captions(): List<String> = fullEntitiesList().toMarkdownV2Captions()

@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun FullTextSourcesList.toMarkdownV2Texts(): List<String> = createMarkdownV2Text(
    this,
    textLength.last
)
@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun TextContent.toMarkdownV2Texts(): List<String> = fullEntitiesList().toMarkdownV2Texts()

@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun FullTextSourcesList.toMarkdownV2Explanations(): List<String> = createMarkdownV2Text(
    this,
    explanationLimit.last
)
@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun ExplainedInput.toMarkdownV2Explanations(): List<String> = fullEntitiesList().toMarkdownV2Texts()


@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun createHtmlText(
    entities: FullTextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, HTMLParseMode)

@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun FullTextSourcesList.toHtmlCaptions(): List<String> = createHtmlText(
    this,
    captionLength.last
)
@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun CaptionedInput.toHtmlCaptions(): List<String> = fullEntitiesList().toHtmlCaptions()

@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun FullTextSourcesList.toHtmlTexts(): List<String> = createHtmlText(
    this,
    textLength.last
)
@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun TextContent.toHtmlTexts(): List<String> = fullEntitiesList().toHtmlTexts()

@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun FullTextSourcesList.toHtmlExplanations(): List<String> = createHtmlText(
    this,
    explanationLimit.last
)
@Deprecated("Replaced into TelegramBotAPI-extensions-utils")
fun ExplainedInput.toHtmlExplanations(): List<String> = fullEntitiesList().toHtmlTexts()


