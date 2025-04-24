package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.reporting.reportables.Reportable

//TODO 1.3.0 KDOC, move to impl?

/** @since 1.3.0 */
fun List<Reportable>.unwrapInvisibleGroupIfSingleElement(): List<Reportable> = when (this.size) {
    1 -> getChildrenIfInvisibleElseItAsList(this.first())
    else -> this
}

/** @since 1.3.0 */
fun List<Reportable>.unwrapInvisibleGroup(): List<Reportable> = this.flatMap {
    getChildrenIfInvisibleElseItAsList(it)
}

@Suppress("DEPRECATION")
/** @since 1.3.0 */
private fun getChildrenIfInvisibleElseItAsList(reportable: Reportable) =
    if (reportable is InvisibleProofGroup) reportable.children
    //TODO remove with 2.0.0 at the latest and the suppress above
    else if (reportable is ch.tutteli.atrium.assertions.AssertionGroup && reportable.type is ch.tutteli.atrium.assertions.InvisibleAssertionGroupType) reportable.children
    else listOf(reportable)
