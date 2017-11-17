package ch.tutteli.atrium.verbs

import ch.tutteli.atrium.spec.verbs.VerbSpec
import ch.tutteli.atrium.verbs.assert.assert
import ch.tutteli.atrium.verbs.assertthat.assertThat
import ch.tutteli.atrium.verbs.expect.expect

object AssertSpec : VerbSpec(
    "assert" to { subject -> assert(subject) },
    "assert" to { subject, assertionCreator -> assert(subject, assertionCreator) },
    "assert" to { subject-> assert(subject) },
    "assert" to { act -> assert { act() } })

object AssertThatSpec : VerbSpec(
    "assertThat" to { subject-> assertThat(subject) },
    "assertThat" to { subject, assertionCreator -> assertThat(subject, assertionCreator) },
    "assertThat" to { subject-> assertThat(subject) },
    "assertThat" to { act -> assertThat { act() } })

object ExpectSpec : VerbSpec(
    "expect" to { subject-> expect(subject) },
    "expect" to { subject, assertionCreator -> expect(subject, assertionCreator) },
    "expect" to { subject-> expect(subject) },
    "expect" to { act -> expect { act() } })
