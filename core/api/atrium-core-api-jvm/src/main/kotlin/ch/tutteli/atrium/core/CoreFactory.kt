@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.core

import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import java.util.*

actual interface CoreFactory : CoreFactoryCommon {

    /**
     * Creates a [TranslationSupplier] which is based on properties and is compatible with [ResourceBundle] concerning
     * the structure of the properties files.
     *
     * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
     * stored in a properties file named `DescriptionAnyAssertion_de_CH.properties` in the folder `/ch/tutteli/atrium/`.
     * Moreover the files need to be encoded in ISO-8859-1 (restriction to be compatible with [ResourceBundle]).
     *
     * An entry in such a file would look like as follows:
     * `TO_BE = a translation for TO_BE`
     *
     * @return The newly created translation supplier.
     */
    @Deprecated("Open an issue in case you used this; Will be removed with 0.17.0")
    fun newPropertiesBasedTranslationSupplier(): TranslationSupplier

    @Deprecated(
        "Use the overload which expects an AtriumErrorAdjuster in addition; will be removed latest with 1.0.0",
        ReplaceWith("this.newOnlyFailureReporter(assertionFormatterFacade, this.newNoOpAtriumErrorAdjuster())")
    )
    fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
}
