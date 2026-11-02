rootProject.name = "samples-composite"

includeBuild("../../../")
includeBuild("../../../samples/jvm/junit5")
includeBuild("../../../samples/jvm/kotest")
includeBuild("../../../samples/jvm/testng")
includeBuild("../../../samples/multiplatform/kotlin-test") {
    name = "multiplatform-kotlin-test"
}
includeBuild("../../../samples/js/kotlin-test") {
    name = "js-kotlin-test"
}
includeBuild("../../../samples/js/kotest") {
    name = "js-kotest"
}

pluginManagement {
    repositories {
//        mavenLocal()
        gradlePluginPortal()
    }
    includeBuild("../../../gradle/build-logic")
    includeBuild("../../../gradle/build-logic-conventions")
}
