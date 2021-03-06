package com.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.aggro.R;
import com.app.api.Category;
import com.app.appfragement.ShowCatAppFragement;
import com.app.gridcategory.ImageItem;
import com.app.gridcategory.SquareImageView;
import com.app.holder.GroupItem;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class AggroRecyclerGridViewAdapter extends RecyclerView.Adapter<AggroRecyclerGridViewAdapter.ItemViewHolder> {

    List<ImageItem> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    Context mContext;

    public AggroRecyclerGridViewAdapter(List<ImageItem> contents,Context mContext) {
        this.contents = contents;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        if (contents != null)
            return contents.size();
        else
            return 1;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new ItemViewHolder(view);
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_grid_small, parent, false);
                ItemViewHolder vh = new ItemViewHolder(view);
                vh.card_view.setTag(vh);
                vh.card_view.setOnClickListener(clickItemListener());
                return vh;
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                bindItemCardCell(position,holder);
                break;
        }
    }

    private void bindItemCardCell(int position,ItemViewHolder holder){
        if (position < getItemCount()) {
            holder.image.setImageResource(contents.get(position).getImage());
            holder.imageTitle.setText(contents.get(position).getTitle());
        }

    }

    private View.OnClickListener clickItemListener(){
        View.OnClickListener l = null;
        l = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemViewHolder holder = (ItemViewHolder) view.getTag();
                int id = holder.getPosition();
                if (view.getId() == holder.card_view.getId()){
                    id = id- 1;
//                    Toast.makeText(mContext, "imageIV onClick at" + id, Toast.LENGTH_SHORT).show();
                   selectItem(contents.get(id).getTitle());
                } else {
                    Toast.makeText(mContext, "RecyclerView Item onClick at " + id, Toast.LENGTH_SHORT).show();
                }
            }
        };

        return l;
    }


    private void selectshowCatApp(String local, Category.AggroCategory level) {
        // update the main content by replacing fragments
        Fragment fragment = ShowCatAppFragement.newInstance(local, level);
        android.support.v4.app.FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction  = fragmentManager.beginTransaction();
//        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        ((Activity)mContext).invalidateOptionsMenu();
    }

    private void selectItem(String category) {
        // update the main content by replacing fragments
        String localvariable = null;
        Category.AggroCategory catLevel = null;
        if (category.trim().equals(mContext.getResources().getString(R.string.cat_bussiness))) {
            localvariable = mContext.getResources().getString(R.string.cat_bussiness);
            catLevel = Category.AggroCategory.BUSINESS;
        } else if (category.trim().equals(mContext.getResources().getString(R.string.cat_lifestyle))) {
            localvariable = mContext.getResources().getString(R.string.cat_lifestyle);
            catLevel = Category.AggroCategory.LIFESTYLE;
        } else if (category.trim().equals(mContext.getResources().getString(R.string.cat_news))) {
            localvariable = mContext.getResources().getString(R.string.cat_news);
            catLevel = Category.AggroCategory.NEWS_AND_MAGAZINES;
        } else if (category.trim().equals(mContext.getResources().getString(R.string.cat_education))) {
            localvariable = mContext.getResources().getString(R.string.cat_education);
            catLevel = Category.AggroCategory.EDUCATION;
        } else if (category.trim().equals(mContext.getResources().getString(R.string.cat_transporation))) {
            localvariable = mContext.getResources().getString(R.string.cat_transporation);
            catLevel = Category.AggroCategory.TRANSPORTATION;
        } else if (category.trim().equals(mContext.getResources().getString(R.string.cat_productiity))) {
            localvariable = mContext.getResources().getString(R.string.cat_productiity);
            catLevel = Category.AggroCategory.PRODUCTIVITY;
        } else if (category.trim().equals(mContext.getResources().getString(R.string.cat_games))) {
            localvariable = mContext.getResources().getString(R.string.cat_games);
            catLevel = Category.AggroCategory.GAMES;
        } else if (category.trim().equals(mContext.getResources().getString(R.string.cat_travel))) {
            localvariable = mContext.getResources().getString(R.string.cat_travel);
            catLevel = Category.AggroCategory.TRAVEL_AND_LOCAL;
        } else if (category.trim().equals(mContext.getResources().getString(R.string.cat_sports))) {
            localvariable = mContext.getResources().getString(R.string.cat_sports);
            catLevel = Category.AggroCategory.SPORTS;
        } else if (category.trim().equals(mContext.getResources().getString(R.string.cat_health))) {
            localvariable = mContext.getResources().getString(R.string.cat_health);
            catLevel = Category.AggroCategory.HEALTH_AND_FITNESS;
        } else if (category.trim().equals(mContext.getResources().getString(R.string.cat_entertainment))) {
            localvariable = mContext.getResources().getString(R.string.cat_entertainment);
            catLevel = Category.AggroCategory.MUSIC_AND_AUDIO;
        } else {
            //custom category
        }

        selectshowCatApp(localvariable,catLevel);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView imageTitle;
        ImageView image;;
        CardView card_view;
        public ItemViewHolder(View convertView){
            super(convertView);
            card_view = (CardView)convertView.findViewById(R.id.card_view);
            imageTitle = (TextView)convertView.findViewById(R.id.text);
            image = (ImageView)convertView.findViewById(R.id.picture);
        }
    }
}