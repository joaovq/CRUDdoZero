package com.queapps.cruddozero.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

fun AppCompatActivity.hideKeyboard(){
    val view  = this.currentFocus
    if (view!=null){
        val imn = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imn.hideSoftInputFromWindow(view.windowToken, 0)
    }
   window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

}

// Uma outra forma de fazer o viewBindign com o lazy
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }