package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.contains
import ch.tutteli.atrium.containsNot
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty

/**
 * A builder for sophisticated `contains` assertions.
 */
class CharSequenceContainsBuilder(private val plant: IAssertionPlant<CharSequence>) {

    /**
     * Prepares an assertion that [IAssertionPlant.subject] contains something exactly [times]
     * where [times] needs to be greater than 0.
     *
     * @return [ValuesOptions] to further define what whe expect is contained exactly [times].
     * @throws IllegalArgumentException In cases [times] is not a positive number.
     */
    fun exactly(times: Int): ValuesOptions {
        if (times < 0) throw IllegalArgumentException("only positive numbers allowed: $times given")
        if (times == 0) throw illegalArgumentExceptionUseContainsNot(plant)

        val translatable = when (times) {
            1 -> DescriptionCharSequenceAssertion.EXACTLY_TIME
            else -> DescriptionCharSequenceAssertion.EXACTLY_TIMES
        }
        return ValuesOptions(TranslatableWithArgs(translatable, times)) { it == times }
    }

    private fun illegalArgumentExceptionUseContainsNot(plant: IAssertionPlant<CharSequence>): Throwable {
        val containsProp: KProperty<CharSequenceContainsBuilder> = plant::contains
        val contains = containsProp.name
        val containsNotFun: KFunction1<CharSequence, IAssertionPlant<CharSequence>> = plant::containsNot
        val containsNot = containsNotFun.name
        return IllegalArgumentException("use $containsNot instead of $contains.${CharSequenceContainsBuilder::exactly.name}(0)")
    }


    /**
     * Provides methods to express what we expect is contained in the given [IAssertionPlant.subject].
     */
    inner class ValuesOptions(
        private val translatable: ITranslatable,
        private val check: (counter: Int) -> Boolean) {

        /**
         * Makes the assertion that [IAssertionPlant.subject] contains the given [objects]' [String.toString]
         * representation.
         *
         * @return This plant to support a fluent-style API.
         * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
         */
        fun values(vararg objects: Any): IAssertionPlant<CharSequence> {
            if (objects.isEmpty()) throw IllegalArgumentException("called `${ValuesOptions::values.name}()` without arguments")
            val assertions = mutableListOf<IAssertion>()
            objects.forEach { it ->
                val lazyAssertion = LazyThreadUnsafeBasicAssertion {
                    val expected = it.toString()
                    var index = plant.subject.indexOf(expected)
                    var counter = 0
                    while (index >= 0) {
                        index = plant.subject.indexOf(expected, index + 1)
                        ++counter
                    }
                    BasicAssertion(translatable, counter, check(counter))
                }
                assertions.add(AssertionGroup(ListAssertionGroupType, DescriptionCharSequenceAssertion.CONTAINS, it.toString(), listOf(lazyAssertion)))
            }
            plant.addAssertion(InvisibleAssertionGroup(assertions))
            return plant
        }
    }
}

