package bjfu.it.mukaikai.weather.utils;

import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

public class LeakAct {
    private static LeakAct singleton;

    private LeakAct() {
    }

    public static LeakAct getInstance() {
        if (singleton == null) {
            singleton = new LeakAct();
        }
        return singleton;
    }

    /**
     * 单例造成的内存泄露
     */
    private TextView mTextViewLeak = null;
    public void setTextViewLeak(TextView textView) {
        mTextViewLeak = textView;
    }

    // fix
    private WeakReference<TextView> mTextView = null;
    public void setTextView(TextView textView) {
        mTextView = new WeakReference<TextView>(textView);
    }
}
