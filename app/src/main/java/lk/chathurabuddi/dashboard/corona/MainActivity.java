package lk.chathurabuddi.dashboard.corona;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lk.chathurabuddi.dashboard.corona.databinding.ActivityMainBinding;
import lk.gov.health.hpb.api.Statistics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String HPB_API_URL = "https://hpb.health.gov.lk/api/get-current-statistical";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final long FAB_ANIMATING_DURATION = 1000;
    private static final long SWIPE_REFRESH_ANIMATING_TIME = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        inflateNavigationDrawer(binding);
        inflateFab(binding);

        updateStatistics(binding);
    }

    private void updateStatistics(final ActivityMainBinding binding) {
        showLoadingAnimations(binding);
        new OkHttpClient()
            .newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
            .newCall(new Request.Builder().url(HPB_API_URL).build())
            .enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    final String errorMessage = "Error retrieving data, Please check your internet connection";
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show());
                    if ((e.getLocalizedMessage() == null)) {
                        Log.e(LOG_TAG, errorMessage, e);
                    } else {
                        Log.e(LOG_TAG, errorMessage + " : " + e.getLocalizedMessage());
                    }
                    stopLoadingAnimations(binding);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    binding.setStatisticsData(new Gson().fromJson(
                        response.body().string(), Statistics.class
                    ).getData());
                    stopLoadingAnimations(binding);
                }
            }
        );
    }

    private void inflateNavigationDrawer(final ActivityMainBinding binding) {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, null, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                binding.contentMain.setTranslationX(slideOffset * drawerView.getWidth());
                binding.drawerLayout.bringChildToFront(drawerView);
                binding.drawerLayout.requestLayout();
            }
        };
        binding.drawerLayout.addDrawerListener(drawerToggle);
        binding.titleContainer.setOnClickListener(v -> binding.drawerLayout.openDrawer(binding.navView));
    }

    private void inflateFab(ActivityMainBinding binding) {
        binding.fab.setOnClickListener(v -> {
            this.updateStatistics(binding);
        });
    }

    private void stopLoadingAnimations(ActivityMainBinding binding) {
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

    private void showLoadingAnimations(ActivityMainBinding binding) {
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
}
