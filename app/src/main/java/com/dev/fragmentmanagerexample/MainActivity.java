package com.dev.fragmentmanagerexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addFrag1, removeFrag1, addFrag2, removeFrag2;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private String FARGMENT1_TAG = "Fragment1", FARGMENT2_TAG = "Fragment2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListenrs();
        initFrag();
    }

    private void initViews() {
        addFrag1 = findViewById(R.id.addFragment1);
        removeFrag1 = findViewById(R.id.removeFragment1);
        addFrag2 = findViewById(R.id.addFragment2);
        removeFrag2 = findViewById(R.id.removeFragment2);
    }

    private void setListenrs() {
        addFrag1.setOnClickListener(this);
        removeFrag1.setOnClickListener(this);
        addFrag2.setOnClickListener(this);
        removeFrag2.setOnClickListener(this);
    }

    private void initFrag() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addFragment1:
                addFragment(1, fragment1);
                break;
            case R.id.removeFragment1:
                removeFragment(1, fragment1);
                break;
            case R.id.addFragment2:
                addFragment(2, fragment2);
                break;
            case R.id.removeFragment2:
                removeFragment(2, fragment2);
                break;
            default: {

            }
        }
    }

    private String getFragmentTag(int frag) {
        return frag == 1 ? FARGMENT1_TAG : FARGMENT2_TAG;
    }

    private String getOppositeFragTag(int frag) {
        return frag == 1 ? FARGMENT2_TAG : FARGMENT1_TAG;
    }

    private void addFragment(int frag, Fragment fragment) {
        if (fragmentManager.findFragmentByTag(getFragmentTag(frag)) != null && fragmentManager.findFragmentByTag(getFragmentTag(frag)).isVisible())
            Toast.makeText(this, "Fragment " + frag + " deja affiché !", Toast.LENGTH_SHORT).show();
        else if (fragmentManager.findFragmentByTag(getOppositeFragTag(frag)) != null && fragmentManager.findFragmentByTag(getOppositeFragTag(frag)).isVisible()) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_layout, fragment, getFragmentTag(frag));
            fragmentTransaction.commit();
        } else {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_layout, fragment, getFragmentTag(frag));
            fragmentTransaction.commit();
        }
    }

    private void removeFragment(int frag, Fragment fragment) {
        if (fragmentManager.findFragmentByTag(getFragmentTag(frag)) == null || !fragmentManager.findFragmentByTag(getFragmentTag(frag)).isVisible())
            Toast.makeText(this, "Fragment " + frag + " n'est pas attahcé !", Toast.LENGTH_SHORT).show();
        else {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
    }
}
