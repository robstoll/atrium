package ch.tutteli.atrium.verbs

import ch.tutteli.atrium.spec.verbs.VerbSpec
import ch.tutteli.atrium.verbs.assert.assert as deprecatedAssert
import ch.tutteli.atrium.verbs.assertthat.assertThat as deprecatedAssertThat
import ch.tutteli.atrium.verbs.expect.expect as deprecatedExpect

object AssertSpec : VerbSpec(
    "assert" to { subject -> assert(subject) },
    "assert" to { subject, assertionCreator -> assert(subject, assertionCreator) },
    "assert" to { subject -> assert(subject) },
    "assert" to { act -> assert { act() } })

object AssertThatSpec : VerbSpec(
    "assertThat" to { subject -> assertThat(subject) },
    "assertThat" to { subject, assertionCreator -> assertThat(subject, assertionCreator) },
    "assertThat" to { subject -> assertThat(subject) },
    "assertThat" to { act -> assertThat { act() } })

object ExpectSpec : VerbSpec(
    "expect" to { subject -> expect(subject) },
    "expect" to { subject, assertionCreator -> expect(subject, assertionCreator) },
    "expect" to { subject -> expect(subject) },
    "expect" to { act -> expect { act() } })


@Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
object DepreactedAssertSpec : VerbSpec(
    "assert" to { subject -> deprecatedAssert(subject) },
    "assert" to { subject, assertionCreator -> deprecatedAssert(subject, assertionCreator) },
    "assert" to { subject -> deprecatedAssert(subject) },
    "assert" to { act -> deprecatedAssert { act() } })

@Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
object DeprecatedAssertThatSpec : VerbSpec(
    "assertThat" to { subject -> deprecatedAssertThat(subject) },
    "assertThat" to { subject, assertionCreator -> deprecatedAssertThat(subject, assertionCreator) },
    "assertThat" to { subject -> deprecatedAssertThat(subject) },
    "assertThat" to { act -> deprecatedAssertThat { act() } })

@Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
object DeprecatedExpectSpec : VerbSpec(
    "expect" to { subject -> deprecatedExpect(subject) },
    "expect" to { subject, assertionCreator -> deprecatedExpect(subject, assertionCreator) },
    "expect" to { subject -> deprecatedExpect(subject) },
    "expect" to { act -> deprecatedExpect { act() } })
