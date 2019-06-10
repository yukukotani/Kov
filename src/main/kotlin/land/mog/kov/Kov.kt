package land.mog.kov

import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.get

object Kov {
    fun patch(parent: Node, node: Node?, old: VNode?, new: VNode): Node? {
        if (old == new) {
            return node
        }
        else if (old == null) {
            val newNode = new.toNode()

            parent.appendChild(newNode)
            return newNode
        }
        else {
            if (node == null) {
                throw IllegalArgumentException("node is null even though old vnode is not null.")
            }

            if (old is VNode.VText && new is VNode.VText) {
                if (old.text != new.text) node.textContent = new.text
                return node
            }
            else if (old is VNode.VElement && new is VNode.VElement) {
                if (node !is Element) {
                    throw IllegalArgumentException("node type should be Element")
                }

                if (old.name != new.name) {
                    val newNode = new.toElement()
                    parent.insertBefore(newNode, node)
                    parent.removeChild(node)
                    return newNode
                }

                if (old.attributes != new.attributes) {
                    node.getAttributeNames().forEach { node.removeAttribute(it) }
                    new.attributes.forEach { (name, value) -> node.setAttribute(name, value) }
                }

                new.children.forEachIndexed { index, newVNode ->
                    patch(node, node.childNodes[index], old.children.getOrNull(index), newVNode)
                }
                return node
            }
            else {
                throw IllegalArgumentException("Patching between difference type nodes is currently not supported.")
            }
        }
    }
}
