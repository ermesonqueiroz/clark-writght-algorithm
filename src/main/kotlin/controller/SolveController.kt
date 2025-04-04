package controller

import services.RoutingService
import com.sun.net.httpserver.HttpExchange
import formatRoutes
import utils.RequestParser

class SolveController(private val routingService: RoutingService) {

    fun handleSolveRequest(exchange: HttpExchange) {
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
