package com.example.mindwind.HelperClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mindwind.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapter2 extends RecyclerView.Adapter<SliderAdapter2.SliderViewHolder>{

    private List<Slideritem> slideritems;
    private ViewPager2 viewPager2;

    public SliderAdapter2(List<Slideritem> slideritems, ViewPager2 viewPager2) {
        this.slideritems = slideritems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_containter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter2.SliderViewHolder holder, int position) {
        holder.setImage(slideritems.get(position));
        if (position== slideritems.size()-2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return slideritems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;
        SliderViewHolder (@NonNull View itemView){
            super(itemView);
            imageView= itemView.findViewById(R.id.imageSlide);
        }
        void setImage (Slideritem slideritem){
            imageView.setImageResource(slideritem.getImage());

        }
    }
    private Runnable runnable= new Runnable() {
        @Override
        public void run() {
            slideritems.addAll(slideritems);
            notifyDataSetChanged();
        }
    };
}
