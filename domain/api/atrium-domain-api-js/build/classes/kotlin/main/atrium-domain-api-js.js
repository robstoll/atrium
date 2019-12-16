(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'atrium-core-api-js'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('atrium-core-api-js'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'atrium-domain-api-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'atrium-domain-api-js'.");
    }
    if (typeof this['atrium-core-api-js'] === 'undefined') {
      throw new Error("Error loading module 'atrium-domain-api-js'. Its dependency 'atrium-core-api-js' was not found. Please, check whether 'atrium-core-api-js' is loaded prior to 'atrium-domain-api-js'.");
    }
    root['atrium-domain-api-js'] = factory(typeof this['atrium-domain-api-js'] === 'undefined' ? {} : this['atrium-domain-api-js'], kotlin, this['atrium-core-api-js']);
  }
}(this, function (_, Kotlin, $module$atrium_core_api_js) {
  'use strict';
  var getKClass = Kotlin.getKClass;
  var loadSingleService = $module$atrium_core_api_js.ch.tutteli.atrium.core.polyfills.loadSingleService_lmshww$;
  var lazy = Kotlin.kotlin.lazy_klfg04$;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Untranslatable = $module$atrium_core_api_js.ch.tutteli.atrium.reporting.translating.Untranslatable;
  var Some = $module$atrium_core_api_js.ch.tutteli.atrium.core.Some;
  var Unit = Kotlin.kotlin.Unit;
  var core = $module$atrium_core_api_js.ch.tutteli.atrium.core;
  var getCallableRef = Kotlin.getCallableRef;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Untranslatable_init = $module$atrium_core_api_js.ch.tutteli.atrium.reporting.translating.Untranslatable_init_h4ejuu$;
  ChangedSubjectPostStep.prototype = Object.create(PostFinalStep.prototype);
  ChangedSubjectPostStep.prototype.constructor = ChangedSubjectPostStep;
  ExtractedFeaturePostStep.prototype = Object.create(PostFinalStep.prototype);
  ExtractedFeaturePostStep.prototype.constructor = ExtractedFeaturePostStep;
  function assertionComposer$lambda() {
    return loadSingleService(getKClass(AssertionComposer));
  }
  var assertionComposer;
  function get_assertionComposer() {
    return assertionComposer.value;
  }
  function AssertionComposer() {
  }
  AssertionComposer.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionComposer',
    interfaces: []
  };
  function anyAssertions$lambda() {
    return loadSingleService(getKClass(AnyAssertions));
  }
  var anyAssertions;
  function get_anyAssertions() {
    return anyAssertions.value;
  }
  function AnyAssertions() {
  }
  AnyAssertions.prototype.notToBeNull_e1rtsw$ = function (assertionContainer, subType) {
    return this.isA_jdrxfq$(assertionContainer, subType);
  };
  AnyAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AnyAssertions',
    interfaces: []
  };
  function charSequenceAssertions$lambda() {
    return loadSingleService(getKClass(CharSequenceAssertions));
  }
  var charSequenceAssertions;
  function get_charSequenceAssertions() {
    return charSequenceAssertions.value;
  }
  function CharSequenceAssertions() {
  }
  CharSequenceAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CharSequenceAssertions',
    interfaces: []
  };
  function collectionAssertions$lambda() {
    return loadSingleService(getKClass(CollectionAssertions));
  }
  var collectionAssertions;
  function get_collectionAssertions() {
    return collectionAssertions.value;
  }
  function CollectionAssertions() {
  }
  CollectionAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CollectionAssertions',
    interfaces: []
  };
  function comparableAssertions$lambda() {
    return loadSingleService(getKClass(ComparableAssertions));
  }
  var comparableAssertions;
  function get_comparableAssertions() {
    return comparableAssertions.value;
  }
  function ComparableAssertions() {
  }
  ComparableAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ComparableAssertions',
    interfaces: []
  };
  function featureAssertions$lambda() {
    return loadSingleService(getKClass(FeatureAssertions));
  }
  var featureAssertions;
  function get_featureAssertions() {
    return featureAssertions.value;
  }
  function FeatureAssertions() {
  }
  FeatureAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FeatureAssertions',
    interfaces: []
  };
  function floatingPointAssertions$lambda() {
    return loadSingleService(getKClass(FloatingPointAssertions));
  }
  var floatingPointAssertions;
  function get_floatingPointAssertions() {
    return floatingPointAssertions.value;
  }
  function FloatingPointAssertionsCommon() {
  }
  FloatingPointAssertionsCommon.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FloatingPointAssertionsCommon',
    interfaces: []
  };
  function iterableAssertions$lambda() {
    return loadSingleService(getKClass(IterableAssertions));
  }
  var iterableAssertions;
  function get_iterableAssertions() {
    return iterableAssertions.value;
  }
  function IterableAssertions() {
  }
  IterableAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IterableAssertions',
    interfaces: []
  };
  function listAssertions$lambda() {
    return loadSingleService(getKClass(ListAssertions));
  }
  var listAssertions;
  function get_listAssertions() {
    return listAssertions.value;
  }
  function ListAssertions() {
  }
  ListAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ListAssertions',
    interfaces: []
  };
  function mapAssertions$lambda() {
    return loadSingleService(getKClass(MapAssertions));
  }
  var mapAssertions;
  function get_mapAssertions() {
    return mapAssertions.value;
  }
  function MapAssertions() {
  }
  MapAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'MapAssertions',
    interfaces: []
  };
  function mapEntryAssertions$lambda() {
    return loadSingleService(getKClass(MapEntryAssertions));
  }
  var mapEntryAssertions;
  function get_mapEntryAssertions() {
    return mapEntryAssertions.value;
  }
  function MapEntryAssertions() {
  }
  MapEntryAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'MapEntryAssertions',
    interfaces: []
  };
  function newFeatureAssertions$lambda() {
    return loadSingleService(getKClass(NewFeatureAssertions));
  }
  var newFeatureAssertions;
  function get_newFeatureAssertions() {
    return newFeatureAssertions.value;
  }
  function NewFeatureAssertions() {
  }
  NewFeatureAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NewFeatureAssertions',
    interfaces: []
  };
  function MetaFeature(description, representation, maybeSubject) {
    this.description = description;
    this.representation = representation;
    this.maybeSubject = maybeSubject;
  }
  MetaFeature.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MetaFeature',
    interfaces: []
  };
  function MetaFeature_init(description, representation, maybeSubject, $this) {
    $this = $this || Object.create(MetaFeature.prototype);
    MetaFeature.call($this, new Untranslatable(description), representation, maybeSubject);
    return $this;
  }
  function MetaFeature_init_0(description, subject, $this) {
    $this = $this || Object.create(MetaFeature.prototype);
    MetaFeature_init(description, subject, new Some(subject), $this);
    return $this;
  }
  MetaFeature.prototype.component1 = function () {
    return this.description;
  };
  MetaFeature.prototype.component2 = function () {
    return this.representation;
  };
  MetaFeature.prototype.component3 = function () {
    return this.maybeSubject;
  };
  MetaFeature.prototype.copy_k50uiu$ = function (description, representation, maybeSubject) {
    return new MetaFeature(description === void 0 ? this.description : description, representation === void 0 ? this.representation : representation, maybeSubject === void 0 ? this.maybeSubject : maybeSubject);
  };
  MetaFeature.prototype.toString = function () {
    return 'MetaFeature(description=' + Kotlin.toString(this.description) + (', representation=' + Kotlin.toString(this.representation)) + (', maybeSubject=' + Kotlin.toString(this.maybeSubject)) + ')';
  };
  MetaFeature.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.description) | 0;
    result = result * 31 + Kotlin.hashCode(this.representation) | 0;
    result = result * 31 + Kotlin.hashCode(this.maybeSubject) | 0;
    return result;
  };
  MetaFeature.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.description, other.description) && Kotlin.equals(this.representation, other.representation) && Kotlin.equals(this.maybeSubject, other.maybeSubject)))));
  };
  function pairAssertions$lambda() {
    return loadSingleService(getKClass(PairAssertions));
  }
  var pairAssertions;
  function get_pairAssertions() {
    return pairAssertions.value;
  }
  function PairAssertions() {
  }
  PairAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'PairAssertions',
    interfaces: []
  };
  function throwableAssertions$lambda() {
    return loadSingleService(getKClass(ThrowableAssertions));
  }
  var throwableAssertions;
  function get_throwableAssertions() {
    return throwableAssertions.value;
  }
  function ThrowableAssertions() {
  }
  ThrowableAssertions.prototype.thrownBuilder_996pa$ = function (assertionVerb, act, reporter) {
    return this.thrownBuilder_1nnvc6$(new Untranslatable(assertionVerb), act, reporter);
  };
  ThrowableAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ThrowableAssertions',
    interfaces: []
  };
  function AnyTypeTransformation() {
  }
  function AnyTypeTransformation$Creator() {
  }
  AnyTypeTransformation$Creator.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Creator',
    interfaces: []
  };
  function AnyTypeTransformation$FailureHandler() {
  }
  AnyTypeTransformation$FailureHandler.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FailureHandler',
    interfaces: []
  };
  function AnyTypeTransformation$ParameterObject(description, representation, subjectPlant, assertionCreator, warningTransformationFailed) {
    this.description = description;
    this.representation = representation;
    this.subjectPlant = subjectPlant;
    this.assertionCreator = assertionCreator;
    this.warningTransformationFailed = warningTransformationFailed;
  }
  AnyTypeTransformation$ParameterObject.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParameterObject',
    interfaces: []
  };
  AnyTypeTransformation$ParameterObject.prototype.component1 = function () {
    return this.description;
  };
  AnyTypeTransformation$ParameterObject.prototype.component2 = function () {
    return this.representation;
  };
  AnyTypeTransformation$ParameterObject.prototype.component3 = function () {
    return this.subjectPlant;
  };
  AnyTypeTransformation$ParameterObject.prototype.component4 = function () {
    return this.assertionCreator;
  };
  AnyTypeTransformation$ParameterObject.prototype.component5 = function () {
    return this.warningTransformationFailed;
  };
  AnyTypeTransformation$ParameterObject.prototype.copy_oj6ey9$ = function (description, representation, subjectPlant, assertionCreator, warningTransformationFailed) {
    return new AnyTypeTransformation$ParameterObject(description === void 0 ? this.description : description, representation === void 0 ? this.representation : representation, subjectPlant === void 0 ? this.subjectPlant : subjectPlant, assertionCreator === void 0 ? this.assertionCreator : assertionCreator, warningTransformationFailed === void 0 ? this.warningTransformationFailed : warningTransformationFailed);
  };
  AnyTypeTransformation$ParameterObject.prototype.toString = function () {
    return 'ParameterObject(description=' + Kotlin.toString(this.description) + (', representation=' + Kotlin.toString(this.representation)) + (', subjectPlant=' + Kotlin.toString(this.subjectPlant)) + (', assertionCreator=' + Kotlin.toString(this.assertionCreator)) + (', warningTransformationFailed=' + Kotlin.toString(this.warningTransformationFailed)) + ')';
  };
  AnyTypeTransformation$ParameterObject.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.description) | 0;
    result = result * 31 + Kotlin.hashCode(this.representation) | 0;
    result = result * 31 + Kotlin.hashCode(this.subjectPlant) | 0;
    result = result * 31 + Kotlin.hashCode(this.assertionCreator) | 0;
    result = result * 31 + Kotlin.hashCode(this.warningTransformationFailed) | 0;
    return result;
  };
  AnyTypeTransformation$ParameterObject.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.description, other.description) && Kotlin.equals(this.representation, other.representation) && Kotlin.equals(this.subjectPlant, other.subjectPlant) && Kotlin.equals(this.assertionCreator, other.assertionCreator) && Kotlin.equals(this.warningTransformationFailed, other.warningTransformationFailed)))));
  };
  AnyTypeTransformation.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AnyTypeTransformation',
    interfaces: []
  };
  function anyTypeTransformationAssertions$lambda() {
    return loadSingleService(getKClass(AnyTypeTransformationAssertions));
  }
  var anyTypeTransformationAssertions;
  function get_anyTypeTransformationAssertions() {
    return anyTypeTransformationAssertions.value;
  }
  function AnyTypeTransformationAssertions() {
  }
  AnyTypeTransformationAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AnyTypeTransformationAssertions',
    interfaces: []
  };
  function failureHandlerFactory$lambda() {
    return loadSingleService(getKClass(FailureHandlerFactory));
  }
  var failureHandlerFactory;
  function get_failureHandlerFactory() {
    return failureHandlerFactory.value;
  }
  function FailureHandlerFactory() {
  }
  FailureHandlerFactory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FailureHandlerFactory',
    interfaces: []
  };
  function Contains() {
  }
  function Contains$Builder() {
  }
  Contains$Builder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Builder',
    interfaces: []
  };
  function Contains$CheckerOption() {
  }
  Contains$CheckerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CheckerOption',
    interfaces: []
  };
  function Contains$SearchBehaviour() {
  }
  Contains$SearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SearchBehaviour',
    interfaces: []
  };
  function Contains$Checker() {
  }
  Contains$Checker.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Checker',
    interfaces: []
  };
  function Contains$Creator() {
  }
  Contains$Creator.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Creator',
    interfaces: []
  };
  Contains.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Contains',
    interfaces: []
  };
  function ChangedSubjectPostStep(assertionContainer, transform, transformAndApply) {
    PostFinalStep.call(this, assertionContainer, transform, transformAndApply);
  }
  ChangedSubjectPostStep.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ChangedSubjectPostStep',
    interfaces: [PostFinalStep]
  };
  function ExtractedFeaturePostStep(assertionContainer, extract, extractAndApply) {
    PostFinalStep.call(this, assertionContainer, extract, extractAndApply);
  }
  ExtractedFeaturePostStep.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExtractedFeaturePostStep',
    interfaces: [PostFinalStep]
  };
  function featureExtractor$lambda() {
    return loadSingleService(getKClass(FeatureExtractor));
  }
  var featureExtractor;
  function get_featureExtractor() {
    return featureExtractor.value;
  }
  function FeatureExtractor() {
  }
  FeatureExtractor.prototype.extract_h9u5x1$ = function (originalAssertionContainer, description, representationForFailure, featureExtraction, maybeSubAssertions, representationInsteadOfFeature, callback$default) {
    if (representationInsteadOfFeature === void 0)
      representationInsteadOfFeature = null;
    return callback$default ? callback$default(originalAssertionContainer, description, representationForFailure, featureExtraction, maybeSubAssertions, representationInsteadOfFeature) : this.extract_h9u5x1$$default(originalAssertionContainer, description, representationForFailure, featureExtraction, maybeSubAssertions, representationInsteadOfFeature);
  };
  FeatureExtractor.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FeatureExtractor',
    interfaces: []
  };
  function PostFinalStep(assertionContainer, action, actionAndApply) {
    this.assertionContainer = assertionContainer;
    this.action = action;
    this.actionAndApply = actionAndApply;
  }
  PostFinalStep.prototype.getExpectOfFeature = function () {
    return this.action(this.assertionContainer);
  };
  function PostFinalStep$collect$lambda(this$PostFinalStep, closure$assertionCreator) {
    return function ($receiver) {
      this$PostFinalStep.actionAndApply($receiver, closure$assertionCreator);
      return Unit;
    };
  }
  PostFinalStep.prototype.collect_yw2nfn$ = function (assertionCreator) {
    return get_assertionCollector().collect_p44pa9$(this.assertionContainer, PostFinalStep$collect$lambda(this, assertionCreator));
  };
  function PostFinalStep$addToFeature$lambda(closure$assertionCreator) {
    return function ($receiver) {
      $receiver.addAssertion_94orq3$(get_assertionCollector().collect_p44pa9$($receiver, closure$assertionCreator));
      return Unit;
    };
  }
  PostFinalStep.prototype.addToFeature_yw2nfn$ = function (assertionCreator) {
    return this.actionAndApply(this.assertionContainer, PostFinalStep$addToFeature$lambda(assertionCreator));
  };
  PostFinalStep.prototype.addToInitial_yw2nfn$ = function (assertionCreator) {
    return this.assertionContainer.addAssertion_94orq3$(this.collect_yw2nfn$(assertionCreator));
  };
  PostFinalStep.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PostFinalStep',
    interfaces: []
  };
  function subjectChanger$lambda() {
    return loadSingleService(getKClass(SubjectChanger));
  }
  var subjectChanger;
  function get_subjectChanger() {
    return subjectChanger.value;
  }
  function SubjectChanger() {
  }
  function SubjectChanger$FailureHandler() {
  }
  SubjectChanger$FailureHandler.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FailureHandler',
    interfaces: []
  };
  SubjectChanger.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SubjectChanger',
    interfaces: []
  };
  function CharSequenceContains() {
  }
  function CharSequenceContains$Builder() {
  }
  CharSequenceContains$Builder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Builder',
    interfaces: [Contains$Builder]
  };
  function CharSequenceContains$CheckerOption() {
  }
  CharSequenceContains$CheckerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CheckerOption',
    interfaces: [Contains$CheckerOption]
  };
  function CharSequenceContains$SearchBehaviour() {
  }
  CharSequenceContains$SearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SearchBehaviour',
    interfaces: [Contains$SearchBehaviour]
  };
  function CharSequenceContains$Creator() {
  }
  CharSequenceContains$Creator.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Creator',
    interfaces: [Contains$Creator]
  };
  function CharSequenceContains$Checker() {
  }
  CharSequenceContains$Checker.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Checker',
    interfaces: [Contains$Checker]
  };
  function CharSequenceContains$Searcher() {
  }
  CharSequenceContains$Searcher.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Searcher',
    interfaces: []
  };
  CharSequenceContains.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CharSequenceContains',
    interfaces: []
  };
  function checkerFactory$lambda() {
    return loadSingleService(getKClass(CheckerFactory));
  }
  var checkerFactory;
  function get_checkerFactory() {
    return checkerFactory.value;
  }
  function CheckerFactory() {
  }
  CheckerFactory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CheckerFactory',
    interfaces: []
  };
  function charSequenceContainsAssertions$lambda() {
    return loadSingleService(getKClass(CharSequenceContainsAssertions));
  }
  var charSequenceContainsAssertions;
  function get_charSequenceContainsAssertions() {
    return charSequenceContainsAssertions.value;
  }
  function CharSequenceContainsAssertions() {
  }
  CharSequenceContainsAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CharSequenceContainsAssertions',
    interfaces: []
  };
  function IgnoringCaseSearchBehaviour() {
  }
  IgnoringCaseSearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IgnoringCaseSearchBehaviour',
    interfaces: [CharSequenceContains$SearchBehaviour]
  };
  function NoOpSearchBehaviour() {
  }
  NoOpSearchBehaviour.prototype.decorateDescription_n3w7xm$ = function (description) {
    return description;
  };
  NoOpSearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NoOpSearchBehaviour',
    interfaces: [CharSequenceContains$SearchBehaviour]
  };
  function NotSearchBehaviour() {
  }
  NotSearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NotSearchBehaviour',
    interfaces: [NoOpSearchBehaviour]
  };
  function searchBehaviourFactory$lambda() {
    return loadSingleService(getKClass(SearchBehaviourFactory));
  }
  var searchBehaviourFactory;
  function get_searchBehaviourFactory() {
    return searchBehaviourFactory.value;
  }
  function SearchBehaviourFactory() {
  }
  SearchBehaviourFactory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SearchBehaviourFactory',
    interfaces: []
  };
  function assertionCollector$lambda() {
    return loadSingleService(getKClass(AssertionCollector));
  }
  var assertionCollector;
  function get_assertionCollector() {
    return assertionCollector.value;
  }
  function AssertionCollector() {
  }
  AssertionCollector.prototype.collect_p44pa9$ = function (assertionContainer, assertionCreator) {
    return this.collect_9ebvy2$(assertionContainer.maybeSubject, assertionCreator);
  };
  AssertionCollector.prototype.collect_4q8p40$ = function (plant, assertionCreator) {
    return this.collect_md0kdf$(plant.subjectProvider, assertionCreator);
  };
  AssertionCollector.prototype.collect_md0kdf$ = function (subjectProvider, assertionCreator) {
    return this.collect_2bcx7n$(subjectProvider, getCallableRef('newCollectingPlant', function ($receiver, subjectProvider) {
      return $receiver.newCollectingPlant_9ce4rd$(subjectProvider);
    }.bind(null, core.coreFactory)), assertionCreator);
  };
  AssertionCollector.prototype.collectNullable_vi88l7$ = function (plant, assertionCreator) {
    return this.collectNullable_x73ge1$(plant.subjectProvider, assertionCreator);
  };
  AssertionCollector.prototype.collectNullable_x73ge1$ = function (subjectProvider, assertionCreator) {
    return this.collect_2bcx7n$(subjectProvider, getCallableRef('newCollectingPlantNullable', function ($receiver, subjectProvider) {
      return $receiver.newCollectingPlantNullable_klfg04$(subjectProvider);
    }.bind(null, core.coreFactory)), assertionCreator);
  };
  AssertionCollector.prototype.collectOrExplain_wkzgjz$ = function (safeToCollect, warningCannotEvaluate, plant, assertionCreator) {
    return this.collectOrExplain_yzke50$(safeToCollect, warningCannotEvaluate, plant.subjectProvider, assertionCreator);
  };
  AssertionCollector.prototype.collectOrExplain_yzke50$ = function (safeToCollect, warningCannotEvaluate, subjectProvider, assertionCreator) {
    return this.collectOrExplain_7ixqcy$(safeToCollect, warningCannotEvaluate, subjectProvider, getCallableRef('newCollectingPlant', function ($receiver, subjectProvider) {
      return $receiver.newCollectingPlant_9ce4rd$(subjectProvider);
    }.bind(null, core.coreFactory)), assertionCreator);
  };
  AssertionCollector.prototype.collectNullableOrExplain_g3yuno$ = function (safeToCollect, warningCannotEvaluate, plant, assertionCreator) {
    return this.collectNullableOrExplain_4y03eg$(safeToCollect, warningCannotEvaluate, plant.subjectProvider, assertionCreator);
  };
  AssertionCollector.prototype.collectNullableOrExplain_4y03eg$ = function (safeToCollect, warningCannotEvaluate, subjectProvider, assertionCreator) {
    return this.collectOrExplain_7ixqcy$(safeToCollect, warningCannotEvaluate, subjectProvider, getCallableRef('newCollectingPlantNullable', function ($receiver, subjectProvider) {
      return $receiver.newCollectingPlantNullable_klfg04$(subjectProvider);
    }.bind(null, core.coreFactory)), assertionCreator);
  };
  AssertionCollector.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionCollector',
    interfaces: []
  };
  function AssertionCollectorForExplanation() {
  }
  AssertionCollectorForExplanation.prototype.collect_por7is$ = function (warningCannotEvaluate, maybeSubject, assertionCreator) {
    return this.collect_tpqdt2$(warningCannotEvaluate, maybeSubject, getCallableRef('newCollectingPlant', function ($receiver, subjectProvider) {
      return $receiver.newCollectingPlant_9ce4rd$(subjectProvider);
    }.bind(null, core.coreFactory)), assertionCreator);
  };
  AssertionCollectorForExplanation.prototype.collectNullable_84ctxy$ = function (warningCannotEvaluate, maybeSubject, assertionCreator) {
    return this.collect_tpqdt2$(warningCannotEvaluate, maybeSubject, getCallableRef('newCollectingPlantNullable', function ($receiver, subjectProvider) {
      return $receiver.newCollectingPlantNullable_klfg04$(subjectProvider);
    }.bind(null, core.coreFactory)), assertionCreator);
  };
  AssertionCollectorForExplanation.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionCollectorForExplanation',
    interfaces: []
  };
  function nonThrowingAssertionCollectorForExplanation$lambda() {
    return loadSingleService(getKClass(NonThrowingAssertionCollectorForExplanation));
  }
  var nonThrowingAssertionCollectorForExplanation;
  function get_nonThrowingAssertionCollectorForExplanation() {
    return nonThrowingAssertionCollectorForExplanation.value;
  }
  function NonThrowingAssertionCollectorForExplanation() {
  }
  NonThrowingAssertionCollectorForExplanation.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NonThrowingAssertionCollectorForExplanation',
    interfaces: [AssertionCollectorForExplanation]
  };
  function throwingAssertionCollectorForExplanation$lambda() {
    return loadSingleService(getKClass(ThrowingAssertionCollectorForExplanation));
  }
  var throwingAssertionCollectorForExplanation;
  function get_throwingAssertionCollectorForExplanation() {
    return throwingAssertionCollectorForExplanation.value;
  }
  function ThrowingAssertionCollectorForExplanation() {
  }
  ThrowingAssertionCollectorForExplanation.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ThrowingAssertionCollectorForExplanation',
    interfaces: [AssertionCollectorForExplanation]
  };
  function FeatureExtractor_0() {
    FeatureExtractor$Companion_getInstance();
  }
  function FeatureExtractor$Companion() {
    FeatureExtractor$Companion_instance = this;
    this.builder = new DescriptionOptionImpl();
  }
  FeatureExtractor$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var FeatureExtractor$Companion_instance = null;
  function FeatureExtractor$Companion_getInstance() {
    if (FeatureExtractor$Companion_instance === null) {
      new FeatureExtractor$Companion();
    }
    return FeatureExtractor$Companion_instance;
  }
  function FeatureExtractor$DescriptionOption() {
  }
  FeatureExtractor$DescriptionOption.prototype.methodCall_25kzsl$ = function (methodName, arguments_0) {
    return this.feature_h4ejuu$(core.coreFactory.newMethodCallFormatter().format_ykr607$(methodName, arguments_0));
  };
  FeatureExtractor$DescriptionOption.prototype.feature_h4ejuu$ = function (featureRepresentation) {
    return this.withDescription_n3w7xm$(Untranslatable_init(featureRepresentation));
  };
  FeatureExtractor$DescriptionOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DescriptionOption',
    interfaces: []
  };
  function FeatureExtractor$ParameterObjectOption() {
  }
  FeatureExtractor$ParameterObjectOption.prototype.withParameterObject_1uqxti$ = function (parameterObject) {
    return get_featureExtractorCreatorFactory().create_h0d6pk$(this.featureDescription, parameterObject);
  };
  FeatureExtractor$ParameterObjectOption.prototype.withParameterObjectNullable_3012el$ = function (parameterObject) {
    return get_featureExtractorCreatorFactory().createNullable_5sozyt$(this.featureDescription, parameterObject);
  };
  FeatureExtractor$ParameterObjectOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ParameterObjectOption',
    interfaces: []
  };
  function FeatureExtractor$CreatorLike() {
  }
  FeatureExtractor$CreatorLike.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CreatorLike',
    interfaces: []
  };
  function FeatureExtractor$Creator() {
  }
  FeatureExtractor$Creator.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Creator',
    interfaces: [FeatureExtractor$CreatorLike]
  };
  function FeatureExtractor$CreatorNullable() {
  }
  FeatureExtractor$CreatorNullable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CreatorNullable',
    interfaces: [FeatureExtractor$CreatorLike]
  };
  function FeatureExtractor$ParameterObject(subjectPlant, extractionNotSuccessful, warningCannotEvaluate, canBeExtracted, featureExtraction) {
    this.subjectPlant = subjectPlant;
    this.extractionNotSuccessful = extractionNotSuccessful;
    this.warningCannotEvaluate = warningCannotEvaluate;
    this.canBeExtracted = canBeExtracted;
    this.featureExtraction = featureExtraction;
  }
  FeatureExtractor$ParameterObject.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParameterObject',
    interfaces: []
  };
  FeatureExtractor$ParameterObject.prototype.component1 = function () {
    return this.subjectPlant;
  };
  FeatureExtractor$ParameterObject.prototype.component2 = function () {
    return this.extractionNotSuccessful;
  };
  FeatureExtractor$ParameterObject.prototype.component3 = function () {
    return this.warningCannotEvaluate;
  };
  FeatureExtractor$ParameterObject.prototype.component4 = function () {
    return this.canBeExtracted;
  };
  FeatureExtractor$ParameterObject.prototype.component5 = function () {
    return this.featureExtraction;
  };
  FeatureExtractor$ParameterObject.prototype.copy_fj2ibc$ = function (subjectPlant, extractionNotSuccessful, warningCannotEvaluate, canBeExtracted, featureExtraction) {
    return new FeatureExtractor$ParameterObject(subjectPlant === void 0 ? this.subjectPlant : subjectPlant, extractionNotSuccessful === void 0 ? this.extractionNotSuccessful : extractionNotSuccessful, warningCannotEvaluate === void 0 ? this.warningCannotEvaluate : warningCannotEvaluate, canBeExtracted === void 0 ? this.canBeExtracted : canBeExtracted, featureExtraction === void 0 ? this.featureExtraction : featureExtraction);
  };
  FeatureExtractor$ParameterObject.prototype.toString = function () {
    return 'ParameterObject(subjectPlant=' + Kotlin.toString(this.subjectPlant) + (', extractionNotSuccessful=' + Kotlin.toString(this.extractionNotSuccessful)) + (', warningCannotEvaluate=' + Kotlin.toString(this.warningCannotEvaluate)) + (', canBeExtracted=' + Kotlin.toString(this.canBeExtracted)) + (', featureExtraction=' + Kotlin.toString(this.featureExtraction)) + ')';
  };
  FeatureExtractor$ParameterObject.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.subjectPlant) | 0;
    result = result * 31 + Kotlin.hashCode(this.extractionNotSuccessful) | 0;
    result = result * 31 + Kotlin.hashCode(this.warningCannotEvaluate) | 0;
    result = result * 31 + Kotlin.hashCode(this.canBeExtracted) | 0;
    result = result * 31 + Kotlin.hashCode(this.featureExtraction) | 0;
    return result;
  };
  FeatureExtractor$ParameterObject.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.subjectPlant, other.subjectPlant) && Kotlin.equals(this.extractionNotSuccessful, other.extractionNotSuccessful) && Kotlin.equals(this.warningCannotEvaluate, other.warningCannotEvaluate) && Kotlin.equals(this.canBeExtracted, other.canBeExtracted) && Kotlin.equals(this.featureExtraction, other.featureExtraction)))));
  };
  FeatureExtractor_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FeatureExtractor',
    interfaces: []
  };
  function featureExtractorCreatorFactory$lambda() {
    return loadSingleService(getKClass(FeatureExtractorCreatorFactory));
  }
  var featureExtractorCreatorFactory;
  function get_featureExtractorCreatorFactory() {
    return featureExtractorCreatorFactory.value;
  }
  function FeatureExtractorCreatorFactory() {
  }
  FeatureExtractorCreatorFactory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FeatureExtractorCreatorFactory',
    interfaces: []
  };
  function DescriptionOptionImpl() {
  }
  DescriptionOptionImpl.prototype.withDescription_n3w7xm$ = function (translatable) {
    return new ParameterObjectOptionImpl(translatable);
  };
  DescriptionOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionOptionImpl',
    interfaces: [FeatureExtractor$DescriptionOption]
  };
  function ParameterObjectOptionImpl(featureDescription) {
    this.featureDescription_56pmf9$_0 = featureDescription;
  }
  Object.defineProperty(ParameterObjectOptionImpl.prototype, 'featureDescription', {
    get: function () {
      return this.featureDescription_56pmf9$_0;
    }
  });
  ParameterObjectOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParameterObjectOptionImpl',
    interfaces: [FeatureExtractor$ParameterObjectOption]
  };
  function IterableContains() {
  }
  function IterableContains$Builder() {
  }
  IterableContains$Builder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Builder',
    interfaces: [Contains$Builder]
  };
  function IterableContains$CheckerOption() {
  }
  IterableContains$CheckerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CheckerOption',
    interfaces: [Contains$CheckerOption]
  };
  function IterableContains$SearchBehaviour() {
  }
  IterableContains$SearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SearchBehaviour',
    interfaces: [Contains$SearchBehaviour]
  };
  function IterableContains$Creator() {
  }
  IterableContains$Creator.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Creator',
    interfaces: [Contains$Creator]
  };
  function IterableContains$Checker() {
  }
  IterableContains$Checker.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Checker',
    interfaces: [Contains$Checker]
  };
  IterableContains.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IterableContains',
    interfaces: []
  };
  function checkerFactory$lambda_0() {
    return loadSingleService(getKClass(CheckerFactory_0));
  }
  var checkerFactory_0;
  function get_checkerFactory_0() {
    return checkerFactory_0.value;
  }
  function CheckerFactory_0() {
  }
  CheckerFactory_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CheckerFactory',
    interfaces: []
  };
  function iterableContainsAssertions$lambda() {
    return loadSingleService(getKClass(IterableContainsAssertions));
  }
  var iterableContainsAssertions;
  function get_iterableContainsAssertions() {
    return iterableContainsAssertions.value;
  }
  function IterableContainsAssertions() {
  }
  IterableContainsAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IterableContainsAssertions',
    interfaces: []
  };
  function InAnyOrderOnlySearchBehaviour() {
  }
  InAnyOrderOnlySearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'InAnyOrderOnlySearchBehaviour',
    interfaces: [IterableContains$SearchBehaviour]
  };
  function InAnyOrderSearchBehaviour() {
  }
  InAnyOrderSearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'InAnyOrderSearchBehaviour',
    interfaces: [IterableContains$SearchBehaviour]
  };
  function InOrderOnlyGroupedSearchBehaviour() {
  }
  InOrderOnlyGroupedSearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'InOrderOnlyGroupedSearchBehaviour',
    interfaces: [IterableContains$SearchBehaviour]
  };
  function InOrderOnlyGroupedWithinSearchBehaviour() {
  }
  InOrderOnlyGroupedWithinSearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'InOrderOnlyGroupedWithinSearchBehaviour',
    interfaces: [InOrderOnlyGroupedSearchBehaviour]
  };
  function InOrderOnlySearchBehaviour() {
  }
  InOrderOnlySearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'InOrderOnlySearchBehaviour',
    interfaces: [IterableContains$SearchBehaviour]
  };
  function InOrderSearchBehaviour() {
  }
  InOrderSearchBehaviour.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'InOrderSearchBehaviour',
    interfaces: [IterableContains$SearchBehaviour]
  };
  function NoOpSearchBehaviour_0() {
  }
  NoOpSearchBehaviour_0.prototype.decorateDescription_n3w7xm$ = function (description) {
    return description;
  };
  NoOpSearchBehaviour_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NoOpSearchBehaviour',
    interfaces: [IterableContains$SearchBehaviour]
  };
  function NotSearchBehaviour_0() {
  }
  NotSearchBehaviour_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NotSearchBehaviour',
    interfaces: [InAnyOrderSearchBehaviour]
  };
  function searchBehaviourFactory$lambda_0() {
    return loadSingleService(getKClass(SearchBehaviourFactory_0));
  }
  var searchBehaviourFactory_0;
  function get_searchBehaviourFactory_0() {
    return searchBehaviourFactory_0.value;
  }
  function SearchBehaviourFactory_0() {
  }
  SearchBehaviourFactory_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SearchBehaviourFactory',
    interfaces: []
  };
  function ThrowableThrown() {
  }
  function ThrowableThrown$Builder() {
  }
  ThrowableThrown$Builder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Builder',
    interfaces: []
  };
  function ThrowableThrown$AbsentThrowableMessageProvider() {
  }
  ThrowableThrown$AbsentThrowableMessageProvider.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AbsentThrowableMessageProvider',
    interfaces: []
  };
  function ThrowableThrown$Creator() {
  }
  ThrowableThrown$Creator.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Creator',
    interfaces: []
  };
  ThrowableThrown.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ThrowableThrown',
    interfaces: []
  };
  function throwableThrownAssertions$lambda() {
    return loadSingleService(getKClass(ThrowableThrownAssertions));
  }
  var throwableThrownAssertions;
  function get_throwableThrownAssertions() {
    return throwableThrownAssertions.value;
  }
  function ThrowableThrownAssertions() {
  }
  ThrowableThrownAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ThrowableThrownAssertions',
    interfaces: []
  };
  function absentThrowableMessageProviderFactory$lambda() {
    return loadSingleService(getKClass(AbsentThrowableMessageProviderFactory));
  }
  var absentThrowableMessageProviderFactory;
  function get_absentThrowableMessageProviderFactory() {
    return absentThrowableMessageProviderFactory.value;
  }
  function AbsentThrowableMessageProviderFactory() {
  }
  AbsentThrowableMessageProviderFactory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AbsentThrowableMessageProviderFactory',
    interfaces: []
  };
  function FloatingPointAssertions() {
  }
  FloatingPointAssertions.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FloatingPointAssertions',
    interfaces: [FloatingPointAssertionsCommon]
  };
  var package$ch = _.ch || (_.ch = {});
  var package$tutteli = package$ch.tutteli || (package$ch.tutteli = {});
  var package$atrium = package$tutteli.atrium || (package$tutteli.atrium = {});
  var package$domain = package$atrium.domain || (package$atrium.domain = {});
  var package$assertions = package$domain.assertions || (package$domain.assertions = {});
  var package$composers = package$assertions.composers || (package$assertions.composers = {});
  Object.defineProperty(package$composers, 'assertionComposer', {
    get: get_assertionComposer
  });
  package$composers.AssertionComposer = AssertionComposer;
  var package$creating = package$domain.creating || (package$domain.creating = {});
  Object.defineProperty(package$creating, 'anyAssertions', {
    get: get_anyAssertions
  });
  package$creating.AnyAssertions = AnyAssertions;
  Object.defineProperty(package$creating, 'charSequenceAssertions', {
    get: get_charSequenceAssertions
  });
  package$creating.CharSequenceAssertions = CharSequenceAssertions;
  Object.defineProperty(package$creating, 'collectionAssertions', {
    get: get_collectionAssertions
  });
  package$creating.CollectionAssertions = CollectionAssertions;
  Object.defineProperty(package$creating, 'comparableAssertions', {
    get: get_comparableAssertions
  });
  package$creating.ComparableAssertions = ComparableAssertions;
  Object.defineProperty(package$creating, 'featureAssertions', {
    get: get_featureAssertions
  });
  package$creating.FeatureAssertions = FeatureAssertions;
  Object.defineProperty(package$creating, 'floatingPointAssertions', {
    get: get_floatingPointAssertions
  });
  package$creating.FloatingPointAssertionsCommon = FloatingPointAssertionsCommon;
  Object.defineProperty(package$creating, 'iterableAssertions', {
    get: get_iterableAssertions
  });
  package$creating.IterableAssertions = IterableAssertions;
  Object.defineProperty(package$creating, 'listAssertions', {
    get: get_listAssertions
  });
  package$creating.ListAssertions = ListAssertions;
  Object.defineProperty(package$creating, 'mapAssertions', {
    get: get_mapAssertions
  });
  package$creating.MapAssertions = MapAssertions;
  Object.defineProperty(package$creating, 'mapEntryAssertions', {
    get: get_mapEntryAssertions
  });
  package$creating.MapEntryAssertions = MapEntryAssertions;
  Object.defineProperty(package$creating, 'newFeatureAssertions', {
    get: get_newFeatureAssertions
  });
  package$creating.NewFeatureAssertions = NewFeatureAssertions;
  package$creating.MetaFeature_init_ivjyqt$ = MetaFeature_init;
  package$creating.MetaFeature_init_umlfku$ = MetaFeature_init_0;
  package$creating.MetaFeature = MetaFeature;
  Object.defineProperty(package$creating, 'pairAssertions', {
    get: get_pairAssertions
  });
  package$creating.PairAssertions = PairAssertions;
  Object.defineProperty(package$creating, 'throwableAssertions', {
    get: get_throwableAssertions
  });
  package$creating.ThrowableAssertions = ThrowableAssertions;
  AnyTypeTransformation.Creator = AnyTypeTransformation$Creator;
  AnyTypeTransformation.FailureHandler = AnyTypeTransformation$FailureHandler;
  AnyTypeTransformation.ParameterObject = AnyTypeTransformation$ParameterObject;
  var package$any = package$creating.any || (package$creating.any = {});
  var package$typetransformation = package$any.typetransformation || (package$any.typetransformation = {});
  package$typetransformation.AnyTypeTransformation = AnyTypeTransformation;
  var package$creators = package$typetransformation.creators || (package$typetransformation.creators = {});
  Object.defineProperty(package$creators, 'anyTypeTransformationAssertions', {
    get: get_anyTypeTransformationAssertions
  });
  package$creators.AnyTypeTransformationAssertions = AnyTypeTransformationAssertions;
  var package$failurehandlers = package$typetransformation.failurehandlers || (package$typetransformation.failurehandlers = {});
  Object.defineProperty(package$failurehandlers, 'failureHandlerFactory', {
    get: get_failureHandlerFactory
  });
  package$failurehandlers.FailureHandlerFactory = FailureHandlerFactory;
  Contains.Builder = Contains$Builder;
  Contains.CheckerOption = Contains$CheckerOption;
  Contains.SearchBehaviour = Contains$SearchBehaviour;
  Contains.Checker = Contains$Checker;
  Contains.Creator = Contains$Creator;
  var package$basic = package$creating.basic || (package$creating.basic = {});
  var package$contains = package$basic.contains || (package$basic.contains = {});
  package$contains.Contains = Contains;
  var package$changers = package$creating.changers || (package$creating.changers = {});
  package$changers.ChangedSubjectPostStep = ChangedSubjectPostStep;
  package$changers.ExtractedFeaturePostStep = ExtractedFeaturePostStep;
  Object.defineProperty(package$changers, 'featureExtractor', {
    get: get_featureExtractor
  });
  package$changers.FeatureExtractor = FeatureExtractor;
  package$changers.PostFinalStep = PostFinalStep;
  Object.defineProperty(package$changers, 'subjectChanger', {
    get: get_subjectChanger
  });
  SubjectChanger.FailureHandler = SubjectChanger$FailureHandler;
  package$changers.SubjectChanger = SubjectChanger;
  CharSequenceContains.Builder = CharSequenceContains$Builder;
  CharSequenceContains.CheckerOption = CharSequenceContains$CheckerOption;
  CharSequenceContains.SearchBehaviour = CharSequenceContains$SearchBehaviour;
  CharSequenceContains.Creator = CharSequenceContains$Creator;
  CharSequenceContains.Checker = CharSequenceContains$Checker;
  CharSequenceContains.Searcher = CharSequenceContains$Searcher;
  var package$charsequence = package$creating.charsequence || (package$creating.charsequence = {});
  var package$contains_0 = package$charsequence.contains || (package$charsequence.contains = {});
  package$contains_0.CharSequenceContains = CharSequenceContains;
  var package$checkers = package$contains_0.checkers || (package$contains_0.checkers = {});
  Object.defineProperty(package$checkers, 'checkerFactory', {
    get: get_checkerFactory
  });
  package$checkers.CheckerFactory = CheckerFactory;
  var package$creators_0 = package$contains_0.creators || (package$contains_0.creators = {});
  Object.defineProperty(package$creators_0, 'charSequenceContainsAssertions', {
    get: get_charSequenceContainsAssertions
  });
  package$creators_0.CharSequenceContainsAssertions = CharSequenceContainsAssertions;
  var package$searchbehaviours = package$contains_0.searchbehaviours || (package$contains_0.searchbehaviours = {});
  package$searchbehaviours.IgnoringCaseSearchBehaviour = IgnoringCaseSearchBehaviour;
  package$searchbehaviours.NoOpSearchBehaviour = NoOpSearchBehaviour;
  package$searchbehaviours.NotSearchBehaviour = NotSearchBehaviour;
  Object.defineProperty(package$searchbehaviours, 'searchBehaviourFactory', {
    get: get_searchBehaviourFactory
  });
  package$searchbehaviours.SearchBehaviourFactory = SearchBehaviourFactory;
  var package$collectors = package$creating.collectors || (package$creating.collectors = {});
  Object.defineProperty(package$collectors, 'assertionCollector', {
    get: get_assertionCollector
  });
  package$collectors.AssertionCollector = AssertionCollector;
  package$collectors.AssertionCollectorForExplanation = AssertionCollectorForExplanation;
  Object.defineProperty(package$collectors, 'nonThrowingAssertionCollectorForExplanation', {
    get: get_nonThrowingAssertionCollectorForExplanation
  });
  package$collectors.NonThrowingAssertionCollectorForExplanation = NonThrowingAssertionCollectorForExplanation;
  Object.defineProperty(package$collectors, 'throwingAssertionCollectorForExplanation', {
    get: get_throwingAssertionCollectorForExplanation
  });
  package$collectors.ThrowingAssertionCollectorForExplanation = ThrowingAssertionCollectorForExplanation;
  Object.defineProperty(FeatureExtractor_0, 'Companion', {
    get: FeatureExtractor$Companion_getInstance
  });
  FeatureExtractor_0.DescriptionOption = FeatureExtractor$DescriptionOption;
  FeatureExtractor_0.ParameterObjectOption = FeatureExtractor$ParameterObjectOption;
  FeatureExtractor_0.CreatorLike = FeatureExtractor$CreatorLike;
  FeatureExtractor_0.Creator = FeatureExtractor$Creator;
  FeatureExtractor_0.CreatorNullable = FeatureExtractor$CreatorNullable;
  FeatureExtractor_0.ParameterObject = FeatureExtractor$ParameterObject;
  var package$feature = package$creating.feature || (package$creating.feature = {});
  var package$extract = package$feature.extract || (package$feature.extract = {});
  package$extract.FeatureExtractor = FeatureExtractor_0;
  var package$creators_1 = package$extract.creators || (package$extract.creators = {});
  Object.defineProperty(package$creators_1, 'featureExtractorCreatorFactory', {
    get: get_featureExtractorCreatorFactory
  });
  package$creators_1.FeatureExtractorCreatorFactory = FeatureExtractorCreatorFactory;
  var package$impl = package$extract.impl || (package$extract.impl = {});
  package$impl.DescriptionOptionImpl = DescriptionOptionImpl;
  package$impl.ParameterObjectOptionImpl = ParameterObjectOptionImpl;
  IterableContains.Builder = IterableContains$Builder;
  IterableContains.CheckerOption = IterableContains$CheckerOption;
  IterableContains.SearchBehaviour = IterableContains$SearchBehaviour;
  IterableContains.Creator = IterableContains$Creator;
  IterableContains.Checker = IterableContains$Checker;
  var package$iterable = package$creating.iterable || (package$creating.iterable = {});
  var package$contains_1 = package$iterable.contains || (package$iterable.contains = {});
  package$contains_1.IterableContains = IterableContains;
  var package$checkers_0 = package$contains_1.checkers || (package$contains_1.checkers = {});
  Object.defineProperty(package$checkers_0, 'checkerFactory', {
    get: get_checkerFactory_0
  });
  package$checkers_0.CheckerFactory = CheckerFactory_0;
  var package$creators_2 = package$contains_1.creators || (package$contains_1.creators = {});
  Object.defineProperty(package$creators_2, 'iterableContainsAssertions', {
    get: get_iterableContainsAssertions
  });
  package$creators_2.IterableContainsAssertions = IterableContainsAssertions;
  var package$searchbehaviours_0 = package$contains_1.searchbehaviours || (package$contains_1.searchbehaviours = {});
  package$searchbehaviours_0.InAnyOrderOnlySearchBehaviour = InAnyOrderOnlySearchBehaviour;
  package$searchbehaviours_0.InAnyOrderSearchBehaviour = InAnyOrderSearchBehaviour;
  package$searchbehaviours_0.InOrderOnlyGroupedSearchBehaviour = InOrderOnlyGroupedSearchBehaviour;
  package$searchbehaviours_0.InOrderOnlyGroupedWithinSearchBehaviour = InOrderOnlyGroupedWithinSearchBehaviour;
  package$searchbehaviours_0.InOrderOnlySearchBehaviour = InOrderOnlySearchBehaviour;
  package$searchbehaviours_0.InOrderSearchBehaviour = InOrderSearchBehaviour;
  package$searchbehaviours_0.NoOpSearchBehaviour = NoOpSearchBehaviour_0;
  package$searchbehaviours_0.NotSearchBehaviour = NotSearchBehaviour_0;
  Object.defineProperty(package$searchbehaviours_0, 'searchBehaviourFactory', {
    get: get_searchBehaviourFactory_0
  });
  package$searchbehaviours_0.SearchBehaviourFactory = SearchBehaviourFactory_0;
  ThrowableThrown.Builder = ThrowableThrown$Builder;
  ThrowableThrown.AbsentThrowableMessageProvider = ThrowableThrown$AbsentThrowableMessageProvider;
  ThrowableThrown.Creator = ThrowableThrown$Creator;
  var package$throwable = package$creating.throwable || (package$creating.throwable = {});
  var package$thrown = package$throwable.thrown || (package$throwable.thrown = {});
  package$thrown.ThrowableThrown = ThrowableThrown;
  var package$creators_3 = package$thrown.creators || (package$thrown.creators = {});
  Object.defineProperty(package$creators_3, 'throwableThrownAssertions', {
    get: get_throwableThrownAssertions
  });
  package$creators_3.ThrowableThrownAssertions = ThrowableThrownAssertions;
  var package$providers = package$thrown.providers || (package$thrown.providers = {});
  Object.defineProperty(package$providers, 'absentThrowableMessageProviderFactory', {
    get: get_absentThrowableMessageProviderFactory
  });
  package$providers.AbsentThrowableMessageProviderFactory = AbsentThrowableMessageProviderFactory;
  package$creating.FloatingPointAssertions = FloatingPointAssertions;
  NotSearchBehaviour.prototype.decorateDescription_n3w7xm$ = NoOpSearchBehaviour.prototype.decorateDescription_n3w7xm$;
  NonThrowingAssertionCollectorForExplanation.prototype.collect_por7is$ = AssertionCollectorForExplanation.prototype.collect_por7is$;
  NonThrowingAssertionCollectorForExplanation.prototype.collectNullable_84ctxy$ = AssertionCollectorForExplanation.prototype.collectNullable_84ctxy$;
  ThrowingAssertionCollectorForExplanation.prototype.collect_por7is$ = AssertionCollectorForExplanation.prototype.collect_por7is$;
  ThrowingAssertionCollectorForExplanation.prototype.collectNullable_84ctxy$ = AssertionCollectorForExplanation.prototype.collectNullable_84ctxy$;
  DescriptionOptionImpl.prototype.methodCall_25kzsl$ = FeatureExtractor$DescriptionOption.prototype.methodCall_25kzsl$;
  DescriptionOptionImpl.prototype.feature_h4ejuu$ = FeatureExtractor$DescriptionOption.prototype.feature_h4ejuu$;
  ParameterObjectOptionImpl.prototype.withParameterObject_1uqxti$ = FeatureExtractor$ParameterObjectOption.prototype.withParameterObject_1uqxti$;
  ParameterObjectOptionImpl.prototype.withParameterObjectNullable_3012el$ = FeatureExtractor$ParameterObjectOption.prototype.withParameterObjectNullable_3012el$;
  assertionComposer = lazy(assertionComposer$lambda);
  anyAssertions = lazy(anyAssertions$lambda);
  charSequenceAssertions = lazy(charSequenceAssertions$lambda);
  collectionAssertions = lazy(collectionAssertions$lambda);
  comparableAssertions = lazy(comparableAssertions$lambda);
  featureAssertions = lazy(featureAssertions$lambda);
  floatingPointAssertions = lazy(floatingPointAssertions$lambda);
  iterableAssertions = lazy(iterableAssertions$lambda);
  listAssertions = lazy(listAssertions$lambda);
  mapAssertions = lazy(mapAssertions$lambda);
  mapEntryAssertions = lazy(mapEntryAssertions$lambda);
  newFeatureAssertions = lazy(newFeatureAssertions$lambda);
  pairAssertions = lazy(pairAssertions$lambda);
  throwableAssertions = lazy(throwableAssertions$lambda);
  anyTypeTransformationAssertions = lazy(anyTypeTransformationAssertions$lambda);
  failureHandlerFactory = lazy(failureHandlerFactory$lambda);
  featureExtractor = lazy(featureExtractor$lambda);
  subjectChanger = lazy(subjectChanger$lambda);
  checkerFactory = lazy(checkerFactory$lambda);
  charSequenceContainsAssertions = lazy(charSequenceContainsAssertions$lambda);
  searchBehaviourFactory = lazy(searchBehaviourFactory$lambda);
  assertionCollector = lazy(assertionCollector$lambda);
  nonThrowingAssertionCollectorForExplanation = lazy(nonThrowingAssertionCollectorForExplanation$lambda);
  throwingAssertionCollectorForExplanation = lazy(throwingAssertionCollectorForExplanation$lambda);
  featureExtractorCreatorFactory = lazy(featureExtractorCreatorFactory$lambda);
  checkerFactory_0 = lazy(checkerFactory$lambda_0);
  iterableContainsAssertions = lazy(iterableContainsAssertions$lambda);
  searchBehaviourFactory_0 = lazy(searchBehaviourFactory$lambda_0);
  throwableThrownAssertions = lazy(throwableThrownAssertions$lambda);
  absentThrowableMessageProviderFactory = lazy(absentThrowableMessageProviderFactory$lambda);
  Kotlin.defineModule('atrium-domain-api-js', _);
  return _;
}));

//# sourceMappingURL=atrium-domain-api-js.js.map
