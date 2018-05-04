package bk.itc.html5.mylib.component.view.setting;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.angads25.toggle.LabeledSwitch;
import com.pixplicity.easyprefs.library.Prefs;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import org.angmarch.views.NiceSpinner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bk.itc.html5.mylib.R;
import bk.itc.html5.mylib.component.util.DimenUtil;
import bk.itc.html5.mylib.component.util.DrawableUtil;

/**
 * Created by Hien on 5/2/2018.
 */

public class SettingView extends LinearLayout {
    private int mBackgroundColor;
    private int mBackgroundRadius;

    private HashMap<String, LabeledSwitch> mSwitchHash = new HashMap<>();
    private HashMap<String, CheckBox> mCheckBoxHas = new HashMap<>();
    private HashMap<String, DiscreteSeekBar> mSeekBarHash = new HashMap<>();
    private HashMap<String, NiceSpinner> mDropHash = new HashMap<>();

    public static final String SETTING_VIEW_BASE_KEY = "my_base_key";
    public static final int SETTING_VIEW_NOT_SET = 0;
    public static final int SETTING_VIEW_ON = 1;
    public static final int SETTING_VIEW_OFF = -1;

    public static final int SETTING_VIEW_SLIDER_UNKNOWN = -1;
    public static final int SETTING_VIEW_DROP_NOT_SET = -1;

    public SettingView(Context context) {
        super(context);
        init();
    }

