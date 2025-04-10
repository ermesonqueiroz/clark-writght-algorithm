package controllers

import com.sun.net.httpserver.HttpExchange
import domain.entities.Distributor
import services.DistributorService
import utils.JsonUtil

class DistributorController : Controller() {
    private val distributorService = registry.inject("distributorService") as DistributorService

    override fun handle(exchange: HttpExchange) {
        try {
            when (exchange.requestMethod) {
                "POST", "PUT" -> handleSave(exchange)
                "GET" -> handleGet(exchange)
                else -> {
                    exchange.sendResponseHeaders(405, -1)
                    exchange.close()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            respond(exchange, 500, """{"error": "${e.message}"}""")
        }
    }

    private fun handleSave(exchange: HttpExchange) {
        val body = exchange.requestBody.reader().readText()
        val distributor = JsonUtil.fromJson<Distributor>(body)
        distributorService.create(distributor)
        respond(exchange, 201, "Distributor created")
    }

    private fun handleGet(exchange: HttpExchange) {
        val distributor = distributorService.get()
        val response = JsonUtil.toJson(distributor)
        respond(exchange, 200, response)
    }

    private fun respond(exchange: HttpExchange, statusCode: Int, response: String) {
        exchange.sendResponseHeaders(statusCode, response.toByteArray().size.toLong())
        exchange.responseBody.use { os -> os.write(response.toByteArray()) }
        exchange.close()
    }
}