package domain
import kotlinx.serialization.Serializable

@Serializable
class Customer(val id: Int, val position: Position)
