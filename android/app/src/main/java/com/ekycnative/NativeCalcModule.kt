package com.ekycnative

import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.vnptit.idg.sdk.activity.VnptIdentityActivity
import com.vnptit.idg.sdk.utils.KeyIntentConstants
import com.vnptit.idg.sdk.utils.SDKEnum

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

    override fun startEkyc(
        accessToken: String?,
        tokenId: String?,
        tokenKey: String?,
        promise: Promise?
    ) {
        val currentActivity: Activity? = currentActivity
        if (currentActivity == null) {
            promise?.reject("Activity Null", "Không tìm thấy Activity hiện tại")
            return
        }
        val intent = Intent(currentActivity, VnptIdentityActivity::class.java)
        intent.putExtra(KeyIntentConstants.ACCESS_TOKEN, accessToken);
        intent.putExtra(KeyIntentConstants.TOKEN_ID, tokenId);
        intent.putExtra(KeyIntentConstants.TOKEN_KEY, tokenKey);
        intent.putExtra(KeyIntentConstants.DOCUMENT_TYPE, SDKEnum. DocumentTypeEnum. IDENTITY_CARD.value);
        intent.putExtra(KeyIntentConstants.VERSION_SDK, SDKEnum.VersionSDKEnum.STANDARD.value);
        intent.putExtra(KeyIntentConstants.IS_SHOW_TUTORIAL, true);
        intent.putExtra(KeyIntentConstants.CHECK_LIVENESS_FACE, SDKEnum.ModeCheckLiveNessFace.iBETA.value);
        intent.putExtra(KeyIntentConstants.IS_CHECK_MASKED_FACE, true)
        intent.putExtra(KeyIntentConstants.IS_CHECK_LIVENESS_CARD, true)
        intent.putExtra(KeyIntentConstants.LOGO, "")
        intent.putExtra(KeyIntentConstants.IS_VALIDATE_POSTCODE, true)
        intent.putExtra(KeyIntentConstants.LANGUAGE_SDK, SDKEnum.LanguageEnum.VIETNAMESE.value)
        intent.putExtra(KeyIntentConstants.IS_ENABLE_SCAN_QRCODE, true)
        intent.putExtra(KeyIntentConstants.VALIDATE_DOCUMENT_TYPE, SDKEnum.ValidateDocumentType.Basic.value)
        intent.putExtra(KeyIntentConstants.IS_ENABLE_GOT_IT, true)
//        intent.putExtra(KeyIntentConstants.CHALLENGE_CODE,"<challenge_code truyền vào>");
        currentActivity.startActivityForResult(intent, 1)
    }

    companion object {
        const val NAME = "NativeCalc"
    }
}