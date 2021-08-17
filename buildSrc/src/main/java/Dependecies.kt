import org.gradle.api.JavaVersion

object Config {
    val minSdk = 21
    val compileSdk = 30
    val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_1_8
    val buildTools = "30.0.2"
}

object Versions {
    // <editor-fold desc="google">
    val androidx_core = "1.0.1"
    val androidx_recyclerview = "1.0.0"
    val androidx_navigation = "2.0.0"
    val androidx_constraintLayout = "1.1.3"
    val material = "1.1.0-alpha04"
    // </editor-fold>

    // <editor-fold desc="testing">
    val junit = "4.12"
    val androidx_espresso = "3.1.0"
    val androidx_testing = "1.1.1"
    //</editor-fold>

    // <editor-fold desc="tools">
    val gradleandroid = "7.0.0"
    val kotlin = "1.5.21"
    val gradleversions = "0.27.0"
    // </editor-fold>

    val frag_nav_version = "3.2.0"

}

object Deps {
    val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"
    val androidx_material = "com.google.android.material:material:${Versions.material}"
    val androidx_navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
    val androidx_navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"
    val androidx_recyclerview = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"

    val testlib_junit = "junit:junit:${Versions.junit}"

    val testandroidx_rules = "androidx.test:rules:${Versions.androidx_testing}"
    val testandroidx_runner = "androidx.test:runner:${Versions.androidx_testing}"
    val testandroidx_espressocore = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"

    val tools_gradleandroid = "com.android.tools.build:gradle:${Versions.gradleandroid}"
    val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val tools_remal_deps_check = "name.remal:gradle-plugins:1.4.1"

    val frag_nav = "com.ncapdevi:frag-nav:${Versions.frag_nav_version}"

}


object HiltDeps {
    private const val hiltVersion = "2.38.1"
    val apply_plugin = "dagger.hilt.android.plugin"
    val hilt_android_plugin_dep = "com.google.dagger:hilt-android-gradle-plugin:${hiltVersion}"
    val hilt_impl_dep = "com.google.dagger:hilt-android:${hiltVersion}"
    val hilt_kapt_compiler = "com.google.dagger:hilt-compiler:${hiltVersion}"
    // For instrumentation/unit tests
    val hilt_test = "com.google.dagger:hilt-android-testing:${hiltVersion}"
    val hilt_kapt_test = "com.google.dagger:hilt-compiler:${hiltVersion}"
}

