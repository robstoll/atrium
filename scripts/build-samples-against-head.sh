#!/usr/bin/env bash
set -euo pipefail
shopt -s inherit_errexit || { echo >&2 "please update to bash 5, see errors above" && exit 1; }
unset CDPATH

# Builds all samples against the current (snapshot) version of atrium. This replaces the former
# composite build misc/tools/atrium-samples-test, see https://github.com/robstoll/atrium/issues/2224
#
# 1. publishes atrium to maven local (signing turned off, see scripts/no-signing.init.gradle.kts)
# 2. sets the current atrium version in each sample and builds it, where the samples resolve atrium
#    from maven local (see scripts/maven-local.init.gradle.kts)
# 3. reverts all changes made to the samples before it exits

script_dir="$(cd "$(dirname "$0")" && pwd)"

cd "$script_dir/.."

trap 'git reset --hard -q' EXIT

PUB=true ./gradlew publishToMavenLocal -I "$script_dir/no-signing.init.gradle.kts"

version=$(sed -n 's/^[[:space:]]*rootProject\.version = "\(.*\)"$/\1/p' build.gradle.kts)
echo "building samples against atrium $version"

for sample in samples/*/*/; do
  [ -f "${sample}build.gradle.kts" ] || continue
  sed -i.bak \
    -e "s/val atriumVersion = \"[^\"]*\"/val atriumVersion = \"$version\"/" \
    -e 's/\(ch\.tutteli\.atrium:[a-zA-Z-]*:\)[0-9][0-9A-Za-z.-]*/\1'"$version"'/g' \
    "${sample}build.gradle.kts"
  rm "${sample}build.gradle.kts.bak"
  echo "building ${sample}"
  ./gradlew -p "$sample" -I "$script_dir/maven-local.init.gradle.kts" build
done

# the maven sample resolves atrium from maven local as well (its pom pins the version via atrium.version)
maven_sample="samples/jvm/maven"
echo "building ${maven_sample}"
sed -i.bak \
  -e "s/<atrium\.version>[^<]*<\/atrium\.version>/<atrium.version>$version<\/atrium.version>/" \
  "${maven_sample}/pom.xml"
rm "${maven_sample}/pom.xml.bak"
(cd "$maven_sample" && ./mvnw -B test)
