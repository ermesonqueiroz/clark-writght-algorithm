package repositories

import domain.entities.Distributor

class DistributorRepository {
    private var distributor: Distributor?= null
    fun save(distributor: Distributor) : Distributor {
        this.distributor = distributor
        return distributor
    }
    fun find(): Distributor {
        return distributor ?: throw IllegalStateException("Distribuidor nao foi atribuido ainda")
    }
}
