import com.sun.net.httpserver.HttpServer
import controller.SolveController
import factories.RoutingStrategyFactory
import services.RoutingService
import java.net.InetSocketAddress

fun main() {
    val strategy = RoutingStrategyFactory.create("clarkwright")
    val routingService = RoutingService(strategy)
    val solveController = SolveController(routingService)

    val server = HttpServer.create(InetSocketAddress(8080), 0)
    server.createContext("/solve") { exchange -> solveController.handleSolveRequest(exchange) }

    server.executor = null
    server.start()
    println("Server started on port 8080...")
}