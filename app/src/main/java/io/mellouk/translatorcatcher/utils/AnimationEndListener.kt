package io.mellouk.translatorcatcher.utils

import android.animation.Animator

abstract class AnimationEndListener : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {
        //NO-OP
    }

    override fun onAnimationCancel(animation: Animator?) {
        //NO-OP
    }

    override fun onAnimationStart(animation: Animator?) {
        //NO-OP
    }
}