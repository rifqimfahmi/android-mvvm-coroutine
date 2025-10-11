package com.rifqimfahmi.foorballapps.testing

/*
 * Created by Rifqi Mulya Fahmi on 05/12/18.
 */
 
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class OpenClass

@OpenClass
@Target(AnnotationTarget.CLASS)
annotation class OpenForTesting