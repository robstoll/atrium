@file:JvmMultifileClass
@file:JvmName("CharSequenceContainsCheckersKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder as DeprecatedAtLeastCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsAtMostCheckerBuilder as DeprecatedAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsButAtMostCheckerBuilder as DeprecatedButAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsExactlyCheckerBuilder as DeprecatedExactlyCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsNotOrAtMostCheckerBuilder as DeprecatedNotOrAtMostCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains.SearchBehaviour as DeprecatedSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder as DeprecatedBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour as DeprecatedNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour as DeprecatedNotSearchBehaviour

@Deprecated("Use the extension fun `zumindest`. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.zumindest(times)"))
fun <T : CharSequence, S : SearchBehaviour> zumindest(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedAtLeastCheckerBuilder<T, S>
    = DeprecatedAtLeastCheckerBuilder(times, builder)

@Deprecated("Use the extension fun `aberHoechstens`. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.aberHoechstens(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> aberHoechstens(checkerBuilder: DeprecatedAtLeastCheckerBuilder<T, S>, times: Int): DeprecatedButAtMostCheckerBuilder<T, S>
    = DeprecatedButAtMostCheckerBuilder(times, checkerBuilder, checkerBuilder.containsBuilder)

@Deprecated("Use the extension fun `genau`. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.genau(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> genau(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedExactlyCheckerBuilder<T, S>
    = DeprecatedExactlyCheckerBuilder(times, builder)

@Deprecated("Use the extension fun `hoechstens`. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.hoechstens(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> hoechstens(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedAtMostCheckerBuilder<T, S>
    = DeprecatedAtMostCheckerBuilder(times, builder)

@Deprecated("Use the extension fun `nichtOderHoechstens`. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.nichtOderHoechstens(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> nichtOderHoechstens(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedNotOrAtMostCheckerBuilder<T, S>
    = DeprecatedNotOrAtMostCheckerBuilder(times, builder)
