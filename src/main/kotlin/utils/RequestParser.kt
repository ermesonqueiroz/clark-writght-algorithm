package utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import domain.entities.Customer
import com.sun.net.httpserver.HttpExchange
import parseCustomers

object RequestParser {
    fun parseSolveRequest(exchange: HttpExchange): Pair<List<Customer>, String> {
        val requestBody = exchange.requestBody.reader().readText()
        val jsonObject = Json.parseToJsonElement(requestBody).jsonObject

        val customersJson = jsonObject["customers"]?.toString() ?: "[]"
        val strategyName = jsonObject["strategy"]?.jsonPrimitive?.content ?: "clarkwright"

        return Pair(parseCustomers(customersJson), strategyName)
    }
}