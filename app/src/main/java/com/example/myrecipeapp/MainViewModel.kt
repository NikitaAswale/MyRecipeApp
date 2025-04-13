


package com.example.myrecipeapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Error

class MainViewModel : ViewModel() {

    private val _categorieState = mutableStateOf(RecipeState())
    val CategoriesState : State<RecipeState> = _categorieState

    init{
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response = recipeServices.getCategories()
                /*_categorieState.value = _categorieState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null

                )*/
                _categorieState.value = RecipeState(
                    loading = false,
                    list = response.categories,
                    error  = null
                )

            }catch (e : Exception){
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}