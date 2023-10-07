package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.specs.verbs.VerbSpec

object ExpectSpec : VerbSpec(
    "expect" to { subject: Int -> expect(subject) },
    "expect" to { subject: Int, assertionCreator -> expect(subject, assertionCreator) },
    "expect" to { subject: Int? -> expect(subject) },
    "expect" to { act -> expect { act() } },
    "expectGrouped" to { description, assertionCreator ->
        expectGrouped(description, groupingActions = assertionCreator)
    },
    "expectGrouped" to { description, assertionCreator ->
        @Suppress(
            // here we don't have access to the API, hence using this deprecated function is OK
            "DEPRECATION"
        )
        expectGrouped(description, groupingActions = assertionCreator)
    },
    "expect" to { subject -> expect(subject) },
)

