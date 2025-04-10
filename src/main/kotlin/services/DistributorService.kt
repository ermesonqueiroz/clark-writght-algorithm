package services

import application.Registry
import application.repository.DistributorRepository
import domain.entities.Distributor

class DistributorService {
    private val repository = Registry.getInstance().inject("distributorRepository") as DistributorRepository

    fun create(distributor: Distributor): Distributor {
        return repository.save(distributor)
    }

    fun get(): Distributor {
        return repository.find()
    }
}