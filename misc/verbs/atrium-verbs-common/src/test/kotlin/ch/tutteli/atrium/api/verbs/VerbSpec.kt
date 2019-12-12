package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.specs.verbs.VerbSpec

object AssertSpec : VerbSpec(
    "assert" to { subject: Int -> assert(subject) },
    "assert" to { subject: Int, assertionCreator -> assert(subject, assertionCreator) },
    "assert" to { subject: Int? -> assert(subject) },
    "assert" to { act -> assert { act() } })

object AssertThatSpec : VerbSpec(
    "assertThat" to { subject: Int -> assertThat(subject) },
    "assertThat" to { subject: Int, assertionCreator -> assertThat(subject, assertionCreator) },
    "assertThat" to { subject: Int? -> assertThat(subject) },
    "assertThat" to { act -> assertThat { act() } })

object ExpectSpec : VerbSpec(
    "expect" to { subject: Int -> expect(subject) },
    "expect" to { subject: Int, assertionCreator -> expect(subject, assertionCreator) },
    "expect" to { subject: Int? -> expect(subject) },
    "expect" to { act -> expect { act() } })

