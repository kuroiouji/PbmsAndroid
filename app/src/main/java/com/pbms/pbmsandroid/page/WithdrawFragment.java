package com.pbms.pbmsandroid.page;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pbms.pbmsandroid.datepicker.AppUtils;
import com.pbms.pbmsandroid.datepicker.DatePickerFragment;
import com.pbms.pbmsandroid.datepicker.DatePickerSelectionInterface;
import com.pbms.pbmsandroid.MainActivity;
import com.pbms.pbmsandroid.R;
import com.pbms.pbmsandroid.adapter.SpBgyAdapter;
import com.pbms.pbmsandroid.adapter.SpPersonAdapter;
import com.pbms.pbmsandroid.model.BudgetYear;
import com.pbms.pbmsandroid.model.BudgetYearDao;
import com.pbms.pbmsandroid.model.PersonDao;
import com.pbms.pbmsandroid.model.TransDao;
import com.pbms.pbmsandroid.service.ApiService;
import com.pbms.pbmsandroid.service.HttpManager;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WithdrawFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WithdrawFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WithdrawFragment extends Fragment implements DatePickerSelectionInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String bgyId;

    private long mLastTimePickerValue;
    private long mLastDatePickerValue;
    private TextView mSelectedDateOrTimeTextView;

    private OnFragmentInteractionListener mListener;

    Button button;
    Spinner spinner, spinner_ps;
    EditText billCode, billTopic;
    TextView textDatePicker;
    private List<BudgetYear> budgetYears;
    private List<PersonDao> personDaos;
    String bgy, ps;

    public WithdrawFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment WithdrawFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WithdrawFragment newInstance(String param1) {
        WithdrawFragment fragment = new WithdrawFragment();
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

    private void initViews(View view) {
        mSelectedDateOrTimeTextView = view.findViewById(R.id.datePicker);
        TextView mDatePickerButton = view.findViewById(R.id.datePicker);
        mDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO call date picker fragment here
                openDatePicker();
            }
        });
        button = view.findViewById(R.id.btn_withdraw);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertWithdraw();
            }
        });
    }

    private void insertWithdraw() {
        String codeBill, topicBill, date;
        codeBill = billCode.getText().toString();
        topicBill = billTopic.getText().toString();
        date = textDatePicker.getText().toString();
        String[] datas = date.split(" ");
        date = datas[2]+"-"+datas[1]+"-"+datas[0];
        if (!bgy.isEmpty() && !ps.isEmpty() && !codeBill.isEmpty() && !topicBill.isEmpty() && !date.isEmpty()) {
            Log.d("data", "insertWithdraw: " + codeBill + " " + topicBill + " " + ps + " " + date + " " + bgy);
            Call<TransDao> call = HttpManager.getInstance().getService().billSave(codeBill, topicBill, ps, date, bgy);
            call.enqueue(new Callback<TransDao>() {
                @Override
                    public void onResponse(Call<TransDao> call, Response<TransDao> response) {
                    if (response.isSuccessful()) {
                        ((MainActivity) getActivity()).goToDraft();
                        Toast.makeText(getActivity(), response.body().getStr(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "บันทึกข้อมูลไม่สำเร็จ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<TransDao> call, Throwable t) {
                    Log.d("insert", "onFailure: " + t);
                    Toast.makeText(getActivity(), "บันทึกข้อมูลไม่สำเร็จ", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void openDatePicker() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();

        if (mLastDatePickerValue > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mLastDatePickerValue);
            Bundle bundle = new Bundle();
            bundle.putInt("year", calendar.get(Calendar.YEAR));
            bundle.putInt("month", calendar.get(Calendar.MONTH) + 1);
            bundle.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
            datePickerFragment.setArguments(bundle);
        }
        datePickerFragment.delegate = WithdrawFragment.this;
        datePickerFragment.setCancelable(false);
        datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("withdraw", "onCreateView: " + bgyId);
        View view = inflater.inflate(R.layout.fragment_withdraw, container, false);

        spinner = view.findViewById(R.id.wd_bgy_id);
        spinner_ps = view.findViewById(R.id.ps_name);
        billCode = view.findViewById(R.id.bill_code);
        billTopic = view.findViewById(R.id.bill_topic);
        textDatePicker = view.findViewById(R.id.datePicker);

        getBgy();
        getPerson();
        initViews(view);
        spinnerEvents();
        return view;
    }

    private void spinnerEvents() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bgy = budgetYears.get(position).getBgyId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_ps.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ps = personDaos.get(position).getPsId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    @Override
    public void onDateSelected(int day, int month, int year) {
        mLastTimePickerValue = 0;
        mSelectedDateOrTimeTextView.setText(String.valueOf(AppUtils.formatCharLength(2, day) + " " + AppUtils.formatCharLength(2, (month + 1)) + " " + (year)));
        mLastDatePickerValue = AppUtils.dateIntoTimeStamp(String.valueOf(day + " " + (month) + " " + year));
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

    public void getBgy() {
        Call<List<BudgetYearDao>> call = HttpManager.getInstance().getService().getBudgetYear();
        call.enqueue(new Callback<List<BudgetYearDao>>() {
            @Override
            public void onResponse(Call<List<BudgetYearDao>> call, Response<List<BudgetYearDao>> response) {
                if (response.isSuccessful()) {
                    Log.d("bgy", "if :: " + response.message());
                    List<BudgetYearDao> res = response.body();
                    budgetYears = res.get(0).getArrBgy();
                    Log.d("bgy", "if :: " + budgetYears.size());
                    if (budgetYears.size() > 0) {
                        SpBgyAdapter adapter = new SpBgyAdapter(getActivity(), budgetYears);
                        spinner.setAdapter(adapter);
                    }
                } else {
                    try {
                        Log.d("bgy", "else :: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BudgetYearDao>> call, Throwable t) {
                Log.d("service", "else :: " + t);
            }
        });
    }

    public void getPerson() {
        Call<List<PersonDao>> call = HttpManager.getInstance().getService().getPerson();
        call.enqueue(new Callback<List<PersonDao>>() {
            @Override
            public void onResponse(Call<List<PersonDao>> call, Response<List<PersonDao>> response) {
                if (response.isSuccessful()) {
                    Log.d("ps", "if :: " + response.message());
                    List<PersonDao> res = response.body();
                    personDaos = res;
                    if (res.size() > 0) {
                        SpPersonAdapter adapter = new SpPersonAdapter(getActivity(), res);
                        spinner_ps.setAdapter(adapter);
                    }
                } else {
                    try {
                        Log.d("ps", "else :: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PersonDao>> call, Throwable t) {
                Log.d("service", "else :: " + t);
            }
        });
    }

}
