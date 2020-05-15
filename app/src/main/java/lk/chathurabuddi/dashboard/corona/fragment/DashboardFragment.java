package lk.chathurabuddi.dashboard.corona.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lk.chathurabuddi.dashboard.corona.MainActivity;
import lk.chathurabuddi.dashboard.corona.R;
import lk.chathurabuddi.dashboard.corona.databinding.FragmentDashboardBinding;
import lk.gov.health.hpb.api.Statistics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardFragment extends Fragment {

    private static final String HPB_API_URL = "https://hpb.health.gov.lk/api/get-current-statistical";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final long FAB_ANIMATING_DURATION = 1000;

    public DashboardFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentDashboardBinding binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dashboard, container, false
        );
        this.inflateViews(binding);
        this.updateStatistics(binding);
        return binding.getRoot();
    }

    private void updateStatistics(@NonNull final FragmentDashboardBinding binding) {
        this.showLoadingAnimations(binding);

        new OkHttpClient()
            .newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
            .newCall(new Request.Builder().url(HPB_API_URL).build())
            .enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    binding.setStatisticsData(new Gson().fromJson(
                        response.body().string(), Statistics.class
                    ).getData());
                    DashboardFragment.this.stopLoadingAnimations(binding);
                }
                @Override
                public void onFailure(Call call, IOException e) {
                    final String errorMessage = "Error retrieving data, Please check your internet connection";
                    runOnUiThread(() -> Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show());
                    if ((e.getLocalizedMessage() == null)) {
                        Log.e(LOG_TAG, errorMessage, e);
                    } else {
                        Log.e(LOG_TAG, errorMessage + " : " + e.getLocalizedMessage());
                    }
                    DashboardFragment.this.stopLoadingAnimations(binding);
                }
            });
    }

    private void inflateViews(@NonNull final FragmentDashboardBinding binding) {
        binding.fab.setOnClickListener(v -> {
            this.updateStatistics(binding);
        });
    }

    private void showLoadingAnimations(@NonNull final FragmentDashboardBinding binding) {
        final RotateAnimation rotateAnimation = new RotateAnimation(
                0, 359, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setDuration(FAB_ANIMATING_DURATION);
        binding.fab.startAnimation(rotateAnimation);
        binding.fab.setClickable(false);

        binding.statContainer.setVisibility(View.INVISIBLE);
        binding.placeholder.setVisibility(View.VISIBLE);

        ShimmerFrameLayout placeholder = binding.placeholder;
        placeholder.startShimmerAnimation();
    }

    private void stopLoadingAnimations(@NonNull final FragmentDashboardBinding binding) {
        runOnUiThread(() -> {
            binding.statContainer.setVisibility(View.VISIBLE);
            binding.placeholder.setVisibility(View.GONE);
        });

        if (binding.fab.getAnimation()!=null){
            binding.fab.getAnimation().setRepeatCount(0);
            new Handler(Looper.getMainLooper()).postDelayed(() -> runOnUiThread(() -> {
                binding.fab.clearAnimation();
                binding.fab.setClickable(true);
            }), FAB_ANIMATING_DURATION);
        }
    }

    private void runOnUiThread(Runnable action) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(action);
        }
    }
}
