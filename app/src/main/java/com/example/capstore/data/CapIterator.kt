package com.example.capstore.data

class CapIterator(private val caps: List<Cap>) {
    private var index = 0
    fun hasNext(): Boolean {
        return index < caps.size
    }
    fun next(): Cap {
        if (!hasNext()) {
            throw Exception("No more caps")
        }
        return caps[index++]
    }
}
class CapCollection(private val caps: List<Cap>) {
    fun iterator(): CapIterator {
        return CapIterator(caps)
    }
}
fun CapCollection.toListFromIterator(): List<Cap> {
    val result = mutableListOf<Cap>()
    val it = iterator()
    while (it.hasNext()) {
        result.add(it.next())
    }
    return result
}

