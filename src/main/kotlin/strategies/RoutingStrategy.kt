package strategies

import domain.entities.Customer
import domain.entities.Route

interface RoutingStrategy {
    fun solve(customers: List<Customer>): List<Route>
}