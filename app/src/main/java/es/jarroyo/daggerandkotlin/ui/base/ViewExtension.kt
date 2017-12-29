package es.jarroyo.daggerandkotlin.ui.base

import android.view.View
import android.widget.EditText

fun EditText.text(): String = this.text.toString()

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}