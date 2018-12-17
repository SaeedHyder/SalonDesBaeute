package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.app.salonbeaute.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WriteReviewFragment extends BaseFragment {
    @BindView(R.id.rbParlourRating)
    CustomRatingBar rbParlourRating;
    @BindView(R.id.txt_title)
    AnyEditTextView txtTitle;
    @BindView(R.id.txt_box)
    AnyEditTextView txtBox;
    @BindView(R.id.sumitButton)
    Button sumitButton;
    Unbinder unbinder;

    public static WriteReviewFragment newInstance() {
        Bundle args = new Bundle();

        WriteReviewFragment fragment = new WriteReviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_review, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listner();
    }

    private void listner() {
        txtBox.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.showBackButton();
        titleBar.setSubHeading(getResString(R.string.write_review));
    }

    private boolean isvalidated() {
        if (txtTitle.getText().toString().isEmpty()) {
            txtTitle.setError(getString(R.string.enter_title));
            if (txtTitle.requestFocus()) {
                setEditTextFocus(txtTitle);
            }
            return false;
        } else if (txtBox.getText() == null || txtBox.getText().toString().isEmpty()) {
            txtBox.setError(getString(R.string.enter_review));
            if (txtBox.requestFocus()) {
                setEditTextFocus(txtBox);
            }
            return false;
        } else
            return true;

    }

    @OnClick(R.id.sumitButton)
    public void onViewClicked() {
        if(isvalidated()){
            getDockActivity().popFragment();
        }
    }
}
