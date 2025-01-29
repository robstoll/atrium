package ch.tutteli.atrium.reporting.reportables

import ch.tutteli.atrium.reporting.text.TextObjFormatter

//TODO 1.3.0 somewhat a bit odd that diagnostics are under reporting but proof are under creating


//TODO 1.3.0 check KDOC (including since of all types in this file)
/**
 * A [Diagnostic] which does not build itself a block but can be combined with other [InlineElement] seamlessly.
 *
 * @since 1.3.0
 */
interface InlineElement : Diagnostic

/**
 *
 * @since 1.3.0
 */
interface TextElement : InlineElement {
    val string: String
}

/**
 *
 * @since 1.3.0
 */
interface Description : TextElement {
    @Deprecated("only here to ease migration, switch to string", ReplaceWith("string"))
    fun getDefault() = string
}

/**
 * Represents a representation of an instance, i.e. is typically formatted by a [TextObjFormatter]
 *
 * @since 1.3.0
 */
interface Representation: InlineElement {
    val representation: Any?
}
