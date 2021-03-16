module ch.tutteli.atrium.verbs.internal {
    requires ch.tutteli.atrium.domain.builders;
    requires kotlin.stdlib;

    //TODO 0.17.0 remove
    uses ch.tutteli.atrium.reporting.ReporterFactory;

    exports ch.tutteli.atrium.api.verbs.internal;
}
