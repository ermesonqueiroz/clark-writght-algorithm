package domain.entities

import domain.entities.Position
import kotlinx.serialization.Serializable

@Serializable
class Customer(val id: Int, val position: Position)
