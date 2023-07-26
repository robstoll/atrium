package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class DescriptionIterableLikeExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    ALL_ELEMENTS("für alle Elemente gilt"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    AN_ELEMENT_WHICH_NEEDS("ein Element, welches"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    AN_ELEMENT_WHICH_EQUALS("Element"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
   AT_LEAST("ist zumindest"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    AT_MOST("ist höchstens"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_CONTAIN("enthält"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    NOT_TO_CONTAIN("enthält nicht"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 1.0.0 */
    USE_NOT_TO_HAVE_ELEMENTS_OR_NONE("verwende %s falls du den Fall leer nicht behandeln möchtest, dann würde die Behauptung stimmen."),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    ELEMENT_WITH_INDEX("Element %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    EXACTLY("ist genau"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    IN_ANY_ORDER("%s, in beliebiger Reihenfolge"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    IN_ANY_ORDER_ONLY("%s ausschliesslich, in beliebiger Reihenfolge"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    IN_ORDER("%, in gegebener Reihenfolge"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    IN_ORDER_ONLY("%s ausschliesslich, in gegebener Reihenfolge"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    IN_ORDER_ONLY_GROUPED("%s ausschliesslich, in gegebener Reihenfolge, in Gruppen"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    INDEX("Index %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    INDEX_FROM_TO("Index %s..%s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    NUMBER_OF_SUCH_ELEMENTS("Anzahl Treffer"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    SIZE_EXCEEDED("❗❗ hasNext() hat `false` zurückgegeben"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    WARNING_ADDITIONAL_ELEMENTS("zusätzliche Elemente entdeckt"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    WARNING_MISMATCHES("folgende Elemente erfüllten keine Aussage (Diskrepanzen)"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    WARNING_MISMATCHES_ADDITIONAL_ELEMENTS("Diskrepanzen und zusätzliche Elemente entdeckt"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    A_NEXT_ELEMENT("ein nächstes Element"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    NO_ELEMENTS("❗❗ kann nicht eruiert werden, leeres Iterable"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    DUPLICATE_ELEMENTS("doppelte Elemente"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    DUPLICATED_BY("(dupliziert von Index: %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    ELEMENT_NOT_FOUND("aber es konnte kein solches Element gefunden werden"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    NUMBER_OF_ELEMENTS_FOUND("und % Elemente wurden gefunden"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 1.0.0 */
    NOT_TO_HAVE_ELEMENTS_OR_ANY("hat keine Elemente oder min. eines gilt"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 1.0.0 */
    NOT_TO_HAVE_ELEMENTS_OR_ALL("hat keine Elemente oder füt alle gilt"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 1.0.0 */
    NOT_TO_HAVE_ELEMENTS_OR_NONE("hat keine Elemente oder für keines gilt"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 1.0.0 */
    NEITHER_EMPTY_NOR_ELEMENT_FOUND("aber es hat Elemente und es konnte kein solches Element gefunden werden"),
}
