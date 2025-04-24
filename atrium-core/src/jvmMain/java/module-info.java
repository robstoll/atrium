module ch.tutteli.atrium.core.api {
    requires    ch.tutteli.kbox;
    requires    org.opentest4j;
    requires    kotlin.stdlib;
    requires    kotlin.reflect;

    exports ch.tutteli.atrium;
    exports ch.tutteli.atrium.assertions;
    exports ch.tutteli.atrium.assertions.builders;
    exports ch.tutteli.atrium.assertions.builders.common;
    exports ch.tutteli.atrium.core;
    exports ch.tutteli.atrium.core.migration;
    exports ch.tutteli.atrium.core.polyfills;
    exports ch.tutteli.atrium.creating;
    exports ch.tutteli.atrium.creating.feature;

    exports ch.tutteli.atrium.creating.proofs;
    exports ch.tutteli.atrium.creating.proofs.basic.contains;
    exports ch.tutteli.atrium.creating.proofs.charsequence.contains;
    exports ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers;
    exports ch.tutteli.atrium.creating.proofs.charsequence.contains.creators;
    exports ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours;
    exports ch.tutteli.atrium.creating.proofs.charsequence.contains.steps;
    exports ch.tutteli.atrium.creating.proofs.builders;

    exports ch.tutteli.atrium.creating.transformers;
    exports ch.tutteli.atrium.creating.typeutils;
    exports ch.tutteli.atrium.creating.utils;
    exports ch.tutteli.atrium.reporting;
    exports ch.tutteli.atrium.reporting.erroradjusters;
    exports ch.tutteli.atrium.reporting.reportables;
    exports ch.tutteli.atrium.reporting.reportables.descriptions;
    exports ch.tutteli.atrium.reporting.prerendering.text;
    exports ch.tutteli.atrium.reporting.text;
    exports ch.tutteli.atrium.reporting.theming.text;
    exports ch.tutteli.atrium.reporting.translating;

    exports ch.tutteli.atrium.reporting.theming.text.impl to ch.tutteli.atrium.verbs.internal;
}
