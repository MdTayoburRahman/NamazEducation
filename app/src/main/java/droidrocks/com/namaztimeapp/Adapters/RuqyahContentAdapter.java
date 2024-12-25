package droidrocks.com.namaztimeapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import droidrocks.com.namaztimeapp.Models.RuqyahAyahEntity;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.databinding.RuqyahContentItemRawBinding;

public class RuqyahContentAdapter extends RecyclerView.Adapter<RuqyahContentAdapter.MyViewHolder> {
    private static final String TAG = "RuqyahContentAdapter";
    private Context mContext;
    private List<RuqyahAyahEntity> ruqyahAyahList;
    public RuqyahContentAdapter(Context mContext, List<RuqyahAyahEntity> ruqyahAyahList) {
        this.mContext = mContext;
        this.ruqyahAyahList = ruqyahAyahList;
    }
    @NonNull
    @Override
    public RuqyahContentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RuqyahContentAdapter.MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.ruqyah_content_item_raw, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull RuqyahContentAdapter.MyViewHolder holder, int position) {
            if (ruqyahAyahList == null){
                Log.d(TAG, "onBindViewHolder: empty list");
                return;
            }

            RuqyahAyahEntity entity = ruqyahAyahList.get(position);
            holder.binding.timetitle.setText(entity.getAyah_title());
            holder.binding.title.setText(entity.getAyah_arabic());
            holder.binding.subtitle.setText(entity.getAyah_bangla());
    }

    @Override
    public int getItemCount() {
        if (ruqyahAyahList==null){
            return 0;
        }
        return ruqyahAyahList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private RuqyahContentItemRawBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding =  RuqyahContentItemRawBinding.bind(itemView);
        }
    }
}
