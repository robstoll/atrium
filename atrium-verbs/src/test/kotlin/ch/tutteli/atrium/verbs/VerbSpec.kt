package ch.tutteli.atrium.verbs

import ch.tutteli.atrium.verbs.assert.assert
import ch.tutteli.atrium.verbs.assertthat.assertThat
import ch.tutteli.atrium.verbs.expect.expect

class AssertSpec : VerbSpecTemplate(
    "assert" to { i -> assert(i) },
    "assert" to { i, createAssertions -> assert(i, createAssertions) },
    "assert" to { i -> assert(i) },
    "assert" to { act -> assert { act() } })

class AssertThatSpec : VerbSpecTemplate(
    "assertThat" to { i -> assertThat(i) },
    "assertThat" to { i, createAssertions -> assertThat(i, createAssertions) },
    "assertThat" to { i -> assertThat(i) },
    "assertThat" to { act -> assertThat { act() } })

class ExpectSpec : VerbSpecTemplate(
    "expect" to { i -> expect(i) },
    "expect" to { i, createAssertions -> expect(i, createAssertions) },
    "expect" to { i -> expect(i) },
    "expect" to { act -> expect { act() } })
