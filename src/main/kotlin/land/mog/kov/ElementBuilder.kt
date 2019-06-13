package land.mog.kov

import land.mog.kov.tags.A
import land.mog.kov.tags.Div
import land.mog.kov.tags.P

open class ElementBuilder(private val root: VNode.VElement, rawAttributes: Map<String, String?> = root.attributes) {
    private val children = root.children.toMutableList()
    protected val attributes = vAttributeMapOf(rawAttributes).toMutableMap()
    var id: String?
        get() = attributes["id"]
        set(value) = setAttribute("id", value)
    var classes: String?
        get() = attributes["class"]
        set(value) = setAttribute("class", value)
    constructor(tagName: String): this(VNode.VElement(tagName))
    
    fun <T: ElementBuilder> appendElement(element: T, block: T.() -> Unit): ElementBuilder {
        element.apply(block)
        return this.addChildNode(element.build())
    }

    fun addChildNode(node: VNode): ElementBuilder {
        children.add(node)
        return this
    }

    fun build(): VNode.VElement {
        
        return root.copy(
            children = children,
            attributes = attributes
        )
    }
    
    protected fun setAttribute(name: String, value: String?) {
        if (value == null) attributes.remove(name)
        else attributes[name] = value
    }

    companion object {
        fun of(root: VNode.VElement, block: ElementBuilder.() -> Unit): VNode.VElement {
            val builder = ElementBuilder(root, root.attributes)
            block.invoke(builder)
            return builder.build()
        }
    }
}

fun ElementBuilder.text(text: String) = this.addChildNode(VNode.VText(text))

fun ElementBuilder.a(classes: String? = null, href: String? = null, block: A.() -> Unit) = appendElement(
    element = A().apply {
        this.classes = classes
        this.href = href
    },
    block = block
)
fun ElementBuilder.div(classes: String? = null, block: Div.() -> Unit) = appendElement(
    element = Div().apply {
        this.classes = classes
    },
    block = block
)
fun ElementBuilder.p(classes: String? = null, block: P.() -> Unit) = appendElement(
    element = P().apply {
        this.classes = classes
    },
    block = block
)

fun vAttributeMapOf(rawAttributes: Map<String, String?>): Map<String, String> {
    val map = mutableMapOf<String, String>()
    rawAttributes.forEach { (name, value) ->
        if (value != null) map[name] = value
    }

    return map
}
