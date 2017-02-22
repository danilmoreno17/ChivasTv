package tqmgpartners.com.chivastv.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user on 20/02/2017.
 */

public class MyTextview extends TextView {

    public MyTextview(Context context) {
        super(context);
        init();
    }

    public MyTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/hvt_light.ttf");
        setTypeface(tf);
    }
}
