package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.matchFor
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.value
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof.TO_EQUAL
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBasic
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof
import ch.tutteli.atrium.specs.expectationVerb
import ch.tutteli.atrium.specs.x


fun Expect<String>.toContainSubject(subject: Any?) =
    toContainDescr(expectationVerb, subject)

fun Expect<String>.toContainToBeDescr(description: Description) = toContainToBeDescr(description.string)
fun Expect<String>.toContainNotToBeDescr(description: Description) = toContainNotToBeDescr(description.string)

fun Expect<String>.toContainToEqualDescr(representation: Any?, numOfMatches: Int = 1) = toContainDescr(TO_EQUAL, representation, numOfMatches)
fun Expect<String>.toContainToBeDescr(representation: Any?) = toContainDescr(DescriptionBasic.TO_BE, representation)
fun Expect<String>.toContainNotToBeDescr(representation: Any?) = toContainDescr(DescriptionBasic.NOT_TO_BE, representation)


fun Expect<String>.toContainToBeGreaterThanDescr(representation: Any?) =
    toContainDescr(DescriptionComparableProof.TO_BE_GREATER_THAN, representation)

fun Expect<String>.toContainToBeLessThanDescr(representation: Any?) =
    toContainDescr(DescriptionComparableProof.TO_BE_LESS_THAN, representation)


fun Expect<String>.toContainDescr(description: Description, representation: Any?, numOfMatches: Int = 1) =
    toContainDescr(description.string, representation, numOfMatches)

fun Expect<String>.toContainDescr(description: String, representation: Any?, numOfMatches: Int = 1) =
    toContain.exactly(numOfMatches).matchFor(Regex("\\Q$description\\E\\s+: \\Q$representation\\E"))

fun Expect<String>.toContainFailure(representation: Any?, numOfMatches: Int = 1) =
    toContain.exactly(numOfMatches).value("${x}$representation")
