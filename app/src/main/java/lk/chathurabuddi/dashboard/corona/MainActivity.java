package lk.chathurabuddi.dashboard.corona;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import lk.chathurabuddi.dashboard.corona.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(
            MainActivity.this,
            R.layout.activity_main
        );
        this.inflateViews(binding);
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

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navView, navController);
        this.setNabBarItemListeners(binding);
    }

    private void setNabBarItemListeners(@NonNull final ActivityMainBinding binding) {
        binding.menuItemDashboard.setOnClickListener(v -> {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.navigation_dashboard);
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        });

        binding.menuItemAbout.setOnClickListener(v -> {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.navigation_about);
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        });
    }
}
