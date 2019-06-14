package land.mog.kov.tags

import land.mog.kov.ElementBuilder

class B: ElementBuilder("b")

class BASE: ElementBuilder("base") {
    var href: String?
        get() = attributes["href"]
        set(value) = setAttribute("href", value)

    var target: String?
        get() = attributes["target"]
        set(value) = setAttribute("target", value)
}

class BDI: ElementBuilder("bdi")

class BDO: ElementBuilder("bdo")

class BLOCKQUOTE: ElementBuilder("blockquote") {
    var cite: String?
        get() = attributes["cite"]
        set(value) = setAttribute("cite", value)
}

// TODO: implement html5 event handler, such as onUndo
class BODY: ElementBuilder("body")

class BR: ElementBuilder("br")

class BUTTON: ElementBuilder("button") {
    var autoFocus: Boolean?
        get() = attributes["autofocus"]?.toBoolean()
        set(value) = setAttribute("autofocus", value?.toString())

    var disabled: Boolean?
        get() = attributes["disabled"]?.toBoolean()
        set(value) = setAttribute("disabled", value?.toString())

    var form: String?
        get() = attributes["form"]
        set(value) = setAttribute("form", value)

    var formAction: String?
        get() = attributes["formaction"]
        set(value) = setAttribute("formaction", value)

    // TODO: Enum-ize
    var formEncType: String?
        get() = attributes["formenctype"]
        set(value) = setAttribute("formenctype", value)

    // TODO: Enum-ize
    var formMethod: String?
        get() = attributes["formmethod"]
        set(value) = setAttribute("formmethod", value)

    var formNovalidate: Boolean?
        get() = attributes["formnovalidate"]?.toBoolean()
        set(value) = setAttribute("formnovalidate", value?.toString())

    var formTarget: String?
        get() = attributes["formtarget"]
        set(value) = setAttribute("formtarget", value)

    var name: String?
        get() = attributes["name"]
        set(value) = setAttribute("name", value)

    var value: String?
        get() = attributes["value"]
        set(value) = setAttribute("value", value)

    // TODO: enum-ize
    var type: String?
        get() = attributes["type"]
        set(value) = setAttribute("type", value)
}
