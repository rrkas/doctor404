package co.doctor404.tb.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import co.doctor404.tb.R;


public class ReportsFragment extends Fragment {

    @Nullable
    @Override
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Please note the third parameter should be false, otherwise a java.lang.IllegalStateException maybe thrown.
        View retView = inflater.inflate(R.layout.fragment_reports, container, false);
        SharedPreferences physical = retView.getContext().getSharedPreferences("physical", Context.MODE_PRIVATE);
        SharedPreferences rhr = retView.getContext().getSharedPreferences("rhr", Context.MODE_PRIVATE);
        if(physical.contains("height")) {
            Double ht = Double.parseDouble(physical.getString("height", "0.0"));
            double wt = Double.parseDouble(physical.getString("weight", "0.0"));
            String bmi = String.format("%.2f Kg/ m2", wt / (ht * ht));
            ((TextView)retView.findViewById(R.id.reports_bmi)).setText(bmi);
        }else{
            ((TextView)retView.findViewById(R.id.reports_bmi)).setText("-- Kg/ m2");
        }
        if(rhr.contains("val")){
            ((TextView)retView.findViewById(R.id.reports_bpm)).setText(rhr.getString("val", "") + " bpm");
        }else{
            ((TextView)retView.findViewById(R.id.reports_bpm)).setText("-- bpm");
        }
        return retView;
    }
}
