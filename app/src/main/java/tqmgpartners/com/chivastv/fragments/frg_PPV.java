package tqmgpartners.com.chivastv.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import tqmgpartners.com.chivastv.Adapters.adaOnDemandAdapter;
import tqmgpartners.com.chivastv.Adapters.adaPPVAdapter;
import tqmgpartners.com.chivastv.ChivasTv;
import tqmgpartners.com.chivastv.Entities.clsEditorial;
import tqmgpartners.com.chivastv.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frg_PPV.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frg_PPV#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_PPV extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frg_PPV() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_PPV.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_PPV newInstance(String param1, String param2) {
        frg_PPV fragment = new frg_PPV();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.frg_ppv, container, false);
        buildGuide(rootView);
        return rootView;
    }
    public String loadJSONFromAsset(String flName) {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.database);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.v("MainActivity", "Load json ok");
        } catch (IOException ex) {
            Log.v("MainActivity", "Error: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public void buildGuide(View rootView){
        try
        {
            LinearLayout _layChannel = (LinearLayout) rootView.findViewById(R.id.lytOnDemand);
            /*------------------------------------PRUEBA ARRAYLIST-----------------------------------------------*/
            ArrayList arrTitles = new ArrayList();
            arrTitles.add("Proximos Eventos");
            ArrayList<clsEditorial> arrEditorials = new ArrayList<>();
            JSONObject obj = new JSONObject(loadJSONFromAsset("raw/database.json"));
            JSONArray m_jArry = obj.getJSONArray("editorials");
            for(int i=0;i<m_jArry.length();i++){
                arrEditorials.add(new clsEditorial(m_jArry.getJSONObject(i)));
            }
            ((ChivasTv)getActivity().getApplication()).colEditorials=arrEditorials;
            /*---------------------------------------------------------------------------------------------------*/
            for( int i = 0; i < 3; i++ )
            {
                TextView textView = new TextView(rootView.getContext());
                textView.setText(arrTitles.get(i).toString());
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                _layChannel.addView(textView);

                RecyclerView rec = new RecyclerView(rootView.getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false);
                rec.setLayoutManager(mLayoutManager);
                rec.setItemAnimator(new DefaultItemAnimator());


                final adaPPVAdapter objAdaProgrammes = new adaPPVAdapter(arrEditorials);
                rec.setAdapter(objAdaProgrammes);
                _layChannel.addView(rec);

                /*scrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) mLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        // Triggered only when new data needs to be appended to the list
                        // Add whatever code is needed to append new items to the bottom of the list
                           new MovieOneCategoryTaskAsync(_frmParent, objCateogory,
                                "10", String.valueOf(objCateogory.getMovies().size()), _frmParent, objAdaProgrammes).execute();
                    }
                };
                    // Adds the scroll listener to RecyclerView
                rec.addOnScrollListener(scrollListener);*/
            }
        }catch (Exception e){
            Log.e("JsonException ",e.getMessage());
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
}
