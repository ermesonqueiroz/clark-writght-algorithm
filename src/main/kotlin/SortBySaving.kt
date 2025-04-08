import domain.entities.Node

class SortBySaving : Comparator<Node> {
    override fun compare(a: Node, b: Node): Int {
        return b.savings.compareTo(a.savings);
    }
}
