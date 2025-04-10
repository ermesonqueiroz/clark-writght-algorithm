import adapters.presentation.controller.CreateCustomerController
import application.Registry
import application.repository.DistributorRepository
import application.usecase.CreateCustomerUseCase
import com.sun.net.httpserver.HttpServer
import controllers.SolveController
import controllers.CustomerController
import controllers.DistributorController
import factories.RoutingStrategyFactory
import infra.repository.CustomerRepositoryFake
import infra.repository.DistributorRepositoryFake
import services.CustomerService
import services.DistributorService
import services.RoutingService
import java.net.InetSocketAddress

fun registerDependencies() {
    val registry = Registry.getInstance()
    registry.provide("distributorRepository", DistributorRepositoryFake())
    registry.provide("customerRepository", CustomerRepositoryFake())
    registry.provide("distributorService", DistributorService())
    registry.provide("customerService", CustomerService())
    registry.provide("routingService", RoutingService(RoutingStrategyFactory.create("clarkwright")))
}

fun main() {
    registerDependencies()

    val server = HttpServer.create(InetSocketAddress(8080), 0)
    server.createContext("/solve", SolveController())
//    server.createContext("/customers", CustomerController())
    server.createContext("/distributors", DistributorController())
    server.createContext("/customers", HttpControllerAdapter(CreateCustomerController(CreateCustomerUseCase())))

    server.executor = null
    server.start()
    println("Server started on port 8080...")
}
