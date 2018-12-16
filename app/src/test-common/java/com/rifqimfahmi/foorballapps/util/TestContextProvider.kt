package com.rifqimfahmi.foorballapps.util

import com.rifqimfahmi.foorballapps.ContextProviders
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/*
 * Created by Rifqi Mulya Fahmi on 16/12/18.
 */

class TestContextProvider : ContextProviders() {
    override val Main: CoroutineContext
        get() = Dispatchers.Unconfined
    override val IO: CoroutineContext
        get() = Dispatchers.Unconfined
}