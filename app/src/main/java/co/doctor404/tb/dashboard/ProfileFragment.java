package co.doctor404.tb.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import co.doctor404.tb.R;
import co.doctor404.tb.profile.ProfileHabitsActivity;
import co.doctor404.tb.profile.ProfileOthersActivity;
import co.doctor404.tb.profile.ProfilePhysicalActivity;


public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Please note the third parameter should be false, otherwise a java.lang.IllegalStateException maybe thrown.
        View fragView = inflater.inflate(R.layout.fragment_profile, container, false);

        fragView.findViewById(R.id.phy).setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ProfilePhysicalActivity.class);
            startActivity(intent);
        });

        fragView.findViewById(R.id.habits).setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ProfileHabitsActivity.class);
            startActivity(intent);
        });

        fragView.findViewById(R.id.others).setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ProfileOthersActivity.class);
            startActivity(intent);
        });

        return fragView;
    }
}


