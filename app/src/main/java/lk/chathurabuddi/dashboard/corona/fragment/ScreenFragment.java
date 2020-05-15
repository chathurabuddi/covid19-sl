package lk.chathurabuddi.dashboard.corona.fragment;

import androidx.fragment.app.Fragment;

public enum ScreenFragment {

    DASHBOARD{
        @Override
        public Fragment getInstance() {
            return new DashboardFragment();
        }
    },
    ABOUT{
        @Override
        public Fragment getInstance() {
            return new AboutFragment();
        }
    };

    public abstract Fragment getInstance();
}
