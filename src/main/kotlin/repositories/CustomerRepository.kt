package repositories

import domain.Customer

class CustomerRepository {
    private val customers = mutableListOf<Customer>()

    fun save(customer: Customer): Customer {
        customers.add(customer)
        return customer
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
