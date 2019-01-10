package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor

import com.github.insanusmokrassar.TelegramBotAPI.bot.BaseRequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base.MultipartRequestCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base.SimpleRequestCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestException
import com.github.insanusmokrassar.TelegramBotAPI.types.ResponseParameters
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.util.cio.toByteArray
import kotlinx.io.charsets.Charset
import kotlinx.serialization.json.JSON

class KtorRequestsExecutor(
    token: String,
    private val client: HttpClient = HttpClient(OkHttp),
    hostUrl: String = "https://api.telegram.org",
    callsFactories: List<KtorCallFactory> = emptyList(),
    excludeDefaultFactories: Boolean = false
) : BaseRequestsExecutor(token, hostUrl) {
    constructor(
        token: String,
        engine: HttpClientEngine = OkHttp.create(),
        hostUrl: String = "https://api.telegram.org"
    ) : this(
        token,
        HttpClient(engine),
        hostUrl
    )

    private val callsFactories: List<KtorCallFactory> = callsFactories.run {
        if (!excludeDefaultFactories) {
            asSequence().plus(SimpleRequestCallFactory()).plus(MultipartRequestCallFactory()).toList()
        } else {
            this
        }
    }

    override suspend fun <T : Any> execute(request: Request<T>): T {
        var call: HttpClientCall? = null
        for (factory in callsFactories) {
            call = factory.prepareCall(
                client,
                baseUrl,
                request
            )
            if (call != null) {
                break
            }
        }
        if (call == null) {
            throw IllegalArgumentException("Can't execute request: $request")
        }
        val content = call.response.content.toByteArray().toString(Charset.defaultCharset())
        val responseObject = JSON.parse(
            ResponseParameters.serializer(request.resultSerializer()),
            content
        )
        return responseObject.result ?: call.let {
            throw RequestException(
                responseObject,
                "Can't get result object"
            )
        }
    }
}