package droidrocks.com.namaztimeapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import droidrocks.com.namaztimeapp.Activitys.NabiStoryDetailsActivity;
import droidrocks.com.namaztimeapp.Models.StoryItem;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.databinding.StoryItemRawBinding;

public class NabiStoryAdapter extends RecyclerView.Adapter<NabiStoryAdapter.MyViewHolder>{

    private Context mContext;
    private List<StoryItem> storyItemList;

    public NabiStoryAdapter(Context mContext, List<StoryItem> storyItemList) {
        this.mContext = mContext;
        this.storyItemList = storyItemList;
    }

    @NonNull
    @Override
    public NabiStoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.story_item_raw, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NabiStoryAdapter.MyViewHolder holder, int position) {
        StoryItem storyItem = storyItemList.get(position);
        holder.binding.title.setText(storyItem.title);
        // Load image from assets using Glide
        Glide.with(mContext)
                .load("file:///android_asset/nabi_story/" + storyItem.getImage()) // Path to the image in assets folder
                .placeholder(R.drawable.placeholder_image) // Placeholder image while loading (optional)
                .error(R.drawable.placeholder_image) // Error image if Glide fails to load (optional)
                .into(holder.binding.itemImage);

        holder.binding.itemCard.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, NabiStoryDetailsActivity.class);
            intent.putExtra("storyItem", storyItem);
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (storyItemList!= null){
            return storyItemList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        StoryItemRawBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = StoryItemRawBinding.bind(itemView);
        }
    }
}
