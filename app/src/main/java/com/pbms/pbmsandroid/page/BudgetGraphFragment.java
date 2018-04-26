package com.pbms.pbmsandroid.page;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.pbms.pbmsandroid.R;
import com.pbms.pbmsandroid.model.UseBudgetDao;
import com.pbms.pbmsandroid.service.HttpManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BudgetGraphFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BudgetGraphFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetGraphFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String bgyId;

    private OnFragmentInteractionListener mListener;

    private View pieChart;
    private PieChart chart;

    public BudgetGraphFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BudgetGraphFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BudgetGraphFragment newInstance(String param1) {
        BudgetGraphFragment fragment = new BudgetGraphFragment();
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
        service();
        View view = inflater.inflate(R.layout.fragment_budget_graph, container, false);
        chart = (PieChart) view.findViewById(R.id.bg_graph);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

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

    public void service() {
        Call<List<UseBudgetDao>> call = HttpManager.getInstance().getService().getUseBudget(bgyId);
        call.enqueue(new Callback<List<UseBudgetDao>>() {
            @Override
            public void onResponse(Call<List<UseBudgetDao>> call, Response<List<UseBudgetDao>> response) {
                if (response.isSuccessful()){
                    List<UseBudgetDao> useBudget = response.body();
                    initPieChart(useBudget);
                    Log.d("getServiceUseBudget","if : " + useBudget.size());
                }else{
                    Log.d("getServiceUseBudget","if : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UseBudgetDao>> call, Throwable t) {
                Log.d("getServiceUseBudget","onFailure : " + t);
            }
        });
    }

    private void initPieChart(List<UseBudgetDao> useBudget) {
        //ArrayList<FruitDao> listStudent = Student.getSampleStudentData(4);
        ArrayList<PieEntry> entries = new ArrayList<>();

        for(UseBudgetDao useBudgetDao: useBudget){
            entries.add(new PieEntry(useBudgetDao.getUsRsSpend(),useBudgetDao.getUsRsName()));
        }

        PieDataSet dataSet = new PieDataSet(entries,"useBudget");
        dataSet.setSelectionShift(10);
        dataSet.setValueTextSize(14);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        dataSet.setValueLinePart1Length(0.5f);
        dataSet.setValueLinePart2Length(0.5f);
        dataSet.setValueFormatter(new PercentFormatter());

        PieData data = new PieData(dataSet);
        chart.setData(data);
        chart.setHoleRadius(30);
        chart.setTransparentCircleRadius(40);
        chart.animateY(3000);
        chart.animateY(3000, Easing.EasingOption.EaseInBounce);
        chart.setUsePercentValues(true);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
}
