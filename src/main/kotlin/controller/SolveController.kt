package controller

import services.RoutingService
import com.sun.net.httpserver.HttpExchange
import domain.Route
import services.CustomerService
import utils.JsonUtil

class SolveController(private val routingService: RoutingService, private val customerService: CustomerService) {


    fun handleSolveRequest(exchange: HttpExchange) {
        if (exchange.requestMethod != "POST") {
            exchange.sendResponseHeaders(405, -1)
            exchange.close()
            return
        }

        try {
            val body = exchange.requestBody.reader().readText()
            val strategyName = JsonUtil.fromJson<String>(body)
            val customers = customerService.getAll()
            routingService.setStrategy(strategyName)
            val resultRoutes = routingService.calculateRoutes(customers)
            val response = JsonUtil.toJson<List<Route>>(resultRoutes)

            exchange.sendResponseHeaders(200, response.toByteArray().size.toLong())
            exchange.responseBody.use { os -> os.write(response.toByteArray()) }
        } catch (e: Exception) {
            exchange.sendResponseHeaders(400, -1)
        } finally {
            exchange.close()
        }
    }
}
