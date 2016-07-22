package com.lenovo.compass.compass.utils;

import com.ksy.recordlib.service.core.KSYStreamerConfig;
import com.ksy.recordlib.service.streamer.RecorderConstants;

/**
 * Created by hansentian on 5/25/16.
 */
public class StreamerUtil {
    public static KSYStreamerConfig createBuilder(String url) {
        KSYStreamerConfig.Builder builder = new KSYStreamerConfig.Builder();
        builder.setFrameRate(15);
        builder.setMaxAverageVideoBitrate(700);
        builder.setMinAverageVideoBitrate(500);
        builder.setInitAverageVideoBitrate(300);
        builder.setAudioBitrate(48);
        builder.setVideoResolution(RecorderConstants.VIDEO_RESOLUTION_480P);
        builder.setEncodeMethod(KSYStreamerConfig.ENCODE_METHOD.SOFTWARE);
        builder.setSampleAudioRateInHz(44100);
        builder.setIFrameIntervalSec(2.0f);
        builder.setEnableStreamStatModule(true);
        builder.setDefaultLandscape(false);
        builder.setFrontCameraMirror(true);
        builder.setManualFocus(false);
        return builder.build();
    }


}
