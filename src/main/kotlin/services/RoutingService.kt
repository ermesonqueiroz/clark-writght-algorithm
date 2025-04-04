package services

import strategies.RoutingStrategy
import domain.Customer
import domain.Route
import factories.RoutingStrategyFactory

class RoutingService(private var strategy: RoutingStrategy) {
    fun setStrategy(strategyName: String) {
        this.strategy = RoutingStrategyFactory.create(strategyName)
    }
    fun calculateRoutes(customers: List<Customer>): List<Route> {
        if (customers.isEmpty()) {
            throw IllegalArgumentException("Customer list cannot be empty")
        }
        return strategy.solve(customers)
    }
}