package domain.entities

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
class Customer(val id: String, val position: Position) {
    companion object {
        fun create(position: Position): Customer {
            return Customer(UUID.randomUUID().toString(), position)
        }
    }
}
