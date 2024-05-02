package droidrocks.com.namaztimeapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import droidrocks.com.namaztimeapp.Models.RuqyahGroupEntity;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.databinding.RuqyahGroupItemRawBinding;

public class RuqyahGroupAdapter extends RecyclerView.Adapter<RuqyahGroupAdapter.MyViewHolder> {
    private static final String TAG = "RuqyahGroupEntityAdapter";
    private Context context;
    private Animation clickAnim;
    private List<RuqyahGroupEntity> ruqyahGroupList;

    public RuqyahGroupAdapter(Context context, List<RuqyahGroupEntity> ruqyahGroupList) {
        this.context = context;
        this.ruqyahGroupList = ruqyahGroupList;
    }

    @NonNull
    @Override
    public RuqyahGroupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RuqyahGroupAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.ruqyah_group_item_raw, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RuqyahGroupAdapter.MyViewHolder holder, int position) {
        clickAnim = AnimationUtils.loadAnimation(context, R.anim.click_animation);
        if (ruqyahGroupList==  null) {
            Log.d(TAG, "onBindViewHolder: empty list");
            return;
        }
        RuqyahGroupEntity entity = ruqyahGroupList.get(position);
        holder.binding.title.setText(entity.getSubtitle().trim());
        holder.binding.subtitle.setText(entity.getTitle().trim());

        holder.binding.itemCard.setOnClickListener(view -> {
            view.startAnimation(clickAnim);
            Log.d(TAG, "onBindViewHolder: clicked");
        });

    }

    @Override
    public int getItemCount() {
        if (ruqyahGroupList== null) {
            return 0;
        }
        return ruqyahGroupList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RuqyahGroupItemRawBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding =  RuqyahGroupItemRawBinding.bind(itemView);
        }
    }
}
