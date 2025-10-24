package com.example.responsi1mobileh1d023054.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023054.R
import com.example.responsi1mobileh1d023054.model.Squad
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.card.MaterialCardView

class PlayerAdapter(
    private var players: List<Squad>,
    private val onPlayerClick: (Squad) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardPlayer: MaterialCardView = view.findViewById(R.id.card_player)
        val tvPlayerName: TextView = view.findViewById(R.id.tv_player_name)
        val tvPlayerNationality: TextView = view.findViewById(R.id.tv_player_nationality)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.tvPlayerName.text = player.name
        holder.tvPlayerNationality.text = player.nationality
        
        // Set card color based on position
        val colorResId = getColorByPosition(player.position)
        holder.cardPlayer.setCardBackgroundColor(
            ContextCompat.getColor(holder.itemView.context, colorResId)
        )
        
        holder.itemView.setOnClickListener {
            onPlayerClick(player)
        }
    }

    override fun getItemCount(): Int = players.size

    fun updatePlayers(newPlayers: List<Squad>) {
        players = newPlayers
        notifyDataSetChanged()
    }

    private fun getColorByPosition(position: String?): Int {
        if (position == null) return R.color.positionDefault
        
        val positionLower = position.lowercase()
        
        return when {
            positionLower.contains("goalkeeper") || 
            positionLower.contains("goalie") -> R.color.positionGoalkeeper

            positionLower.contains("defender") ||
            positionLower.contains("defence") ||
            positionLower.contains("back") ||
            positionLower.contains("centre back") ||
            positionLower.contains("central defender") -> R.color.positionDefender

            positionLower.contains("midfielder") ||
            positionLower.contains("midfield") ||
            positionLower.contains("playmaker") -> R.color.positionMidfielder

            positionLower.contains("forward") ||
            positionLower.contains("striker") ||
            positionLower.contains("attacker") ||
            positionLower.contains("offence") ||
            positionLower.contains("winger") ||
            positionLower.contains("wing") -> R.color.positionForward
            
            else -> R.color.positionDefault
        }
    }
    
    companion object {
        fun showPlayerBottomSheet(player: Squad, view: View) {
            val context = view.context
            val bottomSheetDialog = BottomSheetDialog(context)
            val bottomSheetView = LayoutInflater.from(context)
                .inflate(R.layout.player_info, null)
            
            bottomSheetView.findViewById<TextView>(R.id.tv_bs_name).text = player.name
            bottomSheetView.findViewById<TextView>(R.id.tv_bs_dob).text = 
                "Date of Birth: ${player.dateOfBirth}"
            bottomSheetView.findViewById<TextView>(R.id.tv_bs_country).text = 
                "Nationality: ${player.nationality}"
            bottomSheetView.findViewById<TextView>(R.id.tv_bs_position).text = 
                "Position: ${player.position ?: "Not specified"}"
            
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }
}
