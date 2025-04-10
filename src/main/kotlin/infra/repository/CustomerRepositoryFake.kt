package infra.repository

import application.repository.CustomerRepository
import domain.entities.Customer

class CustomerRepositoryFake : CustomerRepository {
    private val customers = mutableListOf<Customer>()
    private var currentId = 1

    override fun save(customer: Customer) {
        customers.add(customer)
    }

    override fun findAll(): List<Customer> {
        return customers.toList()
    }

    override fun findById(id: String): Customer? {
        return customers.find { it.id == id }
    }

    override fun update(customer: Customer): Boolean {
        val index = customers.indexOfFirst { it.id == customer.id }
        if (index != -1) {
            customers[index] = customer
            return true
        }
        return false
    }

    override fun deleteById(id: String): Boolean {
        return customers.removeIf { it.id == id }
    }
}
