package factories

import domain.Customer
import domain.Position

object CustomerFactory {
    fun fromCsvRow(row: List<String>): Customer {
        val id = row[0].toInt()
        val x = row[1].toInt()
        val y = row[2].toInt()

        val position = Position(x, y)
        return Customer(id, position)
    }
}
