plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("kotlin-android")
}

android {
    namespace = "com.example.model"
}

dependencies {

    //Retrofit
    implementation(Retrofit.converter_gson)
    //Kotlin
    implementation(Kotlin.core)
    //Design
    implementation(Design.appcompat)
    implementation(Design.material)
    //Test
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.junit_ext)
    androidTestImplementation(TestImpl.espresso)
}