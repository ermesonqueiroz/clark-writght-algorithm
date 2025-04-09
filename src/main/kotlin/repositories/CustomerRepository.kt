package repositories

import domain.entities.Customer
import domain.entities.Position

class CustomerRepository {
    private val customers = mutableListOf<Customer>()
    private var currentId = 1
    companion object {
        private var instance: CustomerRepository ? = null
        fun getInstance(): CustomerRepository {
            if (instance == null) {
                instance = CustomerRepository()
            }

            return instance!!
        }
    }

    //esse position supostamente era pra ser um DTO, mas ta bom ja rs
    fun save(position: Position): Customer {
        val customerWithId = Customer(id = currentId++,position)
        customers.add(customerWithId)
        return customerWithId
    }

    fun findAll(): List<Customer> {
        return customers.toList()
    }

    fun findById(id: Int): Customer? {
        return customers.find { it.id == id }
    }

    fun update(customer: Customer): Boolean {
        val index = customers.indexOfFirst { it.id == customer.id }
        if (index != -1) {
            customers[index] = customer
            return true
        }
        return false
    }

    fun deleteById(id: Int): Boolean {
        return customers.removeIf { it.id == id }
    }
}
