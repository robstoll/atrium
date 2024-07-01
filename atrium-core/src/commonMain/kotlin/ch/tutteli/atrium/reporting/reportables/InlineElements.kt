package ch.tutteli.atrium.reporting.reportables

//TODO 1.3.0 check KDOC (including since of all types in this file)
/**
 * A [Reportable] which does not build itself a block but can be combined with other [InlineElement] seamlessly.
 *
 * @since 1.3.0
 */
interface InlineElement : Reportable

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
