package com.pubbix.feature.home.profile.viewmodel;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.pubbix.R;
import com.pubbix.feature.common.Enums;
import com.pubbix.feature.home.profile.datamodel.GenericEditDataModel;
import com.wayfair.brickkit.brick.ViewModel;

public class GenericEditViewModel extends ViewModel<GenericEditDataModel> {
    private Interactions interactions;
    private Enums.EditTextType type;
    public final ObservableField<String> edit = new ObservableField<>();
    public final ObservableField<String> save = new ObservableField<>();
    public final ObservableField<String> hint = new ObservableField<>();
    public final ObservableField<String> genderText = new ObservableField<>();
    public final ObservableBoolean man = new ObservableBoolean();
    public final ObservableBoolean woman = new ObservableBoolean();
    public final ObservableInt counter = new ObservableInt();
    private int genderTracker = 0;

    public GenericEditViewModel(@Nullable GenericEditDataModel dataModel, Enums.EditTextType type, Interactions interactions) {
        super(dataModel);
        this.interactions = interactions;
        this.type = type;
        if (dataModel != null) {
            hint.set(getHint(type));
            counter.set(getCounterMax(type));
            edit.set(dataModel.value);
            save.set("Save");

            //To be improved in the future
            final boolean isMan = isMan(dataModel.value);
            final boolean isWoman = isWoman(dataModel.value);
            genderText.set(getGenderText(isMan));
            man.set(isMan);
            woman.set(isWoman);
            setGenderTracker(isMan, isWoman);
        }
    }

    public Enums.EditTextType getType() {
        return type;
    }

    @BindingAdapter({"android:inputType"})
    public static void setInputType(EditText view, Enums.EditTextType type) {
        switch (type) {
            case NAME:
                view.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case EMAIL:
                view.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case BIOGRAPHY:
                view.setSingleLine(false);
                view.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
                view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                break;
            case IDENTITY:
                view.setSingleLine(false);
                view.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
                view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                break;
        }
    }

    @Bindable
    public View.OnClickListener getSaveButtonClicked() {
        View.OnClickListener onSave = null;
        switch (type) {
            case NAME:
                onSave = view -> interactions.onUpdateFullName(edit.get());
                break;
            case EMAIL:
                onSave = view -> interactions.onUpdateEmail(edit.get());
                break;
            case PHONE:
                onSave = view -> interactions.onUpdatePhoneNumber(edit.get());
                break;
            case BIOGRAPHY:
                onSave = view -> interactions.onUpdateBioDescription(edit.get());
                break;
            case GENDER:
                onSave = view -> interactions.onUpdateGender(genderTracker);
                break;
            case IDENTITY:
                onSave = view -> interactions.onUpdateFoundation(edit.get());
                break;
        }
        return onSave;
    }

    public void onSplitTypeChanged(RadioGroup radioGroup, int id) {
        if (id == R.id.man) {
            genderText.set("a man");
            genderTracker = 0;
        } else if (id == R.id.woman) {
            genderText.set("a woman");
            genderTracker = 1;
        }
    }

    private String getHint(Enums.EditTextType type) {
        String hint = "";
        switch (type) {
            case NAME:
                hint = "Name";
                break;
            case EMAIL:
                hint = "New Email (email@domain.com)";
                break;
            case BIOGRAPHY:
                hint = "Add a biography that describes who you are. Share what you are interested in" +
                        "or a fun fact about you";
                break;
            case IDENTITY:
                hint = "Describe your company";
                break;
        }
        return hint;
    }

    private int getCounterMax(Enums.EditTextType type) {
        int counter = 50;
        switch (type) {
            case NAME:
                counter = 50;
                break;
            case BIOGRAPHY:
                counter = 150;
                break;
            case IDENTITY:
                counter = 150;
                break;
        }
        return counter;
    }

    private boolean isMan(String gender) {
        return gender.equalsIgnoreCase("man");
    }

    private boolean isWoman(String gender) {
        return gender.equalsIgnoreCase("woman");
    }

    private String getGenderText(boolean isMan) {
        return isMan ? "a man" : "a woman";
    }

    private void setGenderTracker(boolean isMan, boolean isWoman) {
        if (isMan) {
            genderTracker = 0;
        } else if (isWoman) {
            genderTracker = 1;
        }
    }

    public interface Interactions {

        void onUpdateFullName(String newValue);

        void onUpdateEmail(String newValue);

        void onUpdatePhoneNumber(String newValue);

        void onUpdateFoundation(String newValue);

        void onUpdateBioDescription(String newValue);

        void onUpdateGender(int gender);
    }
}
