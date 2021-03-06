package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.*

const val markdownBoldControl = "*"
const val markdownItalicControl = "_"
const val markdownCodeControl = "`"
const val markdownPreControl = "```"

const val markdownV2ItalicUnderlineDelimiter = "\u0013"
const val markdownV2StrikethroughControl = "~"
const val markdownV2UnderlineControl = "__"
const val markdownV2UnderlineEndControl = "$markdownV2UnderlineControl$markdownV2ItalicUnderlineDelimiter"
const val markdownV2ItalicEndControl = "$markdownItalicControl$markdownV2ItalicUnderlineDelimiter"

const val htmlBoldControl = "b"
const val htmlItalicControl = "i"
const val htmlCodeControl = "code"
const val htmlPreControl = "pre"
const val htmlUnderlineControl = "u"
const val htmlStrikethroughControl = "s"

private fun String.markdownDefault(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol
) = "$openControlSymbol${toMarkdown()}$closeControlSymbol"
private fun String.markdownV2Default(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol,
    escapeFun: String.() -> String = String::escapeMarkdownV2Common
) = "$openControlSymbol${escapeFun()}$closeControlSymbol"
private fun String.htmlDefault(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol
) = "<$openControlSymbol>${toHtml()}</$closeControlSymbol>"

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.linkMarkdown(link: String): String = "[${toMarkdown()}](${link.toMarkdown()})"
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.linkMarkdownV2(link: String): String = "[${escapeMarkdownV2Common()}](${link.escapeMarkdownV2Link()})"
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.linkHTML(link: String): String = "<a href=\"$link\">${toHtml()}</a>"


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.boldMarkdown(): String = markdownDefault(markdownBoldControl)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.boldMarkdownV2(): String = markdownV2Default(markdownBoldControl)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.boldHTML(): String = htmlDefault(htmlBoldControl)


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.italicMarkdown(): String = markdownDefault(markdownItalicControl)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.italicMarkdownV2(): String = markdownV2Default(markdownItalicControl, markdownV2ItalicEndControl)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.italicHTML(): String = htmlDefault(htmlItalicControl)

/**
 * Crutch for support of strikethrough in default markdown. Simply add modifier, but it will not look like correct
 */
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.strikethroughMarkdown(): String = map { it + "\u0336" }.joinToString("")
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.strikethroughMarkdownV2(): String = markdownV2Default(markdownV2StrikethroughControl)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.strikethroughHTML(): String = htmlDefault(htmlStrikethroughControl)


