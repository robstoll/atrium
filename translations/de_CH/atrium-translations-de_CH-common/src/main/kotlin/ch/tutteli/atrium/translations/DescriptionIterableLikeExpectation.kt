package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
enum class DescriptionIterableLikeExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    ALL_ELEMENTS("alle Einträge"),

    /** @since 0.18.0 */
    AN_ELEMENT_WHICH_NEEDS("ein Element, welches"),

    /** @since 0.18.0 */
    AN_ELEMENT_WHICH_EQUALS("Element"),

    /** @since 0.18.0 */
   AT_LEAST("ist zumindest"),

    /** @since 0.18.0 */
    AT_MOST("ist höchstens"),

    /** @since 0.18.0 */
    TO_CONTAIN("enthält"),

    /** @since 0.18.0 */
    NOT_TO_CONTAIN("enthält nicht"),

    /** @since 0.18.0 */
    ELEMENT_WITH_INDEX("Element %s"),

    /** @since 0.18.0 */
    EXACTLY("ist genau"),

    /** @since 0.18.0 */
    IN_ANY_ORDER("%s, in beliebiger Reihenfolge"),

    /** @since 0.18.0 */
    IN_ANY_ORDER_ONLY("%s ausschliesslich, in beliebiger Reihenfolge"),

    /** @since 0.18.0 */
    IN_ORDER("%, in gegebener Reihenfolge"),

    /** @since 0.18.0 */
    IN_ORDER_ONLY("%s ausschliesslich, in gegebener Reihenfolge"),

    /** @since 0.18.0 */
    IN_ORDER_ONLY_GROUPED("%s ausschliesslich, in gegebener Reihenfolge, in Gruppen"),

    /** @since 0.18.0 */
    INDEX("Index %s"),

    /** @since 0.18.0 */
    INDEX_FROM_TO("Index %s..%s"),

    /** @since 0.18.0 */
    NUMBER_OF_SUCH_ELEMENTS("Anzahl Treffer"),

    /** @since 0.18.0 */
    SIZE_EXCEEDED("❗❗ hasNext() hat `false` zurückgegeben"),

    /** @since 0.18.0 */
    WARNING_ADDITIONAL_ELEMENTS("zusätzliche Elemente entdeckt"),

    /** @since 0.18.0 */
    WARNING_MISMATCHES("folgende Elemente erfüllten keine Aussage (Diskrepanzen)"),

    /** @since 0.18.0 */
    WARNING_MISMATCHES_ADDITIONAL_ELEMENTS("Diskrepanzen und zusätzliche Elemente entdeckt"),

    /** @since 0.18.0 */
    A_NEXT_ELEMENT("ein nächstes Element"),

    /** @since 0.18.0 */
    NO_ELEMENTS("❗❗ kann nicht eruiert werden, leeres Iterable"),

    /** @since 0.18.0 */
    DUPLICATE_ELEMENTS("doppelte Elemente"),

    /** @since 0.18.0 */
    DUPLICATED_BY("(dupliziert von Index: %s"),

    /** @since 0.18.0 */
    ELEMENT_NOT_FOUND("aber es konnte kein solches Element gefunden werden"),

    /** @since 0.18.0 */
    NUMBER_OF_ELEMENTS_FOUND("und % Elemente wurden gefunden")
}
