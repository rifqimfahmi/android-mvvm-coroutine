package com.rifqimfahmi.foorballapps.util

import org.mockito.Mockito

/*
 * Created by Rifqi Mulya Fahmi on 03/12/18.
 */
 
inline fun <reified T> mock(): T = Mockito.mock(T::class.java)