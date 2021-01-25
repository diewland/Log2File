# Log2File

### Installation

add submodule at project home

```
git submodule add https://github.com/diewland/Log2File.git app/src/main/java/com/diewland/log2file
```

add storage permission in `AndroidManifest.xml`

```xml
<manifest ...>

  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

  <application ... />
</manifest>
```
