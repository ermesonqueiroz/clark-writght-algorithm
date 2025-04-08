package domain.entities

import kotlinx.serialization.Serializable
import serializers.LinkedListCustomerSerializer
import java.util.LinkedList

@Serializable
class Route {
    @Serializable(with = LinkedListCustomerSerializer::class)
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
