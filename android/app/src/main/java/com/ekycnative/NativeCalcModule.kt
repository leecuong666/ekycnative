package com.ekycnative

import com.facebook.react.bridge.ReactApplicationContext

class NativeCalcModule(reactContext: ReactApplicationContext): NativeCalcSpec(reactContext){
    override fun getName() = NAME

    override fun add(val1: Double, val2: Double): Double {
        return  val1 + val2
    }

    override fun except(val1: Double, val2: Double): Double {
        return  val1 - val2
    }

    override fun multiply(val1: Double, val2: Double): Double {
        return  val1 * val2
    }

    override fun divide(val1: Double, val2: Double): Double {
        return  val1 / val2
    }

    companion object {
        const val NAME = "NativeCalc"
    }
}