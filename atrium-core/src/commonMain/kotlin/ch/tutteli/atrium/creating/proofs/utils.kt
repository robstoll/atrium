package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.reporting.reportables.Reportable

fun List<Reportable>.unwrapInvisibleGroupIfSingleElement(): List<Reportable> = when (this.size) {
    1 -> getChildrenIfInvisibleElseItAsList(this.first())
    else -> this
}

fun List<Reportable>.unwrapInvisibleGroup(): List<Reportable> = this.flatMap {
    getChildrenIfInvisibleElseItAsList(it)
}

private fun getChildrenIfInvisibleElseItAsList(reportable: Reportable) =
    if (reportable is InvisibleProofGroup) reportable.children
    //TODO remove with 2.0.0 at the latest
    else if (reportable is AssertionGroup && reportable.type is InvisibleAssertionGroupType) reportable.children
    else listOf(reportable)
