package com.example.animationsdemo

import android.animation.*
import android.animation.Animator.AnimatorListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AnimatorListener {

    private var rotateAnimator: Animator? = null
    private var scaleAnimator: Animator? = null
    private var translateAnimator: Animator? = null
    private var fadeAnimator: ObjectAnimator? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun rotateAnimation(view: View) {

        rotateAnimator = AnimatorInflater.loadAnimator(this, R.animator.rotate)
        rotateAnimator?.apply {
            setTarget(targetImage)
            addListener(this@MainActivity)
            start()
        }
    }

    fun scaleAnimation(view: View) {

        scaleAnimator = AnimatorInflater.loadAnimator(this, R.animator.scale)
        scaleAnimator?.apply {
            setTarget(targetImage)
            addListener(this@MainActivity)
            start()
        }
    }

    fun translateAnimation(view: View) {

        translateAnimator = AnimatorInflater.loadAnimator(this, R.animator.translate)
        translateAnimator?.apply {
            setTarget(targetImage)
            addListener(this@MainActivity)
            start()
        }
    }

    fun fadeAnimation(view: View) {

        fadeAnimator = ObjectAnimator.ofFloat(targetImage, "alpha", 1.0f, 0.0f)
        fadeAnimator?.apply {
            duration = 1500
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }

    fun viewPropertyAnimator(view: View) {
        val vpa = targetImage.animate()
        vpa.apply {
            duration = 1000
            rotationX(360.0f)
            scaleX(1.5f)
            scaleY(1.5f)
            translationX(200.0f)
            alpha(0.5f)
            interpolator = OvershootInterpolator()
            start()
        }
    }

    fun propertyValuesHolder(view: View) {
        val rotX = PropertyValuesHolder.ofFloat("rotationX", 360f)
        val scaX = PropertyValuesHolder.ofFloat("scaleX", 1.5f)
        val scaY = PropertyValuesHolder.ofFloat("scaleY", 1.5f)

        val objA = ObjectAnimator.ofPropertyValuesHolder(targetImage, rotX, scaX, scaY)
        objA.apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            start()
        }
    }

    override fun onAnimationRepeat(animation: Animator?) {
        Toast.makeText(this, "Animation repeated", Toast.LENGTH_LONG).show()
    }

    override fun onAnimationEnd(animation: Animator?) {
        Toast.makeText(this, "Animation ended", Toast.LENGTH_LONG).show()
    }

    override fun onAnimationCancel(animation: Animator?) {
        Toast.makeText(this, "Animation cancelled", Toast.LENGTH_LONG).show()
    }

    override fun onAnimationStart(animation: Animator?) {
        if (animation == rotateAnimator)
            Toast.makeText(this, "Rotate Animation started", Toast.LENGTH_LONG).show()

        if (animation == scaleAnimator)
            Toast.makeText(this, "Scale Animation started", Toast.LENGTH_LONG).show()

        if (animation == translateAnimator)
            Toast.makeText(this, "Translate Animation started", Toast.LENGTH_LONG).show()

        if (animation == fadeAnimator)
            Toast.makeText(this, "Fade Animation started", Toast.LENGTH_LONG).show()
    }

}
