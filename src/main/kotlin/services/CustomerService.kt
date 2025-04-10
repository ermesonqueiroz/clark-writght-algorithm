package services

import application.Registry
import domain.entities.Customer
import domain.entities.Position
import application.repository.CustomerRepository

class CustomerService {
    private val customerRepository = Registry.getInstance().inject("customerRepository") as CustomerRepository

    //esse position supostamente era pra ser um DTO, mas ta bom ja rs
    fun create(customer: Customer)  {
        return customerRepository.save(customer)
    }

    fun getAll(): List<Customer> {
        return customerRepository.findAll()
    }

    fun getById(id: String): Customer? {
        return customerRepository.findById(id)
    }

    fun update(id: String, newX: Int, newY: Int): Boolean {
        val customer = customerRepository.findById(id) ?: return false
        val updated = Customer(id, Position(newX, newY))
        return customerRepository.update(updated)
    }

    fun delete(id: String): Boolean {
        return customerRepository.deleteById(id)
    }
}