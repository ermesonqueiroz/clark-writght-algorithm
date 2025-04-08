import application.Registry
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
    val routingService = RoutingService(RoutingStrategyFactory.create("clarkwright"))
    val customerService = CustomerService()
    val distributorService = DistributorService()
    val registry = Registry.getInstance()
    registry.provide("routingService", routingService)
    registry.provide("customerService", customerService)
    registry.provide("distributorService", distributorService)

    val solveController = SolveController()
    val customerController = CustomerController()
    val distributorController = DistributorController()

    val server = HttpServer.create(InetSocketAddress(8080), 0)
    server.createContext("/solve", solveController)
    server.createContext("/customers", customerController)
    server.createContext("/distributors", distributorController)

    server.executor = null
    server.start()
    println("Server started on port 8080...")
}
