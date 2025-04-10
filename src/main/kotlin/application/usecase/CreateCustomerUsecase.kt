package application.usecase

import application.repository.CustomerRepository
import domain.entities.Customer
import domain.entities.Position
import kotlinx.serialization.Serializable

class CreateCustomerUseCase : UseCase() {
    private val customerRepository = registry.inject("customerRepository") as CustomerRepository

    fun execute(input: CreateCustomerInput): Customer {
        val customer = Customer.create(Position(input.x, input.y))
        customerRepository.save(customer)
        return customer
    }
}

@Serializable
data class CreateCustomerInput(val x: Int, val y: Int)
