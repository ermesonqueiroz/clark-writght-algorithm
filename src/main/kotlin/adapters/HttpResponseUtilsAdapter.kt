package adapters

import adapters.presentation.controller.HttpResponseUtils
import com.sun.net.httpserver.HttpExchange

class HttpResponseUtilsAdapter(private val exchange: HttpExchange) : HttpResponseUtils {
    override fun ok(data: String) {
        exchange.responseHeaders.add("Content-Type", "application/json")
        exchange.sendResponseHeaders(200, data.toByteArray().size.toLong())
        exchange.responseBody.use { os -> os.write(data.toByteArray()) }
        exchange.close()
    }

    override fun badRequest(message: String) {
        TODO("Not yet implemented")
    }

    override fun serverError(reason: String) {
        TODO("Not yet implemented")
    }
}
