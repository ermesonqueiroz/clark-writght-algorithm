package controller

import StrategyRequest
import application.Registry
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import domain.entities.Route
import domain.entities.Solve
import services.CustomerService
import services.RoutingService
import repositories.SolveRepository
import utils.JsonUtil

class SolveController : HttpHandler {
    private var registry = Registry.getInstance()
    private var routingService = registry.inject("routingService") as RoutingService
    private var customerService = registry.inject("customerService") as CustomerService
    private var solveRepository = registry.inject("solveRepository") as SolveRepository

    private fun handleSolveRequest(exchange: HttpExchange) {
        val body = exchange.requestBody.reader().readText()
        val strategyRequest = JsonUtil.fromJson<StrategyRequest>(body)
        val strategyName = strategyRequest.strategyName
        val customers = customerService.getAll()

        routingService.setStrategy(strategyName)
        val resultRoutes = routingService.calculateRoutes(customers)

        val solveResult = Solve(
            strategyName = strategyName,
            customersCount = customers.size,
            routes = resultRoutes
        )
        solveRepository.save(solveResult)

        val response = JsonUtil.toJson<List<Route>>(resultRoutes)
        respond(exchange, 200, response)
    }

    override fun handle(exchange: HttpExchange) {
        try {
            when (exchange.requestMethod) {
                "POST" -> handleSolveRequest(exchange)
                else -> {
                    exchange.sendResponseHeaders(405, -1)
                }
            }
        } catch (e: Exception) {
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