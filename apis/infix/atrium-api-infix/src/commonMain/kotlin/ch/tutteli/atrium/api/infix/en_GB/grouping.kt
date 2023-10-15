package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.group
import ch.tutteli.atrium.logic.grouping
import ch.tutteli.atrium.reporting.Text

/**
 * Creates and appends a group based on the given [description] (optionally [representationProvider])
 * and [groupingActions] and returns an [ExpectGrouping].
 *
 * @param description The description of the group.
 * @param representationProvider Optionally, can be specified if an additional representation shall be reported
 *        (default is [Text.EMPTY_PROVIDER])
 * @param groupingActions Some action which defines what happens within the group (typically, creating some
 *        expectations via an expectation-verb such as `expect` or nesting the grouping further).
 *
 * @return An [ExpectGrouping], allowing to define further subgroups or expectations.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.GroupingSamples.group
 *
 * @since 1.1.0
 */
fun ExpectGrouping.group(
    description: String,
    representationProvider: () -> Any? = Text.EMPTY_PROVIDER,
    groupingActions: ExpectGrouping.() -> Unit
): ExpectGrouping = _logicAppend { grouping(description, representationProvider, groupingActions) }

/**
 * Creates and appends a group based on the given [description] (optionally [representationProvider])
 * and [assertionCreator] and returns an [Expect].
 *
 * @param description The description of the group.
 * @param representationProvider Optionally, can be specified if an additional representation shall be reported
 *  (default is [Text.EMPTY_PROVIDER])
 * @param assertionCreator a provider which states the expectations for the current subject belonging to this
 *   newly created group.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.GroupingSamples.group
 *
 * @since 1.1.0
 */
fun <T> Expect<T>.group(
    description: String,
    representationProvider: () -> Any? = Text.EMPTY_PROVIDER,
    assertionCreator: Expect<T>.() -> Unit
): Expect<T> = _logicAppend { group(description, representationProvider, assertionCreator) }
