package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.subjectLessKotestBasedSpec
import io.kotest.core.spec.style.FunSpec

class DescriptiveWithBasedOnSubjectSpec : FunSpec({

    fun addDescriptive(f: (Expect<Int>, Descriptive.HoldsOption) -> Assertion) = expectLambda<Int> {
        _logic.append(f(this, assertionBuilder.descriptive))
    }

    include(subjectLessKotestBasedSpec("",
        "withTest" to addDescriptive { expect, builder ->
            builder.withTest(expect) { it < 3 }
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        },
        "withHelpOnFailureBasedOnDefinedSubject" to addDescriptive { expect, builder ->
            builder.failing
                .withHelpOnFailureBasedOnDefinedSubject(expect) {
                    assertionBuilder.explanatory.withExplanation("asdf").build()
                }
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        },
        "withHelpOnFailureBasedOnSubject" to addDescriptive { expect, builder ->
            builder.failing
                .withHelpOnFailureBasedOnSubject(expect) {
                    ifDefined {
                        assertionBuilder.explanatory.withExplanation("asdf").build()
                    } ifAbsent {
                        assertionBuilder.explanatory.withExplanation("asdf").build()
                    }
                }
                .showForAnyFailure
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        },
        "showOnlyIf" to addDescriptive { expect, builder ->
            builder.failing
                .withHelpOnFailure { assertionBuilder.explanatory.withExplanation("any hint").build() }
                .showBasedOnSubjectOnlyIf(expect) {
                    ifDefined {
                        it < 3
                    } ifAbsent falseProvider
                }
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        },
        "showOnlyIfSubjectDefined" to addDescriptive { expect, builder ->
            builder.failing
                .withHelpOnFailure { assertionBuilder.explanatory.withExplanation("any hint").build() }
                .showOnlyIfSubjectDefined(expect)
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        },
        "showBasedOnDefinedSubjectOnlyIf" to addDescriptive { expect, builder ->
            builder.failing
                .withHelpOnFailure { assertionBuilder.explanatory.withExplanation("any hint").build() }
                .showBasedOnDefinedSubjectOnlyIf(expect) { it < 3 }
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        }
    ))
})
