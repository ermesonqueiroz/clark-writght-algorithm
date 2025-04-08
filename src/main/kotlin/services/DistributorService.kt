package services

import com.sun.net.httpserver.HttpHandler
import domain.entities.Distributor
import repositories.CustomerRepository
import repositories.DistributorRepository

class DistributorService( private val repository: DistributorRepository = DistributorRepository()) {

    fun create(distributor: Distributor): Distributor {
        return repository.save(distributor)
    }

    fun get(): Distributor {
        return repository.find()
    }


}