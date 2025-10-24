package com.example.responsi1mobileh1d023054

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023054.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initlayout()
        initlistener()
    }

    private fun initlayout(){
        binding.layoutHistory.let{
            it.imgIcon.setImageResource(R.drawable.ic_bola)
            it.tvLayout.setText(R.string.menu1)
        }
        binding.layoutHeadCoach.let{
            it.imgIcon.setImageResource(R.drawable.ic_manager)
            it.tvLayout.setText(R.string.menu2)
        }
        binding.layoutTeamSquad.let{
            it.imgIcon.setImageResource(R.drawable.ic_squad)
            it.tvLayout.setText(R.string.menu3)
        }
    }

    private fun initlistener(){
        binding.layoutHistory.root.setOnClickListener{
            startActivity(Intent(this, ClubHistory::class.java))
        }
        binding.layoutHeadCoach.root.setOnClickListener{
            startActivity(Intent(this, HeadCoach::class.java))
        }
        binding.layoutTeamSquad.root.setOnClickListener{
            startActivity(Intent(this, TeamSquad::class.java))
        }
    }

}