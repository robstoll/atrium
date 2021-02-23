package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.reporting.translating.impl.ResourceBundleInspiredLocaleOrderDecider

class ResourceBundleInspiredLocaleOrderDeciderSpec :
    ch.tutteli.atrium.specs.reporting.translating.LocaleOrderDeciderSpec(
        { ResourceBundleInspiredLocaleOrderDecider }
    )