/**
 * Crutch for support of underline in default markdown. Simply add modifier, but it will not look like correct
 */
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.underlineMarkdown(): String = map { it + "\u0347" }.joinToString("")
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.underlineMarkdownV2(): String = markdownV2Default(markdownV2UnderlineControl, markdownV2UnderlineEndControl)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.underlineHTML(): String = htmlDefault(htmlUnderlineControl)


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.codeMarkdown(): String = markdownDefault(markdownCodeControl)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.codeMarkdownV2(): String = markdownV2Default(markdownCodeControl, escapeFun = String::escapeMarkdownV2PreAndCode)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.codeHTML(): String = htmlDefault(htmlCodeControl)


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.preMarkdown(language: String? = null): String = markdownDefault(
    "$markdownPreControl${language ?: ""}\n",
    "\n$markdownPreControl"
)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.preMarkdownV2(language: String? = null): String = markdownV2Default(
    "$markdownPreControl${language ?: ""}\n",
    "\n$markdownPreControl",
    String::escapeMarkdownV2PreAndCode
)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.preHTML(language: String? = null): String = htmlDefault(
    language ?.let {
        "$htmlPreControl><$htmlCodeControl class=\"language-$language\""
    } ?: htmlPreControl,
    language ?.let {
        "$htmlCodeControl></$htmlPreControl"
    } ?: htmlPreControl
)


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.emailMarkdown(): String = linkMarkdown("mailto://$${toMarkdown()}")
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.emailMarkdownV2(): String = linkMarkdownV2("mailto://$${toMarkdown()}")
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.emailHTML(): String = linkHTML("mailto://$${toHtml()}")


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
private inline fun String.mention(adapt: String.() -> String): String = if (startsWith("@")) {
    adapt()
} else {
    "@${adapt()}"
}


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
private inline fun String.hashTag(adapt: String.() -> String): String = if (startsWith("#")) {
    adapt()
} else {
    "#${adapt()}"
}


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.textMentionMarkdown(userId: UserId): String = linkMarkdown(userId.link)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.textMentionMarkdownV2(userId: UserId): String = linkMarkdownV2(userId.link)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.textMentionHTML(userId: UserId): String = linkHTML(userId.link)


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.mentionMarkdown(): String = mention(String::toMarkdown)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.mentionMarkdownV2(): String = mention(String::escapeMarkdownV2Common)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.mentionHTML(): String = mention(String::toHtml)


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.hashTagMarkdown(): String = hashTag(String::toMarkdown)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.hashTagMarkdownV2(): String = hashTag(String::escapeMarkdownV2Common).escapeMarkdownV2Common()
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.hashTagHTML(): String = hashTag(String::toHtml)


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.phoneMarkdown(): String = toMarkdown()
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.phoneMarkdownV2(): String = escapeMarkdownV2Common()
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.phoneHTML(): String = toHtml()


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.command(adapt: String.() -> String): String = if (startsWith("/")) {
    adapt()
} else {
    "/${adapt()}"
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.commandMarkdown(): String = command(String::toMarkdown)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.commandMarkdownV2(): String = command(String::escapeMarkdownV2Common)
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.commandHTML(): String = command(String::toHtml)


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.regularMarkdown(): String = toMarkdown()
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.regularMarkdownV2(): String = escapeMarkdownV2Common()
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.regularHtml(): String = toHtml()


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.cashTagMarkdown(): String = toMarkdown()
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.cashTagMarkdownV2(): String = escapeMarkdownV2Common()
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.cashTagHtml(): String = toHtml()


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.bold(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> boldHTML()
    is Markdown -> boldMarkdown()
    is MarkdownV2 -> boldMarkdownV2()
}


@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.italic(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> italicHTML()
    is Markdown -> italicMarkdown()
    is MarkdownV2 -> italicMarkdownV2()
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.hashTag(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> hashTagHTML()
    is Markdown -> hashTagMarkdown()
    is MarkdownV2 -> hashTagMarkdownV2()
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.code(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> codeHTML()
    is Markdown -> codeMarkdown()
    is MarkdownV2 -> codeMarkdownV2()
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
fun String.pre(parseMode: ParseMode, language: String? = null): String = when (parseMode) {
    is HTML -> preHTML(language)
    is Markdown -> preMarkdown(language)
    is MarkdownV2 -> preMarkdownV2(language)
}
@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.pre(parseMode: ParseMode): String = pre(parseMode, null)

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.email(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> emailHTML()
    is Markdown -> emailMarkdown()
    is MarkdownV2 -> emailMarkdownV2()
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun Pair<String, String>.link(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> first.linkHTML(second)
    is Markdown -> first.linkMarkdown(second)
    is MarkdownV2 -> first.linkMarkdownV2(second)
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.mention(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> mentionHTML()
    is Markdown -> mentionMarkdown()
    is MarkdownV2 -> mentionMarkdownV2()
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun Pair<String, ChatId>.mention(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> first.textMentionHTML(second)
    is Markdown -> first.textMentionMarkdown(second)
    is MarkdownV2 -> first.textMentionMarkdownV2(second)
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.phone(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> phoneHTML()
    is Markdown -> phoneMarkdown()
    is MarkdownV2 -> phoneMarkdownV2()
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.command(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> commandHTML()
    is Markdown -> commandMarkdown()
    is MarkdownV2 -> commandMarkdownV2()
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.underline(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> underlineHTML()
    is Markdown -> underlineMarkdown()
    is MarkdownV2 -> underlineMarkdownV2()
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.strikethrough(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> strikethroughHTML()
    is Markdown -> strikethroughMarkdown()
    is MarkdownV2 -> strikethroughMarkdownV2()
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.regular(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> regularHtml()
    is Markdown -> regularMarkdown()
    is MarkdownV2 -> regularMarkdownV2()
}

@Deprecated("Replaced into project TelegramBotAPI-extensions-utils")
infix fun String.cashtag(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> cashTagHtml()
    is Markdown -> cashTagMarkdown()
    is MarkdownV2 -> cashTagMarkdownV2()
}
