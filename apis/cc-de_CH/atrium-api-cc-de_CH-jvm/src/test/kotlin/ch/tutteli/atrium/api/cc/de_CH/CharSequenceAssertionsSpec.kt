package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
    (Assert<CharSequence>::enthaeltStandardUebersetzungVon.name to Assert<CharSequence>::enthaeltStandardUebersetzungVon),
    @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
    (Assert<CharSequence>::enthaeltNichtDieStandardUebersetzungVon.name to Assert<CharSequence>::enthaeltNichtDieStandardUebersetzungVon),
    Assert<CharSequence>::istLeer.name to Assert<CharSequence>::istLeer,
    Assert<CharSequence>::istNichtLeer.name to Assert<CharSequence>::istNichtLeer,
    Assert<CharSequence>::istNichtBlank.name to Assert<CharSequence>::istNichtBlank,
    Assert<CharSequence>::beginntMit.name to Assert<CharSequence>::beginntMit,
    Assert<CharSequence>::beginntNichtMit.name to Assert<CharSequence>::beginntNichtMit,
    Assert<CharSequence>::endetMit.name to Assert<CharSequence>::endetMit,
    Assert<CharSequence>::endetNichtMit.name to Assert<CharSequence>::endetNichtMit,
    "* ", "- "
)
