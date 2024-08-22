package com.mobil80.pokemon.presentation.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobil80.pokemon.presentation.viewmodel.PokiDetailScreen
import com.mobil80.pokemon.presentation.viewmodel.PokemonDetailViewModel
import com.mobil80.pokemon.presentation.viewmodel.PokemonListScreen
import com.mobil80.pokemon.presentation.viewmodel.PokemonListViewModel
import com.mobil80.pokemon.presentation.ui.theme.JetpackComposePokedexTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    private val detailViewModel by lazy { getViewModel<PokemonDetailViewModel>() }
    private val listViewModel by lazy { getViewModel<PokemonListViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "pokemon_list_screen"
                ) {
                    composable("pokemon_list_screen") {
                        PokemonListScreen(navController = navController, viewModel = listViewModel)
                    }
                    composable(
                        "pokemon_detail_screen/{dominantColor}/{pokemonId}",
                        arguments = listOf(
                            navArgument("pokemonId") {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        val pokemonId = remember {
                            it.arguments?.getInt("pokemonId")
                        }
                        Log.d("viv", "onCreate characterId: $pokemonId")
                        PokiDetailScreen(
                            characterId = pokemonId ?: 0,
                            navController = navController,
                            viewModel = detailViewModel
                        )
                    }
                }
            }
        }
    }
}