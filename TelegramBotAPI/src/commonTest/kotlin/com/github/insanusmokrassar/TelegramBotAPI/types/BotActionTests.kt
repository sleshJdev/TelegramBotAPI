package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.TestsJsonFormat
import com.github.insanusmokrassar.TelegramBotAPI.types.actions.*
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

@ImplicitReflectionSerializer
class BotActionTests {
    @Serializable
    data class Example(
        val botAction: BotAction
    )

    private fun checkBotAction(example: Example, sourceAction: BotAction) {
        assertEquals(
            sourceAction.actionName,
            when (example.botAction) {
                TypingAction -> example.botAction.actionName
                UploadPhotoAction -> example.botAction.actionName
                RecordVideoAction -> example.botAction.actionName
                UploadVideoAction -> example.botAction.actionName
                RecordAudioAction -> example.botAction.actionName
                UploadAudioAction -> example.botAction.actionName
                UploadDocumentAction -> example.botAction.actionName
                FindLocationAction -> example.botAction.actionName
                RecordVideoNoteAction -> example.botAction.actionName
                UploadVideoNoteAction -> example.botAction.actionName
            }
        )
    }

    private fun checkBotActionSerializeDeserialize(example: Example) {
        val stringified = TestsJsonFormat.stringify(Example.serializer(), example)
        assertEquals("{\"botAction\":\"${example.botAction.actionName}\"}", stringified)

        val deserialized = TestsJsonFormat.parse(Example.serializer(), stringified)

        assertEquals(example, deserialized)

        checkBotAction(deserialized, example.botAction)
    }

    @Test
    fun `BotAction correctly serialized and deserialized`() {
        fun BotAction.example() = Example(this)
        listOf(
            TypingAction.example(),
            UploadPhotoAction.example(),
            RecordVideoAction.example(),
            UploadVideoAction.example(),
            RecordAudioAction.example(),
            UploadAudioAction.example(),
            UploadDocumentAction.example(),
            FindLocationAction.example(),
            RecordVideoNoteAction.example(),
            UploadVideoNoteAction.example()
        ).forEach {
            checkBotActionSerializeDeserialize(it)
        }
    }
}
