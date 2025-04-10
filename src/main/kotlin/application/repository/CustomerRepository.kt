package application.repository

import domain.entities.Customer

interface CustomerRepository {
    fun save(customer: Customer)
    fun findAll(): List<Customer>
    fun findById(id: String): Customer?
    fun update(customer: Customer): Boolean
    fun deleteById(id: String): Boolean
}