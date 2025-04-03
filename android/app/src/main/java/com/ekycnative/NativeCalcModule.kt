package com.ekycnative

import android.R.attr.data
import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.vnptit.idg.sdk.activity.VnptIdentityActivity
import com.vnptit.idg.sdk.utils.KeyIntentConstants
import com.vnptit.idg.sdk.utils.KeyResultConstants
import com.vnptit.idg.sdk.utils.SDKEnum
import android.util.Log

class NativeCalcModule(reactContext: ReactApplicationContext): NativeCalcSpec(reactContext){
    override fun getName() = NAME
    private  var RequestCode = 1
    private var mPromise: Promise? = null

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
        mPromise = promise
        val currentActivity: Activity? = currentActivity
        if (currentActivity == null) {
            mPromise?.reject("Activity Null", "Không tìm thấy Activity hiện tại")
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
        currentActivity.startActivityForResult(intent, RequestCode)
    }

    init {
        reactContext.addActivityEventListener(object: com.facebook.react.bridge.ActivityEventListener {
            override fun onActivityResult(p0: Activity?, requestCode: Int, resultCode: Int, intent: Intent?) {
                if (requestCode == RequestCode){
                    if(resultCode == Activity.RESULT_OK){
//                        val resultMap = Arguments.createMap()
//                        resultMap.putString("strNetworkProblem", intent?.getStringExtra(KeyResultConstants.NETWORK_PROBLEM))
//                        resultMap.putString("strDataInfo", intent?.getStringExtra(KeyResultConstants.OCR_RESULT))
//                        resultMap.putString("strDataCompare", intent?.getStringExtra(KeyResultConstants.COMPARE_FACE_RESULT))
//                        resultMap.putString("strDataLiveness", intent?.getStringExtra(KeyResultConstants.LIVENESS_FACE_RESULT))
//                        resultMap.putString("imageFront", intent?.getStringExtra(KeyResultConstants.PATH_IMAGE_FRONT_FULL))
//                        resultMap.putString("imageRear", intent?.getStringExtra(KeyResultConstants.PATH_IMAGE_BACK_FULL))
//                        resultMap.putString("imagePortrait", intent?.getStringExtra(KeyResultConstants.PATH_IMAGE_FACE_FULL))
//                        resultMap.putString("imagePortraitFar", intent?.getStringExtra(KeyResultConstants.PATH_IMAGE_FACE_FAR_FULL))
//                        resultMap.putString("scan3DObject", intent?.getStringExtra(KeyResultConstants.PATH_FACE_SCAN3D))
//                        resultMap.putString("strLivenessCardFront", intent?.getStringExtra(KeyResultConstants.LIVENESS_CARD_FRONT_RESULT))
//                        resultMap.putString("strLivenessCardRear", intent?.getStringExtra(KeyResultConstants.LIVENESS_CARD_BACK_RESULT))
//                        resultMap.putString("strMaskFace", intent?.getStringExtra(KeyResultConstants.MASKED_FACE_RESULT))
//                        resultMap.putString("hashFront", intent?.getStringExtra(KeyResultConstants.HASH_IMAGE_FRONT))
//                        resultMap.putString("hashNearPortrait", intent?.getStringExtra(KeyResultConstants.HASH_IMAGE_FACE_NEAR))
//                        resultMap.putString("hashFarPortrait", intent?.getStringExtra(KeyResultConstants.HASH_IMAGE_FACE_FAR))
                        Log.v("test", "test")

                        mPromise?.resolve(intent?.getStringExtra(KeyResultConstants.COMPARE_FACE_RESULT))
                    }else{
                        mPromise?.reject("FAILED", "Failure")
                    }
                }
            }

            override fun onNewIntent(p0: Intent?) {}
        })
    }

    companion object {
        const val NAME = "NativeCalc"
    }
}