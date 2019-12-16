(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'kbox-js'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('kbox-js'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'atrium-core-api-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'atrium-core-api-js'.");
    }
    if (typeof this['kbox-js'] === 'undefined') {
      throw new Error("Error loading module 'atrium-core-api-js'. Its dependency 'kbox-js' was not found. Please, check whether 'kbox-js' is loaded prior to 'atrium-core-api-js'.");
    }
    root['atrium-core-api-js'] = factory(typeof this['atrium-core-api-js'] === 'undefined' ? {} : this['atrium-core-api-js'], kotlin, this['kbox-js']);
  }
}(this, function (_, Kotlin, $module$kbox_js) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Collection = Kotlin.kotlin.collections.Collection;
  var toString = Kotlin.toString;
  var ensureNotNull = Kotlin.ensureNotNull;
  var listOf = Kotlin.kotlin.collections.listOf_mh5how$;
  var listOf_0 = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var getCallableRef = Kotlin.getCallableRef;
  var throwCCE = Kotlin.throwCCE;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var equals = Kotlin.equals;
  var varargToList = $module$kbox_js.ch.tutteli.kbox.varargToList_r59i0z$;
  var defineInlineFunction = Kotlin.defineInlineFunction;
  var wrapFunction = Kotlin.wrapFunction;
  var lazy = Kotlin.kotlin.lazy_klfg04$;
  var getKClass = Kotlin.getKClass;
  var NoSuchElementException = Kotlin.kotlin.NoSuchElementException;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var IllegalStateException_init = Kotlin.kotlin.IllegalStateException_init_pdl1vj$;
  var Annotation = Kotlin.kotlin.Annotation;
  var RuntimeException_init = Kotlin.kotlin.RuntimeException_init_pdl1vj$;
  var RuntimeException = Kotlin.kotlin.RuntimeException;
  var UnsupportedOperationException_init = Kotlin.kotlin.UnsupportedOperationException_init_pdl1vj$;
  var Unit = Kotlin.kotlin.Unit;
  var drop = Kotlin.kotlin.collections.drop_8ujjk8$;
  var IllegalArgumentException_init = Kotlin.kotlin.IllegalArgumentException_init_pdl1vj$;
  var Array_0 = Array;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
  var StringBuilder = Kotlin.kotlin.text.StringBuilder;
  var isBlank = Kotlin.kotlin.text.isBlank_gw00vp$;
  var drop_0 = Kotlin.kotlin.collections.drop_ba2ldo$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var addAll = Kotlin.kotlin.collections.addAll_ye1y7v$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var get_js = Kotlin.kotlin.js.get_js_1yb8b7$;
  var contains = Kotlin.kotlin.text.contains_li3zpu$;
  var startsWith = Kotlin.kotlin.text.startsWith_7epoxm$;
  var Any = Object;
  var ClassCastException = Kotlin.kotlin.ClassCastException;
  var Set = Kotlin.kotlin.collections.Set;
  var asSequence = Kotlin.kotlin.collections.asSequence_7wnvza$;
  var map = Kotlin.kotlin.sequences.map_z5avom$;
  var emptySequence = Kotlin.kotlin.sequences.emptySequence_287e2$;
  var HashSet_init = Kotlin.kotlin.collections.HashSet_init_287e2$;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var isNotNullAndNotBlank = $module$kbox_js.ch.tutteli.kbox.isNotNullAndNotBlank_qc8d1o$;
  var indexOf = Kotlin.kotlin.text.indexOf_l5u8uk$;
  var splitToSequence = Kotlin.kotlin.text.splitToSequence_o64adg$;
  var dropWhile = Kotlin.kotlin.sequences.dropWhile_euau3h$;
  var substringAfter = Kotlin.kotlin.text.substringAfter_j4ogox$;
  var toList = Kotlin.kotlin.sequences.toList_veqyi0$;
  var AssertionError_init = Kotlin.kotlin.AssertionError_init_pdl1vj$;
  var AssertionError = Kotlin.kotlin.AssertionError;
  ExplanatoryAssertionGroup.prototype = Object.create(EmptyNameAndRepresentationAssertionGroup.prototype);
  ExplanatoryAssertionGroup.prototype.constructor = ExplanatoryAssertionGroup;
  IndentAssertionGroup.prototype = Object.create(EmptyNameAndRepresentationAssertionGroup.prototype);
  IndentAssertionGroup.prototype.constructor = IndentAssertionGroup;
  InvisibleAssertionGroup.prototype = Object.create(EmptyNameAndRepresentationAssertionGroup.prototype);
  InvisibleAssertionGroup.prototype.constructor = InvisibleAssertionGroup;
  HoldsOptionImpl_0.prototype = Object.create(FixedClaimLikeAssertionGroupHoldsOptionImpl.prototype);
  HoldsOptionImpl_0.prototype.constructor = HoldsOptionImpl_0;
  HoldsOptionImpl_1.prototype = Object.create(FixedClaimLikeAssertionGroupHoldsOptionImpl.prototype);
  HoldsOptionImpl_1.prototype.constructor = HoldsOptionImpl_1;
  HoldsStepImpl_0.prototype = Object.create(HoldsStepImpl.prototype);
  HoldsStepImpl_0.prototype.constructor = HoldsStepImpl_0;
  Some.prototype = Object.create(Option.prototype);
  Some.prototype.constructor = Some;
  None.prototype = Object.create(Option.prototype);
  None.prototype.constructor = None;
  MaybeSubject$Absent.prototype = Object.create(MaybeSubject.prototype);
  MaybeSubject$Absent.prototype.constructor = MaybeSubject$Absent;
  MaybeSubject$Present.prototype = Object.create(MaybeSubject.prototype);
  MaybeSubject$Present.prototype.constructor = MaybeSubject$Present;
  PlantHasNoSubjectException.prototype = Object.create(RuntimeException.prototype);
  PlantHasNoSubjectException.prototype.constructor = PlantHasNoSubjectException;
  UsingDefaultTranslator.prototype = Object.create(ArgumentsSupportingTranslator.prototype);
  UsingDefaultTranslator.prototype.constructor = UsingDefaultTranslator;
  AtriumError.prototype = Object.create(AssertionError.prototype);
  AtriumError.prototype.constructor = AtriumError;
  function Assertion() {
  }
  Assertion.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Assertion',
    interfaces: []
  };
  function AssertionGroup() {
  }
  Object.defineProperty(AssertionGroup.prototype, 'name', {
    get: function () {
      return this.description;
    }
  });
  Object.defineProperty(AssertionGroup.prototype, 'subject', {
    get: function () {
      return this.representation;
    }
  });
  AssertionGroup.prototype.holds = function () {
    var $receiver = this.assertions;
    var all$result;
    all$break: do {
      var tmp$;
      if (Kotlin.isType($receiver, Collection) && $receiver.isEmpty()) {
        all$result = true;
        break all$break;
      }
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        if (!element.holds()) {
          all$result = false;
          break all$break;
        }
      }
      all$result = true;
    }
     while (false);
    return all$result;
  };
  function AssertionGroup$Builder() {
    AssertionGroup$Builder_instance = this;
    this.root = new AssertionGroup$Builder$BasicAssertionGroupBuilder(RootAssertionGroupType_getInstance());
    this.list = new AssertionGroup$Builder$BasicAssertionGroupBuilder(DefaultListAssertionGroupType_getInstance());
    this.feature = new AssertionGroup$Builder$BasicAssertionGroupBuilder(DefaultFeatureAssertionGroupType_getInstance());
    this.summary = new AssertionGroup$Builder$BasicAssertionGroupBuilder(DefaultSummaryAssertionGroupType_getInstance());
    this.explanatory = new AssertionGroup$Builder$ExplanatoryAssertionGroupOption();
    this.invisible = new AssertionGroup$Builder$EmptyNameAndSubjectAssertionGroupBuilder(InvisibleAssertionGroupType_getInstance());
  }
  AssertionGroup$Builder.prototype.withType_2n7t4u$ = function (groupType) {
    return new AssertionGroup$Builder$BasicAssertionGroupBuilder(groupType);
  };
  function AssertionGroup$Builder$BasicAssertionGroupBuilder(groupType) {
    this.groupType_0 = groupType;
  }
  AssertionGroup$Builder$BasicAssertionGroupBuilder.prototype.create_9q8chy$ = function (name, subject, assertion) {
    return assertionBuilder.customType_90qaeo$(this.groupType_0).withDescriptionAndRepresentation_75z4jm$(name, subject).withAssertion_94orq3$(assertion).build();
  };
  AssertionGroup$Builder$BasicAssertionGroupBuilder.prototype.create_vlqeqt$ = function (name, subject, assertions) {
    return assertionBuilder.customType_90qaeo$(this.groupType_0).withDescriptionAndRepresentation_75z4jm$(name, subject).withAssertions_tgi7xs$(assertions).build();
  };
  AssertionGroup$Builder$BasicAssertionGroupBuilder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BasicAssertionGroupBuilder',
    interfaces: []
  };
  function AssertionGroup$Builder$ExplanatoryAssertionGroupOption() {
    this.withDefault = new AssertionGroup$Builder$ExplanatoryAssertionGroupBuilder(DefaultExplanatoryAssertionGroupType_getInstance());
    this.withWarning = new AssertionGroup$Builder$ExplanatoryAssertionGroupBuilder(WarningAssertionGroupType_getInstance());
  }
  AssertionGroup$Builder$ExplanatoryAssertionGroupOption.prototype.withType_6l1juf$ = function (groupType) {
    return new AssertionGroup$Builder$ExplanatoryAssertionGroupBuilder(groupType);
  };
  AssertionGroup$Builder$ExplanatoryAssertionGroupOption.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExplanatoryAssertionGroupOption',
    interfaces: []
  };
  function AssertionGroup$Builder$ExplanatoryAssertionGroupBuilder(groupType) {
    this.groupType_0 = groupType;
  }
  AssertionGroup$Builder$ExplanatoryAssertionGroupBuilder.prototype.create_94orq3$ = function (assertion) {
    return this.create_94orq3$(assertion);
  };
  AssertionGroup$Builder$ExplanatoryAssertionGroupBuilder.prototype.create_tgi7xs$ = function (assertions) {
    return new ExplanatoryAssertionGroup(this.groupType_0, assertions);
  };
  AssertionGroup$Builder$ExplanatoryAssertionGroupBuilder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExplanatoryAssertionGroupBuilder',
    interfaces: []
  };
  function AssertionGroup$Builder$EmptyNameAndSubjectAssertionGroupBuilder(groupType) {
    this.groupType_0 = groupType;
  }
  AssertionGroup$Builder$EmptyNameAndSubjectAssertionGroupBuilder.prototype.create_94orq3$ = function (assertion) {
    return AssertionsOption$Companion_getInstance().withDefaultFinalStepAndEmptyDescriptionAndRepresentation_90qaeo$(this.groupType_0).withAssertion_94orq3$(assertion).build();
  };
  AssertionGroup$Builder$EmptyNameAndSubjectAssertionGroupBuilder.prototype.create_tgi7xs$ = function (assertions) {
    return AssertionsOption$Companion_getInstance().withDefaultFinalStepAndEmptyDescriptionAndRepresentation_90qaeo$(this.groupType_0).withAssertions_tgi7xs$(assertions).build();
  };
  AssertionGroup$Builder$EmptyNameAndSubjectAssertionGroupBuilder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EmptyNameAndSubjectAssertionGroupBuilder',
    interfaces: []
  };
  AssertionGroup$Builder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Builder',
    interfaces: []
  };
  var AssertionGroup$Builder_instance = null;
  function AssertionGroup$Builder_getInstance() {
    if (AssertionGroup$Builder_instance === null) {
      new AssertionGroup$Builder();
    }
    return AssertionGroup$Builder_instance;
  }
  AssertionGroup.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionGroup',
    interfaces: [Assertion]
  };
  function AssertionGroupType() {
  }
  AssertionGroupType.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionGroupType',
    interfaces: [BulletPointIdentifier]
  };
  function BasicAssertionGroup(type, description, representation, assertions) {
    this.type_xsvlbe$_0 = type;
    this.description_h044d4$_0 = description;
    this.representation_piorq5$_0 = representation;
    this.assertions_fl6och$_0 = assertions;
  }
  Object.defineProperty(BasicAssertionGroup.prototype, 'type', {
    get: function () {
      return this.type_xsvlbe$_0;
    }
  });
  Object.defineProperty(BasicAssertionGroup.prototype, 'description', {
    get: function () {
      return this.description_h044d4$_0;
    }
  });
  Object.defineProperty(BasicAssertionGroup.prototype, 'representation', {
    get: function () {
      return this.representation_piorq5$_0;
    }
  });
  Object.defineProperty(BasicAssertionGroup.prototype, 'assertions', {
    get: function () {
      return this.assertions_fl6och$_0;
    }
  });
  BasicAssertionGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BasicAssertionGroup',
    interfaces: [AssertionGroup]
  };
  BasicAssertionGroup.prototype.component1 = function () {
    return this.type;
  };
  BasicAssertionGroup.prototype.component2 = function () {
    return this.description;
  };
  BasicAssertionGroup.prototype.component3 = function () {
    return this.representation;
  };
  BasicAssertionGroup.prototype.component4 = function () {
    return this.assertions;
  };
  BasicAssertionGroup.prototype.copy_lywwvr$ = function (type, description, representation, assertions) {
    return new BasicAssertionGroup(type === void 0 ? this.type : type, description === void 0 ? this.description : description, representation === void 0 ? this.representation : representation, assertions === void 0 ? this.assertions : assertions);
  };
  BasicAssertionGroup.prototype.toString = function () {
    return 'BasicAssertionGroup(type=' + Kotlin.toString(this.type) + (', description=' + Kotlin.toString(this.description)) + (', representation=' + Kotlin.toString(this.representation)) + (', assertions=' + Kotlin.toString(this.assertions)) + ')';
  };
  BasicAssertionGroup.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.type) | 0;
    result = result * 31 + Kotlin.hashCode(this.description) | 0;
    result = result * 31 + Kotlin.hashCode(this.representation) | 0;
    result = result * 31 + Kotlin.hashCode(this.assertions) | 0;
    return result;
  };
  BasicAssertionGroup.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.type, other.type) && Kotlin.equals(this.description, other.description) && Kotlin.equals(this.representation, other.representation) && Kotlin.equals(this.assertions, other.assertions)))));
  };
  function BasicDescriptiveAssertion(description, representation, test) {
    this.description_cgyp5b$_0 = description;
    this.representation_x8gmsc$_0 = representation;
    this.test_0 = test;
  }
  Object.defineProperty(BasicDescriptiveAssertion.prototype, 'description', {
    get: function () {
      return this.description_cgyp5b$_0;
    }
  });
  Object.defineProperty(BasicDescriptiveAssertion.prototype, 'representation', {
    get: function () {
      return this.representation_x8gmsc$_0;
    }
  });
  BasicDescriptiveAssertion.prototype.holds = function () {
    return this.test_0();
  };
  BasicDescriptiveAssertion.prototype.toString = function () {
    return this.description.toString() + ': ' + this.representation.toString() + ' (holds=' + this.safeHoldsForToString_0() + ')';
  };
  BasicDescriptiveAssertion.prototype.safeHoldsForToString_0 = function () {
    try {
      return this.holds().toString();
    }
     catch (e) {
      if (Kotlin.isType(e, PlantHasNoSubjectException)) {
        return 'PlantHasNoSubjectException';
      }
       else
        throw e;
    }
  };
  BasicDescriptiveAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BasicDescriptiveAssertion',
    interfaces: [DescriptiveAssertion]
  };
  function BasicDescriptiveAssertion_init(description, representation, holds, $this) {
    $this = $this || Object.create(BasicDescriptiveAssertion.prototype);
    BasicDescriptiveAssertion.call($this, description, representation, BasicDescriptiveAssertion_init$lambda(holds));
    return $this;
  }
  function BasicDescriptiveAssertion_init$lambda(closure$holds) {
    return function () {
      return closure$holds;
    };
  }
  function BasicExplanatoryAssertion(explanation) {
    this.explanation_tzy46r$_0 = explanation;
  }
  Object.defineProperty(BasicExplanatoryAssertion.prototype, 'explanation', {
    get: function () {
      return this.explanation_tzy46r$_0;
    }
  });
  BasicExplanatoryAssertion.prototype.toString = function () {
    return toString(this.explanation);
  };
  BasicExplanatoryAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BasicExplanatoryAssertion',
    interfaces: [ExplanatoryAssertion]
  };
  function BulletPointIdentifier() {
  }
  BulletPointIdentifier.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BulletPointIdentifier',
    interfaces: []
  };
  function DescriptiveAssertion() {
  }
  Object.defineProperty(DescriptiveAssertion.prototype, 'expected', {
    get: function () {
      return this.representation;
    }
  });
  DescriptiveAssertion.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DescriptiveAssertion',
    interfaces: [Assertion]
  };
  function DoNotFilterAssertionGroupType() {
  }
  DoNotFilterAssertionGroupType.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DoNotFilterAssertionGroupType',
    interfaces: [AssertionGroupType]
  };
  function EmptyNameAndRepresentationAssertionGroup(type, assertions) {
    this.type_yzs9ha$_0 = type;
    this.assertions_pn5dyj$_0 = assertions;
    this.description_hdr4g4$_0 = Untranslatable$Companion_getInstance().EMPTY;
    this.representation_i7u08h$_0 = RawString$Companion_getInstance().EMPTY;
  }
  Object.defineProperty(EmptyNameAndRepresentationAssertionGroup.prototype, 'type', {
    get: function () {
      return this.type_yzs9ha$_0;
    }
  });
  Object.defineProperty(EmptyNameAndRepresentationAssertionGroup.prototype, 'assertions', {
    get: function () {
      return this.assertions_pn5dyj$_0;
    }
  });
  Object.defineProperty(EmptyNameAndRepresentationAssertionGroup.prototype, 'description', {
    get: function () {
      return this.description_hdr4g4$_0;
    }
  });
  Object.defineProperty(EmptyNameAndRepresentationAssertionGroup.prototype, 'representation', {
    get: function () {
      return this.representation_i7u08h$_0;
    }
  });
  EmptyNameAndRepresentationAssertionGroup.prototype.toString = function () {
    return Kotlin.getKClassFromExpression(this).simpleName + ' ' + toString(this.assertions);
  };
  EmptyNameAndRepresentationAssertionGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EmptyNameAndRepresentationAssertionGroup',
    interfaces: [AssertionGroup]
  };
  function ExplanatoryAssertion() {
  }
  ExplanatoryAssertion.prototype.holds = function () {
    return true;
  };
  ExplanatoryAssertion.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ExplanatoryAssertion',
    interfaces: [Assertion]
  };
  function ExplanatoryAssertionGroup(type, explanatoryAssertions) {
    EmptyNameAndRepresentationAssertionGroup.call(this, type, explanatoryAssertions);
  }
  ExplanatoryAssertionGroup.prototype.holds = function () {
    return true;
  };
  ExplanatoryAssertionGroup.prototype.toString = function () {
    return ensureNotNull(Kotlin.getKClassFromExpression(this).simpleName);
  };
  ExplanatoryAssertionGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExplanatoryAssertionGroup',
    interfaces: [EmptyNameAndRepresentationAssertionGroup]
  };
  function ExplanatoryAssertionGroupType() {
  }
  ExplanatoryAssertionGroupType.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ExplanatoryAssertionGroupType',
    interfaces: [DoNotFilterAssertionGroupType]
  };
  function DefaultExplanatoryAssertionGroupType() {
    DefaultExplanatoryAssertionGroupType_instance = this;
  }
  DefaultExplanatoryAssertionGroupType.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DefaultExplanatoryAssertionGroupType',
    interfaces: [ExplanatoryAssertionGroupType]
  };
  var DefaultExplanatoryAssertionGroupType_instance = null;
  function DefaultExplanatoryAssertionGroupType_getInstance() {
    if (DefaultExplanatoryAssertionGroupType_instance === null) {
      new DefaultExplanatoryAssertionGroupType();
    }
    return DefaultExplanatoryAssertionGroupType_instance;
  }
  function WarningAssertionGroupType() {
    WarningAssertionGroupType_instance = this;
  }
  WarningAssertionGroupType.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'WarningAssertionGroupType',
    interfaces: [ExplanatoryAssertionGroupType]
  };
  var WarningAssertionGroupType_instance = null;
  function WarningAssertionGroupType_getInstance() {
    if (WarningAssertionGroupType_instance === null) {
      new WarningAssertionGroupType();
    }
    return WarningAssertionGroupType_instance;
  }
  function FeatureAssertionGroupType() {
  }
  FeatureAssertionGroupType.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FeatureAssertionGroupType',
    interfaces: [AssertionGroupType]
  };
  function PrefixFeatureAssertionGroupHeader() {
  }
  PrefixFeatureAssertionGroupHeader.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PrefixFeatureAssertionGroupHeader',
    interfaces: [BulletPointIdentifier]
  };
  function DefaultFeatureAssertionGroupType() {
    DefaultFeatureAssertionGroupType_instance = this;
  }
  DefaultFeatureAssertionGroupType.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DefaultFeatureAssertionGroupType',
    interfaces: [FeatureAssertionGroupType]
  };
  var DefaultFeatureAssertionGroupType_instance = null;
  function DefaultFeatureAssertionGroupType_getInstance() {
    if (DefaultFeatureAssertionGroupType_instance === null) {
      new DefaultFeatureAssertionGroupType();
    }
    return DefaultFeatureAssertionGroupType_instance;
  }
  function IndentAssertionGroup(assertions) {
    EmptyNameAndRepresentationAssertionGroup.call(this, DefaultIndentAssertionGroupType_getInstance(), assertions);
  }
  IndentAssertionGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IndentAssertionGroup',
    interfaces: [EmptyNameAndRepresentationAssertionGroup]
  };
  function IndentAssertionGroupType() {
  }
  IndentAssertionGroupType.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IndentAssertionGroupType',
    interfaces: [AssertionGroupType]
  };
  function DefaultIndentAssertionGroupType() {
    DefaultIndentAssertionGroupType_instance = this;
  }
  DefaultIndentAssertionGroupType.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DefaultIndentAssertionGroupType',
    interfaces: [IndentAssertionGroupType]
  };
  var DefaultIndentAssertionGroupType_instance = null;
  function DefaultIndentAssertionGroupType_getInstance() {
    if (DefaultIndentAssertionGroupType_instance === null) {
      new DefaultIndentAssertionGroupType();
    }
    return DefaultIndentAssertionGroupType_instance;
  }
  function InvisibleAssertionGroup(assertions) {
    EmptyNameAndRepresentationAssertionGroup.call(this, InvisibleAssertionGroupType_getInstance(), assertions);
  }
  InvisibleAssertionGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'InvisibleAssertionGroup',
    interfaces: [EmptyNameAndRepresentationAssertionGroup]
  };
  function InvisibleAssertionGroupType() {
    InvisibleAssertionGroupType_instance = this;
  }
  InvisibleAssertionGroupType.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'InvisibleAssertionGroupType',
    interfaces: [AssertionGroupType]
  };
  var InvisibleAssertionGroupType_instance = null;
  function InvisibleAssertionGroupType_getInstance() {
    if (InvisibleAssertionGroupType_instance === null) {
      new InvisibleAssertionGroupType();
    }
    return InvisibleAssertionGroupType_instance;
  }
  function ListAssertionGroupType() {
  }
  ListAssertionGroupType.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ListAssertionGroupType',
    interfaces: [AssertionGroupType]
  };
  function DefaultListAssertionGroupType() {
    DefaultListAssertionGroupType_instance = this;
  }
  DefaultListAssertionGroupType.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DefaultListAssertionGroupType',
    interfaces: [ListAssertionGroupType]
  };
  var DefaultListAssertionGroupType_instance = null;
  function DefaultListAssertionGroupType_getInstance() {
    if (DefaultListAssertionGroupType_instance === null) {
      new DefaultListAssertionGroupType();
    }
    return DefaultListAssertionGroupType_instance;
  }
  function RepresentationOnlyAssertion() {
  }
  RepresentationOnlyAssertion.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'RepresentationOnlyAssertion',
    interfaces: [Assertion]
  };
  function RootAssertionGroupType() {
    RootAssertionGroupType_instance = this;
  }
  RootAssertionGroupType.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'RootAssertionGroupType',
    interfaces: [AssertionGroupType]
  };
  var RootAssertionGroupType_instance = null;
  function RootAssertionGroupType_getInstance() {
    if (RootAssertionGroupType_instance === null) {
      new RootAssertionGroupType();
    }
    return RootAssertionGroupType_instance;
  }
  function SummaryAssertionGroupType() {
  }
  SummaryAssertionGroupType.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SummaryAssertionGroupType',
    interfaces: [DoNotFilterAssertionGroupType]
  };
  function PrefixSuccessfulSummaryAssertion() {
  }
  PrefixSuccessfulSummaryAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PrefixSuccessfulSummaryAssertion',
    interfaces: [BulletPointIdentifier]
  };
  function PrefixFailingSummaryAssertion() {
  }
  PrefixFailingSummaryAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PrefixFailingSummaryAssertion',
    interfaces: [BulletPointIdentifier]
  };
  function DefaultSummaryAssertionGroupType() {
    DefaultSummaryAssertionGroupType_instance = this;
  }
  DefaultSummaryAssertionGroupType.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DefaultSummaryAssertionGroupType',
    interfaces: [SummaryAssertionGroupType]
  };
  var DefaultSummaryAssertionGroupType_instance = null;
  function DefaultSummaryAssertionGroupType_getInstance() {
    if (DefaultSummaryAssertionGroupType_instance === null) {
      new DefaultSummaryAssertionGroupType();
    }
    return DefaultSummaryAssertionGroupType_instance;
  }
  var assertionBuilder;
  function AssertionBuilder() {
  }
  AssertionBuilder.prototype.createDescriptive_5v4qxd$ = function (description, representation, test) {
    return this.createDescriptive_67aujf$(new Untranslatable(description), representation, test);
  };
  AssertionBuilder.prototype.createDescriptive_67aujf$ = function (description, representation, test) {
    return this.descriptive.withTest_u332lz$(test).withDescriptionAndRepresentation_75z4jm$(description, representation).build();
  };
  AssertionBuilder.prototype.createDescriptive_70d256$ = function (subjectProvider, description, representation, test) {
    return this.createDescriptive_84vzck$(subjectProvider, new Untranslatable(description), representation, test);
  };
  AssertionBuilder.prototype.createDescriptive_84vzck$ = function (subjectProvider, description, representation, test) {
    return this.descriptive.withTest_by3r04$(subjectProvider, test).withDescriptionAndRepresentation_75z4jm$(description, representation).build();
  };
  AssertionBuilder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionBuilder',
    interfaces: []
  };
  function AssertionBuilderFinalStep() {
  }
  AssertionBuilderFinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionBuilderFinalStep',
    interfaces: []
  };
  function AssertionGroupDescriptionAndEmptyRepresentationOption() {
    AssertionGroupDescriptionAndEmptyRepresentationOption$Companion_getInstance();
  }
  AssertionGroupDescriptionAndEmptyRepresentationOption.prototype.withDescription_61zpoe$ = function (description) {
    return this.withDescription_n3w7xm$(new Untranslatable(description));
  };
  function AssertionGroupDescriptionAndEmptyRepresentationOption$Companion() {
    AssertionGroupDescriptionAndEmptyRepresentationOption$Companion_instance = this;
  }
  AssertionGroupDescriptionAndEmptyRepresentationOption$Companion.prototype.create_xbaybz$ = function (type, factory) {
    return new AssertionGroupDescriptionAndEmptyRepresentationOptionImpl(type, factory);
  };
  AssertionGroupDescriptionAndEmptyRepresentationOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AssertionGroupDescriptionAndEmptyRepresentationOption$Companion_instance = null;
  function AssertionGroupDescriptionAndEmptyRepresentationOption$Companion_getInstance() {
    if (AssertionGroupDescriptionAndEmptyRepresentationOption$Companion_instance === null) {
      new AssertionGroupDescriptionAndEmptyRepresentationOption$Companion();
    }
    return AssertionGroupDescriptionAndEmptyRepresentationOption$Companion_instance;
  }
  AssertionGroupDescriptionAndEmptyRepresentationOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionGroupDescriptionAndEmptyRepresentationOption',
    interfaces: []
  };
  function AssertionGroupDescriptionAndRepresentationOption() {
    AssertionGroupDescriptionAndRepresentationOption$Companion_getInstance();
  }
  AssertionGroupDescriptionAndRepresentationOption.prototype.withDescriptionAndRepresentation_afmhsq$ = function (description, representationProvider) {
    return this.withDescriptionAndRepresentation_ymx9x6$(new Untranslatable(description), representationProvider);
  };
  AssertionGroupDescriptionAndRepresentationOption.prototype.withDescriptionAndRepresentation_ymx9x6$ = function (description, representationProvider) {
    return this.withDescriptionAndRepresentation_75z4jm$(description, new LazyRepresentation(representationProvider));
  };
  AssertionGroupDescriptionAndRepresentationOption.prototype.withDescriptionAndEmptyRepresentation_61zpoe$ = function (description) {
    return this.withDescriptionAndRepresentation_75z4jm$(new Untranslatable(description), RawString$Companion_getInstance().EMPTY);
  };
  AssertionGroupDescriptionAndRepresentationOption.prototype.withDescriptionAndEmptyRepresentation_n3w7xm$ = function (description) {
    return this.withDescriptionAndRepresentation_75z4jm$(description, RawString$Companion_getInstance().EMPTY);
  };
  AssertionGroupDescriptionAndRepresentationOption.prototype.withDescriptionAndRepresentation_4w9ihe$ = function (description, representation) {
    return this.withDescriptionAndRepresentation_75z4jm$(new Untranslatable(description), representation);
  };
  function AssertionGroupDescriptionAndRepresentationOption$Companion() {
    AssertionGroupDescriptionAndRepresentationOption$Companion_instance = this;
  }
  AssertionGroupDescriptionAndRepresentationOption$Companion.prototype.create_xbaybz$ = function (type, factory) {
    return new AssertionGroupDescriptionAndRepresentationOptionImpl(type, factory);
  };
  AssertionGroupDescriptionAndRepresentationOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AssertionGroupDescriptionAndRepresentationOption$Companion_instance = null;
  function AssertionGroupDescriptionAndRepresentationOption$Companion_getInstance() {
    if (AssertionGroupDescriptionAndRepresentationOption$Companion_instance === null) {
      new AssertionGroupDescriptionAndRepresentationOption$Companion();
    }
    return AssertionGroupDescriptionAndRepresentationOption$Companion_instance;
  }
  AssertionGroupDescriptionAndRepresentationOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionGroupDescriptionAndRepresentationOption',
    interfaces: []
  };
  function AssertionsOption() {
    AssertionsOption$Companion_getInstance();
  }
  AssertionsOption.prototype.withAssertion_94orq3$ = function (assertion) {
    return this.withAssertions_tgi7xs$(listOf(assertion));
  };
  AssertionsOption.prototype.withAssertions_goesb0$ = function (assertion1, assertion2) {
    return this.withAssertions_tgi7xs$(listOf_0([assertion1, assertion2]));
  };
  AssertionsOption.prototype.withAssertions_j1wybp$ = function (assertion1, assertion2, assertion3) {
    return this.withAssertions_tgi7xs$(listOf_0([assertion1, assertion2, assertion3]));
  };
  function AssertionsOption$Companion() {
    AssertionsOption$Companion_instance = this;
    this.factoryWithDefaultFinalStep_0 = AssertionsOption$Companion$factoryWithDefaultFinalStep$lambda;
  }
  AssertionsOption$Companion.prototype.create_32b8dm$ = function (groupType, description, representation, factory) {
    return new AssertionsOptionImpl(groupType, description, representation, factory);
  };
  AssertionsOption$Companion.prototype.withEmptyDescriptionAndRepresentation_wfx8n3$ = function (groupType, factory) {
    return new AssertionsOptionImpl(groupType, Untranslatable$Companion_getInstance().EMPTY, RawString$Companion_getInstance().EMPTY, factory);
  };
  AssertionsOption$Companion.prototype.withDefaultFinalStepAndEmptyDescriptionAndRepresentation_90qaeo$ = function (groupType) {
    return new AssertionsOptionImpl(groupType, Untranslatable$Companion_getInstance().EMPTY, RawString$Companion_getInstance().EMPTY, getCallableRef('create', function ($receiver, groupType, description, representation, assertions) {
      return $receiver.create_lywwvr$(groupType, description, representation, assertions);
    }.bind(null, BasicAssertionGroupFinalStep$Companion_getInstance())));
  };
  AssertionsOption$Companion.prototype.factoryWithDefaultFinalStep_picbvu$ = function () {
    var tmp$;
    var factory = typeof (tmp$ = this.factoryWithDefaultFinalStep_0) === 'function' ? tmp$ : throwCCE();
    return factory;
  };
  function AssertionsOption$Companion$factoryWithDefaultFinalStep$lambda(t, d, r) {
    return new AssertionsOptionImpl(t, d, r, getCallableRef('create', function ($receiver, groupType, description, representation, assertions) {
      return $receiver.create_lywwvr$(groupType, description, representation, assertions);
    }.bind(null, BasicAssertionGroupFinalStep$Companion_getInstance())));
  }
  AssertionsOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AssertionsOption$Companion_instance = null;
  function AssertionsOption$Companion_getInstance() {
    if (AssertionsOption$Companion_instance === null) {
      new AssertionsOption$Companion();
    }
    return AssertionsOption$Companion_instance;
  }
  AssertionsOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionsOption',
    interfaces: []
  };
  function BasicAssertionGroupFinalStep() {
    BasicAssertionGroupFinalStep$Companion_getInstance();
  }
  function BasicAssertionGroupFinalStep$Companion() {
    BasicAssertionGroupFinalStep$Companion_instance = this;
  }
  BasicAssertionGroupFinalStep$Companion.prototype.create_lywwvr$ = function (groupType, description, representation, assertions) {
    return new BasicAssertionGroupFinalStepImpl(groupType, description, representation, assertions);
  };
  BasicAssertionGroupFinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var BasicAssertionGroupFinalStep$Companion_instance = null;
  function BasicAssertionGroupFinalStep$Companion_getInstance() {
    if (BasicAssertionGroupFinalStep$Companion_instance === null) {
      new BasicAssertionGroupFinalStep$Companion();
    }
    return BasicAssertionGroupFinalStep$Companion_instance;
  }
  BasicAssertionGroupFinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BasicAssertionGroupFinalStep',
    interfaces: [AssertionBuilderFinalStep]
  };
  function SubjectBasedOption() {
    SubjectBasedOption$Companion_getInstance();
  }
  function SubjectBasedOption$DefinedOption() {
  }
  SubjectBasedOption$DefinedOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DefinedOption',
    interfaces: []
  };
  function SubjectBasedOption$AbsentOption() {
  }
  SubjectBasedOption$AbsentOption.prototype.ifAbsent_skz6mo$ = function (failureHintFactory) {
    return to(failureHintFactory, this.ifDefined);
  };
  SubjectBasedOption$AbsentOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AbsentOption',
    interfaces: []
  };
  function SubjectBasedOption$Companion() {
    SubjectBasedOption$Companion_instance = this;
  }
  SubjectBasedOption$Companion.prototype.invoke_borqw1$ = function (subjectProvider, subStep, presentOptionFactory) {
    var tmp$ = subStep(presentOptionFactory());
    var ifAbsent = tmp$.component1()
    , ifPresent = tmp$.component2();
    var $this = subjectProvider.maybeSubject;
    var fold_2f1hkh$result;
    if (Kotlin.isType($this, Some)) {
      fold_2f1hkh$result = ifPresent($this.value);
    }
     else if (equals($this, package$core.None)) {
      fold_2f1hkh$result = ifAbsent();
    }
     else {
      fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
    }
    return fold_2f1hkh$result;
  };
  SubjectBasedOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var SubjectBasedOption$Companion_instance = null;
  function SubjectBasedOption$Companion_getInstance() {
    if (SubjectBasedOption$Companion_instance === null) {
      new SubjectBasedOption$Companion();
    }
    return SubjectBasedOption$Companion_instance;
  }
  SubjectBasedOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SubjectBasedOption',
    interfaces: []
  };
  function HoldsStep() {
  }
  HoldsStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'HoldsStep',
    interfaces: []
  };
  function HoldsStepImpl() {
    this.failing_dq2xoe$_0 = this.withTest_u332lz$(HoldsStepImpl$failing$lambda);
    this.holding_5ytujl$_0 = this.withTest_u332lz$(HoldsStepImpl$holding$lambda);
  }
  Object.defineProperty(HoldsStepImpl.prototype, 'failing', {
    get: function () {
      return this.failing_dq2xoe$_0;
    }
  });
  Object.defineProperty(HoldsStepImpl.prototype, 'holding', {
    get: function () {
      return this.holding_5ytujl$_0;
    }
  });
  function HoldsStepImpl$withTest$lambda(closure$subjectProvider, closure$test) {
    return function () {
      var $this = closure$subjectProvider.maybeSubject;
      var default_0 = trueProvider;
      var f = closure$test;
      var fold_2f1hkh$result;
      if (Kotlin.isType($this, Some)) {
        fold_2f1hkh$result = f($this.value);
      }
       else if (equals($this, package$core.None)) {
        fold_2f1hkh$result = default_0();
      }
       else {
        fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
      }
      return fold_2f1hkh$result;
    };
  }
  HoldsStepImpl.prototype.withTest_by3r04$ = function (subjectProvider, test) {
    return this.withTest_u332lz$(HoldsStepImpl$withTest$lambda(subjectProvider, test));
  };
  HoldsStepImpl.prototype.withTest_u332lz$ = function (test) {
    return this.createNextStep_u332lz$(test);
  };
  function HoldsStepImpl$failing$lambda() {
    return false;
  }
  function HoldsStepImpl$holding$lambda() {
    return true;
  }
  HoldsStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'HoldsStepImpl',
    interfaces: [HoldsStep]
  };
  function Descriptive() {
  }
  function Descriptive$HoldsOption() {
    Descriptive$HoldsOption$Companion_getInstance();
  }
  function Descriptive$HoldsOption$Companion() {
    Descriptive$HoldsOption$Companion_instance = this;
  }
  Descriptive$HoldsOption$Companion.prototype.create = function () {
    return HoldsOptionImpl_getInstance();
  };
  Descriptive$HoldsOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Descriptive$HoldsOption$Companion_instance = null;
  function Descriptive$HoldsOption$Companion_getInstance() {
    if (Descriptive$HoldsOption$Companion_instance === null) {
      new Descriptive$HoldsOption$Companion();
    }
    return Descriptive$HoldsOption$Companion_instance;
  }
  Descriptive$HoldsOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'HoldsOption',
    interfaces: []
  };
  function Descriptive$DescriptionOption() {
    Descriptive$DescriptionOption$Companion_getInstance();
  }
  Descriptive$DescriptionOption.prototype.withDescriptionAndRepresentation_4w9ihe$ = function (description, representation) {
    return this.withDescriptionAndRepresentation_75z4jm$(new Untranslatable(description), representation);
  };
  function Descriptive$DescriptionOption$Companion() {
    Descriptive$DescriptionOption$Companion_instance = this;
  }
  Descriptive$DescriptionOption$Companion.prototype.create_1y9dqr$ = function (test, factory) {
    return new DescriptionOptionImpl(test, factory);
  };
  Descriptive$DescriptionOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Descriptive$DescriptionOption$Companion_instance = null;
  function Descriptive$DescriptionOption$Companion_getInstance() {
    if (Descriptive$DescriptionOption$Companion_instance === null) {
      new Descriptive$DescriptionOption$Companion();
    }
    return Descriptive$DescriptionOption$Companion_instance;
  }
  Descriptive$DescriptionOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DescriptionOption',
    interfaces: []
  };
  function Descriptive$FinalStep() {
    Descriptive$FinalStep$Companion_getInstance();
  }
  function Descriptive$FinalStep$Companion() {
    Descriptive$FinalStep$Companion_instance = this;
  }
  Descriptive$FinalStep$Companion.prototype.create_jhblha$ = function (test, description, representation) {
    return new FinalStepImpl(test, description, representation);
  };
  Descriptive$FinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Descriptive$FinalStep$Companion_instance = null;
  function Descriptive$FinalStep$Companion_getInstance() {
    if (Descriptive$FinalStep$Companion_instance === null) {
      new Descriptive$FinalStep$Companion();
    }
    return Descriptive$FinalStep$Companion_instance;
  }
  Descriptive$FinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FinalStep',
    interfaces: [AssertionBuilderFinalStep]
  };
  Descriptive.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Descriptive',
    interfaces: []
  };
  function withFailureHint($receiver, failureHintFactory) {
    return DescriptiveAssertionWithFailureHint$ShowOption$Companion_getInstance().create_ww6b52$($receiver.test, failureHintFactory);
  }
  function withFailureHintBasedOnDefinedSubject$lambda$lambda() {
    return withExplanatoryAssertion_0(assertionBuilder.explanatoryGroup.withWarningType, RawString$Companion_getInstance().create_61zpoe$(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG)).build();
  }
  function withFailureHintBasedOnDefinedSubject$lambda(closure$failureHintFactory) {
    return function ($receiver) {
      return $receiver.ifDefined_ru8m1r$(closure$failureHintFactory).ifAbsent_skz6mo$(withFailureHintBasedOnDefinedSubject$lambda$lambda);
    };
  }
  function withFailureHintBasedOnDefinedSubject($receiver, subjectProvider, failureHintFactory) {
    return withFailureHintBasedOnSubject($receiver, subjectProvider, withFailureHintBasedOnDefinedSubject$lambda(failureHintFactory)).showOnlyIfSubjectDefined_dcyqpd$(subjectProvider);
  }
  function withFailureHintBasedOnSubject$lambda(closure$subjectProvider, closure$failureHintSubStep) {
    return function () {
      return SubjectBasedOption$Companion_getInstance().invoke_borqw1$(closure$subjectProvider, closure$failureHintSubStep, getCallableRef('create', function ($receiver) {
        return $receiver.create_287e2$();
      }.bind(null, DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion_getInstance())));
    };
  }
  function withFailureHintBasedOnSubject($receiver, subjectProvider, failureHintSubStep) {
    return withFailureHint($receiver, withFailureHintBasedOnSubject$lambda(subjectProvider, failureHintSubStep));
  }
  function DescriptiveAssertionWithFailureHint() {
  }
  function DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption() {
    DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion_getInstance();
  }
  function DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion() {
    DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion_instance = this;
  }
  DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion.prototype.create_287e2$ = function () {
    return new FailureHintSubjectDefinedOptionImpl();
  };
  DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion_instance = null;
  function DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion_getInstance() {
    if (DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion_instance === null) {
      new DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion();
    }
    return DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion_instance;
  }
  DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FailureHintSubjectDefinedOption',
    interfaces: [SubjectBasedOption$DefinedOption]
  };
  function DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption() {
    DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion_getInstance();
  }
  function DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion() {
    DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion_instance = this;
  }
  DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion.prototype.create_qq538l$ = function (ifDefined) {
    return new FailureHintSubjectAbsentOptionImpl(ifDefined);
  };
  DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion_instance = null;
  function DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion_getInstance() {
    if (DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion_instance === null) {
      new DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion();
    }
    return DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion_instance;
  }
  DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FailureHintSubjectAbsentOption',
    interfaces: [SubjectBasedOption$AbsentOption]
  };
  function DescriptiveAssertionWithFailureHint$ShowOption() {
    DescriptiveAssertionWithFailureHint$ShowOption$Companion_getInstance();
  }
  function DescriptiveAssertionWithFailureHint$ShowOption$showOnlyIfSubjectDefined$lambda(closure$subjectProvider) {
    return function () {
      return closure$subjectProvider.maybeSubject.isDefined();
    };
  }
  DescriptiveAssertionWithFailureHint$ShowOption.prototype.showOnlyIfSubjectDefined_dcyqpd$ = function (subjectProvider) {
    return this.showOnlyIf_u332lz$(DescriptiveAssertionWithFailureHint$ShowOption$showOnlyIfSubjectDefined$lambda(subjectProvider));
  };
  function DescriptiveAssertionWithFailureHint$ShowOption$showBasedOnDefinedSubjectOnlyIf$lambda$lambda(closure$predicate) {
    return function (it) {
      return closure$predicate(it);
    };
  }
  function DescriptiveAssertionWithFailureHint$ShowOption$showBasedOnDefinedSubjectOnlyIf$lambda$lambda_0() {
    return false;
  }
  function DescriptiveAssertionWithFailureHint$ShowOption$showBasedOnDefinedSubjectOnlyIf$lambda(closure$predicate) {
    return function ($receiver) {
      return $receiver.ifDefined_ru8m1r$(DescriptiveAssertionWithFailureHint$ShowOption$showBasedOnDefinedSubjectOnlyIf$lambda$lambda(closure$predicate)).ifAbsent_skz6mo$(DescriptiveAssertionWithFailureHint$ShowOption$showBasedOnDefinedSubjectOnlyIf$lambda$lambda_0);
    };
  }
  DescriptiveAssertionWithFailureHint$ShowOption.prototype.showBasedOnDefinedSubjectOnlyIf_by3r04$ = function (subjectProvider, predicate) {
    return this.showBasedOnSubjectOnlyIf_x68nkh$(subjectProvider, DescriptiveAssertionWithFailureHint$ShowOption$showBasedOnDefinedSubjectOnlyIf$lambda(predicate));
  };
  function DescriptiveAssertionWithFailureHint$ShowOption$showBasedOnSubjectOnlyIf$lambda(closure$subjectProvider, closure$showSubStep) {
    return function () {
      return SubjectBasedOption$Companion_getInstance().invoke_borqw1$(closure$subjectProvider, closure$showSubStep, getCallableRef('create', function ($receiver) {
        return $receiver.create_287e2$();
      }.bind(null, DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion_getInstance())));
    };
  }
  DescriptiveAssertionWithFailureHint$ShowOption.prototype.showBasedOnSubjectOnlyIf_x68nkh$ = function (subjectProvider, showSubStep) {
    return this.showOnlyIf_u332lz$(DescriptiveAssertionWithFailureHint$ShowOption$showBasedOnSubjectOnlyIf$lambda(subjectProvider, showSubStep));
  };
  function DescriptiveAssertionWithFailureHint$ShowOption$Companion() {
    DescriptiveAssertionWithFailureHint$ShowOption$Companion_instance = this;
  }
  DescriptiveAssertionWithFailureHint$ShowOption$Companion.prototype.create_ww6b52$ = function (test, failureHintFactory) {
    return new ShowOptionImpl(test, failureHintFactory);
  };
  DescriptiveAssertionWithFailureHint$ShowOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var DescriptiveAssertionWithFailureHint$ShowOption$Companion_instance = null;
  function DescriptiveAssertionWithFailureHint$ShowOption$Companion_getInstance() {
    if (DescriptiveAssertionWithFailureHint$ShowOption$Companion_instance === null) {
      new DescriptiveAssertionWithFailureHint$ShowOption$Companion();
    }
    return DescriptiveAssertionWithFailureHint$ShowOption$Companion_instance;
  }
  DescriptiveAssertionWithFailureHint$ShowOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ShowOption',
    interfaces: []
  };
  function DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption() {
    DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion_getInstance();
  }
  function DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion() {
    DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion_instance = this;
  }
  DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion.prototype.create_287e2$ = function () {
    return new ShowSubjectDefinedOptionImpl();
  };
  DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion_instance = null;
  function DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion_getInstance() {
    if (DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion_instance === null) {
      new DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion();
    }
    return DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion_instance;
  }
  DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ShowSubjectDefinedOption',
    interfaces: [SubjectBasedOption$DefinedOption]
  };
  function DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption() {
    DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion_getInstance();
  }
  function DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion() {
    DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion_instance = this;
  }
  DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion.prototype.create_14urrv$ = function (ifDefined) {
    return new ShowSubjectAbsentOptionImpl(ifDefined);
  };
  DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion_instance = null;
  function DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion_getInstance() {
    if (DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion_instance === null) {
      new DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion();
    }
    return DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion_instance;
  }
  DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ShowSubjectAbsentOption',
    interfaces: [SubjectBasedOption$AbsentOption]
  };
  function DescriptiveAssertionWithFailureHint$FinalStep() {
    DescriptiveAssertionWithFailureHint$FinalStep$Companion_getInstance();
  }
  function DescriptiveAssertionWithFailureHint$FinalStep$Companion() {
    DescriptiveAssertionWithFailureHint$FinalStep$Companion_instance = this;
  }
  DescriptiveAssertionWithFailureHint$FinalStep$Companion.prototype.create_qt8tzc$ = function (test, showHint, failureHintFactory, description, representation) {
    return new FinalStepImpl_0(test, showHint, failureHintFactory, description, representation);
  };
  DescriptiveAssertionWithFailureHint$FinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var DescriptiveAssertionWithFailureHint$FinalStep$Companion_instance = null;
  function DescriptiveAssertionWithFailureHint$FinalStep$Companion_getInstance() {
    if (DescriptiveAssertionWithFailureHint$FinalStep$Companion_instance === null) {
      new DescriptiveAssertionWithFailureHint$FinalStep$Companion();
    }
    return DescriptiveAssertionWithFailureHint$FinalStep$Companion_instance;
  }
  DescriptiveAssertionWithFailureHint$FinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FinalStep',
    interfaces: [AssertionBuilderFinalStep]
  };
  DescriptiveAssertionWithFailureHint.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DescriptiveAssertionWithFailureHint',
    interfaces: []
  };
  function Explanatory() {
  }
  function Explanatory$ExplanationOption() {
    Explanatory$ExplanationOption$Companion_getInstance();
  }
  Explanatory$ExplanationOption.prototype.withExplanation_5pe57p$ = function (translatable, arg, otherArgs) {
    return this.withExplanation_n3w7xm$(new TranslatableWithArgs(translatable, varargToList(arg, otherArgs)));
  };
  Explanatory$ExplanationOption.prototype.withExplanation_61zpoe$ = function (description) {
    return this.withExplanation_n3w7xm$(new Untranslatable(description));
  };
  Explanatory$ExplanationOption.prototype.withExplanation_n3w7xm$ = function (translatable) {
    return this.withExplanation_s8jyv4$(RawString$Companion_getInstance().create_n3w7xm$(translatable));
  };
  Explanatory$ExplanationOption.prototype.withDescription_5pe57p$ = function (translatable, arg, otherArgs) {
    return this.withExplanation_5pe57p$(translatable, arg, otherArgs.slice());
  };
  Explanatory$ExplanationOption.prototype.withDescription_n3w7xm$ = function (translatable) {
    return this.withExplanation_n3w7xm$(translatable);
  };
  Explanatory$ExplanationOption.prototype.withDescription_s8jyv4$ = function (explanation) {
    return this.withExplanation_s8jyv4$(explanation);
  };
  function Explanatory$ExplanationOption$Companion() {
    Explanatory$ExplanationOption$Companion_instance = this;
  }
  Explanatory$ExplanationOption$Companion.prototype.create = function () {
    return ExplanationOptionImpl_getInstance();
  };
  Explanatory$ExplanationOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Explanatory$ExplanationOption$Companion_instance = null;
  function Explanatory$ExplanationOption$Companion_getInstance() {
    if (Explanatory$ExplanationOption$Companion_instance === null) {
      new Explanatory$ExplanationOption$Companion();
    }
    return Explanatory$ExplanationOption$Companion_instance;
  }
  Explanatory$ExplanationOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ExplanationOption',
    interfaces: []
  };
  function Explanatory$FinalStep() {
    Explanatory$FinalStep$Companion_getInstance();
  }
  function Explanatory$FinalStep$Companion() {
    Explanatory$FinalStep$Companion_instance = this;
  }
  Explanatory$FinalStep$Companion.prototype.create_s8jyv4$ = function (explanation) {
    return new FinalStepImpl_1(explanation);
  };
  Explanatory$FinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Explanatory$FinalStep$Companion_instance = null;
  function Explanatory$FinalStep$Companion_getInstance() {
    if (Explanatory$FinalStep$Companion_instance === null) {
      new Explanatory$FinalStep$Companion();
    }
    return Explanatory$FinalStep$Companion_instance;
  }
  Explanatory$FinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FinalStep',
    interfaces: [AssertionBuilderFinalStep]
  };
  Explanatory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Explanatory',
    interfaces: []
  };
  function ExplanatoryGroup() {
  }
  function ExplanatoryGroup$GroupTypeOption() {
    ExplanatoryGroup$GroupTypeOption$Companion_getInstance();
  }
  function ExplanatoryGroup$GroupTypeOption$Companion() {
    ExplanatoryGroup$GroupTypeOption$Companion_instance = this;
  }
  ExplanatoryGroup$GroupTypeOption$Companion.prototype.create = function () {
    return GroupTypeOptionImpl_getInstance();
  };
  ExplanatoryGroup$GroupTypeOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ExplanatoryGroup$GroupTypeOption$Companion_instance = null;
  function ExplanatoryGroup$GroupTypeOption$Companion_getInstance() {
    if (ExplanatoryGroup$GroupTypeOption$Companion_instance === null) {
      new ExplanatoryGroup$GroupTypeOption$Companion();
    }
    return ExplanatoryGroup$GroupTypeOption$Companion_instance;
  }
  ExplanatoryGroup$GroupTypeOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'GroupTypeOption',
    interfaces: [ExplanatoryAssertionGroupTypeOption]
  };
  function ExplanatoryGroup$FinalStep() {
    ExplanatoryGroup$FinalStep$Companion_getInstance();
  }
  function ExplanatoryGroup$FinalStep$Companion() {
    ExplanatoryGroup$FinalStep$Companion_instance = this;
  }
  ExplanatoryGroup$FinalStep$Companion.prototype.create_a3z2th$ = function (groupType, explanatoryAssertions) {
    return new FinalStepImpl_2(groupType, explanatoryAssertions);
  };
  ExplanatoryGroup$FinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ExplanatoryGroup$FinalStep$Companion_instance = null;
  function ExplanatoryGroup$FinalStep$Companion_getInstance() {
    if (ExplanatoryGroup$FinalStep$Companion_instance === null) {
      new ExplanatoryGroup$FinalStep$Companion();
    }
    return ExplanatoryGroup$FinalStep$Companion_instance;
  }
  ExplanatoryGroup$FinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FinalStep',
    interfaces: [ExplanatoryAssertionGroupFinalStep, AssertionBuilderFinalStep]
  };
  ExplanatoryGroup.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ExplanatoryGroup',
    interfaces: []
  };
  function withExplanatoryAssertion($receiver, translatable) {
    return $receiver.withAssertion_94orq3$(package$builders.assertionBuilder.explanatory.withExplanation_n3w7xm$(translatable).build());
  }
  function withExplanatoryAssertion_0($receiver, representation) {
    return $receiver.withAssertion_94orq3$(package$builders.assertionBuilder.explanatory.withExplanation_s8jyv4$(representation).build());
  }
  function withExplanatoryAssertions($receiver, translatable, arg, otherArgs) {
    return withExplanatoryAssertion_1($receiver, translatable, arg, otherArgs.slice());
  }
  function withExplanatoryAssertion_1($receiver, translatable, arg, otherArgs) {
    return $receiver.withAssertion_94orq3$(package$builders.assertionBuilder.explanatory.withExplanation_5pe57p$(translatable, arg, otherArgs.slice()).build());
  }
  var withExplanatoryAssertion_2 = defineInlineFunction('atrium-core-api-js.ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion_dilg2v$', wrapFunction(function () {
    var builders = _.ch.tutteli.atrium.assertions.builders;
    return function ($receiver, explanationStep) {
      return $receiver.withAssertion_94orq3$(explanationStep(builders.assertionBuilder.explanatory));
    };
  }));
  function ExplanatoryAssertionGroupTypeOption() {
  }
  ExplanatoryAssertionGroupTypeOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ExplanatoryAssertionGroupTypeOption',
    interfaces: []
  };
  function ExplanatoryAssertionGroupFinalStep() {
    ExplanatoryAssertionGroupFinalStep$Companion_getInstance();
  }
  function ExplanatoryAssertionGroupFinalStep$Companion() {
    ExplanatoryAssertionGroupFinalStep$Companion_instance = this;
  }
  ExplanatoryAssertionGroupFinalStep$Companion.prototype.create_a3z2th$ = function (groupType, explanatoryAssertions) {
    return new FinalStepImpl_2(groupType, explanatoryAssertions);
  };
  ExplanatoryAssertionGroupFinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ExplanatoryAssertionGroupFinalStep$Companion_instance = null;
  function ExplanatoryAssertionGroupFinalStep$Companion_getInstance() {
    if (ExplanatoryAssertionGroupFinalStep$Companion_instance === null) {
      new ExplanatoryAssertionGroupFinalStep$Companion();
    }
    return ExplanatoryAssertionGroupFinalStep$Companion_instance;
  }
  ExplanatoryAssertionGroupFinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ExplanatoryAssertionGroupFinalStep',
    interfaces: [AssertionBuilderFinalStep]
  };
  function get_fixedClaimGroup($receiver) {
    return FixedClaimGroup$GroupTypeOption$Companion_getInstance().create();
  }
  function FixedClaimGroup() {
  }
  function FixedClaimGroup$GroupTypeOption() {
    FixedClaimGroup$GroupTypeOption$Companion_getInstance();
  }
  function FixedClaimGroup$GroupTypeOption$Companion() {
    FixedClaimGroup$GroupTypeOption$Companion_instance = this;
  }
  FixedClaimGroup$GroupTypeOption$Companion.prototype.create = function () {
    return GroupTypeOptionImpl_getInstance_0();
  };
  FixedClaimGroup$GroupTypeOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var FixedClaimGroup$GroupTypeOption$Companion_instance = null;
  function FixedClaimGroup$GroupTypeOption$Companion_getInstance() {
    if (FixedClaimGroup$GroupTypeOption$Companion_instance === null) {
      new FixedClaimGroup$GroupTypeOption$Companion();
    }
    return FixedClaimGroup$GroupTypeOption$Companion_instance;
  }
  FixedClaimGroup$GroupTypeOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'GroupTypeOption',
    interfaces: [FixedClaimLikeGroup$GroupTypeOption]
  };
  function FixedClaimGroup$HoldsOption() {
    FixedClaimGroup$HoldsOption$Companion_getInstance();
  }
  function FixedClaimGroup$HoldsOption$Companion() {
    FixedClaimGroup$HoldsOption$Companion_instance = this;
  }
  FixedClaimGroup$HoldsOption$Companion.prototype.create_90qaeo$ = function (groupType) {
    return new HoldsOptionImpl_0(groupType);
  };
  FixedClaimGroup$HoldsOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var FixedClaimGroup$HoldsOption$Companion_instance = null;
  function FixedClaimGroup$HoldsOption$Companion_getInstance() {
    if (FixedClaimGroup$HoldsOption$Companion_instance === null) {
      new FixedClaimGroup$HoldsOption$Companion();
    }
    return FixedClaimGroup$HoldsOption$Companion_instance;
  }
  FixedClaimGroup$HoldsOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'HoldsOption',
    interfaces: [FixedClaimLikeGroup$HoldsOption]
  };
  function FixedClaimGroup$FinalStep() {
    FixedClaimGroup$FinalStep$Companion_getInstance();
  }
  function FixedClaimGroup$FinalStep$Companion() {
    FixedClaimGroup$FinalStep$Companion_instance = this;
  }
  FixedClaimGroup$FinalStep$Companion.prototype.create_i5unq6$ = function (groupType, description, representation, assertions, holds) {
    return new FinalStepImpl_3(groupType, description, representation, assertions, holds);
  };
  FixedClaimGroup$FinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var FixedClaimGroup$FinalStep$Companion_instance = null;
  function FixedClaimGroup$FinalStep$Companion_getInstance() {
    if (FixedClaimGroup$FinalStep$Companion_instance === null) {
      new FixedClaimGroup$FinalStep$Companion();
    }
    return FixedClaimGroup$FinalStep$Companion_instance;
  }
  FixedClaimGroup$FinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FinalStep',
    interfaces: [BasicAssertionGroupFinalStep]
  };
  FixedClaimGroup.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FixedClaimGroup',
    interfaces: []
  };
  function FixedClaimLikeGroup() {
  }
  function FixedClaimLikeGroup$GroupTypeOption() {
  }
  Object.defineProperty(FixedClaimLikeGroup$GroupTypeOption.prototype, 'withFeatureType', {
    get: function () {
      return this.withType_90qaeo$(DefaultFeatureAssertionGroupType_getInstance());
    }
  });
  Object.defineProperty(FixedClaimLikeGroup$GroupTypeOption.prototype, 'withListType', {
    get: function () {
      return this.withType_90qaeo$(DefaultListAssertionGroupType_getInstance());
    }
  });
  FixedClaimLikeGroup$GroupTypeOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'GroupTypeOption',
    interfaces: []
  };
  function FixedClaimLikeGroup$HoldsOption() {
  }
  FixedClaimLikeGroup$HoldsOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'HoldsOption',
    interfaces: []
  };
  FixedClaimLikeGroup.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FixedClaimLikeGroup',
    interfaces: []
  };
  function AssertionBuilderImpl() {
    AssertionBuilderImpl_instance = this;
    this.list_5xmzd2$_0 = this.createDescriptionAndRepresentationOption_0(DefaultListAssertionGroupType_getInstance());
    this.feature_v9jwx2$_0 = this.createDescriptionAndRepresentationOption_0(DefaultFeatureAssertionGroupType_getInstance());
    this.summary_flchyi$_0 = AssertionGroupDescriptionAndEmptyRepresentationOption$Companion_getInstance().create_xbaybz$(DefaultSummaryAssertionGroupType_getInstance(), AssertionsOption$Companion_getInstance().factoryWithDefaultFinalStep_picbvu$());
    this.explanatoryGroup_73a27g$_0 = ExplanatoryGroup$GroupTypeOption$Companion_getInstance().create();
    this.descriptive_3c1thg$_0 = Descriptive$HoldsOption$Companion_getInstance().create();
    this.explanatory_ymp8if$_0 = Explanatory$ExplanationOption$Companion_getInstance().create();
    this.representationOnly_rwycx7$_0 = RepresentationOnly$HoldsStep$Companion_getInstance().create();
  }
  Object.defineProperty(AssertionBuilderImpl.prototype, 'list', {
    get: function () {
      return this.list_5xmzd2$_0;
    }
  });
  Object.defineProperty(AssertionBuilderImpl.prototype, 'feature', {
    get: function () {
      return this.feature_v9jwx2$_0;
    }
  });
  Object.defineProperty(AssertionBuilderImpl.prototype, 'summary', {
    get: function () {
      return this.summary_flchyi$_0;
    }
  });
  Object.defineProperty(AssertionBuilderImpl.prototype, 'explanatoryGroup', {
    get: function () {
      return this.explanatoryGroup_73a27g$_0;
    }
  });
  Object.defineProperty(AssertionBuilderImpl.prototype, 'descriptive', {
    get: function () {
      return this.descriptive_3c1thg$_0;
    }
  });
  Object.defineProperty(AssertionBuilderImpl.prototype, 'explanatory', {
    get: function () {
      return this.explanatory_ymp8if$_0;
    }
  });
  Object.defineProperty(AssertionBuilderImpl.prototype, 'representationOnly', {
    get: function () {
      return this.representationOnly_rwycx7$_0;
    }
  });
  AssertionBuilderImpl.prototype.customType_90qaeo$ = function (groupType) {
    return this.createDescriptionAndRepresentationOption_0(groupType);
  };
  AssertionBuilderImpl.prototype.createDescriptionAndRepresentationOption_0 = function (type) {
    return AssertionGroupDescriptionAndRepresentationOption$Companion_getInstance().create_xbaybz$(type, AssertionsOption$Companion_getInstance().factoryWithDefaultFinalStep_picbvu$());
  };
  AssertionBuilderImpl.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'AssertionBuilderImpl',
    interfaces: [AssertionBuilder]
  };
  var AssertionBuilderImpl_instance = null;
  function AssertionBuilderImpl_getInstance() {
    if (AssertionBuilderImpl_instance === null) {
      new AssertionBuilderImpl();
    }
    return AssertionBuilderImpl_instance;
  }
  function AssertionGroupDescriptionAndEmptyRepresentationOptionImpl(groupType, factory) {
    this.groupType_1tdr0p$_0 = groupType;
    this.factory_0 = factory;
  }
  Object.defineProperty(AssertionGroupDescriptionAndEmptyRepresentationOptionImpl.prototype, 'groupType', {
    get: function () {
      return this.groupType_1tdr0p$_0;
    }
  });
  AssertionGroupDescriptionAndEmptyRepresentationOptionImpl.prototype.withDescription_n3w7xm$ = function (description) {
    return this.factory_0(this.groupType, description, RawString$Companion_getInstance().EMPTY);
  };
  AssertionGroupDescriptionAndEmptyRepresentationOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssertionGroupDescriptionAndEmptyRepresentationOptionImpl',
    interfaces: [AssertionGroupDescriptionAndEmptyRepresentationOption]
  };
  function AssertionGroupDescriptionAndRepresentationOptionImpl(groupType, factory) {
    this.groupType_vkhyh6$_0 = groupType;
    this.factory_0 = factory;
  }
  Object.defineProperty(AssertionGroupDescriptionAndRepresentationOptionImpl.prototype, 'groupType', {
    get: function () {
      return this.groupType_vkhyh6$_0;
    }
  });
  AssertionGroupDescriptionAndRepresentationOptionImpl.prototype.withDescriptionAndRepresentation_75z4jm$ = function (description, representation) {
    return this.factory_0(this.groupType, description, representation != null ? representation : RawString$Companion_getInstance().NULL);
  };
  AssertionGroupDescriptionAndRepresentationOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssertionGroupDescriptionAndRepresentationOptionImpl',
    interfaces: [AssertionGroupDescriptionAndRepresentationOption]
  };
  function AssertionsOptionImpl(groupType, description, representation, factory) {
    this.groupType_v1k5ka$_0 = groupType;
    this.description_c883tl$_0 = description;
    this.representation_ds69hg$_0 = representation;
    this.factory_0 = factory;
  }
  Object.defineProperty(AssertionsOptionImpl.prototype, 'groupType', {
    get: function () {
      return this.groupType_v1k5ka$_0;
    }
  });
  Object.defineProperty(AssertionsOptionImpl.prototype, 'description', {
    get: function () {
      return this.description_c883tl$_0;
    }
  });
  Object.defineProperty(AssertionsOptionImpl.prototype, 'representation', {
    get: function () {
      return this.representation_ds69hg$_0;
    }
  });
  AssertionsOptionImpl.prototype.withAssertions_tgi7xs$ = function (assertions) {
    return this.factory_0(this.groupType, this.description, this.representation, assertions);
  };
  AssertionsOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssertionsOptionImpl',
    interfaces: [AssertionsOption]
  };
  function BasicAssertionGroupFinalStepImpl(groupType, description, representation, assertions) {
    this.groupType_x9c2zr$_0 = groupType;
    this.description_a6srxm$_0 = description;
    this.representation_qr1yh7$_0 = representation;
    this.assertions_s5znpt$_0 = assertions;
  }
  Object.defineProperty(BasicAssertionGroupFinalStepImpl.prototype, 'groupType', {
    get: function () {
      return this.groupType_x9c2zr$_0;
    }
  });
  Object.defineProperty(BasicAssertionGroupFinalStepImpl.prototype, 'description', {
    get: function () {
      return this.description_a6srxm$_0;
    }
  });
  Object.defineProperty(BasicAssertionGroupFinalStepImpl.prototype, 'representation', {
    get: function () {
      return this.representation_qr1yh7$_0;
    }
  });
  Object.defineProperty(BasicAssertionGroupFinalStepImpl.prototype, 'assertions', {
    get: function () {
      return this.assertions_s5znpt$_0;
    }
  });
  BasicAssertionGroupFinalStepImpl.prototype.build = function () {
    return new BasicAssertionGroup(this.groupType, this.description, this.representation, this.assertions);
  };
  BasicAssertionGroupFinalStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BasicAssertionGroupFinalStepImpl',
    interfaces: [BasicAssertionGroupFinalStep]
  };
  function FixedClaimLikeAssertionGroupHoldsOptionImpl(groupType) {
    this.groupType_fcgdxb$_0 = groupType;
  }
  Object.defineProperty(FixedClaimLikeAssertionGroupHoldsOptionImpl.prototype, 'groupType', {
    get: function () {
      return this.groupType_fcgdxb$_0;
    }
  });
  Object.defineProperty(FixedClaimLikeAssertionGroupHoldsOptionImpl.prototype, 'holding', {
    get: function () {
      return this.createDescriptionAndRepresentationOption_0(true);
    }
  });
  Object.defineProperty(FixedClaimLikeAssertionGroupHoldsOptionImpl.prototype, 'failing', {
    get: function () {
      return this.createDescriptionAndRepresentationOption_0(false);
    }
  });
  FixedClaimLikeAssertionGroupHoldsOptionImpl.prototype.withClaim_6taknv$ = function (holds) {
    return this.createDescriptionAndRepresentationOption_0(holds);
  };
  FixedClaimLikeAssertionGroupHoldsOptionImpl.prototype.createDescriptionAndRepresentationOption_0 = function (holds) {
    return AssertionGroupDescriptionAndRepresentationOption$Companion_getInstance().create_xbaybz$(this.groupType, this.createAssertionOptionWithHolds_0(holds));
  };
  function FixedClaimLikeAssertionGroupHoldsOptionImpl$createAssertionOptionWithHolds$lambda(closure$holds, this$FixedClaimLikeAssertionGroupHoldsOptionImpl) {
    return function (t, d, r) {
      return AssertionsOption$Companion_getInstance().create_32b8dm$(t, d, r, this$FixedClaimLikeAssertionGroupHoldsOptionImpl.createFixedClaimLikeAssertionGroupFinalStep_6taknv$(closure$holds));
    };
  }
  FixedClaimLikeAssertionGroupHoldsOptionImpl.prototype.createAssertionOptionWithHolds_0 = function (holds) {
    return FixedClaimLikeAssertionGroupHoldsOptionImpl$createAssertionOptionWithHolds$lambda(holds, this);
  };
  FixedClaimLikeAssertionGroupHoldsOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FixedClaimLikeAssertionGroupHoldsOptionImpl',
    interfaces: [FixedClaimLikeGroup$HoldsOption]
  };
  function HoldsOptionImpl() {
    HoldsOptionImpl_instance = this;
    this.failing_pt1zfg$_0 = this.withTest_u332lz$(HoldsOptionImpl$failing$lambda);
    this.holding_64577h$_0 = this.withTest_u332lz$(HoldsOptionImpl$holding$lambda);
  }
  Object.defineProperty(HoldsOptionImpl.prototype, 'failing', {
    get: function () {
      return this.failing_pt1zfg$_0;
    }
  });
  Object.defineProperty(HoldsOptionImpl.prototype, 'holding', {
    get: function () {
      return this.holding_64577h$_0;
    }
  });
  HoldsOptionImpl.prototype.withTest_u332lz$ = function (test) {
    return Descriptive$DescriptionOption$Companion_getInstance().create_1y9dqr$(test, getCallableRef('create', function ($receiver, test, description, representation) {
      return $receiver.create_jhblha$(test, description, representation);
    }.bind(null, Descriptive$FinalStep$Companion_getInstance())));
  };
  function HoldsOptionImpl$withTest$lambda(closure$subjectProvider, closure$test) {
    return function () {
      var $this = closure$subjectProvider.maybeSubject;
      var default_0 = trueProvider;
      var f = closure$test;
      var fold_2f1hkh$result;
      if (Kotlin.isType($this, Some)) {
        fold_2f1hkh$result = f($this.value);
      }
       else if (equals($this, package$core.None)) {
        fold_2f1hkh$result = default_0();
      }
       else {
        fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
      }
      return fold_2f1hkh$result;
    };
  }
  HoldsOptionImpl.prototype.withTest_by3r04$ = function (subjectProvider, test) {
    return this.withTest_u332lz$(HoldsOptionImpl$withTest$lambda(subjectProvider, test));
  };
  function HoldsOptionImpl$failing$lambda() {
    return false;
  }
  function HoldsOptionImpl$holding$lambda() {
    return true;
  }
  HoldsOptionImpl.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'HoldsOptionImpl',
    interfaces: [Descriptive$HoldsOption]
  };
  var HoldsOptionImpl_instance = null;
  function HoldsOptionImpl_getInstance() {
    if (HoldsOptionImpl_instance === null) {
      new HoldsOptionImpl();
    }
    return HoldsOptionImpl_instance;
  }
  function DescriptionOptionImpl(test, factory) {
    this.test_n70qhi$_0 = test;
    this.factory_0 = factory;
  }
  Object.defineProperty(DescriptionOptionImpl.prototype, 'test', {
    get: function () {
      return this.test_n70qhi$_0;
    }
  });
  DescriptionOptionImpl.prototype.withDescriptionAndRepresentation_75z4jm$ = function (description, representation) {
    return this.factory_0(this.test, description, representation != null ? representation : RawString$Companion_getInstance().NULL);
  };
  DescriptionOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionOptionImpl',
    interfaces: [Descriptive$DescriptionOption]
  };
  function FinalStepImpl(test, description, representation) {
    this.test_v86yyj$_0 = test;
    this.description_w8wjpn$_0 = description;
    this.representation_hsua00$_0 = representation;
  }
  Object.defineProperty(FinalStepImpl.prototype, 'test', {
    get: function () {
      return this.test_v86yyj$_0;
    }
  });
  Object.defineProperty(FinalStepImpl.prototype, 'description', {
    get: function () {
      return this.description_w8wjpn$_0;
    }
  });
  Object.defineProperty(FinalStepImpl.prototype, 'representation', {
    get: function () {
      return this.representation_hsua00$_0;
    }
  });
  FinalStepImpl.prototype.build = function () {
    return new BasicDescriptiveAssertion(this.description, this.representation, this.test);
  };
  FinalStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FinalStepImpl',
    interfaces: [Descriptive$FinalStep]
  };
  function FailureHintSubjectDefinedOptionImpl() {
  }
  FailureHintSubjectDefinedOptionImpl.prototype.ifDefined_ru8m1r$ = function (failureHintFactory) {
    return DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion_getInstance().create_qq538l$(failureHintFactory);
  };
  FailureHintSubjectDefinedOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FailureHintSubjectDefinedOptionImpl',
    interfaces: [DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption]
  };
  function FailureHintSubjectAbsentOptionImpl(ifDefined) {
    this.ifDefined_6f3nst$_0 = ifDefined;
  }
  Object.defineProperty(FailureHintSubjectAbsentOptionImpl.prototype, 'ifDefined', {
    get: function () {
      return this.ifDefined_6f3nst$_0;
    }
  });
  FailureHintSubjectAbsentOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FailureHintSubjectAbsentOptionImpl',
    interfaces: [DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption]
  };
  function ShowOptionImpl(test, failureHintFactory) {
    this.test_0 = test;
    this.failureHintFactory_0 = failureHintFactory;
    this.showForAnyFailure_1m9eh0$_0 = this.createDescriptiveLikeAssertionDescriptionOption_0(trueProvider);
  }
  Object.defineProperty(ShowOptionImpl.prototype, 'showForAnyFailure', {
    get: function () {
      return this.showForAnyFailure_1m9eh0$_0;
    }
  });
  ShowOptionImpl.prototype.showOnlyIf_u332lz$ = function (predicate) {
    return this.createDescriptiveLikeAssertionDescriptionOption_0(predicate);
  };
  function ShowOptionImpl$createDescriptiveLikeAssertionDescriptionOption$lambda(closure$predicate, this$ShowOptionImpl) {
    return function (t, d, r) {
      return DescriptiveAssertionWithFailureHint$FinalStep$Companion_getInstance().create_qt8tzc$(t, closure$predicate, this$ShowOptionImpl.failureHintFactory_0, d, r);
    };
  }
  ShowOptionImpl.prototype.createDescriptiveLikeAssertionDescriptionOption_0 = function (predicate) {
    return Descriptive$DescriptionOption$Companion_getInstance().create_1y9dqr$(this.test_0, ShowOptionImpl$createDescriptiveLikeAssertionDescriptionOption$lambda(predicate, this));
  };
  ShowOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ShowOptionImpl',
    interfaces: [DescriptiveAssertionWithFailureHint$ShowOption]
  };
  function ShowSubjectDefinedOptionImpl() {
  }
  ShowSubjectDefinedOptionImpl.prototype.ifDefined_ru8m1r$ = function (failureHintFactory) {
    return DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion_getInstance().create_14urrv$(failureHintFactory);
  };
  ShowSubjectDefinedOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ShowSubjectDefinedOptionImpl',
    interfaces: [DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption]
  };
  function ShowSubjectAbsentOptionImpl(ifDefined) {
    this.ifDefined_vtv4z3$_0 = ifDefined;
  }
  Object.defineProperty(ShowSubjectAbsentOptionImpl.prototype, 'ifDefined', {
    get: function () {
      return this.ifDefined_vtv4z3$_0;
    }
  });
  ShowSubjectAbsentOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ShowSubjectAbsentOptionImpl',
    interfaces: [DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption]
  };
  function FinalStepImpl_0(test, showHint, failureHintFactory, description, representation) {
    this.test_rl78x8$_0 = test;
    this.showHint_cffl3u$_0 = showHint;
    this.failureHintFactory_9d5b23$_0 = failureHintFactory;
    this.description_e7rtfq$_0 = description;
    this.representation_p1u4mn$_0 = representation;
  }
  Object.defineProperty(FinalStepImpl_0.prototype, 'test', {
    get: function () {
      return this.test_rl78x8$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_0.prototype, 'showHint', {
    get: function () {
      return this.showHint_cffl3u$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_0.prototype, 'failureHintFactory', {
    get: function () {
      return this.failureHintFactory_9d5b23$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_0.prototype, 'description', {
    get: function () {
      return this.description_e7rtfq$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_0.prototype, 'representation', {
    get: function () {
      return this.representation_p1u4mn$_0;
    }
  });
  function FinalStepImpl$build$lambda(closure$holds) {
    return function () {
      return closure$holds;
    };
  }
  FinalStepImpl_0.prototype.build = function () {
    var tmp$, tmp$_0;
    try {
      tmp$ = this.test();
    }
     catch (e) {
      if (Kotlin.isType(e, PlantHasNoSubjectException)) {
        tmp$ = false;
      }
       else
        throw e;
    }
    var holds = tmp$;
    if (holds || !this.showHint()) {
      tmp$_0 = assertionBuilder.createDescriptive_67aujf$(this.description, this.representation, FinalStepImpl$build$lambda(holds));
    }
     else {
      tmp$_0 = get_fixedClaimGroup(assertionBuilder).withListType.failing.withDescriptionAndRepresentation_75z4jm$(this.description, this.representation).withAssertion_94orq3$(this.failureHintFactory()).build();
    }
    return tmp$_0;
  };
  FinalStepImpl_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FinalStepImpl',
    interfaces: [DescriptiveAssertionWithFailureHint$FinalStep]
  };
  function ExplanationOptionImpl() {
    ExplanationOptionImpl_instance = this;
  }
  ExplanationOptionImpl.prototype.withExplanation_s8jyv4$ = function (explanation) {
    return Explanatory$FinalStep$Companion_getInstance().create_s8jyv4$(explanation);
  };
  ExplanationOptionImpl.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ExplanationOptionImpl',
    interfaces: [Explanatory$ExplanationOption]
  };
  var ExplanationOptionImpl_instance = null;
  function ExplanationOptionImpl_getInstance() {
    if (ExplanationOptionImpl_instance === null) {
      new ExplanationOptionImpl();
    }
    return ExplanationOptionImpl_instance;
  }
  function FinalStepImpl_1(explanation) {
    this.explanation_rcfo3x$_0 = explanation;
  }
  Object.defineProperty(FinalStepImpl_1.prototype, 'explanation', {
    get: function () {
      return this.explanation_rcfo3x$_0;
    }
  });
  FinalStepImpl_1.prototype.build = function () {
    return new BasicExplanatoryAssertion(this.explanation);
  };
  FinalStepImpl_1.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FinalStepImpl',
    interfaces: [Explanatory$FinalStep]
  };
  function GroupTypeOptionImpl() {
    GroupTypeOptionImpl_instance = this;
    this.withDefaultType_9matwc$_0 = this.createAssertionsOption_0(DefaultExplanatoryAssertionGroupType_getInstance());
    this.withWarningType_cyw2vz$_0 = this.createAssertionsOption_0(WarningAssertionGroupType_getInstance());
  }
  Object.defineProperty(GroupTypeOptionImpl.prototype, 'withDefaultType', {
    get: function () {
      return this.withDefaultType_9matwc$_0;
    }
  });
  Object.defineProperty(GroupTypeOptionImpl.prototype, 'withWarningType', {
    get: function () {
      return this.withWarningType_cyw2vz$_0;
    }
  });
  GroupTypeOptionImpl.prototype.withType_ve4y9n$ = function (groupType) {
    return this.createAssertionsOption_0(groupType);
  };
  GroupTypeOptionImpl.prototype.createAssertionsOption_0 = function (groupType) {
    return AssertionsOption$Companion_getInstance().withEmptyDescriptionAndRepresentation_wfx8n3$(groupType, getCallableRef('createExplanatoryAssertionGroupFinalStep', function ($receiver, groupType, ignoredDescription, ignoredRepresentation, explanatoryAssertions) {
      return $receiver.createExplanatoryAssertionGroupFinalStep_0(groupType, ignoredDescription, ignoredRepresentation, explanatoryAssertions);
    }.bind(null, GroupTypeOptionImpl_getInstance())));
  };
  GroupTypeOptionImpl.prototype.createExplanatoryAssertionGroupFinalStep_0 = function (groupType, ignoredDescription, ignoredRepresentation, explanatoryAssertions) {
    return ExplanatoryGroup$FinalStep$Companion_getInstance().create_a3z2th$(groupType, explanatoryAssertions);
  };
  GroupTypeOptionImpl.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'GroupTypeOptionImpl',
    interfaces: [ExplanatoryGroup$GroupTypeOption]
  };
  var GroupTypeOptionImpl_instance = null;
  function GroupTypeOptionImpl_getInstance() {
    if (GroupTypeOptionImpl_instance === null) {
      new GroupTypeOptionImpl();
    }
    return GroupTypeOptionImpl_instance;
  }
  function FinalStepImpl_2(groupType, explanatoryAssertions) {
    this.groupType_f9pku4$_0 = groupType;
    this.explanatoryAssertions_w3f3z9$_0 = explanatoryAssertions;
  }
  Object.defineProperty(FinalStepImpl_2.prototype, 'groupType', {
    get: function () {
      return this.groupType_f9pku4$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_2.prototype, 'explanatoryAssertions', {
    get: function () {
      return this.explanatoryAssertions_w3f3z9$_0;
    }
  });
  FinalStepImpl_2.prototype.build = function () {
    return new ExplanatoryAssertionGroup(this.groupType, this.explanatoryAssertions);
  };
  FinalStepImpl_2.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FinalStepImpl',
    interfaces: [ExplanatoryGroup$FinalStep]
  };
  function FixedClaimAssertionGroup(type, description, representation, assertions, holds) {
    this.type_wo7cdp$_0 = type;
    this.description_l9wcmp$_0 = description;
    this.representation_bka58m$_0 = representation;
    this.assertions_9rrawa$_0 = assertions;
    this.holds_0 = holds;
  }
  Object.defineProperty(FixedClaimAssertionGroup.prototype, 'type', {
    get: function () {
      return this.type_wo7cdp$_0;
    }
  });
  Object.defineProperty(FixedClaimAssertionGroup.prototype, 'description', {
    get: function () {
      return this.description_l9wcmp$_0;
    }
  });
  Object.defineProperty(FixedClaimAssertionGroup.prototype, 'representation', {
    get: function () {
      return this.representation_bka58m$_0;
    }
  });
  Object.defineProperty(FixedClaimAssertionGroup.prototype, 'assertions', {
    get: function () {
      return this.assertions_9rrawa$_0;
    }
  });
  FixedClaimAssertionGroup.prototype.holds = function () {
    return this.holds_0;
  };
  FixedClaimAssertionGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FixedClaimAssertionGroup',
    interfaces: [AssertionGroup]
  };
  FixedClaimAssertionGroup.prototype.component1 = function () {
    return this.type;
  };
  FixedClaimAssertionGroup.prototype.component2 = function () {
    return this.description;
  };
  FixedClaimAssertionGroup.prototype.component3 = function () {
    return this.representation;
  };
  FixedClaimAssertionGroup.prototype.component4 = function () {
    return this.assertions;
  };
  FixedClaimAssertionGroup.prototype.component5_0 = function () {
    return this.holds_0;
  };
  FixedClaimAssertionGroup.prototype.copy_i5unq6$ = function (type, description, representation, assertions, holds) {
    return new FixedClaimAssertionGroup(type === void 0 ? this.type : type, description === void 0 ? this.description : description, representation === void 0 ? this.representation : representation, assertions === void 0 ? this.assertions : assertions, holds === void 0 ? this.holds_0 : holds);
  };
  FixedClaimAssertionGroup.prototype.toString = function () {
    return 'FixedClaimAssertionGroup(type=' + Kotlin.toString(this.type) + (', description=' + Kotlin.toString(this.description)) + (', representation=' + Kotlin.toString(this.representation)) + (', assertions=' + Kotlin.toString(this.assertions)) + (', holds=' + Kotlin.toString(this.holds_0)) + ')';
  };
  FixedClaimAssertionGroup.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.type) | 0;
    result = result * 31 + Kotlin.hashCode(this.description) | 0;
    result = result * 31 + Kotlin.hashCode(this.representation) | 0;
    result = result * 31 + Kotlin.hashCode(this.assertions) | 0;
    result = result * 31 + Kotlin.hashCode(this.holds_0) | 0;
    return result;
  };
  FixedClaimAssertionGroup.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.type, other.type) && Kotlin.equals(this.description, other.description) && Kotlin.equals(this.representation, other.representation) && Kotlin.equals(this.assertions, other.assertions) && Kotlin.equals(this.holds_0, other.holds_0)))));
  };
  function GroupTypeOptionImpl_0() {
    GroupTypeOptionImpl_instance_0 = this;
  }
  GroupTypeOptionImpl_0.prototype.withType_90qaeo$ = function (groupType) {
    return FixedClaimGroup$HoldsOption$Companion_getInstance().create_90qaeo$(groupType);
  };
  GroupTypeOptionImpl_0.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'GroupTypeOptionImpl',
    interfaces: [FixedClaimGroup$GroupTypeOption]
  };
  var GroupTypeOptionImpl_instance_0 = null;
  function GroupTypeOptionImpl_getInstance_0() {
    if (GroupTypeOptionImpl_instance_0 === null) {
      new GroupTypeOptionImpl_0();
    }
    return GroupTypeOptionImpl_instance_0;
  }
  function HoldsOptionImpl_0(groupType) {
    FixedClaimLikeAssertionGroupHoldsOptionImpl.call(this, groupType);
    this.groupType_j78rrw$_0 = groupType;
  }
  Object.defineProperty(HoldsOptionImpl_0.prototype, 'groupType', {
    get: function () {
      return this.groupType_j78rrw$_0;
    }
  });
  function HoldsOptionImpl$createFixedClaimLikeAssertionGroupFinalStep$lambda(closure$holds) {
    return function (t, d, r, a) {
      return FixedClaimGroup$FinalStep$Companion_getInstance().create_i5unq6$(t, d, r, a, closure$holds);
    };
  }
  HoldsOptionImpl_0.prototype.createFixedClaimLikeAssertionGroupFinalStep_6taknv$ = function (holds) {
    return HoldsOptionImpl$createFixedClaimLikeAssertionGroupFinalStep$lambda(holds);
  };
  HoldsOptionImpl_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'HoldsOptionImpl',
    interfaces: [FixedClaimGroup$HoldsOption, FixedClaimLikeAssertionGroupHoldsOptionImpl]
  };
  function FinalStepImpl_3(groupType, description, representation, assertions, holds) {
    this.groupType_mcbb1$_0 = groupType;
    this.description_uzd3wg$_0 = description;
    this.representation_e0huob$_0 = representation;
    this.assertions_phwbmh$_0 = assertions;
    this.holds_ekhtrc$_0 = holds;
  }
  Object.defineProperty(FinalStepImpl_3.prototype, 'groupType', {
    get: function () {
      return this.groupType_mcbb1$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_3.prototype, 'description', {
    get: function () {
      return this.description_uzd3wg$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_3.prototype, 'representation', {
    get: function () {
      return this.representation_e0huob$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_3.prototype, 'assertions', {
    get: function () {
      return this.assertions_phwbmh$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_3.prototype, 'holds', {
    get: function () {
      return this.holds_ekhtrc$_0;
    }
  });
  FinalStepImpl_3.prototype.build = function () {
    return new FixedClaimAssertionGroup(this.groupType, this.description, this.representation, this.assertions, this.holds);
  };
  FinalStepImpl_3.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FinalStepImpl',
    interfaces: [FixedClaimGroup$FinalStep]
  };
  function PartiallyFixedClaimAssertionGroup(type, description, representation, assertions, preTransformationHolds) {
    this.type_7anbc1$_0 = type;
    this.description_2p7tj3$_0 = description;
    this.representation_x8kqvo$_0 = representation;
    this.assertions_7ykkco$_0 = assertions;
    this.preTransformationHolds_0 = preTransformationHolds;
  }
  Object.defineProperty(PartiallyFixedClaimAssertionGroup.prototype, 'type', {
    get: function () {
      return this.type_7anbc1$_0;
    }
  });
  Object.defineProperty(PartiallyFixedClaimAssertionGroup.prototype, 'description', {
    get: function () {
      return this.description_2p7tj3$_0;
    }
  });
  Object.defineProperty(PartiallyFixedClaimAssertionGroup.prototype, 'representation', {
    get: function () {
      return this.representation_x8kqvo$_0;
    }
  });
  Object.defineProperty(PartiallyFixedClaimAssertionGroup.prototype, 'assertions', {
    get: function () {
      return this.assertions_7ykkco$_0;
    }
  });
  PartiallyFixedClaimAssertionGroup.prototype.holds = function () {
    return this.preTransformationHolds_0 && AssertionGroup.prototype.holds.call(this);
  };
  PartiallyFixedClaimAssertionGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PartiallyFixedClaimAssertionGroup',
    interfaces: [AssertionGroup]
  };
  PartiallyFixedClaimAssertionGroup.prototype.component1 = function () {
    return this.type;
  };
  PartiallyFixedClaimAssertionGroup.prototype.component2 = function () {
    return this.description;
  };
  PartiallyFixedClaimAssertionGroup.prototype.component3 = function () {
    return this.representation;
  };
  PartiallyFixedClaimAssertionGroup.prototype.component4 = function () {
    return this.assertions;
  };
  PartiallyFixedClaimAssertionGroup.prototype.component5_0 = function () {
    return this.preTransformationHolds_0;
  };
  PartiallyFixedClaimAssertionGroup.prototype.copy_i5unq6$ = function (type, description, representation, assertions, preTransformationHolds) {
    return new PartiallyFixedClaimAssertionGroup(type === void 0 ? this.type : type, description === void 0 ? this.description : description, representation === void 0 ? this.representation : representation, assertions === void 0 ? this.assertions : assertions, preTransformationHolds === void 0 ? this.preTransformationHolds_0 : preTransformationHolds);
  };
  PartiallyFixedClaimAssertionGroup.prototype.toString = function () {
    return 'PartiallyFixedClaimAssertionGroup(type=' + Kotlin.toString(this.type) + (', description=' + Kotlin.toString(this.description)) + (', representation=' + Kotlin.toString(this.representation)) + (', assertions=' + Kotlin.toString(this.assertions)) + (', preTransformationHolds=' + Kotlin.toString(this.preTransformationHolds_0)) + ')';
  };
  PartiallyFixedClaimAssertionGroup.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.type) | 0;
    result = result * 31 + Kotlin.hashCode(this.description) | 0;
    result = result * 31 + Kotlin.hashCode(this.representation) | 0;
    result = result * 31 + Kotlin.hashCode(this.assertions) | 0;
    result = result * 31 + Kotlin.hashCode(this.preTransformationHolds_0) | 0;
    return result;
  };
  PartiallyFixedClaimAssertionGroup.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.type, other.type) && Kotlin.equals(this.description, other.description) && Kotlin.equals(this.representation, other.representation) && Kotlin.equals(this.assertions, other.assertions) && Kotlin.equals(this.preTransformationHolds_0, other.preTransformationHolds_0)))));
  };
  function GroupTypeOptionImpl_1() {
    GroupTypeOptionImpl_instance_1 = this;
  }
  GroupTypeOptionImpl_1.prototype.withType_90qaeo$ = function (groupType) {
    return PartiallyFixedClaimGroup$HoldsOption$Companion_getInstance().create_90qaeo$(groupType);
  };
  GroupTypeOptionImpl_1.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'GroupTypeOptionImpl',
    interfaces: [PartiallyFixedClaimGroup$GroupTypeOption]
  };
  var GroupTypeOptionImpl_instance_1 = null;
  function GroupTypeOptionImpl_getInstance_1() {
    if (GroupTypeOptionImpl_instance_1 === null) {
      new GroupTypeOptionImpl_1();
    }
    return GroupTypeOptionImpl_instance_1;
  }
  function HoldsOptionImpl_1(groupType) {
    FixedClaimLikeAssertionGroupHoldsOptionImpl.call(this, groupType);
  }
  function HoldsOptionImpl$createFixedClaimLikeAssertionGroupFinalStep$lambda_0(closure$holds) {
    return function (t, d, r, a) {
      return PartiallyFixedClaimGroup$FinalStep$Companion_getInstance().create_i5unq6$(t, d, r, a, closure$holds);
    };
  }
  HoldsOptionImpl_1.prototype.createFixedClaimLikeAssertionGroupFinalStep_6taknv$ = function (holds) {
    return HoldsOptionImpl$createFixedClaimLikeAssertionGroupFinalStep$lambda_0(holds);
  };
  HoldsOptionImpl_1.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'HoldsOptionImpl',
    interfaces: [PartiallyFixedClaimGroup$HoldsOption, FixedClaimLikeAssertionGroupHoldsOptionImpl]
  };
  function FinalStepImpl_4(groupType, description, representation, assertions, preTransformationHolds) {
    this.groupType_bbf1wt$_0 = groupType;
    this.description_jyj700$_0 = description;
    this.representation_bmixj9$_0 = representation;
    this.assertions_1xoyi1$_0 = assertions;
    this.preTransformationHolds_h4wvpc$_0 = preTransformationHolds;
  }
  Object.defineProperty(FinalStepImpl_4.prototype, 'groupType', {
    get: function () {
      return this.groupType_bbf1wt$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_4.prototype, 'description', {
    get: function () {
      return this.description_jyj700$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_4.prototype, 'representation', {
    get: function () {
      return this.representation_bmixj9$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_4.prototype, 'assertions', {
    get: function () {
      return this.assertions_1xoyi1$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_4.prototype, 'preTransformationHolds', {
    get: function () {
      return this.preTransformationHolds_h4wvpc$_0;
    }
  });
  FinalStepImpl_4.prototype.build = function () {
    return new PartiallyFixedClaimAssertionGroup(this.groupType, this.description, this.representation, this.assertions, this.preTransformationHolds);
  };
  FinalStepImpl_4.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FinalStepImpl',
    interfaces: [PartiallyFixedClaimGroup$FinalStep]
  };
  function RepresentationOnlyAssertionImpl(test, representation) {
    this.test_0 = test;
    this.representation_rfnqi6$_0 = representation;
  }
  Object.defineProperty(RepresentationOnlyAssertionImpl.prototype, 'representation', {
    get: function () {
      return this.representation_rfnqi6$_0;
    }
  });
  RepresentationOnlyAssertionImpl.prototype.holds = function () {
    return this.test_0();
  };
  RepresentationOnlyAssertionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RepresentationOnlyAssertionImpl',
    interfaces: [RepresentationOnlyAssertion]
  };
  function HoldsStepImpl_0() {
    HoldsStepImpl_instance = this;
    HoldsStepImpl.call(this);
  }
  HoldsStepImpl_0.prototype.createNextStep_u332lz$ = function (test) {
    return RepresentationOnly$RepresentationStep$Companion_getInstance().create_u332lz$(test);
  };
  HoldsStepImpl_0.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'HoldsStepImpl',
    interfaces: [HoldsStepImpl, RepresentationOnly$HoldsStep]
  };
  var HoldsStepImpl_instance = null;
  function HoldsStepImpl_getInstance() {
    if (HoldsStepImpl_instance === null) {
      new HoldsStepImpl_0();
    }
    return HoldsStepImpl_instance;
  }
  function RepresentationStepImpl(test) {
    this.test_gnyzpz$_0 = test;
  }
  Object.defineProperty(RepresentationStepImpl.prototype, 'test', {
    get: function () {
      return this.test_gnyzpz$_0;
    }
  });
  RepresentationStepImpl.prototype.withRepresentation_s8jyv4$ = function (representation) {
    return RepresentationOnly$FinalStep$Companion_getInstance().create_kstd2b$(this.test, representation);
  };
  RepresentationStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RepresentationStepImpl',
    interfaces: [RepresentationOnly$RepresentationStep]
  };
  function FinalStepImpl_5(test, representation) {
    this.test_s4vzmi$_0 = test;
    this.representation_2m73p1$_0 = representation;
  }
  Object.defineProperty(FinalStepImpl_5.prototype, 'test', {
    get: function () {
      return this.test_s4vzmi$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_5.prototype, 'representation', {
    get: function () {
      return this.representation_2m73p1$_0;
    }
  });
  FinalStepImpl_5.prototype.build = function () {
    return new RepresentationOnlyAssertionImpl(this.test, this.representation);
  };
  FinalStepImpl_5.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FinalStepImpl',
    interfaces: [RepresentationOnly$FinalStep]
  };
  function invisibleGroup$lambda() {
    return AssertionsOption$Companion_getInstance().withDefaultFinalStepAndEmptyDescriptionAndRepresentation_90qaeo$(InvisibleAssertionGroupType_getInstance());
  }
  var invisibleGroup;
  function get_invisibleGroup($receiver) {
    return invisibleGroup.value;
  }
  function get_partiallyFixedClaimGroup($receiver) {
    return PartiallyFixedClaimGroup$GroupTypeOption$Companion_getInstance().create();
  }
  function PartiallyFixedClaimGroup() {
  }
  function PartiallyFixedClaimGroup$GroupTypeOption() {
    PartiallyFixedClaimGroup$GroupTypeOption$Companion_getInstance();
  }
  function PartiallyFixedClaimGroup$GroupTypeOption$Companion() {
    PartiallyFixedClaimGroup$GroupTypeOption$Companion_instance = this;
  }
  PartiallyFixedClaimGroup$GroupTypeOption$Companion.prototype.create = function () {
    return GroupTypeOptionImpl_getInstance_1();
  };
  PartiallyFixedClaimGroup$GroupTypeOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var PartiallyFixedClaimGroup$GroupTypeOption$Companion_instance = null;
  function PartiallyFixedClaimGroup$GroupTypeOption$Companion_getInstance() {
    if (PartiallyFixedClaimGroup$GroupTypeOption$Companion_instance === null) {
      new PartiallyFixedClaimGroup$GroupTypeOption$Companion();
    }
    return PartiallyFixedClaimGroup$GroupTypeOption$Companion_instance;
  }
  PartiallyFixedClaimGroup$GroupTypeOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'GroupTypeOption',
    interfaces: [FixedClaimLikeGroup$GroupTypeOption]
  };
  function PartiallyFixedClaimGroup$HoldsOption() {
    PartiallyFixedClaimGroup$HoldsOption$Companion_getInstance();
  }
  function PartiallyFixedClaimGroup$HoldsOption$Companion() {
    PartiallyFixedClaimGroup$HoldsOption$Companion_instance = this;
  }
  PartiallyFixedClaimGroup$HoldsOption$Companion.prototype.create_90qaeo$ = function (groupType) {
    return new HoldsOptionImpl_1(groupType);
  };
  PartiallyFixedClaimGroup$HoldsOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var PartiallyFixedClaimGroup$HoldsOption$Companion_instance = null;
  function PartiallyFixedClaimGroup$HoldsOption$Companion_getInstance() {
    if (PartiallyFixedClaimGroup$HoldsOption$Companion_instance === null) {
      new PartiallyFixedClaimGroup$HoldsOption$Companion();
    }
    return PartiallyFixedClaimGroup$HoldsOption$Companion_instance;
  }
  PartiallyFixedClaimGroup$HoldsOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'HoldsOption',
    interfaces: [FixedClaimLikeGroup$HoldsOption]
  };
  function PartiallyFixedClaimGroup$FinalStep() {
    PartiallyFixedClaimGroup$FinalStep$Companion_getInstance();
  }
  function PartiallyFixedClaimGroup$FinalStep$Companion() {
    PartiallyFixedClaimGroup$FinalStep$Companion_instance = this;
  }
  PartiallyFixedClaimGroup$FinalStep$Companion.prototype.create_i5unq6$ = function (groupType, description, representation, assertions, holds) {
    return new FinalStepImpl_4(groupType, description, representation, assertions, holds);
  };
  PartiallyFixedClaimGroup$FinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var PartiallyFixedClaimGroup$FinalStep$Companion_instance = null;
  function PartiallyFixedClaimGroup$FinalStep$Companion_getInstance() {
    if (PartiallyFixedClaimGroup$FinalStep$Companion_instance === null) {
      new PartiallyFixedClaimGroup$FinalStep$Companion();
    }
    return PartiallyFixedClaimGroup$FinalStep$Companion_instance;
  }
  PartiallyFixedClaimGroup$FinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FinalStep',
    interfaces: [BasicAssertionGroupFinalStep]
  };
  PartiallyFixedClaimGroup.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'PartiallyFixedClaimGroup',
    interfaces: []
  };
  function RepresentationOnly() {
  }
  function RepresentationOnly$HoldsStep() {
    RepresentationOnly$HoldsStep$Companion_getInstance();
  }
  function RepresentationOnly$HoldsStep$Companion() {
    RepresentationOnly$HoldsStep$Companion_instance = this;
  }
  RepresentationOnly$HoldsStep$Companion.prototype.create = function () {
    return HoldsStepImpl_getInstance();
  };
  RepresentationOnly$HoldsStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var RepresentationOnly$HoldsStep$Companion_instance = null;
  function RepresentationOnly$HoldsStep$Companion_getInstance() {
    if (RepresentationOnly$HoldsStep$Companion_instance === null) {
      new RepresentationOnly$HoldsStep$Companion();
    }
    return RepresentationOnly$HoldsStep$Companion_instance;
  }
  RepresentationOnly$HoldsStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'HoldsStep',
    interfaces: [HoldsStep]
  };
  function RepresentationOnly$RepresentationStep() {
    RepresentationOnly$RepresentationStep$Companion_getInstance();
  }
  function RepresentationOnly$RepresentationStep$Companion() {
    RepresentationOnly$RepresentationStep$Companion_instance = this;
  }
  RepresentationOnly$RepresentationStep$Companion.prototype.create_u332lz$ = function (test) {
    return new RepresentationStepImpl(test);
  };
  RepresentationOnly$RepresentationStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var RepresentationOnly$RepresentationStep$Companion_instance = null;
  function RepresentationOnly$RepresentationStep$Companion_getInstance() {
    if (RepresentationOnly$RepresentationStep$Companion_instance === null) {
      new RepresentationOnly$RepresentationStep$Companion();
    }
    return RepresentationOnly$RepresentationStep$Companion_instance;
  }
  RepresentationOnly$RepresentationStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'RepresentationStep',
    interfaces: []
  };
  function RepresentationOnly$FinalStep() {
    RepresentationOnly$FinalStep$Companion_getInstance();
  }
  function RepresentationOnly$FinalStep$Companion() {
    RepresentationOnly$FinalStep$Companion_instance = this;
  }
  RepresentationOnly$FinalStep$Companion.prototype.create_kstd2b$ = function (test, representation) {
    return new FinalStepImpl_5(test, representation);
  };
  RepresentationOnly$FinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var RepresentationOnly$FinalStep$Companion_instance = null;
  function RepresentationOnly$FinalStep$Companion_getInstance() {
    if (RepresentationOnly$FinalStep$Companion_instance === null) {
      new RepresentationOnly$FinalStep$Companion();
    }
    return RepresentationOnly$FinalStep$Companion_instance;
  }
  RepresentationOnly$FinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FinalStep',
    interfaces: [AssertionBuilderFinalStep]
  };
  RepresentationOnly.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'RepresentationOnly',
    interfaces: []
  };
  function get_root($receiver) {
    return AssertionGroupDescriptionAndRepresentationOption$Companion_getInstance().create_xbaybz$(RootAssertionGroupType_getInstance(), AssertionsOption$Companion_getInstance().factoryWithDefaultFinalStep_picbvu$());
  }
  function AssertionChecker() {
  }
  AssertionChecker.prototype.check_i2wsmx$ = function (assertionVerb, representationProvider, assertions) {
    this.check_nnvq6$(assertionVerb, new LazyRepresentation(representationProvider), assertions);
  };
  AssertionChecker.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionChecker',
    interfaces: []
  };
  function coreFactory$lambda() {
    return loadSingleService(getKClass(CoreFactory));
  }
  var coreFactory;
  function get_coreFactory() {
    return coreFactory.value;
  }
  function CoreFactoryCommon() {
  }
  CoreFactoryCommon.prototype.newReportingPlant_lh8iui$ = function (assertionVerb, subjectProvider, reporter) {
    return this.newReportingPlant_xxj474$(assertionVerb, subjectProvider, this.newThrowingAssertionChecker_22a895$(reporter));
  };
  CoreFactoryCommon.prototype.newDelegatingReportingAssertionContainer_8dvlby$ = function (originalAssertionHolder, maybeSubject) {
    return this.newReportingAssertionContainer_nmnty6$(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE, maybeSubject, this.newDelegatingAssertionChecker_10aezz$(originalAssertionHolder));
  };
  CoreFactoryCommon.prototype.newReportingAssertionContainer_nmnty6$ = function (assertionVerb, maybeSubject, assertionChecker) {
    var tmp$ = ReportingAssertionContainer$AssertionCheckerDecorator$Companion_getInstance();
    var fold_2f1hkh$result;
    if (Kotlin.isType(maybeSubject, Some)) {
      fold_2f1hkh$result = maybeSubject.value;
    }
     else if (equals(maybeSubject, package$core.None)) {
      fold_2f1hkh$result = RawString$Companion_getInstance().create_61zpoe$(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG);
    }
     else {
      fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
    }
    return this.newReportingAssertionContainer_gglseu$(tmp$.create_dd5zo7$(assertionVerb, maybeSubject, fold_2f1hkh$result, assertionChecker, RawString$Companion_getInstance().NULL));
  };
  CoreFactoryCommon.prototype.newReportingPlant_xxj474$ = function (assertionVerb, subjectProvider, assertionChecker) {
    var evalOnceSubjectProvider = evalOnce(subjectProvider);
    return this.newReportingPlant_ru86lu$(new AssertionPlantWithCommonFields$CommonFields(assertionVerb, evalOnceSubjectProvider, evalOnceSubjectProvider, assertionChecker, RawString$Companion_getInstance().NULL));
  };
  CoreFactoryCommon.prototype.newReportingPlantAndAddAssertionsCreatedBy_m60waw$ = function (assertionVerb, subjectProvider, reporter, assertionCreator) {
    return this.newReportingPlant_lh8iui$(assertionVerb, subjectProvider, reporter).addAssertionsCreatedBy_rnr939$(assertionCreator);
  };
  CoreFactoryCommon.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CoreFactoryCommon',
    interfaces: []
  };
  function newReportingPlantNullable($receiver, assertionVerb, subjectProvider, reporter, nullRepresentation) {
    if (nullRepresentation === void 0)
      nullRepresentation = RawString$Companion_getInstance().NULL;
    return newReportingPlantNullable_0($receiver, assertionVerb, subjectProvider, $receiver.newThrowingAssertionChecker_22a895$(reporter), nullRepresentation);
  }
  function newReportingPlantNullable_0($receiver, assertionVerb, subjectProvider, assertionChecker, nullRepresentation) {
    if (nullRepresentation === void 0)
      nullRepresentation = RawString$Companion_getInstance().NULL;
    var evalOnceSubjectProvider = evalOnce(subjectProvider);
    return $receiver.newReportingPlantNullable_d0uxjj$(new AssertionPlantWithCommonFields$CommonFields(assertionVerb, evalOnceSubjectProvider, evalOnceSubjectProvider, assertionChecker, nullRepresentation));
  }
  function Option() {
    Option$Companion_getInstance();
  }
  Option.prototype.isDefined = function () {
    return Kotlin.isType(this, Some);
  };
  Option.prototype.filter_ucl7l2$ = defineInlineFunction('atrium-core-api-js.ch.tutteli.atrium.core.Option.filter_ucl7l2$', wrapFunction(function () {
    var Some_init = _.ch.tutteli.atrium.core.Some;
    var core = _.ch.tutteli.atrium.core;
    var equals = Kotlin.equals;
    return function (predicate) {
      var fold_2f1hkh$result;
      if (Kotlin.isType(this, Some_init)) {
        var it = this.value;
        fold_2f1hkh$result = predicate(it) ? new Some_init(it) : core.None;
      }
       else if (equals(this, core.None)) {
        fold_2f1hkh$result = core.None;
      }
       else {
        fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
      }
      return fold_2f1hkh$result;
    };
  }));
  Option.prototype.map_2o04qz$ = defineInlineFunction('atrium-core-api-js.ch.tutteli.atrium.core.Option.map_2o04qz$', wrapFunction(function () {
    var Some_init = _.ch.tutteli.atrium.core.Some;
    var core = _.ch.tutteli.atrium.core;
    var equals = Kotlin.equals;
    return function (f) {
      var fold_2f1hkh$result;
      if (Kotlin.isType(this, Some_init)) {
        fold_2f1hkh$result = new Some_init(f(this.value));
      }
       else if (equals(this, core.None)) {
        fold_2f1hkh$result = core.None;
      }
       else {
        fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
      }
      return fold_2f1hkh$result;
    };
  }));
  Option.prototype.flatMap_oolzno$ = defineInlineFunction('atrium-core-api-js.ch.tutteli.atrium.core.Option.flatMap_oolzno$', wrapFunction(function () {
    var core = _.ch.tutteli.atrium.core;
    var Some = _.ch.tutteli.atrium.core.Some;
    var equals = Kotlin.equals;
    return function (f) {
      var fold_2f1hkh$result;
      if (Kotlin.isType(this, Some)) {
        fold_2f1hkh$result = f(this.value);
      }
       else if (equals(this, core.None)) {
        fold_2f1hkh$result = core.None;
      }
       else {
        fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
      }
      return fold_2f1hkh$result;
    };
  }));
  Option.prototype.fold_2f1hkh$ = defineInlineFunction('atrium-core-api-js.ch.tutteli.atrium.core.Option.fold_2f1hkh$', wrapFunction(function () {
    var Some = _.ch.tutteli.atrium.core.Some;
    var core = _.ch.tutteli.atrium.core;
    var equals = Kotlin.equals;
    return function (default_0, f) {
      if (Kotlin.isType(this, Some))
        return f(this.value);
      else if (equals(this, core.None))
        return default_0();
      else
        return Kotlin.noWhenBranchMatched();
    };
  }));
  function Option$Companion() {
    Option$Companion_instance = this;
  }
  Option$Companion.prototype.someIf_nkfre5$ = defineInlineFunction('atrium-core-api-js.ch.tutteli.atrium.core.Option.Companion.someIf_nkfre5$', wrapFunction(function () {
    var Some_init = _.ch.tutteli.atrium.core.Some;
    var core = _.ch.tutteli.atrium.core;
    return function (predicate, provider) {
      return predicate ? new Some_init(provider()) : core.None;
    };
  }));
  Option$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Option$Companion_instance = null;
  function Option$Companion_getInstance() {
    if (Option$Companion_instance === null) {
      new Option$Companion();
    }
    return Option$Companion_instance;
  }
  Option.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Option',
    interfaces: []
  };
  var getOrElse = defineInlineFunction('atrium-core-api-js.ch.tutteli.atrium.core.getOrElse_he15va$', wrapFunction(function () {
    var Some = _.ch.tutteli.atrium.core.Some;
    var core = _.ch.tutteli.atrium.core;
    var equals = Kotlin.equals;
    return function ($receiver, default_0) {
      var fold_2f1hkh$result;
      if (Kotlin.isType($receiver, Some)) {
        fold_2f1hkh$result = $receiver.value;
      }
       else if (equals($receiver, core.None)) {
        fold_2f1hkh$result = default_0();
      }
       else {
        fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
      }
      return fold_2f1hkh$result;
    };
  }));
  function Some(value) {
    Option.call(this);
    this.value = value;
  }
  Some.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Some',
    interfaces: [Option]
  };
  Some.prototype.component1 = function () {
    return this.value;
  };
  Some.prototype.copy_11rb$ = function (value) {
    return new Some(value === void 0 ? this.value : value);
  };
  Some.prototype.toString = function () {
    return 'Some(value=' + Kotlin.toString(this.value) + ')';
  };
  Some.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.value) | 0;
    return result;
  };
  Some.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.value, other.value))));
  };
  function None() {
    None_instance = this;
    Option.call(this);
  }
  None.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'None',
    interfaces: [Option]
  };
  var None_instance = null;
  function None_getInstance() {
    if (None_instance === null) {
      new None();
    }
    return None_instance;
  }
  function falseProvider$lambda() {
    return false;
  }
  var falseProvider;
  function trueProvider$lambda() {
    return true;
  }
  var trueProvider;
  function evalOnce$lambda(this$evalOnce) {
    return function () {
      return this$evalOnce();
    };
  }
  function evalOnce$lambda_0(closure$v) {
    return function () {
      return closure$v.value;
    };
  }
  function evalOnce($receiver) {
    var v = lazy(evalOnce$lambda($receiver));
    return evalOnce$lambda_0(v);
  }
  function useSingleService(kClass, itr) {
    if (!itr.hasNext())
      throw new NoSuchElementException('Could not find any implementation for ' + get_fullName(kClass));
    var service = itr.next();
    if (!!itr.hasNext()) {
      var sb = StringBuilder_init();
      while (itr.hasNext()) {
        var it = itr.next();
        appendln(sb);
        sb.append_gw00v9$(get_fullName(Kotlin.getKClassFromExpression(it)));
      }
      var message = 'Found more than one implementation for ' + get_fullName(kClass) + ':' + '\n' + get_fullName(Kotlin.getKClassFromExpression(service)) + sb;
      throw IllegalStateException_init(message.toString());
    }
    return service;
  }
  function AssertMarker() {
  }
  AssertMarker.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssertMarker',
    interfaces: [Annotation]
  };
  function AssertionHolder() {
  }
  AssertionHolder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionHolder',
    interfaces: []
  };
  function AssertionPlant() {
  }
  AssertionPlant.prototype.createAndAddAssertion_4119du$ = function (description, expected, test) {
    return this.addAssertion_94orq3$(assertionBuilder.createDescriptive_67aujf$(description, expected, test));
  };
  AssertionPlant.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionPlant',
    interfaces: [BaseAssertionPlant]
  };
  function AssertionPlantNullable() {
  }
  AssertionPlantNullable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionPlantNullable',
    interfaces: [BaseAssertionPlant]
  };
  function AssertionPlantWithCommonFields() {
  }
  function AssertionPlantWithCommonFields$CommonFields(assertionVerb, subjectProvider, representationProvider, assertionChecker, nullRepresentation) {
    this.assertionVerb = assertionVerb;
    this.subjectProvider = subjectProvider;
    this.representationProvider_0 = representationProvider;
    this.assertionChecker = assertionChecker;
    this.nullRepresentation_0 = nullRepresentation;
    this.representation_o4c6u9$_0 = lazy(AssertionPlantWithCommonFields$CommonFields$representation$lambda(this));
  }
  Object.defineProperty(AssertionPlantWithCommonFields$CommonFields.prototype, 'representation_0', {
    get: function () {
      return this.representation_o4c6u9$_0.value;
    }
  });
  AssertionPlantWithCommonFields$CommonFields.prototype.check_tgi7xs$ = function (assertions) {
    this.assertionChecker.check_i2wsmx$(this.assertionVerb, this.representation_0, assertions);
  };
  function AssertionPlantWithCommonFields$CommonFields$representation$lambda$lambda(this$CommonFields) {
    return function () {
      var tmp$;
      return (tmp$ = this$CommonFields.representationProvider_0()) != null ? tmp$ : this$CommonFields.nullRepresentation_0;
    };
  }
  function AssertionPlantWithCommonFields$CommonFields$representation$lambda(this$CommonFields) {
    return function () {
      return AssertionPlantWithCommonFields$CommonFields$representation$lambda$lambda(this$CommonFields);
    };
  }
  AssertionPlantWithCommonFields$CommonFields.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CommonFields',
    interfaces: []
  };
  function AssertionPlantWithCommonFields$AssertionPlantWithCommonFields$CommonFields_init(assertionVerb, subject, assertionChecker, nullRepresentation, $this) {
    $this = $this || Object.create(AssertionPlantWithCommonFields$CommonFields.prototype);
    AssertionPlantWithCommonFields$CommonFields.call($this, assertionVerb, evalOnce(AssertionPlantWithCommonFields$AssertionPlantWithCommonFields$CommonFields_init$lambda(subject)), evalOnce(AssertionPlantWithCommonFields$AssertionPlantWithCommonFields$CommonFields_init$lambda_0(subject)), assertionChecker, nullRepresentation);
    return $this;
  }
  function AssertionPlantWithCommonFields$AssertionPlantWithCommonFields$CommonFields_init$lambda(closure$subject) {
    return function () {
      return closure$subject;
    };
  }
  function AssertionPlantWithCommonFields$AssertionPlantWithCommonFields$CommonFields_init$lambda_0(closure$subject) {
    return function () {
      return closure$subject;
    };
  }
  AssertionPlantWithCommonFields.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionPlantWithCommonFields',
    interfaces: []
  };
  function BaseAssertionPlant() {
  }
  Object.defineProperty(BaseAssertionPlant.prototype, 'maybeSubject', {
    get: function () {
      var tmp$;
      try {
        tmp$ = new Some(this.subject);
      }
       catch (e) {
        if (Kotlin.isType(e, PlantHasNoSubjectException)) {
          tmp$ = None_getInstance();
        }
         else
          throw e;
      }
      return tmp$;
    }
  });
  BaseAssertionPlant.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BaseAssertionPlant',
    interfaces: [SubjectProvider]
  };
  function BaseCollectingAssertionPlant() {
  }
  BaseCollectingAssertionPlant.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BaseCollectingAssertionPlant',
    interfaces: [BaseAssertionPlant]
  };
  function BaseReportingAssertionPlant() {
  }
  BaseReportingAssertionPlant.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BaseReportingAssertionPlant',
    interfaces: [AssertionPlantWithCommonFields, BaseAssertionPlant]
  };
  function CheckingAssertionPlant() {
  }
  CheckingAssertionPlant.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CheckingAssertionPlant',
    interfaces: [AssertionPlant]
  };
  function CollectingAssertionContainer() {
  }
  CollectingAssertionContainer.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CollectingAssertionContainer',
    interfaces: [Expect]
  };
  function CollectingAssertionPlant() {
  }
  CollectingAssertionPlant.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CollectingAssertionPlant',
    interfaces: [BaseCollectingAssertionPlant, AssertionPlant]
  };
  function CollectingAssertionPlantNullable() {
  }
  CollectingAssertionPlantNullable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CollectingAssertionPlantNullable',
    interfaces: [BaseCollectingAssertionPlant, AssertionPlantNullable]
  };
  function ExpectMarker() {
  }
  ExpectMarker.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExpectMarker',
    interfaces: [Annotation]
  };
  function Expect() {
  }
  Expect.prototype.createAndAddAssertion_n2o31w$ = function (description, expected, test) {
    return this.createAndAddAssertion_auvybc$(new Untranslatable(description), expected, test);
  };
  Expect.prototype.createAndAddAssertion_auvybc$ = function (description, expected, test) {
    return this.addAssertion_94orq3$(assertionBuilder.createDescriptive_84vzck$(this, description, expected, test));
  };
  Expect.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Expect',
    interfaces: [SubjectProvider]
  };
  function MaybeSubject() {
    MaybeSubject$Companion_getInstance();
  }
  function MaybeSubject$Absent() {
    MaybeSubject$Absent_instance = this;
    MaybeSubject.call(this);
  }
  MaybeSubject$Absent.prototype.get = function () {
    throw PlantHasNoSubjectException_init();
  };
  MaybeSubject$Absent.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Absent',
    interfaces: [MaybeSubject]
  };
  var MaybeSubject$Absent_instance = null;
  function MaybeSubject$Absent_getInstance() {
    if (MaybeSubject$Absent_instance === null) {
      new MaybeSubject$Absent();
    }
    return MaybeSubject$Absent_instance;
  }
  function MaybeSubject$Present(subject) {
    MaybeSubject.call(this);
    this.subject = subject;
  }
  MaybeSubject$Present.prototype.get = function () {
    return this.subject;
  };
  MaybeSubject$Present.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Present',
    interfaces: [MaybeSubject]
  };
  MaybeSubject$Present.prototype.component1 = function () {
    return this.subject;
  };
  MaybeSubject$Present.prototype.copy_11rb$ = function (subject) {
    return new MaybeSubject$Present(subject === void 0 ? this.subject : subject);
  };
  MaybeSubject$Present.prototype.toString = function () {
    return 'Present(subject=' + Kotlin.toString(this.subject) + ')';
  };
  MaybeSubject$Present.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.subject) | 0;
    return result;
  };
  MaybeSubject$Present.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.subject, other.subject))));
  };
  function MaybeSubject$Companion() {
    MaybeSubject$Companion_instance = this;
  }
  MaybeSubject$Companion.prototype.invoke_issdgt$ = function (subject) {
    return subject == null ? MaybeSubject$Absent_getInstance() : new MaybeSubject$Present(subject);
  };
  MaybeSubject$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var MaybeSubject$Companion_instance = null;
  function MaybeSubject$Companion_getInstance() {
    if (MaybeSubject$Companion_instance === null) {
      new MaybeSubject$Companion();
    }
    return MaybeSubject$Companion_instance;
  }
  MaybeSubject.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MaybeSubject',
    interfaces: []
  };
  function PlantHasNoSubjectException(message) {
    RuntimeException_init(message, this);
    this.name = 'PlantHasNoSubjectException';
  }
  PlantHasNoSubjectException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PlantHasNoSubjectException',
    interfaces: [RuntimeException]
  };
  function PlantHasNoSubjectException_init($this) {
    $this = $this || Object.create(PlantHasNoSubjectException.prototype);
    PlantHasNoSubjectException.call($this, 'subject is not available, you as user should not see this message.\n' + 'Please file a bug report (including stacktrace if possible): https://github.com/robstoll/atrium/issues/new');
    return $this;
  }
  function ReportingAssertionContainer() {
  }
  function ReportingAssertionContainer$AssertionCheckerDecorator() {
    ReportingAssertionContainer$AssertionCheckerDecorator$Companion_getInstance();
  }
  function ReportingAssertionContainer$AssertionCheckerDecorator$Companion() {
    ReportingAssertionContainer$AssertionCheckerDecorator$Companion_instance = this;
  }
  ReportingAssertionContainer$AssertionCheckerDecorator$Companion.prototype.create_dd5zo7$ = function (assertionVerb, maybeSubject, representation, assertionChecker, nullRepresentation) {
    return new ReportingAssertionContainer$EagerCommonFields(assertionVerb, maybeSubject, representation, assertionChecker, nullRepresentation);
  };
  ReportingAssertionContainer$AssertionCheckerDecorator$Companion.prototype.createLazy_5sz227$ = function (assertionVerb, maybeSubjectProvider, representationProvider, assertionChecker, nullRepresentation) {
    return new ReportingAssertionContainer$LazyCommonFields(assertionVerb, maybeSubjectProvider, representationProvider, assertionChecker, nullRepresentation);
  };
  ReportingAssertionContainer$AssertionCheckerDecorator$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ReportingAssertionContainer$AssertionCheckerDecorator$Companion_instance = null;
  function ReportingAssertionContainer$AssertionCheckerDecorator$Companion_getInstance() {
    if (ReportingAssertionContainer$AssertionCheckerDecorator$Companion_instance === null) {
      new ReportingAssertionContainer$AssertionCheckerDecorator$Companion();
    }
    return ReportingAssertionContainer$AssertionCheckerDecorator$Companion_instance;
  }
  ReportingAssertionContainer$AssertionCheckerDecorator.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionCheckerDecorator',
    interfaces: []
  };
  function ReportingAssertionContainer$EagerCommonFields(assertionVerb, maybeSubject, representation, assertionChecker, nullRepresentation) {
    this.assertionVerb_0 = assertionVerb;
    this.maybeSubject_duhimc$_0 = maybeSubject;
    this.representation_0 = representation;
    this.assertionChecker_76ngi3$_0 = assertionChecker;
    this.nullRepresentation_0 = nullRepresentation;
  }
  Object.defineProperty(ReportingAssertionContainer$EagerCommonFields.prototype, 'maybeSubject', {
    get: function () {
      return this.maybeSubject_duhimc$_0;
    }
  });
  Object.defineProperty(ReportingAssertionContainer$EagerCommonFields.prototype, 'assertionChecker', {
    get: function () {
      return this.assertionChecker_76ngi3$_0;
    }
  });
  ReportingAssertionContainer$EagerCommonFields.prototype.check_tgi7xs$ = function (assertions) {
    var tmp$;
    this.assertionChecker.check_nnvq6$(this.assertionVerb_0, (tmp$ = this.representation_0) != null ? tmp$ : this.nullRepresentation_0, assertions);
  };
  ReportingAssertionContainer$EagerCommonFields.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EagerCommonFields',
    interfaces: [ReportingAssertionContainer$AssertionCheckerDecorator]
  };
  function ReportingAssertionContainer$LazyCommonFields(assertionVerb, maybeSubjectProvider, representationProvider, assertionChecker, nullRepresentation) {
    this.assertionVerb_0 = assertionVerb;
    this.assertionChecker_vvy427$_0 = assertionChecker;
    this.nullRepresentation_0 = nullRepresentation;
    this.maybeSubject_i4gr7k$_0 = lazy(maybeSubjectProvider);
    this.representation_0 = new LazyRepresentation(ReportingAssertionContainer$LazyCommonFields$representation$lambda(representationProvider, this));
  }
  Object.defineProperty(ReportingAssertionContainer$LazyCommonFields.prototype, 'assertionChecker', {
    get: function () {
      return this.assertionChecker_vvy427$_0;
    }
  });
  Object.defineProperty(ReportingAssertionContainer$LazyCommonFields.prototype, 'maybeSubject', {
    get: function () {
      return this.maybeSubject_i4gr7k$_0.value;
    }
  });
  ReportingAssertionContainer$LazyCommonFields.prototype.check_tgi7xs$ = function (assertions) {
    this.assertionChecker.check_nnvq6$(this.assertionVerb_0, this.representation_0, assertions);
  };
  function ReportingAssertionContainer$LazyCommonFields$representation$lambda(closure$representationProvider, this$LazyCommonFields) {
    return function () {
      var tmp$;
      return (tmp$ = closure$representationProvider()) != null ? tmp$ : this$LazyCommonFields.nullRepresentation_0;
    };
  }
  ReportingAssertionContainer$LazyCommonFields.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LazyCommonFields',
    interfaces: [ReportingAssertionContainer$AssertionCheckerDecorator]
  };
  ReportingAssertionContainer.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ReportingAssertionContainer',
    interfaces: [Expect]
  };
  function ReportingAssertionPlant() {
  }
  ReportingAssertionPlant.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ReportingAssertionPlant',
    interfaces: [BaseReportingAssertionPlant, AssertionPlant]
  };
  function ReportingAssertionPlantNullable() {
  }
  ReportingAssertionPlantNullable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ReportingAssertionPlantNullable',
    interfaces: [BaseReportingAssertionPlant, AssertionPlantNullable]
  };
  function SubjectProvider() {
  }
  SubjectProvider.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SubjectProvider',
    interfaces: [AssertionHolder]
  };
  function AssertionFormatter() {
    AssertionFormatter$Companion_getInstance();
  }
  AssertionFormatter.prototype.format_yf5509$ = function (assertion, parameterObject) {
    if (Kotlin.isType(assertion, AssertionGroup))
      AssertionFormatter$Companion_getInstance().throwNotIntendedForAssertionGroups();
    else
      this.formatNonGroup_yf5509$(assertion, parameterObject);
  };
  function AssertionFormatter$Companion() {
    AssertionFormatter$Companion_instance = this;
    this.CALL_FORMAT_GROUP = 'do not use `' + getCallableRef('format', function ($receiver, assertion, parameterObject) {
      return $receiver.format_yf5509$(assertion, parameterObject), Unit;
    }).callableName + '` for ' + ('`' + toString(getKClass(AssertionGroup).simpleName) + '`s, ') + ('use `' + getCallableRef('formatGroup', function ($receiver, assertionGroup, parameterObject, formatAssertions) {
      return $receiver.formatGroup_5bszl9$(assertionGroup, parameterObject, formatAssertions), Unit;
    }).callableName + '` instead.');
  }
  AssertionFormatter$Companion.prototype.throwNotIntendedForAssertionGroups = function () {
    throw UnsupportedOperationException_init(AssertionFormatter$Companion_getInstance().CALL_FORMAT_GROUP);
  };
  AssertionFormatter$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AssertionFormatter$Companion_instance = null;
  function AssertionFormatter$Companion_getInstance() {
    if (AssertionFormatter$Companion_instance === null) {
      new AssertionFormatter$Companion();
    }
    return AssertionFormatter$Companion_instance;
  }
  AssertionFormatter.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionFormatter',
    interfaces: []
  };
  function AssertionFormatterController() {
    AssertionFormatterController$Companion_getInstance();
  }
  AssertionFormatterController.prototype.isExplanatoryAssertionGroup_94orq3$ = function (assertion) {
    return Kotlin.isType(assertion, AssertionGroup) && Kotlin.isType(assertion.type, ExplanatoryAssertionGroupType);
  };
  function AssertionFormatterController$Companion() {
    AssertionFormatterController$Companion_instance = this;
  }
  AssertionFormatterController$Companion.prototype.noSuitableAssertionFormatterFound_94orq3$ = function (assertion) {
    throw UnsupportedOperationException_init('No suitable ' + toString(getKClass(AssertionFormatter).simpleName) + ' found for the given assertion: ' + assertion);
  };
  AssertionFormatterController$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AssertionFormatterController$Companion_instance = null;
  function AssertionFormatterController$Companion_getInstance() {
    if (AssertionFormatterController$Companion_instance === null) {
      new AssertionFormatterController$Companion();
    }
    return AssertionFormatterController$Companion_instance;
  }
  AssertionFormatterController.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionFormatterController',
    interfaces: []
  };
  function AssertionFormatterFacade() {
  }
  AssertionFormatterFacade.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionFormatterFacade',
    interfaces: []
  };
  function AssertionFormatterParameterObject(sb, prefix, indentLevel, assertionFilter, numberOfDoNotFilterGroups) {
    AssertionFormatterParameterObject$Companion_getInstance();
    this.sb = sb;
    this.prefix = prefix;
    this.indentLevel_0 = indentLevel;
    this.assertionFilter = assertionFilter;
    this.numberOfDoNotFilterGroups_0 = numberOfDoNotFilterGroups;
  }
  AssertionFormatterParameterObject.prototype.createChildWithNewPrefix_61zpoe$ = function (newPrefix) {
    return this.createChildWithNewPrefixAndAdditionalIndent_bm4lxs$(newPrefix, 0);
  };
  AssertionFormatterParameterObject.prototype.createChildWithNewPrefixAndAdditionalIndent_bm4lxs$ = function (newPrefix, additionalIndent) {
    return new AssertionFormatterParameterObject(this.sb, newPrefix, this.indentLevel_0 + this.prefix.length + additionalIndent | 0, this.assertionFilter, this.numberOfDoNotFilterGroups_0);
  };
  AssertionFormatterParameterObject.prototype.createForDoNotFilterAssertionGroup = function () {
    return new AssertionFormatterParameterObject(this.sb, this.prefix, this.indentLevel_0, this.assertionFilter, this.numberOfDoNotFilterGroups_0 + 1 | 0);
  };
  AssertionFormatterParameterObject.prototype.isNotInDoNotFilterGroup = function () {
    return this.numberOfDoNotFilterGroups_0 === 0;
  };
  AssertionFormatterParameterObject.prototype.appendLnIndentAndPrefix = function () {
    appendln(this.sb);
    this.indent_0();
    this.sb.append_gw00v9$(this.prefix);
  };
  AssertionFormatterParameterObject.prototype.appendLnAndIndent = function () {
    appendln(this.sb);
    this.indent_0();
  };
  AssertionFormatterParameterObject.prototype.indent_0 = function () {
    this.indent_za3lpa$(this.indentLevel_0);
  };
  AssertionFormatterParameterObject.prototype.indent_za3lpa$ = function (numberOfSpaces) {
    for (var i = 0; i < numberOfSpaces; i++) {
      this.sb.append_s8itvh$(32);
    }
  };
  function AssertionFormatterParameterObject$Companion() {
    AssertionFormatterParameterObject$Companion_instance = this;
  }
  AssertionFormatterParameterObject$Companion.prototype.new_a0yeei$ = function (sb, assertionFilter) {
    return new AssertionFormatterParameterObject(sb, '', 0, assertionFilter, 0);
  };
  AssertionFormatterParameterObject$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AssertionFormatterParameterObject$Companion_instance = null;
  function AssertionFormatterParameterObject$Companion_getInstance() {
    if (AssertionFormatterParameterObject$Companion_instance === null) {
      new AssertionFormatterParameterObject$Companion();
    }
    return AssertionFormatterParameterObject$Companion_instance;
  }
  AssertionFormatterParameterObject.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssertionFormatterParameterObject',
    interfaces: []
  };
  function AssertionPairFormatter() {
  }
  AssertionPairFormatter.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionPairFormatter',
    interfaces: []
  };
  function createAtriumError(message, errorAdjuster) {
    var err = new AtriumError(message);
    errorAdjuster.adjust_tcv7n7$(err);
    return err;
  }
  function AtriumErrorAdjusterCommon() {
  }
  AtriumErrorAdjusterCommon.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AtriumErrorAdjusterCommon',
    interfaces: []
  };
  function LazyRepresentation(provider) {
    this.provider_0 = provider;
  }
  LazyRepresentation.prototype.eval = function () {
    return this.provider_0();
  };
  LazyRepresentation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LazyRepresentation',
    interfaces: []
  };
  function MethodCallFormatter() {
  }
  MethodCallFormatter.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'MethodCallFormatter',
    interfaces: []
  };
  function ObjectFormatter() {
  }
  ObjectFormatter.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ObjectFormatter',
    interfaces: []
  };
  function RawString() {
    RawString$Companion_getInstance();
  }
  function RawString$Companion() {
    RawString$Companion_instance = this;
    this.NULL = new StringBasedRawString('null');
    this.EMPTY = RawString$Companion_getInstance().create_61zpoe$('');
  }
  RawString$Companion.prototype.create_61zpoe$ = function (string) {
    return new StringBasedRawString(string);
  };
  RawString$Companion.prototype.create_n3w7xm$ = function (translatable) {
    return new TranslatableBasedRawString(translatable);
  };
  RawString$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var RawString$Companion_instance = null;
  function RawString$Companion_getInstance() {
    if (RawString$Companion_instance === null) {
      new RawString$Companion();
    }
    return RawString$Companion_instance;
  }
  RawString.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'RawString',
    interfaces: []
  };
  function Reporter() {
  }
  Reporter.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Reporter',
    interfaces: []
  };
  function reporter$lambda() {
    var tmp$, tmp$_0;
    var id = (tmp$ = getAtriumProperty(ReporterFactory$Companion_getInstance().ATRIUM_PROPERTY_KEY)) != null ? tmp$ : 'default';
    var $receiver = loadServices(getKClass(ReporterFactory));
    var firstOrNull$result;
    firstOrNull$break: do {
      var tmp$_1;
      tmp$_1 = $receiver.iterator();
      while (tmp$_1.hasNext()) {
        var element = tmp$_1.next();
        if (equals(element.id, id)) {
          firstOrNull$result = element;
          break firstOrNull$break;
        }
      }
      firstOrNull$result = null;
    }
     while (false);
    tmp$_0 = firstOrNull$result;
    if (tmp$_0 == null) {
      throw IllegalStateException_init('Could not find a ' + toString(getKClass(ReporterFactory).simpleName) + ' with id ' + id);
    }
    var factory = tmp$_0;
    return factory.create();
  }
  var reporter;
  function get_reporter() {
    return reporter.value;
  }
  function ReporterFactory() {
    ReporterFactory$Companion_getInstance();
  }
  function ReporterFactory$Companion() {
    ReporterFactory$Companion_instance = this;
    this.ATRIUM_PROPERTY_KEY = 'ch.tutteli.atrium.reporting.reporterFactory';
  }
  ReporterFactory$Companion.prototype.specifyFactory_61zpoe$ = function (reporterFactoryId) {
    setAtriumProperty(this.ATRIUM_PROPERTY_KEY, reporterFactoryId);
  };
  ReporterFactory$Companion.prototype.specifyFactoryIfNotYetSet_61zpoe$ = function (reporterFactoryId) {
    if (getAtriumProperty(this.ATRIUM_PROPERTY_KEY) == null) {
      this.specifyFactory_61zpoe$(reporterFactoryId);
    }
  };
  ReporterFactory$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ReporterFactory$Companion_instance = null;
  function ReporterFactory$Companion_getInstance() {
    if (ReporterFactory$Companion_instance === null) {
      new ReporterFactory$Companion();
    }
    return ReporterFactory$Companion_instance;
  }
  ReporterFactory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ReporterFactory',
    interfaces: []
  };
  function StringBasedRawString(string) {
    this.string = string;
  }
  StringBasedRawString.prototype.toString = function () {
    return this.string + ' (RawString)';
  };
  StringBasedRawString.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'StringBasedRawString',
    interfaces: [RawString]
  };
  StringBasedRawString.prototype.component1 = function () {
    return this.string;
  };
  StringBasedRawString.prototype.copy_61zpoe$ = function (string) {
    return new StringBasedRawString(string === void 0 ? this.string : string);
  };
  StringBasedRawString.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.string) | 0;
    return result;
  };
  StringBasedRawString.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.string, other.string))));
  };
  var BUG_REPORT_URL;
  var SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG;
  var SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE;
  function ArgumentsSupportingTranslator(primaryLocale, fallbackLocales) {
    this.primaryLocale = primaryLocale;
    this.fallbackLocales = fallbackLocales;
    var element = this.primaryLocale;
    var iterable = this.fallbackLocales;
    if (!!equals(element.language, 'no')) {
      var message = 'The macrolanguage `no` is not supported but ' + element + ' given.' + '\n' + 'Use either nb_... or nn_...';
      throw IllegalArgumentException_init(message.toString());
    }
    if (!(!equals(element.language, 'zh') || element.country != null || (!equals(element.script, 'Hans') && !equals(element.script, 'Hant')))) {
      var tmp$;
      if (equals(element.script, 'Hant')) {
        tmp$ = 'TW, HK or MO';
      }
       else {
        tmp$ = 'CN or SG';
      }
      var countries = tmp$;
      var message_0 = 'Script `' + toString(element.script) + '` for Locale with language `zh` is not supported.' + '\n' + 'Use a corresponding country instead (' + countries + ').';
      throw IllegalArgumentException_init(message_0.toString());
    }
    var tmp$_0;
    tmp$_0 = iterable.iterator();
    while (tmp$_0.hasNext()) {
      var element_0 = tmp$_0.next();
      if (!!equals(element_0.language, 'no')) {
        var message_1 = 'The macrolanguage `no` is not supported but ' + element_0 + ' given.' + '\n' + 'Use either nb_... or nn_...';
        throw IllegalArgumentException_init(message_1.toString());
      }
      if (!(!equals(element_0.language, 'zh') || element_0.country != null || (!equals(element_0.script, 'Hans') && !equals(element_0.script, 'Hant')))) {
        var tmp$_1;
        if (equals(element_0.script, 'Hant')) {
          tmp$_1 = 'TW, HK or MO';
        }
         else {
          tmp$_1 = 'CN or SG';
        }
        var countries_0 = tmp$_1;
        var message_2 = 'Script `' + toString(element_0.script) + '` for Locale with language `zh` is not supported.' + '\n' + 'Use a corresponding country instead (' + countries_0 + ').';
        throw IllegalArgumentException_init(message_2.toString());
      }
    }
  }
  ArgumentsSupportingTranslator.prototype.translate_n3w7xm$ = function (translatable) {
    if (Kotlin.isType(translatable, TranslatableWithArgs))
      return this.translateWithArgs_sf0tom$_0(translatable);
    else
      return this.translateWithoutArgs_n3w7xm$(translatable);
  };
  ArgumentsSupportingTranslator.prototype.translateWithArgs_sf0tom$_0 = function (translatableWithArgs) {
    var result = this.translateWithoutArgs_n3w7xm$(translatableWithArgs.translatable);
    var array = Array_0(translatableWithArgs.arguments.size);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      var init$result;
      var arg = translatableWithArgs.arguments.get_za3lpa$(i);
      if (Kotlin.isType(arg, Translatable)) {
        init$result = this.translate_n3w7xm$(arg);
      }
       else {
        init$result = arg;
      }
      array[i] = init$result;
    }
    var arr = array;
    return format(result, this.primaryLocale, arr[0], copyToArray(drop(arr, 1)).slice());
  };
  ArgumentsSupportingTranslator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ArgumentsSupportingTranslator',
    interfaces: [Translator]
  };
  function Locale(language, script, country, variant) {
    this.language = language;
    this.script = script;
    this.country = country;
    this.variant = variant;
    this.requireAtLeastOneAndOnlyLetters_0(this.language, 'language');
    if (this.script != null)
      this.requireAtLeastOneAndOnlyLetters_0(this.script, 'script');
    if (this.country != null)
      this.requireAtLeastOneAndOnlyLetters_0(this.country, 'country');
    if (this.variant != null) {
      if (!!isBlank(this.variant)) {
        var message = 'variant cannot be blank - use `null` if you do not want to specify it';
        throw IllegalArgumentException_init(message.toString());
      }
    }
  }
  Locale.prototype.requireAtLeastOneAndOnlyLetters_0 = function (field, name) {
    if (!Regex_init('[a-zA-Z]+').matches_6bul2c$(field)) {
      var message = name + ' has to match [a-zA-Z]+ was ' + field;
      throw IllegalArgumentException_init(message.toString());
    }
  };
  Locale.prototype.toString = function () {
    var sb = (new StringBuilder('Locale(')).append_gw00v9$(this.language);
    if (this.script != null) {
      sb.append_gw00v9$('-').append_gw00v9$(this.script);
    }
    if (this.country != null) {
      sb.append_gw00v9$('_').append_gw00v9$(this.country);
    }
    if (this.variant != null) {
      sb.append_gw00v9$('_').append_gw00v9$(this.variant);
    }
    sb.append_gw00v9$(')');
    return sb.toString();
  };
  Locale.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Locale',
    interfaces: []
  };
  function Locale_init(language, country, $this) {
    $this = $this || Object.create(Locale.prototype);
    Locale.call($this, language, null, country, null);
    return $this;
  }
  function Locale_init_0(language, $this) {
    $this = $this || Object.create(Locale.prototype);
    Locale.call($this, language, null, null, null);
    return $this;
  }
  Locale.prototype.component1 = function () {
    return this.language;
  };
  Locale.prototype.component2 = function () {
    return this.script;
  };
  Locale.prototype.component3 = function () {
    return this.country;
  };
  Locale.prototype.component4 = function () {
    return this.variant;
  };
  Locale.prototype.copy_dawjdb$ = function (language, script, country, variant) {
    return new Locale(language === void 0 ? this.language : language, script === void 0 ? this.script : script, country === void 0 ? this.country : country, variant === void 0 ? this.variant : variant);
  };
  Locale.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.language) | 0;
    result = result * 31 + Kotlin.hashCode(this.script) | 0;
    result = result * 31 + Kotlin.hashCode(this.country) | 0;
    result = result * 31 + Kotlin.hashCode(this.variant) | 0;
    return result;
  };
  Locale.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.language, other.language) && Kotlin.equals(this.script, other.script) && Kotlin.equals(this.country, other.country) && Kotlin.equals(this.variant, other.variant)))));
  };
  function LocaleOrderDecider() {
  }
  LocaleOrderDecider.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'LocaleOrderDecider',
    interfaces: []
  };
  function StringBasedTranslatable() {
  }
  StringBasedTranslatable.prototype.getDefault = function () {
    return this.value;
  };
  StringBasedTranslatable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'StringBasedTranslatable',
    interfaces: [Translatable]
  };
  function Translatable() {
    Translatable$Companion_getInstance();
  }
  Object.defineProperty(Translatable.prototype, 'id', {
    get: function () {
      return fullName(Kotlin.getKClassFromExpression(this), this) + Translatable$Companion_getInstance().ID_SEPARATOR + this.name;
    }
  });
  function Translatable$Companion() {
    Translatable$Companion_instance = this;
    this.ID_SEPARATOR = '-';
  }
  Translatable$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Translatable$Companion_instance = null;
  function Translatable$Companion_getInstance() {
    if (Translatable$Companion_instance === null) {
      new Translatable$Companion();
    }
    return Translatable$Companion_instance;
  }
  Translatable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Translatable',
    interfaces: []
  };
  function TranslatableBasedRawString(translatable) {
    this.translatable = translatable;
  }
  TranslatableBasedRawString.prototype.toString = function () {
    return this.translatable.getDefault() + ' (TranslatableRawString)';
  };
  TranslatableBasedRawString.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TranslatableBasedRawString',
    interfaces: [RawString]
  };
  TranslatableBasedRawString.prototype.component1 = function () {
    return this.translatable;
  };
  TranslatableBasedRawString.prototype.copy_n3w7xm$ = function (translatable) {
    return new TranslatableBasedRawString(translatable === void 0 ? this.translatable : translatable);
  };
  TranslatableBasedRawString.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.translatable) | 0;
    return result;
  };
  TranslatableBasedRawString.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.translatable, other.translatable))));
  };
  function TranslatableWithArgs(translatable, arguments_0) {
    TranslatableWithArgs$Companion_getInstance();
    this.translatable = translatable;
    this.arguments = arguments_0;
    if (!!this.arguments.isEmpty()) {
      var message = 'No arguments specified, do not wrap the translatable into an ' + toString(getKClass(TranslatableWithArgs).simpleName) + ' if not needed.' + ('\n' + 'Translatable: ' + this.translatable);
      throw IllegalArgumentException_init(message.toString());
    }
  }
  Object.defineProperty(TranslatableWithArgs.prototype, 'name', {
    get: function () {
      return this.translatable.name;
    }
  });
  TranslatableWithArgs.prototype.getDefault = function () {
    return format_0(this.translatable.getDefault(), this.arguments.get_za3lpa$(0), copyToArray(drop_0(this.arguments, 1)).slice());
  };
  function TranslatableWithArgs$Companion() {
    TranslatableWithArgs$Companion_instance = this;
  }
  TranslatableWithArgs$Companion.prototype.createList_0 = function (arg) {
    var $receiver = ArrayList_init(1);
    $receiver.add_11rb$(arg);
    return $receiver;
  };
  TranslatableWithArgs$Companion.prototype.createList_1 = function (arg1, arg2) {
    var $receiver = ArrayList_init(2);
    $receiver.add_11rb$(arg1);
    $receiver.add_11rb$(arg2);
    return $receiver;
  };
  TranslatableWithArgs$Companion.prototype.createList_2 = function (arg1, arg2, otherArgs) {
    var $receiver = ArrayList_init(2 + otherArgs.length | 0);
    $receiver.add_11rb$(arg1);
    $receiver.add_11rb$(arg2);
    addAll($receiver, otherArgs);
    return $receiver;
  };
  TranslatableWithArgs$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var TranslatableWithArgs$Companion_instance = null;
  function TranslatableWithArgs$Companion_getInstance() {
    if (TranslatableWithArgs$Companion_instance === null) {
      new TranslatableWithArgs$Companion();
    }
    return TranslatableWithArgs$Companion_instance;
  }
  TranslatableWithArgs.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TranslatableWithArgs',
    interfaces: [Translatable]
  };
  function TranslatableWithArgs_init(translatable, argument, $this) {
    $this = $this || Object.create(TranslatableWithArgs.prototype);
    TranslatableWithArgs.call($this, translatable, TranslatableWithArgs$Companion_getInstance().createList_0(argument));
    return $this;
  }
  function TranslatableWithArgs_init_0(translatable, arg1, arg2, $this) {
    $this = $this || Object.create(TranslatableWithArgs.prototype);
    TranslatableWithArgs.call($this, translatable, TranslatableWithArgs$Companion_getInstance().createList_1(arg1, arg2));
    return $this;
  }
  function TranslatableWithArgs_init_1(translatable, arg1, arg2, otherArgs, $this) {
    $this = $this || Object.create(TranslatableWithArgs.prototype);
    TranslatableWithArgs.call($this, translatable, TranslatableWithArgs$Companion_getInstance().createList_2(arg1, arg2, otherArgs));
    return $this;
  }
  TranslatableWithArgs.prototype.component1 = function () {
    return this.translatable;
  };
  TranslatableWithArgs.prototype.component2 = function () {
    return this.arguments;
  };
  TranslatableWithArgs.prototype.copy_wac1ym$ = function (translatable, arguments_0) {
    return new TranslatableWithArgs(translatable === void 0 ? this.translatable : translatable, arguments_0 === void 0 ? this.arguments : arguments_0);
  };
  TranslatableWithArgs.prototype.toString = function () {
    return 'TranslatableWithArgs(translatable=' + Kotlin.toString(this.translatable) + (', arguments=' + Kotlin.toString(this.arguments)) + ')';
  };
  TranslatableWithArgs.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.translatable) | 0;
    result = result * 31 + Kotlin.hashCode(this.arguments) | 0;
    return result;
  };
  TranslatableWithArgs.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.translatable, other.translatable) && Kotlin.equals(this.arguments, other.arguments)))));
  };
  function TranslationSupplier() {
  }
  TranslationSupplier.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'TranslationSupplier',
    interfaces: []
  };
  function Translator() {
  }
  Translator.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Translator',
    interfaces: []
  };
  function Untranslatable(representation) {
    Untranslatable$Companion_getInstance();
    this.name_btkoro$_0 = representation;
  }
  Object.defineProperty(Untranslatable.prototype, 'name', {
    get: function () {
      return this.name_btkoro$_0;
    }
  });
  Untranslatable.prototype.getDefault = function () {
    return this.name;
  };
  function Untranslatable$Companion() {
    Untranslatable$Companion_instance = this;
    this.EMPTY = new Untranslatable('');
  }
  Untranslatable$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Untranslatable$Companion_instance = null;
  function Untranslatable$Companion_getInstance() {
    if (Untranslatable$Companion_instance === null) {
      new Untranslatable$Companion();
    }
    return Untranslatable$Companion_instance;
  }
  Untranslatable.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Untranslatable',
    interfaces: [Translatable]
  };
  function Untranslatable_init(representation, $this) {
    $this = $this || Object.create(Untranslatable.prototype);
    Untranslatable_init_0(Untranslatable_init$lambda(representation), $this);
    return $this;
  }
  function Untranslatable_init_0(representation, $this) {
    $this = $this || Object.create(Untranslatable.prototype);
    Untranslatable.call($this, representation());
    return $this;
  }
  function Untranslatable_init$lambda(closure$representation) {
    return function () {
      return closure$representation.toString();
    };
  }
  function UsingDefaultTranslator(primaryLocale) {
    if (primaryLocale === void 0)
      primaryLocale = getDefaultLocale();
    ArgumentsSupportingTranslator.call(this, primaryLocale, emptyList());
  }
  UsingDefaultTranslator.prototype.translateWithoutArgs_n3w7xm$ = function (translatable) {
    return translatable.getDefault();
  };
  UsingDefaultTranslator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UsingDefaultTranslator',
    interfaces: [ArgumentsSupportingTranslator]
  };
  function CoreFactory() {
  }
  CoreFactory.prototype.newReportingPlantNullable_df1686$$default = function (assertionVerb, subjectProvider, reporter, nullRepresentation) {
    return newReportingPlantNullable(this, assertionVerb, subjectProvider, reporter, nullRepresentation);
  };
  CoreFactory.prototype.newReportingPlantNullable_df1686$ = function (assertionVerb, subjectProvider, reporter, nullRepresentation, callback$default) {
    if (nullRepresentation === void 0)
      nullRepresentation = RawString$Companion_getInstance().NULL;
    return callback$default ? callback$default(assertionVerb, subjectProvider, reporter, nullRepresentation) : this.newReportingPlantNullable_df1686$$default(assertionVerb, subjectProvider, reporter, nullRepresentation);
  };
  CoreFactory.prototype.newReportingPlantNullable_xmyivw$$default = function (assertionVerb, subjectProvider, assertionChecker, nullRepresentation) {
    return newReportingPlantNullable_0(this, assertionVerb, subjectProvider, assertionChecker, nullRepresentation);
  };
  CoreFactory.prototype.newReportingPlantNullable_xmyivw$ = function (assertionVerb, subjectProvider, assertionChecker, nullRepresentation, callback$default) {
    if (nullRepresentation === void 0)
      nullRepresentation = RawString$Companion_getInstance().NULL;
    return callback$default ? callback$default(assertionVerb, subjectProvider, assertionChecker, nullRepresentation) : this.newReportingPlantNullable_xmyivw$$default(assertionVerb, subjectProvider, assertionChecker, nullRepresentation);
  };
  CoreFactory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CoreFactory',
    interfaces: [CoreFactoryCommon]
  };
  function formatFloatingPointNumber(number) {
    return number.toString();
  }
  function get_fullName($receiver) {
    var tmp$;
    return (tmp$ = simpleNameWithJsFallback($receiver)) != null ? tmp$ : '<unknown> (js: ' + get_js($receiver).name + ')';
  }
  function simpleNameWithJsFallback($receiver) {
    var tmp$;
    return (tmp$ = $receiver.simpleName) != null ? tmp$ : jsFallback($receiver);
  }
  function jsFallback($receiver) {
    var tmp$;
    var js = get_js($receiver);
    if (equals(js.name, 'Boolean'))
      tmp$ = 'Boolean';
    else if (!contains(js.name, '$'))
      tmp$ = '<unknown> (js: ' + js.name + ')';
    else
      tmp$ = null;
    return tmp$;
  }
  function fullName($receiver, obj) {
    var tmp$, tmp$_0;
    var name = $receiver.simpleName;
    if (name != null && startsWith(name, 'Function'))
      tmp$_0 = toString(name) + ' (js: ' + obj.name.toString() + ')';
    else
      tmp$_0 = (tmp$ = simpleNameWithJsFallback($receiver)) != null ? tmp$ : objFallback($receiver, obj);
    return tmp$_0;
  }
  function objFallback($receiver, obj) {
    var tmp$;
    var proto = Object.getPrototypeOf(obj);
    var constructor = proto != null ? proto.constructor : null;
    var name = '`object: ';
    if (constructor != null && '$metadata$' in constructor && constructor.$metadata$.interfaces.length == 1) {
      name += constructor.$metadata$.interfaces[0].name + '`';
    }
     else {
      name += '<unknown>`';
    }
    return (typeof (tmp$ = name) === 'string' ? tmp$ : throwCCE()) + (' (js: ' + get_js($receiver).name + ')');
  }
  function cast($receiver, any) {
    var tmp$, tmp$_0;
    if (any != null && (($receiver != null ? $receiver.equals(Kotlin.getKClassFromExpression(any)) : null) || $receiver.isInstance_s8jyv4$(any))) {
      var t = Kotlin.isType(tmp$ = any, Any) ? tmp$ : throwCCE();
      tmp$_0 = t;
    }
     else {
      var className = any != null ? fullName(Kotlin.getKClassFromExpression(any), any) : 'null';
      throw new ClassCastException('Cannot cast ' + className + ' to ' + get_fullName($receiver) + '.');
    }
    return tmp$_0;
  }
  var serviceRegistry;
  function loadSingleService(kClass) {
    return useSingleService(kClass, loadServices(kClass).iterator());
  }
  function loadServices$lambda(it) {
    return it();
  }
  function loadServices(kClass) {
    var tmp$, tmp$_0, tmp$_1;
    var set = (tmp$ = serviceRegistry.get_11rb$(kClass)) == null || Kotlin.isType(tmp$, Set) ? tmp$ : throwCCE();
    return (tmp$_1 = (tmp$_0 = set != null ? asSequence(set) : null) != null ? map(tmp$_0, loadServices$lambda) : null) != null ? tmp$_1 : emptySequence();
  }
  var registerService = defineInlineFunction('atrium-core-api-js.ch.tutteli.atrium.core.polyfills.registerService_9ce4rd$', wrapFunction(function () {
    var getKClass = Kotlin.getKClass;
    var registerService = _.ch.tutteli.atrium.core.polyfills.registerService_uj6y0s$;
    return function (T_0, isT, service) {
      registerService(getKClass(T_0), service);
    };
  }));
  function registerService_0(serviceInterface, service) {
    var $receiver = serviceRegistry;
    var tmp$;
    var value = $receiver.get_11rb$(serviceInterface);
    if (value == null) {
      var answer = HashSet_init();
      $receiver.put_xwzc9p$(serviceInterface, answer);
      tmp$ = answer;
    }
     else {
      tmp$ = value;
    }
    var services = tmp$;
    services.add_11rb$(service);
  }
  var properties;
  function getAtriumProperty(key) {
    return properties.get_11rb$(key);
  }
  function setAtriumProperty(key, newValue) {
    properties.put_xwzc9p$(key, newValue);
  }
  function appendln($receiver) {
    return $receiver.append_gw00v9$('\n');
  }
  function format($receiver, locale, arg, otherArgs) {
    return replacePlaceholdersWithArgs($receiver, arg, otherArgs);
  }
  function format_0($receiver, arg, otherArgs) {
    return replacePlaceholdersWithArgs($receiver, arg, otherArgs);
  }
  function replacePlaceholdersWithArgs(string, arg, otherArgs) {
    var placeholder = Regex_init('%s');
    var replacement = arg.toString();
    var tmp = {v: placeholder.replaceFirst_x2uqeu$(string, replacement)};
    var tmp$;
    for (tmp$ = 0; tmp$ !== otherArgs.length; ++tmp$) {
      var element = otherArgs[tmp$];
      var $receiver = tmp.v;
      var replacement_0 = element.toString();
      tmp.v = placeholder.replaceFirst_x2uqeu$($receiver, replacement_0);
    }
    return tmp.v;
  }
  function get_stackBacktrace($receiver) {
    var tmp$, tmp$_0;
    var nullableStack = typeof (tmp$ = $receiver.stack) === 'string' ? tmp$ : null;
    if (isNotNullAndNotBlank(nullableStack)) {
      tmp$_0 = splitStackLines(ensureNotNull(nullableStack));
    }
     else {
      tmp$_0 = listOf('Could not populate the stackBacktrace, please file a bug report at https://github.com/robstoll/atrium/issues/new');
    }
    return tmp$_0;
  }
  function splitStackLines$lambda(it) {
    return contains(it, 'init') && contains(it, 'kotlin.js');
  }
  function splitStackLines$lambda_0(closure$searchWord) {
    return function (it) {
      return substringAfter(it, closure$searchWord);
    };
  }
  function splitStackLines(stack) {
    var searchWord = '  at ';
    var firstFrame = indexOf(stack, searchWord);
    var startIndex = firstFrame + searchWord.length | 0;
    return toList(map(dropWhile(splitToSequence(stack.substring(startIndex), Kotlin.charArrayOf(10)), splitStackLines$lambda), splitStackLines$lambda_0(searchWord)));
  }
  function AtriumError(message) {
    AtriumError$Companion_getInstance();
    AssertionError_init(message, this);
    this.name = 'AtriumError';
  }
  function AtriumError$Companion() {
    AtriumError$Companion_instance = this;
  }
  AtriumError$Companion.prototype.create_bek6ta$ = function (message, atriumErrorAdjuster) {
    return createAtriumError(message, atriumErrorAdjuster);
  };
  AtriumError$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AtriumError$Companion_instance = null;
  function AtriumError$Companion_getInstance() {
    if (AtriumError$Companion_instance === null) {
      new AtriumError$Companion();
    }
    return AtriumError$Companion_instance;
  }
  AtriumError.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AtriumError',
    interfaces: [AssertionError]
  };
  function AtriumErrorAdjuster() {
  }
  AtriumErrorAdjuster.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AtriumErrorAdjuster',
    interfaces: [AtriumErrorAdjusterCommon]
  };
  function getDefaultLocale() {
    return Locale_init('en', 'GB');
  }
  var package$ch = _.ch || (_.ch = {});
  var package$tutteli = package$ch.tutteli || (package$ch.tutteli = {});
  var package$atrium = package$tutteli.atrium || (package$tutteli.atrium = {});
  var package$assertions = package$atrium.assertions || (package$atrium.assertions = {});
  package$assertions.Assertion = Assertion;
  AssertionGroup$Builder.prototype.BasicAssertionGroupBuilder = AssertionGroup$Builder$BasicAssertionGroupBuilder;
  AssertionGroup$Builder.prototype.ExplanatoryAssertionGroupOption = AssertionGroup$Builder$ExplanatoryAssertionGroupOption;
  AssertionGroup$Builder.prototype.ExplanatoryAssertionGroupBuilder = AssertionGroup$Builder$ExplanatoryAssertionGroupBuilder;
  AssertionGroup$Builder.prototype.EmptyNameAndSubjectAssertionGroupBuilder = AssertionGroup$Builder$EmptyNameAndSubjectAssertionGroupBuilder;
  Object.defineProperty(AssertionGroup, 'Builder', {
    get: AssertionGroup$Builder_getInstance
  });
  package$assertions.AssertionGroup = AssertionGroup;
  package$assertions.AssertionGroupType = AssertionGroupType;
  package$assertions.BasicAssertionGroup = BasicAssertionGroup;
  package$assertions.BasicDescriptiveAssertion_init_vavzzu$ = BasicDescriptiveAssertion_init;
  package$assertions.BasicDescriptiveAssertion = BasicDescriptiveAssertion;
  package$assertions.BasicExplanatoryAssertion = BasicExplanatoryAssertion;
  package$assertions.BulletPointIdentifier = BulletPointIdentifier;
  package$assertions.DescriptiveAssertion = DescriptiveAssertion;
  package$assertions.DoNotFilterAssertionGroupType = DoNotFilterAssertionGroupType;
  package$assertions.EmptyNameAndRepresentationAssertionGroup = EmptyNameAndRepresentationAssertionGroup;
  package$assertions.ExplanatoryAssertion = ExplanatoryAssertion;
  package$assertions.ExplanatoryAssertionGroup = ExplanatoryAssertionGroup;
  package$assertions.ExplanatoryAssertionGroupType = ExplanatoryAssertionGroupType;
  Object.defineProperty(package$assertions, 'DefaultExplanatoryAssertionGroupType', {
    get: DefaultExplanatoryAssertionGroupType_getInstance
  });
  Object.defineProperty(package$assertions, 'WarningAssertionGroupType', {
    get: WarningAssertionGroupType_getInstance
  });
  package$assertions.FeatureAssertionGroupType = FeatureAssertionGroupType;
  package$assertions.PrefixFeatureAssertionGroupHeader = PrefixFeatureAssertionGroupHeader;
  Object.defineProperty(package$assertions, 'DefaultFeatureAssertionGroupType', {
    get: DefaultFeatureAssertionGroupType_getInstance
  });
  package$assertions.IndentAssertionGroup = IndentAssertionGroup;
  package$assertions.IndentAssertionGroupType = IndentAssertionGroupType;
  Object.defineProperty(package$assertions, 'DefaultIndentAssertionGroupType', {
    get: DefaultIndentAssertionGroupType_getInstance
  });
  package$assertions.InvisibleAssertionGroup = InvisibleAssertionGroup;
  Object.defineProperty(package$assertions, 'InvisibleAssertionGroupType', {
    get: InvisibleAssertionGroupType_getInstance
  });
  package$assertions.ListAssertionGroupType = ListAssertionGroupType;
  Object.defineProperty(package$assertions, 'DefaultListAssertionGroupType', {
    get: DefaultListAssertionGroupType_getInstance
  });
  package$assertions.RepresentationOnlyAssertion = RepresentationOnlyAssertion;
  Object.defineProperty(package$assertions, 'RootAssertionGroupType', {
    get: RootAssertionGroupType_getInstance
  });
  package$assertions.SummaryAssertionGroupType = SummaryAssertionGroupType;
  package$assertions.PrefixSuccessfulSummaryAssertion = PrefixSuccessfulSummaryAssertion;
  package$assertions.PrefixFailingSummaryAssertion = PrefixFailingSummaryAssertion;
  Object.defineProperty(package$assertions, 'DefaultSummaryAssertionGroupType', {
    get: DefaultSummaryAssertionGroupType_getInstance
  });
  var package$builders = package$assertions.builders || (package$assertions.builders = {});
  Object.defineProperty(package$builders, 'assertionBuilder', {
    get: function () {
      return assertionBuilder;
    }
  });
  package$builders.AssertionBuilder = AssertionBuilder;
  package$builders.AssertionBuilderFinalStep = AssertionBuilderFinalStep;
  Object.defineProperty(AssertionGroupDescriptionAndEmptyRepresentationOption, 'Companion', {
    get: AssertionGroupDescriptionAndEmptyRepresentationOption$Companion_getInstance
  });
  package$builders.AssertionGroupDescriptionAndEmptyRepresentationOption = AssertionGroupDescriptionAndEmptyRepresentationOption;
  Object.defineProperty(AssertionGroupDescriptionAndRepresentationOption, 'Companion', {
    get: AssertionGroupDescriptionAndRepresentationOption$Companion_getInstance
  });
  package$builders.AssertionGroupDescriptionAndRepresentationOption = AssertionGroupDescriptionAndRepresentationOption;
  Object.defineProperty(AssertionsOption, 'Companion', {
    get: AssertionsOption$Companion_getInstance
  });
  package$builders.AssertionsOption = AssertionsOption;
  Object.defineProperty(BasicAssertionGroupFinalStep, 'Companion', {
    get: BasicAssertionGroupFinalStep$Companion_getInstance
  });
  package$builders.BasicAssertionGroupFinalStep = BasicAssertionGroupFinalStep;
  SubjectBasedOption.DefinedOption = SubjectBasedOption$DefinedOption;
  SubjectBasedOption.AbsentOption = SubjectBasedOption$AbsentOption;
  $$importsForInline$$['atrium-core-api-js'] = _;
  Object.defineProperty(SubjectBasedOption, 'Companion', {
    get: SubjectBasedOption$Companion_getInstance
  });
  package$builders.SubjectBasedOption = SubjectBasedOption;
  var package$common = package$builders.common || (package$builders.common = {});
  package$common.HoldsStep = HoldsStep;
  var package$impl = package$common.impl || (package$common.impl = {});
  package$impl.HoldsStepImpl = HoldsStepImpl;
  Object.defineProperty(Descriptive$HoldsOption, 'Companion', {
    get: Descriptive$HoldsOption$Companion_getInstance
  });
  Descriptive.HoldsOption = Descriptive$HoldsOption;
  Object.defineProperty(Descriptive$DescriptionOption, 'Companion', {
    get: Descriptive$DescriptionOption$Companion_getInstance
  });
  Descriptive.DescriptionOption = Descriptive$DescriptionOption;
  Object.defineProperty(Descriptive$FinalStep, 'Companion', {
    get: Descriptive$FinalStep$Companion_getInstance
  });
  Descriptive.FinalStep = Descriptive$FinalStep;
  package$builders.Descriptive = Descriptive;
  package$builders.withFailureHint_qd7epp$ = withFailureHint;
  package$builders.withFailureHintBasedOnDefinedSubject_267qre$ = withFailureHintBasedOnDefinedSubject;
  package$builders.withFailureHintBasedOnSubject_4wrt6b$ = withFailureHintBasedOnSubject;
  Object.defineProperty(DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption, 'Companion', {
    get: DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption$Companion_getInstance
  });
  DescriptiveAssertionWithFailureHint.FailureHintSubjectDefinedOption = DescriptiveAssertionWithFailureHint$FailureHintSubjectDefinedOption;
  Object.defineProperty(DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption, 'Companion', {
    get: DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption$Companion_getInstance
  });
  DescriptiveAssertionWithFailureHint.FailureHintSubjectAbsentOption = DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption;
  Object.defineProperty(DescriptiveAssertionWithFailureHint$ShowOption, 'Companion', {
    get: DescriptiveAssertionWithFailureHint$ShowOption$Companion_getInstance
  });
  DescriptiveAssertionWithFailureHint.ShowOption = DescriptiveAssertionWithFailureHint$ShowOption;
  Object.defineProperty(DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption, 'Companion', {
    get: DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption$Companion_getInstance
  });
  DescriptiveAssertionWithFailureHint.ShowSubjectDefinedOption = DescriptiveAssertionWithFailureHint$ShowSubjectDefinedOption;
  Object.defineProperty(DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption, 'Companion', {
    get: DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption$Companion_getInstance
  });
  DescriptiveAssertionWithFailureHint.ShowSubjectAbsentOption = DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption;
  Object.defineProperty(DescriptiveAssertionWithFailureHint$FinalStep, 'Companion', {
    get: DescriptiveAssertionWithFailureHint$FinalStep$Companion_getInstance
  });
  DescriptiveAssertionWithFailureHint.FinalStep = DescriptiveAssertionWithFailureHint$FinalStep;
  package$builders.DescriptiveAssertionWithFailureHint = DescriptiveAssertionWithFailureHint;
  $$importsForInline$$['kbox-js'] = $module$kbox_js;
  Object.defineProperty(Explanatory$ExplanationOption, 'Companion', {
    get: Explanatory$ExplanationOption$Companion_getInstance
  });
  Explanatory.ExplanationOption = Explanatory$ExplanationOption;
  Object.defineProperty(Explanatory$FinalStep, 'Companion', {
    get: Explanatory$FinalStep$Companion_getInstance
  });
  Explanatory.FinalStep = Explanatory$FinalStep;
  package$builders.Explanatory = Explanatory;
  Object.defineProperty(ExplanatoryGroup$GroupTypeOption, 'Companion', {
    get: ExplanatoryGroup$GroupTypeOption$Companion_getInstance
  });
  ExplanatoryGroup.GroupTypeOption = ExplanatoryGroup$GroupTypeOption;
  Object.defineProperty(ExplanatoryGroup$FinalStep, 'Companion', {
    get: ExplanatoryGroup$FinalStep$Companion_getInstance
  });
  ExplanatoryGroup.FinalStep = ExplanatoryGroup$FinalStep;
  package$builders.ExplanatoryGroup = ExplanatoryGroup;
  package$builders.withExplanatoryAssertion_smk3tk$ = withExplanatoryAssertion;
  package$builders.withExplanatoryAssertion_2luw80$ = withExplanatoryAssertion_0;
  package$builders.withExplanatoryAssertions_fkn93x$ = withExplanatoryAssertions;
  package$builders.withExplanatoryAssertion_fkn93x$ = withExplanatoryAssertion_1;
  package$builders.withExplanatoryAssertion_dilg2v$ = withExplanatoryAssertion_2;
  package$builders.ExplanatoryAssertionGroupTypeOption = ExplanatoryAssertionGroupTypeOption;
  Object.defineProperty(ExplanatoryAssertionGroupFinalStep, 'Companion', {
    get: ExplanatoryAssertionGroupFinalStep$Companion_getInstance
  });
  package$builders.ExplanatoryAssertionGroupFinalStep = ExplanatoryAssertionGroupFinalStep;
  package$builders.get_fixedClaimGroup_5juyqv$ = get_fixedClaimGroup;
  Object.defineProperty(FixedClaimGroup$GroupTypeOption, 'Companion', {
    get: FixedClaimGroup$GroupTypeOption$Companion_getInstance
  });
  FixedClaimGroup.GroupTypeOption = FixedClaimGroup$GroupTypeOption;
  Object.defineProperty(FixedClaimGroup$HoldsOption, 'Companion', {
    get: FixedClaimGroup$HoldsOption$Companion_getInstance
  });
  FixedClaimGroup.HoldsOption = FixedClaimGroup$HoldsOption;
  Object.defineProperty(FixedClaimGroup$FinalStep, 'Companion', {
    get: FixedClaimGroup$FinalStep$Companion_getInstance
  });
  FixedClaimGroup.FinalStep = FixedClaimGroup$FinalStep;
  package$builders.FixedClaimGroup = FixedClaimGroup;
  FixedClaimLikeGroup.GroupTypeOption = FixedClaimLikeGroup$GroupTypeOption;
  FixedClaimLikeGroup.HoldsOption = FixedClaimLikeGroup$HoldsOption;
  package$builders.FixedClaimLikeGroup = FixedClaimLikeGroup;
  var package$impl_0 = package$builders.impl || (package$builders.impl = {});
  Object.defineProperty(package$impl_0, 'AssertionBuilderImpl', {
    get: AssertionBuilderImpl_getInstance
  });
  package$impl_0.AssertionGroupDescriptionAndEmptyRepresentationOptionImpl = AssertionGroupDescriptionAndEmptyRepresentationOptionImpl;
  package$impl_0.AssertionGroupDescriptionAndRepresentationOptionImpl = AssertionGroupDescriptionAndRepresentationOptionImpl;
  package$impl_0.AssertionsOptionImpl = AssertionsOptionImpl;
  package$impl_0.BasicAssertionGroupFinalStepImpl = BasicAssertionGroupFinalStepImpl;
  package$impl_0.FixedClaimLikeAssertionGroupHoldsOptionImpl = FixedClaimLikeAssertionGroupHoldsOptionImpl;
  var package$descriptive = package$impl_0.descriptive || (package$impl_0.descriptive = {});
  Object.defineProperty(package$descriptive, 'HoldsOptionImpl', {
    get: HoldsOptionImpl_getInstance
  });
  package$descriptive.DescriptionOptionImpl = DescriptionOptionImpl;
  package$descriptive.FinalStepImpl = FinalStepImpl;
  var package$descriptiveWithFailureHint = package$impl_0.descriptiveWithFailureHint || (package$impl_0.descriptiveWithFailureHint = {});
  package$descriptiveWithFailureHint.FailureHintSubjectDefinedOptionImpl = FailureHintSubjectDefinedOptionImpl;
  package$descriptiveWithFailureHint.FailureHintSubjectAbsentOptionImpl = FailureHintSubjectAbsentOptionImpl;
  package$descriptiveWithFailureHint.ShowOptionImpl = ShowOptionImpl;
  package$descriptiveWithFailureHint.ShowSubjectDefinedOptionImpl = ShowSubjectDefinedOptionImpl;
  package$descriptiveWithFailureHint.ShowSubjectAbsentOptionImpl = ShowSubjectAbsentOptionImpl;
  package$descriptiveWithFailureHint.FinalStepImpl = FinalStepImpl_0;
  var package$explanatory = package$impl_0.explanatory || (package$impl_0.explanatory = {});
  Object.defineProperty(package$explanatory, 'ExplanationOptionImpl', {
    get: ExplanationOptionImpl_getInstance
  });
  package$explanatory.FinalStepImpl = FinalStepImpl_1;
  var package$explanatoryGroup = package$impl_0.explanatoryGroup || (package$impl_0.explanatoryGroup = {});
  Object.defineProperty(package$explanatoryGroup, 'GroupTypeOptionImpl', {
    get: GroupTypeOptionImpl_getInstance
  });
  package$explanatoryGroup.FinalStepImpl = FinalStepImpl_2;
  var package$fixedClaimGroup = package$impl_0.fixedClaimGroup || (package$impl_0.fixedClaimGroup = {});
  package$fixedClaimGroup.FixedClaimAssertionGroup = FixedClaimAssertionGroup;
  Object.defineProperty(package$fixedClaimGroup, 'GroupTypeOptionImpl', {
    get: GroupTypeOptionImpl_getInstance_0
  });
  package$fixedClaimGroup.HoldsOptionImpl = HoldsOptionImpl_0;
  package$fixedClaimGroup.FinalStepImpl = FinalStepImpl_3;
  var package$partiallyFixedClaimGroup = package$impl_0.partiallyFixedClaimGroup || (package$impl_0.partiallyFixedClaimGroup = {});
  package$partiallyFixedClaimGroup.PartiallyFixedClaimAssertionGroup = PartiallyFixedClaimAssertionGroup;
  Object.defineProperty(package$partiallyFixedClaimGroup, 'GroupTypeOptionImpl', {
    get: GroupTypeOptionImpl_getInstance_1
  });
  package$partiallyFixedClaimGroup.HoldsOptionImpl = HoldsOptionImpl_1;
  package$partiallyFixedClaimGroup.FinalStepImpl = FinalStepImpl_4;
  var package$representationOnly = package$impl_0.representationOnly || (package$impl_0.representationOnly = {});
  package$representationOnly.RepresentationOnlyAssertionImpl = RepresentationOnlyAssertionImpl;
  Object.defineProperty(package$representationOnly, 'HoldsStepImpl', {
    get: HoldsStepImpl_getInstance
  });
  package$representationOnly.RepresentationStepImpl = RepresentationStepImpl;
  package$representationOnly.FinalStepImpl = FinalStepImpl_5;
  package$builders.get_invisibleGroup_5juyqv$ = get_invisibleGroup;
  package$builders.get_partiallyFixedClaimGroup_5juyqv$ = get_partiallyFixedClaimGroup;
  Object.defineProperty(PartiallyFixedClaimGroup$GroupTypeOption, 'Companion', {
    get: PartiallyFixedClaimGroup$GroupTypeOption$Companion_getInstance
  });
  PartiallyFixedClaimGroup.GroupTypeOption = PartiallyFixedClaimGroup$GroupTypeOption;
  Object.defineProperty(PartiallyFixedClaimGroup$HoldsOption, 'Companion', {
    get: PartiallyFixedClaimGroup$HoldsOption$Companion_getInstance
  });
  PartiallyFixedClaimGroup.HoldsOption = PartiallyFixedClaimGroup$HoldsOption;
  Object.defineProperty(PartiallyFixedClaimGroup$FinalStep, 'Companion', {
    get: PartiallyFixedClaimGroup$FinalStep$Companion_getInstance
  });
  PartiallyFixedClaimGroup.FinalStep = PartiallyFixedClaimGroup$FinalStep;
  package$builders.PartiallyFixedClaimGroup = PartiallyFixedClaimGroup;
  Object.defineProperty(RepresentationOnly$HoldsStep, 'Companion', {
    get: RepresentationOnly$HoldsStep$Companion_getInstance
  });
  RepresentationOnly.HoldsStep = RepresentationOnly$HoldsStep;
  Object.defineProperty(RepresentationOnly$RepresentationStep, 'Companion', {
    get: RepresentationOnly$RepresentationStep$Companion_getInstance
  });
  RepresentationOnly.RepresentationStep = RepresentationOnly$RepresentationStep;
  Object.defineProperty(RepresentationOnly$FinalStep, 'Companion', {
    get: RepresentationOnly$FinalStep$Companion_getInstance
  });
  RepresentationOnly.FinalStep = RepresentationOnly$FinalStep;
  package$builders.RepresentationOnly = RepresentationOnly;
  package$builders.get_root_5juyqv$ = get_root;
  var package$checking = package$atrium.checking || (package$atrium.checking = {});
  package$checking.AssertionChecker = AssertionChecker;
  var package$core = package$atrium.core || (package$atrium.core = {});
  Object.defineProperty(package$core, 'coreFactory', {
    get: get_coreFactory
  });
  package$core.CoreFactoryCommon = CoreFactoryCommon;
  package$core.newReportingPlantNullable_wt2050$ = newReportingPlantNullable;
  package$core.newReportingPlantNullable_dpfkz2$ = newReportingPlantNullable_0;
  package$core.Some = Some;
  Object.defineProperty(Option, 'Companion', {
    get: Option$Companion_getInstance
  });
  package$core.Option = Option;
  package$core.getOrElse_he15va$ = getOrElse;
  Object.defineProperty(package$core, 'None', {
    get: None_getInstance
  });
  Object.defineProperty(package$core, 'falseProvider', {
    get: function () {
      return falseProvider;
    }
  });
  Object.defineProperty(package$core, 'trueProvider', {
    get: function () {
      return trueProvider;
    }
  });
  package$core.evalOnce_ba455$ = evalOnce;
  var package$polyfills = package$core.polyfills || (package$core.polyfills = {});
  package$polyfills.useSingleService_sybwf3$ = useSingleService;
  var package$creating = package$atrium.creating || (package$atrium.creating = {});
  package$creating.AssertMarker = AssertMarker;
  package$creating.AssertionHolder = AssertionHolder;
  package$creating.AssertionPlant = AssertionPlant;
  package$creating.AssertionPlantNullable = AssertionPlantNullable;
  AssertionPlantWithCommonFields.CommonFields_init_qqinrg$ = AssertionPlantWithCommonFields$AssertionPlantWithCommonFields$CommonFields_init;
  AssertionPlantWithCommonFields.CommonFields = AssertionPlantWithCommonFields$CommonFields;
  package$creating.AssertionPlantWithCommonFields = AssertionPlantWithCommonFields;
  package$creating.BaseAssertionPlant = BaseAssertionPlant;
  package$creating.BaseCollectingAssertionPlant = BaseCollectingAssertionPlant;
  package$creating.BaseReportingAssertionPlant = BaseReportingAssertionPlant;
  package$creating.CheckingAssertionPlant = CheckingAssertionPlant;
  package$creating.CollectingAssertionContainer = CollectingAssertionContainer;
  package$creating.CollectingAssertionPlant = CollectingAssertionPlant;
  package$creating.CollectingAssertionPlantNullable = CollectingAssertionPlantNullable;
  package$creating.ExpectMarker = ExpectMarker;
  package$creating.Expect = Expect;
  Object.defineProperty(MaybeSubject, 'Absent', {
    get: MaybeSubject$Absent_getInstance
  });
  MaybeSubject.Present = MaybeSubject$Present;
  Object.defineProperty(MaybeSubject, 'Companion', {
    get: MaybeSubject$Companion_getInstance
  });
  package$creating.MaybeSubject = MaybeSubject;
  package$creating.PlantHasNoSubjectException_init = PlantHasNoSubjectException_init;
  package$creating.PlantHasNoSubjectException = PlantHasNoSubjectException;
  Object.defineProperty(ReportingAssertionContainer$AssertionCheckerDecorator, 'Companion', {
    get: ReportingAssertionContainer$AssertionCheckerDecorator$Companion_getInstance
  });
  ReportingAssertionContainer.AssertionCheckerDecorator = ReportingAssertionContainer$AssertionCheckerDecorator;
  package$creating.ReportingAssertionContainer = ReportingAssertionContainer;
  package$creating.ReportingAssertionPlant = ReportingAssertionPlant;
  package$creating.ReportingAssertionPlantNullable = ReportingAssertionPlantNullable;
  package$creating.SubjectProvider = SubjectProvider;
  Object.defineProperty(AssertionFormatter, 'Companion', {
    get: AssertionFormatter$Companion_getInstance
  });
  var package$reporting = package$atrium.reporting || (package$atrium.reporting = {});
  package$reporting.AssertionFormatter = AssertionFormatter;
  Object.defineProperty(AssertionFormatterController, 'Companion', {
    get: AssertionFormatterController$Companion_getInstance
  });
  package$reporting.AssertionFormatterController = AssertionFormatterController;
  package$reporting.AssertionFormatterFacade = AssertionFormatterFacade;
  Object.defineProperty(AssertionFormatterParameterObject, 'Companion', {
    get: AssertionFormatterParameterObject$Companion_getInstance
  });
  package$reporting.AssertionFormatterParameterObject = AssertionFormatterParameterObject;
  package$reporting.AssertionPairFormatter = AssertionPairFormatter;
  package$reporting.createAtriumError_moof3z$ = createAtriumError;
  package$reporting.AtriumErrorAdjusterCommon = AtriumErrorAdjusterCommon;
  package$reporting.LazyRepresentation = LazyRepresentation;
  package$reporting.MethodCallFormatter = MethodCallFormatter;
  package$reporting.ObjectFormatter = ObjectFormatter;
  Object.defineProperty(RawString, 'Companion', {
    get: RawString$Companion_getInstance
  });
  package$reporting.RawString = RawString;
  package$reporting.Reporter = Reporter;
  Object.defineProperty(package$reporting, 'reporter', {
    get: get_reporter
  });
  Object.defineProperty(ReporterFactory, 'Companion', {
    get: ReporterFactory$Companion_getInstance
  });
  package$reporting.ReporterFactory = ReporterFactory;
  package$reporting.StringBasedRawString = StringBasedRawString;
  Object.defineProperty(package$reporting, 'BUG_REPORT_URL', {
    get: function () {
      return BUG_REPORT_URL;
    }
  });
  Object.defineProperty(package$reporting, 'SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG', {
    get: function () {
      return SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG;
    }
  });
  Object.defineProperty(package$reporting, 'SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE', {
    get: function () {
      return SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE;
    }
  });
  var package$translating = package$reporting.translating || (package$reporting.translating = {});
  package$translating.ArgumentsSupportingTranslator = ArgumentsSupportingTranslator;
  package$translating.Locale_init_puj7f4$ = Locale_init;
  package$translating.Locale_init_61zpoe$ = Locale_init_0;
  package$translating.Locale = Locale;
  package$translating.LocaleOrderDecider = LocaleOrderDecider;
  package$translating.StringBasedTranslatable = StringBasedTranslatable;
  Object.defineProperty(Translatable, 'Companion', {
    get: Translatable$Companion_getInstance
  });
  package$translating.Translatable = Translatable;
  package$translating.TranslatableBasedRawString = TranslatableBasedRawString;
  Object.defineProperty(TranslatableWithArgs, 'Companion', {
    get: TranslatableWithArgs$Companion_getInstance
  });
  package$translating.TranslatableWithArgs_init_73s8gv$ = TranslatableWithArgs_init;
  package$translating.TranslatableWithArgs_init_k5h9va$ = TranslatableWithArgs_init_0;
  package$translating.TranslatableWithArgs_init_oy3j4s$ = TranslatableWithArgs_init_1;
  package$translating.TranslatableWithArgs = TranslatableWithArgs;
  package$translating.TranslationSupplier = TranslationSupplier;
  package$translating.Translator = Translator;
  Object.defineProperty(Untranslatable, 'Companion', {
    get: Untranslatable$Companion_getInstance
  });
  package$translating.Untranslatable_init_6bul2c$ = Untranslatable_init;
  package$translating.Untranslatable_init_h4ejuu$ = Untranslatable_init_0;
  package$translating.Untranslatable = Untranslatable;
  package$translating.UsingDefaultTranslator = UsingDefaultTranslator;
  package$core.CoreFactory = CoreFactory;
  package$polyfills.formatFloatingPointNumber_3p81yu$ = formatFloatingPointNumber;
  package$polyfills.get_fullName_lr8r8q$ = get_fullName;
  package$polyfills.fullName_sqpj8w$ = fullName;
  package$polyfills.cast_o6trj3$ = cast;
  package$polyfills.loadSingleService_lmshww$ = loadSingleService;
  package$polyfills.loadServices_lmshww$ = loadServices;
  package$polyfills.registerService_uj6y0s$ = registerService_0;
  package$polyfills.getAtriumProperty_61zpoe$ = getAtriumProperty;
  package$polyfills.setAtriumProperty_puj7f4$ = setAtriumProperty;
  package$polyfills.appendln_dn5lc7$ = appendln;
  package$polyfills.format_mnwk67$ = format;
  package$polyfills.format_kv1tue$ = format_0;
  package$polyfills.get_stackBacktrace_dbl4o4$ = get_stackBacktrace;
  Object.defineProperty(AtriumError, 'Companion', {
    get: AtriumError$Companion_getInstance
  });
  package$reporting.AtriumError = AtriumError;
  package$reporting.AtriumErrorAdjuster = AtriumErrorAdjuster;
  package$translating.getDefaultLocale = getDefaultLocale;
  Object.defineProperty(BasicAssertionGroup.prototype, 'name', Object.getOwnPropertyDescriptor(AssertionGroup.prototype, 'name'));
  Object.defineProperty(BasicAssertionGroup.prototype, 'subject', Object.getOwnPropertyDescriptor(AssertionGroup.prototype, 'subject'));
  BasicAssertionGroup.prototype.holds = AssertionGroup.prototype.holds;
  Object.defineProperty(BasicDescriptiveAssertion.prototype, 'expected', Object.getOwnPropertyDescriptor(DescriptiveAssertion.prototype, 'expected'));
  BasicExplanatoryAssertion.prototype.holds = ExplanatoryAssertion.prototype.holds;
  Object.defineProperty(EmptyNameAndRepresentationAssertionGroup.prototype, 'name', Object.getOwnPropertyDescriptor(AssertionGroup.prototype, 'name'));
  Object.defineProperty(EmptyNameAndRepresentationAssertionGroup.prototype, 'subject', Object.getOwnPropertyDescriptor(AssertionGroup.prototype, 'subject'));
  EmptyNameAndRepresentationAssertionGroup.prototype.holds = AssertionGroup.prototype.holds;
  DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption.prototype.ifAbsent_skz6mo$ = SubjectBasedOption$AbsentOption.prototype.ifAbsent_skz6mo$;
  DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption.prototype.ifAbsent_skz6mo$ = SubjectBasedOption$AbsentOption.prototype.ifAbsent_skz6mo$;
  Object.defineProperty(FixedClaimGroup$GroupTypeOption.prototype, 'withFeatureType', Object.getOwnPropertyDescriptor(FixedClaimLikeGroup$GroupTypeOption.prototype, 'withFeatureType'));
  Object.defineProperty(FixedClaimGroup$GroupTypeOption.prototype, 'withListType', Object.getOwnPropertyDescriptor(FixedClaimLikeGroup$GroupTypeOption.prototype, 'withListType'));
  AssertionBuilderImpl.prototype.createDescriptive_5v4qxd$ = AssertionBuilder.prototype.createDescriptive_5v4qxd$;
  AssertionBuilderImpl.prototype.createDescriptive_67aujf$ = AssertionBuilder.prototype.createDescriptive_67aujf$;
  AssertionBuilderImpl.prototype.createDescriptive_70d256$ = AssertionBuilder.prototype.createDescriptive_70d256$;
  AssertionBuilderImpl.prototype.createDescriptive_84vzck$ = AssertionBuilder.prototype.createDescriptive_84vzck$;
  AssertionGroupDescriptionAndEmptyRepresentationOptionImpl.prototype.withDescription_61zpoe$ = AssertionGroupDescriptionAndEmptyRepresentationOption.prototype.withDescription_61zpoe$;
  AssertionGroupDescriptionAndRepresentationOptionImpl.prototype.withDescriptionAndRepresentation_afmhsq$ = AssertionGroupDescriptionAndRepresentationOption.prototype.withDescriptionAndRepresentation_afmhsq$;
  AssertionGroupDescriptionAndRepresentationOptionImpl.prototype.withDescriptionAndRepresentation_ymx9x6$ = AssertionGroupDescriptionAndRepresentationOption.prototype.withDescriptionAndRepresentation_ymx9x6$;
  AssertionGroupDescriptionAndRepresentationOptionImpl.prototype.withDescriptionAndRepresentation_4w9ihe$ = AssertionGroupDescriptionAndRepresentationOption.prototype.withDescriptionAndRepresentation_4w9ihe$;
  AssertionGroupDescriptionAndRepresentationOptionImpl.prototype.withDescriptionAndEmptyRepresentation_61zpoe$ = AssertionGroupDescriptionAndRepresentationOption.prototype.withDescriptionAndEmptyRepresentation_61zpoe$;
  AssertionGroupDescriptionAndRepresentationOptionImpl.prototype.withDescriptionAndEmptyRepresentation_n3w7xm$ = AssertionGroupDescriptionAndRepresentationOption.prototype.withDescriptionAndEmptyRepresentation_n3w7xm$;
  AssertionsOptionImpl.prototype.withAssertions_goesb0$ = AssertionsOption.prototype.withAssertions_goesb0$;
  AssertionsOptionImpl.prototype.withAssertions_j1wybp$ = AssertionsOption.prototype.withAssertions_j1wybp$;
  AssertionsOptionImpl.prototype.withAssertion_94orq3$ = AssertionsOption.prototype.withAssertion_94orq3$;
  DescriptionOptionImpl.prototype.withDescriptionAndRepresentation_4w9ihe$ = Descriptive$DescriptionOption.prototype.withDescriptionAndRepresentation_4w9ihe$;
  FailureHintSubjectAbsentOptionImpl.prototype.ifAbsent_skz6mo$ = DescriptiveAssertionWithFailureHint$FailureHintSubjectAbsentOption.prototype.ifAbsent_skz6mo$;
  ShowOptionImpl.prototype.showOnlyIfSubjectDefined_dcyqpd$ = DescriptiveAssertionWithFailureHint$ShowOption.prototype.showOnlyIfSubjectDefined_dcyqpd$;
  ShowOptionImpl.prototype.showBasedOnDefinedSubjectOnlyIf_by3r04$ = DescriptiveAssertionWithFailureHint$ShowOption.prototype.showBasedOnDefinedSubjectOnlyIf_by3r04$;
  ShowOptionImpl.prototype.showBasedOnSubjectOnlyIf_x68nkh$ = DescriptiveAssertionWithFailureHint$ShowOption.prototype.showBasedOnSubjectOnlyIf_x68nkh$;
  ShowSubjectAbsentOptionImpl.prototype.ifAbsent_skz6mo$ = DescriptiveAssertionWithFailureHint$ShowSubjectAbsentOption.prototype.ifAbsent_skz6mo$;
  ExplanationOptionImpl.prototype.withExplanation_5pe57p$ = Explanatory$ExplanationOption.prototype.withExplanation_5pe57p$;
  ExplanationOptionImpl.prototype.withExplanation_61zpoe$ = Explanatory$ExplanationOption.prototype.withExplanation_61zpoe$;
  ExplanationOptionImpl.prototype.withExplanation_n3w7xm$ = Explanatory$ExplanationOption.prototype.withExplanation_n3w7xm$;
  ExplanationOptionImpl.prototype.withDescription_5pe57p$ = Explanatory$ExplanationOption.prototype.withDescription_5pe57p$;
  ExplanationOptionImpl.prototype.withDescription_n3w7xm$ = Explanatory$ExplanationOption.prototype.withDescription_n3w7xm$;
  ExplanationOptionImpl.prototype.withDescription_s8jyv4$ = Explanatory$ExplanationOption.prototype.withDescription_s8jyv4$;
  Object.defineProperty(FixedClaimAssertionGroup.prototype, 'name', Object.getOwnPropertyDescriptor(AssertionGroup.prototype, 'name'));
  Object.defineProperty(FixedClaimAssertionGroup.prototype, 'subject', Object.getOwnPropertyDescriptor(AssertionGroup.prototype, 'subject'));
  Object.defineProperty(GroupTypeOptionImpl_0.prototype, 'withFeatureType', Object.getOwnPropertyDescriptor(FixedClaimGroup$GroupTypeOption.prototype, 'withFeatureType'));
  Object.defineProperty(GroupTypeOptionImpl_0.prototype, 'withListType', Object.getOwnPropertyDescriptor(FixedClaimGroup$GroupTypeOption.prototype, 'withListType'));
  Object.defineProperty(PartiallyFixedClaimAssertionGroup.prototype, 'name', Object.getOwnPropertyDescriptor(AssertionGroup.prototype, 'name'));
  Object.defineProperty(PartiallyFixedClaimAssertionGroup.prototype, 'subject', Object.getOwnPropertyDescriptor(AssertionGroup.prototype, 'subject'));
  Object.defineProperty(PartiallyFixedClaimGroup$GroupTypeOption.prototype, 'withFeatureType', Object.getOwnPropertyDescriptor(FixedClaimLikeGroup$GroupTypeOption.prototype, 'withFeatureType'));
  Object.defineProperty(PartiallyFixedClaimGroup$GroupTypeOption.prototype, 'withListType', Object.getOwnPropertyDescriptor(FixedClaimLikeGroup$GroupTypeOption.prototype, 'withListType'));
  Object.defineProperty(GroupTypeOptionImpl_1.prototype, 'withFeatureType', Object.getOwnPropertyDescriptor(PartiallyFixedClaimGroup$GroupTypeOption.prototype, 'withFeatureType'));
  Object.defineProperty(GroupTypeOptionImpl_1.prototype, 'withListType', Object.getOwnPropertyDescriptor(PartiallyFixedClaimGroup$GroupTypeOption.prototype, 'withListType'));
  Object.defineProperty(AssertionPlant.prototype, 'maybeSubject', Object.getOwnPropertyDescriptor(BaseAssertionPlant.prototype, 'maybeSubject'));
  Object.defineProperty(AssertionPlantNullable.prototype, 'maybeSubject', Object.getOwnPropertyDescriptor(BaseAssertionPlant.prototype, 'maybeSubject'));
  Object.defineProperty(BaseCollectingAssertionPlant.prototype, 'maybeSubject', Object.getOwnPropertyDescriptor(BaseAssertionPlant.prototype, 'maybeSubject'));
  Object.defineProperty(BaseReportingAssertionPlant.prototype, 'maybeSubject', Object.getOwnPropertyDescriptor(BaseAssertionPlant.prototype, 'maybeSubject'));
  CheckingAssertionPlant.prototype.createAndAddAssertion_4119du$ = AssertionPlant.prototype.createAndAddAssertion_4119du$;
  Object.defineProperty(CheckingAssertionPlant.prototype, 'maybeSubject', Object.getOwnPropertyDescriptor(AssertionPlant.prototype, 'maybeSubject'));
  CollectingAssertionContainer.prototype.createAndAddAssertion_n2o31w$ = Expect.prototype.createAndAddAssertion_n2o31w$;
  CollectingAssertionContainer.prototype.createAndAddAssertion_auvybc$ = Expect.prototype.createAndAddAssertion_auvybc$;
  CollectingAssertionPlant.prototype.createAndAddAssertion_4119du$ = AssertionPlant.prototype.createAndAddAssertion_4119du$;
  Object.defineProperty(CollectingAssertionPlant.prototype, 'maybeSubject', Object.getOwnPropertyDescriptor(AssertionPlant.prototype, 'maybeSubject'));
  Object.defineProperty(CollectingAssertionPlantNullable.prototype, 'maybeSubject', Object.getOwnPropertyDescriptor(AssertionPlantNullable.prototype, 'maybeSubject'));
  ReportingAssertionContainer.prototype.createAndAddAssertion_n2o31w$ = Expect.prototype.createAndAddAssertion_n2o31w$;
  ReportingAssertionContainer.prototype.createAndAddAssertion_auvybc$ = Expect.prototype.createAndAddAssertion_auvybc$;
  ReportingAssertionPlant.prototype.createAndAddAssertion_4119du$ = AssertionPlant.prototype.createAndAddAssertion_4119du$;
  Object.defineProperty(ReportingAssertionPlant.prototype, 'maybeSubject', Object.getOwnPropertyDescriptor(AssertionPlant.prototype, 'maybeSubject'));
  Object.defineProperty(ReportingAssertionPlantNullable.prototype, 'maybeSubject', Object.getOwnPropertyDescriptor(AssertionPlantNullable.prototype, 'maybeSubject'));
  Object.defineProperty(StringBasedTranslatable.prototype, 'id', Object.getOwnPropertyDescriptor(Translatable.prototype, 'id'));
  Object.defineProperty(TranslatableWithArgs.prototype, 'id', Object.getOwnPropertyDescriptor(Translatable.prototype, 'id'));
  Object.defineProperty(Untranslatable.prototype, 'id', Object.getOwnPropertyDescriptor(Translatable.prototype, 'id'));
  CoreFactory.prototype.newReportingPlant_lh8iui$ = CoreFactoryCommon.prototype.newReportingPlant_lh8iui$;
  CoreFactory.prototype.newReportingPlant_xxj474$ = CoreFactoryCommon.prototype.newReportingPlant_xxj474$;
  CoreFactory.prototype.newDelegatingReportingAssertionContainer_8dvlby$ = CoreFactoryCommon.prototype.newDelegatingReportingAssertionContainer_8dvlby$;
  CoreFactory.prototype.newReportingAssertionContainer_nmnty6$ = CoreFactoryCommon.prototype.newReportingAssertionContainer_nmnty6$;
  CoreFactory.prototype.newReportingPlantAndAddAssertionsCreatedBy_m60waw$ = CoreFactoryCommon.prototype.newReportingPlantAndAddAssertionsCreatedBy_m60waw$;
  assertionBuilder = AssertionBuilderImpl_getInstance();
  invisibleGroup = lazy(invisibleGroup$lambda);
  coreFactory = lazy(coreFactory$lambda);
  falseProvider = falseProvider$lambda;
  trueProvider = trueProvider$lambda;
  reporter = lazy(reporter$lambda);
  BUG_REPORT_URL = 'https://github.com/robstoll/atrium/issues/new';
  SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG = 'Should not be shown to the user; if you see this, please file a bug report at https://github.com/robstoll/atrium/issues/new';
  SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE = new Untranslatable(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG);
  serviceRegistry = LinkedHashMap_init();
  properties = LinkedHashMap_init();
  Kotlin.defineModule('atrium-core-api-js', _);
  return _;
}));

//# sourceMappingURL=atrium-core-api-js.js.map
