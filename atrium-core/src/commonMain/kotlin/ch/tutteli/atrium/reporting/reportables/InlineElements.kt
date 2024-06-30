package ch.tutteli.atrium.reporting.reportables

/**
 * A [Reportable] which does not build itself a block but can be combined with other [InlineElement] seamlessly.
 */
interface InlineElement : Reportable


interface TextElement : InlineElement {
    val string: String
}

