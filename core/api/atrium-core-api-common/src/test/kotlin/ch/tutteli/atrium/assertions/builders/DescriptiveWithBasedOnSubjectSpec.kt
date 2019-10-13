package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.specs.SubjectLessSpec
import ch.tutteli.atrium.specs.expectLambda
import org.spekframework.spek2.Spek

class DescriptiveWithBasedOnSubjectSpec : Spek({

    fun addDescriptive(f: (Expect<Int>, Descriptive.HoldsOption) -> Assertion) = expectLambda<Int> {
        addAssertion(f(this, ExpectImpl.builder.descriptive))
    }

    include(object : SubjectLessSpec<Int>("",
        "withTest" to addDescriptive { expect, builder ->
            builder.withTest(expect) { it < 3 }
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        },
        "withFailureHintBasedOnDefinedSubject" to addDescriptive { expect, builder ->
            builder.failing
                .withFailureHintBasedOnDefinedSubject(expect) {
                    ExpectImpl.builder.explanatory.withExplanation("asdf").build()
                }
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        },
        "withFailureHintBasedOnSubject" to addDescriptive { expect, builder ->
            builder.failing
                .withFailureHintBasedOnSubject(expect) {
                    ifDefined {
                        ExpectImpl.builder.explanatory.withExplanation("asdf").build()
                    } ifAbsent {
                        ExpectImpl.builder.explanatory.withExplanation("asdf").build()
                    }
                }
                .showForAnyFailure
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        },
        "showOnlyIf" to addDescriptive { expect, builder ->
            builder.failing
                .withFailureHint { ExpectImpl.builder.explanatory.withExplanation("any hint").build() }
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
                .withFailureHint { ExpectImpl.builder.explanatory.withExplanation("any hint").build() }
                .showOnlyIfSubjectDefined(expect)
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        },
        "showBasedOnDefinedSubjectOnlyIf" to addDescriptive { expect, builder ->
            builder.failing
                .withFailureHint { ExpectImpl.builder.explanatory.withExplanation("any hint").build() }
                .showBasedOnDefinedSubjectOnlyIf(expect) { it < 3 }
                .withDescriptionAndRepresentation("what ever", 1)
                .build()
        }
    ) {})
})
