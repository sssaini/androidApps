package youtube.android.sec.com.ipl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import youtube.android.sec.com.ipl.MatchFragment.OnListFragmentInteractionListener;
import youtube.android.sec.com.ipl.dummy.DummyContent.DummyItem;
import youtube.android.sec.com.ipl.model.Match;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMatchRecyclerViewAdapter extends RecyclerView.Adapter<MyMatchRecyclerViewAdapter.ViewHolder> {

    private final List<Match> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyMatchRecyclerViewAdapter(List<Match> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_match, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Match tmp = mValues.get(position);
        holder.mFirstTeam.setText(tmp.teamFirst);
        holder.mSecondTeam.setText(tmp.teamSecond);
        holder.mTime.setText(tmp.time);
        holder.mGround.setText(tmp.vanue);


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
        public final TextView mFirstTeam;
        public final TextView mSecondTeam;
        public final TextView mTime;
        public final TextView mGround;
        public Match mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mFirstTeam = (TextView) view.findViewById(R.id.first_team);
            mSecondTeam =(TextView) view.findViewById(R.id.second_team);
            mTime =(TextView)view.findViewById(R.id.time);
            mGround=(TextView) view.findViewById(R.id.vanue);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mFirstTeam.getText()+ "'"+ mSecondTeam.getText()+" "+mTime.getText();
        }
    }
}
