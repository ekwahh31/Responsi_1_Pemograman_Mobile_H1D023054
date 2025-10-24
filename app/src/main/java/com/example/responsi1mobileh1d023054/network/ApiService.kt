package com.example.responsi1mobileh1d023054.network

import com.example.responsi1mobileh1d023054.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("teams/{id}")
    fun getTeamById(
        @Path("id") teamId: Int
    ): Call<TeamResponse>
}
