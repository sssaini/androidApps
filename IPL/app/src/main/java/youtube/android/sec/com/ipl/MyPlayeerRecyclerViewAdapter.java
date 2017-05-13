package youtube.android.sec.com.ipl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import youtube.android.sec.com.ipl.PlayeerFragment.OnListFragmentInteractionListener;
import youtube.android.sec.com.ipl.dummy.DummyContent.DummyItem;
import youtube.android.sec.com.ipl.model.Player;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPlayeerRecyclerViewAdapter extends RecyclerView.Adapter<MyPlayeerRecyclerViewAdapter.ViewHolder> {

    private final List<Player> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyPlayeerRecyclerViewAdapter(List<Player> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_playeer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Player tmp = mValues.get(position);
        holder.mName.setText(tmp.name);
        holder.mRole.setText(tmp.role);
        holder.mTeam.setText(tmp.team);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName;
        public final TextView mRole;
        public final TextView mTeam;
        public Player mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.name);
            mRole = (TextView) view.findViewById(R.id.role);
            mTeam =(TextView) view.findViewById(R.id.team);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'"+ mTeam.getText();
        }
    }
}
