# Team21 AE Consulting — Android APK Build

> **© Innocent Forteh — Team21 Academy 2026**
> Build your Android APK entirely via GitHub — no Android Studio, no local tools required.

---

## 📋 What This Repository Does

This repo wraps the **Team21 AE Unified Excellence Platform** (HTML file) into a native Android APK using a WebView. Every time you push to `main`, GitHub Actions automatically builds and packages the APK. You download it as an artifact and install it directly on any Android device.

---

## 🚀 STEP-BY-STEP: Build Your APK on GitHub

### STEP 1 — Create a New GitHub Repository

1. Go to **https://github.com/new**
2. Name it: `team21-ae-app` (or any name you prefer)
3. Set it to **Private** (recommended) or Public
4. Do **NOT** initialise with README (you'll push this folder's contents)
5. Click **Create repository**

---

### STEP 2 — Upload Your Platform HTML File

The HTML platform file must be placed at:
```
app/src/main/assets/Team21_Unified_Platform.html
```

**Option A — Via GitHub web interface:**
1. In the repo, navigate to `app/src/main/assets/`
2. Click **Add file → Upload files**
3. Upload your `Team21_Unified_Platform.html`
4. This replaces the placeholder file already there

**Option B — Via command line:**
```bash
cp /path/to/Team21_Unified_Platform.html app/src/main/assets/
git add app/src/main/assets/Team21_Unified_Platform.html
git commit -m "Add platform HTML"
git push
```

---

### STEP 3 — Push All Files to GitHub

On your computer, open a terminal in this folder and run:

```bash
# Initialise git
git init

# Add GitHub remote (replace YOUR_USERNAME with your GitHub username)
git remote add origin https://github.com/YOUR_USERNAME/team21-ae-app.git

# Stage everything
git add .

# First commit
git commit -m "Initial Team21 AE Android app"

# Push to GitHub
git push -u origin main
```

> ⚠️ If your default branch is `master` instead of `main`, use `git push -u origin master`

---

### STEP 4 — Watch GitHub Actions Build Your APK

1. Go to your GitHub repository
2. Click the **Actions** tab
3. You will see a workflow called **"Build Team21 APK"** running
4. Click on it to watch the build progress in real time
5. The build takes approximately **3–5 minutes**

**Build stages you will see:**
- ✅ Checkout repository
- ✅ Set up JDK 17
- ✅ Cache Gradle packages
- ✅ Build Debug APK
- ✅ Upload Debug APK

---

### STEP 5 — Download Your APK

1. Once the build shows a green ✅ checkmark, click on it
2. Scroll to the bottom of the build page
3. Under **Artifacts**, you will see **Team21-Debug-APK**
4. Click it to download a ZIP file
5. Extract the ZIP — inside is `app-debug.apk`

---

### STEP 6 — Install on Android Device

1. Transfer `app-debug.apk` to your Android phone (via USB, WhatsApp, email, Google Drive)
2. On the Android device, go to **Settings → Security** (or **Settings → Apps → Special app access**)
3. Enable **"Install unknown apps"** for your file manager or browser
4. Open the APK file and tap **Install**
5. The app will appear on your home screen as **"Team21 AE"**

---

## 🔐 OPTIONAL: Build a Signed Release APK

A **signed APK** is required for Google Play Store and recommended for distribution. The debug APK works for direct installation.

### Generate a Keystore (one-time setup)

Run this on your computer (requires Java installed):

```bash
keytool -genkey -v \
  -keystore team21.keystore \
  -alias team21key \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000 \
  -dname "CN=Innocent Forteh, OU=Team21 Academy, O=Team21, L=Cameroon, ST=Cameroon, C=CM"
```

You will be prompted to set a **store password** and **key password**. Remember these.

### Add Secrets to GitHub

1. Go to your GitHub repository → **Settings → Secrets and variables → Actions**
2. Click **New repository secret** for each of the following:

| Secret Name | Value |
|-------------|-------|
| `KEYSTORE_BASE64` | Base64-encoded keystore file (see below) |
| `KEY_ALIAS` | `team21key` (or what you chose) |
| `KEY_PASSWORD` | Your key password |
| `STORE_PASSWORD` | Your store password |

**To get the Base64 keystore string:**
```bash
# On Mac/Linux:
base64 -i team21.keystore | pbcopy     # copies to clipboard (Mac)
base64 -i team21.keystore              # prints to terminal (Linux)

# On Windows PowerShell:
[Convert]::ToBase64String([IO.File]::ReadAllBytes("team21.keystore")) | clip
```

