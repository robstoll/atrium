package ch.tutteli.atrium.reporting.theming.text

import ch.tutteli.atrium.reporting.reportables.Icon

// TODO 1.3.0 KDOC
interface TextIconStyler {
    fun style(icon: Icon): StyledString
}
