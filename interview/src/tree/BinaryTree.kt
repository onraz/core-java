package tree

data class Node(
        val value: String,
        var left: Node? = null,
        var right: Node? = null
) {
    fun left(node: Node) {
        this.left = node
    }

    fun right(node: Node) {
        this.right = node
    }
}

data class Tree(val root: Node) {
    fun print() {
        fun printTree(tree: Node) {
            println(tree.value)
            tree.left?.let { printTree(it) }
            tree.right?.let { printTree(it) }
        }
        printTree(root)
    }

    fun add(value: String) {
        fun addTree(value: String, tree: Node) {
            if (value < tree.value) {
                if (tree.left == null) {
                    tree.left = Node(value)
                } else {
                    addTree(value, tree.left!!)
                }
            } else {
                if (tree.right == null) {
                    tree.right = Node(value)
                } else {
                    addTree(value, tree.right!!)
                }
            }
        }
        addTree(value, root)
    }
}

fun main() {
    val tree = Tree(Node("25"))
    tree.add("10")
    tree.add("30")
    tree.print()
}