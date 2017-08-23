package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

object CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<CharSequence>::enthaeltStandardUebersetzungVon.name to IAssertionPlant<CharSequence>::enthaeltStandardUebersetzungVon,
    IAssertionPlant<CharSequence>::enthaeltNichtDieStandardUebersetzungVon.name to IAssertionPlant<CharSequence>::enthaeltNichtDieStandardUebersetzungVon,
    IAssertionPlant<CharSequence>::istLeer.name to IAssertionPlant<CharSequence>::istLeer,
    IAssertionPlant<CharSequence>::istNichtLeer.name to IAssertionPlant<CharSequence>::istNichtLeer,
    IAssertionPlant<CharSequence>::beginntMit.name to IAssertionPlant<CharSequence>::beginntMit,
    IAssertionPlant<CharSequence>::beginntNichtMit.name to IAssertionPlant<CharSequence>::beginntNichtMit,
    IAssertionPlant<CharSequence>::endetMit.name to IAssertionPlant<CharSequence>::endetMit,
    IAssertionPlant<CharSequence>::endetNichtMit.name to IAssertionPlant<CharSequence>::endetNichtMit
)
