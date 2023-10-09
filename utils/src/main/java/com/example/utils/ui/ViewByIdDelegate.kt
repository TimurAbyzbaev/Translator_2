package com.example.utils.ui

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class ViewByIdDelegate<out T : View>(private val rootGetter: () -> View?, private val viewId: Int) {
    private var rootRef: WeakReference<View>? = null

    private var viewRef: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var view = viewRef
        val cachedRoot = rootRef?.get()
        //Получаем root
        val currentRoot = rootGetter()

        if(currentRoot != cachedRoot || view == null) {
            if(currentRoot == null) {
                if (view != null){
                    //Failsafe, возвращать хотя бы последнюю view
                    return view
                }
                throw IllegalStateException("Cannot get View, there is no root yet")
            }
            //создаем view
            view = currentRoot.findViewById(viewId)
            //сохраняем ссылку на View чтобы не создавать ее каждый раз заново
            viewRef = view
            //Сохраняем ссылку на root чтобы не искать его каждый раз
            rootRef = WeakReference(currentRoot)
        }
        checkNotNull(view) { "View with id \"$viewId\" not found on root" }
        return view
    }

    fun <T : View> Activity.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
        //возвращаем корневую view
        return ViewByIdDelegate({window.decorView.findViewById(android.R.id.content)}, viewId)
    }

    fun <T : View> Fragment.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
        return ViewByIdDelegate({view}, viewId)
    }
}

