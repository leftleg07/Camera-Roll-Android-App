package us.koller.cameraroll.kotlin.styles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import us.koller.cameraroll.kotlin.R;

public class NestedRecyclerView extends Style {

    public static int getValue(Context context) {
        return context.getResources().getInteger(R.integer.STYLE_NESTED_RECYCLER_VIEW_VALUE);
    }

    @Override
    public int getViewHolderLayoutRes() {
        return R.layout.album_cover_nested_recyclerview;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolderInstance(@NonNull ViewGroup parent) {
//        return new NestedRecyclerViewAlbumHolder(inflateView(parent));
        return null;
    }

    @Override
    boolean columnCountChangeable() {
        return false;
    }

    @Override
    String getColumnCountPrefKey(Context context) {
        return context.getString(R.string.STYLE_NESTED_RECYCLER_VIEW_COLUMN_COUNT_PREF_KEY);
    }

    @Override
    int getDefaultColumnCount() {
        return 1;
    }

    @Override
    int getGridSpacingRes() {
        return R.dimen.nested_recycler_view_style_grid_spacing;
    }

    @Override
    public View createPrefDialogView(@NonNull ViewGroup container) {
        View view = inflatePrefDialogItemView(container);

        Context context = container.getContext();

        TextView name = view.findViewById(R.id.name);
        name.setText(context.getString(R.string.STYLE_NESTED_RECYCLER_VIEW_NAME));

        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.style_nested_recycler_view);
        imageView.setColorFilter(getAccentColor(context));

        disableColumnCountButtons(view);

        return view;
    }
}
