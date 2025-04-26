// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultCharSequenceAssertions


    /**
     * Starts the building process of a sophisticated `contains` assertions.
     */
    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toContainBuilder, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toContainBuilder()", "ch.tutteli.atrium.creating.proofs.toContainBuilder")
    )
fun <T : CharSequence> AssertionContainer<T>.containsBuilder(): CharSequenceContains.EntryPointStep<T, NoOpSearchBehaviour> = impl.containsBuilder(this)

    /**
     * Starts the building process of a sophisticated `contains` assertions and already applies a [NotCheckerStep] with
     * a [NotSearchBehaviour].
     */
    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToContainBuilder, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToContainBuilder()", "ch.tutteli.atrium.creating.proofs.notToContainBuilder")
    )
fun <T : CharSequence> AssertionContainer<T>.containsNotBuilder(): NotCheckerStep<T, NotSearchBehaviour> = impl.containsNotBuilder(this)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toStartWith, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toStartWith(expected)", "ch.tutteli.atrium.creating.proofs.toStartWith")
    )
fun <T : CharSequence> AssertionContainer<T>.startsWith(expected: CharSequence): Assertion = impl.startsWith(this, expected)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToStartWith, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToStartWith(expected)", "ch.tutteli.atrium.creating.proofs.notToStartWith")
    )
fun <T : CharSequence> AssertionContainer<T>.startsNotWith(expected: CharSequence): Assertion = impl.startsNotWith(this, expected)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toEndWith, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toEndWith(expected)", "ch.tutteli.atrium.creating.proofs.toEndWith")
    )
fun <T : CharSequence> AssertionContainer<T>.endsWith(expected: CharSequence): Assertion = impl.endsWith(this, expected)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToEndWith, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToEndWith(expected)", "ch.tutteli.atrium.creating.proofs.notToEndWith")
    )
fun <T : CharSequence> AssertionContainer<T>.endsNotWith(expected: CharSequence): Assertion = impl.endsNotWith(this, expected)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeEmpty, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeEmpty()", "ch.tutteli.atrium.creating.proofs.toBeEmpty")
    )
fun <T : CharSequence> AssertionContainer<T>.isEmpty(): Assertion = impl.isEmpty(this)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeEmpty, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeEmpty()", "ch.tutteli.atrium.creating.proofs.notToBeEmpty")
    )
fun <T : CharSequence> AssertionContainer<T>.isNotEmpty(): Assertion = impl.isNotEmpty(this)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeBlank, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeBlank()", "ch.tutteli.atrium.creating.proofs.notToBeBlank")
    )
fun <T : CharSequence> AssertionContainer<T>.isNotBlank(): Assertion = impl.isNotBlank(this)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toMatch, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toMatch(expected)", "ch.tutteli.atrium.creating.proofs.toMatch")
    )
fun <T : CharSequence> AssertionContainer<T>.matches(expected: Regex): Assertion = impl.matches(this, expected)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToMatch, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToMatch(expected)", "ch.tutteli.atrium.creating.proofs.notToMatch")
    )
fun <T : CharSequence> AssertionContainer<T>.mismatches(expected: Regex): Assertion = impl.mismatches(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: CharSequenceAssertions
    get() = getImpl(CharSequenceAssertions::class) { DefaultCharSequenceAssertions() }
