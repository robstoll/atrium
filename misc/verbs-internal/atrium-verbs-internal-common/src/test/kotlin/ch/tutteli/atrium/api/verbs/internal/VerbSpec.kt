package ch.tutteli.atrium.api.verbs.internal

object ExpectSpec : ch.tutteli.atrium.specs.verbs.VerbSpec(
    "expect" to { subject: Int -> expect(subject) },
    "expect" to { subject: Int, assertionCreator -> expect(subject, assertionCreator) },
    "expect" to { subject: Int? -> expect(subject) },
    "expect" to { act -> expect { act() } })

