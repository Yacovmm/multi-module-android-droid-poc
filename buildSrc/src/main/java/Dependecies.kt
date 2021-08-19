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
    val androidx_core = "1.6.0"
    val androidx_recyclerview = "1.0.0"
    val androidx_constraintLayout = "1.1.3"
    val material = "1.1.0-alpha04"
    // </editor-fold>

    // <editor-fold desc="testing">
    val junit = "4.12"
    val androidx_espresso = "3.1.0"
    val androidx_testing = "1.1.1"
    // </editor-fold>

    // <editor-fold desc="tools">
    val gradleandroid = "7.0.0"
    val kotlin = "1.5.21"
    val gradleversions = "0.27.0"
    // </editor-fold>

    val frag_nav_version = "3.3.0"

    val retrofit_version = "2.9.0"

    val gson_version = "2.9.0"
    val okhttp_version = "5.0.0-alpha.1"
}

object Deps {
    val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"
    val androidx_material = "com.google.android.material:material:${Versions.material}"
    val androidx_recyclerview = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"

    val kotlin_serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"

    val testlib_junit = "junit:junit:${Versions.junit}"

    val testandroidx_rules = "androidx.test:rules:${Versions.androidx_testing}"
    val testandroidx_runner = "androidx.test:runner:${Versions.androidx_testing}"
    val testandroidx_espressocore = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"

    val tools_gradleandroid = "com.android.tools.build:gradle:${Versions.gradleandroid}"
    val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val tools_remal_deps_check = "name.remal:gradle-plugins:1.4.1"

    val frag_nav = "com.ncapdevi:frag-nav:${Versions.frag_nav_version}"

    // Retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"

    // GsonConverter
    val gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.gson_version}"
//    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    val serialization_dep = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2"
    val serializationx_converter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"

    // OKHttp
    val ok_http = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
    val ok_http_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"

    // Collection KTX
    val collection_ktx = "androidx.collection:collection-ktx:1.1.0"

    // Fragment KTX
    val fragment_ktx = "androidx.fragment:fragment-ktx:1.3.3"

    // Lifecycle KTX
    val lifecycle_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"

    // LiveData KTX
    val liveData_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"

    // ViewModel KTX
    val viewModel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
}

object CoroutinesDeps {
    val coroutineVersion = "1.5.1"
    val coroutine_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
    val coroutine_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
    val legacy_support = "androidx.legacy:legacy-support-v4:1.0.0"
}

object HiltDeps {
    private const val hiltVersion = "2.38.1"
    val apply_plugin = "dagger.hilt.android.plugin"
    val hilt_android_plugin_dep = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    val hilt_impl_dep = "com.google.dagger:hilt-android:$hiltVersion"
    val hilt_kapt_compiler = "com.google.dagger:hilt-compiler:$hiltVersion"

    val hilt_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"

    // For instrumentation/unit tests
    val hilt_test = "com.google.dagger:hilt-android-testing:$hiltVersion"
    val hilt_kapt_test = "com.google.dagger:hilt-compiler:$hiltVersion"
}
