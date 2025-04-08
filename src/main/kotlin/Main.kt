import application.Registry
import com.sun.net.httpserver.HttpServer
import controller.SolveController
import factories.RoutingStrategyFactory
import services.RoutingService
import java.net.InetSocketAddress

fun main() {
    val routingService = RoutingService(RoutingStrategyFactory.create("clarkwright"))
    val registry = Registry.getInstance()
    registry.provide("routingService", routingService)

    val solveController = SolveController()

    val server = HttpServer.create(InetSocketAddress(8080), 0)
    server.createContext("/solve", solveController)

    server.executor = null
    server.start()
    println("Server started on port 8080...")
}
