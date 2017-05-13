package youtube.android.sec.com.ipl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import youtube.android.sec.com.ipl.model.Comment;
import youtube.android.sec.com.ipl.model.Match;

/**
 * Created by shiv on 4/16/2017.
 */

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder> {


    private final List<Comment> mValues;
    private final ScoreFragment.OnFragmentInteractionListener mListener;

    public CommentRecyclerViewAdapter(List<Comment> items, ScoreFragment.OnFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new CommentRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mItem=mValues.get(position);
        Comment tmp =mValues.get(position);
        holder.mComment.setText(tmp.msg);
    }

    @Override
    public int getItemCount() {


        return mValues.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Comment mItem;
        public View mView;
        public TextView mComment;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mComment =(TextView) view.findViewById(R.id.current_comment);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
