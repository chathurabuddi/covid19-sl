package lk.chathurabuddi.dashboard.corona;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import lk.chathurabuddi.dashboard.corona.databinding.ActivityMainBinding;
import lk.chathurabuddi.dashboard.corona.fragment.ScreenFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(
            MainActivity.this,
            R.layout.activity_main
        );
        this.inflateViews(binding);
        this.displayFragment(binding, ScreenFragment.DASHBOARD);
    }

    private void inflateViews(@NonNull final ActivityMainBinding binding) {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                binding.contentMain.setTranslationX(slideOffset * drawerView.getWidth());
                binding.drawerLayout.bringChildToFront(drawerView);
                binding.drawerLayout.requestLayout();
            }
        };
        binding.drawerLayout.addDrawerListener(drawerToggle);
        binding.drawerIcon.setOnClickListener(v -> binding.drawerLayout.openDrawer(binding.navView));

        this.setNabBarItemListeners(binding);
    }

    private void setNabBarItemListeners(@NonNull final ActivityMainBinding binding) {
        binding.menuItemDashboard.setOnClickListener(v -> {
            displayFragment(binding, ScreenFragment.DASHBOARD);
        });

        binding.menuItemAbout.setOnClickListener(v -> {
            displayFragment(binding, ScreenFragment.ABOUT);
        });
    }

    private void displayFragment(@NonNull final ActivityMainBinding binding, ScreenFragment screenFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(binding.navHost.getId());
        if (currentFragment == null || !screenFragment.name().equals(currentFragment.getTag())) {
            fragmentManager.beginTransaction().replace(
                    binding.navHost.getId(),
                    screenFragment.getInstance(),
                    screenFragment.name()
            ).commit();
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }
}
