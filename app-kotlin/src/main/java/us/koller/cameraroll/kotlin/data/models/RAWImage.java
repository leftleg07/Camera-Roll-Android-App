package us.koller.cameraroll.kotlin.data.models;

import android.content.Context;
import android.os.Parcel;

import us.koller.cameraroll.kotlin.R;
import us.koller.cameraroll.kotlin.util.Util;

public class RAWImage extends Photo {

    RAWImage() {

    }

    RAWImage(Parcel parcel) {
        super(parcel);
    }

    @Override
    public int[] retrieveImageDimens(Context context) {
        return Util.getImageDimensions(context, getUri(context));
    }

    @Override
    public String getType(Context context) {
        return context.getString(R.string.raw_image);
    }
}
