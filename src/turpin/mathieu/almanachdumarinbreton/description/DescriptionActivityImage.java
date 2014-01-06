package turpin.mathieu.almanachdumarinbreton.description;

import turpin.mathieu.almanachdumarinbreton.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

@SuppressLint("SetJavaScriptEnabled")
public class DescriptionActivityImage extends DescriptionActivity{

	private ViewFlipper viewFlipper;
	private float lastX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description_image);

		viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
	}

	// Method to handle touch event like left to right swap and right to left swap
	public boolean onTouchEvent(MotionEvent touchevent)
	{
		switch (touchevent.getAction())
		{
		// when user first touches the screen to swap
		case MotionEvent.ACTION_DOWN:
		{
			lastX = touchevent.getX();
			break;
		}
		case MotionEvent.ACTION_UP:
		{
			float currentX = touchevent.getX();

			// if left to right swipe on screen
			if (lastX < currentX)
			{
				// If no more View/Child to flip
				if (viewFlipper.getDisplayedChild() == 0)
					break;

				// set the required Animation type to ViewFlipper
				// The Next screen will come in form Left and current Screen will go OUT from Right
				viewFlipper.setInAnimation(this, R.anim.in_from_left);
				viewFlipper.setOutAnimation(this, R.anim.out_to_right);
				// Show the previous Screen
				viewFlipper.showPrevious();
			}

			// if right to left swipe on screen
			if (lastX > currentX)
			{
				if (viewFlipper.getDisplayedChild() == viewFlipper.getChildCount()-1)
					break;
				// set the required Animation type to ViewFlipper
				// The Next screen will come in form Right and current Screen will go OUT from Left
				viewFlipper.setInAnimation(this, R.anim.in_from_right);
				viewFlipper.setOutAnimation(this, R.anim.out_to_left);
				// Show The next Screen
				viewFlipper.showNext();
			}
			break;
		}
		}
		return false;
	}

}
