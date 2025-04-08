package domain.entities

import java.util.LinkedList

class Route {
    val customers = LinkedList<Customer>()

    fun add(customer: Customer) {
        customers.add(customer)
    }

    fun getCustomers(): List<Customer> {
        return customers
    }

    fun containsCustomer(customer: Customer): Boolean {
        return customers.any { it.id == customer.id }
    }

}
