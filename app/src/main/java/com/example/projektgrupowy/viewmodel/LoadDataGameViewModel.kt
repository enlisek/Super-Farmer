package com.example.projektgrupowy.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.projektgrupowy.model.APIconnection.ApiRequest
import com.example.projektgrupowy.model.APIconnection.PlayerRemoteRepository
import com.example.projektgrupowy.model.APIconnection.PlayerRemoteService
import com.example.projektgrupowy.model.Player
import com.example.projektgrupowy.model.PlayerWithoutId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.await

class LoadDataGameViewModel(application: Application): AndroidViewModel(application)  {
    var myResponse:MutableLiveData<Response<Player>> = MutableLiveData()

    fun addPlayer(id: Long,
                  playerName: String,
                  numberOfRabbit: Int,
                  numberOfPig: Int,
                  numberOfSheep: Int,
                  numberOfCow: Int,
                  numberOfHorse: Int,
                  numberOfDog: Int,
                  numberOfBigDog: Int){
        GlobalScope.launch(Dispatchers.IO) {
            val r1 = PlayerRemoteService.api.getAllPlayers().await()
            for (i in r1){
                Log.v(":)))",i.toString())
            }
            var playerHelp= PlayerWithoutId(
            playerName,
            numberOfRabbit,
            numberOfPig,
            numberOfSheep,
            numberOfCow,
            numberOfHorse,
            numberOfDog,
            numberOfBigDog
            )

           var call =  PlayerRemoteService.api.addPlayer(playerHelp)
            var response = call.execute()
            Log.v("Response", response.body().toString())
//            player = response.body()
//            player.id = 314;
//            Log.d("xD", player.toString())

        }

    }

}