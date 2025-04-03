package org.example

import strategies.ClarkWrightAlgorithm
import domain.Customer
import domain.Position
import domain.Route
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ClarkWrightAlgorithmTest {
    private fun routesToString(routes: List<Route>): List<String> {
        val result = mutableListOf<String>()

        for (route in routes) {
            var strRoute = ""

            for (customer in route.customers) {
                strRoute += customer.id
            }

            result.add(strRoute)
        }

        return result
    }

    @Test
    fun testVeryEasyScenario() {
        val algorithm = ClarkWrightAlgorithm()

        val result = algorithm.solve(
            listOf(
                Customer(1, Position(15, 26)),
                Customer(2, Position(25, 30)),
                Customer(3, Position(35, 15)),
            )
        )

        assertEquals(routesToString(result), mutableListOf("123"))
    }
}