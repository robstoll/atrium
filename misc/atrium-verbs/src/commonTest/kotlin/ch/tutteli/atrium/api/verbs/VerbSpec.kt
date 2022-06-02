package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.specs.verbs.VerbSpec

object ExpectSpec : VerbSpec(
    "expect" to { subject: Int -> expect(subject) },
    "expect" to { subject: Int, assertionCreator -> expect(subject, assertionCreator) },
    "expect" to { subject: Int? -> expect(subject) },
    "expect" to { act -> expect { act() } })

