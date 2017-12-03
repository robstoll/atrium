package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which postulate that a
 * [IAssertionPlant.subject] of type `T` can be narrowed to `TSub` where `TSub <: T`.
 */
enum class DescriptionNarrowingAssertion(override val value: String) : ISimpleTranslatable {
    IS_A("ist der Typ oder ein Subtyp von"),
    WARNING_DOWN_CAST_FAILED("Konnte die zusätzlichen Aussagen (Assertions) nicht auswerten -- down-cast zu %s schlug fehl."),
}
