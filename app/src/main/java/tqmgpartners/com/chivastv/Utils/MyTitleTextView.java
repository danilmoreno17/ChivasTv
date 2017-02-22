package tqmgpartners.com.chivastv.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user on 21/02/2017.
 */

public class MyTitleTextView extends TextView {
    public MyTitleTextView(Context context) {
        super(context);
    }

    public MyTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/hvt_bold.ttf");
        setTypeface(tf);
    }
}
