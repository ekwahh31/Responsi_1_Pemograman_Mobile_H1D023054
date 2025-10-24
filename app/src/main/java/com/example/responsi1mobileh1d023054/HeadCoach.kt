package com.example.responsi1mobileh1d023054

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.responsi1mobileh1d023054.model.TeamResponse
import com.example.responsi1mobileh1d023054.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadCoach : AppCompatActivity() {
    
    private lateinit var tvCoachName: TextView
    private lateinit var tvCoachDob: TextView
    private lateinit var tvCoachCountry: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_head_coach)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvCoachName = findViewById(R.id.tv_coach_name)
        tvCoachDob = findViewById(R.id.tv_coach_dob)
        tvCoachCountry = findViewById(R.id.tv_coach_country)

        loadCoachData()
    }
    
    private fun loadCoachData() {
        val teamId = 17 // Ajax Amsterdam
        
        val apiService = ApiConfig.getApiService()
        apiService.getTeamById(teamId).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(
                call: Call<TeamResponse>,
                response: Response<TeamResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { teamResponse ->
                        teamResponse.coach?.let { coach ->
                            // Display coach data
                            tvCoachName.text = coach.name ?: "N/A"
                            tvCoachDob.text = coach.dateOfBirth ?: "N/A"
                            tvCoachCountry.text = coach.nationality ?: "N/A"
                            
                            Log.d("HeadCoach", "Coach loaded: ${coach.name}")
                        } ?: run {
                            Toast.makeText(
                                this@HeadCoach,
                                "Coach data not available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = "Failed to load: ${response.code()}\n$errorBody"
                    Toast.makeText(
                        this@HeadCoach,
                        "Error ${response.code()}: Cek API Token!",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("HeadCoach", errorMessage)
                }
            }
            
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Toast.makeText(
                    this@HeadCoach,
                    "Error: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("HeadCoach", "Failed to load data", t)
            }
        })
    }
}