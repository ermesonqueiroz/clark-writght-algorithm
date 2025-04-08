package services

import domain.Customer
import domain.Position
import repositories.CustomerRepository

class CustomerService(
    private val repository: CustomerRepository = CustomerRepository()
) {

    fun create(customer: Customer): Customer {
        return repository.save(customer)
    }

    fun getAll(): List<Customer> {
        return repository.findAll()
    }

    fun getById(id: Int): Customer? {
        return repository.findById(id)
    }

    fun update(id: Int, newX: Int, newY: Int): Boolean {
        val customer = repository.findById(id) ?: return false
        val updated = Customer(id, Position(newX, newY))
        return repository.update(updated)
    }

    fun delete(id: Int): Boolean {
        return repository.deleteById(id)
    }
}