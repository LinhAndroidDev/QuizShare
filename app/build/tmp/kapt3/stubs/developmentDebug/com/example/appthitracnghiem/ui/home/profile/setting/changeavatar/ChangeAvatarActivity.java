package com.example.appthitracnghiem.ui.home.profile.setting.changeavatar;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.ActivityChangeAvatarBinding;
import com.example.appthitracnghiem.ui.base.BaseActivity;
import com.example.appthitracnghiem.ui.home.HomeActivity;
import com.example.appthitracnghiem.utils.Const;
import com.example.appthitracnghiem.utils.PreferenceKey;
import com.example.appthitracnghiem.utils.UriConvertFile;
import com.soundcloud.android.crop.Crop;
import com.squareup.picasso.Picasso;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import java.io.File;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 &2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001&B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\"\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0012\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J+\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00072\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016\u00a2\u0006\u0002\u0010 J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u0010H\u0002J\b\u0010%\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b\u00a8\u0006\'"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/profile/setting/changeavatar/ChangeAvatarActivity;", "Lcom/example/appthitracnghiem/ui/base/BaseActivity;", "Lcom/example/appthitracnghiem/ui/home/profile/setting/changeavatar/ChangeAvatarViewModel;", "()V", "binding", "Lcom/example/appthitracnghiem/databinding/ActivityChangeAvatarBinding;", "screenHeight", "", "getScreenHeight", "()I", "setScreenHeight", "(I)V", "screenWitch", "getScreenWitch", "setScreenWitch", "bindData", "", "initUi", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "saveImageToGallery", "bitmap", "Landroid/graphics/Bitmap;", "setSizeScreen", "setText", "Companion", "app_developmentDebug"})
public final class ChangeAvatarActivity extends com.example.appthitracnghiem.ui.base.BaseActivity<com.example.appthitracnghiem.ui.home.profile.setting.changeavatar.ChangeAvatarViewModel> {
    private com.example.appthitracnghiem.databinding.ActivityChangeAvatarBinding binding;
    private int screenWitch = 0;
    private int screenHeight = 0;
    @org.jetbrains.annotations.NotNull
    public static final com.example.appthitracnghiem.ui.home.profile.setting.changeavatar.ChangeAvatarActivity.Companion Companion = null;
    private static final int REQUEST_CODE_PERMISSIONS = 101;
    private static final int REQUEST_CODE_PICK_IMAGE = 102;
    
    public ChangeAvatarActivity() {
        super();
    }
    
    public final int getScreenWitch() {
        return 0;
    }
    
    public final void setScreenWitch(int p0) {
    }
    
    public final int getScreenHeight() {
        return 0;
    }
    
    public final void setScreenHeight(int p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setSizeScreen() {
    }
    
    @java.lang.Override
    public void bindData() {
    }
    
    private final void initUi() {
    }
    
    @java.lang.Override
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull
    int[] grantResults) {
    }
    
    @java.lang.Override
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
    
    private final void saveImageToGallery(android.graphics.Bitmap bitmap) {
    }
    
    private final void setText() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/profile/setting/changeavatar/ChangeAvatarActivity$Companion;", "", "()V", "REQUEST_CODE_PERMISSIONS", "", "REQUEST_CODE_PICK_IMAGE", "app_developmentDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}