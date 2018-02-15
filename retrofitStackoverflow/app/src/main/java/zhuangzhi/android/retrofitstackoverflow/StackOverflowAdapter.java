package zhuangzhi.android.retrofitstackoverflow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Stack;

import zhuangzhi.android.retrofitstackoverflow.model.Answer;

/**
 * Created by zhuangzhili on 2018-02-14.
 */

public class StackOverflowAdapter extends RecyclerView.Adapter<StackOverflowAdapter.ViewHolder> {

    private List<Answer> data;

    public StackOverflowAdapter(List<Answer> data) {
        this.data = data;
    }


    @Override
    public StackOverflowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_selectable_list_item,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StackOverflowAdapter.ViewHolder holder, int position) {
        Answer answer = (Answer) data.get(position);
        holder.textView.setText(answer.toString());
        holder.itemView.setTag(answer.answerId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
