package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.Descriptive
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.specs.SubjectLessSpec
import ch.tutteli.atrium.specs.expectLambda
import ch.tutteli.atrium.specs.include
import org.spekframework.spek2.Spek

class DescriptiveWithBasedOnSubjectSpec : Spek({

    fun addDescriptive(f: (Expect<Int>, Descriptive.HoldsOption) -> Assertion) = expectLambda<Int> {
        addAssertion(f(this, ExpectImpl.builder.descriptive))
    }

    include(object : SubjectLessSpec<Int>("",
        "withTest" to addDescriptive { expect, builder ->
            builder.withTest(expect) { it < 3 }
                .withDescriptionAndRepresentation(Untranslatable("what ever"), 1)
                .build()
        },
        "withFailureHintBasedOnDefinedSubject" to addDescriptive { expect, builder ->
            builder.failing
                .withFailureHintBasedOnDefinedSubject(expect) {
                        ExpectImpl.builder.explanatory.withDescription("asdf").build()
                }
                .withDescriptionAndRepresentation(Untranslatable("what ever"), 1)
                .build()
        },
        "withFailureHintBasedOnSubject" to addDescriptive { expect, builder ->
            builder.failing
                .withFailureHintBasedOnSubject(expect) {
                    ifDefined {
                        ExpectImpl.builder.explanatory.withDescription("asdf").build()
                    } ifAbsent {
                        ExpectImpl.builder.explanatory.withDescription("asdf").build()
                    }
                }
                .showForAnyFailure
                .withDescriptionAndRepresentation(Untranslatable("what ever"), 1)
                .build()
        },
        "showOnlyIf" to addDescriptive { expect, builder ->
            builder.failing
                .withFailureHint { ExpectImpl.builder.explanatory.withDescription("any hint").build() }
                .showBasedOnSubjectOnlyIf(expect) {
                    ifDefined {
                        it < 3
                    } ifAbsent falseProvider
                }
                .withDescriptionAndRepresentation(Untranslatable("what ever"), 1)
                .build()
        },
        "showOnlyIfSubjectDefined" to addDescriptive { expect, builder ->
            builder.failing
                .withFailureHint { ExpectImpl.builder.explanatory.withDescription("any hint").build() }
                .showOnlyIfSubjectDefined(expect)
                .withDescriptionAndRepresentation(Untranslatable("what ever"), 1)
                .build()
        }
    ) {})
})
