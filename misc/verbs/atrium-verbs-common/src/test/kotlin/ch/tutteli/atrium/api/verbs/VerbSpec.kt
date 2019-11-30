package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.specs.verbs.VerbSpec

object AssertSpec : VerbSpec(
    "assert" to { subject: Int, representation, options -> assert(subject, representation, options) },
    "assert" to { subject: Int, representation, options, assertionCreator ->
        assert(subject, representation, options, assertionCreator)
    },
    "assert" to { subject: Int?, representation, options -> assert(subject, representation, options) },
    "assert" to { act: () -> Any?, options, representation -> assert(options, representation, { act() }) }
)

object AssertThatSpec : VerbSpec(
    "assertThat" to { subject: Int, representation, options -> assertThat(subject, representation, options) },
    "assertThat" to { subject: Int, representation, options, assertionCreator ->
        assertThat(subject, representation, options, assertionCreator)
    },
    "assertThat" to { subject: Int?, representation, options -> assertThat(subject, representation, options) },
    "assertThat" to { act: () -> Any?, options, representation -> assertThat(options, representation, { act() }) }
)

object ExpectSpec : VerbSpec(
    "expect" to { subject: Int, representation, options -> expect(subject, representation, options) },
    "expect" to { subject: Int, representation, options, assertionCreator ->
        expect(subject, representation, options, assertionCreator)
    },
    "expect" to { subject: Int?, representation, options -> expect(subject, representation, options) },
    "expect" to { act: () -> Any?, options, representation -> expect(options, representation, { act() }) }
)
