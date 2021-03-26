package com.medical.autism.parent.api;

import com.medical.autism.ApiManager;

public class ParentApiManager {
    public static ParentService parentService = ApiManager.retrofit.create(ParentService.class);
}