    public SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SettingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        setBackground(Color.WHITE, 3);
    }

    public void setBackground(int color, int radius) {
        mBackgroundColor = color;
        mBackgroundRadius = radius;
    }

    public void addSwitch(String title, final String key , final StateListener listener) {
        ViewGroup component = preInflate(title, false);

        final LabeledSwitch labeledSwitch = new LabeledSwitch(getContext());
        labeledSwitch.setColorOn(getContext().getResources().getColor(android.R.color.holo_green_dark));
        component.addView(labeledSwitch);
        mSwitchHash.put(SETTING_VIEW_BASE_KEY + key, labeledSwitch);

        int state = getState(key);
        if(state == SETTING_VIEW_NOT_SET) {
            setSwitch(key, false, false);
        } else {
            if(state == SETTING_VIEW_ON) {
                setSwitch(key, true, true);
            }
        }

        if(listener != null) {
            labeledSwitch.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!labeledSwitch.isOn()) {
                        putState(key, true);
                        listener.onStateChangeByUser(true);
                    }else {
                        putState(key, false);
                        listener.onStateChangeByUser(false);
                    }
                }
            });
        }
    }

    public void addCheckBox(String title, final String key, final StateListener listener) {
        ViewGroup component = preInflate(title, false);

        final CheckBox checkBox = new CheckBox(getContext());
        component.addView(checkBox);
        mCheckBoxHas.put(SETTING_VIEW_BASE_KEY + key, checkBox);

        int state = getState(key);
        if(state == SETTING_VIEW_NOT_SET) {
            setCheckBox(key, false, false);
        } else {
            if(state == SETTING_VIEW_ON) {
                setCheckBox(key, true, true);
            }
        }

        if(listener != null) {
            checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkBox.isChecked()) {
                        putState(key, true);
                        listener.onStateChangeByUser(true);
                    }else {
                        putState(key, false);
                        listener.onStateChangeByUser(false);
                    }
                }
            });
        }
    }

    public void addSlider(String title, final String key, int min, int max, int def, final SliderListener listener) {
        ViewGroup component = preInflate(title, true);

        DiscreteSeekBar seekBar = new DiscreteSeekBar(getContext());
        seekBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        seekBar.setMin(min);
        seekBar.setMax(max);
        component.addView(seekBar);
        component.setVisibility(VISIBLE);
        mSeekBarHash.put(SETTING_VIEW_BASE_KEY + key, seekBar);

        int value = getSliderValue(key);
        if(value == SETTING_VIEW_SLIDER_UNKNOWN) {
            setSlider(key, def, false);
        } else {
            setSlider(key, value, true);
        }

        if (listener != null) {
            seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
                @Override
                public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                    if(fromUser) {
                        putSliderValue(key, value);
                        listener.onSliderChange(value);
                    }
                }

                @Override
                public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

                }
            });
        }
    }

    public void addDropDown(String title, final String key, List<String> list, int def, final DropListener listener) {
        ViewGroup component = preInflate(title, false);

        NiceSpinner niceSpinner = new NiceSpinner(getContext());
        niceSpinner.attachDataSource(list);
        component.addView(niceSpinner);
        mDropHash.put(SETTING_VIEW_BASE_KEY + key, niceSpinner);

        int index = getDropIndex(key);
        if(index == SETTING_VIEW_DROP_NOT_SET) {
            setDrop(key, def, false);
        }else {
            setDrop(key, index, true);
        }

        if (listener != null) {
            niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    listener.onSelected(i);
                    putDropIndex(key, i);
                }
            });
        }


    }

    public void addButton(String title, String text, final ButtonListener listener) {
        View view = inflateItem();

        TextView textView = (TextView) view.findViewById(R.id.setting_item_title);
        ViewGroup component = (ViewGroup) view.findViewById(R.id.setting_item_right_component);
        View layout = view.findViewById(R.id.setting_item_layout);

        TextView button = new TextView(getContext());
        button.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText(text);
        button.setTextSize(16);
        button.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_orange_dark));
        button.setBackground(DrawableUtil.createBackground(getContext().getResources().getColor(android.R.color.holo_orange_dark), 5, 0, Color.WHITE));
        button.setTypeface(Typeface.DEFAULT_BOLD);
        button.setTextColor(Color.WHITE);
        int px = (int) DimenUtil.pxFromDp(16);
        button.setPadding(px, px/2, px, px/2);
        component.addView(button);

        textView.setText(title);

        layout.setBackground(DrawableUtil.createBackground(mBackgroundColor, mBackgroundRadius, 0, Color.WHITE));

        addView(view);

        if (listener != null) {
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClick();
                }
            });
        }
    }

    private View inflateItem() {
        View view = View.inflate(getContext(), R.layout.setting_item, null);
        return view;
    }

    public static int getState(String key) {
        return Prefs.getInt(SETTING_VIEW_BASE_KEY + key, 0);
    }

    private void putState(String key, boolean state) {
        if (state) {
            Prefs.putInt(SETTING_VIEW_BASE_KEY + key, SETTING_VIEW_ON);
        }else {
            Prefs.putInt(SETTING_VIEW_BASE_KEY + key, SETTING_VIEW_OFF);
        }
    }

    public static int getSliderValue(String key) {
        return Prefs.getInt(SETTING_VIEW_BASE_KEY + key, SETTING_VIEW_SLIDER_UNKNOWN);
    }

    private void putSliderValue(String key, int value) {
        Prefs.putInt(SETTING_VIEW_BASE_KEY + key, value);
    }

    public static int getDropIndex(String key) {
        return Prefs.getInt(SETTING_VIEW_BASE_KEY + key, SETTING_VIEW_DROP_NOT_SET);
    }

    private void putDropIndex(String key, int index) {
        Prefs.putInt(SETTING_VIEW_BASE_KEY + key, index);
    }

    public boolean setSwitch(String key, boolean state, boolean isSilent) {
        Iterator it = mSwitchHash.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, LabeledSwitch> pair = (Map.Entry<String, LabeledSwitch>)it.next();

            if(pair.getKey().equals(SETTING_VIEW_BASE_KEY + key)) {
                pair.getValue().setOn(state);
                if(!isSilent) {
                    putState(key, state);
                }
                return true;
            }

            it.remove(); // avoids a ConcurrentModificationException
        }

        return false;
    }

    public boolean setCheckBox(String key, boolean state, boolean isSilent) {
        Iterator it = mCheckBoxHas.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, CheckBox> pair = (Map.Entry<String, CheckBox>)it.next();

            if(pair.getKey().equals(SETTING_VIEW_BASE_KEY + key)) {
                pair.getValue().setChecked(state);
                if(!isSilent) {
                    putState(key, state);
                }
                return true;
            }


            it.remove(); // avoids a ConcurrentModificationException
        }

        return false;
    }

    public boolean setSlider(String key, int value, boolean isSilent) {
        Iterator it = mSeekBarHash.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, DiscreteSeekBar> pair = (Map.Entry<String, DiscreteSeekBar>)it.next();

            if(pair.getKey().equals(SETTING_VIEW_BASE_KEY + key)) {
                pair.getValue().setProgress(value);
                if(!isSilent) {
                    putSliderValue(key, value);
                }
                return true;
            }

            it.remove(); // avoids a ConcurrentModificationException
        }

        return false;
    }

    public boolean setDrop(String key, int index, boolean isSilent) {
        Iterator it = mDropHash.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, NiceSpinner> pair = (Map.Entry<String, NiceSpinner>)it.next();

            if(pair.getKey().equals(SETTING_VIEW_BASE_KEY + key)) {
                pair.getValue().setSelectedIndex(index);
                if(!isSilent) {
                    putSliderValue(key, index);
                }
                return true;
            }

            it.remove(); // avoids a ConcurrentModificationException
        }

        return false;
    }

    private ViewGroup preInflate(String title, boolean isBottomComponent) {
        View view = inflateItem();

        TextView textView = (TextView) view.findViewById(R.id.setting_item_title);
        View layout = view.findViewById(R.id.setting_item_layout);
        ViewGroup component;

        if(isBottomComponent) {
            component = (ViewGroup) view.findViewById(R.id.setting_item_bottom_component);
        }else {
            component = (ViewGroup) view.findViewById(R.id.setting_item_right_component);
        }

        textView.setText(title);
        layout.setBackground(DrawableUtil.createBackground(mBackgroundColor, mBackgroundRadius, 0, Color.WHITE));

        addView(view);

        return component;
    }

    public interface StateListener {
        void onStateChangeByUser(boolean state);
    }

    public interface ButtonListener {
        void onButtonClick();
    }

    public interface SliderListener {
        void onSliderChange(int value);
    }

    public interface DropListener {
        void onSelected(int index);
    }
}
