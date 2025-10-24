package com.example.responsi1mobileh1d023054.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023054.R
import com.example.responsi1mobileh1d023054.adapter.PlayerAdapter
import com.example.responsi1mobileh1d023054.model.TeamResponse
import com.example.responsi1mobileh1d023054.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamSquadFragment : Fragment() {
    
    private lateinit var rvPlayers: RecyclerView
    private lateinit var playerAdapter: PlayerAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, 
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_squad, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        rvPlayers = view.findViewById(R.id.rv_players)
        setupRecyclerView()
        loadTeamData()
    }
    
    private fun setupRecyclerView() {
        playerAdapter = PlayerAdapter(emptyList()) { player ->
            PlayerAdapter.showPlayerBottomSheet(player, requireView())
        }
        
        rvPlayers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = playerAdapter
        }
    }
    
    private fun loadTeamData() {
        val teamId = 17 // Ajax Amsterdam
        
        val apiService = ApiConfig.getApiService()
        apiService.getTeamById(teamId).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(
                call: Call<TeamResponse>,
                response: Response<TeamResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { teamResponse ->
                        // Filter hanya pemain (bukan coach)
                        val players = teamResponse.squad.filter { 
                            it.position != null && it.position.isNotEmpty() 
                        }
                        playerAdapter.updatePlayers(players)
                        
                        Log.d("TeamSquadFragment", "Loaded ${players.size} players")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = "Failed to load: ${response.code()}\n$errorBody"
                    Toast.makeText(
                        context,
                        "Error ${response.code()}: Cek API Token!",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("TeamSquadFragment", errorMessage)
                }
            }
            
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Error: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("TeamSquadFragment", "Failed to load data", t)
            }
        })
    }
    
    companion object {
        fun newInstance() = TeamSquadFragment()
    }
}
