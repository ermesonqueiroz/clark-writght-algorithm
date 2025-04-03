import domain.Customer
import domain.Node
import domain.Route

class ClarkWrightAlgorithm: RoutingStrategy {
    private fun findRouteByCustomer(routes: List<Route>, customer: Customer): Route? {
        return routes.find { it.containsCustomer(customer) }
    }

    override fun solve(customers: List<Customer>): List<Route> {
        val nodes: MutableList<Node> = ArrayList()

        for (i in 1..<customers.size) {
            val distanceFromOrigin = customers[0].position.calculateDistance(customers[i].position)
            for (j in 0..<i) {
                val saving = (distanceFromOrigin
                        + customers[0].position.calculateDistance(customers[j].position)
                        - customers[i].position.calculateDistance(customers[j].position))

                val newNode = Node(customers[j], customers[i], saving)
                nodes.add(newNode)
            }
        }

        nodes.sortWith(SortBySaving())

        val routes: MutableList<Route> = ArrayList()

        for (auxNode in nodes) {
            val routeFrom = findRouteByCustomer(routes, auxNode.from)
            val routeTo = findRouteByCustomer(routes, auxNode.to)

            if (routeFrom == null && routeTo == null) {
                val newRoute = Route()
                newRoute.add(auxNode.from)
                newRoute.add(auxNode.to)
                routes.add(newRoute)
            } else if (routeFrom != null && routeTo == null) {
                if (routeFrom.customers.getLast().id == auxNode.from.id) {
                    routeFrom.customers.add(auxNode.to)
                } else if (routeFrom.customers.getFirst().id == auxNode.from.id) {
                    routeFrom.customers.addFirst(auxNode.to)
                }
            } else if (routeFrom == null) {
                if (routeTo!!.customers.getLast().id == auxNode.to.id) {
                    routeTo.customers.add(auxNode.from)
                } else if (routeTo.customers.getFirst().id == auxNode.to.id) {
                    routeTo.customers.addFirst(auxNode.from)
                }
            } else if (routeFrom !== routeTo) {
                if ((routeFrom.customers.getFirst().id == auxNode.from.id || routeFrom.customers.getLast()
                        .id == auxNode.from.id) && (routeTo!!.customers.getFirst()
                        .id == auxNode.to.id || routeTo.customers.getLast().id == auxNode.to.id)
                ) {
                    routeFrom.customers.addAll(routeTo.customers)
                }
            }
        }

        return routes
    }
}
