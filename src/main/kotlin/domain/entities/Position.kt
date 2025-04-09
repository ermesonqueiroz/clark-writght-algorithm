package domain.entities

import kotlin.math.pow
import kotlin.math.sqrt
import kotlinx.serialization.Serializable

@Serializable
class Position(var x: Int, var y: Int) {
    fun calculateDistance(position: Position): Double {
        return sqrt((position.x - x).toDouble().pow(2.0) + (position.y - y).toDouble().pow(2.0))
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}
