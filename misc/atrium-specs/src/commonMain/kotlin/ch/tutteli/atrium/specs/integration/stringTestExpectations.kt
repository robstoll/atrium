package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof.TO_EQUAL
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof


fun Expect<String>.toContainSubject(subject: String) =
    toContainDescr("I expected subject", subject)

fun Expect<String>.toContainToEqualDescr(representation: String) = toContainDescr(TO_EQUAL, representation)
fun Expect<String>.toContainToBeGreaterDescr(representation: String) =
    toContainDescr(DescriptionComparableProof.TO_BE_GREATER_THAN, representation)

fun Expect<String>.toContainToBeLessThanDescr(representation: String) =
    toContainDescr(DescriptionComparableProof.TO_BE_LESS_THAN, representation)


fun Expect<String>.toContainDescr(description: Description, representation: String) =
    toContainDescr(description.string, representation)

fun Expect<String>.toContainDescr(description: String, representation: String) =
    toContain.exactly(1).matchFor(Regex("\\Q$description\\E\\s+: \\Q$representation\\E"))

