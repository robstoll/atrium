package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents a translator of [ITranslatable]s.
 *
 * It shall be more or less compatible with [ResourceBundle] in terms of how candidate [Locale]s are determined.
 * So, more or less the same rules apply as described in [ResourceBundle.Control.getCandidateLocales].
 * However, it shall apply an extended fallback mechanism. In case not a single properties file could be found
 * for one of the candidate [Locale]s, then instead of falling back to [Locale.getDefault] (as [ResourceBundle]
 * would do per default), one shall be able to specify fallback [Locale]s oneself. Whether this includes
 * [Locale.getDefault] or not is up to the user of Atrium.
 * Moreover, the fallback even applies if a properties file for one of the candidate [Locale]s is specified but does
 * not contain the property which we are looking for ([ResourceBundle] would throw a [MissingResourceException] in
 * such a case).
 *
 * Following an example. `de_CH` is used as primary Locale and `fr_CH` as fallback Locale.
 * We are looking for the translation of `DescriptionAnyAssertions.TO_BE`. The following files exists:
 *
 * *DescriptionAnyAssertions_de_CH.properties* with NOT_WHAT_WE_ARE_LOOKING_FOR = foo
 * *DescriptionAnyAssertions_fr.properties* with TO_BE = est
 *
 * The resolution would be as follows:
 * - de_CH
 * - de
 * - ROOT
 * - fr_CH
 * - fr => found
 * - ROOT (not processed anymore)
 *
 * Notice, that a [ITranslator] should treat the two special cases Norwegian and Chinese differently than
 * [ResourceBundle] suggests (the actual implementation for Java seems to be buggy anyway).
 *
 * A [ITranslator] should not support [Locale]s with [language][Locale.getLanguage] equal to `no` and
 * should throw an [IllegalArgumentException] instead.
 * A user has to use either `nn` (for Nynorsk) or `nb` (for Bokmål). One can still define the other Locale as
 * fallback, which effectively makes the ambiguous `no` Locale obsolete. As an example, one can define `nn_NO` as
 * primary Locale and `nb_NO` as fallback Locale.
 *
 * Furthermore it should throw an [IllegalArgumentException] in case one has specified `zh` as language, did not
 * define a country but script `Hant` or script `Hans`.
 * A user should use a corresponding country instead and only provide a script in case one wants to be explicit to
 * avoid ambiguity (e.g., zh-Hans_HK for Chinese in simplified script in Hong Kong).
 */
interface ITranslator {
    /**
     * Returns the translation of the given [translatable] or its [getDefault][ITranslatable.getDefault]
     * in case there is not a translation defined for it.
     *
     *  @param translatable The [ITranslatable] which shall be translated.
     *
     * @return The result of the translation for the given [translatable].
     */
    fun translate(translatable: ITranslatable): String
}
