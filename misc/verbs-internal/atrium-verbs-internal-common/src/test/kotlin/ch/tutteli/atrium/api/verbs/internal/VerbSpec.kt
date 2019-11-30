package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.specs.verbs.VerbSpec

object ExpectSpec : VerbSpec(
    "expect" to { subject: Int, representation, options -> expect(subject, representation, options) },
    "expect" to { subject: Int, representation, options, assertionCreator ->
        expect(subject, representation, options, assertionCreator)
    },
    "expect" to { subject: Int?, representation, options -> expect(subject, representation, options) },
    "expect" to { act: () -> Any?, options, representation -> expect(options, representation, { act() }) }
)
