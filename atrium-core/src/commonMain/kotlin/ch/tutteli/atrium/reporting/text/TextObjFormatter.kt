package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.reporting.theming.text.StyledString

// TODO 1.3.0 KDOC
// TODO 1.3.0 merge with TextObjectFormatter ?
interface TextObjFormatter {
    fun format(value: Any?): StyledString
}
