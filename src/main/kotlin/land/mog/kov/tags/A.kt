package land.mog.kov.tags

import land.mog.kov.ElementBuilder

class A: ElementBuilder("a") {
    var href: String?
        get() = attributes["href"]
        set(value) = setAttribute("href", value)

    var target: String?
        get() = attributes["target"]
        set(value) = setAttribute("target", value)


    var ping: String?
        get() = attributes["ping"]
        set(value) = setAttribute("ping", value)

    var rel: String?
        get() = attributes["rel"]
        set(value) = setAttribute("rel", value)

    var hrefLang: String?
        get() = attributes["hreflang"]
        set(value) = setAttribute("hreflang", value)

    var type: String?
        get() = attributes["type"]
        set(value) = setAttribute("type", value)
    
    init {
        this.classes = classes
        this.href = href
    }
}

class ABBR: ElementBuilder("abbr")

class ADDRESS: ElementBuilder("address")

class AREA: ElementBuilder("area") {
    var coords: String?
        get() = attributes["coords"]
        set(value) = setAttribute("coords", value)

    var href: String?
        get() = attributes["href"]
        set(value) = setAttribute("href", value)

    var hrefLang: String?
        get() = attributes["hreflang"]
        set(value) = setAttribute("hreflang", value)

    var alt: String?
        get() = attributes["alt"]
        set(value) = setAttribute("alt", value)

    var target: String?
        get() = attributes["target"]
        set(value) = setAttribute("target", value)

    var media: String?
        get() = attributes["media"]
        set(value) = setAttribute("media", value)

    var rel: String?
        get() = attributes["rel"]
        set(value) = setAttribute("rel", value)

    var ping: String?
        get() = attributes["ping"]
        set(value) = setAttribute("ping", value)

    var type: String?
        get() = attributes["type"]
        set(value) = setAttribute("type", value)
}

class ARTICLE: ElementBuilder("article")

class ASIDE: ElementBuilder("aside")

class AUDIO: ElementBuilder("audio") {
    var src: String?
        get() = attributes["src"]
        set(value) = setAttribute("src", value)

    var autoBuffer: Boolean?
        get() = attributes["autobuffer"]?.toBoolean()
        set(value) = setAttribute("autobuffer", value?.toString())

    var autoPlay: Boolean?
        get() = attributes["autoplay"]?.toBoolean()
        set(value) = setAttribute("autoplay", value?.toString())

    var loop: Boolean?
        get() = attributes["loop"]?.toBoolean()
        set(value) = setAttribute("loop", value?.toString())

    var controls: Boolean?
        get() = attributes["controls"]?.toBoolean()
        set(value) = setAttribute("controls", value?.toString())
}
