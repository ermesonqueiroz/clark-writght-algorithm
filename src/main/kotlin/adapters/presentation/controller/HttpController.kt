package adapters.presentation.controller

abstract class HttpController {
    open fun handle(httpRequest: HttpRequest, httpResponseUtils: HttpResponseUtils) {}
}