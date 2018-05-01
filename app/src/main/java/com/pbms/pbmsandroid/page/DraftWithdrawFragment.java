package com.pbms.pbmsandroid.page;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pbms.pbmsandroid.MainActivity;
import com.pbms.pbmsandroid.R;
import com.pbms.pbmsandroid.adapter.RvDraftWithdrawAdapter;
import com.pbms.pbmsandroid.model.DraftWithdrawDao;
import com.pbms.pbmsandroid.service.HttpManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DraftWithdrawFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DraftWithdrawFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DraftWithdrawFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String bgyId;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;
    Button button;

    public DraftWithdrawFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DraftWithdrawFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DraftWithdrawFragment newInstance(String param1) {
        DraftWithdrawFragment fragment = new DraftWithdrawFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bgyId = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_draft_withdraw, container, false);
        button = view.findViewById(R.id.btn_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goToWithdraw();
            }
        });
        recyclerView = view.findViewById(R.id.rv_wd);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        getDraftList();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
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
        void onFragmentInteraction(Uri uri);
    }

    public void getDraftList() {
        Call<List<DraftWithdrawDao>> call = HttpManager.getInstance().getService().getDraftWithdrawByBgy(bgyId);
        call.enqueue(new Callback<List<DraftWithdrawDao>>() {
            @Override
            public void onResponse(Call<List<DraftWithdrawDao>> call, Response<List<DraftWithdrawDao>> response) {
                if (response.isSuccessful()) {
                    Log.d("service", "if :: " + response.message());
                    List<DraftWithdrawDao> res = response.body();
                    Log.d("service", "if :: " + res.size());
                    RvDraftWithdrawAdapter adapter = new RvDraftWithdrawAdapter(res,getActivity(),bgyId);
                    recyclerView.setAdapter(adapter);
                } else {
                    try {
                        Log.d("service", "else :: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DraftWithdrawDao>> call, Throwable t) {
                Log.d("service", "else :: " + t);
            }
        });

    }
}
