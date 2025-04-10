package adapters.presentation.controller

interface HttpResponse {
    val body: Any
    val statusCode: Int
}
