package lk.chathurabuddi.dashboard.corona.fragment;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chathura Buddhika
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import android.content.Context;
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
import androidx.fragment.app.FragmentManager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lk.chathurabuddi.dashboard.corona.R;
import lk.chathurabuddi.dashboard.corona.databinding.FragmentDashboardBinding;
import lk.gov.health.hpb.api.Statistics;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardFragment extends Fragment {

    private static final String HPB_API_URL = "https://hpb.health.gov.lk/api/get-current-statistical";
    private static final String LOG_TAG = DashboardFragment.class.getSimpleName();
    private static final long   FAB_ANIMATING_DURATION = 1000;
    private static final long   HTTP_CONNECT_TIMEOUT = 30;
    private static final long   HTTP_READ_TIMEOUT = 30;
    private static final int    HTTP_CACHE_MAX_AGE = 10;
    private static final String HTTP_CACHE_DIR = "http-cache";
    private static final int    HTTP_CACHE_DIR_SIZE = 2 * 1024 * 1024;

    private Context APP_CONTEXT;

    public DashboardFragment() { }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        APP_CONTEXT = context.getApplicationContext();
    }

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
            .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(chain -> {
                Response response = chain.proceed(chain.request());
                return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header(
                    "Cache-Control",
                        new CacheControl
                            .Builder().maxAge(HTTP_CACHE_MAX_AGE, TimeUnit.MINUTES)
                            .build().toString())
                    .build();
            })
            .cache(new Cache(
                new File(APP_CONTEXT.getCacheDir(), HTTP_CACHE_DIR),
                HTTP_CACHE_DIR_SIZE
            ))
            .build()
            .newCall(new Request.Builder().url(HPB_API_URL).build())
            .enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.body() != null) {
                        binding.setStatisticsData(new Gson().fromJson(
                            response.body().string(), Statistics.class
                        ).getData());
                    }
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

        binding.btnSymptoms.setOnClickListener(v -> {
            displayFragment(ScreenFragment.SYMPTOMS);
        });

        binding.btnPrevention.setOnClickListener(v -> {
            displayFragment(ScreenFragment.PREVENTION);
        });
    }

    private void displayFragment(ScreenFragment screenFragment) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in_left, R.anim.slide_out_right
            ).replace(
                R.id.nav_host,
                screenFragment.getInstance(),
                screenFragment.name()
            ).commit();
        }
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
