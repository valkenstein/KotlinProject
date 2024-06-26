package org.example.project

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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