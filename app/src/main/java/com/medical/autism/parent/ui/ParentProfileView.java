package com.medical.autism.parent.ui;


import android.net.Uri;

import com.medical.autism.parent.model.ParentProfileData;

public interface ParentProfileView {
    void getProfileSuccess(ParentProfileData data);

    void getProfileFailed(String message);

    void updatePhotoSuccess(String message, Uri uri);

    void updatePhotoFailed(String message);

    void updateProfileSuccess(String message);

    void updateProfileFailed(String message);
}
