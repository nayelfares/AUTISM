package com.medical.autism.parent.ui;


import com.medical.autism.parent.model.ParentProfileData;

public interface ParentProfileView {
    void getProfileSuccess(ParentProfileData data);

    void getProfileFailed(String message);
}
