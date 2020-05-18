package lk.chathurabuddi.dashboard.corona.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import lk.chathurabuddi.dashboard.corona.BuildConfig;
import lk.chathurabuddi.dashboard.corona.R;

public class AboutFragment extends Fragment {

    public AboutFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.inflateViews(view);
    }

    private void inflateViews(@NonNull View view) {
        view.findViewById(R.id.fab).setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                fragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.slide_in_right, R.anim.slide_out_left
                ).replace(
                    R.id.nav_host,
                    ScreenFragment.DASHBOARD.getInstance(),
                    ScreenFragment.DASHBOARD.name()
                ).commit();
            }
        });

        ((TextView)view.findViewById(R.id.version_tag)).setText(BuildConfig.VERSION_NAME);
    }
}
