package org.molina.poebrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Vikin on 15/03/2018.
 */

public class PoeRecyclerViewAdapter extends RecyclerView.Adapter<PoeRecyclerViewHolder> {

    private static final String LOG_TAG = PoeRecyclerViewAdapter.class.getSimpleName();

    private List<Champion> mChampionList;
    private Context mContext;

    public PoeRecyclerViewAdapter(List<Champion> mChampionList, Context mContext) {
        this.mChampionList = mChampionList;
        this.mContext = mContext;
    }

    @Override
    public PoeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, null, false);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        PoeRecyclerViewHolder poeRecyclerViewHolder = new PoeRecyclerViewHolder(view);

        return poeRecyclerViewHolder;
    }

    @Override
    public int getItemCount() {
        return (mChampionList != null ? mChampionList.size() : 0);
    }

    @Override
    public void onBindViewHolder(PoeRecyclerViewHolder holder, int position) {
        Champion championItem = mChampionList.get(position);

        holder.mCharacterPic.setImageResource(getChampionClassForThumbnail(championItem.getClase()));

        holder.mTitle.setText(championItem.getName());
    }

    public void loadNewData(List<Champion> champions) {
        mChampionList = champions;

        notifyDataSetChanged();
    }

    public Champion getChampion(int position) {
        return (mChampionList != null ? mChampionList.get(position) : null);
    }

    protected int getChampionClassForThumbnail(String clase) {
        switch (clase.toLowerCase()) {
            case "ranger":
            case "deadeye":
            case "raider":
            case "pathfinder":
                return R.drawable.ranger;
            case "scion":
                return R.drawable.scion;
            case "marauder":
            case "juggernaut":
            case "berserker":
            case "chieftain":
                return R.drawable.marauder;
            case "shadow":
            case "assassin":
            case "saboteur":
            case "trickster":
                return R.drawable.shadow;
            case "witch":
            case "necromancer":
            case "elementalist":
            case "occultist":
                return R.drawable.witch;
            case "duelist":
            case "slayer":
            case "gladiator":
            case "champion":
                return R.drawable.duelist;
            case "templar":
            case "inquisitor":
            case "hierophant":
            case "guardian":
                return R.drawable.templar;
            default:
                return R.drawable.placeholder;
        }
    }
}
