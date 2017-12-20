package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

object CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<CharSequence>::enthaeltStandardUebersetzungVon.name to AssertionPlant<CharSequence>::enthaeltStandardUebersetzungVon,
    AssertionPlant<CharSequence>::enthaeltNichtDieStandardUebersetzungVon.name to AssertionPlant<CharSequence>::enthaeltNichtDieStandardUebersetzungVon,
    AssertionPlant<CharSequence>::istLeer.name to AssertionPlant<CharSequence>::istLeer,
    AssertionPlant<CharSequence>::istNichtLeer.name to AssertionPlant<CharSequence>::istNichtLeer,
    AssertionPlant<CharSequence>::beginntMit.name to AssertionPlant<CharSequence>::beginntMit,
    AssertionPlant<CharSequence>::beginntNichtMit.name to AssertionPlant<CharSequence>::beginntNichtMit,
    AssertionPlant<CharSequence>::endetMit.name to AssertionPlant<CharSequence>::endetMit,
    AssertionPlant<CharSequence>::endetNichtMit.name to AssertionPlant<CharSequence>::endetNichtMit
)
