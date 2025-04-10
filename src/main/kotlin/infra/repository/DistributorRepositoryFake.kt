package infra.repository

import application.repository.DistributorRepository
import domain.entities.Distributor

class DistributorRepositoryFake : DistributorRepository {
    private var distributor: Distributor? = null

    override fun save(distributor: Distributor): Distributor {
        this.distributor = distributor
        return distributor
    }

    override fun find(): Distributor {
        return distributor ?: throw IllegalStateException("Distribuidor não foi atribuído ainda")
    }
}