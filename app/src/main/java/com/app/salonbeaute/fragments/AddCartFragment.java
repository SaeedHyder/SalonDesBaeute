package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.DialogHelper;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.binders.AddCartBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddCartFragment extends BaseFragment implements RecyclerClickListner {
    @BindView(R.id.rv_addCart)
    CustomRecyclerView rvAddCart;
    Unbinder unbinder;
    @BindView(R.id.bottomCart)
    ImageView bottomCart;
    @BindView(R.id.txt_salon_title)
    AnyTextView txtSalonTitle;
    @BindView(R.id.txt_price)
    AnyTextView txtPrice;
    @BindView(R.id.bookNowButton)
    Button bookNowButton;

    private ArrayList<String> collection;

    public static AddCartFragment newInstance() {
        Bundle args = new Bundle();

        AddCartFragment fragment = new AddCartFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();
    }

    private void setData() {
        collection = new ArrayList<>();
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");

        rvAddCart.BindRecyclerView(new AddCartBinder(getDockActivity(), prefHelper, this), collection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getResString(R.string.salon_des_beaute));
        titleBar.showShareButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHelper dialoge=new DialogHelper(getDockActivity());
                dialoge.shareDialoge(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));

                    }
                });
                dialoge.showDialog();
            }
        });
        titleBar.showCartButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDockActivity().replaceDockableFragment(PaymentDetailFragment.newInstance(),"PaymentDetailFragment");
            }
        });
    }


    @Override
    public void onClick(Object entity, int position) {

    }


    @OnClick(R.id.bookNowButton)
    public void onViewClicked() {
       getDockActivity().replaceDockableFragment(CalendarFragment.newInstance(),"CalendarFragment",true);
    }
}
