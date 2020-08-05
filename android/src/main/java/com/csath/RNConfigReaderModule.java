
package com.csath;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.reflect.Field;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;

import java.util.Map;
import java.util.HashMap;
import android.content.res.XmlResourceParser;

public class RNConfigReaderModule extends ReactContextBaseJavaModule {
  public RNConfigReaderModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNConfigReader";
  }

  @Override
  public Map<String, Object> getConstants() {
      final Map<String, Object> constants = new HashMap<>();
      Context context = getReactApplicationContext();
      int idVariables = context.getResources().getIdentifier("export_variables", "array", context.getPackageName());

      if (idVariables != 0){
        String[] variables = context.getResources().getStringArray(idVariables);
        for (String varName : variables){
          int id = context.getResources().getIdentifier(varName, "string", context.getPackageName());
          constants.put(varName, context.getResources().getString(id));
        }
      }else{
        Log.d("ReactNative", "RNConfigReader: " + idVariables);
      }
      
      return constants;
  }


}