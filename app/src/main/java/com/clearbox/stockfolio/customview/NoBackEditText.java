package com.clearbox.stockfolio.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class NoBackEditText extends androidx.appcompat.widget.AppCompatEditText {

    Activity mActivty;

    public NoBackEditText(Context context) {
        super(context);
        mActivty = (Activity) context;
        setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    //Stop from exiting
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
    }

    public NoBackEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mActivty = (Activity) context;
        setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    //Stop from exiting
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
    }

    public NoBackEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mActivty = (Activity) context;
        setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    //Stop from exiting
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
    }

    /**
     * Overrides the handling of the back key to move back to the
     * previous sources or dismiss the search dialog, instead of
     * dismissing the input method.
     */
    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (mActivty != null && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            KeyEvent.DispatcherState state = getKeyDispatcherState();
            if (state != null) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getRepeatCount() == 0) {
                    state.startTracking(event, this);
                    return true;
                } else if (event.getAction() == KeyEvent.ACTION_UP
                        && !event.isCanceled() && state.isTracking(event)) {
                    mActivty.onBackPressed();
                    return true;
                }
            }
        }
        return super.dispatchKeyEventPreIme(event);
    }
}