Paste the entire output as the value of `KEYSTORE_BASE64`.

### Trigger a New Build

Push any change (or manually trigger via Actions → Run workflow). The workflow will now produce **both** a debug and a signed release APK.

---

## 🏷️ OPTIONAL: Create a GitHub Release with Version Tag

To create a permanent, versioned release:

```bash
# Tag the commit
git tag v1.0.0
git push origin v1.0.0
```

GitHub Actions will automatically:
1. Build both APKs
2. Create a GitHub Release named "Team21 AE Platform v1.0.0"
3. Attach both APKs as downloadable release assets

Go to your repo → **Releases** to see and share the download links.

---

## 📁 Repository Structure

```
team21-ae-app/
│
├── .github/
│   └── workflows/
│       └── build-apk.yml          ← GitHub Actions build config
│
├── app/
│   ├── build.gradle               ← App build config (version, SDK, deps)
│   ├── proguard-rules.pro         ← Code optimisation rules
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml ← App permissions & config
│           ├── assets/
│           │   └── Team21_Unified_Platform.html  ← ⭐ YOUR PLATFORM FILE
│           ├── java/com/team21/consulting/
│           │   ├── MainActivity.java    ← WebView wrapper
│           │   └── SplashActivity.java  ← Splash screen
│           └── res/
│               ├── layout/
│               │   ├── activity_main.xml    ← Main screen layout
│               │   └── activity_splash.xml  ← Splash screen layout
│               ├── values/
│               │   ├── strings.xml   ← App text resources
│               │   ├── themes.xml    ← App theme (navy/gold colours)
│               │   └── colors.xml    ← Colour definitions
│               ├── mipmap-*/         ← App icons (all densities)
│               ├── drawable/         ← Vector icons
│               └── xml/              ← Backup rules
│
├── gradle/wrapper/
│   ├── gradle-wrapper.jar
│   └── gradle-wrapper.properties
│
├── build.gradle                  ← Root build config
├── settings.gradle               ← Project settings
├── gradle.properties             ← Gradle options
├── gradlew                       ← Gradle wrapper script (Unix)
├── .gitignore
└── README.md                     ← This file
```

---

## 🔄 Updating the App

Whenever you update your platform HTML file:

```bash
# Replace the HTML file
cp /path/to/new/Team21_Unified_Platform.html app/src/main/assets/

# Commit and push
git add app/src/main/assets/Team21_Unified_Platform.html
git commit -m "Update platform to v3.1"
git push
```

GitHub Actions builds a new APK automatically within minutes.

To bump the version number, edit `app/build.gradle`:
```gradle
versionCode 2          // Integer, increment by 1 each release
versionName "3.1"      // Human-readable version string
```

---

## ⚙️ App Technical Specs

| Setting | Value |
|---------|-------|
| Package Name | `com.team21.consulting` |
| Min Android | Android 5.0 (API 21) — covers 99%+ of devices |
| Target Android | Android 14 (API 34) |
| Orientation | Portrait |
| Permissions | Internet, Storage (for CSV downloads) |
| Data Storage | WebView localStorage (persists on device) |
| Offline | ✅ Fully offline — platform loaded from assets |
| WebView | AndroidX WebKit — latest Chromium engine |

---

## 🛠️ Troubleshooting

**Build fails — "Gradle wrapper not found"**
→ Make sure `gradle/wrapper/gradle-wrapper.jar` is committed (it is included in this repo).

**Build fails — "SDK not found"**
→ The GitHub Actions runner installs Android SDK automatically. No action needed.

**App installs but shows blank screen**
→ Ensure `Team21_Unified_Platform.html` is in `app/src/main/assets/` (not a subfolder).

**"App not installed" error on device**
→ Uninstall any previous version first, then reinstall the new APK.

**localStorage data lost after update**
→ Uninstalling the app clears all data. Always export CSV backups via the Admin Portal before updating.

**Build takes too long**
→ Gradle caching is enabled. The first build takes 5–8 minutes. Subsequent builds take 2–3 minutes.

---

## 📞 Support

**Platform:** Team21 AE Consulting — Unified Excellence Platform
**Developer:** Innocent Forteh
**Organisation:** Team21 Academy
**Copyright:** © Innocent Forteh 2026 — All rights reserved

> *"Those who wait on the LORD shall renew their strength; they shall mount up with wings as eagles."* — Isaiah 40:31
