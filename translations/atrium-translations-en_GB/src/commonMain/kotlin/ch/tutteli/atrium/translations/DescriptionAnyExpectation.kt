// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the expectation functions which are applicable to [Any].
 */
@Deprecated(
    "Switch to DescriptionAnyProof, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.descriptions.DescriptionAnyProof")
)
enum class DescriptionAnyExpectation(override val value: String) : StringBasedTranslatable {

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.descriptions.DescriptionAnyProof")
    )
    /** @since 0.18.0 */
    TO_EQUAL("to equal"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.descriptions.DescriptionAnyProof.NOT_TO_EQUAL")
    )
    /** @since 0.18.0 */
    NOT_TO_EQUAL("not to equal"),

    //TODO 1.3.0 deprecate and define replacement
    /** @since 1.1.0 */
    NOT_TO_EQUAL_NULL_TO_BE_AN_INSTANCE_OF("not to equal: null but to be an instance of"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.descriptions.DescriptionAnyProof.TO_BE_AN_INSTANCE_OF")
    )
    /** @since 0.18.0 */
    TO_BE_AN_INSTANCE_OF("to be an instance of type"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.descriptions.DescriptionAnyProof.TO_BE_THE_INSTANCE")
    )
    /** @since 0.18.0 */
    TO_BE_THE_INSTANCE("to be the instance"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.descriptions.DescriptionAnyProof.NOT_TO_BE_AN_INSTANCE_OF")
    )
    /** @since 1.1.0 */
    NOT_TO_BE_AN_INSTANCE_OF("not to be an instance of"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.descriptions.DescriptionAnyProof.NOT_TO_BE_THE_INSTANCE")
    )
    /** @since 0.18.0 */
    NOT_TO_BE_THE_INSTANCE("not to be the instance"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.descriptions.DescriptionAnyProof.NOT_TO_EQUAL_ONE_OF")
    )
    /** @since 0.18.0 */
    NOT_TO_EQUAL_ONE_IN("not to equal one in"),

    //TODO 1.3.0 deprecate and define replacement
    /** @since 0.18.0 */
    BECAUSE("because"),
}
