//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionIterableAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.ALL_ELEMENTS")
    )
    ALL("alle Einträge"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_NEEDS")
    )
    AN_ELEMENT_WHICH("ein Element, welches"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_EQUALS")
    )
    AN_ELEMENT_WHICH_EQUALS("Element"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_NEEDS; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_NEEDS")
    )
    AN_ENTRY_WHICH(AN_ELEMENT_WHICH.getDefault()),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_EQUALS; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_EQUALS")
    )
    AN_ENTRY_WHICH_IS(AN_ELEMENT_WHICH_EQUALS.getDefault()),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AT_LEAST")
    )
    AT_LEAST("ist zumindest"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.AT_MOST")
    )
    AT_MOST("ist höchstens"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.TO_CONTAIN")
    )
    CONTAINS("enthält"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.NOT_TO_CONTAIN")
    )
    CONTAINS_NOT("enthält nicht"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.ELEMENT_WITH_INDEX")
    )
    ELEMENT_WITH_INDEX("Element %s"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation.ELEMENT_WITH_INDEX; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.ELEMENT_WITH_INDEX")
    )
    ENTRY_WITH_INDEX(ELEMENT_WITH_INDEX.getDefault()),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.EXACTLY")
    )
    EXACTLY("ist genau"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.IN_ANY_ORDER")
    )
    IN_ANY_ORDER("%s, in beliebiger Reihenfolge"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.IN_ANY_ORDER_ONLY")
    )
    IN_ANY_ORDER_ONLY("%s ausschliesslich, in beliebiger Reihenfolge"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.IN_ORDER")
    )
    IN_ORDER("%, in gegebener Reihenfolge"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.IN_ORDER_ONLY")
    )
    IN_ORDER_ONLY("%s ausschliesslich, in gegebener Reihenfolge"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.IN_ORDER_ONLY_GROUPED")
    )
    IN_ORDER_ONLY_GROUPED("%s ausschliesslich, in gegebener Reihenfolge, in Gruppen"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.INDEX")
    )
    INDEX("Index %s"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.INDEX_FROM_TO")
    )
    INDEX_FROM_TO("Index %s..%s"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.NUMBER_OF_SUCH_ELEMENTS")
    )
    NUMBER_OF_OCCURRENCES("Anzahl Treffer"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.SIZE_EXCEEDED")
    )
    SIZE_EXCEEDED("❗❗ hasNext() hat `false` zurückgegeben"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.WARNING_ADDITIONAL_ELEMENTS")
    )
    WARNING_ADDITIONAL_ELEMENTS("zusätzliche Elemente entdeckt"),

    @Deprecated(
        "Use WARNING_ADDITIONAL_ELEMENTS; will be removed with 1.0.0 at the latest",
        ReplaceWith("WARNING_ADDITIONAL_ELEMENTS")
    )
    WARNING_ADDITIONAL_ENTRIES(WARNING_ADDITIONAL_ELEMENTS.getDefault()),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.WARNING_MISMATCHES")
    )
    WARNING_MISMATCHES("folgende Elemente erfüllten keine Aussage (Diskrepanzen)"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.WARNING_MISMATCHES_ADDITIONAL_ELEMENTS")
    )
    WARNING_MISMATCHES_ADDITIONAL_ELEMENTS("Diskrepanzen und zusätzliche Elemente entdeckt"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation.WARNING_MISMATCHES_ADDITIONAL_ELEMENTS; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.WARNING_MISMATCHES_ADDITIONAL_ELEMENTS")
    )
    WARNING_MISMATCHES_ADDITIONAL_ENTRIES(WARNING_MISMATCHES_ADDITIONAL_ELEMENTS.getDefault()),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.A_NEXT_ELEMENT")
    )
    NEXT_ELEMENT("ein nächstes Element"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.NO_ELEMENTS")
    )
    NO_ELEMENTS("❗❗ kann nicht eruiert werden, leeres Iterable"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.DUPLICATE_ELEMENTS")
    )
    DUPLICATE_ELEMENTS("doppelte Elemente"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.DUPLICATED_BY")
    )
    DUPLICATED_BY("(dupliziert von Index: %s"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.ELEMENT_NOT_FOUND")
    )
    ELEMENT_NOT_FOUND("aber es konnte kein solches Element gefunden werden"),

    @Deprecated(
        "Use DescriptionIterableLikeExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionIterableLikeExpectation.NUMBER_OF_ELEMENTS_FOUND")
    )
    NUMBER_OF_ELEMENTS_FOUND("und % Elemente wurden gefunden")
}
