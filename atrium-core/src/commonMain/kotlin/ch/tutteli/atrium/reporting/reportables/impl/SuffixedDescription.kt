package ch.tutteli.atrium.reporting.reportables.impl

import ch.tutteli.atrium.reporting.reportables.Description

class SuffixedDescription(description: Description, suffix: String) : Description {
    override val string: String = description.string + suffix
}
