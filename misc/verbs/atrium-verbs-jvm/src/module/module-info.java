module ch.tutteli.atrium.verbs {
    requires ch.tutteli.atrium.domain.builders;
    requires kotlin.stdlib;

    uses ch.tutteli.atrium.reporting.ReporterFactory;

    exports ch.tutteli.atrium.api.verbs;
    exports ch.tutteli.atrium.verbs;
}
