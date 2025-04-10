package repositories

import domain.entities.Solve

interface ISolveRepository {
    fun save(result: Solve)
    fun findAll(): List<Solve>
    fun findLatest(): Solve?
    fun clear()
}