package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.creating.proofs.builders.buildSimpleProof
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof

//
//internal fun <E : Any> allCreatedAssertionsHold(
//    container: AssertionContainer<*>,
//    subject: E?,
//    assertionCreator: (Expect<E>.() -> Unit)?
//): Boolean = when (subject) {
//    null -> assertionCreator == null
//    else -> assertionCreator != null && container.collectBasedOnSubject(Some(subject), assertionCreator).holds()
//}
//
//internal fun <E : Any> createExplanatoryAssertionGroup(
//    container: AssertionContainer<*>,
//    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
//    maybeSubject: Option<E> = None
//): AssertionGroup = assertionBuilder
//    .explanatoryGroup
//    .withDefaultType
//    .let {
//        // we don't use toEqualNullIfNullGivenElse because we want to report an empty assertionCreatorOrNull and
//        // since we use None as subject and are already inside an explanatory assertion group, it would not be reported
//        if (assertionCreatorOrNull != null) {
//            it.collectAssertions(container, maybeSubject, assertionCreatorOrNull)
//        } else {
//            it.withAssertion(
//                // it is for an proofExplanation where it does not matter if the assertion holds or not
//                // thus it is OK to use trueProvider
//                assertionBuilder.createDescriptive(TO_EQUAL, Text.NULL, trueProvider)
//            )
//        }
//    }
//    .build()
//
////TODO 1.3.0 replace with Representable and remove suppression
//@Suppress("DEPRECATION")
//internal fun <E> createIndexAssertions(
//    list: List<E>,
//    predicate: (IndexedValue<E>) -> Boolean
//) = list
//    .asSequence()
//    .withIndex()
//    .filter { predicate(it) }
//    .map { (index, element) ->
//        assertionBuilder.createDescriptive(
//            ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(
//                DescriptionIterableLikeExpectation.INDEX,
//                index
//            ),
//            element,
//            falseProvider
//        )
//    }
//    .toList()


internal fun ProofContainer<*>.createFailureExplanationForMismatches(
    mismatches: List<Proof>
): Proof = buildProof {
    failureExplanationGroup(DescriptionIterableLikeProof.WARNING_MISMATCHES) {
        addAll(mismatches)
    }
}

//TODO 1.3.0 that's a horrible name
internal fun ProofContainer<*>.createProofGroupFromReportables(
    description: Description,
    representation: Any?,
    reportables: List<Reportable>
): Proof =
    if (reportables.isEmpty()) {
        //TODO 1.3.0 document in which case they are empty and why then true and not false
        buildSimpleProof(description, representation) { true }
    } else buildProof {
        proofGroup(description, representation) {
            addAll(reportables)
        }
    }
//
//internal fun <E> decorateAssertionWithHasNext(
//    assertion: AssertionGroup,
//    listAssertionContainer: AssertionContainer<List<E>>
//): AssertionGroup {
//    val hasNext = listAssertionContainer.hasNext(::identity)
//    return if (hasNext.holds()) {
//        assertion
//    } else {
//        assertionBuilder.invisibleGroup
//            .withAssertions(
//                hasNext,
//                assertionBuilder.explanatoryGroup
//                    .withDefaultType
//                    .withAssertion(assertion)
//                    .build()
//            )
//            .build()
//    }
//}

//
////TODO 1.3.0 replace with Representable and remove suppression
//@Suppress("DEPRECATION")
//internal fun <E> decorateWithHintUseNotToHaveElementsOrNone(
//    assertion: AssertionGroup,
//    listAssertionContainer: AssertionContainer<List<E>>,
//    notToHaveNextOrNoneFunName: String
//): AssertionGroup {
//    val hasNext = listAssertionContainer.hasNext(::identity)
//    return if (!hasNext.holds()) {
//        assertionBuilder.invisibleGroup
//            .withAssertions(
//                assertion,
//                assertionBuilder.explanatoryGroup
//                    .withHintType
//                    .withExplanatoryAssertion(
//                        ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(
//                            DescriptionIterableLikeExpectation.USE_NOT_TO_HAVE_ELEMENTS_OR_NONE,
//                            notToHaveNextOrNoneFunName
//                        )
//                    )
//                    .build()
//            )
//            .build()
//    } else {
//        assertion
//    }
//}
