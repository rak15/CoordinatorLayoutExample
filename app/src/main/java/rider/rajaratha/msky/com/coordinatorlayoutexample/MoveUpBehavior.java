package rider.rajaratha.msky.com.coordinatorlayoutexample;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MoveUpBehavior extends CoordinatorLayout.Behavior<CircleImageView> {
    Context context;
    public MoveUpBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        Toast.makeText(context, "dependency view changed", Toast.LENGTH_SHORT).show();
        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        child.setTranslationY(translationY);
        return true;
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, CircleImageView child, View dependency) {
        Toast.makeText(context, "dependency view removed", Toast.LENGTH_SHORT).show();
        float translationY = dependency.getTranslationY();
        child.setTranslationY(translationY);
    }
}
