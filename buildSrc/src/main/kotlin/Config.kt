object Config {
    object Version {
        const val code = 1
        const val name = "v1.0.0"
    }

    private const val kotlinVersion = "1.3.21"

    object SdkVersions {
        const val compile = 28
        const val target = 28
        const val min = 19
    }

    object Plugins {
        const val android = "com.android.tools.build:gradle:3.4.1"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object Libs {
        object Kotlin {
            const val jvm = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
        }

        object AndroidX {
            private const val version = "1.0.0"
            const val appcompat = "androidx.appcompat:appcompat:$version"
            const val core = "androidx.core:core-ktx:$version"
            const val fragment = "androidx.fragment:fragment-ktx:$version"
            const val cardView = "androidx.cardview:cardview:$version"
            const val palette = "androidx.palette:palette:$version"
            const val exifInterface = "androidx.exifinterface:exifinterface:$version"
            const val preference = "androidx.legacy:legacy-preference-v14:$version"
            const val annotation = "androidx.annotation:annotation:$version"
        }

        object Material {
            const val material = "com.google.android.material:material:1.1.0-alpha06"
        }

        object Misc {
            private const val glideVersion = "4.9.0"
            private const val scaleImageViewVersion = "3.10.0"
            private const val photoViewVersion = "2.3.0"
            private const val dragSelectRecyclerViewVersion = "0.3"
            private const val exoPlayerVersion = "2.10.0"
            private const val pageIndicatorViewVersion = "1.0.3"
            private const val recyclerFastScrollVersion = "0.3.2"
            
            const val glide = "com.github.bumptech.glide:glide:$glideVersion"
            const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"

            const val  scaleImageView = "com.davemorrissey.labs:subsampling-scale-image-view:$scaleImageViewVersion"

            const val  photoView = "com.github.chrisbanes:PhotoView:$photoViewVersion"

            const val  dragSelectRecyclerView = "com.github.MFlisar:DragSelectRecyclerView:$dragSelectRecyclerViewVersion"

            const val  exoPlayer = "com.google.android.exoplayer:exoplayer:$exoPlayerVersion"

            const val  pageIndicatorView = "com.romandanylyk:pageindicatorview:$pageIndicatorViewVersion"

            const val  recyclerFastScroll = "com.github.pluscubed:recycler-fast-scroll:$recyclerFastScrollVersion@aar"
            
        }

        object Arch {
            private const val version = "1.1.1"

            const val runtime = "android.arch.lifecycle:runtime:$version"
            const val viewModel = "android.arch.lifecycle:viewmodel:$version"
            const val extensions = "android.arch.lifecycle:extensions:$version"
            const val compiler = "android.arch.lifecycle:compiler:$version"

            const val paging = "android.arch.paging:runtime:1.0.1"
        }

        object Firebase {
            const val core = "com.google.firebase:firebase-core:16.0.6"
            const val auth = "com.google.firebase:firebase-auth:16.1.0"
            const val firestore = "com.google.firebase:firebase-firestore:17.1.4"
            const val database = "com.google.firebase:firebase-database:16.0.5"
            const val storage = "com.google.firebase:firebase-storage:16.0.5"
        }

        object PlayServices {
            const val auth = "com.google.android.gms:play-services-auth:16.0.1"
        }


        object Provider {
            const val facebook = "com.facebook.android:facebook-login:4.38.1"
            // WARNING: the Twitter requires Java 8 support. Therefore, the dep cannot be upgraded
            // futher until we decide to force clients to enable Java 8 support.
            const val twitter = "com.twitter.sdk.android:twitter-core:3.1.1@aar"
        }



        object Test {
            const val junit = "junit:junit:4.12"
            const val truth = "com.google.truth:truth:0.42"
            const val mockito = "org.mockito:mockito-android:2.21.0"
            const val robolectric = "org.robolectric:robolectric:4.0.2"

            const val runner = "com.android.support.test:runner:1.0.2"
            const val rules = "com.android.support.test:rules:1.0.2"
        }

        object Lint {
            private const val version = "26.2.1"

            const val api = "com.android.tools.lint:lint-api:$version"
            const val tests = "com.android.tools.lint:lint-tests:$version"
        }
    }
}
