package org.molina.poebrowser;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vikin on 15/03/2018.
 */

public class PoeRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView mCharacterPic;
    public TextView mTitle;

    public PoeRecyclerViewHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.textViewTitle);
        mCharacterPic = itemView.findViewById(R.id.imageViewPic);
    }

    public ImageView getCharacterPic() {
        return mCharacterPic;
    }
}
