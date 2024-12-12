// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
@Deprecated(
    "Switch to DescriptionIterableLikeProof from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof")
)
enum class DescriptionIterableLikeExpectation(override val value: String) : StringBasedTranslatable {

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.ALL_ELEMENTS")
    )
    /** @since 0.18.0 */
    ALL_ELEMENTS("elements need all"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.AN_ELEMENT_WHICH_NEEDS")
    )
    /** @since 0.18.0 */
    AN_ELEMENT_WHICH_NEEDS("an element which needs"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.AN_ELEMENT_WHICH_EQUALS")
    )
    /** @since 0.18.0 */
    AN_ELEMENT_WHICH_EQUALS("an element which equals"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.AT_LEAST")
    )
    /** @since 0.18.0 */
    AT_LEAST("is at least"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.AT_MOST")
    )
    /** @since 0.18.0 */
    AT_MOST("is at most"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.TO_CONTAIN")
    )
    /** @since 0.18.0 */
    TO_CONTAIN("to contain"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.NOT_TO_CONTAIN")
    )
    /** @since 0.18.0 */
    NOT_TO_CONTAIN("not to contain"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.USE_NOT_TO_HAVE_ELEMENTS_OR_NONE")
    )
    /** @since 1.0.0 */
    USE_NOT_TO_HAVE_ELEMENTS_OR_NONE("use %s if you don't care about the empty case, then the expectation would have been met."),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.ELEMENT_WITH_INDEX")
    )
    /** @since 0.18.0 */
    ELEMENT_WITH_INDEX("element %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.EXACTLY")
    )
    /** @since 0.18.0 */
    EXACTLY("is exactly"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.IN_ANY_ORDER")
    )
    /** @since 0.18.0 */
    IN_ANY_ORDER("%s, in any order"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.IN_ANY_ORDER_ONLY")
    )
    /** @since 0.18.0 */
    IN_ANY_ORDER_ONLY("%s only, in any order"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.IN_ORDER")
    )
    /** @since 0.18.0 */
    IN_ORDER("%, in order"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.IN_ORDER_ONLY")
    )
    /** @since 0.18.0 */
    IN_ORDER_ONLY("%s only, in order"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.IN_ORDER_ONLY_GROUPED")
    )
    /** @since 0.18.0 */
    IN_ORDER_ONLY_GROUPED("%s only, in order, grouped"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.INDEX")
    )
    /** @since 0.18.0 */
    INDEX("index %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.INDEX_FROM_TO")
    )
    /** @since 0.18.0 */
    INDEX_FROM_TO("index %s..%s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.NUMBER_OF_SUCH_ELEMENTS")
    )
    /** @since 0.18.0 */
    NUMBER_OF_SUCH_ELEMENTS("number of such elements"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.SIZE_EXCEEDED")
    )
    /** @since 0.18.0 */
    SIZE_EXCEEDED("❗❗ hasNext() returned false"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.WARNING_ADDITIONAL_ELEMENTS")
    )
    /** @since 0.18.0 */
    WARNING_ADDITIONAL_ELEMENTS("additional elements detected"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.WARNING_MISMATCHES")
    )
    /** @since 0.18.0 */
    WARNING_MISMATCHES("following elements were mismatched"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.WARNING_MISMATCHES_ADDITIONAL_ELEMENTS")
    )
    /** @since 0.18.0 */
    WARNING_MISMATCHES_ADDITIONAL_ELEMENTS("mismatches and additional elements detected"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.A_NEXT_ELEMENT")
    )
    /** @since 0.18.0 */
    A_NEXT_ELEMENT("a next element"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.NO_ELEMENTS")
    )
    /** @since 0.18.0 */
    NO_ELEMENTS("❗❗ cannot be determined, empty IterableLike"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.DUPLICATE_ELEMENTS")
    )
    /** @since 0.18.0 */
    DUPLICATE_ELEMENTS("duplicate elements"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.DUPLICATED_BY")
    )
    /** @since 0.18.0 */
    DUPLICATED_BY("duplicated by index: %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.ELEMENT_NOT_FOUND")
    )
    /** @since 0.18.0 */
    ELEMENT_NOT_FOUND("but no such element was found"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.NUMBER_OF_ELEMENTS_FOUND")
    )
    /** @since 0.18.0 */
    NUMBER_OF_ELEMENTS_FOUND("and %s such elements were found"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.NOT_TO_HAVE_ELEMENTS_OR_ANY")
    )
    /** @since 1.0.0 */
    NOT_TO_HAVE_ELEMENTS_OR_ANY("not to have elements or any of it needs"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.NOT_TO_HAVE_ELEMENTS_OR_ALL")
    )
    /** @since 1.0.0 */
    NOT_TO_HAVE_ELEMENTS_OR_ALL("not to have elements or all need"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.NOT_TO_HAVE_ELEMENTS_OR_NONE")
    )
    /** @since 1.0.0 */
    NOT_TO_HAVE_ELEMENTS_OR_NONE("not to have elements or none"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.NEITHER_EMPTY_NOR_ELEMENT_FOUND")
    )
    /** @since 1.0.0 */
    NEITHER_EMPTY_NOR_ELEMENT_FOUND("but it had next element(s) and none matched the requirements"),
}
