package controller

import application.Registry
import services.RoutingService
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import formatRoutes
import utils.RequestParser

class SolveController : HttpHandler {
    private val routingService = Registry.getInstance().inject("routingService") as RoutingService;

    override fun handle(exchange: HttpExchange) {
        if (exchange.requestMethod != "POST") {
            exchange.sendResponseHeaders(405, -1)
            exchange.close()
            return
        }

        try {
            val (customers, strategyName) = RequestParser.parseSolveRequest(exchange)

            routingService.setStrategy(strategyName)
            val resultRoutes = routingService.calculateRoutes(customers)
            val response = formatRoutes(resultRoutes)

            exchange.sendResponseHeaders(200, response.toByteArray().size.toLong())
            exchange.responseBody.use { os -> os.write(response.toByteArray()) }
        } catch (e: Exception) {
            exchange.sendResponseHeaders(400, -1)
        } finally {
            exchange.close()
        }
    }
}
