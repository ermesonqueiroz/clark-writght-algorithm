package services

import domain.Distributor
import repositories.DistributorRepository

class DistributorService(
    private val repository: DistributorRepository = DistributorRepository()
) {

    fun create(distributor: Distributor): Distributor {
        return repository.save(distributor)
    }

    fun get(): Distributor {
        return repository.find()
    }


}