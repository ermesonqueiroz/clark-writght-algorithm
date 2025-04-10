package domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Solve(
    val strategyName: String,
    val customersCount: Int,
    val routes: List<Route>,
    
)