import domain.Customer
import domain.Route

class RoutingService(private var strategy: RoutingStrategy) {
    fun setStrategy(strategy: RoutingStrategy) {
        this.strategy = strategy
    }

    fun calculateRoutes(customers: List<Customer>): List<Route> {
        if (customers.isEmpty()) {
            throw IllegalArgumentException("Customer list cannot be empty")
        }
        return strategy.solve(customers);
    }
}