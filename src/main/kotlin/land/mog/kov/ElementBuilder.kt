package land.mog.kov

class ElementBuilder(private val root: VNode.VElement, rawAttributes: Map<String, String?>) {
    private val children = root.children.toMutableList()
    private val attributes = vAttributeMapOf(rawAttributes).toMutableMap()
    var id: String?
        get() = attributes["id"]
        set(value) {
            if (value == null) attributes.remove("id")
            else attributes["id"] = value
    }
    
    fun appendElement(name: String, rawAttributes: Map<String, String?>, block: ElementBuilder.() -> Unit): ElementBuilder {
        val builder = ElementBuilder(VNode.VElement(name), rawAttributes)
        block.invoke(builder)
        return this.addChildNode(builder.build())
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

    companion object {
        fun of(root: VNode.VElement, block: ElementBuilder.() -> Unit): VNode.VElement {
            val builder = ElementBuilder(root, root.attributes)
            block.invoke(builder)
            return builder.build()
        }
    }
}

fun ElementBuilder.text(text: String) = this.addChildNode(VNode.VText(text))

fun ElementBuilder.a(classes: String? = null, href: String? = null, block: ElementBuilder.() -> Unit) = appendElement(
    name = "a", 
    rawAttributes = mapOf("class" to classes, "href" to href), 
    block = block
)
fun ElementBuilder.div(classes: String? = null, block: ElementBuilder.() -> Unit) = appendElement(
    name = "div",
    rawAttributes = mapOf("class" to classes),
    block = block
)
fun ElementBuilder.p(classes: String? = null, block: ElementBuilder.() -> Unit) = appendElement(
    name = "p",
    rawAttributes = mapOf("class" to classes),
    block = block
)

fun vAttributeMapOf(rawAttributes: Map<String, String?>): Map<String, String> {
    val map = mutableMapOf<String, String>()
    rawAttributes.forEach { (name, value) ->
        if (value != null) map[name] = value
    }

    return map
}
