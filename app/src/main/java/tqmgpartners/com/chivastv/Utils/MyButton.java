package tqmgpartners.com.chivastv.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by user on 20/02/2017.
 */

public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
        init();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/hvt_light.ttf");
        setTypeface(tf);
    }
}
