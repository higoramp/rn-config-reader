# 🛠 react-native-config-reader [![npm](https://img.shields.io/npm/v/rn-config-reader.svg)](https://npmjs.com/package/rn-config-reader)  [![npm](https://img.shields.io/npm/dm/rn-config-reader.svg)](https://npmjs.com/package/rn-config-reader)

A native library to access configuration variables from JS code.

based on [csath project](https://github.com/csath/react-native-config-reader)


For **react-native@0.60+** versions use **react-native-config-reader@4.0+**
(Autolinking support enabled now)


## Installation

For **rn 0.60+ Auto Linking** will do things for you.

If not follow these: 

1. `$ npm install rn-config-reader --save` or  `$ yarn add rn-config-reader`

2. `$ react-native link rn-config-reader`

3. Go to **android/app/src/main/packageName/MainApplication.java** and find line

   `new RNConfigReaderPackage()`

See [manual installation](#manual-installation) below if you have issues with `react-native link`.

## Usage
```javascript
import RNConfigReader from 'rn-config-reader';

// access any of the defined config variables in andoird build gradle or ios info.plist
const configValue = RNConfigReader.ANY_DEFINED_CONFIG_FIELD;


```

### More examples

Create a string-array item on  `strings.xml` file with the strings variables you want to expose to JS **(res/values/strings.xml)**

```xml
<resources>
    <string-array name="export_variables">
        <item>TEST_CONFIG_FIELD</item>
    </string-array>
    <string name="TEST_CONFIG_FIELD">ConfigValue</string>
</resources>
```
Create new field inside ios `info.plist` file

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">

<plist version="1.0"> 
<dict>
  	<key>CFBundleDisplayName</key>
	<string>com.react-native.react-native-config-reader</string>
  
	<key>TEST_CONFIG_FIELD</key>
	<string>"Hello I'm your test config value"</string>
</dict>
</plist>


```

Now you can acess them inside the JS code

```javascript
import { Platform } from 'react-native';
import RNConfigReader from 'rn-config-reader';

if(Platform.OS === 'ios') {
  const iosBundleDisplayName = RNConfigReader.CFBundleDisplayName;
  const testConfigValue = RNConfigReader.TEST_CONFIG_FIELD;
}

if(Platform.OS === 'android') {
  const androidApplicationID = RNConfigReader.applicationId;
  const testConfigValue = RNConfigReader.TEST_CONFIG_FIELD;
}


```

## Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-config-reader` and add `RNConfigReader.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNConfigReader.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.RNConfigReaderPackage;` to the imports at the top of the file
  - Add `new RNConfigReaderPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-config-reader'
  	project(':react-native-config-reader').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-config-reader/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-config-reader')
  	```

##### Android advanced configurations with Multiple environments

If your app uses an `applicationIdSuffix` or a different `applicationId` depending on the build variants, you must append the following line inside the `buildTypes` block in your `android/app/build.gradle` file and specify your new package name.

```
  resValue "string", "rn_config_reader_custom_package", "com.yourNewPackage"
```

Example

```
buildTypes {
  ...
  debug {
    ...
    applicationIdSuffix ".dev"
    resValue "string", "rn_config_reader_custom_package", "com.yourNewPackage"
  }
}
```

#### Windows (Beta)
[Read it!](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNConfigReader.sln` in `node_modules/react-native-config-reader/windows/RNConfigReader.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Config.Reader.RNConfigReader;` to the usings at the top of the file
  - Add `new RNConfigReaderPackage()` to the `List<IReactPackage>` returned by the `Packages` method

## Troubleshooting

### Problems with Proguard

When Proguard is enabled (which it is by default for Android release builds), it can rename the BuildConfig Java class in the minification process and prevent `react-native-config-reader` from referencing it. To avoid this, add an exception to android/app/proguard-rules.pro:

`-keep class com.yourNewPackage.BuildConfig { *; }`

com.yourNewPackage should match the package value in your app/src/main/AndroidManifest.xml file.

If using Dexguard, the shrinking phase will remove resources it thinks are unused. It is necessary to add an exception to preserve the build config package name.

`-keepresources string/rn_config_reader_custom_package`


## License
MIT License

Copyright (c) 2019 Chanaka Athurugiriya

  
