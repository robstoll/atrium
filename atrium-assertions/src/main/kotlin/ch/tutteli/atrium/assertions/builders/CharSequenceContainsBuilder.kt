package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty

/**
 * A builder for sophisticated [CharSequence] `contains` assertions.
 *
 * @property plant The [IAssertionPlant] to which created [IAssertion] shall be added.
 *
 * @constructor A builder for sophisticated [CharSequence] `contains` assertions.
 * @param plant The [IAssertionPlant] to which created [IAssertion] shall be added.
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
        //TODO move to atrium-assertions-code-completion-style only the implementation should remain here
        //val containsProp: KProperty<CharSequenceContainsBuilder> = plant::contains
        val contains = "contains" //containsProp.name
        //val containsNotFun: KFunction2<Any, Array<out Any>, IAssertionPlant<CharSequence>> = plant::containsNot
        val containsNot = "containsNot" //containsNotFun.name
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

