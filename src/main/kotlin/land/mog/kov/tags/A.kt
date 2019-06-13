package land.mog.kov.tags

import land.mog.kov.ElementBuilder

class A: ElementBuilder("a") {
    var href: String?
        get() = attributes["href"]
        set(value) {
            if (value == null) attributes.remove("href")
            else attributes["href"] = value
        }
    
    init {
        this.classes
        this.href = href
    }
}
