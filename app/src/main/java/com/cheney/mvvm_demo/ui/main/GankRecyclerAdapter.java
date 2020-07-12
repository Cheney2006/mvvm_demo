package com.cheney.mvvm_demo.ui.main;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cheney.mvvm_demo.R;
import com.cheney.mvvm_demo.databinding.ItemGankBinding;
import com.cheney.mvvm_demo.databinding.ItemGankFuliBinding;
import com.cheney.mvvm_demo.entity.Gank;

import java.util.Collections;
import java.util.List;

public class GankRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Gank> ganks;

    private ItemClickCallback itemClickCallback;

    public GankRecyclerAdapter(ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
        this.ganks = Collections.EMPTY_LIST;
    }

    public final void setTodayGanks(final List<Gank> ganks) {
        if (this.ganks.isEmpty()) {
            this.ganks = ganks;
            this.notifyItemRangeInserted(0, this.ganks.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff((DiffUtil.Callback) (new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return GankRecyclerAdapter.this.ganks.size();
                }

                @Override
                public int getNewListSize() {
                    return ganks.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return TextUtils.equals((CharSequence) ((Gank) GankRecyclerAdapter.this.ganks.get(oldItemPosition)).getGankId(), (CharSequence) ((Gank) ganks.get(newItemPosition)).getGankId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return TextUtils.equals((CharSequence) ((Gank) GankRecyclerAdapter.this.ganks.get(oldItemPosition)).getGankId(), (CharSequence) ((Gank) ganks.get(newItemPosition)).getGankId()) && ((Gank) GankRecyclerAdapter.this.ganks.get(oldItemPosition)).isViewed() == ((Gank) ganks.get(newItemPosition)).isViewed();
                }
            }));

            this.ganks = ganks;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case Gank.GROUP_FULI:
            case Gank.GROUP_ANDROID:
            case Gank.GROUP_FRONT_END:
            case Gank.GROUP_VIDEO:
                viewHolder = new GroupVH((TextView) (View.inflate(parent.getContext(), R.layout.item_group, null)));
                break;

            case Gank.FULI:
                viewHolder = new FuliVH(DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.item_gank_fuli,
                        parent,
                        false));
                break;
            case Gank.ANDROID:
            case Gank.FRONT_END:
            case Gank.VIDEO:
                viewHolder = new DefaultVH(
                        DataBindingUtil.inflate(
                                LayoutInflater.from(parent.getContext()),
                                R.layout.item_gank,
                                parent,
                                false
                        )
                );
                break;
            default:
                viewHolder = null;
                // impossible
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case Gank.GROUP_FULI:
                GroupVH groupVH= (GroupVH) holder;
                groupVH.titleView.setText("福利");
                break;
            case Gank.GROUP_ANDROID:
                groupVH= (GroupVH) holder;
                groupVH.titleView.setText("Android");
                break;
            case Gank.GROUP_FRONT_END:
                groupVH= (GroupVH) holder;
                groupVH.titleView.setText("前端");
                break;
            case Gank.GROUP_VIDEO:
                groupVH= (GroupVH) holder;
                groupVH.titleView.setText("视频");
                break;
            case Gank.FULI:
                FuliVH fuliVH = (FuliVH) holder;
                fuliVH.binding.setGank(getItem(position));
                break;

            case Gank.ANDROID:
            case Gank.FRONT_END:
            case Gank.VIDEO:
                DefaultVH defaultVH = (DefaultVH) holder;
                defaultVH.binding.setGank(getItem(position));
                defaultVH.binding.setOnClick(this.itemClickCallback);
//                defaultVH.binding.executePendingBindings();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return ganks.size();
    }

    private final Gank getItem(int position){
        return this.ganks.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return this.ganks.get(position).getTypeId();
    }

    public static final class GroupVH extends RecyclerView.ViewHolder {
        @NonNull
        private final TextView titleView;

        @NonNull
        public final TextView getTitleView() {
            return this.titleView;
        }

        public GroupVH(@NonNull TextView titleView) {
            super((View) titleView);
            this.titleView = titleView;
        }
    }

    public static final class FuliVH extends RecyclerView.ViewHolder {
        @NonNull
        private final ItemGankFuliBinding binding;


        public FuliVH(ItemGankFuliBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static final class DefaultVH extends RecyclerView.ViewHolder {
        @NonNull
        private final ItemGankBinding binding;


        public DefaultVH(@NonNull ItemGankBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
