import adapters.HttpResponseUtilsAdapter
import adapters.presentation.controller.HttpController
import adapters.presentation.controller.HttpRequest
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler

class HttpControllerAdapter(private val httpController: HttpController) : HttpHandler {
    override fun handle(exchange: HttpExchange) {
        val body = exchange.requestBody.bufferedReader().readText()
        val httpRequest = HttpRequest(body)
        val httpResponseUtils = HttpResponseUtilsAdapter(exchange)
        httpController.handle(httpRequest, httpResponseUtils)
    }
}