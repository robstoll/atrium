(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'atrium-domain-builders-js', 'kbox-js', 'atrium-domain-api-js'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('atrium-domain-builders-js'), require('kbox-js'), require('atrium-domain-api-js'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'atrium-api-cc-de_CH-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'atrium-api-cc-de_CH-js'.");
    }
    if (typeof this['atrium-domain-builders-js'] === 'undefined') {
      throw new Error("Error loading module 'atrium-api-cc-de_CH-js'. Its dependency 'atrium-domain-builders-js' was not found. Please, check whether 'atrium-domain-builders-js' is loaded prior to 'atrium-api-cc-de_CH-js'.");
    }
    if (typeof this['kbox-js'] === 'undefined') {
      throw new Error("Error loading module 'atrium-api-cc-de_CH-js'. Its dependency 'kbox-js' was not found. Please, check whether 'kbox-js' is loaded prior to 'atrium-api-cc-de_CH-js'.");
    }
    if (typeof this['atrium-domain-api-js'] === 'undefined') {
      throw new Error("Error loading module 'atrium-api-cc-de_CH-js'. Its dependency 'atrium-domain-api-js' was not found. Please, check whether 'atrium-domain-api-js' is loaded prior to 'atrium-api-cc-de_CH-js'.");
    }
    root['atrium-api-cc-de_CH-js'] = factory(typeof this['atrium-api-cc-de_CH-js'] === 'undefined' ? {} : this['atrium-api-cc-de_CH-js'], kotlin, this['atrium-domain-builders-js'], this['kbox-js'], this['atrium-domain-api-js']);
  }
}(this, function (_, Kotlin, $module$atrium_domain_builders_js, $module$kbox_js, $module$atrium_domain_api_js) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var defineInlineFunction = Kotlin.defineInlineFunction;
  var wrapFunction = Kotlin.wrapFunction;
  var creating = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating;
  var creating_0 = $module$atrium_domain_builders_js.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
  var asIterable = Kotlin.kotlin.collections.asIterable_us0mfu$;
  var asIterable_0 = Kotlin.kotlin.collections.asIterable_964n91$;
  var asIterable_1 = Kotlin.kotlin.collections.asIterable_355ntz$;
  var asIterable_2 = Kotlin.kotlin.collections.asIterable_i2lc79$;
  var asIterable_3 = Kotlin.kotlin.collections.asIterable_tmsbgo$;
  var asIterable_4 = Kotlin.kotlin.collections.asIterable_se6h4x$;
  var asIterable_5 = Kotlin.kotlin.collections.asIterable_rjqryz$;
  var asIterable_6 = Kotlin.kotlin.collections.asIterable_bvy38s$;
  var asIterable_7 = Kotlin.kotlin.collections.asIterable_l1lu5t$;
  var SubjectChangerBuilder = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder;
  var addAssertionForAssert = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertionForAssert_b6ozzt$;
  var varargToList = $module$kbox_js.ch.tutteli.kbox.varargToList_r59i0z$;
  var creators = $module$atrium_domain_builders_js.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.charsequence.contains.creators;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
  var searchbehaviours = $module$atrium_domain_builders_js.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours;
  var getPropertyCallableRef = Kotlin.getPropertyCallableRef;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var WithTimesCheckerOption = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.WithTimesCheckerOption;
  var CharSequenceContains$CheckerOption = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.CheckerOption;
  var getCallableRef = Kotlin.getCallableRef;
  var AtLeastCheckerOptionBase = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.AtLeastCheckerOptionBase;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var AtMostCheckerOptionBase = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.AtMostCheckerOptionBase;
  var ButAtMostCheckerOptionBase = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.ButAtMostCheckerOptionBase;
  var ExactlyCheckerOptionBase = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.ExactlyCheckerOptionBase;
  var NotCheckerOptionBase = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.NotCheckerOptionBase;
  var NotOrAtMostCheckerOptionBase = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.NotOrAtMostCheckerOptionBase;
  var WithTimesCheckerOption_0 = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.WithTimesCheckerOption;
  var IterableContains$CheckerOption = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.CheckerOption;
  var AtLeastCheckerOptionBase_0 = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.AtLeastCheckerOptionBase;
  var AtMostCheckerOptionBase_0 = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.AtMostCheckerOptionBase;
  var ButAtMostCheckerOptionBase_0 = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.ButAtMostCheckerOptionBase;
  var ExactlyCheckerOptionBase_0 = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.ExactlyCheckerOptionBase;
  var NotCheckerOptionBase_0 = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.NotCheckerOptionBase;
  var NotOrAtMostCheckerOptionBase_0 = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.NotOrAtMostCheckerOptionBase;
  var PleaseUseReplacementException = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException;
  var Untranslatable_init = $module$atrium_domain_builders_js.$$importsForInline$$['atrium-core-api-js'].ch.tutteli.atrium.reporting.translating.Untranslatable;
  var searchbehaviours_0 = $module$atrium_domain_builders_js.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours;
  var creators_0 = $module$atrium_domain_builders_js.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
  var addAssertionForAssert_0 = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertionForAssert_wjune8$;
  var groupsToList = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.utils.groupsToList_nrpso6$;
  var listOf = Kotlin.kotlin.collections.listOf_mh5how$;
  var GroupWithoutNullableEntries = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries;
  var GroupWithNullableEntries = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries;
  var VarArgHelper = $module$atrium_domain_builders_js.ch.tutteli.atrium.domain.builders.utils.VarArgHelper;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var listOf_0 = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var asIterable_8 = Kotlin.kotlin.sequences.asIterable_veqyi0$;
  var PrimitiveClasses$stringClass = Kotlin.kotlin.reflect.js.internal.PrimitiveClasses.stringClass;
  var Unit = Kotlin.kotlin.Unit;
  var creators_1 = $module$atrium_domain_builders_js.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.throwable.thrown.creators;
  AtLeastCheckerOptionImpl.prototype = Object.create(AtLeastCheckerOptionBase.prototype);
  AtLeastCheckerOptionImpl.prototype.constructor = AtLeastCheckerOptionImpl;
  AtMostCheckerOptionImpl.prototype = Object.create(AtMostCheckerOptionBase.prototype);
  AtMostCheckerOptionImpl.prototype.constructor = AtMostCheckerOptionImpl;
  ButAtMostCheckerOptionImpl.prototype = Object.create(ButAtMostCheckerOptionBase.prototype);
  ButAtMostCheckerOptionImpl.prototype.constructor = ButAtMostCheckerOptionImpl;
  ExactlyCheckerOptionImpl.prototype = Object.create(ExactlyCheckerOptionBase.prototype);
  ExactlyCheckerOptionImpl.prototype.constructor = ExactlyCheckerOptionImpl;
  NotCheckerOptionImpl.prototype = Object.create(NotCheckerOptionBase.prototype);
  NotCheckerOptionImpl.prototype.constructor = NotCheckerOptionImpl;
  NotOrAtMostCheckerOptionImpl.prototype = Object.create(NotOrAtMostCheckerOptionBase.prototype);
  NotOrAtMostCheckerOptionImpl.prototype.constructor = NotOrAtMostCheckerOptionImpl;
  AtLeastCheckerOptionImpl_0.prototype = Object.create(AtLeastCheckerOptionBase_0.prototype);
  AtLeastCheckerOptionImpl_0.prototype.constructor = AtLeastCheckerOptionImpl_0;
  AtMostCheckerOptionImpl_0.prototype = Object.create(AtMostCheckerOptionBase_0.prototype);
  AtMostCheckerOptionImpl_0.prototype.constructor = AtMostCheckerOptionImpl_0;
  ButAtMostCheckerOptionImpl_0.prototype = Object.create(ButAtMostCheckerOptionBase_0.prototype);
  ButAtMostCheckerOptionImpl_0.prototype.constructor = ButAtMostCheckerOptionImpl_0;
  ExactlyCheckerOptionImpl_0.prototype = Object.create(ExactlyCheckerOptionBase_0.prototype);
  ExactlyCheckerOptionImpl_0.prototype.constructor = ExactlyCheckerOptionImpl_0;
  NotCheckerOptionImpl_0.prototype = Object.create(NotCheckerOptionBase_0.prototype);
  NotCheckerOptionImpl_0.prototype.constructor = NotCheckerOptionImpl_0;
  NotOrAtMostCheckerOptionImpl_0.prototype = Object.create(NotOrAtMostCheckerOptionBase_0.prototype);
  NotOrAtMostCheckerOptionImpl_0.prototype.constructor = NotOrAtMostCheckerOptionImpl_0;
  function ist($receiver, expected) {
    creating.AnyAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.anyAssertions.toBe_7ehriy$($receiver, expected));
  }
  function istNicht($receiver, expected) {
    creating.AnyAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.anyAssertions.notToBe_kqhmyx$($receiver, expected));
  }
  function istSelbeInstanzWie($receiver, expected) {
    creating.AnyAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.anyAssertions.isSame_kqhmyx$($receiver, expected));
  }
  function istNichtSelbeInstanzWie($receiver, expected) {
    creating.AnyAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.anyAssertions.isNotSame_kqhmyx$($receiver, expected));
  }
  var ist_0 = defineInlineFunction('atrium-api-cc-de_CH-js.ch.tutteli.atrium.api.cc.de_CH.ist_djrnbm$', wrapFunction(function () {
    var getKClass = Kotlin.getKClass;
    var creating = _.$$importsForInline$$['atrium-domain-builders-js'].ch.tutteli.atrium.domain.builders.creating;
    var creating_0 = _.$$importsForInline$$['atrium-domain-builders-js'].$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (T_0, isT, $receiver, expected) {
      creating.AnyAssertionsBuilder;
      var type = getKClass(T_0);
      $receiver.addAssertion_94orq3$(creating_0.anyAssertions.isNullable_y7fwcm$($receiver, type, expected));
    };
  }));
  var istNullWennNullGegebenSonst = defineInlineFunction('atrium-api-cc-de_CH-js.ch.tutteli.atrium.api.cc.de_CH.istNullWennNullGegebenSonst_djhqrr$', wrapFunction(function () {
    var getKClass = Kotlin.getKClass;
    var creating = _.$$importsForInline$$['atrium-domain-builders-js'].ch.tutteli.atrium.domain.builders.creating;
    var creating_0 = _.$$importsForInline$$['atrium-domain-builders-js'].$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (T_0, isT, $receiver, assertionCreatorOrNull) {
      creating.AnyAssertionsBuilder;
      var type = getKClass(T_0);
      $receiver.addAssertion_94orq3$(creating_0.anyAssertions.isNullIfNullGivenElse_x9kay7$($receiver, type, assertionCreatorOrNull));
    };
  }));
  function get_und($receiver) {
    return $receiver;
  }
  function und($receiver, assertionCreator) {
    return $receiver.addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function asIterable$lambda(it) {
    return asIterable(it);
  }
  function asIterable_9($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asIterable$lambda);
  }
  function asIterable_10($receiver, assertionCreator) {
    return asIterable_9($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function asIterable$lambda_0(it) {
    return asIterable_0(it);
  }
  function asIterable_11($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asIterable$lambda_0);
  }
  function asIterable_12($receiver, assertionCreator) {
    return asIterable_11($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function asIterable$lambda_1(it) {
    return asIterable_1(it);
  }
  function asIterable_13($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asIterable$lambda_1);
  }
  function asIterable_14($receiver, assertionCreator) {
    return asIterable_13($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function asIterable$lambda_2(it) {
    return asIterable_2(it);
  }
  function asIterable_15($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asIterable$lambda_2);
  }
  function asIterable_16($receiver, assertionCreator) {
    return asIterable_15($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function asIterable$lambda_3(it) {
    return asIterable_3(it);
  }
  function asIterable_17($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asIterable$lambda_3);
  }
  function asIterable_18($receiver, assertionCreator) {
    return asIterable_17($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function asIterable$lambda_4(it) {
    return asIterable_4(it);
  }
  function asIterable_19($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asIterable$lambda_4);
  }
  function asIterable_20($receiver, assertionCreator) {
    return asIterable_19($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function asIterable$lambda_5(it) {
    return asIterable_5(it);
  }
  function asIterable_21($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asIterable$lambda_5);
  }
  function asIterable_22($receiver, assertionCreator) {
    return asIterable_21($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function asIterable$lambda_6(it) {
    return asIterable_6(it);
  }
  function asIterable_23($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asIterable$lambda_6);
  }
  function asIterable_24($receiver, assertionCreator) {
    return asIterable_23($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function asIterable$lambda_7(it) {
    return asIterable_7(it);
  }
  function asIterable_25($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asIterable$lambda_7);
  }
  function asIterable_26($receiver, assertionCreator) {
    return asIterable_25($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function get_enthaelt($receiver) {
    creating.CharSequenceAssertionsBuilder;
    return creating_0.charSequenceAssertions.containsBuilder_20mk8n$($receiver);
  }
  function get_enthaeltNicht($receiver) {
    creating.CharSequenceAssertionsBuilder;
    return new NotCheckerOptionImpl(creating_0.charSequenceAssertions.containsNotBuilder_20mk8n$($receiver));
  }
  function enthaelt($receiver, expected, otherExpected) {
    return werte(zumindest(get_enthaelt($receiver), 1), expected, otherExpected.slice());
  }
  function enthaeltNicht($receiver, expected, otherExpected) {
    return werte(get_enthaeltNicht($receiver), expected, otherExpected.slice());
  }
  function enthaeltRegex($receiver, pattern, otherPatterns) {
    return regex(zumindest(get_enthaelt($receiver), 1), pattern, otherPatterns.slice());
  }
  function beginntMit($receiver, expected) {
    creating.CharSequenceAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.charSequenceAssertions.startsWith_phxxlr$($receiver, expected));
  }
  function beginntNichtMit($receiver, expected) {
    creating.CharSequenceAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.charSequenceAssertions.startsNotWith_phxxlr$($receiver, expected));
  }
  function endetMit($receiver, expected) {
    creating.CharSequenceAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.charSequenceAssertions.endsWith_phxxlr$($receiver, expected));
  }
  function endetNichtMit($receiver, expected) {
    creating.CharSequenceAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.charSequenceAssertions.endsNotWith_phxxlr$($receiver, expected));
  }
  function istLeer($receiver) {
    creating.CharSequenceAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.charSequenceAssertions.isEmpty_3rtcbd$($receiver));
  }
  function istNichtLeer($receiver) {
    creating.CharSequenceAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.charSequenceAssertions.isNotEmpty_3rtcbd$($receiver));
  }
  function istNichtBlank($receiver) {
    creating.CharSequenceAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.charSequenceAssertions.isNotBlank_3rtcbd$($receiver));
  }
  function zumindest($receiver, times) {
    return new AtLeastCheckerOptionImpl(times, $receiver);
  }
  function aberHoechstens($receiver, times) {
    return new ButAtMostCheckerOptionImpl(times, $receiver, $receiver.containsBuilder);
  }
  function genau($receiver, times) {
    return new ExactlyCheckerOptionImpl(times, $receiver);
  }
  function hoechstens($receiver, times) {
    return new AtMostCheckerOptionImpl(times, $receiver);
  }
  function nichtOderHoechstens($receiver, times) {
    return new NotOrAtMostCheckerOptionImpl(times, $receiver);
  }
  var CharSequenceContainsAssertionsBuilder$regex$lambda = wrapFunction(function () {
    var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
    return function (it) {
      return Regex_init(it);
    };
  });
  function wert($receiver, expected) {
    return werte($receiver, expected, []);
  }
  function werte($receiver, expected, otherExpected) {
    creating.CharSequenceAssertionsBuilder;
    creating.CharSequenceContainsAssertionsBuilder;
    var expected_0 = varargToList(expected, otherExpected);
    return addAssertion($receiver, creators.charSequenceContainsAssertions.values_34khi3$($receiver, expected_0));
  }
  function wert_0($receiver, expected) {
    return werte_0($receiver, expected, []);
  }
  function werte_0($receiver, expected, otherExpected) {
    creating.CharSequenceAssertionsBuilder;
    creating.CharSequenceContainsAssertionsBuilder;
    var expected_0 = varargToList(expected, otherExpected);
    return addAssertion($receiver, creators.charSequenceContainsAssertions.valuesIgnoringCase_56nr2g$($receiver, expected_0));
  }
  function wert_1($receiver, expected) {
    return wert_0(zumindest($receiver, 1), expected);
  }
  function werte_1($receiver, expected, otherExpected) {
    return werte_0(zumindest($receiver, 1), expected, otherExpected.slice());
  }
  function regex($receiver, pattern, otherPatterns) {
    creating.CharSequenceAssertionsBuilder;
    creating.CharSequenceContainsAssertionsBuilder;
    var expected = varargToList(pattern, otherPatterns);
    var destination = ArrayList_init(collectionSizeOrDefault(expected, 10));
    var tmp$;
    tmp$ = expected.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(Regex_init(item));
    }
    return addAssertion($receiver, creators.charSequenceContainsAssertions.regex_imkq43$($receiver, destination));
  }
  function regex_0($receiver, pattern, otherPatterns) {
    creating.CharSequenceAssertionsBuilder;
    creating.CharSequenceContainsAssertionsBuilder;
    var expected = varargToList(pattern, otherPatterns);
    return addAssertion($receiver, creators.charSequenceContainsAssertions.regexIgnoringCase_dzi1c1$($receiver, expected));
  }
  function regex_1($receiver, pattern, otherPatterns) {
    return regex_0(zumindest($receiver, 1), pattern, otherPatterns.slice());
  }
  function addAssertion($receiver, assertion) {
    return addAssertionForAssert($receiver, assertion);
  }
  function get_ignoriereGrossKleinschreibung($receiver) {
    creating.CharSequenceAssertionsBuilder;
    creating.CharSequenceContainsAssertionsBuilder;
    creating.CharSequenceContainsSearchBehaviourFactoryBuilder;
    return searchbehaviours.searchBehaviourFactory.ignoringCase_553t70$($receiver);
  }
  function get_ignoriereGrossKleinschreibung_0($receiver) {
    return new NotCheckerOptionImpl(get_ignoriereGrossKleinschreibung($receiver.containsBuilder));
  }
  function hatDieGroesse($receiver, size) {
    creating.CollectionAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.collectionAssertions.hasSize_s5wgvh$($receiver, size));
  }
  function istLeer_0($receiver) {
    creating.CollectionAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.collectionAssertions.isEmpty_dx9xz9$($receiver));
  }
  function istNichtLeer_0($receiver) {
    creating.CollectionAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.collectionAssertions.isNotEmpty_dx9xz9$($receiver));
  }
  function get_size($receiver) {
    return property_1($receiver, getPropertyCallableRef('size', 1, function ($receiver) {
      return $receiver.size;
    }));
  }
  function size($receiver, assertionCreator) {
    creating.CollectionAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.collectionAssertions.size_xupd9b$($receiver, assertionCreator));
  }
  function istKleinerAls($receiver, expected) {
    creating.ComparableAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.comparableAssertions.isLessThan_hz4bm$($receiver, expected));
  }
  function istKleinerOderGleich($receiver, expected) {
    creating.ComparableAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.comparableAssertions.isLessOrEquals_hz4bm$($receiver, expected));
  }
  function istGroesserAls($receiver, expected) {
    creating.ComparableAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.comparableAssertions.isGreaterThan_hz4bm$($receiver, expected));
  }
  function istGroesserOderGleich($receiver, expected) {
    creating.ComparableAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.comparableAssertions.isGreaterOrEquals_hz4bm$($receiver, expected));
  }
  function AtLeastCheckerOption() {
  }
  AtLeastCheckerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AtLeastCheckerOption',
    interfaces: [WithTimesCheckerOption]
  };
  function AtMostCheckerOption() {
  }
  AtMostCheckerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AtMostCheckerOption',
    interfaces: [CharSequenceContains$CheckerOption]
  };
  function ButAtMostCheckerOption() {
  }
  ButAtMostCheckerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ButAtMostCheckerOption',
    interfaces: [CharSequenceContains$CheckerOption]
  };
  function ExactlyCheckerOption() {
  }
  ExactlyCheckerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ExactlyCheckerOption',
    interfaces: [CharSequenceContains$CheckerOption]
  };
  function NotCheckerOption() {
  }
  NotCheckerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NotCheckerOption',
    interfaces: [CharSequenceContains$CheckerOption]
  };
  function NotOrAtMostCheckerOption() {
  }
  NotOrAtMostCheckerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NotOrAtMostCheckerOption',
    interfaces: [CharSequenceContains$CheckerOption]
  };
  function AtLeastCheckerOptionImpl(times, containsBuilder) {
    AtLeastCheckerOptionBase.call(this, times, containsBuilder, nameContainsNotValuesFun(), AtLeastCheckerOptionImpl_init$lambda(containsBuilder));
  }
  function AtLeastCheckerOptionImpl_init$lambda(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('zumindest', function ($receiver, times) {
        return zumindest($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  AtLeastCheckerOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AtLeastCheckerOptionImpl',
    interfaces: [AtLeastCheckerOption, AtLeastCheckerOptionBase]
  };
  function AtMostCheckerOptionImpl(times, containsBuilder) {
    AtMostCheckerOptionBase.call(this, times, containsBuilder, nameContainsNotValuesFun(), AtMostCheckerOptionImpl_init$lambda(containsBuilder), AtMostCheckerOptionImpl_init$lambda_0(containsBuilder), AtMostCheckerOptionImpl_init$lambda_1(containsBuilder));
  }
  function AtMostCheckerOptionImpl_init$lambda(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('hoechstens', function ($receiver, times) {
        return hoechstens($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  function AtMostCheckerOptionImpl_init$lambda_0(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('zumindest', function ($receiver, times) {
        return zumindest($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  function AtMostCheckerOptionImpl_init$lambda_1(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('genau', function ($receiver, times) {
        return genau($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  AtMostCheckerOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AtMostCheckerOptionImpl',
    interfaces: [AtMostCheckerOption, AtMostCheckerOptionBase]
  };
  function ButAtMostCheckerOptionImpl(times, atLeastBuilder, containsBuilder) {
    ButAtMostCheckerOptionBase.call(this, times, atLeastBuilder, containsBuilder, nameContainsNotValuesFun(), ButAtMostCheckerOptionImpl_init$lambda(containsBuilder, atLeastBuilder), ButAtMostCheckerOptionImpl_init$lambda_0(containsBuilder), ButAtMostCheckerOptionImpl_init$lambda_1(containsBuilder), ButAtMostCheckerOptionImpl_init$lambda_2(atLeastBuilder), ButAtMostCheckerOptionImpl_init$lambda_3(containsBuilder));
  }
  function ButAtMostCheckerOptionImpl_init$lambda(closure$containsBuilder, closure$atLeastBuilder) {
    return function (l, u) {
      return getCallableRef('zumindest', function ($receiver, times) {
        return zumindest($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + l + ').' + getCallableRef('aberHoechstens', function ($receiver, times) {
        return aberHoechstens($receiver, times);
      }.bind(null, closure$atLeastBuilder)).callableName + '(' + u + ')';
    };
  }
  function ButAtMostCheckerOptionImpl_init$lambda_0(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('hoechstens', function ($receiver, times) {
        return hoechstens($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  function ButAtMostCheckerOptionImpl_init$lambda_1(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('zumindest', function ($receiver, times) {
        return zumindest($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  function ButAtMostCheckerOptionImpl_init$lambda_2(closure$atLeastBuilder) {
    return function (it) {
      return getCallableRef('aberHoechstens', function ($receiver, times) {
        return aberHoechstens($receiver, times);
      }.bind(null, closure$atLeastBuilder)).callableName + '(' + it + ')';
    };
  }
  function ButAtMostCheckerOptionImpl_init$lambda_3(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('genau', function ($receiver, times) {
        return genau($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  ButAtMostCheckerOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ButAtMostCheckerOptionImpl',
    interfaces: [ButAtMostCheckerOption, ButAtMostCheckerOptionBase]
  };
  function ExactlyCheckerOptionImpl(times, containsBuilder) {
    ExactlyCheckerOptionBase.call(this, times, containsBuilder, nameContainsNotValuesFun(), ExactlyCheckerOptionImpl_init$lambda(containsBuilder));
  }
  function ExactlyCheckerOptionImpl_init$lambda(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('genau', function ($receiver, times) {
        return genau($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  ExactlyCheckerOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExactlyCheckerOptionImpl',
    interfaces: [ExactlyCheckerOption, ExactlyCheckerOptionBase]
  };
  function NotCheckerOptionImpl(containsBuilder) {
    NotCheckerOptionBase.call(this, containsBuilder);
  }
  NotCheckerOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotCheckerOptionImpl',
    interfaces: [NotCheckerOption, NotCheckerOptionBase]
  };
  function NotOrAtMostCheckerOptionImpl(times, containsBuilder) {
    NotOrAtMostCheckerOptionBase.call(this, times, containsBuilder, nameContainsNotValuesFun(), NotOrAtMostCheckerOptionImpl_init$lambda(containsBuilder));
  }
  function NotOrAtMostCheckerOptionImpl_init$lambda(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('nichtOderHoechstens', function ($receiver, times) {
        return nichtOderHoechstens($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  NotOrAtMostCheckerOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotOrAtMostCheckerOptionImpl',
    interfaces: [NotOrAtMostCheckerOption, NotOrAtMostCheckerOptionBase]
  };
  function nameContainsNotValuesFun() {
    var f = getCallableRef('enthaeltNicht', function ($receiver, expected, otherExpected) {
      return enthaeltNicht($receiver, expected, otherExpected);
    });
    return f.callableName;
  }
  function AtLeastCheckerOption_0() {
  }
  AtLeastCheckerOption_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AtLeastCheckerOption',
    interfaces: [WithTimesCheckerOption_0]
  };
  function AtMostCheckerOption_0() {
  }
  AtMostCheckerOption_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AtMostCheckerOption',
    interfaces: [WithTimesCheckerOption_0]
  };
  function ButAtMostCheckerOption_0() {
  }
  ButAtMostCheckerOption_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ButAtMostCheckerOption',
    interfaces: [WithTimesCheckerOption_0]
  };
  function ExactlyCheckerOption_0() {
  }
  ExactlyCheckerOption_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ExactlyCheckerOption',
    interfaces: [WithTimesCheckerOption_0]
  };
  function NotCheckerOption_0() {
  }
  NotCheckerOption_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NotCheckerOption',
    interfaces: [IterableContains$CheckerOption]
  };
  function NotOrAtMostCheckerOption_0() {
  }
  NotOrAtMostCheckerOption_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NotOrAtMostCheckerOption',
    interfaces: [WithTimesCheckerOption_0]
  };
  function AtLeastCheckerOptionImpl_0(times, containsBuilder) {
    AtLeastCheckerOptionBase_0.call(this, times, containsBuilder, nameContainsNotValuesFun_0(), AtLeastCheckerOptionImpl_init$lambda_0(containsBuilder));
  }
  function AtLeastCheckerOptionImpl_init$lambda_0(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('zumindest', function ($receiver, times) {
        return zumindest_0($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  AtLeastCheckerOptionImpl_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AtLeastCheckerOptionImpl',
    interfaces: [AtLeastCheckerOption_0, AtLeastCheckerOptionBase_0]
  };
  function AtMostCheckerOptionImpl_0(times, containsBuilder) {
    AtMostCheckerOptionBase_0.call(this, times, containsBuilder, nameContainsNotValuesFun_0(), AtMostCheckerOptionImpl_init$lambda_2(containsBuilder), AtMostCheckerOptionImpl_init$lambda_3(containsBuilder), AtMostCheckerOptionImpl_init$lambda_4(containsBuilder));
  }
  function AtMostCheckerOptionImpl_init$lambda_2(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('hoechstens', function ($receiver, times) {
        return hoechstens_0($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  function AtMostCheckerOptionImpl_init$lambda_3(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('zumindest', function ($receiver, times) {
        return zumindest_0($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  function AtMostCheckerOptionImpl_init$lambda_4(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('genau', function ($receiver, times) {
        return genau_0($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  AtMostCheckerOptionImpl_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AtMostCheckerOptionImpl',
    interfaces: [AtMostCheckerOption_0, AtMostCheckerOptionBase_0]
  };
  function ButAtMostCheckerOptionImpl_0(times, atLeastBuilder, containsBuilder) {
    ButAtMostCheckerOptionBase_0.call(this, times, atLeastBuilder, containsBuilder, nameContainsNotValuesFun_0(), ButAtMostCheckerOptionImpl_init$lambda_4(containsBuilder, atLeastBuilder), ButAtMostCheckerOptionImpl_init$lambda_5(containsBuilder), ButAtMostCheckerOptionImpl_init$lambda_6(containsBuilder), ButAtMostCheckerOptionImpl_init$lambda_7(atLeastBuilder), ButAtMostCheckerOptionImpl_init$lambda_8(containsBuilder));
  }
  function ButAtMostCheckerOptionImpl_init$lambda_4(closure$containsBuilder, closure$atLeastBuilder) {
    return function (l, u) {
      return getCallableRef('zumindest', function ($receiver, times) {
        return zumindest_0($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + l + ').' + getCallableRef('aberHoechstens', function ($receiver, times) {
        return aberHoechstens_0($receiver, times);
      }.bind(null, closure$atLeastBuilder)).callableName + '(' + u + ')';
    };
  }
  function ButAtMostCheckerOptionImpl_init$lambda_5(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('hoechstens', function ($receiver, times) {
        return hoechstens_0($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  function ButAtMostCheckerOptionImpl_init$lambda_6(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('zumindest', function ($receiver, times) {
        return zumindest_0($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  function ButAtMostCheckerOptionImpl_init$lambda_7(closure$atLeastBuilder) {
    return function (it) {
      return getCallableRef('aberHoechstens', function ($receiver, times) {
        return aberHoechstens_0($receiver, times);
      }.bind(null, closure$atLeastBuilder)).callableName + '(' + it + ')';
    };
  }
  function ButAtMostCheckerOptionImpl_init$lambda_8(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('genau', function ($receiver, times) {
        return genau_0($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  ButAtMostCheckerOptionImpl_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ButAtMostCheckerOptionImpl',
    interfaces: [ButAtMostCheckerOption_0, ButAtMostCheckerOptionBase_0]
  };
  function ExactlyCheckerOptionImpl_0(times, containsBuilder) {
    ExactlyCheckerOptionBase_0.call(this, times, containsBuilder, nameContainsNotValuesFun_0(), ExactlyCheckerOptionImpl_init$lambda_0(containsBuilder));
  }
  function ExactlyCheckerOptionImpl_init$lambda_0(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('genau', function ($receiver, times) {
        return genau_0($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  ExactlyCheckerOptionImpl_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExactlyCheckerOptionImpl',
    interfaces: [ExactlyCheckerOption_0, ExactlyCheckerOptionBase_0]
  };
  function NotCheckerOptionImpl_0(containsBuilder) {
    NotCheckerOptionBase_0.call(this, containsBuilder);
  }
  NotCheckerOptionImpl_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotCheckerOptionImpl',
    interfaces: [NotCheckerOption_0, NotCheckerOptionBase_0]
  };
  function NotOrAtMostCheckerOptionImpl_0(times, containsBuilder) {
    NotOrAtMostCheckerOptionBase_0.call(this, times, containsBuilder, nameContainsNotValuesFun_0(), NotOrAtMostCheckerOptionImpl_init$lambda_0(containsBuilder));
  }
  function NotOrAtMostCheckerOptionImpl_init$lambda_0(closure$containsBuilder) {
    return function (it) {
      return getCallableRef('nichtOderHoechstens', function ($receiver, times) {
        return nichtOderHoechstens_0($receiver, times);
      }.bind(null, closure$containsBuilder)).callableName + '(' + it + ')';
    };
  }
  NotOrAtMostCheckerOptionImpl_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotOrAtMostCheckerOptionImpl',
    interfaces: [NotOrAtMostCheckerOption_0, NotOrAtMostCheckerOptionBase_0]
  };
  function nameContainsNotValuesFun_0() {
    var f = getCallableRef('enthaeltNicht', function ($receiver, expected, otherExpected) {
      return enthaeltNicht_0($receiver, expected, otherExpected);
    });
    return f.callableName;
  }
  function FeatureAssertionsBuilder$property$lambda(closure$property, closure$plant) {
    return function () {
      return closure$property(closure$plant.subject);
    };
  }
  function FeatureAssertionsBuilder$property$lambda_0(closure$property, closure$plant) {
    return function () {
      return closure$property(closure$plant.subject);
    };
  }
  function FeatureAssertionsBuilder$property$lambda_1(closure$property, closure$plant) {
    return function () {
      return closure$property(closure$plant.subject);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf0$lambda(closure$method, closure$plant) {
    return function () {
      return closure$method(closure$plant.subject);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf0$lambda_0(closure$method, closure$plant) {
    return function () {
      return closure$method(closure$plant.subject);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf0$lambda_1(closure$method, closure$plant) {
    return function () {
      return closure$method(closure$plant.subject);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf1$lambda(closure$method, closure$plant) {
    return function (a1) {
      return closure$method(closure$plant.subject, a1);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf1$lambda_0(closure$method, closure$plant) {
    return function (a1) {
      return closure$method(closure$plant.subject, a1);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf1$lambda_1(closure$method, closure$plant) {
    return function (a1) {
      return closure$method(closure$plant.subject, a1);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf2$lambda(closure$method, closure$plant) {
    return function (a1, a2) {
      return closure$method(closure$plant.subject, a1, a2);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf2$lambda_0(closure$method, closure$plant) {
    return function (a1, a2) {
      return closure$method(closure$plant.subject, a1, a2);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf2$lambda_1(closure$method, closure$plant) {
    return function (a1, a2) {
      return closure$method(closure$plant.subject, a1, a2);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf3$lambda(closure$method, closure$plant) {
    return function (a1, a2, a3) {
      return closure$method(closure$plant.subject, a1, a2, a3);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf3$lambda_0(closure$method, closure$plant) {
    return function (a1, a2, a3) {
      return closure$method(closure$plant.subject, a1, a2, a3);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf3$lambda_1(closure$method, closure$plant) {
    return function (a1, a2, a3) {
      return closure$method(closure$plant.subject, a1, a2, a3);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf4$lambda(closure$method, closure$plant) {
    return function (a1, a2, a3, a4) {
      return closure$method(closure$plant.subject, a1, a2, a3, a4);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf4$lambda_0(closure$method, closure$plant) {
    return function (a1, a2, a3, a4) {
      return closure$method(closure$plant.subject, a1, a2, a3, a4);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf4$lambda_1(closure$method, closure$plant) {
    return function (a1, a2, a3, a4) {
      return closure$method(closure$plant.subject, a1, a2, a3, a4);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf5$lambda(closure$method, closure$plant) {
    return function (a1, a2, a3, a4, a5) {
      return closure$method(closure$plant.subject, a1, a2, a3, a4, a5);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf5$lambda_0(closure$method, closure$plant) {
    return function (a1, a2, a3, a4, a5) {
      return closure$method(closure$plant.subject, a1, a2, a3, a4, a5);
    };
  }
  function FeatureAssertionsBuilder$returnValueOf5$lambda_1(closure$method, closure$plant) {
    return function (a1, a2, a3, a4, a5) {
      return closure$method(closure$plant.subject, a1, a2, a3, a4, a5);
    };
  }
  function property($receiver, property) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.property_v1jh8n$($receiver, property, new Untranslatable_init(property.callableName));
  }
  function property_0($receiver, property) {
    throw new PleaseUseReplacementException('Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function property_1($receiver, property) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.property_v1jh8n$($receiver, FeatureAssertionsBuilder$property$lambda(property, $receiver), new Untranslatable_init(property.callableName));
  }
  function property_2($receiver, property, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.property_v1jh8n$($receiver, property, new Untranslatable_init(property.callableName)).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function property_3($receiver, property, assertionCreator) {
    throw new PleaseUseReplacementException('Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function property_4($receiver, property, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.property_v1jh8n$($receiver, FeatureAssertionsBuilder$property$lambda_0(property, $receiver), new Untranslatable_init(property.callableName)).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function property_5($receiver, property) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.property_j4srze$($receiver, property, new Untranslatable_init(property.callableName));
  }
  function property_6($receiver, property) {
    throw new PleaseUseReplacementException('Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function property_7($receiver, property) {
    creating.FeatureAssertionsBuilder;
    var l = FeatureAssertionsBuilder$property$lambda_1(property, $receiver);
    return creating_0.featureAssertions.property_j4srze$($receiver, l, new Untranslatable_init(property.callableName));
  }
  function rueckgabewertVon($receiver, method) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf0_6ilun3$($receiver, method, method.callableName);
  }
  function rueckgabewertVon_0($receiver, method) {
    throw new PleaseUseReplacementException('Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_1($receiver, method) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf0_6ilun3$($receiver, FeatureAssertionsBuilder$returnValueOf0$lambda(method, $receiver), method.callableName);
  }
  function rueckgabewertVon_2($receiver, method, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf0_6ilun3$($receiver, method, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_3($receiver, method, assertionCreator) {
    throw new PleaseUseReplacementException('Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_4($receiver, method, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf0_6ilun3$($receiver, FeatureAssertionsBuilder$returnValueOf0$lambda_0(method, $receiver), method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_5($receiver, method) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf0_ux3z7m$($receiver, method, method.callableName);
  }
  function rueckgabewertVon_6($receiver, method) {
    throw new PleaseUseReplacementException('Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_7($receiver, method) {
    creating.FeatureAssertionsBuilder;
    var l = FeatureAssertionsBuilder$returnValueOf0$lambda_1(method, $receiver);
    return creating_0.featureAssertions.returnValueOf0_awp556$($receiver, l, l, method.callableName);
  }
  function rueckgabewertVon_8($receiver, method, arg1) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf1_z7b8x6$($receiver, method, arg1, method.callableName);
  }
  function rueckgabewertVon_9($receiver, method, arg1) {
    throw new PleaseUseReplacementException('Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_10($receiver, method, arg1) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf1_z7b8x6$($receiver, FeatureAssertionsBuilder$returnValueOf1$lambda(method, $receiver), arg1, method.callableName);
  }
  function rueckgabewertVon_11($receiver, method, arg1, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf1_z7b8x6$($receiver, method, arg1, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_12($receiver, method, arg1, assertionCreator) {
    throw new PleaseUseReplacementException('Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_13($receiver, method, arg1, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf1_z7b8x6$($receiver, FeatureAssertionsBuilder$returnValueOf1$lambda_0(method, $receiver), arg1, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_14($receiver, method, arg1) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf1_w21xql$($receiver, method, arg1, method.callableName);
  }
  function rueckgabewertVon_15($receiver, method, arg1) {
    throw new PleaseUseReplacementException('Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_16($receiver, method, arg1) {
    creating.FeatureAssertionsBuilder;
    var l = FeatureAssertionsBuilder$returnValueOf1$lambda_1(method, $receiver);
    return creating_0.featureAssertions.returnValueOf1_w21xql$($receiver, l, arg1, method.callableName);
  }
  function rueckgabewertVon_17($receiver, method, arg1, arg2) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf2_cb7pf4$($receiver, method, arg1, arg2, method.callableName);
  }
  function rueckgabewertVon_18($receiver, method, arg1, arg2) {
    throw new PleaseUseReplacementException('Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_19($receiver, method, arg1, arg2) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf2_cb7pf4$($receiver, FeatureAssertionsBuilder$returnValueOf2$lambda(method, $receiver), arg1, arg2, method.callableName);
  }
  function rueckgabewertVon_20($receiver, method, arg1, arg2, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf2_cb7pf4$($receiver, method, arg1, arg2, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_21($receiver, method, arg1, arg2, assertionCreator) {
    throw new PleaseUseReplacementException('Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_22($receiver, method, arg1, arg2, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf2_cb7pf4$($receiver, FeatureAssertionsBuilder$returnValueOf2$lambda_0(method, $receiver), arg1, arg2, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_23($receiver, method, arg1, arg2) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf2_ecu6cz$($receiver, method, arg1, arg2, method.callableName);
  }
  function rueckgabewertVon_24($receiver, method, arg1, arg2) {
    throw new PleaseUseReplacementException('Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_25($receiver, method, arg1, arg2) {
    creating.FeatureAssertionsBuilder;
    var l = FeatureAssertionsBuilder$returnValueOf2$lambda_1(method, $receiver);
    return creating_0.featureAssertions.returnValueOf2_ecu6cz$($receiver, l, arg1, arg2, method.callableName);
  }
  function rueckgabewertVon_26($receiver, method, arg1, arg2, arg3) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf3_hcmhmj$($receiver, method, arg1, arg2, arg3, method.callableName);
  }
  function rueckgabewertVon_27($receiver, method, arg1, arg2, arg3) {
    throw new PleaseUseReplacementException('Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_28($receiver, method, arg1, arg2, arg3) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf3_hcmhmj$($receiver, FeatureAssertionsBuilder$returnValueOf3$lambda(method, $receiver), arg1, arg2, arg3, method.callableName);
  }
  function rueckgabewertVon_29($receiver, method, arg1, arg2, arg3, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf3_hcmhmj$($receiver, method, arg1, arg2, arg3, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_30($receiver, method, arg1, arg2, arg3, assertionCreator) {
    throw new PleaseUseReplacementException('Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_31($receiver, method, arg1, arg2, arg3, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf3_hcmhmj$($receiver, FeatureAssertionsBuilder$returnValueOf3$lambda_0(method, $receiver), arg1, arg2, arg3, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_32($receiver, method, arg1, arg2, arg3) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf3_fwwyfy$($receiver, method, arg1, arg2, arg3, method.callableName);
  }
  function rueckgabewertVon_33($receiver, method, arg1, arg2, arg3) {
    throw new PleaseUseReplacementException('Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_34($receiver, method, arg1, arg2, arg3) {
    creating.FeatureAssertionsBuilder;
    var l = FeatureAssertionsBuilder$returnValueOf3$lambda_1(method, $receiver);
    return creating_0.featureAssertions.returnValueOf3_fwwyfy$($receiver, l, arg1, arg2, arg3, method.callableName);
  }
  function rueckgabewertVon_35($receiver, method, arg1, arg2, arg3, arg4) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf4_5owrw7$($receiver, method, arg1, arg2, arg3, arg4, method.callableName);
  }
  function rueckgabewertVon_36($receiver, method, arg1, arg2, arg3, arg4) {
    throw new PleaseUseReplacementException('Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_37($receiver, method, arg1, arg2, arg3, arg4) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf4_5owrw7$($receiver, FeatureAssertionsBuilder$returnValueOf4$lambda(method, $receiver), arg1, arg2, arg3, arg4, method.callableName);
  }
  function rueckgabewertVon_38($receiver, method, arg1, arg2, arg3, arg4, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf4_5owrw7$($receiver, method, arg1, arg2, arg3, arg4, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_39($receiver, method, arg1, arg2, arg3, arg4, assertionCreator) {
    throw new PleaseUseReplacementException('Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_40($receiver, method, arg1, arg2, arg3, arg4, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf4_5owrw7$($receiver, FeatureAssertionsBuilder$returnValueOf4$lambda_0(method, $receiver), arg1, arg2, arg3, arg4, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_41($receiver, method, arg1, arg2, arg3, arg4) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf4_sspjtg$($receiver, method, arg1, arg2, arg3, arg4, method.callableName);
  }
  function rueckgabewertVon_42($receiver, method, arg1, arg2, arg3, arg4) {
    throw new PleaseUseReplacementException('Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_43($receiver, method, arg1, arg2, arg3, arg4) {
    creating.FeatureAssertionsBuilder;
    var l = FeatureAssertionsBuilder$returnValueOf4$lambda_1(method, $receiver);
    return creating_0.featureAssertions.returnValueOf4_sspjtg$($receiver, l, arg1, arg2, arg3, arg4, method.callableName);
  }
  function rueckgabewertVon_44($receiver, method, arg1, arg2, arg3, arg4, arg5) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf5_nytevo$($receiver, method, arg1, arg2, arg3, arg4, arg5, method.callableName);
  }
  function rueckgabewertVon_45($receiver, method, arg1, arg2, arg3, arg4, arg5) {
    throw new PleaseUseReplacementException('Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_46($receiver, method, arg1, arg2, arg3, arg4, arg5) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf5_nytevo$($receiver, FeatureAssertionsBuilder$returnValueOf5$lambda(method, $receiver), arg1, arg2, arg3, arg4, arg5, method.callableName);
  }
  function rueckgabewertVon_47($receiver, method, arg1, arg2, arg3, arg4, arg5, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf5_nytevo$($receiver, method, arg1, arg2, arg3, arg4, arg5, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_48($receiver, method, arg1, arg2, arg3, arg4, arg5, assertionCreator) {
    throw new PleaseUseReplacementException('Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_49($receiver, method, arg1, arg2, arg3, arg4, arg5, assertionCreator) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf5_nytevo$($receiver, FeatureAssertionsBuilder$returnValueOf5$lambda_0(method, $receiver), arg1, arg2, arg3, arg4, arg5, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function rueckgabewertVon_50($receiver, method, arg1, arg2, arg3, arg4, arg5) {
    creating.FeatureAssertionsBuilder;
    return creating_0.featureAssertions.returnValueOf5_hfvscp$($receiver, method, arg1, arg2, arg3, arg4, arg5, method.callableName);
  }
  function rueckgabewertVon_51($receiver, method, arg1, arg2, arg3, arg4, arg5) {
    throw new PleaseUseReplacementException('Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`');
  }
  function rueckgabewertVon_52($receiver, method, arg1, arg2, arg3, arg4, arg5) {
    creating.FeatureAssertionsBuilder;
    var l = FeatureAssertionsBuilder$returnValueOf5$lambda_1(method, $receiver);
    return creating_0.featureAssertions.returnValueOf5_hfvscp$($receiver, l, arg1, arg2, arg3, arg4, arg5, method.callableName);
  }
  function istMitFehlerToleranz($receiver, expected, tolerance) {
    creating.FloatingPointAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.floatingPointAssertions.toBeWithErrorTolerance_bvnqyq$($receiver, expected, tolerance));
  }
  function istMitFehlerToleranz_0($receiver, expected, tolerance) {
    creating.FloatingPointAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.floatingPointAssertions.toBeWithErrorTolerance_afszox$($receiver, expected, tolerance));
  }
  function get_enthaelt_0($receiver) {
    creating.IterableAssertionsBuilder;
    return creating_0.iterableAssertions.containsBuilder_fyqaqw$($receiver);
  }
  function get_enthaeltNicht_0($receiver) {
    creating.IterableAssertionsBuilder;
    return new NotCheckerOptionImpl_0(creating_0.iterableAssertions.containsNotBuilder_fyqaqw$($receiver));
  }
  function enthaelt_0($receiver, expected, otherExpected) {
    return werte_2(zumindest_0(get_inBeliebigerReihenfolge(get_enthaelt_0($receiver)), 1), expected, otherExpected.slice());
  }
  function enthaelt_1($receiver, assertionCreatorOrNull) {
    return eintrag(zumindest_0(get_inBeliebigerReihenfolge(get_enthaelt_0($receiver)), 1), assertionCreatorOrNull);
  }
  function enthaelt_2($receiver, assertionCreatorOrNull, otherAssertionCreatorsOrNulls) {
    return eintraege(zumindest_0(get_inBeliebigerReihenfolge(get_enthaelt_0($receiver)), 1), assertionCreatorOrNull, otherAssertionCreatorsOrNulls.slice());
  }
  function enthaeltExakt($receiver, expected, otherExpected) {
    return werte_4(get_nur_0(get_inGegebenerReihenfolge(get_enthaelt_0($receiver))), expected, otherExpected.slice());
  }
  function enthaeltExakt_0($receiver, assertionCreatorOrNull) {
    return eintrag_1(get_nur_0(get_inGegebenerReihenfolge(get_enthaelt_0($receiver))), assertionCreatorOrNull);
  }
  function enthaeltExakt_1($receiver, assertionCreatorOrNull, otherAssertionCreatorsOrNulls) {
    return eintraege_1(get_nur_0(get_inGegebenerReihenfolge(get_enthaelt_0($receiver))), assertionCreatorOrNull, otherAssertionCreatorsOrNulls.slice());
  }
  function enthaeltNicht_0($receiver, expected, otherExpected) {
    return werte_2(get_enthaeltNicht_0($receiver), expected, otherExpected.slice());
  }
  function irgendEiner($receiver, assertionCreatorOrNull) {
    return eintrag(zumindest_0(get_inBeliebigerReihenfolge(get_enthaelt_0($receiver)), 1), assertionCreatorOrNull);
  }
  function keiner($receiver, assertionCreatorOrNull) {
    return eintrag(get_enthaeltNicht_0($receiver), assertionCreatorOrNull);
  }
  function alle($receiver, assertionCreatorOrNull) {
    creating.IterableAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.iterableAssertions.all_rcrmla$($receiver, assertionCreatorOrNull));
  }
  function zumindest_0($receiver, times) {
    return new AtLeastCheckerOptionImpl_0(times, $receiver);
  }
  function aberHoechstens_0($receiver, times) {
    return new ButAtMostCheckerOptionImpl_0(times, $receiver, $receiver.containsBuilder);
  }
  function genau_0($receiver, times) {
    return new ExactlyCheckerOptionImpl_0(times, $receiver);
  }
  function hoechstens_0($receiver, times) {
    return new AtMostCheckerOptionImpl_0(times, $receiver);
  }
  function nichtOderHoechstens_0($receiver, times) {
    return new NotOrAtMostCheckerOptionImpl_0(times, $receiver);
  }
  function get_inBeliebigerReihenfolge($receiver) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    creating.IterableContainsSearchBehaviourFactoryBuilder;
    return searchbehaviours_0.searchBehaviourFactory.inAnyOrder_mn0tah$($receiver);
  }
  function get_nur($receiver) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    creating.IterableContainsSearchBehaviourFactoryBuilder;
    return searchbehaviours_0.searchBehaviourFactory.inAnyOrderOnly_i6bwk2$($receiver);
  }
  function get_inGegebenerReihenfolge($receiver) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    creating.IterableContainsSearchBehaviourFactoryBuilder;
    return searchbehaviours_0.searchBehaviourFactory.inOrder_mn0tah$($receiver);
  }
  function get_nur_0($receiver) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    creating.IterableContainsSearchBehaviourFactoryBuilder;
    return searchbehaviours_0.searchBehaviourFactory.inOrderOnly_9wcnok$($receiver);
  }
  function get_gruppiert($receiver) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    creating.IterableContainsSearchBehaviourFactoryBuilder;
    return searchbehaviours_0.searchBehaviourFactory.inOrderOnlyGrouped_owsmxk$($receiver);
  }
  function get_innerhalb($receiver) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    creating.IterableContainsSearchBehaviourFactoryBuilder;
    return searchbehaviours_0.searchBehaviourFactory.inOrderOnlyGroupedWithin_izjtps$($receiver);
  }
  function wert_2($receiver, expected) {
    return werte_2($receiver, expected, []);
  }
  function werte_2($receiver, expected, otherExpected) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    var expected_0 = varargToList(expected, otherExpected);
    return addAssertion_0($receiver, creators_0.iterableContainsAssertions.valuesInAnyOrder_34ablu$($receiver, expected_0));
  }
  function eintrag($receiver, assertionCreatorOrNull) {
    return eintraege($receiver, assertionCreatorOrNull, []);
  }
  function eintraege($receiver, assertionCreatorOrNull, otherAssertionCreatorsOrNulls) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    var assertionCreators = varargToList(assertionCreatorOrNull, otherAssertionCreatorsOrNulls);
    return addAssertion_0($receiver, creators_0.iterableContainsAssertions.entriesInAnyOrderWithAssert_w7knt0$($receiver, assertionCreators));
  }
  function addAssertion_0($receiver, assertion) {
    return addAssertionForAssert($receiver, assertion);
  }
  function wert_3($receiver, expected) {
    return werte_3($receiver, expected, []);
  }
  function werte_3($receiver, expected, otherExpected) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    var expected_0 = varargToList(expected, otherExpected);
    return addAssertion_1($receiver, creators_0.iterableContainsAssertions.valuesInAnyOrderOnly_8257eh$($receiver, expected_0));
  }
  function eintrag_0($receiver, assertionCreatorOrNull) {
    return eintraege_0($receiver, assertionCreatorOrNull, []);
  }
  function eintraege_0($receiver, assertionCreatorOrNull, otherAssertionCreatorsOrNulls) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    var assertionCreators = varargToList(assertionCreatorOrNull, otherAssertionCreatorsOrNulls);
    return addAssertion_1($receiver, creators_0.iterableContainsAssertions.entriesInAnyOrderOnlyWithAssert_xcv0gv$($receiver, assertionCreators));
  }
  function addAssertion_1($receiver, assertion) {
    return addAssertionForAssert_0($receiver, assertion);
  }
  function wert_4($receiver, expected) {
    return werte_4($receiver, expected, []);
  }
  function werte_4($receiver, expected, otherExpected) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    var expected_0 = varargToList(expected, otherExpected);
    return addAssertion_1($receiver, creators_0.iterableContainsAssertions.valuesInOrderOnly_nkuvon$($receiver, expected_0));
  }
  function eintrag_1($receiver, assertionCreatorOrNull) {
    return eintraege_1($receiver, assertionCreatorOrNull, []);
  }
  function eintraege_1($receiver, assertionCreatorOrNull, otherAssertionCreatorsOrNulls) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    var assertionCreators = varargToList(assertionCreatorOrNull, otherAssertionCreatorsOrNulls);
    return addAssertion_1($receiver, creators_0.iterableContainsAssertions.entriesInOrderOnlyWithAssert_mxfmod$($receiver, assertionCreators));
  }
  function inBeliebigerReihenfolge($receiver, firstGroup, secondGroup, otherExpectedGroups) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    var groups = groupsToList(firstGroup, secondGroup, otherExpectedGroups);
    return addAssertion_1($receiver, creators_0.iterableContainsAssertions.valuesInOrderOnlyGrouped_qy4r6y$($receiver, groups));
  }
  function inBeliebigerReihenfolge_0($receiver, firstGroup, secondGroup, otherExpectedGroups) {
    creating.IterableAssertionsBuilder;
    creating.IterableContainsAssertionsBuilder;
    var groups = groupsToList(firstGroup, secondGroup, otherExpectedGroups);
    return addAssertion_1($receiver, creators_0.iterableContainsAssertions.entriesInOrderOnlyGroupedWithAssert_gua2de$($receiver, groups));
  }
  function get($receiver, index) {
    return creating.ListAssertionsBuilder.get_1agat1$($receiver, index);
  }
  function get_0($receiver, index, assertionCreator) {
    creating.ListAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.listAssertions.get_xq7pz1$($receiver, index, assertionCreator));
  }
  function get_1($receiver, index) {
    return creating.ListAssertionsBuilder.getNullable_t1641a$($receiver, index);
  }
  function enthaelt_3($receiver, entry, otherEntries) {
    creating.MapAssertionsBuilder;
    var keyValuePairs = varargToList(entry, otherEntries);
    return $receiver.addAssertion_94orq3$(creating_0.mapAssertions.contains_kz0a7c$($receiver, keyValuePairs));
  }
  function enthaelt_4($receiver, keyValue, otherKeyValues) {
    creating.MapAssertionsBuilder;
    var $receiver_0 = varargToList(keyValue, otherKeyValues);
    var destination = ArrayList_init(collectionSizeOrDefault($receiver_0, 10));
    var tmp$;
    tmp$ = $receiver_0.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(item.toPair());
    }
    return $receiver.addAssertion_94orq3$(creating_0.mapAssertions.containsKeyWithValueAssertions_qvj7tk$($receiver, destination));
  }
  function enthaeltKey($receiver, key) {
    creating.MapAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapAssertions.containsKey_l1dk5y$($receiver, key));
  }
  function enthaeltNichtKey($receiver, key) {
    creating.MapAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapAssertions.containsNotKey_l1dk5y$($receiver, key));
  }
  function getExistierend($receiver, key) {
    creating.MapAssertionsBuilder;
    return creating_0.mapAssertions.getExisting_9dg1yy$($receiver, key);
  }
  function getExistierend_0($receiver, key, assertionCreator) {
    creating.MapAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapAssertions.getExisting_99ax85$($receiver, key, assertionCreator));
  }
  function getExistierend_1($receiver, key) {
    creating.MapAssertionsBuilder;
    return creating_0.mapAssertions.getExistingNullable_x9kbod$($receiver, key);
  }
  function hatDieGroesse_0($receiver, size) {
    creating.MapAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapAssertions.hasSize_s812fp$($receiver, size));
  }
  function istLeer_1($receiver) {
    creating.MapAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapAssertions.isEmpty_miysk1$($receiver));
  }
  function istNichtLeer_1($receiver) {
    creating.MapAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapAssertions.isNotEmpty_miysk1$($receiver));
  }
  function get_keys($receiver) {
    return property_1($receiver, getPropertyCallableRef('keys', 1, function ($receiver) {
      return $receiver.keys;
    }));
  }
  function keys($receiver, assertionCreator) {
    creating.MapAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapAssertions.keys_q8smbf$($receiver, assertionCreator));
  }
  function get_values($receiver) {
    return property_1($receiver, getPropertyCallableRef('values', 1, function ($receiver) {
      return $receiver.values;
    }));
  }
  function values($receiver, assertionCreator) {
    creating.MapAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapAssertions.values_xld2cd$($receiver, assertionCreator));
  }
  function asEntries$lambda(it) {
    return it.entries;
  }
  function asEntries($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asEntries$lambda);
  }
  function asEntries_0($receiver, assertionCreator) {
    return asEntries($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function istKeyValue($receiver, key, value) {
    creating.MapAssertionsBuilder;
    creating.MapEntryAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapEntryAssertions.isKeyValue_yzs2t2$($receiver, key, value));
  }
  function get_key($receiver) {
    return property_1($receiver, getPropertyCallableRef('key', 1, function ($receiver) {
      return $receiver.key;
    }));
  }
  function get_key_0($receiver) {
    return property_7($receiver, getPropertyCallableRef('key', 1, function ($receiver) {
      return $receiver.key;
    }));
  }
  function key($receiver, assertionCreator) {
    creating.MapAssertionsBuilder;
    creating.MapEntryAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapEntryAssertions.key_ggy5ib$($receiver, assertionCreator));
  }
  function get_value($receiver) {
    return property_1($receiver, getPropertyCallableRef('value', 1, function ($receiver) {
      return $receiver.value;
    }));
  }
  function get_value_0($receiver) {
    return property_7($receiver, getPropertyCallableRef('value', 1, function ($receiver) {
      return $receiver.value;
    }));
  }
  function value($receiver, assertionCreator) {
    creating.MapAssertionsBuilder;
    creating.MapEntryAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.mapEntryAssertions.value_ql4mhr$($receiver, assertionCreator));
  }
  function get_first($receiver) {
    return property_1($receiver, getPropertyCallableRef('first', 1, function ($receiver) {
      return $receiver.first;
    }));
  }
  function get_first_0($receiver) {
    return property_7($receiver, getPropertyCallableRef('first', 1, function ($receiver) {
      return $receiver.first;
    }));
  }
  function first($receiver, assertionCreator) {
    creating.PairAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.pairAssertions.first_ct3cgu$($receiver, assertionCreator));
  }
  function get_second($receiver) {
    return property_1($receiver, getPropertyCallableRef('second', 1, function ($receiver) {
      return $receiver.second;
    }));
  }
  function get_second_0($receiver) {
    return property_7($receiver, getPropertyCallableRef('second', 1, function ($receiver) {
      return $receiver.second;
    }));
  }
  function second($receiver, assertionCreator) {
    creating.PairAssertionsBuilder;
    return $receiver.addAssertion_94orq3$(creating_0.pairAssertions.second_2owvhe$($receiver, assertionCreator));
  }
  function Eintrag(assertionCreator) {
    this.assertionCreator = assertionCreator;
  }
  Eintrag.prototype.toList = function () {
    return listOf(this.assertionCreator);
  };
  Eintrag.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Eintrag',
    interfaces: [GroupWithNullableEntries, GroupWithoutNullableEntries]
  };
  function Eintraege(assertionCreatorOrNull, otherAssertionCreatorsOrNulls) {
    this.assertionCreatorOrNull = assertionCreatorOrNull;
    this.otherAssertionCreatorsOrNulls = otherAssertionCreatorsOrNulls;
  }
  Object.defineProperty(Eintraege.prototype, 'expected', {
    get: function () {
      return this.assertionCreatorOrNull;
    }
  });
  Object.defineProperty(Eintraege.prototype, 'otherExpected', {
    get: function () {
      return this.otherAssertionCreatorsOrNulls;
    }
  });
  Eintraege.prototype.toList = function () {
    return varargToList(this.assertionCreatorOrNull, this.otherAssertionCreatorsOrNulls);
  };
  Eintraege.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Eintraege',
    interfaces: [VarArgHelper, GroupWithNullableEntries, GroupWithoutNullableEntries]
  };
  function KeyValue(key, valueAssertionCreatorOrNull) {
    this.key = key;
    this.valueAssertionCreatorOrNull = valueAssertionCreatorOrNull;
  }
  KeyValue.prototype.toPair = function () {
    return to(this.key, this.valueAssertionCreatorOrNull);
  };
  KeyValue.prototype.toString = function () {
    return 'KeyValue(key=' + this.key + ', value=' + (this.valueAssertionCreatorOrNull == null ? 'null' : 'lambda') + ')';
  };
  KeyValue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'KeyValue',
    interfaces: []
  };
  KeyValue.prototype.component1 = function () {
    return this.key;
  };
  KeyValue.prototype.component2 = function () {
    return this.valueAssertionCreatorOrNull;
  };
  KeyValue.prototype.copy_qdihzl$ = function (key, valueAssertionCreatorOrNull) {
    return new KeyValue(key === void 0 ? this.key : key, valueAssertionCreatorOrNull === void 0 ? this.valueAssertionCreatorOrNull : valueAssertionCreatorOrNull);
  };
  KeyValue.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.key) | 0;
    result = result * 31 + Kotlin.hashCode(this.valueAssertionCreatorOrNull) | 0;
    return result;
  };
  KeyValue.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.key, other.key) && Kotlin.equals(this.valueAssertionCreatorOrNull, other.valueAssertionCreatorOrNull)))));
  };
  function Wert(expected) {
    this.expected = expected;
  }
  Wert.prototype.toList = function () {
    return listOf(this.expected);
  };
  Wert.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Wert',
    interfaces: [GroupWithoutNullableEntries, GroupWithNullableEntries]
  };
  Wert.prototype.component1 = function () {
    return this.expected;
  };
  Wert.prototype.copy_11rb$ = function (expected) {
    return new Wert(expected === void 0 ? this.expected : expected);
  };
  Wert.prototype.toString = function () {
    return 'Wert(expected=' + Kotlin.toString(this.expected) + ')';
  };
  Wert.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.expected) | 0;
    return result;
  };
  Wert.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.expected, other.expected))));
  };
  function Werte(expected, otherExpected) {
    this.expected_qmcfra$_0 = expected;
    this.otherExpected_216g0q$_0 = otherExpected;
  }
  Object.defineProperty(Werte.prototype, 'expected', {
    get: function () {
      return this.expected_qmcfra$_0;
    }
  });
  Object.defineProperty(Werte.prototype, 'otherExpected', {
    get: function () {
      return this.otherExpected_216g0q$_0;
    }
  });
  Werte.prototype.toList = function () {
    return listOf_0([this.expected].concat(this.otherExpected));
  };
  Werte.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Werte',
    interfaces: [VarArgHelper, GroupWithNullableEntries, GroupWithoutNullableEntries]
  };
  function asIterable$lambda_8(it) {
    return asIterable_8(it);
  }
  function asIterable_27($receiver) {
    return SubjectChangerBuilder.Companion.create_dcyqpd$($receiver).unreported_el91ig$(asIterable$lambda_8);
  }
  var wirft = defineInlineFunction('atrium-api-cc-de_CH-js.ch.tutteli.atrium.api.cc.de_CH.wirft_pim048$', wrapFunction(function () {
    var getKClass = Kotlin.getKClass;
    var creating = _.$$importsForInline$$['atrium-domain-builders-js'].ch.tutteli.atrium.domain.builders.creating;
    var creators = _.$$importsForInline$$['atrium-domain-builders-js'].$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.throwable.thrown.creators;
    return function (TExpected_0, isTExpected, $receiver, assertionCreator) {
      creating.ThrowableAssertionsBuilder;
      creating.ThrowableThrownAssertionsBuilder;
      var expectedType = getKClass(TExpected_0);
      creators.throwableThrownAssertions.toBe_vdu9lw$($receiver, expectedType, assertionCreator);
    };
  }));
  function wirftNichts($receiver) {
    creating.ThrowableAssertionsBuilder;
    creating.ThrowableThrownAssertionsBuilder;
    creators_1.throwableThrownAssertions.nothingThrown_sruzb2$($receiver);
  }
  function message($receiver, assertionCreator) {
    var $receiver_0 = property_7($receiver, getPropertyCallableRef('message', 1, function ($receiver) {
      return $receiver.message;
    }));
    creating.AnyAssertionsBuilder;
    var type = PrimitiveClasses$stringClass;
    $receiver_0.addAssertion_94orq3$(creating_0.anyAssertions.isNotNull_u0xxoi$($receiver_0, type, assertionCreator));
  }
  function messageEnthaelt$lambda(closure$expected, closure$otherExpected) {
    return function ($receiver) {
      enthaelt($receiver, closure$expected, closure$otherExpected.slice());
      return Unit;
    };
  }
  function messageEnthaelt($receiver, expected, otherExpected) {
    message($receiver, messageEnthaelt$lambda(expected, otherExpected));
  }
  var istNichtNull = defineInlineFunction('atrium-api-cc-de_CH-js.ch.tutteli.atrium.api.cc.de_CH.istNichtNull_8aftgm$', wrapFunction(function () {
    var getKClass = Kotlin.getKClass;
    var creating = _.$$importsForInline$$['atrium-domain-builders-js'].ch.tutteli.atrium.domain.builders.creating;
    var creating_0 = _.$$importsForInline$$['atrium-domain-builders-js'].$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (T_0, isT, $receiver, assertionCreator) {
      creating.AnyAssertionsBuilder;
      var type = getKClass(T_0);
      $receiver.addAssertion_94orq3$(creating_0.anyAssertions.isNotNull_u0xxoi$($receiver, type, assertionCreator));
    };
  }));
  var istEin = defineInlineFunction('atrium-api-cc-de_CH-js.ch.tutteli.atrium.api.cc.de_CH.istEin_lplxq6$', wrapFunction(function () {
    var getKClass = Kotlin.getKClass;
    var creating = _.$$importsForInline$$['atrium-domain-builders-js'].ch.tutteli.atrium.domain.builders.creating;
    var creators = _.$$importsForInline$$['atrium-domain-builders-js'].$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.any.typetransformation.creators;
    return function (TSub_0, isTSub, $receiver, assertionCreator) {
      creating.AnyAssertionsBuilder;
      creating.AnyTypeTransformationAssertionsBuilder;
      var subType = getKClass(TSub_0);
      creators.anyTypeTransformationAssertions.isA_e0ept2$($receiver, subType, assertionCreator);
    };
  }));
  $$importsForInline$$['atrium-domain-builders-js'] = $module$atrium_domain_builders_js;
  var package$ch = _.ch || (_.ch = {});
  var package$tutteli = package$ch.tutteli || (package$ch.tutteli = {});
  var package$atrium = package$tutteli.atrium || (package$tutteli.atrium = {});
  var package$api = package$atrium.api || (package$atrium.api = {});
  var package$cc = package$api.cc || (package$api.cc = {});
  var package$de_CH = package$cc.de_CH || (package$cc.de_CH = {});
  package$de_CH.ist_fyhk31$ = ist;
  package$de_CH.istNicht_fyhk31$ = istNicht;
  package$de_CH.istSelbeInstanzWie_fyhk31$ = istSelbeInstanzWie;
  package$de_CH.istNichtSelbeInstanzWie_fyhk31$ = istNichtSelbeInstanzWie;
  package$de_CH.get_und_9p701z$ = get_und;
  package$de_CH.und_qy1d0l$ = und;
  package$de_CH.asIterable_hpp7q3$ = asIterable_9;
  package$de_CH.asIterable_9pd2ac$ = asIterable_10;
  package$de_CH.asIterable_jucohg$ = asIterable_11;
  package$de_CH.asIterable_nmvgs8$ = asIterable_12;
  package$de_CH.asIterable_oxjpj2$ = asIterable_13;
  package$de_CH.asIterable_6pi7dw$ = asIterable_14;
  package$de_CH.asIterable_kwm0ec$ = asIterable_15;
  package$de_CH.asIterable_73z630$ = asIterable_16;
  package$de_CH.asIterable_rxlz2n$ = asIterable_17;
  package$de_CH.asIterable_txq7fw$ = asIterable_18;
  package$de_CH.asIterable_ni32u0$ = asIterable_19;
  package$de_CH.asIterable_nrtvio$ = asIterable_20;
  package$de_CH.asIterable_rafijo$ = asIterable_21;
  package$de_CH.asIterable_u17vkc$ = asIterable_22;
  package$de_CH.asIterable_ut3qg3$ = asIterable_23;
  package$de_CH.asIterable_w5ekyi$ = asIterable_24;
  package$de_CH.asIterable_wleyu0$ = asIterable_25;
  package$de_CH.asIterable_f9p8c4$ = asIterable_26;
  package$de_CH.get_enthaelt_eovgxw$ = get_enthaelt;
  package$de_CH.get_enthaeltNicht_eovgxw$ = get_enthaeltNicht;
  package$de_CH.enthaelt_b3libv$ = enthaelt;
  package$de_CH.enthaeltNicht_b3libv$ = enthaeltNicht;
  package$de_CH.enthaeltRegex_1wkhgb$ = enthaeltRegex;
  package$de_CH.beginntMit_u663l4$ = beginntMit;
  package$de_CH.beginntNichtMit_u663l4$ = beginntNichtMit;
  package$de_CH.endetMit_u663l4$ = endetMit;
  package$de_CH.endetNichtMit_u663l4$ = endetNichtMit;
  package$de_CH.istLeer_eovgxw$ = istLeer;
  package$de_CH.istNichtLeer_eovgxw$ = istNichtLeer;
  package$de_CH.istNichtBlank_eovgxw$ = istNichtBlank;
  package$de_CH.zumindest_ea4pd7$ = zumindest;
  package$de_CH.aberHoechstens_qol3qd$ = aberHoechstens;
  package$de_CH.genau_ea4pd7$ = genau;
  package$de_CH.hoechstens_ea4pd7$ = hoechstens;
  package$de_CH.nichtOderHoechstens_ea4pd7$ = nichtOderHoechstens;
  package$de_CH.wert_m3m317$ = wert;
  $$importsForInline$$['kbox-js'] = $module$kbox_js;
  package$de_CH.werte_gz31o3$ = werte;
  package$de_CH.wert_o6wcn6$ = wert_0;
  package$de_CH.werte_9h9sk0$ = werte_0;
  package$de_CH.wert_76a7rn$ = wert_1;
  package$de_CH.werte_mzfyvz$ = werte_1;
  package$de_CH.regex_5inwjn$ = regex;
  package$de_CH.regex_ogsv5s$ = regex_0;
  package$de_CH.regex_6u5w27$ = regex_1;
  package$de_CH.get_ignoriereGrossKleinschreibung_6u63ed$ = get_ignoriereGrossKleinschreibung;
  package$de_CH.get_ignoriereGrossKleinschreibung_3jbiux$ = get_ignoriereGrossKleinschreibung_0;
  package$de_CH.hatDieGroesse_yfsfje$ = hatDieGroesse;
  package$de_CH.istLeer_z1hifg$ = istLeer_0;
  package$de_CH.istNichtLeer_z1hifg$ = istNichtLeer_0;
  package$de_CH.get_size_z55mks$ = get_size;
  package$de_CH.size_yfwjcp$ = size;
  package$de_CH.istKleinerAls_yysd4y$ = istKleinerAls;
  package$de_CH.istKleinerOderGleich_yysd4y$ = istKleinerOderGleich;
  package$de_CH.istGroesserAls_yysd4y$ = istGroesserAls;
  package$de_CH.istGroesserOderGleich_yysd4y$ = istGroesserOderGleich;
  var package$creating = package$de_CH.creating || (package$de_CH.creating = {});
  var package$charsequence = package$creating.charsequence || (package$creating.charsequence = {});
  var package$contains = package$charsequence.contains || (package$charsequence.contains = {});
  var package$builders = package$contains.builders || (package$contains.builders = {});
  package$builders.AtLeastCheckerOption = AtLeastCheckerOption;
  package$builders.AtMostCheckerOption = AtMostCheckerOption;
  package$builders.ButAtMostCheckerOption = ButAtMostCheckerOption;
  package$builders.ExactlyCheckerOption = ExactlyCheckerOption;
  package$builders.NotCheckerOption = NotCheckerOption;
  package$builders.NotOrAtMostCheckerOption = NotOrAtMostCheckerOption;
  var package$impl = package$builders.impl || (package$builders.impl = {});
  package$impl.AtLeastCheckerOptionImpl = AtLeastCheckerOptionImpl;
  package$impl.AtMostCheckerOptionImpl = AtMostCheckerOptionImpl;
  package$impl.ButAtMostCheckerOptionImpl = ButAtMostCheckerOptionImpl;
  package$impl.ExactlyCheckerOptionImpl = ExactlyCheckerOptionImpl;
  package$impl.NotCheckerOptionImpl = NotCheckerOptionImpl;
  package$impl.NotOrAtMostCheckerOptionImpl = NotOrAtMostCheckerOptionImpl;
  package$impl.nameContainsNotValuesFun_8be2vx$ = nameContainsNotValuesFun;
  var package$iterable = package$creating.iterable || (package$creating.iterable = {});
  var package$contains_0 = package$iterable.contains || (package$iterable.contains = {});
  var package$builders_0 = package$contains_0.builders || (package$contains_0.builders = {});
  package$builders_0.AtLeastCheckerOption = AtLeastCheckerOption_0;
  package$builders_0.AtMostCheckerOption = AtMostCheckerOption_0;
  package$builders_0.ButAtMostCheckerOption = ButAtMostCheckerOption_0;
  package$builders_0.ExactlyCheckerOption = ExactlyCheckerOption_0;
  package$builders_0.NotCheckerOption = NotCheckerOption_0;
  package$builders_0.NotOrAtMostCheckerOption = NotOrAtMostCheckerOption_0;
  var package$impl_0 = package$builders_0.impl || (package$builders_0.impl = {});
  package$impl_0.AtLeastCheckerOptionImpl = AtLeastCheckerOptionImpl_0;
  package$impl_0.AtMostCheckerOptionImpl = AtMostCheckerOptionImpl_0;
  package$impl_0.ButAtMostCheckerOptionImpl = ButAtMostCheckerOptionImpl_0;
  package$impl_0.ExactlyCheckerOptionImpl = ExactlyCheckerOptionImpl_0;
  package$impl_0.NotCheckerOptionImpl = NotCheckerOptionImpl_0;
  package$impl_0.NotOrAtMostCheckerOptionImpl = NotOrAtMostCheckerOptionImpl_0;
  package$impl_0.nameContainsNotValuesFun_8be2vx$ = nameContainsNotValuesFun_0;
  package$de_CH.property_1k46b1$ = property;
  package$de_CH.property_cz59m3$ = property_0;
  package$de_CH.property_s5il82$ = property_1;
  package$de_CH.property_9w3198$ = property_2;
  package$de_CH.property_pa96x0$ = property_3;
  package$de_CH.property_2lq7dr$ = property_4;
  package$de_CH.property_q1gwqe$ = property_5;
  package$de_CH.property_gdvqlu$ = property_6;
  package$de_CH.property_wex6fj$ = property_7;
  package$de_CH.rueckgabewertVon_i8v3o0$ = rueckgabewertVon;
  package$de_CH.rueckgabewertVon_3plnqw$ = rueckgabewertVon_0;
  package$de_CH.rueckgabewertVon_niiuke$ = rueckgabewertVon_1;
  package$de_CH.rueckgabewertVon_q05unz$ = rueckgabewertVon_2;
  package$de_CH.rueckgabewertVon_tms1nd$ = rueckgabewertVon_3;
  package$de_CH.rueckgabewertVon_it5uy9$ = rueckgabewertVon_4;
  package$de_CH.rueckgabewertVon_ky81t9$ = rueckgabewertVon_5;
  package$de_CH.rueckgabewertVon_7njctn$ = rueckgabewertVon_6;
  package$de_CH.rueckgabewertVon_gehopt$ = rueckgabewertVon_7;
  package$de_CH.rueckgabewertVon_dzgal1$ = rueckgabewertVon_8;
  package$de_CH.rueckgabewertVon_x8fmil$ = rueckgabewertVon_9;
  package$de_CH.rueckgabewertVon_10jy53$ = rueckgabewertVon_10;
  package$de_CH.rueckgabewertVon_6ttjnf$ = rueckgabewertVon_11;
  package$de_CH.rueckgabewertVon_q9z4ib$ = rueckgabewertVon_12;
  package$de_CH.rueckgabewertVon_js25uh$ = rueckgabewertVon_13;
  package$de_CH.rueckgabewertVon_u48l3c$ = rueckgabewertVon_14;
  package$de_CH.rueckgabewertVon_5ps7fk$ = rueckgabewertVon_15;
  package$de_CH.rueckgabewertVon_4n3ggc$ = rueckgabewertVon_16;
  package$de_CH.rueckgabewertVon_7qo5y9$ = rueckgabewertVon_17;
  package$de_CH.rueckgabewertVon_l4nu2v$ = rueckgabewertVon_18;
  package$de_CH.rueckgabewertVon_to1rbp$ = rueckgabewertVon_19;
  package$de_CH.rueckgabewertVon_iky9io$ = rueckgabewertVon_20;
  package$de_CH.rueckgabewertVon_ib0waw$ = rueckgabewertVon_21;
  package$de_CH.rueckgabewertVon_cf8qi$ = rueckgabewertVon_22;
  package$de_CH.rueckgabewertVon_gn5qnw$ = rueckgabewertVon_23;
  package$de_CH.rueckgabewertVon_bud3fg$ = rueckgabewertVon_24;
  package$de_CH.rueckgabewertVon_fhe4ge$ = rueckgabewertVon_25;
  package$de_CH.rueckgabewertVon_owys7u$ = rueckgabewertVon_26;
  package$de_CH.rueckgabewertVon_jn7l82$ = rueckgabewertVon_27;
  package$de_CH.rueckgabewertVon_83du0y$ = rueckgabewertVon_28;
  package$de_CH.rueckgabewertVon_x82j2s$ = rueckgabewertVon_29;
  package$de_CH.rueckgabewertVon_l1luf0$ = rueckgabewertVon_30;
  package$de_CH.rueckgabewertVon_mkjc4s$ = rueckgabewertVon_31;
  package$de_CH.rueckgabewertVon_40arsn$ = rueckgabewertVon_32;
  package$de_CH.rueckgabewertVon_oetwxd$ = rueckgabewertVon_33;
  package$de_CH.rueckgabewertVon_6dnxsx$ = rueckgabewertVon_34;
  package$de_CH.rueckgabewertVon_icxu1y$ = rueckgabewertVon_35;
  package$de_CH.rueckgabewertVon_ugbvqq$ = rueckgabewertVon_36;
  package$de_CH.rueckgabewertVon_x61l5s$ = rueckgabewertVon_37;
  package$de_CH.rueckgabewertVon_1uu7fd$ = rueckgabewertVon_38;
  package$de_CH.rueckgabewertVon_c5dn41$ = rueckgabewertVon_39;
  package$de_CH.rueckgabewertVon_dthafn$ = rueckgabewertVon_40;
  package$de_CH.rueckgabewertVon_9613yb$ = rueckgabewertVon_41;
  package$de_CH.rueckgabewertVon_c65swl$ = rueckgabewertVon_42;
  package$de_CH.rueckgabewertVon_x2v8oz$ = rueckgabewertVon_43;
  package$de_CH.rueckgabewertVon_5xzz9d$ = rueckgabewertVon_44;
  package$de_CH.rueckgabewertVon_cto0dj$ = rueckgabewertVon_45;
  package$de_CH.rueckgabewertVon_3a5jx7$ = rueckgabewertVon_46;
  package$de_CH.rueckgabewertVon_fgzo4r$ = rueckgabewertVon_47;
  package$de_CH.rueckgabewertVon_fk9p8z$ = rueckgabewertVon_48;
  package$de_CH.rueckgabewertVon_qp5hvd$ = rueckgabewertVon_49;
  package$de_CH.rueckgabewertVon_4yorj6$ = rueckgabewertVon_50;
  package$de_CH.rueckgabewertVon_qiotqi$ = rueckgabewertVon_51;
  package$de_CH.rueckgabewertVon_efrk8y$ = rueckgabewertVon_52;
  package$de_CH.istMitFehlerToleranz_mi4utn$ = istMitFehlerToleranz;
  package$de_CH.istMitFehlerToleranz_9ag4zs$ = istMitFehlerToleranz_0;
  package$de_CH.get_enthaelt_sjwp3f$ = get_enthaelt_0;
  package$de_CH.get_enthaeltNicht_sjwp3f$ = get_enthaeltNicht_0;
  package$de_CH.enthaelt_7kxdom$ = enthaelt_0;
  package$de_CH.enthaelt_26t6yl$ = enthaelt_1;
  package$de_CH.enthaelt_53iuzh$ = enthaelt_2;
  package$de_CH.enthaeltExakt_7kxdom$ = enthaeltExakt;
  package$de_CH.enthaeltExakt_26t6yl$ = enthaeltExakt_0;
  package$de_CH.enthaeltExakt_53iuzh$ = enthaeltExakt_1;
  package$de_CH.enthaeltNicht_7kxdom$ = enthaeltNicht_0;
  package$de_CH.irgendEiner_26t6yl$ = irgendEiner;
  package$de_CH.keiner_26t6yl$ = keiner;
  package$de_CH.alle_26t6yl$ = alle;
  package$de_CH.zumindest_1em10v$ = zumindest_0;
  package$de_CH.aberHoechstens_sqg55e$ = aberHoechstens_0;
  package$de_CH.genau_1em10v$ = genau_0;
  package$de_CH.hoechstens_1em10v$ = hoechstens_0;
  package$de_CH.nichtOderHoechstens_1em10v$ = nichtOderHoechstens_0;
  package$de_CH.get_inBeliebigerReihenfolge_q2rm44$ = get_inBeliebigerReihenfolge;
  package$de_CH.get_nur_g7g2i7$ = get_nur;
  package$de_CH.get_inGegebenerReihenfolge_q2rm44$ = get_inGegebenerReihenfolge;
  package$de_CH.get_nur_s2hhoh$ = get_nur_0;
  package$de_CH.get_gruppiert_mz1dh1$ = get_gruppiert;
  package$de_CH.get_innerhalb_un39u5$ = get_innerhalb;
  package$de_CH.wert_eaisei$ = wert_2;
  package$de_CH.werte_f0fymx$ = werte_2;
  package$de_CH.eintrag_hi4wfq$ = eintrag;
  package$de_CH.eintraege_23s9xs$ = eintraege;
  package$de_CH.wert_ku03u9$ = wert_3;
  package$de_CH.werte_smg46a$ = werte_3;
  package$de_CH.eintrag_rf7r51$ = eintrag_0;
  package$de_CH.eintraege_i33d57$ = eintraege_0;
  package$de_CH.addAssertion_a7urqr$ = addAssertion_1;
  package$de_CH.wert_yeswx$ = wert_4;
  package$de_CH.werte_gfniz4$ = werte_4;
  package$de_CH.eintrag_4tvq4z$ = eintrag_1;
  package$de_CH.eintraege_wyu7t$ = eintraege_1;
  package$de_CH.inBeliebigerReihenfolge_toha76$ = inBeliebigerReihenfolge;
  package$de_CH.inBeliebigerReihenfolge_52czo$ = inBeliebigerReihenfolge_0;
  package$de_CH.get_h2kcw8$ = get;
  package$de_CH.get_g15992$ = get_0;
  package$de_CH.get_f0v1dn$ = get_1;
  package$de_CH.enthaelt_f8ry8v$ = enthaelt_3;
  package$de_CH.enthaelt_c3ghui$ = enthaelt_4;
  package$de_CH.enthaeltKey_jxqh5$ = enthaeltKey;
  package$de_CH.enthaeltNichtKey_jxqh5$ = enthaeltNichtKey;
  package$de_CH.getExistierend = getExistierend;
  package$de_CH.getExistierend_zc06nu$ = getExistierend_0;
  package$de_CH.getExistierend_myaa0k$ = getExistierend_1;
  package$de_CH.hatDieGroesse_rlwr0y$ = hatDieGroesse_0;
  package$de_CH.istLeer_8s025w$ = istLeer_1;
  package$de_CH.istNichtLeer_8s025w$ = istNichtLeer_1;
  package$de_CH.get_keys_z5h5m9$ = get_keys;
  package$de_CH.keys_5gi86v$ = keys;
  package$de_CH.get_values_z5h5m9$ = get_values;
  package$de_CH.values_ji70qo$ = values;
  package$de_CH.asEntries_altgpi$ = asEntries;
  package$de_CH.asEntries_jf1a28$ = asEntries_0;
  package$de_CH.istKeyValue_yjvu3x$ = istKeyValue;
  package$de_CH.get_key_u5iqoi$ = get_key;
  package$de_CH.get_key_vq1tqj$ = get_key_0;
  package$de_CH.key_si82od$ = key;
  package$de_CH.get_value_ie48s2$ = get_value;
  package$de_CH.get_value_ahyv2f$ = get_value_0;
  package$de_CH.value_u9deqa$ = value;
  package$de_CH.get_first_y9k48h$ = get_first;
  package$de_CH.get_first_r6m446$ = get_first_0;
  package$de_CH.first_1w3qb2$ = first;
  package$de_CH.get_second_ea2v83$ = get_second;
  package$de_CH.get_second_mmez6u$ = get_second_0;
  package$de_CH.second_e5mavj$ = second;
  package$de_CH.Eintrag = Eintrag;
  package$de_CH.Eintraege = Eintraege;
  package$de_CH.KeyValue = KeyValue;
  package$de_CH.Wert = Wert;
  package$de_CH.Werte = Werte;
  package$de_CH.asIterable_a1l1bf$ = asIterable_27;
  package$de_CH.wirftNichts_vesb4f$ = wirftNichts;
  $$importsForInline$$['atrium-api-cc-de_CH-js'] = _;
  package$de_CH.message_y7xogv$ = message;
  package$de_CH.messageEnthaelt_5qe43m$ = messageEnthaelt;
  Object.defineProperty(Eintraege.prototype, 'mapArguments', Object.getOwnPropertyDescriptor(VarArgHelper.prototype, 'mapArguments'));
  Object.defineProperty(Werte.prototype, 'mapArguments', Object.getOwnPropertyDescriptor(VarArgHelper.prototype, 'mapArguments'));
  Kotlin.defineModule('atrium-api-cc-de_CH-js', _);
  return _;
}));

//# sourceMappingURL=atrium-api-cc-de_CH-js.js.map
