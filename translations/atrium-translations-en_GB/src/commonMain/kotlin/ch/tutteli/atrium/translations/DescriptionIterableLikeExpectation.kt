// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
enum class DescriptionIterableLikeExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    ALL_ELEMENTS("elements need all"),

    /** @since 0.18.0 */
    AN_ELEMENT_WHICH_NEEDS("an element which needs"),

    /** @since 0.18.0 */
    AN_ELEMENT_WHICH_EQUALS("an element which equals"),

    /** @since 0.18.0 */
    AT_LEAST("is at least"),

    /** @since 0.18.0 */
    AT_MOST("is at most"),

    /** @since 0.18.0 */
    TO_CONTAIN("to contain"),

    /** @since 0.18.0 */
    NOT_TO_CONTAIN("not to contain"),

    /** @since 1.0.0 */
    USE_NOT_TO_HAVE_ELEMENTS_OR_NONE("use %s if you don't care about the empty case, then the expectation would have been met."),

    /** @since 0.18.0 */
    ELEMENT_WITH_INDEX("element %s"),

    /** @since 0.18.0 */
    EXACTLY("is exactly"),

    /** @since 0.18.0 */
    IN_ANY_ORDER("%s, in any order"),

    /** @since 0.18.0 */
    IN_ANY_ORDER_ONLY("%s only, in any order"),

    /** @since 0.18.0 */
    IN_ORDER("%, in order"),

    /** @since 0.18.0 */
    IN_ORDER_ONLY("%s only, in order"),

    /** @since 0.18.0 */
    IN_ORDER_ONLY_GROUPED("%s only, in order, grouped"),

    /** @since 0.18.0 */
    INDEX("index %s"),

    /** @since 0.18.0 */
    INDEX_FROM_TO("index %s..%s"),

    /** @since 0.18.0 */
    NUMBER_OF_SUCH_ELEMENTS("number of such elements"),

    /** @since 0.18.0 */
    SIZE_EXCEEDED("❗❗ hasNext() returned false"),

    /** @since 0.18.0 */
    WARNING_ADDITIONAL_ELEMENTS("additional elements detected"),

    /** @since 0.18.0 */
    WARNING_MISMATCHES("following elements were mismatched"),

    /** @since 0.18.0 */
    WARNING_MISMATCHES_ADDITIONAL_ELEMENTS("mismatches and additional elements detected"),

    /** @since 0.18.0 */
    A_NEXT_ELEMENT("a next element"),

    /** @since 0.18.0 */
    NO_ELEMENTS("❗❗ cannot be determined, empty IterableLike"),

    /** @since 0.18.0 */
    DUPLICATE_ELEMENTS("duplicate elements"),

    /** @since 0.18.0 */
    DUPLICATED_BY("duplicated by index: %s"),

    /** @since 0.18.0 */
    ELEMENT_NOT_FOUND("but no such element was found"),

    /** @since 0.18.0 */
    NUMBER_OF_ELEMENTS_FOUND("and %s such elements were found"),

    /** @since 1.0.0 */
    NOT_TO_HAVE_ELEMENTS_OR_ANY("not to have elements or any of it needs"),

    /** @since 1.0.0 */
    NOT_TO_HAVE_ELEMENTS_OR_ALL("not to have elements or all need"),

    /** @since 1.0.0 */
    NOT_TO_HAVE_ELEMENTS_OR_NONE("not to have elements or none"),

    /** @since 1.0.0 */
    NEITHER_EMPTY_NOR_ELEMENT_FOUND("but it had next elements and no such element was found"),
}
