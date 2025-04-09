package repositories

import application.Registry
import domain.entities.Distributor

class DistributorRepository private constructor() {
    private var distributor: Distributor? = null

    companion object {
        private var instance: DistributorRepository ? = null
        fun getInstance(): DistributorRepository {
            if (instance == null) {
                instance = DistributorRepository()
            }

            return instance!!
        }
    }

    fun save(distributor: Distributor): Distributor {
        this.distributor = distributor
        return distributor
    }

    fun find(): Distributor {
        return distributor ?: throw IllegalStateException("Distribuidor não foi atribuído ainda")
    }


}