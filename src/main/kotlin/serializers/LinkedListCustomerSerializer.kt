package serializers

import domain.entities.Customer
import kotlinx.serialization.KSerializer

object LinkedListCustomerSerializer : LinkedListSerializer<Customer>(Customer.serializer())
