package ch.tutteli.atrium.reporting.translating


/**
 * Represents a translator of [Translatable]s.
 *
 * It shall be more or less compatible with [ResourceBundle](https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html)
 * in terms of how candidate [Locale]s are determined.
 * So, more or less the same rules apply as described in [ResourceBundle.Control.getCandidateLocales](https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.Control.html#getCandidateLocales-java.lang.String-java.util.Locale-).
 * However, it shall apply an extended fallback mechanism. In case not a single properties file could be found
 * for one of the candidate [Locale]s, then instead of falling back to `java.util.Locale.getDefault`
 * (as `ResourceBundle` would do per default), one shall be able to specify fallback [Locale]s oneself --
 * whether this includes `java.util.Locale.getDefault`or not  on a JVM platform is up to the user of Atrium.
 * Moreover, the fallback even applies if a properties file for one of the candidate [Locale]s is specified but does
 * not contain the property which we are looking for (`ResourceBundle` would throw a [MissingResourceException](https://docs.oracle.com/javase/8/docs/api/java/util/MissingResourceException.html) in
 * such a case).
 * Last but not least, Atrium's [Locale] does not have an equivalent of java.util.Locale.ROOT`;
 * a user can define fallback locales on its own, so there is no need for `ROOT`.
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
 * - fr_CH
 * - fr => found
 *
 * Notice, that a [Translator] should treat the two special cases Norwegian and Chinese differently than
 * `ResourceBundle` suggests (the actual implementation for Java is buggy anyway).
 *
 * A [Translator] should not support [Locale]s with [Locale.language] equal to `no` and
 * should throw an [IllegalArgumentException] instead.
 * A user has to use either `nn` (for Nynorsk) or `nb` (for Bokmål). One can still define the other Locale as
 * fallback if needed (which effectively makes the ambiguous `no` Locale obsolete). As an example, one can define
 * `nn_NO` as primary Locale and `nb_NO` as fallback Locale.
 *
 * Furthermore it should throw an [IllegalArgumentException] in case one has specified `zh` as language, script `Hant`
 * or script `Hans` but did not define a country.
 * A user has to be explicit concerning the country. One is allowed to leave out the script but can also add it to be
 * explicit and avoid confusion (e.g., zh-Hans_HK for Chinese in simplified script in Hong Kong).
 */
interface Translator {

    /**
     * Returns the translation of the given [translatable] or its [getDefault][Translatable.getDefault]
     * in case there is not a translation defined for it.
     *
     * @param translatable The [Translatable] which shall be translated.
     *
     * @return The result of the translation for the given [translatable].
     */
    fun translate(translatable: Translatable): String
}
