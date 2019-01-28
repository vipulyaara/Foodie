package com.foodie.consumer.algorithms

/**
 * @author Vipul Kumar; dated 08/01/19.
 */
fun main(args: Array<String>) {
    val array = arrayOf(1, 2, 3, 4, 5, 7, 8)
    var root = bstRootNode(array, 0, array.size - 1)
    root = insert(root, 6)
//    println(search(root, 6))
    preOrder(root)
}

fun search(root: Node?, i: Int): Node? {
    if (root == null) {
        throw RuntimeException("Key does not exist")
    }
    if (i < root.data) return search(root.left, i)
    if (i > root.data) return search(root.right, i)
    if (i == root.data) return root
    return null
}

fun insert(root: Node?, i: Int): Node? {
    var newRoot = root
    if (newRoot == null) {
        newRoot = Node(i)
    }
    if (newRoot.data > i) newRoot.left = insert(newRoot.left, i)
    if (newRoot.data < i) newRoot.right = insert(newRoot.right, i)
    return newRoot
}

fun bstRootNode(array: Array<Int>, start: Int, end: Int): Node? {
    if (start > end) {
        return null
    }
    val mid = (end + start) / 2
    return Node(
        array[mid],
        bstRootNode(array, start, mid - 1),
        bstRootNode(array, mid + 1, end)
    )
}

fun preOrder(node: Node?) {
    if (node == null) {
        return
    }
    print(node.data.toString() + " ")
    preOrder(node.left)
    preOrder(node.right)
}

data class Node(var data: Int, var left: Node? = null, var right: Node? = null)
