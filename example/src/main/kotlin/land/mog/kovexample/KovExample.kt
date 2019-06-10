package land.mog.kovexample

import land.mog.kov.*
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    val root = document.getElementById("root")!!
    val rootVNode = VNode.from(root)
    val old = ElementBuilder.of(rootVNode as VNode.VElement) {
        div {
            id = "parent"
            p { 
                text("inP") 
            }
            div(classes = "oldDiv") {
                text("inDiv")
            }
        }
    }
    val new = ElementBuilder.of(rootVNode) {
        div {
            id = "newParent"
            div {
                id = "divFromP"
                text("Div from P")
            }
            div(classes = "newDiv") { 
                text("inDiv") 
            }
            p { 
                text("P appended") 
            }
            a(href = "https://google.co.jp") {
                text("A appended with href")
            }
        }
    }

    Kov.patch(root.parentNode!!, root, VNode.from(root), old)
    window.setTimeout({
        Kov.patch(root.parentNode!!, root, VNode.from(root), new)
    }, 2000)
}
