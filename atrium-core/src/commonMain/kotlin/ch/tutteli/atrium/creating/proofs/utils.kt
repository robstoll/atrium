package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.reporting.reportables.Reportable

fun List<Reportable>.unwrapInvisibleGroupIfSingleElement(): List<Reportable> = when (this.size) {
    1 -> (this.first() as? InvisibleProofGroup)?.children ?: this
    else -> this
}
