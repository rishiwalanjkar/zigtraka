package custom.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

	public CustomTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		createFont();
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		createFont();
	}

	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		createFont();
	}

	public void createFont(){
		Typeface font=Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Light.ttf"); 
        setTypeface(font);
	}

}
