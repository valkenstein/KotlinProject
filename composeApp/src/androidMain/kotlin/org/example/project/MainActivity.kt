package org.example.project

import App
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import domain.BonusOperationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import network.di.KoinInjector
import org.koin.java.KoinJavaComponent.inject
import presentation.component.AppProject

class MainActivity : ComponentActivity() {
    //private val bonusOperationUseCase: BonusOperationUseCase by inject()
    private var scope = CoroutineScope(Dispatchers.Main + SupervisorJob())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        KoinInjector.koinApp.androidContext(this)
//        Log.e("bonusOperationUseCase11", bonusOperationUseCase.toString())
//        println(bonusOperationUseCase)
       // getBonus()
        setContent {
            AppProject(false, false)

        }
    }

//    private fun getBonus() {
//        scope.launch {
//            try {
//                val r = bonusOperationUseCase.getBonus()
//                println(r)
//            } catch (e: Exception) {
//                println(e.printStackTrace())
//
//            }
//        }
//    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}