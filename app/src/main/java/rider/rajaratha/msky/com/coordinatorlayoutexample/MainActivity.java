package rider.rajaratha.msky.com.coordinatorlayoutexample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private Button button;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        view = findViewById(R.id.tv11);
        button = findViewById(R.id.button_show_snack);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar code is like s of Toast (instead of context we give rootView i.e.: coordinatorLayout)
                Snackbar.make(coordinatorLayout, "Hello from SnackBar", Snackbar.LENGTH_INDEFINITE).setAction("Next Activity", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    }
                }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();
            }
        });
    }

    public void onSnackBarClicked(final View mView) {
        // get the center for the clipping circle
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;
        float radius = (float) Math.hypot(cx, cy);
        if (view.getVisibility() == View.INVISIBLE) {

// create the animator for this view (the start radius is zero)
            Animator anim = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, radius);
                anim.setDuration(2000);
            }

// make the view visible and start the animation
            view.setVisibility(View.VISIBLE);
            if (anim != null) {
                anim.start();
            }
        }else {
            // create the animator for this view (the start radius is zero)
            Animator anim = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, radius, 0);
            }

            // make the view invisible when the animation is done
            if (anim != null) {
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.INVISIBLE);
                    }
                });

                anim.start();
            }

        }
    }
}
