package com.android.espermobiles.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addToComposite(composite: CompositeDisposable) {
    composite.add(this)
}