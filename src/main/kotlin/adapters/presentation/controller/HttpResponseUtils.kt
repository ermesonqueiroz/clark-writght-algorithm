package adapters.presentation.controller

interface HttpResponseUtils {
    fun ok(data: String)
    fun badRequest(message: String)
    fun serverError(reason: String)
}