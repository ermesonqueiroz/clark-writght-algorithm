import domain.Customer
import domain.Route

interface RoutingStrategy {
    fun solve(customers: List<Customer>): List<Route>
}