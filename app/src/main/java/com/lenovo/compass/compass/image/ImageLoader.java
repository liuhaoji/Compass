package com.lenovo.compass.compass.image;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.animated.base.AbstractAnimatedDrawable;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.lenovo.compass.compass.CompassApplication;

import java.lang.reflect.Field;

/**
 * Created by hansentian on 5/18/16.
 */
public class ImageLoader {

    public interface ImageDurationGetListener {
        void animationDurationGet(int duration);
    }

    public static void init(Context context) {
        ImagePipelineConfig config = ImagePipelineConfigFactory.getImagePipelineConfig(context);
        Fresco.initialize(context, config);
    }

    public static void loadImage(DraweeView imageEx, String uri) {
        if (imageEx == null) {
            return;
        }
        if (uri == null) {
            return;
        }
        ImageRequest imageRequest =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                        .build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(imageEx.getController())
                .setAutoPlayAnimations(true)
                .build();
        imageEx.setController(draweeController);
    }

    public static void loadGifImage(DraweeView imageEx, String uri, final ImageDurationGetListener listener) {
        if (imageEx == null) {
            return;
        }
        if (uri == null) {
            return;
        }
        ImageRequest imageRequest =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                        .build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(imageEx.getController())
                .setAutoPlayAnimations(false)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        int duration = 0;
                        if (animatable != null) {
                            try {
                                Field field = AbstractAnimatedDrawable.class.getDeclaredField("mTotalLoops");
                                field.setAccessible(true);
                                field.set(animatable, 1);
                                field = AbstractAnimatedDrawable.class.getDeclaredField("mDurationMs");
                                field.setAccessible(true);
                                duration = field.getInt(animatable);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            listener.animationDurationGet(duration);
                            animatable.start();
                        }
                    }
                })
                .build();
        draweeController.getAnimatable();
        imageEx.setController(draweeController);
    }

    public static void loadImageWithHolder(DraweeView imageEx, String uri, Drawable placeHoleder, Drawable failureHolder) {
        loadImageWithHolder(imageEx, uri, placeHoleder, failureHolder, false);
    }

    public static void loadImageWithHolder(DraweeView imageEx, String uri, Drawable placeHoleder, Drawable failureHolder, boolean circleRounder) {
        if (imageEx == null) {
            return;
        }
        if (uri == null) {
            return;
        }
        GenericDraweeHierarchyBuilder builder = GenericDraweeHierarchyBuilder.newInstance(CompassApplication.getInstance().getResources());
        //设置圆形圆角参数；RoundingParams.asCircle()是将图像设置成圆形
        //设置淡入淡出动画持续时间(单位：毫秒ms)
        //构建
        if (placeHoleder != null) {
            builder.setPlaceholderImage(placeHoleder, ScalingUtils.ScaleType.CENTER_CROP);
        }
        if (failureHolder != null) {
            builder.setFailureImage(failureHolder, ScalingUtils.ScaleType.CENTER_CROP);
        }
        if (circleRounder) {
            builder.setRoundingParams(RoundingParams.asCircle());
        }
        imageEx.setHierarchy(builder.build());
        ImageRequest imageRequest =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                        .setResizeOptions(
                                new ResizeOptions(imageEx.getLayoutParams().width, imageEx.getLayoutParams().height))
                        .build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(imageEx.getController())
                .setAutoPlayAnimations(true)
                .build();
        imageEx.setController(draweeController);

    }

    public static void loadImageAsCircle(DraweeView imageEx, String uri, Context context) {
        if (imageEx == null) {
            return;
        }
        //获取GenericDraweeHierarchy对象
        GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(context.getResources())
                //设置圆形圆角参数；RoundingParams.asCircle()是将图像设置成圆形
                .setRoundingParams(RoundingParams.asCircle())
                        //设置淡入淡出动画持续时间(单位：毫秒ms)
                        //构建
                .build();
        imageEx.setHierarchy(hierarchy);
        DraweeController controller = (DraweeController) Fresco.newDraweeControllerBuilder()
                //设置需要下载的图片地址
                .setUri(uri)
                        //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                        //构建
                .build();
        //设置Controller
        imageEx.setController(controller);
    }


    public static void resetHierarchy(DraweeView ime, Drawable placeHoleder, Drawable failureHolder) {
        if (ime == null) {
            return;
        }
        GenericDraweeHierarchy gdh = new GenericDraweeHierarchyBuilder(CompassApplication.getInstance().getResources())
                .setPlaceholderImage(placeHoleder, ScalingUtils.ScaleType.CENTER_CROP)
                .setFailureImage(failureHolder, ScalingUtils.ScaleType.CENTER_CROP)
                .build();
        ime.setHierarchy(gdh);
    }

    public static void resetHierarchy(DraweeView ime, Drawable placeHoleder) {
        if (ime == null) {
            return;
        }
        GenericDraweeHierarchy gdh = new GenericDraweeHierarchyBuilder(CompassApplication.getInstance().getResources())
                .setPlaceholderImage(placeHoleder, ScalingUtils.ScaleType.CENTER_CROP)
                .setFailureImage(placeHoleder, ScalingUtils.ScaleType.CENTER_CROP)
                .build();
        ime.setHierarchy(gdh);
    }

    public static void display(DraweeView imageEx, String uri, Drawable placeHoleder, Drawable failureHolder,float radius) {
        if (imageEx == null) {
            return;
        }
        if (uri == null) {
            return;
        }
        GenericDraweeHierarchyBuilder builder = GenericDraweeHierarchyBuilder.newInstance(CompassApplication.getInstance().getResources());
        //设置圆形圆角参数；RoundingParams.asCircle()是将图像设置成圆形
        //设置淡入淡出动画持续时间(单位：毫秒ms)
        //构建
        if (placeHoleder != null) {
            builder.setPlaceholderImage(placeHoleder, ScalingUtils.ScaleType.CENTER_CROP);
        }
        if (failureHolder != null) {
            builder.setFailureImage(failureHolder, ScalingUtils.ScaleType.CENTER_CROP);
        }
            builder.setRoundingParams(RoundingParams.fromCornersRadius(radius));

        imageEx.setHierarchy(builder.build());
        ImageRequest imageRequest =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                        .setResizeOptions(
                                new ResizeOptions(imageEx.getLayoutParams().width, imageEx.getLayoutParams().height))
                        .build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(imageEx.getController())
                .setAutoPlayAnimations(true)
                .build();
        imageEx.setController(draweeController);

    }

}
