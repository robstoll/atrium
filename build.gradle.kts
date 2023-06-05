import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.*

plugins {
    id("build-logic.root-build")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

version = "1.1.0-SNAPSHOT"
group = "ch.tutteli.atrium"

// main
val kboxVersion by extra("0.16.0")
val niokVersion by extra("1.4.7")

// test
val jacocoToolVersion by extra("0.8.9")
val junitPlatformVersion by extra("1.9.2")
val jupiterVersion by extra("5.9.2")
// need to use an old version of spek as the newer contains a bug which causes that no tests are discovered and executed
val spekVersion by extra("2.0.12")
//TODO v1.1.0 decide whether we drop kotest again or not
//val kotestVersion by extra("4.6.4")
val spekExtensionsVersion by extra("1.2.1")
val mockkVersion by extra("1.10.0")
val mockitoKotlinVersion by extra("2.2.0")

// project setup
val d8Version by extra("1.6.84")

val multiplatformProjectNames by extra(
    setOf(
        "core",
        "logic", "logic-kotlin_1_3",
        "translations-en_GB", "translations-de_CH",
        "api-fluent", "api-fluent-kotlin_1_3",
        "api-infix", "api-infix-kotlin_1_3",
        "fluent",
        "infix",
        "verbs",
        "verbs-internal",
        "specs"
    )
)
val multiplatformProjects by extra(
    multiplatformProjectNames.asSequence().map { prefixedProject(it) }.toSet()
)
val toolProjects by extra(subprojects.asSequence().filter {
    it.projectDir.path.contains("/misc/tools/") || it.projectDir.path.contains("\\misc\\tools\\")
}.toSet())

val bundleSmokeTests = subprojects.asSequence().filter {
    it.name.contains("-smoke-test")
}.toSet()

val subprojectsWithoutToolProjects = subprojects - toolProjects
val subprojectsWithoutToolAndSmokeTestProjects = subprojectsWithoutToolProjects - bundleSmokeTests

subprojects {
    group = rootProject.group
    version = rootProject.version
}

//TODO 1.1.0 re-introduce bcTests again? I am currently not sure if it is actually worth it
// did not have the need to be binary compatible for a while
//val apiProjects = subprojects.filter {
//    it.name.startsWith("${rootProject.name}-api")
//}
//configure(apiProjects) {
//    createTestJarTask(apiProject)
//    createTestSourcesJarTask(apiProject)
//}

fun AbstractDokkaTask.configurePlugins() {
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage = "Atrium &copy; Copyright Robert Stoll &lt;rstoll@tutteli.ch&gt;"
    }
}

tasks.withType<DokkaMultiModuleTask> {
    moduleName.set("Atrium")
    configurePlugins()

    // we want to be sure that we don't include spec in dokkaMultiModule
    // dependsOn(prefixedProject("specs").tasks.getByName("cleanDokkaHtmlPartial"))
    // dependsOn(prefixedProject("verbs-internal").tasks.getByName("cleanDokkaHtmlPartial"))
}

gradle.taskGraph.whenReady {
    if (hasTask(":dokkaHtmlMultiModule")) {
        listOf("specs", "verbs-internal").forEach { projectName ->
            prefixedProject(projectName).tasks.withType<DokkaTaskPartial> {
                enabled = false
            }
        }
    }
}

// takes some time to configure since gradle 6.9 so only if CI
// TODO: rework into build-logic/...
//if (java.lang.Boolean.parseBoolean(System.getenv("CI"))) {
//    apply(from = "gradle/scripts/check-dexer.gradle")
//}


// TODO 1.5.0 reactivate and transform to Kotlin as soon as we tackle the scala API again
//def getSubprojectTasks(String name) {
//    return subprojects.collect { it.tasks.findByName(name) }.findAll { it != null }
//}
//
//task publishForScala(description: "fast publish to maven local for scala projects") {
//    dependsOn getSubprojectTasks("publishToMavenLocal")
//}
//
//gradle.taskGraph.whenReady { graph ->
//    if (graph.hasTask(":publishForScala")) {
//        ["test", "dokka", "signTutteliPublication", "validateBeforePublish", "javadocJar", "sourcesJar"].forEach {
//            getSubprojectTasks(it).forEach { it.enabled = false }
//        }
//    }
//}

nexusPublishing {
    repositories {
        sonatype()
    }
}

