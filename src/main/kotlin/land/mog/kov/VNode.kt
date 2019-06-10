package land.mog.kov

import land.mog.kov.extensions.NodeType
import land.mog.kov.extensions.map
import land.mog.kov.extensions.nodeTypeEnum
import land.mog.kov.extensions.nullIfEmpty
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.Text
import kotlin.browser.document

sealed class VNode {

    abstract val children: List<VNode>
    abstract val node: Node?

    data class VText(
        val text: String,
        override val children: List<VNode> = listOf(),
        override val node: Node? = null
    ): VNode()

    data class VElement(
        val name: String,
        val attributes: Map<String, String> = mapOf(),
        override val children: List<VNode> = listOf(),
        override val node: Node? = null
    ): VNode()

    companion object {
        fun from(node: Node): VNode {
            return if (node.nodeTypeEnum == NodeType.TEXT_NODE) {
                VText(
                    text = node.nodeValue ?: ""
                )
            } else {
                val element = node as Element
                val attributes = vAttributeMapOf(
                    mapOf("id" to element.id, "class" to element.className.nullIfEmpty())
                )
                VElement(
                    name = element.nodeName.toLowerCase(),
                    children = element.childNodes.map { from(it) },
                    attributes = attributes
                )
            }
        }
    }
}

fun VNode.toNode(): Node {
    return when (this) {
        is VNode.VText -> Text(this.text)
        is VNode.VElement -> this.toElement()
    }
}

fun VNode.VElement.toElement(): Element {
    val element = document.createElement(this.name)
    this.attributes.forEach { (name, value) ->
        element.setAttribute(name, value)
    }
    this.children.forEach { 
        element.append(it.toNode())
    }
    return element
}
