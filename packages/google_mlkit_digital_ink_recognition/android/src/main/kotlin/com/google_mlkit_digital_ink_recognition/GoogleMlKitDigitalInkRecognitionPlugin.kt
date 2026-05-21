package com.google_mlkit_digital_ink_recognition

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class GoogleMlKitDigitalInkRecognitionPlugin :
    FlutterPlugin,
    MethodChannel.MethodCallHandler {
    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, CHANNEL_NAME)
        channel.setMethodCallHandler(DigitalInkRecognizer())
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onMethodCall(
        call: MethodCall,
        result: MethodChannel.Result,
    ) {
        result.notImplemented()
    }

    companion object {
        // Must match the channel name used by Dart side
        // (packages/google_mlkit_digital_ink_recognition/lib/src/digital_ink_recognizer.dart).
        // Kotlin migration (9d4b21f) accidentally used "_recognition" instead of
        // "_recognizer", which broke the channel on Android with:
        //   MissingPluginException(No implementation found for method
        //   vision#manageInkModels on channel google_mlkit_digital_ink_recognizer)
        private const val CHANNEL_NAME = "google_mlkit_digital_ink_recognizer"
    }
}