/*
Release & deploy a commit
--------------------------------

1. update main:

Either use the following commands or the manual steps below

export ATRIUM_PREVIOUS_VERSION=1.0.0
export ATRIUM_VERSION=1.1.0
find ./ -name "*.md" | xargs perl -0777 -i \
   -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
   -pe "s@tree/main@tree/v$ATRIUM_VERSION@g;" \
   -pe "s@latest#/doc@$ATRIUM_VERSION/doc@g;"
perl -0777 -i \
  -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
  -pe "s/rootProject.version = \"${ATRIUM_VERSION}-SNAPSHOT\"/rootProject.version = \"$ATRIUM_VERSION\"/;" \
  ./build.gradle.kts
perl -0777 -i \
  -pe 's/(<!-- for main -->\n)\n([\S\s]*?)(\n<!-- for a specific release -->\n)<!--\n([\S\s]*?)-->\n(\n# <img)/$1<!--\n$2-->$3\n$4\n$5/;' \
  -pe 's/(---\n❗ You are taking[^-]*?---)/<!$1>/;' \
  ./README.md
git commit -a -m "v$ATRIUM_VERSION"

check changes (CONTRIBUTING.md, difference.md, build.gradle.kts, README.md)
git push

alternatively the manual steps:

    a) change rootProject.version in build.gradle.kts to X.Y.Z
    b) search for old version in README.md and replace with new
    c) search for `tree/main` in all .md files and replace it with `tree/vX.Y.Z`
    d) search for `latest#/doc` in all .md files and replace with `X.Y.Z/doc`
    e) use the release badges in README (comment out the ones for main and uncomment the ones for the release)
    f) comment out the warning in README.md about taking a sneak peak
    g) commit & push (modified CONTRIBUTING.md, differences.md, build.gradle and README.md)

2. prepare release on github
    a) git tag "v$ATRIUM_VERSION"
    b) git push origin "v$ATRIUM_VERSION"
    c) Log in to github and create draft for the release

The tag is required for dokka in order that the externalLinkDocumentation and source-mapping works

3. update github pages:
Assumes you have a atrium-gh-pages folder on the same level as atrium where the gh-pages branch is checked out

Either use the following commands or the manual steps below (assuming ATRIUM_VERSION is already set from commands above)

export ATRIUM_GH_PAGES_LOGO_CSS_VERSION="1.2"
export ATRIUM_GH_PAGES_ALERT_CSS_VERSION="1.1"
export ATRIUM_GH_PAGES_VERSIONS_JS_VERSION="1.1.3"
export ATRIUM_GH_PAGES_VERSIONS_JS_VERSION_NEXT="1.2.0"

gr dokkaHtmlMultiModule
git add . && git commit -m "dokka generation for v$ATRIUM_VERSION"

cd ../atrium-gh-pages
perl -0777 -i \
  -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
  ./index.html
perl -0777 -i \
  -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
  ./latest/index.html
perl -0777 -i \
  -pe "s/(\s+)\"$ATRIUM_PREVIOUS_VERSION\",/\$1\"$ATRIUM_VERSION\",\$1\"$ATRIUM_PREVIOUS_VERSION\",/;" \
  ./scripts/versions.js
perl -0777 -i \
  -pe "s@(<div class=\"sideMenu\">)@\${1}\n <div class=\"sideMenuPart\" pageid=\"Atrium::.ext/allModules///PointingToDeclaration//0\"><div class=\"overview\"><a href=\"./\">All modules</a></div></div>@g;" \
  "./$ATRIUM_VERSION/kdoc/navigation.html"

find "./$ATRIUM_VERSION" -name "*.html" | xargs perl -0777 -i \
    -pe "s@\"((?:\.\./+)*)styles/logo-styles.css\" rel=\"Stylesheet\">@\"../../\${1}styles/logo-styles.css?v=$ATRIUM_GH_PAGES_LOGO_CSS_VERSION\" rel=\"Stylesheet\">\n<link href=\"../../\${1}styles/alert.css?v=$ATRIUM_GH_PAGES_ALERT_CSS_VERSION\" rel=\"Stylesheet\">\n<script id=\"versions-script\" type=\"text/javascript\" src=\"\../../\${1}scripts/versions.js?v=$ATRIUM_GH_PAGES_VERSIONS_JS_VERSION\" data-version=\"$ATRIUM_VERSION\" async=\"async\"></script>@g;" \
    -pe "s@((?:\.\./+)*)images/logo-icon.svg\"([^>]+)>@../../\${1}logo-icon.svg\"\$2>\n<meta name=\"og:image\" content=\"\${1}logo_social.png\"/>@g;" \
    -pe "s@(<div class=\"library-name\">[\s\n\r]+<a href=\"(?:\.\./+)*)index.html\">@\$1../../index.html\">@g;" \
    -pe "s@<html>@<html lang=\"en\">@g;" \
    -pe "s@<head>@<meta name=\"keywords\" content=\"Atrium, Kotlin, Expectation-library, Assertion-Library, Test, Testing, Multiplatform, better error reports, Code Documentation\">\n<meta name=\"author\" content=\"Robert Stoll\">\n<meta name=\"copyright\" content=\"Robert Stoll\">@g;" \
    -pe "s@<title>([^<]+)</title>@<title>\$1 - Atrium $ATRIUM_VERSION</title>\n<meta name=\"description\" content=\"Code documentation of Atrium $ATRIUM_VERSION: \$1\">@g;" \
    -pe "s@(<div class=\"library-name\">\n\s*<a href=\"[^\"]+)index.html\"@\${1}\" title=\"Back to Overview Code Documentation of Atrium\"@g;"

find "./" -name "*.html" | xargs perl -0777 -i \
    -pe "s@(scripts/versions\.js\?v\=)$ATRIUM_GH_PAGES_VERSIONS_JS_VERSION@\${1}$ATRIUM_GH_PAGES_VERSIONS_JS_VERSION_NEXT@g;"

cp "./$ATRIUM_PREVIOUS_VERSION/index.html" "./$ATRIUM_VERSION/index.html"
perl -0777 -i \
  -pe "s/$ATRIUM_PREVIOUS_VERSION/$ATRIUM_VERSION/g;" \
  -pe "s@Released .*</p>@Released $(date '+%b %d, %Y')</p>@;" \
  "./$ATRIUM_VERSION/index.html"
git add . && git commit -m "v$ATRIUM_VERSION"

check changes
git push

cd ../atrium

alternatively the manual steps:
    a) gr gr dokkaHtmlMultiModule
    b) change version number in atrium-gh-pages/index.html and atrium-gh-pages/latest/index.html
    c) add new version to atrium-gh-pages/scripts/versions.js
    d) replace logo-styles.css with own in root
    d) search and replace to add version drop down into the header
    e) commit & push changes

3. deploy to maven central:
(assumes you have an alias named gr pointing to ./gradlew)
    a) java -version 2>&1 | grep "version \"11" && CI=true gr clean publishToSonatype
    b) Log into https://oss.sonatype.org/#stagingRepositories
    c) check if staging repo is ok
    d) close repo
    e) release repo

4. publish release on github
    1) Log in to github and publish draft

Prepare next dev cycle
-----------------------
    1. update main:

Either use the following commands or the manual steps below

export ATRIUM_VERSION=1.0.0
export ATRIUM_NEXT_VERSION=1.1.0
find ./ -name "*.md" | xargs perl -0777 -i \
   -pe "s@tree/v$ATRIUM_VERSION@tree/main@g;" \
   -pe "s@$ATRIUM_VERSION/doc@latest#/doc@g;" \
   -pe "s/add \\\`\@since $ATRIUM_VERSION\\\` \(adapt to current/add \\\`\@since $ATRIUM_NEXT_VERSION\\\` \(adapt to current/g;"
perl -0777 -i \
  -pe "s/rootProject.version = \"$ATRIUM_VERSION\"/rootProject.version = \"${ATRIUM_NEXT_VERSION}-SNAPSHOT\"/;" \
  -pe "s/ATRIUM_VERSION=$ATRIUM_VERSION/ATRIUM_VERSION=$ATRIUM_NEXT_VERSION/;" \
  ./build.gradle.kts
perl -0777 -i \
  -pe 's/(<!-- for main -->\n)<!--\n([\S\s]*?)-->(\n<!-- for a specific release -->)\n([\S\s]*?)\n(\n# <img)/$1\n$2$3\n<!--$4-->\n$5/;' \
  -pe 's/<!(---\n❗ You are taking[^-]*?---)>/$1/;' \
  -pe "s@(latest version: \[README of v$ATRIUM_VERSION\].*tree/)main/@\$1v$ATRIUM_VERSION/@;" \
  ./README.md
git commit -a -m "prepare dev cycle of $ATRIUM_NEXT_VERSION"

check changes
git push

alternatively the manual steps:

    a) search for `tree/vX.Y.Z` in all .md and build.gradle files and replace it with `tree/v0.12.0`
b) search for `X.Y.Z/doc` in all .md files and replace with `latest#/doc`
   c) use the main badges in README (uncomment them in README and comment out release badges)
   d) uncomment the warning about taking a sneak peek in README and revert `tree/v0.12.0` still point to the tag
e) change rootProject.version in build.gradle to X.Y.Z-SNAPSHOT
f) commit & push changes

2. establish backward compatibility tests for the previous version
a) append new version to bcConfigs in settings.gradle.kts
b) git commit -a -m "establish backward compatibility tests for v$ATRIUM_VERSION"
c) commit & push changes

3. update samples (optional, since dependabot will create pull requests)
a) use newly released version in samples (search again for the old-version and replace with new)
b) commit & push changes

*/
