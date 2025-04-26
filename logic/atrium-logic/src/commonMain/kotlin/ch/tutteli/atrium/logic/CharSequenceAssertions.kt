//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [CharSequence] type.
 */
@Deprecated("Switch to CharSequenceProofs, will be removed with 2.0.0 at the latest")
interface CharSequenceAssertions {

    /**
     * Starts the building process of a sophisticated `contains` assertions.
     */
    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toContainBuilder, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toContainBuilder()", "ch.tutteli.atrium.creating.proofs.toContainBuilder")
    )
    fun <T : CharSequence> containsBuilder(
        container: AssertionContainer<T>
    ): CharSequenceContains.EntryPointStep<T, NoOpSearchBehaviour>

    /**
     * Starts the building process of a sophisticated `contains` assertions and already applies a [NotCheckerStep] with
     * a [NotSearchBehaviour].
     */
    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToContainBuilder, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToContainBuilder()", "ch.tutteli.atrium.creating.proofs.notToContainBuilder")
    )
    fun <T : CharSequence> containsNotBuilder(
        container: AssertionContainer<T>
    ): NotCheckerStep<T, NotSearchBehaviour>

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toStartWith, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toStartWith(expected)", "ch.tutteli.atrium.creating.proofs.toStartWith")
    )
    fun <T : CharSequence> startsWith(container: AssertionContainer<T>, expected: CharSequence): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToStartWith, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToStartWith(expected)", "ch.tutteli.atrium.creating.proofs.notToStartWith")
    )
    fun <T : CharSequence> startsNotWith(container: AssertionContainer<T>, expected: CharSequence): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toEndWith, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toEndWith(expected)", "ch.tutteli.atrium.creating.proofs.toEndWith")
    )
    fun <T : CharSequence> endsWith(container: AssertionContainer<T>, expected: CharSequence): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToEndWith, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToEndWith(expected)", "ch.tutteli.atrium.creating.proofs.notToEndWith")
    )
    fun <T : CharSequence> endsNotWith(container: AssertionContainer<T>, expected: CharSequence): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeEmpty, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeEmpty()", "ch.tutteli.atrium.creating.proofs.toBeEmpty")
    )
    fun <T : CharSequence> isEmpty(container: AssertionContainer<T>): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeEmpty, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeEmpty()", "ch.tutteli.atrium.creating.proofs.notToBeEmpty")
    )
    fun <T : CharSequence> isNotEmpty(container: AssertionContainer<T>): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeBlank, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeBlank()", "ch.tutteli.atrium.creating.proofs.notToBeBlank")
    )
    fun <T : CharSequence> isNotBlank(container: AssertionContainer<T>): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toMatch, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toMatch(expected)", "ch.tutteli.atrium.creating.proofs.toMatch")
    )
    fun <T : CharSequence> matches(container: AssertionContainer<T>, expected: Regex): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToMatch, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToMatch(expected)", "ch.tutteli.atrium.creating.proofs.notToMatch")
    )
    fun <T : CharSequence> mismatches(container: AssertionContainer<T>, expected: Regex): Assertion
}
