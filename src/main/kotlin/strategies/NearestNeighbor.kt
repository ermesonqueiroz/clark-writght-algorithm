package strategies

import domain.Customer
import domain.Route

class NearestNeighbor: RoutingStrategy {
    override fun solve(customers: List<Customer>): List<Route> {
        val Routes = mutableListOf<Route>()
        return Routes
    }
}