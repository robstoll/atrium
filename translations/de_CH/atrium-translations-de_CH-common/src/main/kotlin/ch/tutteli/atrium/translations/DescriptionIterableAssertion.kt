package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
enum class DescriptionIterableAssertion(override val value: String) : StringBasedTranslatable {
    ALL("alle Einträge"),
    AN_ELEMENT_WHICH("ein Element, welches"),
    AN_ELEMENT_WHICH_EQUALS("Element"),
    @Deprecated("Use AN_ELEMENT_WHICH; will be removed with 1.0.0 at the latest", ReplaceWith("AN_ELEMENT_WHICH"))
    AN_ENTRY_WHICH(AN_ELEMENT_WHICH.getDefault()),
    @Deprecated("Use AN_ELEMENT_WHICH_EQUALS; will be removed with 1.0.0 at the latest", ReplaceWith("AN_ELEMENT_WHICH_EQUALS"))
    AN_ENTRY_WHICH_IS(AN_ELEMENT_WHICH_EQUALS.getDefault()),
    AT_LEAST("ist zumindest"),
    AT_MOST("ist höchstens"),
    CONTAINS("enthält"),
    CONTAINS_NOT("enthält nicht"),
    ELEMENT_WITH_INDEX("Element %s"),
    @Deprecated("Use ELEMENT_WITH_INDEX; will be removed with 1.0.0 at the latest", ReplaceWith("ELEMENT_WITH_INDEX"))
    ENTRY_WITH_INDEX(ELEMENT_WITH_INDEX.getDefault()),
    EXACTLY("ist genau"),
    HAS_ELEMENT("hat mindestens ein Element"),
    IN_ANY_ORDER("%s, in beliebiger Reihenfolge"),
    IN_ANY_ORDER_ONLY("%s ausschliesslich, in beliebiger Reihenfolge"),
    IN_ORDER("%, in gegebener Reihenfolge"),
    IN_ORDER_ONLY("%s ausschliesslich, in gegebener Reihenfolge"),
    IN_ORDER_ONLY_GROUPED("%s ausschliesslich, in gegebener Reihenfolge, in Gruppen"),
    INDEX("Index %s"),
    INDEX_FROM_TO("Index %s..%s"),
    NUMBER_OF_OCCURRENCES("Anzahl Treffer"),
    SIZE_EXCEEDED("❗❗ hasNext() hat `false` zurückgegeben"),
    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- `Iterable` gibt keinen nächsten Eintrag zurück.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    CANNOT_EVALUATE_SUBJECT_ONLY_NULL("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- `Iterable` gibt nur `null` zurück.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    WARNING_ADDITIONAL_ELEMENTS("zusätzliche Elemente entdeckt"),
    @Deprecated("Use WARNING_ADDITIONAL_ELEMENTS; will be removed with 1.0.0 at the latest", ReplaceWith("WARNING_ADDITIONAL_ELEMENTS"))
    WARNING_ADDITIONAL_ENTRIES(WARNING_ADDITIONAL_ELEMENTS.getDefault()),
    WARNING_MISMATCHES("folgende Elemente erfüllten keine Aussage (Diskrepanzen)"),
    WARNING_MISMATCHES_ADDITIONAL_ELEMENTS("Diskrepanzen und zusätzliche Elemente entdeckt"),
    @Deprecated("Use WARNING_MISMATCHES_ADDITIONAL_ELEMENTS; will be removed with 1.0.0 at the latest", ReplaceWith("WARNING_MISMATCHES_ADDITIONAL_ELEMENTS"))
    WARNING_MISMATCHES_ADDITIONAL_ENTRIES(WARNING_MISMATCHES_ADDITIONAL_ELEMENTS.getDefault()),
    NEXT_ELEMENT("ein nächstes Element"),
    NO_ELEMENTS("❗❗ kann nicht eruiert werden, leeres Iterable"),
    DUPLICATE_ELEMENTS("doppelte Elemente"),
    DUPLICATED_BY("(dupliziert von Index: %s"),
    ENTRY_NOT_FOUND("aber es konnte kein solches Element gefunden werden"),
    VALUE_NOT_FOUND("aber es konnte kein solches Element gefunden werden")
}

internal const val COULD_NOT_EVALUATE_DEFINED_ASSERTIONS =
    "Konnte die zusätzlichen Aussagen (Assertions) nicht auswerten"
internal const val VISIT_COULD_NOT_EVALUATE_ASSERTIONS =
    "Besuchen Sie die folgende Website für weiterführende Informationen (in Englisch): https://docs.atriumlib.org/could-not-evaluate-assertions"
