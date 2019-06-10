package land.mog.kov.extensions

import org.w3c.dom.Node

val Node.nodeTypeEnum: NodeType
    get() = NodeType.values().find { it.id == this.nodeType } ?: throw IllegalStateException("Node type with id \"${this.nodeType}\" is not defined.")

enum class NodeType(val id: Short) {
    ELEMENT_NODE(1),
    @Deprecated("Still works, but maybe removed in the future.")
    ATTRIBUTE_NODE(2),
    TEXT_NODE(3),
    @Deprecated("Still works, but maybe removed in the future.")
    CDATA_SECTION_NODE(4),
    @Deprecated("Still works, but maybe removed in the future.")
    ENTITY_REFERENCE_NODE(5),
    @Deprecated("Still works, but maybe removed in the future.")
    ENTITY_NODE(6),
    PROCESSING_INSTRUCTION_NODE(7),
    COMMENT_NODE(8),
    DOCUMENT_NODE(9),
    DOCUMENT_TYPE_NODE(10),
    DOCUMENT_FRAGMENT_NODE(11),
    @Deprecated("Still works, but maybe removed in the future.")
    NOTATION_NODE(12);
}
