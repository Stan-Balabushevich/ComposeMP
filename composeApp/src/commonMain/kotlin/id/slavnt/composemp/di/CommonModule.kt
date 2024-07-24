package id.slavnt.composemp.di

import id.slavnt.composemp.framework.video.VideoPlayer
import id.slavnt.composemp.framework.video.createVideoPlayer
import org.koin.dsl.module

val commonModule = module {
    single<VideoPlayer> { createVideoPlayer() }
}
