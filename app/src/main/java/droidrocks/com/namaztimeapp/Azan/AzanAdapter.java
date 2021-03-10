package droidrocks.com.namaztimeapp.Azan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import droidrocks.com.namaztimeapp.R;

public class AzanAdapter extends RecyclerView.Adapter<AzanAdapter.ViewHolder> {

    String title [];
    Context mContext;

    public AzanAdapter(String[] title, Context mContext) {
        this.title = title;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.azan_layout_raw,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(title[position]);
        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    Intent intent = new Intent(mContext,ExoPlayerActivity.class);
                    intent.putExtra("link","https://droidrocks.com/azans/a1.mp3");
                    mContext.startActivity(intent);
                }
                if (position == 1){
                    Intent intent = new Intent(mContext,ExoPlayerActivity.class);
                    intent.putExtra("link","https://droidrocks.com/azans/a2.mp3");
                    mContext.startActivity(intent);
                }
                if (position == 2){
                    Intent intent = new Intent(mContext,ExoPlayerActivity.class);
                    intent.putExtra("link","https://droidrocks.com/azans/a3.mp3");
                    mContext.startActivity(intent);
                }
                if (position == 3){
                    Intent intent = new Intent(mContext,ExoPlayerActivity.class);
                    intent.putExtra("link","https://droidrocks.com/azans/a4.mp3");
                    mContext.startActivity(intent);
                }
                if (position == 4){
                    Intent intent = new Intent(mContext,ExoPlayerActivity.class);
                    intent.putExtra("link","https://droidrocks.com/azans/a5.mp3");
                    mContext.startActivity(intent);
                }
                if (position == 5){
                    Intent intent = new Intent(mContext,ExoPlayerActivity.class);
                    intent.putExtra("link","https://droidrocks.com/azans/a6.mp3");
                    mContext.startActivity(intent);
                }
                if (position == 6){
                    Intent intent = new Intent(mContext,ExoPlayerActivity.class);
                    intent.putExtra("link","https://droidrocks.com/azans/a7.mp3");
                    mContext.startActivity(intent);
                }
                if (position == 7){
                    Intent intent = new Intent(mContext,ExoPlayerActivity.class);
                    intent.putExtra("link","https://droidrocks.com/azans/a8.mp3");
                    mContext.startActivity(intent);
                }
                if (position == 8){
                    Intent intent = new Intent(mContext,ExoPlayerActivity.class);
                    intent.putExtra("link","https://droidrocks.com/azans/a9.mp3");
                    mContext.startActivity(intent);
                }
                if (position == 9){
                    Intent intent = new Intent(mContext,ExoPlayerActivity.class);
                    intent.putExtra("link","https://droidrocks.com/azans/a10.mp3");
                    mContext.startActivity(intent);
                }



            }
        });

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView ;
        MaterialCardView materialCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.azan_name_tv);
            materialCardView = itemView.findViewById(R.id.azan_card);

        }
    }
}
