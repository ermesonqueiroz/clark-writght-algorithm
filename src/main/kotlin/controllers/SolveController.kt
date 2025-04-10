package controllers

import StrategyRequest
import application.Registry
import services.RoutingService
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import domain.entities.Route
import services.CustomerService
import utils.JsonUtil

class SolveController:HttpHandler {
    private var registry = Registry.getInstance()
    private var routingService = registry.inject("routingService") as RoutingService
    private var customerService = registry.inject("customerService") as CustomerService

    private fun handleSolveRequest(exchange: HttpExchange) {
            val body = exchange.requestBody.reader().readText()
            val strategyRequest = JsonUtil.fromJson<StrategyRequest>(body)
            val strategyName = strategyRequest.strategyName
            val customers = customerService.getAll()
            routingService.setStrategy(strategyName)
            val resultRoutes = routingService.calculateRoutes(customers)
            println(resultRoutes[0].toString())
            val response = JsonUtil.toJson<List<Route>>(resultRoutes)

            respond(exchange,200,response)
    }

    override fun handle(exchange: HttpExchange) {
        try {
            when (exchange.requestMethod) {
                "POST" -> handleSolveRequest(exchange)
                else -> {
                    exchange.sendResponseHeaders(405, -1)
                }
            }
        } catch(e:Exception) {
            e.printStackTrace()
            respond(exchange, 500, """{"error": "${e.message}"}""")
        }
    }

    private fun respond(exchange: HttpExchange, statusCode: Int, response: String) {
        exchange.sendResponseHeaders(statusCode, response.toByteArray().size.toLong())
        exchange.responseBody.use { os -> os.write(response.toByteArray()) }
        exchange.close()
    }
}
