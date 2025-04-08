package strategies

import domain.entities.Customer
import domain.entities.Route

class NearestNeighbor: RoutingStrategy {
    override fun solve(customers: List<Customer>): List<Route> {
        val Routes = mutableListOf<Route>()
        return Routes
    }
}