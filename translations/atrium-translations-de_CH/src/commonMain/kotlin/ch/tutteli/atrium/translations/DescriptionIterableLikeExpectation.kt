@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
@Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
enum class DescriptionIterableLikeExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    ALL_ELEMENTS("für alle Elemente gilt"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    AN_ELEMENT_WHICH_NEEDS("ein Element, welches"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    AN_ELEMENT_WHICH_EQUALS("Element"),

    /** @since 0.18.0 */
   @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    AT_LEAST("ist zumindest"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    AT_MOST("ist höchstens"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    TO_CONTAIN("enthält"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NOT_TO_CONTAIN("enthält nicht"),

    /** @since 1.0.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    USE_NOT_TO_HAVE_ELEMENTS_OR_NONE("verwende %s falls du den Fall leer nicht behandeln möchtest, dann würde die Behauptung stimmen."),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    ELEMENT_WITH_INDEX("Element %s"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    EXACTLY("ist genau"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    IN_ANY_ORDER("%s, in beliebiger Reihenfolge"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    IN_ANY_ORDER_ONLY("%s ausschliesslich, in beliebiger Reihenfolge"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    IN_ORDER("%, in gegebener Reihenfolge"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    IN_ORDER_ONLY("%s ausschliesslich, in gegebener Reihenfolge"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    IN_ORDER_ONLY_GROUPED("%s ausschliesslich, in gegebener Reihenfolge, in Gruppen"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    INDEX("Index %s"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    INDEX_FROM_TO("Index %s..%s"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NUMBER_OF_SUCH_ELEMENTS("Anzahl Treffer"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    SIZE_EXCEEDED("❗❗ hasNext() hat `false` zurückgegeben"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    WARNING_ADDITIONAL_ELEMENTS("zusätzliche Elemente entdeckt"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    WARNING_MISMATCHES("folgende Elemente erfüllten keine Aussage (Diskrepanzen)"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    WARNING_MISMATCHES_ADDITIONAL_ELEMENTS("Diskrepanzen und zusätzliche Elemente entdeckt"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    A_NEXT_ELEMENT("ein nächstes Element"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NO_ELEMENTS("❗❗ kann nicht eruiert werden, leeres Iterable"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    DUPLICATE_ELEMENTS("doppelte Elemente"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    DUPLICATED_BY("(dupliziert von Index: %s"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    ELEMENT_NOT_FOUND("aber es konnte kein solches Element gefunden werden"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NUMBER_OF_ELEMENTS_FOUND("und % Elemente wurden gefunden"),

    /** @since 1.0.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NOT_TO_HAVE_ELEMENTS_OR_ANY("hat keine Elemente oder min. eines gilt"),

    /** @since 1.0.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NOT_TO_HAVE_ELEMENTS_OR_ALL("hat keine Elemente oder füt alle gilt"),

    /** @since 1.0.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NOT_TO_HAVE_ELEMENTS_OR_NONE("hat keine Elemente oder für keines gilt"),

    /** @since 1.0.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NEITHER_EMPTY_NOR_ELEMENT_FOUND("aber es hat Elemente und es konnte kein solches Element gefunden werden"),
}
