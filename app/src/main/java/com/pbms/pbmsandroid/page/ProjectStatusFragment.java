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

import com.pbms.pbmsandroid.MainActivity;
import com.pbms.pbmsandroid.R;
import com.pbms.pbmsandroid.adapter.RvStatusAdapter;
import com.pbms.pbmsandroid.model.ProjectDao;
import com.pbms.pbmsandroid.model.StatusDao;
import com.pbms.pbmsandroid.service.HttpManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectStatusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProjectStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectStatusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String bgyId;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;
    List<StatusDao> status;

    public ProjectStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ProjectStatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectStatusFragment newInstance(String param1) {
        ProjectStatusFragment fragment = new ProjectStatusFragment();
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
        View view =  inflater.inflate(R.layout.fragment_project_status, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        getSt();

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

    public void getProAcList() {
        Log.d("service", "service: "+((MainActivity) getActivity()).getBgyId());
        Call<List<ProjectDao>> call = HttpManager.getInstance().getService().getProjectByYear(bgyId);
        Log.d("service", "GG ");
        call.enqueue(new Callback<List<ProjectDao>>() {
            @Override
            public void onResponse(Call<List<ProjectDao>> call, Response<List<ProjectDao>> response) {
                if (response.isSuccessful()) {
                    List<ProjectDao> res = response.body();
                    Log.d("service", "if :: " + response.message());
                    RvStatusAdapter adapter = new RvStatusAdapter(res, status, getActivity(),bgyId);
                    recyclerView.setAdapter(adapter);
                    /*for (ProjectDao row : res) {
                        Log.d("service", row.getPjName());
                    }*/
                } else {
                    try {
                        Log.d("service", "else :: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProjectDao>> call, Throwable t) {
                Log.d("service", "else :: " + t);
            }
        });
    }

    public void getSt() {

        Call<List<StatusDao>> call = HttpManager.getInstance().getService().getStatus();
        call.enqueue(new Callback<List<StatusDao>>() {
            @Override
            public void onResponse(Call<List<StatusDao>> call, Response<List<StatusDao>> response) {
                if (response.isSuccessful()) {
                    List<StatusDao> res = response.body();
                    Log.d("service", "if :: " + response.message());
                    status = res;
                    getProAcList();
                } else {
                    try {
                        Log.d("service", "else :: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<StatusDao>> call, Throwable t) {
                Log.d("service", "else :: " + t);
            }
        });
    }
}
