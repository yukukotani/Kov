package land.mog.kov.extensions

import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.w3c.dom.get

inline fun NodeList.forEach(action: (Node) -> Unit) {
    for (i in 0 until this.length) {
        action.invoke(this[i]!!)
    }
}

inline fun <R> NodeList.map(transform: (Node) -> R): List<R> {
    val list = mutableListOf<R>()
    for (i in 0 until this.length) {
        list.add(transform.invoke(this[i]!!))
    }
    
    return list
}
