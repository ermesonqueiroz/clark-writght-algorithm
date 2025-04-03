package factories

import strategies.RoutingStrategy
import strategies.ClarkWrightAlgorithm
import strategies.NearestNeighbor

object RoutingStrategyFactory {
    fun create(strategyName: String): RoutingStrategy {
        return when (strategyName.lowercase()) {
            "clarkwright" -> ClarkWrightAlgorithm()
            "nearestneighbor" -> NearestNeighbor()
            else -> throw IllegalArgumentException("Unknown strategy: $strategyName")
        }
    }
}