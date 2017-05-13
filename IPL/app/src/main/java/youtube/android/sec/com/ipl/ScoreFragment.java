package youtube.android.sec.com.ipl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import youtube.android.sec.com.ipl.model.Comment;
import youtube.android.sec.com.ipl.model.LiveScore;
import youtube.android.sec.com.ipl.model.Player;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static  final String SHOWDIALOG="showdialog";
 public static final int DIALOGMSG =1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView mTeam;
    private TextView mRun;
    private TextView mWicket;
    private TextView mOver;
    private DatabaseReference mDatabase;
    private Query mDatabaseSummary;
    private ValueEventListener mScoreListioner;
    private ChildEventListener mSummeryListioner;
    private boolean firsttime=true;
    private RecyclerView mSummaryList;

    private CommentRecyclerViewAdapter mCommentAdapter;
    private List<Comment> mCommentList;
    private OnFragmentInteractionListener mListener;

    public ScoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreFragment newInstance(String param1, String param2) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mCommentList = new ArrayList<Comment>();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("livescore");

        mCommentAdapter = new CommentRecyclerViewAdapter(mCommentList , (OnFragmentInteractionListener) getActivity());
        mScoreListioner = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                LiveScore score = dataSnapshot.getValue(LiveScore.class);
                Utils.hideProgressDialog();
                if (score == null) {
                    Toast.makeText(getActivity(), "Score Update fialed ", Toast.LENGTH_SHORT).show();
                } else {
                    String team = score.team;
                    int run = score.run;
                    int wicket = score.wicket;
                    int over = score.over;
                    int ball = score.ball;
                    mTeam.setText(team);
                    mRun.setText(Integer.toString(run));
                    mWicket.setText(Integer.toString(wicket));
                    String overWithBall = Integer.toString(over) + "." + Integer.toString(ball);
                    mOver.setText(overWithBall);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w("ScoreFragment", " Database Error", databaseError.toException());
            }
        };

        mSummeryListioner = new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Comment newComment = dataSnapshot.getValue(Comment.class);
                mCommentList.add(0,newComment);
                mCommentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseSummary = FirebaseDatabase.getInstance().getReference().child("comment").limitToLast(10);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        mTeam = (TextView) view.findViewById(R.id.team);
        mRun = (TextView) view.findViewById(R.id.run);
        mWicket = (TextView) view.findViewById(R.id.wicket);
        mOver = (TextView) view.findViewById(R.id.over);
        mSummaryList= (RecyclerView) view.findViewById(R.id.list_summary);
        mSummaryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCommentAdapter = new CommentRecyclerViewAdapter(mCommentList , mListener);
        mSummaryList.setAdapter(mCommentAdapter);

        // set Adapter here for comment summary

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        mhandler.sendEmptyMessageAtTime(DIALOGMSG,0);
        mDatabase.addValueEventListener(mScoreListioner);
        mDatabaseSummary.addChildEventListener(mSummeryListioner);
    }

    @Override
    public void onPause() {
        super.onPause();
        Utils.hideProgressDialog();

        if(mScoreListioner!=null){
            mDatabase.removeEventListener(mScoreListioner);
        }
        if(mSummeryListioner!=null){
            mCommentList.clear();
            mDatabaseSummary.removeEventListener(mSummeryListioner);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


        Utils.hideProgressDialog();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onScoreFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Utils.hideProgressDialog();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onScoreFragmentInteraction(Uri uri);
    }
    private final  Handler mhandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            int dialog =msg.getData().getInt(SHOWDIALOG);
            switch (dialog){
                case DIALOGMSG:
                    Utils.showProgressDialog(getActivity());
                    break;
            }
        }
    };
 }
