package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.DialogHelper;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PaymentDetailFragment extends BaseFragment {
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.txt_title)
    AnyTextView txtTitle;
    @BindView(R.id.txt_price)
    AnyTextView txtPrice;
    @BindView(R.id.txt_stylist)
    AnyTextView txtStylist;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.rb_Online_payment)
    RadioButton rbOnlinePayment;
    @BindView(R.id.rb_pay_after_service)
    RadioButton rbPayAfterService;
    @BindView(R.id.txt_price_summary)
    AnyTextView txtPriceSummary;
    @BindView(R.id.txt_price_total)
    AnyTextView txtPriceTotal;
    @BindView(R.id.bookNowButtonBtn)
    Button bookNowButtonBtn;
    @BindView(R.id.card_view_address)
    CardView cardViewAddress;
    Unbinder unbinder;

    public static PaymentDetailFragment newInstance() {
        Bundle args = new Bundle();

        PaymentDetailFragment fragment = new PaymentDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_payment_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getResString(R.string.payment_details));
        titleBar.showBackButton();
    }


    @OnClick(R.id.bookNowButtonBtn)
    public void onViewClicked() {
        DialogHelper dialoge=new DialogHelper(getDockActivity());
        dialoge.bookingDialoge(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(HomeFragment.newInstance(),"HomeFragment");
                dialoge.hideDialog();
            }
        });
        dialoge.showDialog();
        dialoge.setCancelable(false);
    }
}
