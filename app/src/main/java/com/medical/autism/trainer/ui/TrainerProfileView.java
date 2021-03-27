package com.medical.autism.trainer.ui;


import android.net.Uri;
import com.medical.autism.trainer.model.TrainerProfileData;

public interface TrainerProfileView {
    void getProfileSuccess(TrainerProfileData data);

    void getProfileFailed(String message);

    void updatePhotoSuccess(String message, Uri uri);

    void updatePhotoFailed(String message);

    void updateProfileSuccess(String message);

    void updateProfileFailed(String message);
}
