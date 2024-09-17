package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.reporting.reportables.Reportable

//TODO KDOC, move to impl?

fun List<Reportable>.unwrapInvisibleGroupIfSingleElement(): List<Reportable> = when (this.size) {
    1 -> getChildrenIfInvisibleElseItAsList(this.first())
    else -> this
}

fun List<Reportable>.unwrapInvisibleGroup(): List<Reportable> = this.flatMap {
    getChildrenIfInvisibleElseItAsList(it)
}

@Suppress("DEPRECATION")
private fun getChildrenIfInvisibleElseItAsList(reportable: Reportable) =
    if (reportable is InvisibleProofGroup) reportable.children
    //TODO remove with 2.0.0 at the latest and the suppress above
    else if (reportable is ch.tutteli.atrium.assertions.AssertionGroup && reportable.type is ch.tutteli.atrium.assertions.InvisibleAssertionGroupType) reportable.children
    else listOf(reportable)
