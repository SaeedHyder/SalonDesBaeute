package com.app.salonbeaute.fragments;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.DialogHelper;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.binders.TimingBinder;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;
import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class CalendarFragment extends BaseFragment implements RecyclerClickListner {
    @BindView(R.id.rv_timing)
    CustomRecyclerView rvTiming;
    @BindView(R.id.image)
    CircleImageView image;
    @BindView(R.id.btn_change)
    AnyTextView btnChange;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.proceedBtn)
    Button proceedBtn;
    Unbinder unbinder;
    @BindView(R.id.rb_current_address)
    RadioButton rbCurrentAddress;
    @BindView(R.id.rb_new_address)
    RadioButton rbNewAddress;
    @BindView(R.id.edt_comment)
    AnyEditTextView edtComment;
    @BindView(R.id.cancelButton)
    Button cancelButton;
    @BindView(R.id.saveButton)
    Button saveButton;
    @BindView(R.id.card_view_address)
    CardView cardViewAddress;
    @BindView(R.id.cb_address)
    CheckBox cbAddress;
    @BindView(R.id.txtAddressTitle)
    AnyTextView txtAddressTitle;
    @BindView(R.id.calendarView)
    CalendarView calendarView;

    private ArrayList<String> timingCollection;
    private int previousSelectedPos=0;


    public static CalendarFragment newInstance() {
        Bundle args = new Bundle();

        CalendarFragment fragment = new CalendarFragment();
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
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTiming();
        checkBoxListner();
        setAddressListner();
        setCalendarEvents();
    }

    private void setCalendarEvents() {
        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar));
        calendarView.setEvents(events);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

            }
        });

    }

    private void setAddressListner() {

        rbCurrentAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    txtAddressTitle.setText(getResString(R.string.current_address));
                    edtComment.setBackground(getDockActivity().getResources().getDrawable(R.drawable.address_selected_background));
                    edtComment.setText(getResString(R.string.lorem_small));
                    edtComment.setEnabled(false);
                }
            }
        });
        rbNewAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    txtAddressTitle.setText(getResString(R.string.add_new_address));
                    edtComment.setBackground(getDockActivity().getResources().getDrawable(R.drawable.address_unselected_background));
                    edtComment.setEnabled(true);
                    edtComment.getText().clear();
                    //  setEditTextFocus(edtComment);
                }
            }
        });
    }

    private void checkBoxListner() {

        cbAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    cardView.setVisibility(View.GONE);
                    cardViewAddress.setVisibility(View.VISIBLE);
                } else {
                    cardView.setVisibility(View.VISIBLE);
                    cardViewAddress.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setTiming() {

        timingCollection = new ArrayList<>();
        timingCollection.add("");
        timingCollection.add("");
        timingCollection.add("");
        timingCollection.add("");
        timingCollection.add("");
        timingCollection.add("");
        timingCollection.add("");
        rvTiming.BindRecyclerView(new TimingBinder(getDockActivity(), prefHelper, this, 0), timingCollection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.HORIZONTAL, false)
                , new DefaultItemAnimator());
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getResString(R.string.payment_details));

    }


    @Override
    public void onClick(Object entity, int position) {

       /* rvTiming.BindRecyclerView(new TimingBinder(getDockActivity(), prefHelper, this, position), timingCollection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.HORIZONTAL, false)
                , new DefaultItemAnimator());

        rvTiming.smoothScrollToPosition(position);*/

       rvTiming.notifyItemChanged(position);
       rvTiming.notifyItemChanged(previousSelectedPos);
       previousSelectedPos=position;


    }


    @OnClick({R.id.btn_change, R.id.proceedBtn, R.id.cancelButton, R.id.saveButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change:
                DialogHelper dialoge = new DialogHelper(getDockActivity());
                dialoge.changeUserDialoge(getDockActivity());
                dialoge.showDialog();
                break;
            case R.id.proceedBtn:
               getDockActivity().replaceDockableFragment(PaymentDetailFragment.newInstance(),"PaymentDetailFragment");
                break;
            case R.id.cancelButton:
                cardViewAddress.setVisibility(View.GONE);
                cardView.setVisibility(View.VISIBLE);
                break;
            case R.id.saveButton:
                cardViewAddress.setVisibility(View.GONE);
                cardView.setVisibility(View.VISIBLE);
                break;
        }
    }



}
