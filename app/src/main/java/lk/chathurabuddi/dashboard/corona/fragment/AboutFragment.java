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

import android.content.Intent;
import android.net.Uri;
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
import lk.chathurabuddi.dashboard.corona.util.DeviceInformation;

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

        view.findViewById(R.id.btn_report_bug).setOnClickListener(v -> {
            final String mailSubject = "[Bug Report] COVID19-SL";
            final String deviceInfo = DeviceInformation.extract();
            Intent intent = new Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:contact@chathurabuddi.lk?subject="+mailSubject+"&body="+deviceInfo)
            );
            intent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
            intent.putExtra(Intent.EXTRA_TEXT, deviceInfo);
            startActivity(Intent.createChooser(intent, "Choose an Email Client"));
        });

        ((TextView)view.findViewById(R.id.version_tag)).setText(BuildConfig.VERSION_NAME);
    }
}
