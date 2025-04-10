package repositories

import domain.entities.Solve

class SolveRepository private constructor() : ISolveRepository {
    private val solveHistory = mutableListOf<Solve>()

    companion object {
        private var instance: SolveRepository? = null

        fun getInstance(): SolveRepository {
            if (instance == null) {
                instance = SolveRepository()
            }
            return instance!!
        }
    }

    override fun save(result: Solve) {
        solveHistory.add(result)
    }

    override fun findAll(): List<Solve> {
        return solveHistory.toList()
    }

    override fun findLatest(): Solve? {
        return solveHistory.lastOrNull()
    }

    override fun clear() {
        solveHistory.clear()
    }
}