package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.grouping
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.specs.verbs.VerbSpec

object ExpectSpec : VerbSpec(
    "expect" to { subject: Int -> expect(subject) },
    "expect" to { subject: Int, assertionCreator -> expect(subject, assertionCreator) },
    "expect" to { subject: Int? -> expect(subject) },
    "expect" to { act: () -> Any? -> expect { act() } },
    "expectGrouped" to { description, assertionCreator ->
        expectGrouped(description, groupingActions = assertionCreator)
    },
    "expectGrouped" to { description, assertionCreator ->
        _logicAppend { grouping(description, Text.Companion.EMPTY_PROVIDER, groupingActions = assertionCreator) }
    },
    "expect" to { subject -> expect(subject) },
)
