package land.mog.kov.extensions

fun String.nullIfEmpty(): String? {
    return if (isEmpty())
        null
    else
        this
}
