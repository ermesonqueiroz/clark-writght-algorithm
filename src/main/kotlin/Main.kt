package org.example

import ClarkWrightAlgorithm
import domain.Customer
import domain.Route
import factories.CustomerFactory
import utils.FileUtils

fun main() {
    val customers: MutableList<Customer> = ArrayList()

    for (row in FileUtils.readCSV("customers.csv")) {
        customers.add(CustomerFactory.fromCsvRow(row))
    }

    val algorithm = ClarkWrightAlgorithm()

    val routes: List<Route> = algorithm.solve(customers)

    for (i in routes.indices) {
        val route = routes[i]
        println("Rota $i:")
        for (customer in route.customers) {
            println(customer.id.toString() + " " + customer.position)
        }
        println()
    }
}