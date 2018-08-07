package com.pubbix.feature.home.profile.edit.picker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.pubbix.feature.home.profile.edit.EditProfileContract;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private EditProfileContract.Presenter presenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        return dialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year,
                          int monthOfYear, int dayOfMonth) {
        ++monthOfYear;
        final  String dayOfBirth = year + "-" + monthOfYear + "-" + dayOfMonth;
        presenter.updateBirthday(dayOfBirth);
    }

    public void setPresenter(EditProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
