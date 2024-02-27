package droidrocks.com.namaztimeapp.Api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import droidrocks.com.namaztimeapp.Activitys.PostViewActivity;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.databinding.BlogpostItemRawBinding;

public class BlogPostAdapter extends RecyclerView.Adapter<BlogPostAdapter.myViewHolder> {

    private Context context;
    private List<PostDataModel> postDataModelList;


    public BlogPostAdapter(Context context, List<PostDataModel> postDataModelList) {
        this.context = context;
        this.postDataModelList = postDataModelList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.blogpost_item_raw,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        PostDataModel model = postDataModelList.get(position);
        String title =model.getTitle();
        holder.binding.postTitle.setText(title);
        holder.binding.postTitle.setSelected(true);
        String date = model.getDate();
        holder.binding.dateOfPost.setText(date);
        Glide.with(context)
                .load(model.getImage_url())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.binding.postImage);


        holder.binding.card.setOnClickListener(view -> {

            context.startActivity(new Intent(context, PostViewActivity.class)
                    .putExtra("title",model.getTitle())
                    .putExtra("image",model.getImage_url())
                    .putExtra("date",model.getDate())
                    .putExtra("content",model.getContent()));


        });


    }


    @Override
    public int getItemCount() {
        return postDataModelList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateDataList(List<PostDataModel> list) {
        this.postDataModelList = list;
        notifyDataSetChanged();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        BlogpostItemRawBinding binding;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
         binding =  BlogpostItemRawBinding.bind(itemView);

        }
    }
}
