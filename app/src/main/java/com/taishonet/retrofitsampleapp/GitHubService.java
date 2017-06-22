package com.taishonet.retrofitsampleapp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{login}")
    Observable<User> getUser(@Path("login") String login);
}
