#!/usr/bin/env bash
set -euo pipefail
shopt -s inherit_errexit
unset CDPATH

function escapeRegex() {
    local -r pattern='s/[.[\*$^(){}+?|\\]/\\&/g'
    if (($# == 0)); then
        sed "$pattern"
    elif (($# == 1)); then
        sed "$pattern" <<<"$1"
    else
        echo "wrong number of args"
        exit 1
    fi
}

function perlReplaceFile() {
    local -r file=$1
    local -r regex=$2
    perl -pi -e "BEGIN { \$r = 0 } \$r += $regex; END { exit !\$r }" "$file"
}

function replaceOrAddImportFile() {
    local -r file=$1
    local -r importToReplace=$2
    local -r newName=$3

    if ! replaceImportFile "$file" "$importToReplace" "$newName"; then
        addImportIfNeededFile "$file" "$newName"
    fi
}

function replaceImportFile() {
    local -r file=$1
    local -r importToReplace=$2
    local -r newName=$3
    perlReplace "s/import \\Q$importToReplace\\E\$/import $newName/g" "$file"
}

function addImportIfNeededFile() {
    local -r file=$1
    local -r newName=$2
    local newImportEscaped
    newImportEscaped=$(escapeRegex "$newName")
    if ! grep -q -E "^import $newImportEscaped\$" "$file"; then
        perlReplace "s/(package [^\n]+\n+)/\$1\nimport $newName/g" "$file"
    fi
}

function migrateFrom_1_2_to_1_3() {

    if (($# != 1)); then
        echo "you need to specify the file to migrate"
        exit 1
    fi
    local file=$1

    function perlReplace() {
        perlReplaceFile "$file" "$@"
    }
    function replaceOrAddImport() {
        replaceOrAddImportFile "$file" "$@"
    }
    function replaceImport() {
        replaceImportFile "$file" "$@"
    }
    function addImportIfNeeded() {
        addImportIfNeededFile "$file" "$@"
    }

    local -ra replaceImports=(
        ch.tutteli.atrium.logic._logicAppend ch.tutteli.atrium._coreAppend
        ch.tutteli.atrium.logic._logic ch.tutteli.atrium._core
        ch.tutteli.atrium.logic.creating.feature.MetaFeature ch.tutteli.atrium.creating.proofs.feature.MetaFeature
        ch.tutteli.atrium.logic.creating.FeatureExpectOptionsChooser ch.tutteli.atrium.creating.FeatureExpectOptionsChooser
        ch.tutteli.atrium.translations.ErrorMessages ch.tutteli.atrium.reporting.reportables.ErrorMessages

    )
    local -i replaceImportsLength="${#replaceImports[@]}"
    for ((i = 0; i < replaceImportsLength; i += 2)); do
        local oldImport="${replaceImports[i]}"
        local newImport="${replaceImports[i + 1]}"
        replaceImport "$oldImport" "$newImport" || true
    done

    local -a symbolRenamings=(
        expectLambda expectationCreator ch.tutteli.atrium.logic.utils.expectLambda ch.tutteli.atrium.creating.expectationCreator
        createDescriptiveAssertion buildSimpleProof ch.tutteli.atrium.logic.createDescriptiveAssertion ch.tutteli.atrium.creating.proofs.builders.buildSimpleProof

        CharSequenceContains.EntryPointStepLogic CharSequenceToContain.EntryPointStepCore ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.EntryPointStepLogic ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.EntryPointStepCore
        CharSequenceContains.EntryPointStepInternal CharSequenceToContain.EntryPointStepInternal ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.EntryPointStepInternal ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.EntryPointStepInternal
        CharSequenceContains.EntryPointStep CharSequenceToContain.EntryPointStep ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.EntryPointStep ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.EntryPointStep
        CharSequenceContains.CheckerStepLogic CharSequenceToContain.CheckerStepCore ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.CheckerStepLogic ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.CheckerStepCore
        CharSequenceContains.CheckerStepLInternal CharSequenceToContain.CheckerStepLInternal ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.CheckerStepInternal ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.CheckerStepInternal
        CharSequenceContains.CheckerStep CharSequenceToContain.CheckerStep ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.CheckerStep ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.CheckerStep
        CharSequenceContains.SearchBehaviour CharSequenceToContain.SearchBehaviour ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.SearchBehaviour ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.SearchBehaviour
        CharSequenceContains.Checker CharSequenceToContain.Checker ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.Checker ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.Checker
        CharSequenceContains.Creator CharSequenceToContain.Creator ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.Creator ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.Creator
        CharSequenceContains CharSequenceToContain ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

        WithTimesCheckerStepLogic WithTimesCheckerStepCore ch.tutteli.atrium.logic.creating.charsequence.contains.steps.WithTimesCheckerStepLogic ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.WithTimesCheckerStepCore
        WithTimesCheckerStep WithTimesCheckerStep ch.tutteli.atrium.logic.creating.charsequence.contains.steps.WithTimesCheckerStep ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.WithTimesCheckerStep

        Contains.EntryPointStepLogic ToContain.EntryPointStepCore ch.tutteli.atrium.logic.creating.basic.contains.Contains.EntryPointStepLogic ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.EntryPointStepCore
        Contains.EntryPointStepLInternal ToContain.EntryPointStepLInternal ch.tutteli.atrium.logic.creating.basic.contains.Contains.EntryPointStepInternal ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.EntryPointStepInternal
        Contains.EntryPointStep ToContain.EntryPointStep ch.tutteli.atrium.logic.creating.basic.contains.Contains.EntryPointStep ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.EntryPointStep
        Contains.CheckerStepLogic ToContain.CheckerStepCore ch.tutteli.atrium.logic.creating.basic.contains.Contains.CheckerStepLogic ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.CheckerStepCore
        Contains.CheckerStepLInternal ToContain.CheckerStepLInternal ch.tutteli.atrium.logic.creating.basic.contains.Contains.CheckerStepInternal ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.CheckerStepInternal
        Contains.CheckerStep ToContain.CheckerStep ch.tutteli.atrium.logic.creating.basic.contains.Contains.CheckerStep ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.CheckerStep
        Contains.SearchBehaviour ToContain.SearchBehaviour ch.tutteli.atrium.logic.creating.basic.contains.Contains.SearchBehaviour ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.SearchBehaviour
        Contains.Checker ToContain.Checker ch.tutteli.atrium.logic.creating.basic.contains.Contains.Checker ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.Checker
        Contains.Creator ToContain.Creator ch.tutteli.atrium.logic.creating.basic.contains.Contains.Creator ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.Creator
        Contains ToContain ch.tutteli.atrium.logic.creating.basic.contains.Contains ch.tutteli.atrium.creating.proofs.basic.contains.ToContain

        Translatable Description ch.tutteli.atrium.reporting.translating.Translatable ch.tutteli.atrium.reporting.reportables.Description
        toAssertionCreator toExpectationCreator ch.tutteli.atrium.logic.toAssertionCreator ch.tutteli.atrium.creating.toExpectationCreator
        toAssertionContainer toProofContainer ch.tutteli.atrium.logic.toAssertionContainer ch.tutteli.atrium.creating.toProofContainer
        AssertionContainer ProofContainer ch.tutteli.atrium.creating.AssertionContainer ch.tutteli.atrium.creating.ProofContainer
        AssertionGroup ProofGroup ch.tutteli.atrium.assertions.AssertionGroup ch.tutteli.atrium.creating.proofs.ProofGroup
        Assertion Proof ch.tutteli.atrium.assertions.Assertion ch.tutteli.atrium.creating.proofs.Proof


    )
    local -ra descriptionRenamings=(
        DescriptionAnyExpectation DescriptionAnyProof
        DescriptionAnyExpectation.BECAUSE DescriptionDocumentationUtil.BECAUSE

        DescriptionBasic DescriptionBasic
        DescriptionCharSequenceExpectation DescriptionCharSequenceProof
        DescriptionCollectionExpectation DescriptionCollectionProof
        DescriptionComparableExpectation DescriptionComparableProof
        DescriptionDateTimeLikeExpectation DescriptionDateTimeLikeProof
        DescriptionFloatingPointException DescriptionFloatingPointProof
        DescriptionFunLikeExpectation DescriptionFunLikeProof
        DescriptionIterableLikeExpectation DescriptionIterableLikeProof
        DescriptionListLikeExpectation DescriptionListLikeProof
        DescriptionMapLikeExpectation DescriptionMapLikeProof
        DescriptionResultExpectation DescriptionResultProof
        DescriptionThrowableExpectation DescriptionThrowableProof
        # TODO 1.3.0 add more?
    )
    local -i descriptionRenamingsLength="${#descriptionRenamings[@]}"
    for ((i = 0; i < descriptionRenamingsLength; i += 2)); do
        local oldName="${descriptionRenamings[i]}"
        local newName="${descriptionRenamings[i + 1]}"
        symbolRenamings+=("$oldName" "$newName" "ch.tutteli.atrium.translations.$oldName" "ch.tutteli.atrium.reporting.reportables.descriptions.$newName")
    done

    local -i symbolRenamingsLength="${#symbolRenamings[@]}"
    for ((i = 0; i < symbolRenamingsLength; i += 4)); do
        local oldName="${symbolRenamings[i]}"
        local newName="${symbolRenamings[i + 1]}"
        local oldQualified="${symbolRenamings[i + 2]}"
        local newQualified="${symbolRenamings[i + 3]}"

        local needsImport=false
        if perlReplace "s/\\Q$oldQualified\\E/$newQualified/g"; then
            needsImport=true
        fi
        if perlReplace "s/\\Q$oldName\\E/$newName/g"; then
            needsImport=true
        fi
        if [[ $needsImport == true ]]; then
            replaceOrAddImport "$oldQualified" "$newQualified"
        fi
    done

    local -ra packageChanges=(
        ch.tutteli.atrium.logic.creating.charsequence.contains.checkers ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers
        ch.tutteli.atrium.logic.creating.charsequence.contains.creators ch.tutteli.atrium.creating.proofs.charsequence.contains.creators
        ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours
        ch.tutteli.atrium.logic.creating.charsequence.contains.searchers ch.tutteli.atrium.creating.proofs.charsequence.contains.searchers
        ch.tutteli.atrium.logic.creating.charsequence.contains.steps ch.tutteli.atrium.creating.proofs.charsequence.contains.steps
        ch.tutteli.atrium.logic.creating.charsequence.contains ch.tutteli.atrium.creating.proofs.charsequence.contains
        ch.tutteli.atrium.logcic.creating.feature ch.tutteli.atrium.creating.proofs.feature
        ch.tutteli.atrium.logic.creating.transformers ch.tutteli.atrium.creating.transformers
        ch.tutteli.atrium.logic.creating.typeutils ch.tutteli.atrium.creating.typeutils
        ch.tutteli.atrium.logic.utils ch.tutteli.atrium.creating.utils
    )
    local -i packageChangesLength="${#packageChanges[@]}"
    for ((i = 0; i < packageChangesLength; i += 2)); do
        local old="${packageChanges[i]}"
        local new="${packageChanges[i + 1]}"
        perlReplace "s/\\Q$old\\E/$new/g" || true
        replaceImport "$oldImport.*" "$newImport.*" || true
    done
    replaceImport "ch.tutteli.atrium.logic.*" "ch.tutteli.atrium.creating.*" || true

    if perlReplace "s/_logicAppend/_coreAppend/g"; then
        replaceOrAddImport "ch.tutteli.atrium.logic._logicAppend" "ch.tutteli.atrium._coreAppend"
    fi
    if perlReplace 's/_logic(\.| )/_core$1/g'; then
        replaceOrAddImport "ch.tutteli.atrium.logic._logic" "ch.tutteli.atrium._core"
    fi

    # val definitions and references in KDoc
    perlReplace 's/_logic(:|\])/_core$1/g' || true
    perlReplace 's/entryPointStepLogic/entryPointStepCore/g' || true

    local -ra containerExtensionRenamings=(
        containsBuilder toContainBuilder
        containsNotBuilder notToContainBuilder
        startsWith toStartWith
        startsNotWith notToStartWith
        endsWith toEndWith
        endsNotWith notToEndWith
        isEmpty toBeEmpty
        isNotEmpty notToBeEmpty
        isNotBlank notToBeBlank
        matches toMatch
        mismatches notToMatch

        isLessThan toBeLessThan
        isLessThanOrEqual toBeLessThanOrEqualTo
        isNotGreaterThan notToBeGreaterThan
        isEqualComparingTo toBeEqualComparingTo
        isGreaterThanOrEqual toBeGreaterThanOrEqualTo
        isNotLessThan notToBeLessThan
        isGreaterThan toBeGreaterThan

        toBeNullIfNullGivenElse toEqualNullIfNullGivenElse
        notToBeNullButOfType notToEqualNullButToBeAnInstanceOf
        toBe toEqual
        notToBe notToEqual
        isSameAs toBeTheInstance
        isNotSameAs notToBeTheInstance
        isA toBeAnInstanceOf
        isNotIn notToEqualOneIn

        property property
        manualFeature manualFeature
        extractSubject extractSubject
        f0 f0
        f1 f1
        f2 f2
        f3 f3
        f4 f4
        f5 f5
    )
    local -i containerExtensionRenamingsLength="${#containerExtensionRenamings[@]}"
    for ((i = 0; i < containerExtensionRenamingsLength; i += 2)); do
        local funToReplace="${containerExtensionRenamings[i]}"
        local replacement="${containerExtensionRenamings[i + 1]}"

        local needsImport=false
        if perlReplace "s/_coreAppend \{ $funToReplace\(/_coreAppend \{ $replacement\(/g"; then
            needsImport=true
        fi
        if perlReplace "s/_core\.$funToReplace\(/_core.$replacement\(/g"; then
            needsImport=true
        fi
        if perlReplace "s/(container|assertionContainer)\.$funToReplace\(/\${1}.$replacement\(/g"; then
            needsImport=true
        fi
        if [[ $needsImport == true ]]; then
            replaceOrAddImport "ch.tutteli.atrium.logic.$funToReplace" "ch.tutteli.atrium.creating.proofs.$replacement"
        fi
    done

    local -ra replace_coreImports=(
        changeSubject
        toExpectGrouping
        collectForComposition
        collect
        extractFeature
    )
    for importToReplace in "${replace_coreImports[@]}"; do
        local needsImport=false
        if grep -q "_core.$importToReplace" "$file"; then
            needsImport=true
        elif grep -q "container.$importToReplace" "$file"; then
            needsImport=true
        elif grep -q "assertionContainer.$importToReplace" "$file"; then
            needsImport=true
        fi
        if [[ $needsImport == true ]]; then
            replaceOrAddImport "ch.tutteli.atrium.logic.$importToReplace" "ch.tutteli.atrium.creating.$importToReplace"
        fi
    done
    local -ra replaceSimpleImports=(
        creating.transformers.SubjectChangerBuilder
        creating.transformers.SubjectChanger
        creating.transformers.FeatureExtractorBuilder
        creating.transformers.FeatureExtractor
        creating.transformers.TransformationExecutionStep
    )
    for importToReplace in "${replaceSimpleImports[@]}"; do
        replaceImport "ch.tutteli.atrium.logic.$importToReplace" "ch.tutteli.atrium.$importToReplace" || true
    done

    local needsImport=false
    if perlReplace 's/(\s+)(apply \{)\s*(.*)_core\.appendAsGroup\(assertionCreator\).*\}/$1$2\n$1    $3_core.appendAsGroupIndicateIfOneCollected(\n$1        ExpectationCreatorWithUsageHints(\n$1            usageHintsAlternativeWithoutExpectationCreator = listOf(\/* TODO add a usage hint in case you have an overload which does not expect an expectationCreator *\/),\n$1            expectationCreator = assertionCreator\n$1        )\n$1    ).first\n$1\}/g'; then
        needsImport=true
    fi
    if perlReplace 's/(\s+)(_core|container|assertionContainer)\.appendAsGroup\(assertionCreator\)/$1$2.appendAsGroupIndicateIfOneCollected(\n$1    ExpectationCreatorWithUsageHints(\n$1        usageHintsAlternativeWithoutExpectationCreator = listOf(\/* TODO add a usage hint in case you have an overload which does not expect an expectationCreator *\/),\n$1        expectationCreator = assertionCreator\n$1    )\n$1).first/g'; then
        needsImport=true
    fi
    if perlReplace 's/(\s+)(.*)(transformAndAppend|collectAndAppend)\(assertionCreator\)/$1$2$3(\n$1    ExpectationCreatorWithUsageHints(\n$1        usageHintsAlternativeWithoutExpectationCreator = listOf(\/* TODO add a usage hint in case you have an overload which does not expect an expectationCreator *\/),\n$1        expectationCreator = assertionCreator\n$1    )\n$1)/g'; then
        needsImport=true
    fi
    if perlReplace 's/(\s+)(_core|container|assertionContainer).collect\(assertionCreator\)/$1$2collect(\n$1    ExpectationCreatorWithUsageHints(\n$1        usageHintsAlternativeWithoutExpectationCreator = listOf(\/* TODO add a usage hint in case you have an overload which does not expect an expectationCreator *\/),\n$1        expectationCreator = assertionCreator\n$1    )\n$1)/g'; then
        needsImport=true
    fi
    if [[ $needsImport == true ]]; then
        addImportIfNeeded "ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints"
    fi

    if perlReplace 's/assertionBuilder.createDescriptive/Proof.simple/g'; then
        addImportIfNeeded "ch.tutteli.atrium.creating.proofs.Proof"
    fi

    echo "migrated successfully"
}

migrateFrom_1_2_to_1_3 "$@"
