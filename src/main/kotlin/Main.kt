import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

fun main() {
    val strategy = ClarkWrightAlgorithm()
    val service = RoutingService(strategy)
    val server = HttpServer.create(InetSocketAddress(8080), 0)
    server.createContext("/solve") { exchange ->
        println("Received request: ${exchange.requestMethod} ${exchange.requestURI}")
        if (exchange.requestMethod == "POST") {
            val requestBody = exchange.requestBody.reader().readText()
            val customers = parseCustomers(requestBody)
            val routes = service.calculateRoutes(customers)
            val response = formatRoutes(routes)

            exchange.sendResponseHeaders(200, response.toByteArray().size.toLong())
            exchange.responseBody.use { os -> os.write(response.toByteArray()) }
        } else {
            exchange.sendResponseHeaders(405, -1)
        }
        exchange.close()
    }

    server.executor = null // Uses default executor
    server.start()
    println("Server started on port 8080...")
}
