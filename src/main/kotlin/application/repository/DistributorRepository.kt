package application.repository

import domain.entities.Distributor

interface DistributorRepository {
    fun save(distributor: Distributor): Distributor
    fun find(): Distributor
}