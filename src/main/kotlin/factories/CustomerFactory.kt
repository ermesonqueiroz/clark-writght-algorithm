package factories

import domain.entities.Customer
import domain.entities.Position

object CustomerFactory {
    fun fromCsvRow(row: List<String>): Customer {
        val id = row[0]
        val x = row[1].toInt()
        val y = row[2].toInt()

        val position = Position(x, y)
        return Customer(id, position)
    }
}
