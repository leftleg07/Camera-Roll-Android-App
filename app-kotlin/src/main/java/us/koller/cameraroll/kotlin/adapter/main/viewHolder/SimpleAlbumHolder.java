package us.koller.cameraroll.kotlin.adapter.main.viewHolder;

import android.view.View;
import android.widget.ImageView;

import us.koller.cameraroll.kotlin.R;
import us.koller.cameraroll.kotlin.data.models.Album;
import us.koller.cameraroll.kotlin.ui.widget.ParallaxImageView;

public class SimpleAlbumHolder extends AlbumHolder {

    public SimpleAlbumHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setAlbum(Album album) {
        super.setAlbum(album);
        final ImageView image = itemView.findViewById(R.id.image);
        if (image instanceof ParallaxImageView) {
            ((ParallaxImageView) image).setParallaxTranslation();
        }
        loadImage(image);
    }
}
