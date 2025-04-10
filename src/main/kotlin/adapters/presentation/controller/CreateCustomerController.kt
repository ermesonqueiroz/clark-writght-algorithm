package adapters.presentation.controller

import application.usecase.CreateCustomerUseCase
import application.usecase.CreateCustomerInput
import utils.JsonUtil

class CreateCustomerController(private val createCustomerUseCase: CreateCustomerUseCase) : HttpController() {
    override fun handle(httpRequest: HttpRequest, httpResponseUtils: HttpResponseUtils) {
        val body = JsonUtil.fromJson<CreateCustomerInput>(httpRequest.body)
        val customer = createCustomerUseCase.execute(body)
        httpResponseUtils.ok(JsonUtil.toJson(customer))
    }
}