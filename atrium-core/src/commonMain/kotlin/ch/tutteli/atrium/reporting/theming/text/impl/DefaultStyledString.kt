package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.replaceWrap

internal data class DefaultStyledString(
    override val unstyled: String,
    override val maybeStyled: Option<String>,
    override val noLineBreak: Boolean,
    override val horizontalAlignment: HorizontalAlignment,
) : StyledString {

    override fun toString(): String = "SS(u=$unstyled, n=$noLineBreak, a=${horizontalAlignment.name})"

    override fun result(): String {
        val s = maybeStyled.getOrElse { unstyled }
        return replaceWrap(s)
    }

    override fun withoutLineBreaks(): StyledString = if (this.noLineBreak) this else this.copy(noLineBreak = true)
    override fun appendUnstyled(unstyledString: String) =
        copy(unstyled = unstyled + unstyledString, maybeStyled = maybeStyled.map { it + unstyledString })

    override fun withHorizontalAlignment(alignment: HorizontalAlignment): StyledString =
        copy(horizontalAlignment = alignment)
}
