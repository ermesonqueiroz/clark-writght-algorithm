import com.sun.net.httpserver.HttpServer
import controller.SolveController
import controllers.CustomerController
import controllers.DistributorController
import factories.RoutingStrategyFactory
import services.CustomerService
import services.DistributorService
import services.RoutingService
import java.net.InetSocketAddress

fun main() {
    val strategy = RoutingStrategyFactory.create("clarkwright")
    val routingService = RoutingService(strategy)
    val customerService = CustomerService()
    val distributorService = DistributorService()
    val distributorController = DistributorController(distributorService)
    val solveController = SolveController(routingService,customerService)
    val customerController = CustomerController(customerService)
    val server = HttpServer.create(InetSocketAddress(8080), 0)
    server.createContext("/solve") { exchange -> solveController.handleSolveRequest(exchange) }
    server.createContext("/customers") { exchange ->
        customerController.handle(exchange)
    }
    server.createContext("/distributor") { exchange ->
        distributorController.handle(exchange)
    }
    server.executor = null
    server.start()
    println("Server started on port 8080...")
}