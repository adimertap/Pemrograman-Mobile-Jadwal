package com.example.donasi2.Edittextfragment;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.donasi2.R;

/**
 * Custom view that is an extension of EditText.
 * It provides a Clear ("x") button within the text field
 * that, when tapped, clears the text from the field.
 */

public class EditTextWithCaps
        extends android.support.v7.widget.AppCompatEditText {

    Drawable mCapsButtonImage;
    String input;

    public EditTextWithCaps(Context context) {
        super(context);
        init();
    }

    public EditTextWithCaps(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithCaps(Context context,
                            AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Initialize Drawable member variable.
        mCapsButtonImage =
                ResourcesCompat.getDrawable(getResources(),
                        R.drawable.ic_keyboard_capslock_opaque, null);

        // If the X (clear) button is tapped, clear the text.
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Use the getCompoundDrawables()[2] expression to check
                // if the drawable is on the "end" of text [2].
                if ((getCompoundDrawablesRelative()[2] != null)) {
                    float capsButtonStart; // Used for LTR languages
                    float capsButtonEnd;  // Used for RTL languages
                    boolean isCapsButtonClicked = false;
                    // Detect the touch in RTL or LTR layout direction.
                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        // If RTL, get the end of the button on the left side.
                        capsButtonEnd = mCapsButtonImage
                                .getIntrinsicWidth() + getPaddingStart();
                        // If the touch occurred before the end of the button,
                        // set isClearButtonClicked to true.
                        if (event.getX() < capsButtonEnd) {
                            isCapsButtonClicked = true;
                        }
                    } else {
                        // Layout is LTR.
                        // Get the start of the button on the right side.
                        capsButtonStart = (getWidth() - getPaddingEnd()
                                - mCapsButtonImage.getIntrinsicWidth());
                        // If the touch occurred after the start of the button,
                        // set isClearButtonClicked to true.
                        if (event.getX() > capsButtonStart) {
                            isCapsButtonClicked = true;
                        }
                    }
                    // Check for actions if the button is tapped.
                    if (isCapsButtonClicked) {
                        // Check for ACTION_DOWN (always occurs before ACTION_UP).
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            // Switch to the black version of clear button.
                            mCapsButtonImage =
                                    ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.ic_keyboard_capslock_black, null);
                            showCapsButton();
                        }
                        // Check for ACTION_UP.
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            // Switch to the opaque version of clear button.
                            mCapsButtonImage =
                                    ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.ic_keyboard_capslock_opaque, null);
                            // Clear the text and hide the clear button

                            input = String.valueOf(getText());
                            input = input.toUpperCase();
                            setText(input);
                            hideCapsButton();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });

        // If the text changes, show or hide the X (clear) button.
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after) {
                // Do nothing.
            }

            @Override
            public void onTextChanged(CharSequence s,
                                      int start, int before, int count) {
                showCapsButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing.
            }
        });
    }

    /**
     * Shows the clear (X) button.
     */

    private void showCapsButton() {
        // Sets the Drawables (if any) to appear to the left of,
        // above, to the right of, and below the text.
        setCompoundDrawablesRelativeWithIntrinsicBounds
                (null,                      // Start of text.
                        null,               // Top of text.
                        mCapsButtonImage,  // End of text.
                        null);              // Below text.
    }

    /**
     * Hides the clear button.
     */
    private void hideCapsButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds
                (null,             // Start of text.
                        null,      // Top of text.
                        null,      // End of text.
                        null);     // Below text.
    }
}