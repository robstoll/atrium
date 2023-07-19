package ch.tutteli.atrium.api.infix.en_GB.creating.charSequence.toContain.impl

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtLeastCheckerStep
import kotlin.reflect.KFunction2

/** @since 1.1.0 */
internal object StaticNames {

    /** @since 1.1.0 */
    val notToContainValuesFun = run {
        val notToContain: KFunction2<Expect<CharSequence>, Values<Any>, Expect<CharSequence>> =
            Expect<CharSequence>::notToContain
        val values: KFunction2<Int, Array<out Int>, Values<Int>> = ::values
        "`${notToContain.name} ${values.name}`"
    }

    /** @since 1.1.0 */
    val atLeast = CharSequenceContains.EntryPointStep<CharSequence, *>::atLeast.name

    /** @since 1.1.0 */
    val butAtMost = AtLeastCheckerStep<CharSequence, *>::butAtMost.name

    /** @since 1.1.0 */
    val atMost = CharSequenceContains.EntryPointStep<CharSequence, *>::atMost.name

    /** @since 1.1.0 */
    val exactly = CharSequenceContains.EntryPointStep<CharSequence, *>::exactly.name

    /** @since 1.1.0 */
    val notOrAtMost = CharSequenceContains.EntryPointStep<CharSequence, *>::notOrAtMost.name
}
